package com.yuntai.upp.access.ws;

import com.yuntai.upp.access.AbstractWsClinet;
import com.yuntai.upp.access.ProviderBoot;
import com.yuntai.upp.client.basic.enums.CmdType;
import com.yuntai.upp.client.basic.model.dto.query.QueryDto;
import com.yuntai.upp.client.basic.model.dto.refund.RefundDto;
import com.yuntai.upp.client.basic.model.ws.SenderObject;
import com.yuntai.upp.client.basic.model.ws.SenderPack;
import com.yuntai.upp.client.basic.util.HttpUtil;
import com.yuntai.upp.client.basic.util.JaxbUtil;
import com.yuntai.upp.client.basic.util.UUIDUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.UUID;

/**
 * @description 单元测试-负交易
 * @className RefundTest
 * @package com.yuntai.upp.access.ws
 * @author jinren@hsyuntai.com
 * @date 2019-07-04 10:26
 * @copyright 版权归 HSYUNTAI 所有
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProviderBoot.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RefundTest extends AbstractWsClinet {

    @Test
    public void test() {
        HttpUtil.post(HttpUtil.Atom.builder()
                .url(URL_WS)
                .content(HttpUtil.CONTENT_XML)
                .accept(HttpUtil.ACCEPT_XML)
                .data(MessageFormat.format(TEMPLATE, JaxbUtil.xml(SenderObject.builder()
                        .pack(SenderPack.builder()
                                .code(CmdType.S0005.getCode())
                                .cell(MessageFormat.format(REQUEST,
                                        UUIDUtil.createUUID(),
                                        CmdType.S0005.getDesc(),
                                        UUIDUtil.createUUID(),
                                        JaxbUtil.xml(RefundDto.builder()
                                                /* 当前配置为单机, isv 标识与商户标识可为空 */
//                                                .isvId(0L)
//                                                .partnerId(0L)
                                                /* 大多数医院 payment_no = refund_no */
                                                .paymentNo(UUIDUtil.createUUID())
                                                .refundNo(UUIDUtil.createUUID())
                                                .refundFee(new BigDecimal(0.01D).setScale(2, BigDecimal.ROUND_HALF_UP))
                                                /* 以下为特殊渠道|附加参数, 可为空 */
//                                                .expandData("")
                                                .build())))
                                .build())
                        .build())))
                .build());

    }
}
