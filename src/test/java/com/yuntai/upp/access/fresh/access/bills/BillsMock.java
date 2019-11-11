package com.yuntai.upp.access.fresh.access.bills;

import com.yuntai.upp.client.basic.util.DateUtil;
import com.yuntai.upp.client.config.cache.CacheInstance;
import com.yuntai.upp.client.config.constant.ConstantInstance;
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

    private static final String PATH = "/upp/bills/{0}/current/triple/partner/{1}/";
    private static final String NAME = "{0}-triple-partner-{1}-bills.csv";

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
                 /*
                  * 临时使用(仅供单元测试, 实际场景禁止采用该方式)
                  */
                .ftpIp(CacheInstance.ftpIp(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .ftpPort(CacheInstance.ftpPort(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .ftpUser(CacheInstance.ftpUser(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .ftpPwd(CacheInstance.ftpPwd(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .filePath(MessageFormat.format(PATH, ConstantInstance.PARTNER_ID, DateUtil.format(LocalDateTime.now(), DateUtil.FORMAT_DATE)))
                .fileName(MessageFormat.format(NAME, ConstantInstance.PARTNER_ID, DateUtil.format(LocalDateTime.now(), DateUtil.FORMAT_DATE)))
                .partnerId(ConstantInstance.PARTNER_ID)
                .isMergeRefund(YesOrNo.YES.getCode())
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().minusHours(2))
                .checkBillsDownloadType(CheckBillsDownloadType.DIRECT.getCode())
                .build();
    }

    /**
     * @description 缺失-timestamp
     * @param
     * @return com.yuntai.upp.client.fresh.model.dto.bills.BillsDto
     * @author jinren@hsyuntai.com
     * @date 2019/11/11 09:01
     */
    protected static BillsDto defectTimestamp() {
        return BillsDto.builder()
                .timestamp(System.currentTimeMillis())
                .version(Signable.VERSION)
                /*
                 * 临时使用(仅供单元测试, 实际场景禁止采用该方式)
                 */
                .ftpIp(CacheInstance.ftpIp(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .ftpPort(CacheInstance.ftpPort(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .ftpUser(CacheInstance.ftpUser(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .ftpPwd(CacheInstance.ftpPwd(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .filePath(MessageFormat.format(PATH, ConstantInstance.PARTNER_ID, DateUtil.format(LocalDateTime.now(), DateUtil.FORMAT_DATE)))
                .fileName(MessageFormat.format(NAME, ConstantInstance.PARTNER_ID, DateUtil.format(LocalDateTime.now(), DateUtil.FORMAT_DATE)))
                .partnerId(ConstantInstance.PARTNER_ID)
                .isMergeRefund(YesOrNo.YES.getCode())
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().minusHours(2))
                .checkBillsDownloadType(CheckBillsDownloadType.DIRECT.getCode())
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
        return BillsDto.builder()
                .timestamp(System.currentTimeMillis())
                .version(Signable.VERSION)
                /*
                 * 临时使用(仅供单元测试, 实际场景禁止采用该方式)
                 */
                .ftpIp(CacheInstance.ftpIp(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .ftpPort(CacheInstance.ftpPort(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .ftpUser(CacheInstance.ftpUser(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .ftpPwd(CacheInstance.ftpPwd(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .filePath(MessageFormat.format(PATH, ConstantInstance.PARTNER_ID, DateUtil.format(LocalDateTime.now(), DateUtil.FORMAT_DATE)))
                .fileName(MessageFormat.format(NAME, ConstantInstance.PARTNER_ID, DateUtil.format(LocalDateTime.now(), DateUtil.FORMAT_DATE)))
                .partnerId(ConstantInstance.PARTNER_ID)
                .isMergeRefund(YesOrNo.YES.getCode())
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().minusHours(2))
                .checkBillsDownloadType(CheckBillsDownloadType.DIRECT.getCode())
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
        return BillsDto.builder()
                .timestamp(System.currentTimeMillis())
                .version(Signable.VERSION)
                /*
                 * 临时使用(仅供单元测试, 实际场景禁止采用该方式)
                 */
                .ftpIp(CacheInstance.ftpIp(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .ftpPort(CacheInstance.ftpPort(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .ftpUser(CacheInstance.ftpUser(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .ftpPwd(CacheInstance.ftpPwd(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .filePath(MessageFormat.format(PATH, ConstantInstance.PARTNER_ID, DateUtil.format(LocalDateTime.now(), DateUtil.FORMAT_DATE)))
                .fileName(MessageFormat.format(NAME, ConstantInstance.PARTNER_ID, DateUtil.format(LocalDateTime.now(), DateUtil.FORMAT_DATE)))
                .partnerId(ConstantInstance.PARTNER_ID)
                .isMergeRefund(YesOrNo.YES.getCode())
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().minusHours(2))
                .checkBillsDownloadType(CheckBillsDownloadType.DIRECT.getCode())
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
        return BillsDto.builder()
                .timestamp(System.currentTimeMillis())
                .version(Signable.VERSION)
                /*
                 * 临时使用(仅供单元测试, 实际场景禁止采用该方式)
                 */
                .ftpIp(CacheInstance.ftpIp(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .ftpPort(CacheInstance.ftpPort(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .ftpUser(CacheInstance.ftpUser(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .ftpPwd(CacheInstance.ftpPwd(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .filePath(MessageFormat.format(PATH, ConstantInstance.PARTNER_ID, DateUtil.format(LocalDateTime.now(), DateUtil.FORMAT_DATE)))
                .fileName(MessageFormat.format(NAME, ConstantInstance.PARTNER_ID, DateUtil.format(LocalDateTime.now(), DateUtil.FORMAT_DATE)))
                .partnerId(ConstantInstance.PARTNER_ID)
                .isMergeRefund(YesOrNo.YES.getCode())
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().minusHours(2))
                .checkBillsDownloadType(CheckBillsDownloadType.DIRECT.getCode())
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
        return BillsDto.builder()
                .timestamp(System.currentTimeMillis())
                .version(Signable.VERSION)
                /*
                 * 临时使用(仅供单元测试, 实际场景禁止采用该方式)
                 */
                .ftpIp(CacheInstance.ftpIp(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .ftpPort(CacheInstance.ftpPort(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .ftpUser(CacheInstance.ftpUser(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .ftpPwd(CacheInstance.ftpPwd(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .filePath(MessageFormat.format(PATH, ConstantInstance.PARTNER_ID, DateUtil.format(LocalDateTime.now(), DateUtil.FORMAT_DATE)))
                .fileName(MessageFormat.format(NAME, ConstantInstance.PARTNER_ID, DateUtil.format(LocalDateTime.now(), DateUtil.FORMAT_DATE)))
                .partnerId(ConstantInstance.PARTNER_ID)
                .isMergeRefund(YesOrNo.YES.getCode())
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().minusHours(2))
                .checkBillsDownloadType(CheckBillsDownloadType.DIRECT.getCode())
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
        return BillsDto.builder()
                .timestamp(System.currentTimeMillis())
                .version(Signable.VERSION)
                /*
                 * 临时使用(仅供单元测试, 实际场景禁止采用该方式)
                 */
                .ftpIp(CacheInstance.ftpIp(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .ftpPort(CacheInstance.ftpPort(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .ftpUser(CacheInstance.ftpUser(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .ftpPwd(CacheInstance.ftpPwd(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .filePath(MessageFormat.format(PATH, ConstantInstance.PARTNER_ID, DateUtil.format(LocalDateTime.now(), DateUtil.FORMAT_DATE)))
                .fileName(MessageFormat.format(NAME, ConstantInstance.PARTNER_ID, DateUtil.format(LocalDateTime.now(), DateUtil.FORMAT_DATE)))
                .partnerId(ConstantInstance.PARTNER_ID)
                .isMergeRefund(YesOrNo.YES.getCode())
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().minusHours(2))
                .checkBillsDownloadType(CheckBillsDownloadType.DIRECT.getCode())
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
        return BillsDto.builder()
                .timestamp(System.currentTimeMillis())
                .version(Signable.VERSION)
                /*
                 * 临时使用(仅供单元测试, 实际场景禁止采用该方式)
                 */
                .ftpIp(CacheInstance.ftpIp(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .ftpPort(CacheInstance.ftpPort(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .ftpUser(CacheInstance.ftpUser(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .ftpPwd(CacheInstance.ftpPwd(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .filePath(MessageFormat.format(PATH, ConstantInstance.PARTNER_ID, DateUtil.format(LocalDateTime.now(), DateUtil.FORMAT_DATE)))
                .fileName(MessageFormat.format(NAME, ConstantInstance.PARTNER_ID, DateUtil.format(LocalDateTime.now(), DateUtil.FORMAT_DATE)))
                .partnerId(ConstantInstance.PARTNER_ID)
                .isMergeRefund(YesOrNo.YES.getCode())
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().minusHours(2))
                .checkBillsDownloadType(CheckBillsDownloadType.DIRECT.getCode())
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
        return BillsDto.builder()
                .timestamp(System.currentTimeMillis())
                .version(Signable.VERSION)
                /*
                 * 临时使用(仅供单元测试, 实际场景禁止采用该方式)
                 */
                .ftpIp(CacheInstance.ftpIp(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .ftpPort(CacheInstance.ftpPort(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .ftpUser(CacheInstance.ftpUser(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .ftpPwd(CacheInstance.ftpPwd(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .filePath(MessageFormat.format(PATH, ConstantInstance.PARTNER_ID, DateUtil.format(LocalDateTime.now(), DateUtil.FORMAT_DATE)))
                .fileName(MessageFormat.format(NAME, ConstantInstance.PARTNER_ID, DateUtil.format(LocalDateTime.now(), DateUtil.FORMAT_DATE)))
                .partnerId(ConstantInstance.PARTNER_ID)
                .isMergeRefund(YesOrNo.YES.getCode())
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().minusHours(2))
                .checkBillsDownloadType(CheckBillsDownloadType.DIRECT.getCode())
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
        return BillsDto.builder()
                .timestamp(System.currentTimeMillis())
                .version(Signable.VERSION)
                /*
                 * 临时使用(仅供单元测试, 实际场景禁止采用该方式)
                 */
                .ftpIp(CacheInstance.ftpIp(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .ftpPort(CacheInstance.ftpPort(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .ftpUser(CacheInstance.ftpUser(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .ftpPwd(CacheInstance.ftpPwd(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .filePath(MessageFormat.format(PATH, ConstantInstance.PARTNER_ID, DateUtil.format(LocalDateTime.now(), DateUtil.FORMAT_DATE)))
                .fileName(MessageFormat.format(NAME, ConstantInstance.PARTNER_ID, DateUtil.format(LocalDateTime.now(), DateUtil.FORMAT_DATE)))
                .partnerId(ConstantInstance.PARTNER_ID)
                .isMergeRefund(YesOrNo.YES.getCode())
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().minusHours(2))
                .checkBillsDownloadType(CheckBillsDownloadType.DIRECT.getCode())
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
        return BillsDto.builder()
                .timestamp(System.currentTimeMillis())
                .version(Signable.VERSION)
                /*
                 * 临时使用(仅供单元测试, 实际场景禁止采用该方式)
                 */
                .ftpIp(CacheInstance.ftpIp(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .ftpPort(CacheInstance.ftpPort(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .ftpUser(CacheInstance.ftpUser(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .ftpPwd(CacheInstance.ftpPwd(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .filePath(MessageFormat.format(PATH, ConstantInstance.PARTNER_ID, DateUtil.format(LocalDateTime.now(), DateUtil.FORMAT_DATE)))
                .fileName(MessageFormat.format(NAME, ConstantInstance.PARTNER_ID, DateUtil.format(LocalDateTime.now(), DateUtil.FORMAT_DATE)))
                .partnerId(ConstantInstance.PARTNER_ID)
                .isMergeRefund(YesOrNo.YES.getCode())
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().minusHours(2))
                .checkBillsDownloadType(CheckBillsDownloadType.DIRECT.getCode())
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
        return BillsDto.builder()
                .timestamp(System.currentTimeMillis())
                .version(Signable.VERSION)
                /*
                 * 临时使用(仅供单元测试, 实际场景禁止采用该方式)
                 */
                .ftpIp(CacheInstance.ftpIp(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .ftpPort(CacheInstance.ftpPort(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .ftpUser(CacheInstance.ftpUser(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .ftpPwd(CacheInstance.ftpPwd(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .filePath(MessageFormat.format(PATH, ConstantInstance.PARTNER_ID, DateUtil.format(LocalDateTime.now(), DateUtil.FORMAT_DATE)))
                .fileName(MessageFormat.format(NAME, ConstantInstance.PARTNER_ID, DateUtil.format(LocalDateTime.now(), DateUtil.FORMAT_DATE)))
                .partnerId(ConstantInstance.PARTNER_ID)
                .isMergeRefund(YesOrNo.YES.getCode())
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().minusHours(2))
                .checkBillsDownloadType(CheckBillsDownloadType.DIRECT.getCode())
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
        return BillsDto.builder()
                .timestamp(System.currentTimeMillis())
                .version(Signable.VERSION)
                /*
                 * 临时使用(仅供单元测试, 实际场景禁止采用该方式)
                 */
                .ftpIp(CacheInstance.ftpIp(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .ftpPort(CacheInstance.ftpPort(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .ftpUser(CacheInstance.ftpUser(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .ftpPwd(CacheInstance.ftpPwd(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .filePath(MessageFormat.format(PATH, ConstantInstance.PARTNER_ID, DateUtil.format(LocalDateTime.now(), DateUtil.FORMAT_DATE)))
                .fileName(MessageFormat.format(NAME, ConstantInstance.PARTNER_ID, DateUtil.format(LocalDateTime.now(), DateUtil.FORMAT_DATE)))
                .partnerId(ConstantInstance.PARTNER_ID)
                .isMergeRefund(YesOrNo.YES.getCode())
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().minusHours(2))
                .checkBillsDownloadType(CheckBillsDownloadType.DIRECT.getCode())
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
        return BillsDto.builder()
                .timestamp(System.currentTimeMillis())
                .version(Signable.VERSION)
                /*
                 * 临时使用(仅供单元测试, 实际场景禁止采用该方式)
                 */
                .ftpIp(CacheInstance.ftpIp(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .ftpPort(CacheInstance.ftpPort(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .ftpUser(CacheInstance.ftpUser(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .ftpPwd(CacheInstance.ftpPwd(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID))
                .filePath(MessageFormat.format(PATH, ConstantInstance.PARTNER_ID, DateUtil.format(LocalDateTime.now(), DateUtil.FORMAT_DATE)))
                .fileName(MessageFormat.format(NAME, ConstantInstance.PARTNER_ID, DateUtil.format(LocalDateTime.now(), DateUtil.FORMAT_DATE)))
                .partnerId(ConstantInstance.PARTNER_ID)
                .isMergeRefund(YesOrNo.YES.getCode())
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().minusHours(2))
                .checkBillsDownloadType(CheckBillsDownloadType.DIRECT.getCode())
                .build();
    }
}
