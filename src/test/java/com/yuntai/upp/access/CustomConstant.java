package com.yuntai.upp.access;

import com.yuntai.upp.client.basic.util.BigDecimalUtil;
import com.yuntai.upp.client.basic.util.UUIDUtil;
import com.yuntai.upp.sdk.enums.BizType;

import java.math.BigDecimal;

/**
 * @description 自定义常量
 * @className CustomConstant
 * @package com.yuntai.upp.access
 * @author jinren@hsyuntai.com
 * @date 2019/11/22 13:40
 * @copyright 版权归 HSYUNTAI 所有
 */
public class CustomConstant {

    /**
     *
     */
    public static final String OUT_PAYMENT_NO = UUIDUtil.create();
    /**
     *
     */
    public static final String IN_PAYMENT_NO = UUIDUtil.create();
    /**
     *
     */
    public static final String PAYMENT_NO = UUIDUtil.create();
    /**
     *
     */
    public static final String OUT_REFNUD_NO = UUIDUtil.create();
    /**
     *
     */
    public static final String IN_REFUND_NO = UUIDUtil.create();
    /**
     *
     */
    public static final String REFUND_NO = UUIDUtil.create();
    /**
     *
     */
    public static final String REQUEST_NO = UUIDUtil.create();
    /**
     *
     */
    public static final BigDecimal TRADE_FEE = BigDecimalUtil.convert(0.01D);
    /**
     *
     */
    public static final String BIZ_TYPE = BizType.BARCODE_PAY.getCode();
}
