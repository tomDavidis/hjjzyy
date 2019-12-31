package com.yuntai.upp.access.fresh.mock;

import com.yuntai.upp.client.config.cache.CacheInstance;
import com.yuntai.upp.sdk.util.SignUtil;
import org.junit.Assert;

import java.time.LocalDate;
import java.util.HashMap;

import static com.yuntai.upp.client.config.constant.ConstantInstance.ISV_ID;
import static com.yuntai.upp.client.config.constant.ConstantInstance.PARTNER_ID;
import static com.yuntai.upp.sdk.interfaces.Signable.VERSION;

/**
 * @description 数据模拟
 * @className MedicalMock
 * @package com.yuntai.upp.access.fresh.mock
 * @author jinren@hsyuntai.com
 * @date 2019/12/31 15:27
 * @copyright 版权归 HSYUNTAI 所有
 */
public class MedicalMock {

    /**
     * @description 正常场景-数据
     * @param
     * @return java.util.HashMap<java.lang.String,java.lang.Object>
     * @author jinren@hsyuntai.com
     * @date 2019/12/31 15:28
     */
    public static HashMap<String, Object> normal() {
        HashMap<String, Object> model = new HashMap<String, Object>(32) {
            private static final long serialVersionUID = -5196973660294711985L;
            {
                put("timestamp", System.currentTimeMillis());
                put("version", VERSION);
                /*
                 * 临时使用(仅供单元测试, 实际场景禁止采用该方式)
                 */
                put("isvId", ISV_ID);
                put("partnerId", PARTNER_ID);
                put("billsDate", LocalDate.now());
            }
        };
        String salt = CacheInstance.md5Salt(PARTNER_ID, ISV_ID);
        Assert.assertNotNull(salt);
        model.put("sign", SignUtil.signMd5(model, salt));
        return model;
    }
}
