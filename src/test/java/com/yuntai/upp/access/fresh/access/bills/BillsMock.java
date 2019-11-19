package com.yuntai.upp.access.fresh.access.bills;

import com.yuntai.upp.client.basic.util.DateUtil;
import com.yuntai.upp.client.basic.util.UUIDUtil;
import com.yuntai.upp.client.config.cache.CacheInstance;
import com.yuntai.upp.client.fresh.model.dto.bills.BillsDto;
import com.yuntai.upp.sdk.enums.CheckBillsDownloadType;
import com.yuntai.upp.sdk.enums.YesOrNo;

import java.text.MessageFormat;
import java.time.LocalDateTime;

import static com.yuntai.upp.client.basic.util.DateUtil.FORMAT_DATE;
import static com.yuntai.upp.client.config.constant.ConstantInstance.ISV_ID;
import static com.yuntai.upp.client.config.constant.ConstantInstance.PARTNER_ID;
import static com.yuntai.upp.sdk.interfaces.Signable.VERSION;

/**
 * @description 数据模拟
 * @className BillsMock
 * @package com.yuntai.upp.access.fresh.access.bills
 * @author jinren@hsyuntai.com
 * @date 2019/11/8 17:29
 * @copyright 版权归 HSYUNTAI 所有
 */
public class BillsMock {

    private static final String PATH = "/upp/bills/{0}/current/triple/partner/{1}/";
    private static final String NAME = "{0}-triple-partner-{1}-bills.csv";

    private static final BillsDto NORMAL = BillsDto.builder()
            .timestamp(System.currentTimeMillis())
            .version(VERSION)
            /*
             * 临时使用(仅供单元测试, 实际场景禁止采用该方式)
             */
            .ftpIp(CacheInstance.ftpIp(PARTNER_ID, ISV_ID))
            .ftpPort(CacheInstance.ftpPort(PARTNER_ID, ISV_ID))
            .ftpUser(CacheInstance.ftpUser(PARTNER_ID, ISV_ID))
            .ftpPwd(CacheInstance.ftpPwd(PARTNER_ID, ISV_ID))
            .filePath(MessageFormat.format(PATH, PARTNER_ID, DateUtil.format(LocalDateTime.now(), FORMAT_DATE)))
            .fileName(MessageFormat.format(NAME, PARTNER_ID, DateUtil.format(LocalDateTime.now(), FORMAT_DATE)))
            .partnerId(PARTNER_ID)
            .isMergeRefund(YesOrNo.YES.getCode())
            .startTime(LocalDateTime.now())
            .endTime(LocalDateTime.now().minusHours(2))
            .checkBillsDownloadType(CheckBillsDownloadType.DIRECT.getCode())
            /*
             * sign 字段临时赋值(默认 sign 字段内的签名必验签通过)
             */
            .sign(UUIDUtil.create())
            .build();

    /**
     * @description 正常场景-数据
     * @param
     * @return com.yuntai.upp.client.fresh.model.dto.bills.BillsDto
     * @author jinren@hsyuntai.com
     * @date 2019/11/8 17:30
     */
    protected static BillsDto normal() {
         return NORMAL;
    }

    /**
     * @description 缺失-timestamp
     * @param
     * @return com.yuntai.upp.client.fresh.model.dto.bills.BillsDto
     * @author jinren@hsyuntai.com
     * @date 2019/11/11 09:01
     */
    protected static BillsDto defectTimestamp() {
        return NORMAL.toBuilder()
                .timestamp(null)
                .build();
    }

    /**
     * @description 缺失-version
     * @param
     * @return com.yuntai.upp.client.fresh.model.dto.bills.BillsDto
     * @author jinren@hsyuntai.com
     * @date 2019/11/11 09:01
     */
    protected static BillsDto defectVersion() {
        return NORMAL.toBuilder()
                .version(null)
                .build();
    }

