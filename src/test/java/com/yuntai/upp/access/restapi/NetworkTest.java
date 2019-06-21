package com.yuntai.upp.access.restapi;

import com.yuntai.upp.access.AbstractRestapiClient;
import com.yuntai.upp.access.ProviderBoot;
import com.yuntai.upp.model.dto.access.network.NetworkDto;
import com.yuntai.upp.support.enums.AccessCmdType;
import com.yuntai.upp.support.util.DateUtil;
import com.yuntai.upp.support.util.HttpUtil;
import com.yuntai.upp.support.util.JaxbUtil;
import com.yuntai.upp.support.util.UUIDUtil;
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

    @Test
    public void testXml() {
        HttpUtil.post(HttpUtil.Atom.builder()
                .url("http://localhost:7000/hs-access-facepay/FacePayRestService/facePayService")
                .content(HttpUtil.CONTENT_TEXT)
                .accept(HttpUtil.ACCEPT_JSON)
                .data(MessageFormat.format(TEMPLATE,
                        AccessCmdType.S0000.getCode(),
                        MessageFormat.format(REQUEST,
                                UUIDUtil.createUUID(),
                                AccessCmdType.S0000.getDesc(),
                                UUIDUtil.createUUID(),
                                JaxbUtil.xml(NetworkDto.builder()
                                        .field(LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateUtil.DEFAULT_FORMAT)))
                                        .build()))))
                .build());
    }

    @Test
    public void testJSON() {
        HttpUtil.post(HttpUtil.Atom.builder()
                .url("http://localhost:7000/hs-access-facepay/FacePayRestService/facePayService/JSON")
                .content(HttpUtil.CONTENT_TEXT)
                .accept(HttpUtil.ACCEPT_JSON)
                .data(MessageFormat.format(TEMPLATE,
                        AccessCmdType.S0000.getCode(),
                        MessageFormat.format(REQUEST,
                                UUIDUtil.createUUID(),
                                AccessCmdType.S0000.getDesc(),
                                UUIDUtil.createUUID(),
                                JaxbUtil.xml(NetworkDto.builder()
                                        .field(LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateUtil.DEFAULT_FORMAT)))
                                        .build()))))
                .build());
    }
}
