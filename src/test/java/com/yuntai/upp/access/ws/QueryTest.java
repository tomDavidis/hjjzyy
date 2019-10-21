package com.yuntai.upp.access.ws;

import com.yuntai.upp.access.AbstractWsClinet;
import com.yuntai.upp.access.UppAccessApplication;
import com.yuntai.upp.client.basic.enums.outer.OuterBizCodeType;
import com.yuntai.upp.client.basic.util.HttpUtil;
import com.yuntai.upp.client.basic.util.JaxbUtil;
import com.yuntai.upp.client.basic.util.UUIDUtil;
import com.yuntai.upp.client.outdated.model.dto.query.QueryDto;
import com.yuntai.upp.client.outdated.model.ws.SenderPack;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.MessageFormat;

/**
 * @description 单元测试-查询
 * @className QueryTest
 * @package com.yuntai.upp.access.ws
 * @author jinren@hsyuntai.com
 * @date 2019-07-04 10:26
 * @copyright 版权归 HSYUNTAI 所有
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UppAccessApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class QueryTest extends AbstractWsClinet {

    @Test
    public void test() {
        HttpUtil.post(HttpUtil.Atom.builder()
                .url(URL_WS)
                .content(HttpUtil.CONTENT_XML)
                .accept(HttpUtil.ACCEPT_XML)
                .data(MessageFormat.format(TEMPLATE_SERVICE, JaxbUtil.xml(SenderPack.builder()
                        .code(OuterBizCodeType.S0003.getCode())
                        .cell(MessageFormat.format(REQUEST,
                                UUIDUtil.create(),
                                OuterBizCodeType.S0003.getInnerCmdType().getDesc(),
                                UUIDUtil.create(),
                                JaxbUtil.xml(QueryDto.builder()
                                        /* 当前配置为单机, isv 标识与商户标识可为空 */
//                                                .isvId(0L)
//                                                .partnerId(0L)
                                        .paymentNo(UUIDUtil.create())
                                        .bizType(1)
                                        /* 以下为特殊渠道|附加参数, 可为空 */
//                                                .expandData("")
                                        .build())))
                        .build())))
                .build());

    }
}
