//package com.yuntai.upp.access.fresh.access.paymentcallback;
//
//import com.yuntai.upp.access.UppAccessApplication;
//import com.yuntai.upp.access.fresh.access.AbstractAccess;
//import com.yuntai.upp.client.basic.enums.inner.InnerCmdType;
//import com.yuntai.upp.client.fresh.model.bo.Outcome;
//import com.yuntai.upp.client.fresh.model.dto.paymentcallback.PaymentCallbackDto;
//import com.yuntai.upp.client.fresh.model.vo.paymentcallback.PaymentCallbackVo;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
///**
// * @description 单元测试-交易通知(正交易)
// * @className PaymentCallbackTest
// * @package com.yuntai.upp.access.fresh.access.paymentcallback
// * @author jinren@hsyuntai.com
// * @date 2019/11/8 16:23
// * @copyright 版权归 HSYUNTAI 所有
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {UppAccessApplication.class})
//public class PaymentCallbackTest extends AbstractAccess<PaymentCallbackDto, PaymentCallbackVo> {
//
//    /**
//     * @description 正常场景-数据
//     * @param
//     * @return void
//     * @author jinren@hsyuntai.com
//     * @date 2019/11/8 17:35
//     */
//    @Test
//    @Override
//    public void testNormal() {
//        Outcome<PaymentCallbackVo> outcome = super.send(InnerCmdType.PAYMENT_CALLBACK,
//                PaymentCallbackMock.normal(),
//                Boolean.FALSE);
//        Assert.assertTrue(outcome.isResult());
//    }
//}
