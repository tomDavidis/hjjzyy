package com.yuntai.upp.access.restapi.outdated;

import com.yuntai.upp.access.AbstractRestapiClient;
import com.yuntai.upp.access.ProviderBoot;
import com.yuntai.upp.client.basic.enums.outer.OuterBizCodeType;
import com.yuntai.upp.client.basic.util.HttpUtil;
import com.yuntai.upp.client.basic.util.JaxbUtil;
import com.yuntai.upp.client.basic.util.UUIDUtil;
import com.yuntai.upp.client.outdated.model.dto.refund.RefundDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.text.MessageFormat;

/**
 * @description 单元测试-负交易
 * @className RefundTest
 * @package com.yuntai.upp.access.restapi.outdated
 * @author jinren@hsyuntai.com
 * @date 2019-07-26 14:47
 * @copyright 版权归 HSYUNTAI 所有
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProviderBoot.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RefundTest extends AbstractRestapiClient {

    @Test
    public void testXml() {
        HttpUtil.post(HttpUtil.Atom.builder()
                .url(URL_XML)
                .content(HttpUtil.CONTENT_TEXT)
                .accept(HttpUtil.ACCEPT_JSON)
                .data(MessageFormat.format(TEMPLATE,
                        OuterBizCodeType.S0005.getCode(),
                        MessageFormat.format(REQUEST,
                                UUIDUtil.create(),
                                OuterBizCodeType.S0005.getInnerCmdType().getDesc(),
                                UUIDUtil.create(),
                                JaxbUtil.xml(RefundDto.builder()
                                        /* 当前配置为单机, isv 标识与商户标识可为空 */
//                                                .isvId(0L)
//                                                .partnerId(0L)
                                        /* 大多数医院 payment_no = refund_no */
                                        .paymentNo(UUIDUtil.create())
                                        .refundNo(UUIDUtil.create())
                                        .refundFee(new BigDecimal(0.01D).setScale(2, BigDecimal.ROUND_HALF_UP))
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
                        OuterBizCodeType.S0005.getCode(),
                        MessageFormat.format(REQUEST,
                                UUIDUtil.create(),
                                OuterBizCodeType.S0005.getInnerCmdType().getDesc(),
                                UUIDUtil.create(),
                                JaxbUtil.xml(RefundDto.builder()
                                        /* 当前配置为单机, isv 标识与商户标识可为空 */
//                                                .isvId(0L)
//                                                .partnerId(0L)
                                        /* 大多数医院 payment_no = refund_no */
                                        .paymentNo(UUIDUtil.create())
                                        .refundNo(UUIDUtil.create())
                                        .refundFee(new BigDecimal(0.01D).setScale(2, BigDecimal.ROUND_HALF_UP))
                                        /* 以下为特殊渠道|附加参数, 可为空 */
//                                                .expandData("")
                                        .build()))))
                .build());
    }
}
