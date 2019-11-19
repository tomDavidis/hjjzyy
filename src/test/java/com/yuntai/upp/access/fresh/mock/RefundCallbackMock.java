package com.yuntai.upp.access.fresh.mock;

import com.yuntai.upp.client.basic.util.UUIDUtil;
import com.yuntai.upp.client.config.cache.CacheInstance;
import com.yuntai.upp.client.fresh.model.dto.refundcallback.RefundCallbackDto;
import com.yuntai.upp.sdk.enums.BizType;
import com.yuntai.upp.sdk.enums.ChannelProductType;
import com.yuntai.upp.sdk.enums.ChannelType;
import com.yuntai.upp.sdk.enums.TradeStatus;
import com.yuntai.upp.sdk.interfaces.Signable;
import com.yuntai.upp.sdk.util.SignUtil;
import org.junit.Assert;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
        String salt = CacheInstance.md5Salt(PARTNER_ID, ISV_ID);
        Assert.assertNotNull(salt);
        model.setSign(SignUtil.signMd5(model, salt));
        return model;
    }
}
