package com.yuntai.upp.access.restapi.fresh.barcode;

import com.alibaba.fastjson.TypeReference;
import com.yuntai.upp.access.UppAccessApplication;
import com.yuntai.upp.access.restapi.fresh.AbstractControllerTest;
import com.yuntai.upp.client.basic.util.UUIDUtil;
import com.yuntai.upp.client.fresh.model.bo.Outcome;
import com.yuntai.upp.client.fresh.model.dto.barcode.BarcodeDto;
import com.yuntai.upp.client.fresh.model.vo.barcode.BarcodeVo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @description 单元测试-正交易(条码)
 * @className BarcodeTest
 * @package com.yuntai.upp.access.restapi.fresh.barcode
 * @author jinren@hsyuntai.com
 * @date 2019-07-29 10:22
 * @copyright 版权归 HSYUNTAI 所有
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UppAccessApplication.class})
public class BarcodeTest extends AbstractControllerTest<BarcodeVo> {

    private static final String URI = "/access/barcode";

    @Test
    public void normalAllRoute() {
        Outcome outcome = tcp(URI, BarcodeDto.builder()
                .authCode("")
                .paymentNo(UUIDUtil.create())
                .tradeFee(new BigDecimal(0.01D).setScale(2, BigDecimal.ROUND_HALF_UP))
                .subject("条码支付(upp-client)")
                .expireTime(LocalDateTime.now().plusMinutes(5))
                .build(),
                new TypeReference<Outcome<BarcodeVo>>() {
                });

        Assert.assertTrue(outcome.isResult());
    }
//
//    @Test
//    public void normalAllBussiness() {
//        BarcodeDto dto = BarcodeDto.builder()
//                .authCode("")
//                .paymentNo(UUIDUtil.create())
//                .tradeFee(new BigDecimal(0.01D).setScale(2, BigDecimal.ROUND_HALF_UP))
//                .subject("条码支付(upp-client)")
//                .expireTime(LocalDateTime.now().plusMinutes(5))
//                .build();
//
//        // 1.报文转换(客户端 DTO -> 云端 DTO)
//        UnitedPaymentParam param = BarcodeParam.convert(dto);
//        // 2.报文推送至云端, 接收云端响应, 响应报文 -> 云端 VO
//        UnitedPaymentResult result = HdpClientInstance.send(CmdType.BARCODE, param, UnitedPaymentResult.class);
//        // 3.参数校验(云端 VO)
//        BarcodeVerify.verify(result);
//        // 4.报文转换(云端 VO -> 客户端 VO)
//        return BarcodeResult.convert(result);
//    }
}
