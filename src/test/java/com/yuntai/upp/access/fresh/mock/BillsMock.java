package com.yuntai.upp.access.fresh.mock;

import com.yuntai.upp.client.basic.util.DateUtil;
import com.yuntai.upp.client.config.cache.CacheInstance;
import com.yuntai.upp.client.fresh.model.dto.bills.BillsDto;
import com.yuntai.upp.sdk.enums.CheckBillsDownloadType;
import com.yuntai.upp.sdk.enums.YesOrNo;
import com.yuntai.upp.sdk.util.SignUtil;
import org.junit.Assert;

import java.text.MessageFormat;
import java.time.LocalDateTime;

import static com.yuntai.upp.client.basic.util.DateUtil.FORMAT_DATE;
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

    private static final String PATH = "/upp/bills/{0}/current/triple/partner/{1}/";
    private static final String NAME = "{0}-triple-partner-{1}-bills.csv";

    /**
     * @description 正常场景
     * @param
     * @return com.yuntai.upp.client.fresh.model.dto.bills.BillsDto
     * @author jinren@hsyuntai.com
     * @date 2019/11/8 17:30
     */
    public static BillsDto normal() {
        BillsDto model = BillsDto.builder()
                .timestamp(System.currentTimeMillis())
                .version(VERSION)
                /*
                 * 临时使用(仅供单元测试, 实际场景禁止采用该方式)
                 */
                .partnerId(PARTNER_ID)
                .ftpIp(CacheInstance.ftpIp(PARTNER_ID, ISV_ID))
                .ftpPort(CacheInstance.ftpPort(PARTNER_ID, ISV_ID))
                .ftpUser(CacheInstance.ftpUser(PARTNER_ID, ISV_ID))
                .ftpPwd(CacheInstance.ftpPwd(PARTNER_ID, ISV_ID))
                .filePath(MessageFormat.format(PATH, PARTNER_ID, DateUtil.format(LocalDateTime.now(), FORMAT_DATE)))
                .fileName(MessageFormat.format(NAME, PARTNER_ID, DateUtil.format(LocalDateTime.now(), FORMAT_DATE)))
                .isMergeRefund(YesOrNo.YES.getCode())
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().minusHours(2))
                .checkBillsDownloadType(CheckBillsDownloadType.DIRECT.getCode())
                .build();
        String salt = CacheInstance.md5Salt(PARTNER_ID, ISV_ID);
        Assert.assertNotNull(salt);
        model.setSign(SignUtil.signMd5(model, salt));
        return model;
    }
}
