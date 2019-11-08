package com.yuntai.upp.access.fresh.access.refundcallback;

import com.yuntai.upp.client.basic.util.UUIDUtil;
import com.yuntai.upp.client.fresh.model.dto.refundcallback.RefundCallbackDto;
import com.yuntai.upp.sdk.enums.BizType;
import com.yuntai.upp.sdk.enums.ChannelProductType;
import com.yuntai.upp.sdk.enums.ChannelType;
import com.yuntai.upp.sdk.enums.TradeStatus;
import com.yuntai.upp.sdk.interfaces.Signable;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @description 数据模拟
 * @className RefundCallbackMock
 * @package com.yuntai.upp.access.fresh.access.refundcallback
 * @author jinren@hsyuntai.com
 * @date 2019/11/8 17:30
 * @copyright 版权归 HSYUNTAI 所有
 */
public class RefundCallbackMock {

    /**
     * @description 正常场景-数据
     * @param
     * @return com.yuntai.upp.client.fresh.model.dto.refundcallback.RefundCallbackDto
     * @author jinren@hsyuntai.com
     * @date 2019/11/8 17:36
     */
    protected static  RefundCallbackDto normal() {
        return RefundCallbackDto.builder()
                .timestamp(System.currentTimeMillis())
                .version(Signable.VERSION)
                .partnerId(999L)
                .isvId(20000L)
                .bizType(BizType.REGISTER.getCode())
                .bizId(UUIDUtil.create())
                .channelType(ChannelType.ALI.getCode())
                .channelProduct(ChannelProductType.ALI_BAR_CODE.getCode())
                .requestNo(UUIDUtil.create())
                .refundNo(UUIDUtil.create())
                .refundFee(BigDecimal.ONE)
                .tradeStatus(TradeStatus.REFUND_SUCCESS.getCode())
                .outRefundNo(UUIDUtil.create())
                .refundTime(LocalDateTime.now())
                .bizData(null)
                .build();
    }
}
