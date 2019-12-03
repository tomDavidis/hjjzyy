package com.yuntai.upp.access.outdated.mock;

import com.yuntai.upp.client.basic.enums.outer.OuterPayChannelType;
import com.yuntai.upp.client.basic.interfaces.EnumConvert;
import com.yuntai.upp.client.outdated.model.dto.query.QueryDto;
import com.yuntai.upp.sdk.enums.TradeStatus;
import com.yuntai.upp.sdk.enums.TradeType;
import com.yuntai.upp.sdk.result.UnitedPaymentQueryResult;
import lombok.NonNull;

import java.time.LocalDateTime;

import static com.yuntai.upp.access.CustomConstant.CHANNEL_PRODUCT;
import static com.yuntai.upp.access.CustomConstant.CHANNEL_TYPE;
import static com.yuntai.upp.access.CustomConstant.IN_PAYMENT_NO;
import static com.yuntai.upp.access.CustomConstant.OUT_PAYMENT_NO;
import static com.yuntai.upp.access.CustomConstant.PAYMENT_NO;
import static com.yuntai.upp.access.CustomConstant.TRADE_FEE;
import static com.yuntai.upp.client.config.constant.ConstantInstance.ISV_ID;
import static com.yuntai.upp.client.config.constant.ConstantInstance.PARTNER_ID;

/**
 * @description 数据模拟-交易查询
 * @className QueryMock
 * @package com.yuntai.upp.access.outdated.mock
 * @author jinren@hsyuntai.com
 * @date 2019/12/2 16:45
 * @copyright 版权归 HSYUNTAI 所有
 */
public class QueryMock {

    /**
     * @description 正常场景
     * @param
     * @return com.yuntai.upp.client.outdated.model.dto.query.QueryDto
     * @author jinren@hsyuntai.com
     * @date 2019/12/3 10:41
     */
    public static QueryDto normal() {
        return QueryDto.builder()
                .paymentNo(PAYMENT_NO)
                .bizType(OuterPayChannelType.BAR_CODE.getValue())
                .build();
    }

    /**
     * @description 模拟云端返回
     * @param dto 请求入参模型(模拟数据)
     * @return java.lang.Object
     * @author jinren@hsyuntai.com
     * @date 2019/12/3 10:41
     */
    public static UnitedPaymentQueryResult mock(@NonNull QueryDto dto) {
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
                .bizType(EnumConvert.element(dto.getBizType(), OuterPayChannelType.class).getBizType().getCode())
                .bizId(dto.getPaymentNo())
                .build();
    }
}
