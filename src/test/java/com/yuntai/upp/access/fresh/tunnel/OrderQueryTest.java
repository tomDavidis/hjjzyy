package com.yuntai.upp.access.fresh.tunnel;

import com.alibaba.fastjson.TypeReference;
import com.yuntai.upp.access.UppAccessApplication;
import com.yuntai.upp.access.fresh.AbstractSoapui;
import com.yuntai.upp.access.fresh.mock.OrderQueryMock;
import com.yuntai.upp.access.util.MessageUtil;
import com.yuntai.upp.access.util.MockUtil;
import com.yuntai.upp.client.config.hdp.HdpClientInstance;
import com.yuntai.upp.client.fresh.model.bo.Outcome;
import com.yuntai.upp.client.fresh.model.dto.orderquery.OrderQueryDto;
import com.yuntai.upp.client.fresh.model.vo.orderquery.OrderQueryVo;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static com.yuntai.upp.client.config.constant.ConstantInstance.ORDER_QUERY;

/**
 * @description 单元测试-订单查询
 * @className OrderQueryTest
 * @package com.yuntai.upp.access.fresh.tunnel
 * @author jinren@hsyuntai.com
 * @date 2019/11/22 10:11
 * @copyright 版权归 HSYUNTAI 所有
 */
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringRunner.class)
@PowerMockIgnore({"javax.*.*", "com.sun.*", "org.*"})
@PrepareForTest({HdpClientInstance.class})
@SpringBootTest(classes = {UppAccessApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class OrderQueryTest extends AbstractSoapui<OrderQueryDto, Outcome<OrderQueryVo>> {

    /**
     * @description 正常场景
     * @param
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/11/22 15:26
     */
    @Ignore
    @Test
    @Override
    public void testNormal() {
        Outcome<OrderQueryVo> outcome = send(OrderQueryMock.normal(), ORDER_QUERY, new TypeReference<Outcome<OrderQueryVo>>() {});
        Assert.assertNotNull(outcome);
        /*
         * 实体内含有非基础数据类型, 解析有点问题, 暂不支持验签
         */
        Assert.assertTrue(outcome.isResult());
        Assert.assertEquals(SUCCESS, outcome.getKind());
    }

    /**
     * @description 正常场景
     * @param
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/11/22 10:11
     */
    @Test
    @Override
    public void testMock() {
//        Outcome<OrderQueryVo> outcome = send(OrderQueryMock.normal(), ORDER_QUERY, new TypeReference<Outcome<OrderQueryVo>>() {});
//        Assert.assertNotNull(outcome);
//        /*
//         * 实体内含有非基础数据类型, 解析有点问题, 暂不支持验签
//         */
//        Assert.assertTrue(outcome.isResult());
//        Assert.assertEquals(SUCCESS, outcome.getKind());
    }

    /**
     * @description 字段缺失
     * @param
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/11/22 10:11
     */
    @Test
    @Override
    public void testDefect() {
        Arrays.stream(OrderQueryDto.class.getDeclaredFields())
                .forEach(field -> Arrays.stream(field.getDeclaredAnnotations())
                        .filter(MockUtil::filter)
                        .forEach(annotation -> {
                            OrderQueryDto model = MockUtil.mock(OrderQueryMock.normal(), field, annotation);
                            Outcome<OrderQueryVo> outcome = send(model, ORDER_QUERY, new TypeReference<Outcome<OrderQueryVo>>() {});
                            Assert.assertNotNull(outcome);
                            Assert.assertEquals(FAIL, outcome.getKind());
                            Assert.assertEquals(outcome.getMsg(), MessageUtil.message(model));
                        }));
    }
}