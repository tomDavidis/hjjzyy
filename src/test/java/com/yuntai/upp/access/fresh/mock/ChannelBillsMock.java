package com.yuntai.upp.access.fresh.mock;

import com.yuntai.upp.client.config.cache.CacheInstance;
import com.yuntai.upp.client.fresh.model.dto.channelbills.ChannelBillsDto;
import com.yuntai.upp.sdk.util.SignUtil;
import org.junit.Assert;

import java.time.LocalDate;

import static com.yuntai.upp.client.config.constant.ConstantInstance.ISV_ID;
import static com.yuntai.upp.client.config.constant.ConstantInstance.PARTNER_ID;
import static com.yuntai.upp.sdk.interfaces.Signable.VERSION;

/**
 * @description 数据模拟-账单下载(渠道账单)
 * @className ChannelBillsMock
 * @package com.yuntai.upp.access.fresh.mock
 * @author jinren@hsyuntai.com
 * @date 2019/11/21 14:02
 * @copyright 版权归 HSYUNTAI 所有
 */
public class ChannelBillsMock {

    /**
     * @description 正常场景
     * @param
     * @return com.yuntai.upp.client.fresh.model.dto.channelbills.ChannelBillsDto
     * @author jinren@hsyuntai.com
     * @date 2019/11/21 14:02
     */
    public static ChannelBillsDto normal() {
        ChannelBillsDto model = new ChannelBillsDto().toBuilder()
                .timestamp(System.currentTimeMillis())
                .version(VERSION)
                /*
                 * 临时使用(仅供单元测试, 实际场景禁止采用该方式)
                 */
                .isvId(ISV_ID)
                .partnerId(PARTNER_ID)
                .startDate(LocalDate.now().withMonth(11).withDayOfMonth(6))
                .endDate(LocalDate.now().withMonth(11).withDayOfMonth(6))
                .build();
        String salt = CacheInstance.md5Salt(PARTNER_ID, ISV_ID);
        Assert.assertNotNull(salt);
        model.setSign(SignUtil.signMd5(model, salt));
        return model;
    }
}
