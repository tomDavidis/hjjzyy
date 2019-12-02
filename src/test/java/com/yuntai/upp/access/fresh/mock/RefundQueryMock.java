package com.yuntai.upp.access.fresh.mock;
import com.yuntai.upp.client.config.cache.CacheInstance;
import com.yuntai.upp.client.fresh.model.dto.refundquery.RefundQueryDto;
import com.yuntai.upp.sdk.result.UnitedRefundQueryResult;
import com.yuntai.upp.sdk.util.SignUtil;
import lombok.NonNull;
import org.junit.Assert;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.yuntai.upp.access.CustomConstant.REQUEST_NO;
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
                .partnerId(0L)
                .channelType("")
                .channelProduct("")
                .tradeType("")
                .tradeStatus("")
                .requestNo("")
                .refundNo("")
                .outRefundNo("")
                .inRefundNo("")
                .paymentNo("")
                .outPaymentNo("")
                .inPaymentNo("")
                .refundFee(new BigDecimal("0"))
                .refundTime(LocalDateTime.now())
                .failedReason("")
                .bizData("")
                .build();
    }
}