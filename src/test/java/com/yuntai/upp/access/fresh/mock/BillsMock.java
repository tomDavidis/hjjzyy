package com.yuntai.upp.access.fresh.mock;

import com.yuntai.upp.client.basic.util.UUIDUtil;
import com.yuntai.upp.client.config.cache.CacheInstance;
import com.yuntai.upp.sdk.core.DataObject;
import com.yuntai.upp.sdk.enums.YesOrNo;
import com.yuntai.upp.sdk.util.SignUtil;
import org.junit.Assert;

import java.time.LocalDateTime;
import java.util.HashMap;

import static com.yuntai.upp.client.config.constant.ConstantInstance.ISV_ID;
import static com.yuntai.upp.client.config.constant.ConstantInstance.PARTNER_ID;
import static com.yuntai.upp.sdk.interfaces.Signable.VERSION;

/**
 * @description 数据模拟-账单下载
 * @className BillsMock
 * @package com.yuntai.upp.access.fresh.mock.bills
 * @author jinren@hsyuntai.com
 * @date 2019/11/8 17:29
 * @copyright 版权归 HSYUNTAI 所有
 */
public class BillsMock {

    /**
     * @description 正常场景
     * @param
     * @return java.util.HashMap<java.lang.String,java.lang.Object>
     * @author jinren@hsyuntai.com
     * @date 2019/12/30 10:26
     */
    public static HashMap<String, Object> normal() {
        HashMap<String, Object> model = new HashMap<String, Object>(16) {
            private static final long serialVersionUID = -1579461657351393812L;
            {
                put("timestamp", System.currentTimeMillis());
                put("version", VERSION);
                /*
                 * 临时使用(仅供单元测试, 实际场景禁止采用该方式)
                 */
                put("partnerId", PARTNER_ID);
                put("isvId", ISV_ID);
                put("isMergeRefund", YesOrNo.YES.getCode());
                put("startTime", LocalDateTime.now());
                put("endTime", LocalDateTime.now().minusHours(2));
                put("billsNo", UUIDUtil.create());
            }
        };
        String salt = CacheInstance.md5Salt(PARTNER_ID, ISV_ID);
        Assert.assertNotNull(salt);
        model.put("sign", SignUtil.signMd5(model, salt));
        return model;
    }

    /**
     * @description 模拟云端返回
     * @param
     * @return com.yuntai.upp.sdk.core.DataObject
     * @author jinren@hsyuntai.com
     * @date 2019/12/30 11:54
     */
    public static DataObject mock() {
        return DataObject.convert(Boolean.TRUE);
    }
}
