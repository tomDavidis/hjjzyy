package com.yuntai.upp.access.outdated.tunnel;

import com.yuntai.upp.access.outdated.AbstractSoapui;
import com.yuntai.upp.access.outdated.mock.RefundMock;
import com.yuntai.upp.access.util.MockUtil;
import com.yuntai.upp.client.basic.enums.inner.InnerCmdType;
import com.yuntai.upp.client.basic.enums.outer.OuterBizCodeType;
import com.yuntai.upp.client.config.hdp.HdpClientInstance;
import com.yuntai.upp.client.outdated.model.dto.refund.RefundDto;
import com.yuntai.upp.client.outdated.model.vo.refund.RefundVo;
import com.yuntai.upp.client.outdated.model.ws.ReceiverGeneric;
import com.yuntai.upp.sdk.interfaces.Signable;
import com.yuntai.upp.sdk.result.UnitedRefundResult;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import javax.validation.constraints.NotNull;
import java.util.Arrays;

/**
 * @description 单元测试-交易退款
 * @className RefundTest
 * @package com.yuntai.upp.access.outdated.tunnel
 * @author jinren@hsyuntai.com
 * @date 2019/12/2 16:49
 * @copyright 版权归 HSYUNTAI 所有
 */
public class RefundTest extends AbstractSoapui<RefundDto, ReceiverGeneric<RefundVo>> {

    /**
     * @description 字段缺失
     * @param
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/12/3 10:47
     */
    @Test
    @Override
    public void testDefect() {
        Arrays.stream(RefundDto.class.getDeclaredFields())
                .forEach(field -> Arrays.stream(field.getDeclaredAnnotations())
                        .filter(MockUtil::filter)
                        .forEach(annotation -> {
                            RefundDto model = MockUtil.mock(RefundMock.normal(), field, annotation);
                            Object object = send(model, OuterBizCodeType.S0005, ReceiverGeneric.class);
                            Assert.assertNotNull(object);
                            assert object instanceof ReceiverGeneric;
                            ReceiverGeneric<RefundVo> outcome = (ReceiverGeneric<RefundVo>) object;
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
     * @date 2019/12/3 10:47
     */
    @Ignore
    @Test
    @Override
    public void testNormal() {
        execute(RefundMock.normal());
    }

    /**
     * @description 正常场景
     * @param
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/12/3 10:47
     */
    @Test
    @Override
    public void testMock() {
        RefundDto dto = RefundMock.normal();
        UnitedRefundResult result = RefundMock.mock(dto);
        PowerMockito.when(HdpClientInstance.send(Mockito.any(InnerCmdType.class), Mockito.any(Signable.class), Mockito.any()))
                .thenReturn(result);
        execute(dto);
    }

    /**
     * @description 执行 & 校验(正常场景)
     * @param dto 入参模型
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/12/3 10:47
     */
    private void execute(@NotNull RefundDto dto) {
        Object object = send(dto, OuterBizCodeType.S0005, ReceiverGeneric.class);
        Assert.assertNotNull(object);
        assert object instanceof ReceiverGeneric;
        ReceiverGeneric<RefundVo> outcome = (ReceiverGeneric<RefundVo>) object;
        Assert.assertNotNull(outcome);
        Assert.assertNotNull(outcome.getHeader());
        Assert.assertEquals(outcome.getHeader().getMsg(), SUCCESS, outcome.getHeader().getCode());
        Assert.assertNotNull(outcome.getBody());
    }
}
