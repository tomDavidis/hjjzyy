package com.yuntai.upp.access.fresh.mock;
import com.yuntai.upp.client.config.cache.CacheInstance;
import com.yuntai.upp.client.fresh.model.dto.refundquery.RefundQueryDto;
import com.yuntai.upp.sdk.enums.TradeStatus;
import com.yuntai.upp.sdk.enums.TradeType;
import com.yuntai.upp.sdk.result.UnitedRefundQueryResult;
import com.yuntai.upp.sdk.util.SignUtil;
import lombok.NonNull;
import org.junit.Assert;

import java.time.LocalDateTime;

import static com.yuntai.upp.access.CustomConstant.CHANNEL_PRODUCT;
import static com.yuntai.upp.access.CustomConstant.CHANNEL_TYPE;
import static com.yuntai.upp.access.CustomConstant.IN_PAYMENT_NO;
import static com.yuntai.upp.access.CustomConstant.IN_REFUND_NO;
import static com.yuntai.upp.access.CustomConstant.OUT_PAYMENT_NO;
import static com.yuntai.upp.access.CustomConstant.OUT_REFNUD_NO;
import static com.yuntai.upp.access.CustomConstant.PAYMENT_NO;
import static com.yuntai.upp.access.CustomConstant.REFUND_NO;
import static com.yuntai.upp.access.CustomConstant.REQUEST_NO;
import static com.yuntai.upp.access.CustomConstant.TRADE_FEE;
import static com.yuntai.upp.client.config.constant.ConstantInstance.ISV_ID;
import static com.yuntai.upp.client.config.constant.ConstantInstance.PARTNER_ID;
import static com.yuntai.upp.sdk.interfaces.Signable.VERSION;

/**
 * @description 数据模拟-正交易查询
 * @className RefundQueryMock
 * @package com.yuntai.upp.access.fresh.mock
 * @author jinren@hsyuntai.com
 * @date 2019/11/22 14:12
 * @copyright 版权归 HSYUNTAI 所有
 */
public class RefundQueryMock {

    /**
     * @description 正常场景
     * @param
     * @return com.yuntai.upp.client.fresh.model.dto.refundquery.RefundQueryDto
     * @author jinren@hsyuntai.com
     * @date 2019/11/22 14:12
     */
    public static RefundQueryDto normal() {
        RefundQueryDto model = RefundQueryDto.builder()
                .timestamp(System.currentTimeMillis())
                .version(VERSION)
                /*
                 * 临时使用(仅供单元测试, 实际场景禁止采用该方式)
                 */
                .isvId(ISV_ID)
                .partnerId(PARTNER_ID)
                .requestNo(REQUEST_NO)
                .build();
        String salt = CacheInstance.md5Salt(PARTNER_ID, ISV_ID);
        Assert.assertNotNull(salt);
        model.setSign(SignUtil.signMd5(model, salt));
        return model;
    }

    /**
     * @description 模拟云端返回
     * @param dto 请求入参模型(模拟数据)
     * @return com.yuntai.upp.sdk.result.UnitedRefundQueryResult
     * @author jinren@hsyuntai.com
     * @date 2019/11/29 16:44
     */
    public static UnitedRefundQueryResult mock(@NonNull RefundQueryDto dto) {
        return UnitedRefundQueryResult.builder()
                /*
                 * 临时使用(仅供单元测试, 实际场景禁止采用该方式)
                 */
                .partnerId(PARTNER_ID)
                .channelType(CHANNEL_TYPE)
                .channelProduct(CHANNEL_PRODUCT)
                .tradeType(TradeType.REFUND.getCode())
                .tradeStatus(TradeStatus.REFUND_SUCCESS.getCode())
                .requestNo(dto.getRequestNo())
                .refundNo(REFUND_NO)
                .outRefundNo(OUT_REFNUD_NO)
                .inRefundNo(IN_REFUND_NO)
                .paymentNo(PAYMENT_NO)
                .outPaymentNo(OUT_PAYMENT_NO)
                .inPaymentNo(IN_PAYMENT_NO)
                .refundFee(TRADE_FEE)
                .refundTime(LocalDateTime.now())
                .build();
    }
}