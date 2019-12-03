package com.yuntai.upp.access.outdated.tunnel;

import com.yuntai.upp.access.fresh.mock.OrderQueryMock;
import com.yuntai.upp.access.outdated.AbstractSoapui;
import com.yuntai.upp.access.outdated.mock.BarCodeMock;
import com.yuntai.upp.access.util.MockUtil;
import com.yuntai.upp.client.basic.enums.inner.InnerCmdType;
import com.yuntai.upp.client.basic.enums.outer.OuterBizCodeType;
import com.yuntai.upp.client.config.hdp.HdpClientInstance;
import com.yuntai.upp.client.fresh.model.dto.orderquery.OrderQueryDto;
import com.yuntai.upp.client.outdated.model.dto.barcode.BarCodeDto;
import com.yuntai.upp.client.outdated.model.vo.barcode.BarCodeVo;
import com.yuntai.upp.client.outdated.model.ws.ReceiverGeneric;
import com.yuntai.upp.sdk.param.UnitedOrderQueryParam;
import com.yuntai.upp.sdk.param.UnitedPaymentParam;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import javax.validation.constraints.NotNull;
import java.util.Arrays;

/**
 * @description 单元测试-条码支付
 * @className BarCodeTest
 * @package com.yuntai.upp.access.outdated.tunnel
 * @author jinren@hsyuntai.com
 * @date 2019/12/2 16:47
 * @copyright 版权归 HSYUNTAI 所有
 */
public class BarCodeTest extends AbstractSoapui<BarCodeDto, ReceiverGeneric<BarCodeVo>> {

    /**
     * @description 字段缺失
     * @param
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/12/2 17:18
     */
    @Test
    @Override
    public void testDefect() {
        Arrays.stream(BarCodeDto.class.getDeclaredFields())
                .forEach(field -> Arrays.stream(field.getDeclaredAnnotations())
                        .filter(MockUtil::filter)
                        .forEach(annotation -> {
                            BarCodeDto model = MockUtil.mock(BarCodeMock.normal(), field, annotation);
                            Object object = send(model, OuterBizCodeType.S0002, ReceiverGeneric.class);
                            Assert.assertNotNull(object);
                            assert object instanceof ReceiverGeneric;
                            ReceiverGeneric<BarCodeVo> outcome = (ReceiverGeneric<BarCodeVo>) object;
                            Assert.assertNotNull(outcome);
                            Assert.assertNotNull(outcome.getHeader());
                            Assert.assertEquals(outcome.getHeader().getMsg(), FAIL, outcome.getHeader().getCode());
                            Assert.assertNull(outcome.getBody());
                        }));
    }

    /**
     * @description 正常场景
     * @param
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/12/2 17:18
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
     * @date 2019/12/2 17:18
     */
    @Test
    @Override
    public void testMock() {
        BarCodeDto dto = BarCodeMock.normal();
        PowerMockito.when(HdpClientInstance.send(Mockito.any(InnerCmdType.class),
                Mockito.any(UnitedOrderQueryParam.class), Mockito.any()))
                .thenReturn(OrderQueryMock.mock(OrderQueryDto.convert(dto)));
        PowerMockito.when(HdpClientInstance.send(Mockito.any(InnerCmdType.class),
                Mockito.any(UnitedPaymentParam.class), Mockito.any()))
                .thenReturn(BarCodeMock.mock(dto));
        execute(dto);
    }

    /**
     * @description 执行 & 校验(正常场景)
     * @param dto 入参模型
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/12/2 17:19
     */
    private void execute(@NotNull BarCodeDto dto) {
        Object object = send(dto, OuterBizCodeType.S0002, ReceiverGeneric.class);
        Assert.assertNotNull(object);
        assert object instanceof ReceiverGeneric;
        ReceiverGeneric<BarCodeVo> outcome = (ReceiverGeneric<BarCodeVo>) object;
        Assert.assertNotNull(outcome);
        Assert.assertNotNull(outcome.getHeader());
        Assert.assertEquals(outcome.getHeader().getMsg(), SUCCESS, outcome.getHeader().getCode());
        Assert.assertNotNull(outcome.getBody());
    }
}