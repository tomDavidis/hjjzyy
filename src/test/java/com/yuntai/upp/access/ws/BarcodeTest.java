package com.yuntai.upp.access.ws;

import com.yuntai.upp.access.AbstractWsClinet;
import com.yuntai.upp.access.ProviderBoot;
import com.yuntai.upp.client.basic.enums.CmdType;
import com.yuntai.upp.client.basic.model.dto.barcode.BarcodeDto;
import com.yuntai.upp.client.basic.model.ws.SenderObject;
import com.yuntai.upp.client.basic.model.ws.SenderPack;
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
import java.util.UUID;

/**
 * @description 单元测试-正交易(条码)
 * @className BarcodeTest
 * @package com.yuntai.upp.access.ws
 * @author jinren@hsyuntai.com
 * @date 2019-07-04 10:25
 * @copyright 版权归 HSYUNTAI 所有
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProviderBoot.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BarcodeTest extends AbstractWsClinet {

    @Test
    public void test() {
        HttpUtil.post(HttpUtil.Atom.builder()
                .url(URL_WS)
                .content(HttpUtil.CONTENT_XML)
                .accept(HttpUtil.ACCEPT_XML)
                .data(MessageFormat.format(TEMPLATE, JaxbUtil.xml(SenderObject.builder()
                        .pack(SenderPack.builder()
                                .code(CmdType.S0002.getCode())
                                .cell(MessageFormat.format(REQUEST,
                                        UUIDUtil.createUUID(),
                                        CmdType.S0002.getDesc(),
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
                                                .build())))
                                .build())
                        .build())))
                .build());

    }
}
