package com.yuntai.upp.access.fresh.mock;

import com.yuntai.upp.client.basic.util.BigDecimalUtil;
import com.yuntai.upp.client.basic.util.UUIDUtil;
import com.yuntai.upp.client.config.cache.CacheInstance;
import com.yuntai.upp.client.fresh.model.dto.refund.RefundDto;
import com.yuntai.upp.sdk.util.SignUtil;
import org.junit.Assert;

import static com.yuntai.upp.client.config.constant.ConstantInstance.ISV_ID;
import static com.yuntai.upp.client.config.constant.ConstantInstance.PARTNER_ID;
import static com.yuntai.upp.sdk.interfaces.Signable.VERSION;

/**
 * @description 数据模拟-交易退款
 * @className RefundMock
 * @package com.yuntai.upp.access.fresh.mock
 * @author jinren@hsyuntai.com
 * @date 2019/11/22 08:54
 * @copyright 版权归 HSYUNTAI 所有
 */
public class RefundMock {

    /**
     * @description 正常场景
     * @param
     * @return com.yuntai.upp.client.fresh.model.dto.refund.RefundDto
     * @author jinren@hsyuntai.com
     * @date 2019/11/22 08:55
     */
    public static RefundDto normal() {
        RefundDto model = new RefundDto().toBuilder()
                .timestamp(System.currentTimeMillis())
                .version(VERSION)
                /*
                 * 临时使用(仅供单元测试, 实际场景禁止采用该方式)
                 */
                .isvId(ISV_ID)
                .partnerId(PARTNER_ID)
                .paymentNo("${payment_no}")
                .bizType("${biz_type}")
                .refundFee(BigDecimalUtil.convert(0.01D))
                .requestNo(UUIDUtil.create())
                .refundNo(UUIDUtil.create())
                .build();
        String salt = CacheInstance.md5Salt(PARTNER_ID, ISV_ID);
        Assert.assertNotNull(salt);
        model.setSign(SignUtil.signMd5(model, salt));
        return model;
    }
}