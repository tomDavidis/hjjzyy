//package com.yuntai.upp.access.fresh.access.present;
//
//import com.yuntai.upp.access.UppAccessApplication;
//import com.yuntai.upp.access.fresh.access.AbstractAccess;
//import com.yuntai.upp.client.basic.enums.inner.InnerCmdType;
//import com.yuntai.upp.client.fresh.model.bo.Outcome;
//import com.yuntai.upp.client.fresh.model.dto.present.PresentDto;
//import com.yuntai.upp.client.fresh.model.vo.present.PresentVo;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
///**
// * @description 单元测试-实时交易
// * @className PresentTest
// * @package com.yuntai.upp.access.fresh.access
// * @author jinren@hsyuntai.com
// * @date 2019-06-24 16:27
// * @copyright 版权归 HSYUNTAI 所有
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {UppAccessApplication.class})
//public class PresentTest extends AbstractAccess<PresentDto, PresentVo> {
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
//        Outcome<PresentVo> outcome = super.send(InnerCmdType.PRESENT,
//                PresentMock.normal(),
//                Boolean.FALSE);
//        Assert.assertTrue(outcome.isResult());
//    }
//}
