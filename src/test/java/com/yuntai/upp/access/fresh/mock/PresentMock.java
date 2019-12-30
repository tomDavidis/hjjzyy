package com.yuntai.upp.access.fresh.mock;

import com.yuntai.upp.client.config.cache.CacheInstance;
import com.yuntai.upp.sdk.util.SignUtil;
import org.junit.Assert;

import java.time.LocalDateTime;
import java.util.HashMap;

import static com.yuntai.upp.access.CustomConstant.OUT_PAYMENT_NO;
import static com.yuntai.upp.access.CustomConstant.PAYMENT_NO;
import static com.yuntai.upp.client.config.constant.ConstantInstance.ISV_ID;
import static com.yuntai.upp.client.config.constant.ConstantInstance.PARTNER_ID;
import static com.yuntai.upp.sdk.interfaces.Signable.VERSION;

/**
 * @description 数据模拟
 * @className PresentMock
 * @package com.yuntai.upp.access.fresh.access
 * @author jinren@hsyuntai.com
 * @date 2019/11/8 17:30
 * @copyright 版权归 HSYUNTAI 所有
 */
public class PresentMock {

    /**
     * @description 正常场景-数据
     * @param
     * @return com.yuntai.upp.client.fresh.model.dto.present.PresentDto
     * @author jinren@hsyuntai.com
     * @date 2019/11/8 17:37
     */
    public static HashMap<String, Object> normal() {
        HashMap<String, Object> model = new HashMap<String, Object>(32) {
            private static final long serialVersionUID = -5811744674968042724L;
            {
                put("timestamp", System.currentTimeMillis());
                put("version", VERSION);
                /*
                 * 临时使用(仅供单元测试, 实际场景禁止采用该方式)
                 */
                put("isvId", ISV_ID);
                put("partnerId", PARTNER_ID);
                put("outPaymentNo", OUT_PAYMENT_NO);
                put("paymentNo", PAYMENT_NO);
                put("gmtCreate", LocalDateTime.now().minusDays(1));
            }
        };
        String salt = CacheInstance.md5Salt(PARTNER_ID, ISV_ID);
        Assert.assertNotNull(salt);
        model.put("sign", SignUtil.signMd5(model, salt));
        return model;
    }
}
