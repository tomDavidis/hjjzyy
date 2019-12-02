package com.yuntai.upp.access.fresh.mock;

import com.yuntai.upp.client.config.cache.CacheInstance;
import com.yuntai.upp.client.fresh.model.dto.refund.RefundDto;
import com.yuntai.upp.sdk.enums.TradeStatus;
import com.yuntai.upp.sdk.result.UnitedRefundResult;
import com.yuntai.upp.sdk.util.SignUtil;
import lombok.NonNull;
import org.junit.Assert;

import java.time.LocalDateTime;

import static com.yuntai.upp.access.CustomConstant.BIZ_TYPE;
import static com.yuntai.upp.access.CustomConstant.CHANNEL_PRODUCT;
import static com.yuntai.upp.access.CustomConstant.CHANNEL_TYPE;
import static com.yuntai.upp.access.CustomConstant.IN_PAYMENT_NO;
import static com.yuntai.upp.access.CustomConstant.IN_REFUND_NO;
import static com.yuntai.upp.access.CustomConstant.OUT_PAYMENT_NO;
import static com.yuntai.upp.access.CustomConstant.OUT_REFNUD_NO;
import static com.yuntai.upp.access.CustomConstant.PAYMENT_NO;
import static com.yuntai.upp.access.CustomConstant.REQUEST_NO;
import static com.yuntai.upp.access.CustomConstant.TRADE_FEE;
import static com.yuntai.upp.client.config.constant.ConstantInstance.ISV_ID;
import static com.yuntai.upp.client.config.constant.ConstantInstance.PARTNER_ID;
import static com.yuntai.upp.sdk.interfaces.Signable.VERSION;

/**
 * @description 数据模拟-交易退款
 * @className RefundMock
 * @package com.yuntai.upp.access.fresh.mock
 * @author jinren@hsyuntai.com
 * @date 2019/11/22 08:54
 * @copyright 版权归 HSYUNTAI 所有
 */
public class RefundMock {

    /**
     * @description 正常场景
     * @param
     * @return com.yuntai.upp.client.fresh.model.dto.refund.RefundDto
     * @author jinren@hsyuntai.com
     * @date 2019/11/22 08:55
     */
    public static RefundDto normal() {
        RefundDto model = new RefundDto().toBuilder()
                .timestamp(System.currentTimeMillis())
                .version(VERSION)
                /*
                 * 临时使用(仅供单元测试, 实际场景禁止采用该方式)
                 */
                .isvId(ISV_ID)
                .partnerId(PARTNER_ID)
                .paymentNo(PAYMENT_NO)
                .bizType(BIZ_TYPE)
                .refundFee(TRADE_FEE)
                .requestNo(REQUEST_NO)
                .refundNo(REQUEST_NO)
                .build();
        String salt = CacheInstance.md5Salt(PARTNER_ID, ISV_ID);
        Assert.assertNotNull(salt);
        model.setSign(SignUtil.signMd5(model, salt));
        return model;
    }

    /**
     * @description 模拟云端返回
     * @param dto 请求入参模型(模拟数据)
     * @return com.yuntai.upp.sdk.result.UnitedRefundResult
     * @author jinren@hsyuntai.com
     * @date 2019/11/29 16:44
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
                .requestNo(dto.getRequestNo())
                .channelType(CHANNEL_TYPE)
                .channelProduct(CHANNEL_PRODUCT)
                .refundTime(LocalDateTime.now())
                .paymentNo(dto.getPaymentNo())
                .inPaymentNo(IN_PAYMENT_NO)
                .outPaymentNo(OUT_PAYMENT_NO)
                .bizData(dto.getBizData())
                .build();
    }
}