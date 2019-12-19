package com.yuntai.upp.access.fresh.mock;

import com.yuntai.upp.client.basic.util.UUIDUtil;
import com.yuntai.upp.client.config.cache.CacheInstance;
import com.yuntai.upp.client.fresh.model.dto.channelbills.ChannelBillsDto;
import com.yuntai.upp.sdk.enums.BizType;
import com.yuntai.upp.sdk.enums.TradeType;
import com.yuntai.upp.sdk.result.UnitedChannelBillsResult;
import com.yuntai.upp.sdk.util.SignUtil;
import lombok.NonNull;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.yuntai.upp.access.CustomConstant.BIZ_TYPE;
import static com.yuntai.upp.access.CustomConstant.CHANNEL_TYPE;
import static com.yuntai.upp.access.CustomConstant.TRADE_FEE;
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
                .startDate(LocalDate.now().withMonth(9).withDayOfMonth(18))
                .endDate(LocalDate.now().withMonth(9).withDayOfMonth(18))
                .bizType(new ArrayList<String>(2) {
                    private static final long serialVersionUID = -6891438722692253009L;
                    {
                        add(BizType.REGISTER.getCode());
                        add(BizType.BARCODE_PAY.getCode());
                    }
                })
                .billsNo(UUIDUtil.create())
                .build();
        String salt = CacheInstance.md5Salt(PARTNER_ID, ISV_ID);
        Assert.assertNotNull(salt);
        model.setSign(SignUtil.signMd5(model, salt));
        return model;
    }

    /**
     * @description 模拟云端返回
     * @param dto 请求入参模型(模拟数据)
     * @return java.util.List<com.yuntai.upp.sdk.result.UnitedChannelBillsResult>
     * @author jinren@hsyuntai.com
     * @date 2019/12/12 16:13
     */
    public static List<UnitedChannelBillsResult> mock(@NonNull ChannelBillsDto dto) {
        List<UnitedChannelBillsResult> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(UnitedChannelBillsResult.builder()
                    /*
                     * 临时使用(仅供单元测试, 实际场景禁止采用该方式)
                     */
                    .partnerId(PARTNER_ID)
                    .isvId(ISV_ID)
                    .tradeType(Objects.equals(1, i & 1) ? TradeType.PAY.getCode() : TradeType.REFUND.getCode())
                    .mchId(RandomStringUtils.randomAlphanumeric(16))
                    .bizType(BIZ_TYPE)
                    .channelType(CHANNEL_TYPE)
                    .billsDate(dto.getStartDate())
                    .tradeFee(TRADE_FEE)
                    .tradeTime(LocalDateTime.now())
                    .outTradeNo(UUIDUtil.create())
                    .inTradeNo(UUIDUtil.create())
                    .balanceNo(UUIDUtil.create())
                    .build());
        }
        return list;
    }
}
