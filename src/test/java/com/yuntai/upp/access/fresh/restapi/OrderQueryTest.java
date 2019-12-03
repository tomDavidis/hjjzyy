package com.yuntai.upp.access.fresh.restapi;

import com.alibaba.fastjson.TypeReference;
import com.yuntai.upp.access.fresh.AbstractRestapi;
import com.yuntai.upp.access.fresh.mock.OrderQueryMock;
import com.yuntai.upp.access.util.MockUtil;
import com.yuntai.upp.client.basic.enums.inner.InnerCmdType;
import com.yuntai.upp.client.config.hdp.HdpClientInstance;
import com.yuntai.upp.client.fresh.model.bo.Outcome;
import com.yuntai.upp.client.fresh.model.dto.orderquery.OrderQueryDto;
import com.yuntai.upp.client.fresh.model.vo.orderquery.OrderQueryVo;
import com.yuntai.upp.sdk.interfaces.Signable;
import com.yuntai.upp.sdk.result.UnitedOrderQueryResult;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import javax.validation.constraints.NotNull;
import java.util.Arrays;

/**
 * @description 单元测试-订单查询
 * @className OrderQueryTest
 * @package com.yuntai.upp.access.fresh.restapi
 * @author jinren@hsyuntai.com
 * @date 2019/12/2 10:05
 * @copyright 版权归 HSYUNTAI 所有
 */
public class OrderQueryTest extends AbstractRestapi<OrderQueryDto, Outcome<OrderQueryVo>> {

    /**
     * 接口路径
     */
    private static final String URL = "/access/orderQuery";

    /**
     * @description 字段缺失
     * @param
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/12/2 10:20
     */
    @Test
    @Override
    public void testDefect() {
        Arrays.stream(OrderQueryDto.class.getDeclaredFields())
                .forEach(field -> Arrays.stream(field.getDeclaredAnnotations())
                        .filter(MockUtil::filter)
                        .forEach(annotation -> {
                            OrderQueryDto model = MockUtil.mock(OrderQueryMock.normal(), field, annotation);
                            Outcome<OrderQueryVo> outcome = send(URL, model, new TypeReference<Outcome<OrderQueryVo>>() {});
                            Assert.assertNotNull(outcome);
                            Assert.assertEquals(FAIL, outcome.getKind());
                        }));
    }

    /**
     * @description 正常场景
     * @param
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/12/2 10:20
     */
    @Ignore
    @Test
    @Override
    public void testNormal() {
        execute(OrderQueryMock.normal());
    }

    /**
     * @description 正常场景
     * @param
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/12/2 10:20
     */
    @Test
    @Override
    public void testMock() {
        OrderQueryDto dto = OrderQueryMock.normal();
        UnitedOrderQueryResult result = OrderQueryMock.mock(dto);
        PowerMockito.when(HdpClientInstance.send(Mockito.any(InnerCmdType.class), Mockito.any(Signable.class), Mockito.any()))
                .thenReturn(result);
        execute(dto);
    }

    /**
     * @description 执行 & 校验(正常场景)
     * @param dto 入参模型
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/12/2 10:20
     */
    private void execute(@NotNull OrderQueryDto dto) {
        Outcome<OrderQueryVo> outcome = send(URL, dto, new TypeReference<Outcome<OrderQueryVo>>() {});
        Assert.assertNotNull(outcome);
        /*
         * 实体内含有非基础数据类型, 解析有点问题, 暂不支持验签
         */
        Assert.assertTrue(outcome.isResult());
        Assert.assertEquals(SUCCESS, outcome.getKind());
    }
}