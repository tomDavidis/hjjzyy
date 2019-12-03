package com.yuntai.upp.access.outdated.tunnel;

import com.yuntai.upp.access.outdated.AbstractSoapui;
import com.yuntai.upp.access.outdated.mock.QueryMock;
import com.yuntai.upp.access.util.MockUtil;
import com.yuntai.upp.client.basic.enums.inner.InnerCmdType;
import com.yuntai.upp.client.basic.enums.outer.OuterBizCodeType;
import com.yuntai.upp.client.config.hdp.HdpClientInstance;
import com.yuntai.upp.client.outdated.model.dto.query.QueryDto;
import com.yuntai.upp.client.outdated.model.vo.query.QueryVo;
import com.yuntai.upp.client.outdated.model.ws.ReceiverGeneric;
import com.yuntai.upp.sdk.interfaces.Signable;
import com.yuntai.upp.sdk.result.UnitedPaymentQueryResult;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import javax.validation.constraints.NotNull;
import java.util.Arrays;

/**
 * @description 单元测试-交易查询
 * @className PaymentQueryTest
 * @package com.yuntai.upp.access.outdated.tunnel
 * @author jinren@hsyuntai.com
 * @date 2019/12/2 16:48
 * @copyright 版权归 HSYUNTAI 所有
 */
public class QueryTest extends AbstractSoapui<QueryDto, ReceiverGeneric<QueryVo>> {

    /**
     * @description 字段缺失
     * @param
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/12/3 10:40
     */
    @Test
    @Override
    public void testDefect() {
        Arrays.stream(QueryDto.class.getDeclaredFields())
                .forEach(field -> Arrays.stream(field.getDeclaredAnnotations())
                        .filter(MockUtil::filter)
                        .forEach(annotation -> {
                            QueryDto model = MockUtil.mock(QueryMock.normal(), field, annotation);
                            Object object = send(model, OuterBizCodeType.S0003, ReceiverGeneric.class);
                            Assert.assertNotNull(object);
                            assert object instanceof ReceiverGeneric;
                            ReceiverGeneric<QueryVo> outcome = (ReceiverGeneric<QueryVo>) object;
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
     * @date 2019/12/3 10:40
     */
    @Ignore
    @Test
    @Override
    public void testNormal() {
        execute(QueryMock.normal());
    }

    /**
     * @description 正常场景
     * @param
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/12/3 10:40
     */
    @Test
    @Override
    public void testMock() {
        QueryDto dto = QueryMock.normal();
        UnitedPaymentQueryResult result = QueryMock.mock(dto);
        PowerMockito.when(HdpClientInstance.send(Mockito.any(InnerCmdType.class), Mockito.any(Signable.class), Mockito.any()))
                .thenReturn(result);
        execute(dto);
    }

    /**
     * @description 执行 & 校验(正常场景)
     * @param dto 入参模型
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/12/3 10:40
     */
    private void execute(@NotNull QueryDto dto) {
        Object object = send(dto, OuterBizCodeType.S0003, ReceiverGeneric.class);
        Assert.assertNotNull(object);
        assert object instanceof ReceiverGeneric;
        ReceiverGeneric<QueryVo> outcome = (ReceiverGeneric<QueryVo>) object;
        Assert.assertNotNull(outcome);
        Assert.assertNotNull(outcome.getHeader());
        Assert.assertEquals(outcome.getHeader().getMsg(), SUCCESS, outcome.getHeader().getCode());
        Assert.assertNotNull(outcome.getBody());
    }
}