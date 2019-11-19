//package com.yuntai.upp.access.fresh.access.refundcallback;
//
//import com.yuntai.upp.access.UppAccessApplication;
//import com.yuntai.upp.access.fresh.access.AbstractAccess;
//import com.yuntai.upp.client.basic.enums.inner.InnerCmdType;
//import com.yuntai.upp.client.fresh.model.bo.Outcome;
//import com.yuntai.upp.client.fresh.model.dto.refundcallback.RefundCallbackDto;
//import com.yuntai.upp.client.fresh.model.vo.refundcallback.RefundCallbackVo;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
///**
// * @description 单元测试-交易通知(负交易)
// * @className PaymentCallbackTest
// * @package com.yuntai.upp.access.fresh.access
// * @author jinren@hsyuntai.com
// * @date 2019-09-06 11:13
// * @copyright 版权归 HSYUNTAI 所有
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {UppAccessApplication.class})
//public class RefundCallbackTest extends AbstractAccess<RefundCallbackDto, RefundCallbackVo> {
//
//    /**
//     * @description 正常场景-数据
//     * @param
//     * @return void
//     * @author jinren@hsyuntai.com
//     * @date 2019/11/8 17:36
//     */
//    @Test
//    @Override
//    public void testNormal() {
//        Outcome<RefundCallbackVo> outcome = super.send(InnerCmdType.REFUND_CALLBACK,
//                RefundCallbackMock.normal(),
//                Boolean.FALSE);
//        Assert.assertTrue(outcome.isResult());
//    }
//}
