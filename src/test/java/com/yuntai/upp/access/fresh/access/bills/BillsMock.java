package com.yuntai.upp.access.fresh.access.bills;

import com.yuntai.upp.client.basic.util.DateUtil;
import com.yuntai.upp.client.fresh.model.dto.bills.BillsDto;
import com.yuntai.upp.sdk.enums.CheckBillsDownloadType;
import com.yuntai.upp.sdk.enums.YesOrNo;
import com.yuntai.upp.sdk.interfaces.Signable;

import java.text.MessageFormat;
import java.time.LocalDateTime;

/**
 * @description 数据模拟
 * @className BillsMock
 * @package com.yuntai.upp.access.fresh.access.bills
 * @author jinren@hsyuntai.com
 * @date 2019/11/8 17:29
 * @copyright 版权归 HSYUNTAI 所有
 */
public class BillsMock {

    /**
     * @description 正常场景-数据
     * @param
     * @return com.yuntai.upp.client.fresh.model.dto.bills.BillsDto
     * @author jinren@hsyuntai.com
     * @date 2019/11/8 17:30
     */
    protected static BillsDto normal() {
         return BillsDto.builder()
                .timestamp(System.currentTimeMillis())
                .version(Signable.VERSION)
                .ftpIp("114.215.200.225")
                .ftpPort(21)
                .ftpUser("dzftp")
                .ftpPwd("dzxqq")
                .filePath(MessageFormat.format("/upp/bills/{0}/current/triple/partner/{1}/", 999L, DateUtil.format(LocalDateTime.now(), DateUtil.FORMAT_DATE)))
                .fileName(MessageFormat.format("{0}-triple-partner-{1}-bills.csv", 999L, DateUtil.format(LocalDateTime.now(), DateUtil.FORMAT_DATE)))
                .partnerId(999L)
                .isMergeRefund(YesOrNo.YES.getCode())
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().minusHours(2))
                .checkBillsDownloadType(CheckBillsDownloadType.DIRECT.getCode())
                .build();
    }
}
