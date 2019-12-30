package com.yuntai.upp.access.fresh.mock;

import com.yuntai.upp.client.config.cache.CacheInstance;
import com.yuntai.upp.sdk.enums.BizType;
import com.yuntai.upp.sdk.enums.ChannelProductType;
import com.yuntai.upp.sdk.enums.TradeStatus;
import com.yuntai.upp.sdk.util.SignUtil;
import lombok.NonNull;
import org.junit.Assert;

import java.time.LocalDateTime;
import java.util.HashMap;

import static com.yuntai.upp.access.CustomConstant.BIZ_ID;
import static com.yuntai.upp.access.CustomConstant.BIZ_TYPE;
import static com.yuntai.upp.access.CustomConstant.CHANNEL_PRODUCT;
import static com.yuntai.upp.access.CustomConstant.CHANNEL_TYPE;
import static com.yuntai.upp.access.CustomConstant.IN_PAYMENT_NO;
import static com.yuntai.upp.access.CustomConstant.OUT_PAYMENT_NO;
import static com.yuntai.upp.access.CustomConstant.TRADE_FEE;
import static com.yuntai.upp.client.config.constant.ConstantInstance.ISV_ID;
import static com.yuntai.upp.client.config.constant.ConstantInstance.PARTNER_ID;
import static com.yuntai.upp.sdk.interfaces.Signable.VERSION;

/**
 * @description 数据模拟-正交易通知
 * @className PaymentCallbackMock
 * @package com.yuntai.upp.access.fresh.access
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
    public static HashMap<String, Object> normal() {
        HashMap<String, Object> model = new HashMap<String, Object>(32) {
            private static final long serialVersionUID = 41091879228546038L;
            {
                put("timestamp", System.currentTimeMillis());
                put("version", VERSION);
                put("isvId", ISV_ID);
                /*
                 * 临时使用(仅供单元测试, 实际场景禁止采用该方式)
                 */
                put("partnerId", PARTNER_ID);
                put("bizType", BIZ_TYPE);
                put("bizId", BIZ_ID);
                put("channelType", CHANNEL_TYPE);
                put("channelProduct", CHANNEL_PRODUCT);
                put("tradeFee", TRADE_FEE);
                put("tradeStatus", TradeStatus.PAY_SUCCESS.getCode());
                put("outPaymentNo", OUT_PAYMENT_NO);
                put("inPaymentNo", IN_PAYMENT_NO);
                put("paymentTime", LocalDateTime.now());
                put("bizData", null);
            }
        };
        String salt = CacheInstance.md5Salt(PARTNER_ID, ISV_ID);
        Assert.assertNotNull(salt);
        model.put("sign", SignUtil.signMd5(model, salt));
        return model;
    }

    /**
     * @description 正常场景-数据
     * @param bizType 业务类型
     * @param channelProductType 渠道产品
     * @return com.yuntai.upp.client.fresh.model.dto.paymentcallback.PaymentCallbackDto
     * @author jinren@hsyuntai.com
     * @date 2019/11/22 14:18
     */
    public static HashMap<String, Object> normal(@NonNull BizType bizType,
                                                 @NonNull ChannelProductType channelProductType) {
        HashMap<String, Object> model = PaymentCallbackMock.normal();
        model.putAll(new HashMap<String, Object>(8) {
            private static final long serialVersionUID = 1691037813160372204L;
            {
                put("bizType", bizType.getCode());
                put("channelType", channelProductType.getChannelType().getCode());
                put("channelProduct", channelProductType.getCode());
            }
        });
        String salt = CacheInstance.md5Salt(PARTNER_ID, ISV_ID);
        Assert.assertNotNull(salt);
        model.put("sign", SignUtil.signMd5(model, salt));
        return model;
    }
}
