package com.yuntai.upp.access.restapi.fresh;

import com.alibaba.fastjson.JSON;
import com.yuntai.upp.access.AbstractRestapiClient;
import com.yuntai.upp.access.ProviderBoot;
import com.yuntai.upp.client.basic.util.HttpUtil;
import com.yuntai.upp.client.basic.util.UUIDUtil;
import com.yuntai.upp.client.fresh.model.dto.refund.RefundDto;
import com.yuntai.upp.sdk.enums.BizType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * @description 单元测试-交易退款
 * @className RefundTest
 * @package com.yuntai.upp.access.restapi.fresh
 * @author jinren@hsyuntai.com
 * @date 2019-07-26 14:53
 * @copyright 版权归 HSYUNTAI 所有
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProviderBoot.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RefundTest extends AbstractRestapiClient {

    private static final String URL = "http://127.0.0.1:7000/hs-access-facepay/access/refund";

    @Test
    public void test() {
        HttpUtil.post(HttpUtil.Atom.builder()
                .url(URL)
                .content(HttpUtil.CONTENT_JSON)
                .accept(HttpUtil.ACCEPT_JSON)
                .data(JSON.toJSONString(RefundDto.builder()
                        .paymentNo(UUIDUtil.createUUID())
                        .refundNo(UUIDUtil.createUUID())
                        .requestNo(UUIDUtil.createUUID())
                        .refundFee(new BigDecimal(0.01D).setScale(2, BigDecimal.ROUND_HALF_UP))
                        .bizType(BizType.SCAN_PAY.getCode())
                        .build()))
                .build());
    }
}
