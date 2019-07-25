package com.yuntai.upp.access.restapi;

import com.alibaba.fastjson.JSON;
import com.yuntai.upp.access.AbstractRestapiClient;
import com.yuntai.upp.access.ProviderBoot;
import com.yuntai.upp.client.basic.enums.inner.CmdType;
import com.yuntai.upp.client.basic.model.dto.query.QueryDto;
import com.yuntai.upp.client.basic.util.HttpUtil;
import com.yuntai.upp.client.basic.util.JaxbUtil;
import com.yuntai.upp.client.basic.util.UUIDUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.MessageFormat;

/**
 * @description 单元测试-查询
 * @className QueryTest
 * @package com.yuntai.upp.access.restapi
 * @author jinren@hsyuntai.com
 * @date 2019-07-04 10:25
 * @copyright 版权归 HSYUNTAI 所有
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProviderBoot.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class QueryTest extends AbstractRestapiClient {

    private static final String URL = "http://127.0.0.1:7000/hs-access-facepay/access/query";

    @Test
    public void testXml() {
        HttpUtil.post(HttpUtil.Atom.builder()
                .url(URL_XML)
                .content(HttpUtil.CONTENT_TEXT)
                .accept(HttpUtil.ACCEPT_JSON)
                .data(MessageFormat.format(TEMPLATE,
                        CmdType.S0003.getCode(),
                        MessageFormat.format(REQUEST,
                                UUIDUtil.createUUID(),
                                CmdType.S0003.getDesc(),
                                UUIDUtil.createUUID(),
                                JaxbUtil.xml(QueryDto.builder()
                                        /* 当前配置为单机, isv 标识与商户标识可为空 */
//                                                .isvId(0L)
//                                                .partnerId(0L)
                                        .paymentNo(UUIDUtil.createUUID())
                                        /* 以下为特殊渠道|附加参数, 可为空 */
//                                                .expandData("")
                                        .build()))))
                .build());
    }

    @Test
    public void testJSON() {
        HttpUtil.post(HttpUtil.Atom.builder()
                .url(URL_JSON)
                .content(HttpUtil.CONTENT_TEXT)
                .accept(HttpUtil.ACCEPT_JSON)
                .data(MessageFormat.format(TEMPLATE,
                        CmdType.S0003.getCode(),
                        MessageFormat.format(REQUEST,
                                UUIDUtil.createUUID(),
                                CmdType.S0003.getDesc(),
                                UUIDUtil.createUUID(),
                                JaxbUtil.xml(QueryDto.builder()
                                        /* 当前配置为单机, isv 标识与商户标识可为空 */
//                                                .isvId(0L)
//                                                .partnerId(0L)
                                        .paymentNo(UUIDUtil.createUUID())
                                        /* 以下为特殊渠道|附加参数, 可为空 */
//                                                .expandData("")
                                        .build()))))
                .build());
    }

    @Test
    public void testJSON2() {
        HttpUtil.post(HttpUtil.Atom.builder()
                .url(URL)
                .content(HttpUtil.CONTENT_JSON)
                .accept(HttpUtil.ACCEPT_JSON)
                .data(JSON.toJSONString(QueryDto.builder()
                        /* 当前配置为单机, isv 标识与商户标识可为空 */
//                                                .isvId(0L)
//                                                .partnerId(0L)
                        .paymentNo(UUIDUtil.createUUID())
                        /* 以下为特殊渠道|附加参数, 可为空 */
//                                                .expandData("")
                        .build()))
                .build());
    }
}
