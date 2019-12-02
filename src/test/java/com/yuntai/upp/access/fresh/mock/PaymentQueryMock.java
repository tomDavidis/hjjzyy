package com.yuntai.upp.access.fresh.mock;

import com.yuntai.upp.client.config.cache.CacheInstance;
import com.yuntai.upp.client.fresh.model.dto.paymentquery.PaymentQueryDto;
import com.yuntai.upp.sdk.enums.TradeStatus;
import com.yuntai.upp.sdk.enums.TradeType;
import com.yuntai.upp.sdk.result.UnitedPaymentQueryResult;
import com.yuntai.upp.sdk.util.SignUtil;
import lombok.NonNull;
import org.junit.Assert;

import java.time.LocalDateTime;

import static com.yuntai.upp.access.CustomConstant.BIZ_TYPE;
import static com.yuntai.upp.access.CustomConstant.CHANNEL_PRODUCT;
import static com.yuntai.upp.access.CustomConstant.CHANNEL_TYPE;
import static com.yuntai.upp.access.CustomConstant.IN_PAYMENT_NO;
import static com.yuntai.upp.access.CustomConstant.OUT_PAYMENT_NO;
import static com.yuntai.upp.access.CustomConstant.PAYMENT_NO;
import static com.yuntai.upp.access.CustomConstant.TRADE_FEE;
import static com.yuntai.upp.client.config.constant.ConstantInstance.ISV_ID;
import static com.yuntai.upp.client.config.constant.ConstantInstance.PARTNER_ID;
import static com.yuntai.upp.sdk.interfaces.Signable.VERSION;

/**
 * @description 数据模拟-正交易查询
 * @className PaymentQueryMock
 * @package com.yuntai.upp.access.fresh.mock
 * @author jinren@hsyuntai.com
 * @date 2019/11/22 13:34
 * @copyright 版权归 HSYUNTAI 所有
 */
public class PaymentQueryMock {

    /**
     * @description 正常场景
     * @param
     * @return com.yuntai.upp.client.fresh.model.dto.paymentquery.PaymentQueryDto
     * @author jinren@hsyuntai.com
     * @date 2019/11/22 13:35
     */
    public static PaymentQueryDto normal() {
        PaymentQueryDto model = PaymentQueryDto.builder()
                .timestamp(System.currentTimeMillis())
                .version(VERSION)
                /*
                 * 临时使用(仅供单元测试, 实际场景禁止采用该方式)
                 */
                .isvId(ISV_ID)
                .partnerId(PARTNER_ID)
                .bizType(BIZ_TYPE)
                .paymentNo(PAYMENT_NO)
                .build();
        String salt = CacheInstance.md5Salt(PARTNER_ID, ISV_ID);
        Assert.assertNotNull(salt);
        model.setSign(SignUtil.signMd5(model, salt));
        return model;
    }

    /**
     * @description 模拟云端返回
     * @param dto 请求入参模型(模拟数据)
     * @return com.yuntai.upp.sdk.result.UnitedPaymentQueryResult
     * @author jinren@hsyuntai.com
     * @date 2019/11/29 16:43
     */
    public static UnitedPaymentQueryResult mock(@NonNull PaymentQueryDto dto) {
        return UnitedPaymentQueryResult.builder()
                /*
                 * 临时使用(仅供单元测试, 实际场景禁止采用该方式)
                 */
                .isvId(ISV_ID)
                .partnerId(PARTNER_ID)
                .channelType(CHANNEL_TYPE)
                .channelProduct(CHANNEL_PRODUCT)
                .tradeType(TradeType.PAY.getCode())
                .tradeFee(TRADE_FEE)
                .tradeStatus(TradeStatus.PAY_SUCCESS.getCode())
                .outPaymentNo(OUT_PAYMENT_NO)
                .inPaymentNo(IN_PAYMENT_NO)
                .paymentNo(dto.getPaymentNo())
                .paymentTime(LocalDateTime.now())
                .bizType(dto.getBizType())
                .bizId(dto.getPaymentNo())
                .build();
    }
}
