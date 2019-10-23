package com.yuntai.upp.access.ws;

import com.yuntai.upp.access.AbstractWsClinet;
import com.yuntai.upp.access.UppAccessApplication;
import com.yuntai.upp.client.basic.enums.outer.OuterBizCodeType;
import com.yuntai.upp.client.basic.util.HttpUtil;
import com.yuntai.upp.client.basic.util.JaxbUtil;
import com.yuntai.upp.client.basic.util.UUIDUtil;
import com.yuntai.upp.client.outdated.model.dto.network.NetworkDto;
import com.yuntai.upp.client.outdated.model.ws.SenderPack;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.MessageFormat;
import java.time.LocalDateTime;

/**
 * @description 单元测试-网络测试
 * @className NetworkTest
 * @package com.yuntai.upp.access.ws
 * @author jinren@hsyuntai.com
 * @date 2019-06-14 13:40
 * @copyright 版权归 HSYUNTAI 所有
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UppAccessApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class NetworkTest extends AbstractWsClinet {

    @Test
    public void test() {
        HttpUtil.post(HttpUtil.Atom.builder()
                .url(URL_WS_SERVICE)
                .content(HttpUtil.CONTENT_XML)
                .accept(HttpUtil.ACCEPT_XML)
                .data(MessageFormat.format(TEMPLATE_NETWORK, JaxbUtil.xml(SenderPack.builder()
                        .bizCode(OuterBizCodeType.S0000.getCode())
                        .requestData(MessageFormat.format(REQUEST,
                                UUIDUtil.create(),
                                OuterBizCodeType.S0000.getInnerCmdType().getDesc(),
                                UUIDUtil.create(),
                                JaxbUtil.xml(NetworkDto.builder()
                                        .field(LocalDateTime.now().plusSeconds(0))
                                        .build())))
                        .build())))
                .build());
    }
}
