package com.yuntai.upp.access.fresh.mock;

import com.yuntai.upp.client.config.cache.CacheInstance;
import com.yuntai.upp.client.config.constant.ConstantInstance;
import com.yuntai.upp.client.fresh.model.dto.aggcode.AggCodeDto;
import com.yuntai.upp.sdk.interfaces.Signable;
import com.yuntai.upp.sdk.util.SignUtil;
import org.junit.Assert;

import static com.yuntai.upp.client.config.constant.ConstantInstance.ISV_ID;
import static com.yuntai.upp.client.config.constant.ConstantInstance.PARTNER_ID;

/**
 * @description 数据模拟-聚合支付
 * @className AggCodeMock
 * @package com.yuntai.upp.access.fresh.mock
 * @author jinren@hsyuntai.com
 * @date 2019/11/21 11:47
 * @copyright 版权归 HSYUNTAI 所有
 */
public class AggCodeMock {

    /**
     * @description 正常场景
     * @param
     * @return com.yuntai.upp.client.fresh.model.dto.aggcode.AggCodeDto
     * @author jinren@hsyuntai.com
     * @date 2019/11/21 11:47
     */
    public static AggCodeDto normal() {
        AggCodeDto model = new AggCodeDto().toBuilder()
                .isvId(ConstantInstance.ISV_ID)
                .partnerId(ConstantInstance.PARTNER_ID)
                .version(Signable.VERSION)
                .timestamp(System.currentTimeMillis())
                .build();
        String salt = CacheInstance.md5Salt(PARTNER_ID, ISV_ID);
        Assert.assertNotNull(salt);
        model.setSign(SignUtil.signMd5(model, salt));
        return model;
    }
}
