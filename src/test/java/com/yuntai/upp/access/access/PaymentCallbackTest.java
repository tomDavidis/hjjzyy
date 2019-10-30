package com.yuntai.upp.access.access;

import com.alibaba.fastjson.JSON;
import com.yuntai.hdp.access.RequestPack;
import com.yuntai.upp.access.UppAccessApplication;
import com.yuntai.upp.client.basic.enums.inner.InnerCmdType;
import com.yuntai.upp.client.basic.util.TraceIdUtil;
import com.yuntai.upp.client.basic.util.UUIDUtil;
import com.yuntai.upp.client.config.cache.CacheInstance;
import com.yuntai.upp.client.fresh.export.access.ClientReceiver;
import com.yuntai.upp.client.fresh.model.dto.paymentcallback.PaymentCallbackDto;
import com.yuntai.upp.sdk.enums.BizType;
import com.yuntai.upp.sdk.enums.ChannelProductType;
import com.yuntai.upp.sdk.enums.ChannelType;
import com.yuntai.upp.sdk.enums.TradeStatus;
import com.yuntai.upp.sdk.interfaces.Signable;
import com.yuntai.upp.sdk.util.SignUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @description 单元测试-交易通知(正交易)
 * @className PaymentCallbackTest
 * @package com.yuntai.upp.access.access
 * @author jinren@hsyuntai.com
 * @date 2019-09-06 11:13
 * @copyright 版权归 HSYUNTAI 所有
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UppAccessApplication.class})
public class PaymentCallbackTest {

    @Resource(name = "clientReceiver")
    private ClientReceiver receiver;

    @Value("${hdp.resource-id}")
    private String resourceId;

    @Before
    public void before() {
        TraceIdUtil.createLocalTraceId(this.getClass().getName());
    }

    @After
    public void after() {
        TraceIdUtil.clearLocalTraceId();
    }

    @Test
    public void test() throws Exception {
        RequestPack request = new RequestPack();

        request.setCmd(InnerCmdType.PAYMENT_CALLBACK.getCode());
        request.setSeqno(UUIDUtil.create());
        request.setClientId(UUIDUtil.create());
        request.setHosId(resourceId);
        request.setSendTime(System.currentTimeMillis());

        PaymentCallbackDto dto = PaymentCallbackDto.builder()
                .timestamp(System.currentTimeMillis())
                .version(Signable.VERSION)
                .partnerId(999L)
                .isvId(20000L)
                .bizType(BizType.REGISTER.getCode())
                .bizId(UUIDUtil.create())
                .channelType(ChannelType.ALI.getCode())
                .channelProduct(ChannelProductType.ALI_BAR_CODE.getCode())
                .tradeFee(BigDecimal.ONE)
                .tradeStatus(TradeStatus.PAY_SUCCESS.getCode())
                .outPaymentNo(UUIDUtil.create())
                .inPaymentNo(UUIDUtil.create())
                .paymentTime(LocalDateTime.now())
                .bizData(null)
                .build();
        dto.setSign(SignUtil.signMd5(dto, CacheInstance.md5Salt(dto.getPartnerId(), dto.getIsvId())));
        request.setBody(JSON.toJSONString(dto));
        receiver.getHospitalResult(request);
        TimeUnit.SECONDS.sleep(3);
    }
}
