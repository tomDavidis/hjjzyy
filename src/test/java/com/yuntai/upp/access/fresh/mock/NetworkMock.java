package com.yuntai.upp.access.fresh.mock;

import com.yuntai.upp.client.config.cache.CacheInstance;
import com.yuntai.upp.client.config.constant.ConstantInstance;
import com.yuntai.upp.client.fresh.model.dto.network.NetworkDto;
import com.yuntai.upp.sdk.interfaces.Signable;
import com.yuntai.upp.sdk.util.SignUtil;
import org.junit.Assert;

import static com.yuntai.upp.client.config.constant.ConstantInstance.ISV_ID;
import static com.yuntai.upp.client.config.constant.ConstantInstance.PARTNER_ID;

/**
 * @description 数据模拟-网络链路
 * @className NetworkMock
 * @package com.yuntai.upp.access.fresh.mock
 * @author jinren@hsyuntai.com
 * @date 2019/11/20 17:02
 * @copyright 版权归 HSYUNTAI 所有
 */
public class NetworkMock {

    /**
     * @description 正常场景
     * @param
     * @return com.yuntai.upp.client.fresh.model.dto.network.NetworkDto
     * @author jinren@hsyuntai.com
     * @date 2019/11/20 17:03
     */
    public static NetworkDto normal() {
        NetworkDto model = new NetworkDto().toBuilder()
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