    /**
     * @description 缺失-ftpIp
     * @param
     * @return com.yuntai.upp.client.fresh.model.dto.bills.BillsDto
     * @author jinren@hsyuntai.com
     * @date 2019/11/11 09:02
     */
    protected static BillsDto defectFtpIp() {
        return NORMAL.toBuilder()
                .ftpIp(null)
                .build();
    }

    /**
     * @description 缺失-ftpPort
     * @param
     * @return com.yuntai.upp.client.fresh.model.dto.bills.BillsDto
     * @author jinren@hsyuntai.com
     * @date 2019/11/11 09:02
     */
    protected static BillsDto defectFtpPort() {
        return NORMAL.toBuilder()
                .ftpPort(null)
                .build();
    }

    /**
     * @description 缺失-ftpUser
     * @param
     * @return com.yuntai.upp.client.fresh.model.dto.bills.BillsDto
     * @author jinren@hsyuntai.com
     * @date 2019/11/11 09:02
     */
    protected static BillsDto defectFtpUser() {
        return NORMAL.toBuilder()
                .ftpUser(null)
                .build();
    }

    /**
     * @description 缺失-ftpPwd
     * @param
     * @return com.yuntai.upp.client.fresh.model.dto.bills.BillsDto
     * @author jinren@hsyuntai.com
     * @date 2019/11/11 09:02
     */
    protected static BillsDto defectFtpPwd() {
        return NORMAL.toBuilder()
                .ftpPwd(null)
                .build();
    }

    /**
     * @description 缺失-filePath
     * @param
     * @return com.yuntai.upp.client.fresh.model.dto.bills.BillsDto
     * @author jinren@hsyuntai.com
     * @date 2019/11/11 09:04
     */
    protected static BillsDto defectFilePath() {
        return NORMAL.toBuilder()
                .filePath(null)
                .build();
    }

    /**
     * @description 缺失-fileName
     * @param
     * @return com.yuntai.upp.client.fresh.model.dto.bills.BillsDto
     * @author jinren@hsyuntai.com
     * @date 2019/11/11 09:04
     */
    protected static BillsDto defectFileName() {
        return NORMAL.toBuilder()
                .fileName(null)
                .build();
    }

    /**
     * @description 缺失-partnerId
     * @param
     * @return com.yuntai.upp.client.fresh.model.dto.bills.BillsDto
     * @author jinren@hsyuntai.com
     * @date 2019/11/11 09:02
     */
    protected static BillsDto defectPartnerId() {
        return NORMAL.toBuilder()
                .partnerId(null)
                .build();
    }

    /**
     * @description 缺失-isMergeRefund
     * @param
     * @return com.yuntai.upp.client.fresh.model.dto.bills.BillsDto
     * @author jinren@hsyuntai.com
     * @date 2019/11/11 09:02
     */
    protected static BillsDto defectIsMergeRefund() {
        return NORMAL.toBuilder()
                .isMergeRefund(null)
                .build();
    }

    /**
     * @description 缺失-startTime
     * @param
     * @return com.yuntai.upp.client.fresh.model.dto.bills.BillsDto
     * @author jinren@hsyuntai.com
     * @date 2019/11/11 09:03
     */
    protected static BillsDto defectStartTime() {
        return NORMAL.toBuilder()
                .startTime(null)
                .build();
    }

    /**
     * @description 缺失-endTime
     * @param
     * @return com.yuntai.upp.client.fresh.model.dto.bills.BillsDto
     * @author jinren@hsyuntai.com
     * @date 2019/11/11 09:04
     */
    protected static BillsDto defectEndTime() {
        return NORMAL.toBuilder()
                .endTime(null)
                .build();
    }

    /**
     * @description 缺失-checkBillsDownloadType
     * @param
     * @return com.yuntai.upp.client.fresh.model.dto.bills.BillsDto
     * @author jinren@hsyuntai.com
     * @date 2019/11/11 09:05
     */
    protected static BillsDto defectCheckBillsDownloadType() {
        return NORMAL.toBuilder()
                .checkBillsDownloadType(null)
                .build();
    }
}
