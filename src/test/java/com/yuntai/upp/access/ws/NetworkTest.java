package com.yuntai.upp.access.ws;

import com.yuntai.upp.access.AbstractWsClinet;
import com.yuntai.upp.access.ProviderBoot;
import com.yuntai.upp.model.dto.access.network.NetworkDto;
import com.yuntai.upp.model.ws.SenderObject;
import com.yuntai.upp.model.ws.SenderPack;
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
                .url("http://localhost:7000/hs-access-facepay/services/facePayWebService")
                .content(HttpUtil.CONTENT_XML)
                .accept(HttpUtil.ACCEPT_XML)
                .data(MessageFormat.format(TEMPLATE, JaxbUtil.xml(SenderObject.builder()
                        .pack(SenderPack.builder()
                                .code(AccessCmdType.S0000.getCode())
                                .cell(MessageFormat.format(REQUEST,
                                        UUIDUtil.createUUID(),
                                        AccessCmdType.S0000.getDesc(),
                                        UUIDUtil.createUUID(),
                                        JaxbUtil.xml(NetworkDto.builder()
                                                .field(LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateUtil.DEFAULT_FORMAT)))
                                                .build())))
                                .build())
                        .build())))
                .build());
    }
}
