package com.yuntai.upp.access.outdated.mock;
import com.yuntai.upp.client.basic.enums.outer.OuterPayChannelType;
import com.yuntai.upp.client.basic.interfaces.EnumConvert;
import com.yuntai.upp.client.outdated.model.dto.close.CloseDto;
import com.yuntai.upp.sdk.enums.YesOrNo;
import com.yuntai.upp.sdk.result.UnitedCloseResult;
import lombok.NonNull;

import static com.yuntai.upp.access.CustomConstant.PAYMENT_NO;
import static com.yuntai.upp.access.CustomConstant.REFUND_NO;
import static com.yuntai.upp.client.config.constant.ConstantInstance.ISV_ID;
import static com.yuntai.upp.client.config.constant.ConstantInstance.PARTNER_ID;

/**
 * @description 数据模拟-交易关闭
 * @className CloseMock
 * @package com.yuntai.upp.access.outdated.mock
 * @author jinren@hsyuntai.com
 * @date 2019/12/2 16:33
 * @copyright 版权归 HSYUNTAI 所有
 */
public class CloseMock {

    /**
     * @description 正常场景
     * @param
     * @return com.yuntai.upp.client.outdated.model.dto.close.CloseDto
     * @author jinren@hsyuntai.com
     * @date 2019/12/3 10:17
     */
    public static CloseDto normal() {
        return CloseDto.builder()
                .paymentNo(PAYMENT_NO)
                .bizType(OuterPayChannelType.BAR_CODE.getValue())
                .refundNo(REFUND_NO)
                .build();
    }

    /**
     * @description 模拟云端返回
     * @param dto 请求入参模型(模拟数据)
     * @return com.yuntai.upp.sdk.result.UnitedCloseResult
     * @author jinren@hsyuntai.com
     * @date 2019/12/3 10:17
     */
    public static UnitedCloseResult mock(@NonNull CloseDto dto) {
        return UnitedCloseResult.builder()
                /*
                 * 临时使用(仅供单元测试, 实际场景禁止采用该方式)
                 */
                .isvId(ISV_ID)
                .partnerId(PARTNER_ID)
                .bizType(EnumConvert.element(dto.getBizType(), OuterPayChannelType.class).getBizType().getCode())
                .bizId(dto.getPaymentNo())
                .isCloseOrder(YesOrNo.YES.getCode())
                .build();
    }
}
