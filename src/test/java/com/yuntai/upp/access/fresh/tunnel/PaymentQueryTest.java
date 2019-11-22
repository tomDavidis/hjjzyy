package com.yuntai.upp.access.fresh.tunnel;

import com.alibaba.fastjson.TypeReference;
import com.yuntai.upp.access.UppAccessApplication;
import com.yuntai.upp.access.fresh.AbstractSoapui;
import com.yuntai.upp.access.fresh.mock.PaymentQueryMock;
import com.yuntai.upp.access.util.MessageUtil;
import com.yuntai.upp.access.util.MockUtil;
import com.yuntai.upp.client.config.cache.CacheInstance;
import com.yuntai.upp.client.config.hdp.HdpClientInstance;
import com.yuntai.upp.client.fresh.model.bo.Outcome;
import com.yuntai.upp.client.fresh.model.dto.paymentquery.PaymentQueryDto;
import com.yuntai.upp.client.fresh.model.vo.paymentquery.PaymentQueryVo;
import com.yuntai.upp.sdk.util.SignUtil;
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

import static com.yuntai.upp.client.config.constant.ConstantInstance.ISV_ID;
import static com.yuntai.upp.client.config.constant.ConstantInstance.PARTNER_ID;
import static com.yuntai.upp.client.config.constant.ConstantInstance.PAYMENT_QUERY;

/**
 * @description 单元测试-正交易查询
 * @className PaymentQueryTest
 * @package com.yuntai.upp.access.fresh.tunnel
 * @author jinren@hsyuntai.com
 * @date 2019/11/22 13:29
 * @copyright 版权归 HSYUNTAI 所有
 */
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringRunner.class)
@PowerMockIgnore({"javax.*.*", "com.sun.*", "org.*"})
@PrepareForTest({HdpClientInstance.class})
@SpringBootTest(classes = {UppAccessApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PaymentQueryTest extends AbstractSoapui<PaymentQueryDto, Outcome<PaymentQueryVo>> {

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
        Outcome<PaymentQueryVo> outcome = send(PaymentQueryMock.normal(), PAYMENT_QUERY, new TypeReference<Outcome<PaymentQueryVo>>() {});
        Assert.assertNotNull(outcome);
        Assert.assertTrue(SignUtil.verifyMd5(outcome, CacheInstance.md5Salt(PARTNER_ID, ISV_ID)));
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
//        Outcome<PaymentQueryVo> outcome = send(PaymentQueryMock.normal(), PAYMENT_QUERY, new TypeReference<Outcome<PaymentQueryVo>>() {});
//        Assert.assertNotNull(outcome);
//        Assert.assertTrue(SignUtil.verifyMd5(outcome, CacheInstance.md5Salt(PARTNER_ID, ISV_ID)));
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
        Arrays.stream(PaymentQueryDto.class.getDeclaredFields())
                .forEach(field -> Arrays.stream(field.getDeclaredAnnotations())
                        .filter(MockUtil::filter)
                        .forEach(annotation -> {
                            PaymentQueryDto model = MockUtil.mock(PaymentQueryMock.normal(), field, annotation);
                            Outcome<PaymentQueryVo> outcome = send(model, PAYMENT_QUERY, new TypeReference<Outcome<PaymentQueryVo>>() {});
                            Assert.assertNotNull(outcome);
                            Assert.assertEquals(FAIL, outcome.getKind());
                            Assert.assertEquals(outcome.getMsg(), MessageUtil.message(model));
                        }));
    }
}