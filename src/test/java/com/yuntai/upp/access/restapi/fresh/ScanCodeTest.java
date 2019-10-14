package com.yuntai.upp.access.restapi.fresh;

import com.alibaba.fastjson.JSON;
import com.yuntai.upp.access.AbstractRestapiClient;
import com.yuntai.upp.access.UppAccessApplication;
import com.yuntai.upp.client.basic.util.HttpUtil;
import com.yuntai.upp.client.basic.util.UUIDUtil;
import com.yuntai.upp.client.fresh.model.dto.scancode.ScanCodeDto;
import com.yuntai.upp.sdk.enums.ChannelProductType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @description 正交易(扫码)
 * @className ScanCodeTest
 * @package com.yuntai.upp.access.restapi.fresh
 * @author jinren@hsyuntai.com
 * @date 2019-07-26 14:53
 * @copyright 版权归 HSYUNTAI 所有
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UppAccessApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ScanCodeTest extends AbstractRestapiClient {

    private static final String URL = "http://127.0.0.1:7000/hs-access-facepay/access/scancode";

    @Test
    public void test() {
        HttpUtil.post(HttpUtil.Atom.builder()
                .url(URL)
                .content(HttpUtil.CONTENT_JSON)
                .accept(HttpUtil.ACCEPT_JSON)
                .data(JSON.toJSONString(ScanCodeDto.builder()
                        .paymentNo(UUIDUtil.create())
                        .tradeFee(new BigDecimal(0.01D).setScale(2, BigDecimal.ROUND_HALF_UP))
                        .channelProduct(ChannelProductType.ALI_BAR_CODE.getCode())
                        .subject("条码支付(upp-client)")
                        .expireTime(LocalDateTime.now().plusMinutes(5))
                        .build()))
                .build());
    }
}
