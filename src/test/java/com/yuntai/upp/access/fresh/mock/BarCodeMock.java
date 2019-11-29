package com.yuntai.upp.access.fresh.mock;

import com.yuntai.upp.client.basic.enums.inner.InnerBizType;
import com.yuntai.upp.client.basic.util.BigDecimalUtil;
import com.yuntai.upp.client.config.cache.CacheInstance;
import com.yuntai.upp.client.fresh.model.bo.Rule;
import com.yuntai.upp.client.fresh.model.dto.barcode.BarCodeDto;
import com.yuntai.upp.sdk.enums.ChannelProductType;
import com.yuntai.upp.sdk.enums.TradeStatus;
import com.yuntai.upp.sdk.result.UnitedPreOrderResult;
import com.yuntai.upp.sdk.util.SignUtil;
import lombok.NonNull;
import org.junit.Assert;

import java.time.LocalDateTime;

import static com.yuntai.upp.access.CustomConstant.IN_PAYMENT_NO;
import static com.yuntai.upp.access.CustomConstant.OUT_PAYMENT_NO;
import static com.yuntai.upp.access.CustomConstant.PAYMENT_NO;
import static com.yuntai.upp.client.config.constant.ConstantInstance.ISV_ID;
import static com.yuntai.upp.client.config.constant.ConstantInstance.PARTNER_ID;
import static com.yuntai.upp.sdk.interfaces.Signable.VERSION;

/**
 * @description 数据模拟-条码支付
 * @className BarCodeMock
 * @package com.yuntai.upp.access.fresh.mock.barcode
 * @author jinren@hsyuntai.com
 * @date 2019/11/19 17:05
 * @copyright 版权归 HSYUNTAI 所有
 */
public class BarCodeMock {

    /**
     * @description 正常场景
     * @param
     * @return com.yuntai.upp.client.fresh.model.dto.barcode.BarCodeDto
     * @author jinren@hsyuntai.com
     * @date 2019/11/19 17:08
     */
    public static BarCodeDto normal() {
        BarCodeDto model = BarCodeDto.builder()
                .timestamp(System.currentTimeMillis())
                .version(VERSION)
                /*
                 * 临时使用(仅供单元测试, 实际场景禁止采用该方式)
                 */
                .isvId(ISV_ID)
                .partnerId(PARTNER_ID)
                .authCode("286621192146852425")
                .paymentNo(PAYMENT_NO)
                .tradeFee(BigDecimalUtil.convert(0.01D))
                .subject("条码支付(upp-client)-标题")
                .body("条码支付(upp-client)-内容")
                .expireTime(LocalDateTime.now().plusMinutes(5))
                .build();
        String salt = CacheInstance.md5Salt(PARTNER_ID, ISV_ID);
        Assert.assertNotNull(salt);
        model.setSign(SignUtil.signMd5(model, salt));
        return model;
    }

    /**
     * @description 模拟云端返回
     * @param dto 请求入参模型(模拟数据)
     * @return com.yuntai.upp.sdk.result.UnitedPreOrderResult
     * @author jinren@hsyuntai.com
     * @date 2019/11/29 14:40
     */
    public static UnitedPreOrderResult mock(@NonNull BarCodeDto dto) {
        ChannelProductType channelProductType = Rule.channel(dto.getChannelProduct(), dto.getAuthCode());
        return UnitedPreOrderResult.builder()
                .partnerId(0L)
                .isvId(0L)
                .channelType(channelProductType.getChannelType().getCode())
                .channelProduct(channelProductType.getCode())
                .tradeStatus(TradeStatus.WAIT_PAY.getCode())
                .outPaymentNo(OUT_PAYMENT_NO)
                .inPaymentNo(IN_PAYMENT_NO)
                .paymentTime(LocalDateTime.now())
                .paymentNo(dto.getPaymentNo())
                .tradeFee(dto.getTradeFee())
                .bizData(dto.getBizData())
                .bizType(InnerBizType.BAR_CODE.getCode())
                .bizId(dto.getPaymentNo())
                .build();
    }
}
