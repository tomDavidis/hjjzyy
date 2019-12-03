package com.yuntai.upp.access.fresh.restapi;

import com.alibaba.fastjson.TypeReference;
import com.yuntai.upp.access.fresh.AbstractRestapi;
import com.yuntai.upp.access.fresh.mock.BarCodeMock;
import com.yuntai.upp.access.util.MockUtil;
import com.yuntai.upp.client.basic.enums.inner.InnerCmdType;
import com.yuntai.upp.client.config.cache.CacheInstance;
import com.yuntai.upp.client.config.hdp.HdpClientInstance;
import com.yuntai.upp.client.fresh.model.bo.Outcome;
import com.yuntai.upp.client.fresh.model.dto.barcode.BarCodeDto;
import com.yuntai.upp.client.fresh.model.vo.barcode.BarCodeVo;
import com.yuntai.upp.sdk.interfaces.Signable;
import com.yuntai.upp.sdk.result.UnitedPaymentResult;
import com.yuntai.upp.sdk.util.SignUtil;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import javax.validation.constraints.NotNull;
import java.util.Arrays;

import static com.yuntai.upp.client.config.constant.ConstantInstance.ISV_ID;
import static com.yuntai.upp.client.config.constant.ConstantInstance.PARTNER_ID;

/**
 * @description 单元测试-交易退款
 * @className BarCodeTest
 * @package com.yuntai.upp.access.fresh.restapi
 * @author jinren@hsyuntai.com
 * @date 2019/12/2 10:04
 * @copyright 版权归 HSYUNTAI 所有
 */
public class BarCodeTest extends AbstractRestapi<BarCodeDto, Outcome<BarCodeVo>> {

    /**
     * 接口路径
     */
    private static final String URL = "/access/barCode";

    /**
     * @description 字段缺失
     * @param
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/12/2 10:06
     */
    @Test
    @Override
    public void testDefect() {
        Arrays.stream(BarCodeDto.class.getDeclaredFields())
                .forEach(field -> Arrays.stream(field.getDeclaredAnnotations())
                        .filter(MockUtil::filter)
                        .forEach(annotation -> {
                            BarCodeDto model = MockUtil.mock(BarCodeMock.normal(), field, annotation);
                            Outcome<BarCodeVo> outcome = send(URL, model, new TypeReference<Outcome<BarCodeVo>>() {});
                            Assert.assertNotNull(outcome);
                            Assert.assertEquals(FAIL, outcome.getKind());
                        }));
    }

    /**
     * @description 正常场景
     * @param
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/12/2 10:06
     */
    @Ignore
    @Test
    @Override
    public void testNormal() {
        execute(BarCodeMock.normal());
    }

    /**
     * @description 正常场景
     * @param
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/12/2 10:06
     */
    @Test
    @Override
    public void testMock() {
        BarCodeDto dto = BarCodeMock.normal();
        UnitedPaymentResult result = BarCodeMock.mock(dto);
        PowerMockito.when(HdpClientInstance.send(Mockito.any(InnerCmdType.class), Mockito.any(Signable.class), Mockito.any()))
                .thenReturn(result);
        execute(dto);
    }

    /**
     * @description 执行 & 校验(正常场景)
     * @param dto 入参模型
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/12/2 10:06
     */
    private void execute(@NotNull BarCodeDto dto) {
        Outcome<BarCodeVo> outcome = send(URL, dto, new TypeReference<Outcome<BarCodeVo>>() {});
        Assert.assertNotNull(outcome);
        Assert.assertTrue(SignUtil.verifyMd5(outcome, CacheInstance.md5Salt(PARTNER_ID, ISV_ID)));
        Assert.assertTrue(outcome.isResult());
        Assert.assertEquals(SUCCESS, outcome.getKind());
    }
}
