package com.yuntai.upp.access.fresh.mock;

import com.yuntai.upp.client.basic.enums.inner.InnerBizType;
import com.yuntai.upp.client.config.cache.CacheInstance;
import com.yuntai.upp.client.fresh.model.dto.facecode.FaceCodeDto;
import com.yuntai.upp.sdk.enums.ChannelProductType;
import com.yuntai.upp.sdk.enums.TradeStatus;
import com.yuntai.upp.sdk.result.UnitedPaymentResult;
import com.yuntai.upp.sdk.util.SignUtil;
import lombok.NonNull;
import org.junit.Assert;

import java.time.LocalDateTime;

import static com.yuntai.upp.access.CustomConstant.FACE_CODE;
import static com.yuntai.upp.access.CustomConstant.IN_PAYMENT_NO;
import static com.yuntai.upp.access.CustomConstant.OUT_PAYMENT_NO;
import static com.yuntai.upp.access.CustomConstant.PAYMENT_NO;
import static com.yuntai.upp.access.CustomConstant.TRADE_FEE;
import static com.yuntai.upp.client.config.constant.ConstantInstance.ISV_ID;
import static com.yuntai.upp.client.config.constant.ConstantInstance.PARTNER_ID;
import static com.yuntai.upp.sdk.interfaces.Signable.VERSION;

/**
 * @description 数据模拟-刷脸支付
 * @className FaceCodeMock
 * @package com.yuntai.upp.access.fresh.mock
 * @author jinren@hsyuntai.com
 * @date 2019/12/2 15:47
 * @copyright 版权归 HSYUNTAI 所有
 */
public class FaceCodeMock {

    /**
     * @description 正常场景
     * @param
     * @return com.yuntai.upp.client.fresh.model.dto.facecode.FaceCodeDto
     * @author jinren@hsyuntai.com
     * @date 2019/12/2 15:52
     */
    public static FaceCodeDto normal() {
        FaceCodeDto model = new FaceCodeDto().toBuilder()
                .timestamp(System.currentTimeMillis())
                .version(VERSION)
                /*
                 * 临时使用(仅供单元测试, 实际场景禁止采用该方式)
                 */
                .isvId(ISV_ID)
                .partnerId(PARTNER_ID)
                .paymentNo(PAYMENT_NO)
                .channelProduct(ChannelProductType.ALI_FACE_CODE.getCode())
                .faceCode(FACE_CODE)
                .tradeFee(TRADE_FEE)
                .subject("刷脸支付(upp-client)-标题")
                .body("刷脸支付(upp-client)-内容")
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
     * @return com.yuntai.upp.client.fresh.model.dto.facecode.FaceCodeDto
     * @author jinren@hsyuntai.com
     * @date 2019/12/2 15:52
     */
    public static FaceCodeDto normal(@NonNull CacheInstance.Channel channel) {
        FaceCodeDto model = FaceCodeMock.normal().toBuilder()
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
     * @date 2019/12/2 15:52
     */
    public static UnitedPaymentResult mock(@NonNull FaceCodeDto dto) {
        return UnitedPaymentResult.builder()
                /*
                 * 临时使用(仅供单元测试, 实际场景禁止采用该方式)
                 */
                .isvId(ISV_ID)
                .partnerId(PARTNER_ID)
                .channelType(ChannelProductType.ALI_FACE_CODE.getChannelType().getCode())
                .channelProduct(ChannelProductType.ALI_FACE_CODE.getCode())
                .tradeStatus(TradeStatus.WAIT_PAY.getCode())
                .outPaymentNo(OUT_PAYMENT_NO)
                .inPaymentNo(IN_PAYMENT_NO)
                .paymentTime(LocalDateTime.now())
                .paymentNo(dto.getPaymentNo())
                .tradeFee(dto.getTradeFee())
                .bizData(dto.getBizData())
                .bizType(InnerBizType.SCAN_CODE.getCode())
                .bizId(dto.getPaymentNo())
                .build();
    }
}
