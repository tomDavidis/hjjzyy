package com.yuntai.upp.access.outdated.mock;

import com.yuntai.upp.client.basic.enums.inner.InnerBizType;
import com.yuntai.upp.client.basic.enums.outer.OuterChannelType;
import com.yuntai.upp.client.fresh.model.bo.Rule;
import com.yuntai.upp.client.outdated.model.dto.barcode.BarCodeDto;
import com.yuntai.upp.client.outdated.rule.ChannelProductRule;
import com.yuntai.upp.sdk.enums.ChannelProductType;
import com.yuntai.upp.sdk.enums.TradeStatus;
import com.yuntai.upp.sdk.result.UnitedPaymentResult;

import java.time.LocalDateTime;

import static com.yuntai.upp.access.CustomConstant.AUTH_CODE;
import static com.yuntai.upp.access.CustomConstant.IN_PAYMENT_NO;
import static com.yuntai.upp.access.CustomConstant.OUT_PAYMENT_NO;
import static com.yuntai.upp.access.CustomConstant.PAYMENT_NO;
import static com.yuntai.upp.access.CustomConstant.TRADE_FEE;
import static com.yuntai.upp.client.config.constant.ConstantInstance.ISV_ID;
import static com.yuntai.upp.client.config.constant.ConstantInstance.PARTNER_ID;

/**
 * @description 数据模拟-条码支付
 * @className BarCodeMock
 * @package com.yuntai.upp.access.outdated.mock
 * @author jinren@hsyuntai.com
 * @date 2019/12/2 16:32
 * @copyright 版权归 HSYUNTAI 所有
 */
public class BarCodeMock {

    /**
     * @description 正常场景
     * @param
     * @return com.yuntai.upp.client.outdated.model.dto.barcode.BarCodeDto
     * @author jinren@hsyuntai.com
     * @date 2019/12/2 17:21
     */
    public static BarCodeDto normal() {
        return BarCodeDto.builder()
                .authCode(AUTH_CODE)
                .paymentNo(PAYMENT_NO)
                .channelProduct(OuterChannelType.ALI_BAR_CODE.getValue())
                .tradeFee(TRADE_FEE)
                .subject("条码支付(upp-client)-标题")
                .build();
    }

    /**
     * @description 模拟云端返回
     * @param dto 请求入参模型(模拟数据)
     * @return com.yuntai.upp.sdk.result.UnitedPaymentResult
     * @author jinren@hsyuntai.com
     * @date 2019/12/3 09:42
     */
    public static UnitedPaymentResult mock(BarCodeDto dto) {
        ChannelProductType channelProductType = Rule.channel(ChannelProductRule.bianQue(dto.getChannelProduct()), dto.getAuthCode());
        return UnitedPaymentResult.builder()
                /*
                 * 临时使用(仅供单元测试, 实际场景禁止采用该方式)
                 */
                .isvId(ISV_ID)
                .partnerId(PARTNER_ID)
                .channelType(channelProductType.getChannelType().getCode())
                .channelProduct(channelProductType.getCode())
                .tradeStatus(TradeStatus.WAIT_PAY.getCode())
                .outPaymentNo(OUT_PAYMENT_NO)
                .inPaymentNo(IN_PAYMENT_NO)
                .paymentTime(LocalDateTime.now())
                .paymentNo(dto.getPaymentNo())
                .tradeFee(dto.getTradeFee())
                .bizType(InnerBizType.BAR_CODE.getCode())
                .bizId(dto.getPaymentNo())
                .build();
    }
}
