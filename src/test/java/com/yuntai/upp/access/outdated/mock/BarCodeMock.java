package com.yuntai.upp.access.outdated.mock;

import com.yuntai.upp.client.basic.enums.outer.OuterChannelType;
import com.yuntai.upp.client.outdated.model.dto.barcode.BarCodeDto;

import static com.yuntai.upp.access.CustomConstant.AUTH_CODE;
import static com.yuntai.upp.access.CustomConstant.PAYMENT_NO;
import static com.yuntai.upp.access.CustomConstant.TRADE_FEE;

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
}
