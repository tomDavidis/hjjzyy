package com.yuntai.upp.access.restapi;

import com.alibaba.fastjson.JSON;
import com.yuntai.upp.access.AbstractRestapiClient;
import com.yuntai.upp.access.ProviderBoot;
import com.yuntai.upp.client.basic.model.dto.network.NetworkDto;
import com.yuntai.upp.client.basic.enums.CmdType;
import com.yuntai.upp.client.basic.util.DateUtil;
import com.yuntai.upp.client.basic.util.HttpUtil;
import com.yuntai.upp.client.basic.util.JaxbUtil;
import com.yuntai.upp.client.basic.util.UUIDUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @description 单元测试-网络测试
 * @className NetworkTest
 * @package com.yuntai.upp.access.restapi
 * @author jinren@hsyuntai.com
 * @date 2019-06-20 09:14
 * @copyright 版权归 HSYUNTAI 所有
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProviderBoot.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class NetworkTest extends AbstractRestapiClient {

    private static final String URL = "http://127.0.0.1:7000/hs-access-facepay/access/network";

    @Test
    public void testXml() {
        HttpUtil.post(HttpUtil.Atom.builder()
                .url(URL_XML)
                .content(HttpUtil.CONTENT_TEXT)
                .accept(HttpUtil.ACCEPT_JSON)
                .data(MessageFormat.format(TEMPLATE,
                        CmdType.S0000.getCode(),
                        MessageFormat.format(REQUEST,
                                UUIDUtil.createUUID(),
                                CmdType.S0000.getDesc(),
                                UUIDUtil.createUUID(),
                                JaxbUtil.xml(NetworkDto.builder()
                                        .field(LocalDateTime.now().plusSeconds(0).format(DateTimeFormatter.ofPattern(DateUtil.DEFAULT_FORMAT)))
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
                        CmdType.S0000.getCode(),
                        MessageFormat.format(REQUEST,
                                UUIDUtil.createUUID(),
                                CmdType.S0000.getDesc(),
                                UUIDUtil.createUUID(),
                                JaxbUtil.xml(NetworkDto.builder()
                                        .field(LocalDateTime.now().plusSeconds(0).format(DateTimeFormatter.ofPattern(DateUtil.DEFAULT_FORMAT)))
                                        .build()))))
                .build());
    }

    @Test
    public void testJSON2() {
        HttpUtil.post(HttpUtil.Atom.builder()
                .url(URL)
                .content(HttpUtil.CONTENT_JSON)
                .accept(HttpUtil.ACCEPT_JSON)
                .data(JSON.toJSONString(NetworkDto.builder()
                        .field(LocalDateTime.now().plusSeconds(0).format(DateTimeFormatter.ofPattern(DateUtil.DEFAULT_FORMAT)))
                        .build()))
                .build());
    }
}
