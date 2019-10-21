package com.yuntai.upp.access.helper.demo;

/**
 * @description: 交易通知数据模拟
 * @className: CallbackMock
 * @package: com.yuntai.upp.access.helper.demo
 * @author: jinren@hsyuntai.com
 * @date: 2019-09-06 10:17
 * @copyright: 版权归 HSYUNTAI 所有
 */
public class PaymentCallbackMock {

    /**
     * @description 数据模拟(真实场景禁止使用,仅供开发使用)
     * @return boolean
     * @author jinren@hsyuntai.com
     * @date 2019-09-06 10:19
     */
    public static boolean mock() {
        return Math.random() > 0.0D;
    }
}
