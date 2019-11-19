package com.yuntai.upp.access.util;

import com.yuntai.upp.client.basic.util.ValidatorUtil;
import org.hibernate.validator.HibernateValidator;
import org.junit.Assert;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.text.MessageFormat;
import java.util.Set;

/**
 * @description 字段校验工具
 * @className MessageUtil
 * @package com.yuntai.upp.access.util
 * @author jinren@hsyuntai.com
 * @date 2019/11/19 10:08
 * @copyright 版权归 HSYUNTAI 所有
 */
public class MessageUtil {

    /**
     * 开启快速结束模式 fail_fast (true)
     */
    private static Validator validator = Validation
            .byProvider(HibernateValidator.class)
            .configure()
            .failFast(true)
            .buildValidatorFactory()
            .getValidator();

    /**
     * 校验对象
     *
     * @param t bean
     * @param groups 校验组
     * @return ValidResult
     */
    public static <T> String message(T t, Class<?>... groups) {
        Set<ConstraintViolation<T>> set = validator.validate(t, groups);
        Assert.assertNotNull(set);
        ConstraintViolation<T> violation = set.iterator().next();
        return MessageFormat.format(ValidatorUtil.TEMPLATE, violation.getPropertyPath().toString(), violation.getMessage());
    }
}
