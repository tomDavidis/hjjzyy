package com.yuntai.upp.access.ws;

import com.yuntai.upp.access.AbstractWsClinet;
import com.yuntai.upp.access.ProviderBoot;
import com.yuntai.upp.client.basic.enums.outer.BizCodeType;
import com.yuntai.upp.client.outdated.model.dto.network.NetworkDto;
import com.yuntai.upp.client.outdated.model.ws.SenderObject;
import com.yuntai.upp.client.outdated.model.ws.SenderPack;
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
 * @package com.yuntai.upp.access.ws
 * @author jinren@hsyuntai.com
 * @date 2019-06-14 13:40
 * @copyright 版权归 HSYUNTAI 所有
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProviderBoot.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class NetworkTest extends AbstractWsClinet {

    @Test
    public void test() {
        HttpUtil.post(HttpUtil.Atom.builder()
                .url(URL_WS)
                .content(HttpUtil.CONTENT_XML)
                .accept(HttpUtil.ACCEPT_XML)
                .data(MessageFormat.format(TEMPLATE, JaxbUtil.xml(SenderObject.builder()
                        .pack(SenderPack.builder()
                                .code(BizCodeType.S0000.getCode())
                                .cell(MessageFormat.format(REQUEST,
                                        UUIDUtil.createUUID(),
                                        BizCodeType.S0000.getCmdType().getDesc(),
                                        UUIDUtil.createUUID(),
                                        JaxbUtil.xml(NetworkDto.builder()
                                                .field(LocalDateTime.now().plusSeconds(0).format(DateTimeFormatter.ofPattern(DateUtil.DEFAULT_FORMAT)))
                                                .build())))
                                .build())
                        .build())))
                .build());
    }
}
