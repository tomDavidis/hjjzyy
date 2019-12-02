package com.yuntai.upp.access.fresh.mock;

import com.yuntai.upp.client.config.cache.CacheInstance;
import com.yuntai.upp.client.fresh.model.dto.refundcallback.RefundCallbackDto;
import com.yuntai.upp.sdk.enums.BizType;
import com.yuntai.upp.sdk.enums.ChannelProductType;
import com.yuntai.upp.sdk.enums.TradeStatus;
import com.yuntai.upp.sdk.interfaces.Signable;
import com.yuntai.upp.sdk.util.SignUtil;
import lombok.NonNull;
import org.junit.Assert;

import java.time.LocalDateTime;

import static com.yuntai.upp.access.CustomConstant.BIZ_ID;
import static com.yuntai.upp.access.CustomConstant.CHANNEL_PRODUCT;
import static com.yuntai.upp.access.CustomConstant.CHANNEL_TYPE;
import static com.yuntai.upp.access.CustomConstant.OUT_REFNUD_NO;
import static com.yuntai.upp.access.CustomConstant.REFUND_NO;
import static com.yuntai.upp.access.CustomConstant.REQUEST_NO;
import static com.yuntai.upp.access.CustomConstant.TRADE_FEE;
import static com.yuntai.upp.client.config.constant.ConstantInstance.ISV_ID;
import static com.yuntai.upp.client.config.constant.ConstantInstance.PARTNER_ID;

/**
 * @description 数据模拟-负交易通知
 * @className RefundCallbackMock
 * @package com.yuntai.upp.access.fresh.access
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
    public static  RefundCallbackDto normal() {
        RefundCallbackDto model = RefundCallbackDto.builder()
                .timestamp(System.currentTimeMillis())
                .version(Signable.VERSION)
                /*
                 * 临时使用(仅供单元测试, 实际场景禁止采用该方式)
                 */
                .isvId(ISV_ID)
                .partnerId(PARTNER_ID)
                .bizType(BizType.REGISTER.getCode())
                .bizId(BIZ_ID)
                .channelType(CHANNEL_TYPE)
                .channelProduct(CHANNEL_PRODUCT)
                .requestNo(REQUEST_NO)
                .refundNo(REFUND_NO)
                .refundFee(TRADE_FEE)
                .tradeStatus(TradeStatus.REFUND_SUCCESS.getCode())
                .outRefundNo(OUT_REFNUD_NO)
                .refundTime(LocalDateTime.now())
                .bizData(null)
                .build();
        String salt = CacheInstance.md5Salt(PARTNER_ID, ISV_ID);
        Assert.assertNotNull(salt);
        model.setSign(SignUtil.signMd5(model, salt));
        return model;
    }

    /**
     * @description 正常场景-数据
     * @param bizType 业务类型
     * @param channelProductType 渠道产品
     * @return com.yuntai.upp.client.fresh.model.dto.refundcallback.RefundCallbackDto
     * @author jinren@hsyuntai.com
     * @date 2019/11/22 14:19
     */
    public static RefundCallbackDto normal(@NonNull BizType bizType,
                                            @NonNull ChannelProductType channelProductType) {
        RefundCallbackDto model = RefundCallbackMock.normal().toBuilder()
                .bizType(bizType.getCode())
                .channelType(channelProductType.getChannelType().getCode())
                .channelProduct(channelProductType.getCode())
                .build();
        String salt = CacheInstance.md5Salt(PARTNER_ID, ISV_ID);
        Assert.assertNotNull(salt);
        model.setSign(SignUtil.signMd5(model, salt));
        return model;
    }
}
