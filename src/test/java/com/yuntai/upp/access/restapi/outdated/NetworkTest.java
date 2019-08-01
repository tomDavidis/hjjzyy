package com.yuntai.upp.access.restapi.outdated;

import com.yuntai.upp.access.AbstractRestapiClient;
import com.yuntai.upp.access.ProviderBoot;
import com.yuntai.upp.client.basic.enums.outer.OuterBizCodeType;
import com.yuntai.upp.client.basic.util.DateUtil;
import com.yuntai.upp.client.basic.util.HttpUtil;
import com.yuntai.upp.client.basic.util.JaxbUtil;
import com.yuntai.upp.client.basic.util.UUIDUtil;
import com.yuntai.upp.client.outdated.model.dto.network.NetworkDto;
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
 * @package com.yuntai.upp.access.restapi.outdated
 * @author jinren@hsyuntai.com
 * @date 2019-07-26 14:46
 * @copyright 版权归 HSYUNTAI 所有
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProviderBoot.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class NetworkTest extends AbstractRestapiClient {

    @Test
    public void testXml() {
        HttpUtil.post(HttpUtil.Atom.builder()
                .url(URL_XML)
                .content(HttpUtil.CONTENT_TEXT)
                .accept(HttpUtil.ACCEPT_JSON)
                .data(MessageFormat.format(TEMPLATE,
                        OuterBizCodeType.S0000.getCode(),
                        MessageFormat.format(REQUEST,
                                UUIDUtil.create(),
                                OuterBizCodeType.S0000.getCmdType().getDesc(),
                                UUIDUtil.create(),
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
                        OuterBizCodeType.S0000.getCode(),
                        MessageFormat.format(REQUEST,
                                UUIDUtil.create(),
                                OuterBizCodeType.S0000.getCmdType().getDesc(),
                                UUIDUtil.create(),
                                JaxbUtil.xml(NetworkDto.builder()
                                        .field(LocalDateTime.now().plusSeconds(0).format(DateTimeFormatter.ofPattern(DateUtil.DEFAULT_FORMAT)))
                                        .build()))))
                .build());
    }
}
