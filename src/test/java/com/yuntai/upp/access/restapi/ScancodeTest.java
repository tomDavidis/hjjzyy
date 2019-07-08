package com.yuntai.upp.access.restapi;

import com.yuntai.upp.access.AbstractRestapiClient;
import com.yuntai.upp.access.ProviderBoot;
import com.yuntai.upp.client.basic.enums.CmdType;
import com.yuntai.upp.client.basic.model.dto.barcode.BarcodeDto;
import com.yuntai.upp.client.basic.util.HttpUtil;
import com.yuntai.upp.client.basic.util.JaxbUtil;
import com.yuntai.upp.client.basic.util.UUIDUtil;
import com.yuntai.upp.sdk.enums.ChannelProductType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.text.MessageFormat;

/**
 * @description 单元测试-正交易(扫码)
 * @className ScancodeTest
 * @package com.yuntai.upp.access.restapi
 * @author jinren@hsyuntai.com
 * @date 2019-07-04 10:25
 * @copyright 版权归 HSYUNTAI 所有
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProviderBoot.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ScancodeTest extends AbstractRestapiClient {

    @Test
    public void testXml() {
        HttpUtil.post(HttpUtil.Atom.builder()
                .url(URL_XML)
                .content(HttpUtil.CONTENT_TEXT)
                .accept(HttpUtil.ACCEPT_JSON)
                .data(MessageFormat.format(TEMPLATE,
                        CmdType.S0001_1.getCode(),
                        MessageFormat.format(REQUEST,
                                UUIDUtil.createUUID(),
                                CmdType.S0001_1.getDesc(),
                                UUIDUtil.createUUID(),
                                JaxbUtil.xml(BarcodeDto.builder()
                                        /* 当前配置为单机, isv 标识与商户标识可为空 */
//                                                .isvId(0L)
//                                                .partnerId(0L)
                                        /* 真实条码 */
                                        .authCode("")
                                        .paymentNo(UUIDUtil.createUUID())
                                        .tradeFee(new BigDecimal(0.01D).setScale(2, BigDecimal.ROUND_HALF_UP))
                                        .channelProduct(ChannelProductType.ALI_BAR_CODE.getValue())
                                        .subject("条码支付(upp-client)")
                                        /* 以下为特殊渠道|附加参数, 可为空 */
//                                                .terminalNo("")
//                                                .expireTime("")
//                                                .expandData("")
//                                                .districtId("")
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
                        CmdType.S0001_1.getCode(),
                        MessageFormat.format(REQUEST,
                                UUIDUtil.createUUID(),
                                CmdType.S0001_1.getDesc(),
                                UUIDUtil.createUUID(),
                                JaxbUtil.xml(BarcodeDto.builder()
                                        /* 当前配置为单机, isv 标识与商户标识可为空 */
//                                                .isvId(0L)
//                                                .partnerId(0L)
                                        /* 真实条码 */
                                        .authCode("")
                                        .paymentNo(UUIDUtil.createUUID())
                                        .tradeFee(new BigDecimal(0.01D).setScale(2, BigDecimal.ROUND_HALF_UP))
                                        .channelProduct(ChannelProductType.ALI_BAR_CODE.getValue())
                                        .subject("条码支付(upp-client)")
                                        /* 以下为特殊渠道|附加参数, 可为空 */
//                                                .terminalNo("")
//                                                .expireTime("")
//                                                .expandData("")
//                                                .districtId("")
                                        .build()))))
                .build());
    }
}