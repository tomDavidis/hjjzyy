package com.yuntai.upp.access.outdated.tunnel;

import com.yuntai.upp.access.outdated.AbstractSoapui;
import com.yuntai.upp.access.outdated.mock.ScanCodeMock;
import com.yuntai.upp.access.util.MockUtil;
import com.yuntai.upp.client.basic.enums.inner.InnerCmdType;
import com.yuntai.upp.client.basic.enums.outer.OuterBizCodeType;
import com.yuntai.upp.client.config.hdp.HdpClientInstance;
import com.yuntai.upp.client.outdated.model.dto.scancode.ScanCodeDto;
import com.yuntai.upp.client.outdated.model.vo.scancode.ScanCodeVo;
import com.yuntai.upp.client.outdated.model.ws.ReceiverGeneric;
import com.yuntai.upp.sdk.param.UnitedPaymentParam;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import javax.validation.constraints.NotNull;
import java.util.Arrays;

/**
 * @description 单元测试-扫码支付
 * @className ScanCodeTest
 * @package com.yuntai.upp.access.outdated.tunnel
 * @author jinren@hsyuntai.com
 * @date 2019/12/2 16:49
 * @copyright 版权归 HSYUNTAI 所有
 */
public class ScanCodeTest extends AbstractSoapui<ScanCodeDto, ReceiverGeneric<ScanCodeVo>> {

    /**
     * @description 字段缺失
     * @param
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/12/3 10:48
     */
    @Test
    @Override
    public void testDefect() {
        Arrays.stream(ScanCodeDto.class.getDeclaredFields())
                .forEach(field -> Arrays.stream(field.getDeclaredAnnotations())
                        .filter(MockUtil::filter)
                        .forEach(annotation -> {
                            ScanCodeDto model = MockUtil.mock(ScanCodeMock.scanCode(), field, annotation);
                            Object object = send(model, OuterBizCodeType.S0001_0, ReceiverGeneric.class);
                            Assert.assertNotNull(object);
                            assert object instanceof ReceiverGeneric;
                            ReceiverGeneric<ScanCodeVo> outcome = (ReceiverGeneric<ScanCodeVo>) object;
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
     * @date 2019/12/3 10:49
     */
    @Ignore
    @Test
    @Override
    public void testNormal() {
        execute(ScanCodeMock.scanCode());
    }

    /**
     * @description 正常场景
     * @param
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/12/3 10:49
     */
    @Test
    @Override
    public void testMock() {
        ScanCodeDto dto = ScanCodeMock.scanCode();
        PowerMockito.when(HdpClientInstance.send(Mockito.any(InnerCmdType.class),
                Mockito.any(UnitedPaymentParam.class), Mockito.any()))
                .thenReturn(ScanCodeMock.scanCode(dto));
        execute(dto);
    }

    /**
     * @description 执行 & 校验(正常场景)
     * @param dto 入参模型
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/12/3 10:49
     */
    private void execute(@NotNull ScanCodeDto dto) {
        Object object = send(dto, OuterBizCodeType.S0001_0, ReceiverGeneric.class);
        Assert.assertNotNull(object);
        assert object instanceof ReceiverGeneric;
        ReceiverGeneric<ScanCodeVo> outcome = (ReceiverGeneric<ScanCodeVo>) object;
        Assert.assertNotNull(outcome);
        Assert.assertNotNull(outcome.getHeader());
        Assert.assertEquals(outcome.getHeader().getMsg(), SUCCESS, outcome.getHeader().getCode());
        Assert.assertNotNull(outcome.getBody());
    }
}

