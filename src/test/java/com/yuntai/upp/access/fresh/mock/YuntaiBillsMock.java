package com.yuntai.upp.access.fresh.mock;

import com.yuntai.upp.client.basic.util.DateUtil;
import com.yuntai.upp.client.basic.util.UUIDUtil;
import com.yuntai.upp.client.config.cache.CacheInstance;
import com.yuntai.upp.client.fresh.model.dto.yuntaibills.YuntaiBillsDto;
import com.yuntai.upp.sdk.enums.SourceType;
import com.yuntai.upp.sdk.enums.TradeType;
import com.yuntai.upp.sdk.util.SignUtil;
import lombok.NonNull;
import org.junit.Assert;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.yuntai.upp.access.CustomConstant.BIZ_TYPE;
import static com.yuntai.upp.access.CustomConstant.CHANNEL_PRODUCT;
import static com.yuntai.upp.access.CustomConstant.CHANNEL_TYPE;
import static com.yuntai.upp.access.CustomConstant.TRADE_FEE;
import static com.yuntai.upp.client.config.constant.ConstantInstance.ISV_ID;
import static com.yuntai.upp.client.config.constant.ConstantInstance.PARTNER_ID;
import static com.yuntai.upp.sdk.interfaces.Signable.VERSION;

/**
 * @description 数据模拟-账单下载(芸泰账单)
 * @className YuntaiBillsMock
 * @package com.yuntai.upp.access.fresh.mock
 * @author jinren@hsyuntai.com
 * @date 2019/11/21 20:13
 * @copyright 版权归 HSYUNTAI 所有
 */
public class YuntaiBillsMock {

    /**
     * @description 正常场景
     * @param
     * @return com.yuntai.upp.client.fresh.model.dto.yuntaibills.YuntaiBillsDto
     * @author jinren@hsyuntai.com
     * @date 2019/11/21 20:15
     */
    public static YuntaiBillsDto normal() {
        YuntaiBillsDto model = new YuntaiBillsDto().toBuilder()
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

    /**
     * @description 模拟云端返回
     * @param dto 请求入参模型(模拟数据)
     * @return java.io.InputStream
     * @author jinren@hsyuntai.com
     * @date 2019/12/2 11:23
     */
    public static InputStream mock(@NonNull YuntaiBillsDto dto) {
        StringBuilder sb = new StringBuilder();
        sb.append("partnerId,isvId,billsDate,tradeType,channelType,channelProduct,tradeFee,derateFee,"
                + "tradeTime,outTradeNo,inTradeNo,tradeNo,balanceNo,bizType,districtId,sourceType\n");
        for (int i = 0; i < 10; i++) {
            /*
             * 临时使用(仅供单元测试, 实际场景禁止采用该方式)
             */
            sb.append(PARTNER_ID).append(",")
                    .append(ISV_ID).append(",")
                    .append(DateUtil.format(dto.getStartDate(), DateUtil.FORMAT_DATE)).append(",")
                    .append(TradeType.PAY.getCode()).append(",")
                    .append(CHANNEL_TYPE).append(",")
                    .append(CHANNEL_PRODUCT).append(",")
                    .append(TRADE_FEE).append(",")
                    .append(TRADE_FEE).append(",")
                    .append(DateUtil.format(LocalDateTime.now(), DateUtil.FORMAT_DATE_TIME)).append(",")
                    .append(UUIDUtil.create()).append(",")
                    .append(UUIDUtil.create()).append(",")
                    .append(UUIDUtil.create()).append(",")
                    .append(UUIDUtil.create()).append(",")
                    .append(BIZ_TYPE).append(",")
                    .append(UUIDUtil.create()).append(",")
                    .append(SourceType.OUTPATIENT.getCode())
                    .append("\n");
        }
        return new ByteArrayInputStream(sb.toString().getBytes());
    }
}
