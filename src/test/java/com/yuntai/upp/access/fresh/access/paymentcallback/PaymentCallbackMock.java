package com.yuntai.upp.access.fresh.access.paymentcallback;

import com.yuntai.upp.client.basic.util.UUIDUtil;
import com.yuntai.upp.client.fresh.model.dto.paymentcallback.PaymentCallbackDto;
import com.yuntai.upp.sdk.enums.BizType;
import com.yuntai.upp.sdk.enums.ChannelProductType;
import com.yuntai.upp.sdk.enums.ChannelType;
import com.yuntai.upp.sdk.enums.TradeStatus;
import com.yuntai.upp.sdk.interfaces.Signable;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @description 数据模拟
 * @className PaymentCallbackMock
 * @package com.yuntai.upp.access.fresh.access.paymentcallback
 * @author jinren@hsyuntai.com
 * @date 2019/11/8 17:30
 * @copyright 版权归 HSYUNTAI 所有
 */
public class PaymentCallbackMock {

    /**
     * @description 正常场景-数据
     * @param
     * @return com.yuntai.upp.client.fresh.model.dto.paymentcallback.PaymentCallbackDto
     * @author jinren@hsyuntai.com
     * @date 2019/11/8 17:37
     */
    protected static PaymentCallbackDto normal() {
        return PaymentCallbackDto.builder()
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
    }
}
