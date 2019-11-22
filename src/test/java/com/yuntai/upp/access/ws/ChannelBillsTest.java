package com.yuntai.upp.access.ws;

import com.yuntai.upp.access.AbstractWsClinet;
import com.yuntai.upp.access.UppAccessApplication;
import com.yuntai.upp.client.basic.util.HttpUtil;
import com.yuntai.upp.client.basic.util.JaxbUtil;
import com.yuntai.upp.client.config.constant.ConstantInstance;
import com.yuntai.upp.client.fresh.model.dto.channelbills.ChannelBillsDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.MessageFormat;
import java.time.LocalDate;

/**
 * @description 单元测试-账单下载(条码)
 * @className ChannelBillsTest
 * @package com.yuntai.upp.access.ws
 * @author jinren@hsyuntai.com
 * @date 2019/10/21 19:25
 * @copyright 版权归 HSYUNTAI 所有
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UppAccessApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ChannelBillsTest extends AbstractWsClinet {

    @Test
    public void test() {
        HttpUtil.post(HttpUtil.Atom.builder()
                .url(URL_WS_DETAIL)
                .content(HttpUtil.CONTENT_XML)
                .accept(HttpUtil.ACCEPT_XML)
                .data(MessageFormat.format(TEMPLATE_CHANNEL_BILLS, JaxbUtil.xml(ChannelBillsDto.builder()
                        .isvId(ConstantInstance.ISV_ID)
                        .partnerId(ConstantInstance.PARTNER_ID)
                        .timestamp(System.currentTimeMillis())
                        .version("1.0.0")
                        .startDate(LocalDate.now().withMonth(11).withDayOfMonth(6))
                        .endDate(LocalDate.now().withMonth(11).withDayOfMonth(6))
                        .build())))
                .build());
    }
}
