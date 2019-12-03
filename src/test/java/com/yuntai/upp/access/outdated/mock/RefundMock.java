package com.yuntai.upp.access.outdated.mock;

import com.yuntai.upp.client.basic.enums.outer.OuterPayChannelType;
import com.yuntai.upp.client.outdated.model.dto.refund.RefundDto;
import com.yuntai.upp.sdk.enums.TradeStatus;
import com.yuntai.upp.sdk.result.UnitedRefundResult;
import lombok.NonNull;

import java.time.LocalDateTime;

import static com.yuntai.upp.access.CustomConstant.CHANNEL_PRODUCT;
import static com.yuntai.upp.access.CustomConstant.CHANNEL_TYPE;
import static com.yuntai.upp.access.CustomConstant.IN_PAYMENT_NO;
import static com.yuntai.upp.access.CustomConstant.IN_REFUND_NO;
import static com.yuntai.upp.access.CustomConstant.OUT_PAYMENT_NO;
import static com.yuntai.upp.access.CustomConstant.OUT_REFNUD_NO;
import static com.yuntai.upp.access.CustomConstant.PAYMENT_NO;
import static com.yuntai.upp.access.CustomConstant.REFUND_NO;
import static com.yuntai.upp.access.CustomConstant.TRADE_FEE;
import static com.yuntai.upp.client.config.constant.ConstantInstance.PARTNER_ID;

/**
 * @description 数据模拟-交易退款
 * @className RefundMock
 * @package com.yuntai.upp.access.outdated.mock
 * @author jinren@hsyuntai.com
 * @date 2019/12/2 16:46
 * @copyright 版权归 HSYUNTAI 所有
 */
public class RefundMock {

    /**
     * @description 正常场景
     * @param
     * @return com.yuntai.upp.client.outdated.model.dto.refund.RefundDto
     * @author jinren@hsyuntai.com
     * @date 2019/12/3 11:05
     */
    public static RefundDto normal() {
        return RefundDto.builder()
                .paymentNo(PAYMENT_NO)
                .bizType(OuterPayChannelType.BAR_CODE.getValue())
                .refundFee(TRADE_FEE)
                .refundNo(REFUND_NO)
                .build();
    }

    /**
     * @description 模拟云端返回
     * @param dto 请求入参模型(模拟数据)
     * @return com.yuntai.upp.sdk.result.UnitedRefundResult
     * @author jinren@hsyuntai.com
     * @date 2019/12/3 11:34
     */
    public static UnitedRefundResult mock(@NonNull RefundDto dto) {
        return UnitedRefundResult.builder()
                /*
                 * 临时使用(仅供单元测试, 实际场景禁止采用该方式)
                 */
                .partnerId(PARTNER_ID)
                .tradeStatus(TradeStatus.REFUND_SUCCESS.getCode())
                .refundFee(dto.getRefundFee())
                .outRefundNo(OUT_REFNUD_NO)
                .inRefundNo(IN_REFUND_NO)
                .refundNo(dto.getRefundNo())
                .requestNo(dto.getRefundNo())
                .channelType(CHANNEL_TYPE)
                .channelProduct(CHANNEL_PRODUCT)
                .refundTime(LocalDateTime.now())
                .paymentNo(dto.getPaymentNo())
                .inPaymentNo(IN_PAYMENT_NO)
                .outPaymentNo(OUT_PAYMENT_NO)
                .build();
    }
}
