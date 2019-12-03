package com.yuntai.upp.access.outdated.mock;

import com.yuntai.upp.client.basic.enums.inner.InnerBizType;
import com.yuntai.upp.client.basic.enums.outer.OuterChannelType;
import com.yuntai.upp.client.basic.interfaces.EnumConvert;
import com.yuntai.upp.client.basic.util.BigDecimalUtil;
import com.yuntai.upp.client.outdated.model.dto.scancode.ScanCodeDto;
import com.yuntai.upp.sdk.enums.TradeStatus;
import com.yuntai.upp.sdk.result.UnitedPaymentResult;
import com.yuntai.upp.sdk.result.UnitedPreOrderResult;
import lombok.NonNull;

import java.time.LocalDateTime;

import static com.yuntai.upp.access.CustomConstant.IN_PAYMENT_NO;
import static com.yuntai.upp.access.CustomConstant.OUT_PAYMENT_NO;
import static com.yuntai.upp.access.CustomConstant.PAYMENT_NO;
import static com.yuntai.upp.access.CustomConstant.TRADE_FEE;
import static com.yuntai.upp.client.config.constant.ConstantInstance.ISV_ID;
import static com.yuntai.upp.client.config.constant.ConstantInstance.PARTNER_ID;

/**
 * @description 数据模拟-扫码支付
 * @className ScanCodeMock
 * @package com.yuntai.upp.access.outdated.mock
 * @author jinren@hsyuntai.com
 * @date 2019/12/2 16:46
 * @copyright 版权归 HSYUNTAI 所有
 */
public class ScanCodeMock {

    /**
     * @description 正常场景
     * @param
     * @return com.yuntai.upp.client.outdated.model.dto.scancode.ScanCodeDto
     * @author jinren@hsyuntai.com
     * @date 2019/12/3 11:09
     */
    public static ScanCodeDto aggCode() {
        return ScanCodeDto.builder()
                .paymentNo(PAYMENT_NO)
                .tradeFee(TRADE_FEE)
                /*
                 * 特殊枚举
                 */
                .channelProduct(67)
                .subject("扫码支付(upp-client)-标题")
                .build();
    }

    /**
     * @description 模拟云端返回
     * @param dto 请求入参模型(模拟数据)
     * @return com.yuntai.upp.sdk.result.UnitedPreOrderResult
     * @author jinren@hsyuntai.com
     * @date 2019/12/3 11:09
     */
    public static UnitedPreOrderResult aggCode(@NonNull ScanCodeDto dto) {
        return UnitedPreOrderResult.builder()
                /*
                 * 临时使用(仅供单元测试, 实际场景禁止采用该方式)
                 */
                .isvId(ISV_ID)
                .partnerId(PARTNER_ID)
                .scanUrl("这里是聚合支付的码串地址")
                .paymentTime(LocalDateTime.now())
                .tradeFee(BigDecimalUtil.convert(dto.getTradeFee()))
                .bizType(InnerBizType.SCAN_CODE.getCode())
                .bizId(dto.getPaymentNo())
                .build();
    }

    /**
     * @description 正常场景
     * @param
     * @return com.yuntai.upp.client.outdated.model.dto.scancode.ScanCodeDto
     * @author jinren@hsyuntai.com
     * @date 2019/12/3 11:09
     */
    public static ScanCodeDto scanCode() {
        return ScanCodeDto.builder()
                .paymentNo(PAYMENT_NO)
                .tradeFee(TRADE_FEE)
                .channelProduct(OuterChannelType.ALI_SCAN_CODE.getValue())
                .subject("扫码支付(upp-client)-标题")
                .build();
    }

    /**
     * @description 模拟云端返回
     * @param dto 请求入参模型(模拟数据)
     * @return com.yuntai.upp.sdk.result.UnitedPreOrderResult
     * @author jinren@hsyuntai.com
     * @date 2019/12/3 11:09
     */
    public static UnitedPaymentResult scanCode(@NonNull ScanCodeDto dto) {
        return UnitedPaymentResult.builder()
                /*
                 * 临时使用(仅供单元测试, 实际场景禁止采用该方式)
                 */
                .isvId(ISV_ID)
                .partnerId(PARTNER_ID)
                .channelType(EnumConvert.element(dto.getChannelProduct(), OuterChannelType.class)
                        .getChannelProductType().getChannelType().getCode())
                .channelProduct(EnumConvert.element(dto.getChannelProduct(), OuterChannelType.class)
                        .getChannelProductType().getCode())
                .tradeStatus(TradeStatus.WAIT_PAY.getCode())
                .sign("码串 ... ")
                .outPaymentNo(OUT_PAYMENT_NO)
                .inPaymentNo(IN_PAYMENT_NO)
                .paymentTime(LocalDateTime.now())
                .paymentNo(dto.getPaymentNo())
                .tradeFee(dto.getTradeFee())
                .bizType(InnerBizType.SCAN_CODE.getCode())
                .bizId(dto.getPaymentNo())
                .build();
    }
}
