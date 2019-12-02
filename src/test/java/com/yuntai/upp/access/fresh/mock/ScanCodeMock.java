package com.yuntai.upp.access.fresh.mock;
import com.yuntai.upp.client.basic.util.UUIDUtil;
import com.yuntai.upp.client.config.cache.CacheInstance;
import com.yuntai.upp.client.fresh.model.dto.scancode.ScanCodeDto;
import com.yuntai.upp.sdk.enums.ChannelProductType;
import com.yuntai.upp.sdk.result.UnitedPaymentResult;
import com.yuntai.upp.sdk.util.SignUtil;
import lombok.NonNull;
import org.junit.Assert;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.yuntai.upp.access.CustomConstant.TRADE_FEE;
import static com.yuntai.upp.client.config.constant.ConstantInstance.ISV_ID;
import static com.yuntai.upp.client.config.constant.ConstantInstance.PARTNER_ID;
import static com.yuntai.upp.sdk.interfaces.Signable.VERSION;

/**
 * @description 数据模拟-扫码支付
 * @className ScanCodeMock
 * @package com.yuntai.upp.access.fresh.mock
 * @author jinren@hsyuntai.com
 * @date 2019/11/21 13:30
 * @copyright 版权归 HSYUNTAI 所有
 */
public class ScanCodeMock {

    /**
     * @description 正常场景
     * @param
     * @return com.yuntai.upp.client.fresh.model.dto.scancode.ScanCodeDto
     * @author jinren@hsyuntai.com
     * @date 2019/11/21 13:31
     */
    public static ScanCodeDto normal() {
        ScanCodeDto model = new ScanCodeDto().toBuilder()
                .timestamp(System.currentTimeMillis())
                .version(VERSION)
                /*
                 * 临时使用(仅供单元测试, 实际场景禁止采用该方式)
                 */
                .isvId(ISV_ID)
                .partnerId(PARTNER_ID)
                .paymentNo(UUIDUtil.create())
                .channelProduct(ChannelProductType.ALI_SCAN_CODE.getCode())
                .tradeFee(TRADE_FEE)
                .subject("扫码支付(upp-client)-标题")
                .body("扫码支付(upp-client)-内容")
                .expireTime(LocalDateTime.now().plusMinutes(5))
                .build();
        String salt = CacheInstance.md5Salt(PARTNER_ID, ISV_ID);
        Assert.assertNotNull(salt);
        model.setSign(SignUtil.signMd5(model, salt));
        return model;
    }

    /**
     * @description 正常场景
     * @param channel 交易渠道
     * @return com.yuntai.upp.client.fresh.model.dto.scancode.ScanCodeDto
     * @author jinren@hsyuntai.com
     * @date 2019/11/21 13:56
     */
    public static ScanCodeDto normal(@NonNull CacheInstance.Channel channel) {
        ScanCodeDto model = ScanCodeMock.normal().toBuilder()
                .channelProduct(channel.getChannelProductType().getCode())
                .build();
        String salt = CacheInstance.md5Salt(PARTNER_ID, ISV_ID);
        Assert.assertNotNull(salt);
        model.setSign(SignUtil.signMd5(model, salt));
        return model;
    }

    /**
     * @description 模拟云端返回
     * @param dto 请求入参模型(模拟数据)
     * @return com.yuntai.upp.sdk.result.UnitedPaymentResult
     * @author jinren@hsyuntai.com
     * @date 2019/11/29 16:44
     */
    public static UnitedPaymentResult mock(@NonNull ScanCodeDto dto) {
        return UnitedPaymentResult.builder()
                .partnerId(0L)
                .isvId(0L)
                .channelType("")
                .channelProduct("")
                .tradeStatus("")
                .failedReason("")
                .sign("")
                .outPaymentNo("")
                .inPaymentNo("")
                .paymentTime(LocalDateTime.now())
                .paymentNo("")
                .tradeFee(new BigDecimal("0"))
                .bizData("")
                .bizType("")
                .bizId("")
                .build();
    }
}