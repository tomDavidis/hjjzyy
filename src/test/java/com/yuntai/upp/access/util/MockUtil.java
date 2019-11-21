package com.yuntai.upp.access.util;

import com.yuntai.upp.client.basic.annotation.EnumOf;
import com.yuntai.upp.client.basic.annotation.Future;
import com.yuntai.upp.client.basic.util.BigDecimalUtil;
import com.yuntai.upp.client.basic.util.LoggerUtil;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.junit.Assert;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlElement;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @description 模拟工具类
 * @className MockUtil
 * @package com.yuntai.upp.access.util
 * @author jinren@hsyuntai.com
 * @date 2019/11/19 14:57
 * @copyright 版权归 HSYUNTAI 所有
 */
@Slf4j
public class MockUtil {

    /**
     * @description 判断当前注解是否需要进行校验
     * @param annotation 注解
     * @return boolean
     * @author jinren@hsyuntai.com
     * @date 2019/11/21 11:39
     */
    public static boolean filter(@NonNull Annotation annotation) {
        return !(annotation instanceof XmlElement);
    }

    /**
     * @description 数据模拟
     * @param model 模型
     * @param field 字段
     * @param annotation 注解
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/11/19 14:53
     */
    public static <I> I mock(@NonNull I model,
                  @NonNull Field field,
                  @NonNull Annotation annotation) {
        field.setAccessible(true);
        try {
            if (annotation instanceof NotNull) {
                field.set(model, null);
            } else if (annotation instanceof NotBlank) {
                field.set(model, "");
            } else if (annotation instanceof Length) {
                field.set(model, RandomStringUtils.randomAlphanumeric(((Length) annotation).max() + 1));
            } else if (annotation instanceof EnumOf) {
                field.set(model, "test");
            } else if (annotation instanceof Future) {
                field.set(model, LocalDateTime.now());
            } else if (annotation instanceof Range && Objects.equals(field.getType(), Long.class)) {
                field.set(model, -1L);
            } else if (annotation instanceof Range && Objects.equals(field.getType(), Integer.class)) {
                field.set(model, -1);
            } else if (annotation instanceof PastOrPresent) {
                field.set(model, LocalDateTime.now().plusDays(1));
            } else if (annotation instanceof Pattern) {
                field.set(model, ",test");
            } else if (annotation instanceof Digits) {
                field.set(model, BigDecimalUtil.convert(Math.pow(10, (((Digits) annotation).integer() + 1))));
            } else if (annotation instanceof DecimalMin) {
                field.set(model, BigDecimalUtil.convert(((DecimalMin) annotation).value()).subtract(BigDecimal.ONE).setScale(2, BigDecimal.ROUND_HALF_UP));
            } else {
                LoggerUtil.error(LOGGER, "field: {0}, validate: {1}", field.getName(), annotation.annotationType().getSimpleName());
            }
        } catch (Exception exception) {
            LoggerUtil.error(exception, LOGGER);
            Assert.fail(exception.getMessage());
        }
        return model;
    }
}
