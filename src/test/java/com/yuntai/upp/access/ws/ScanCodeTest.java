package com.yuntai.upp.access.ws;

import com.yuntai.upp.access.AbstractWsClinet;
import com.yuntai.upp.access.UppAccessApplication;
import com.yuntai.upp.client.basic.enums.outer.OuterBizCodeType;
import com.yuntai.upp.client.basic.enums.outer.OuterChannelType;
import com.yuntai.upp.client.basic.util.HttpUtil;
import com.yuntai.upp.client.basic.util.JaxbUtil;
import com.yuntai.upp.client.basic.util.UUIDUtil;
import com.yuntai.upp.client.outdated.model.dto.scancode.ScanCodeDto;
import com.yuntai.upp.client.outdated.model.ws.SenderPack;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.text.MessageFormat;

/**
 * @description 单元测试-正交易(扫码)
 * @className ScanCodeTest
 * @package com.yuntai.upp.access.ws
 * @author jinren@hsyuntai.com
 * @date 2019-07-04 10:25
 * @copyright 版权归 HSYUNTAI 所有
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UppAccessApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ScanCodeTest extends AbstractWsClinet {

    @Test
    public void test() {
        HttpUtil.post(HttpUtil.Atom.builder()
                .url(URL_WS_SERVICE)
                .content(HttpUtil.CONTENT_XML)
                .accept(HttpUtil.ACCEPT_XML)
                .data(MessageFormat.format(TEMPLATE_SERVICE, JaxbUtil.xml(SenderPack.builder()
                        .code(OuterBizCodeType.S0001_1.getCode())
                        .cell(MessageFormat.format(REQUEST,
                                UUIDUtil.create(),
                                OuterBizCodeType.S0001_1.getInnerCmdType().getDesc(),
                                UUIDUtil.create(),
                                JaxbUtil.xml(ScanCodeDto.builder()
                                        /* 当前配置为单机, isv 标识与商户标识可为空 */
//                                                .isvId(0L)
//                                                .partnerId(0L)
                                        /* 真实条码 */
                                        .paymentNo(UUIDUtil.create())
                                        .tradeFee(new BigDecimal(0.01D).setScale(2, BigDecimal.ROUND_HALF_UP))
                                        .channelProduct(OuterChannelType.ALI_BAR_CODE.getValue())
                                        .subject("扫码支付(upp-client)")
                                        /* 以下为特殊渠道|附加参数, 可为空 */
//                                                .terminalNo("")
//                                                .expireTime("")
//                                                .expandData("")
//                                                .districtId("")
                                        .build())))
                        .build())))
                .build());

    }
}
