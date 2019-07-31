package com.yuntai.upp.access.restapi.fresh;

import com.alibaba.fastjson.JSON;
import com.yuntai.upp.access.AbstractRestapiClient;
import com.yuntai.upp.access.ProviderBoot;
import com.yuntai.upp.client.basic.util.HttpUtil;
import com.yuntai.upp.client.basic.util.UUIDUtil;
import com.yuntai.upp.client.fresh.model.dto.close.CloseDto;
import com.yuntai.upp.sdk.enums.BizType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description 单元测试-交易关闭
 * @className CloseTest
 * @package com.yuntai.upp.access.restapi.fresh
 * @author jinren@hsyuntai.com
 * @date 2019-07-26 14:52
 * @copyright 版权归 HSYUNTAI 所有
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProviderBoot.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CloseTest extends AbstractRestapiClient {

    private static final String URL = "http://127.0.0.1:7000/hs-access-facepay/access/close";

    @Test
    public void test() {
        HttpUtil.post(HttpUtil.Atom.builder()
                .url(URL)
                .content(HttpUtil.CONTENT_JSON)
                .accept(HttpUtil.ACCEPT_JSON)
                .data(JSON.toJSONString(CloseDto.builder()
                        .paymentNo(UUIDUtil.create())
                        .bizType(BizType.SCAN_PAY.getCode())
                        .build()))
                .build());
    }
}
