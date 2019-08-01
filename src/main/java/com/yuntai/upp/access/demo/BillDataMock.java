package com.yuntai.upp.access.demo;

import com.yuntai.upp.client.basic.util.UUIDUtil;
import com.yuntai.upp.client.fresh.model.vo.bill.BillVo;
import com.yuntai.upp.sdk.enums.BizType;
import com.yuntai.upp.sdk.enums.ChannelType;
import com.yuntai.upp.sdk.enums.CheckBillsPayType;
import com.yuntai.upp.sdk.enums.CheckBillsStatType;
import com.yuntai.upp.sdk.enums.SourceType;
import com.yuntai.upp.sdk.enums.TradeType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @description 账单下载数据模拟
 * @className BillDataMock
 * @package com.yuntai.upp.access.demo
 * @author jinren@hsyuntai.com
 * @date 2019-06-22 10:11
 * @copyright 版权归 HSYUNTAI 所有
 */
public class BillDataMock {

    private static final Integer LENGTH = 100;

    /**
     * @description 数据模拟(真实场景禁止使用,仅供开发使用)
     * @param
     * @return java.util.List<com.yuntai.upp.client.basic.model.vo.bill.BillVo>
     * @author jinren@hsyuntai.com
     * @date 2019-06-22 10:13
     */
    public static List<BillVo> mock() {
        List<BillVo> vos = new ArrayList<>();

        /* 模拟数据,仅供无 HIS 接口时,工程测试使用 - start */
        for (int i = 0; i < LENGTH; i ++) {
            /*
             * 以下各值请务必按规则|枚举进行赋值(上层有规则校验限制)
             */
            vos.add(BillVo.builder()
                    // 商户标识
                    // 该值可直接通过 BillHelper 中的 data 方法中的形参 dto 中直接获取
                    // partnerId = dto.getPartnerId().get(0)
                    .partnerId(999L)
                    // 账单日期
                    // 真实场景可通过起始时间进行转换
                    // end_time - start_time <= 24H && start_time >= 00:00
                    .billsDate(LocalDate.now())
                    // pay:支付 refundFee:退款
                    .tradeType(Math.random() > 0.5D ? TradeType.PAY.getCode() : TradeType.REFUND.getCode())
                    // 注意!!! 金额数字没有负数,取绝对值
                    .tradeFee(new BigDecimal(Math.random() * 100).abs().setScale(2, BigDecimal.ROUND_HALF_UP))
                    // 交易时间
                    .tradeTime(LocalDateTime.now())

                    // 芸泰流水
                    .outTradeNo(UUIDUtil.create())
                    // 第三方流水
                    .inTradeNo(UUIDUtil.create())
                    // HIS 流水
                    .tradeNo(UUIDUtil.create())

                    // 业务类型 请参照 BizType 枚举类
                    .bizType(BizType.REGISTER.getCode())
                    // 院区标识(存在这赋值,部分商户需要)
                    .districtId(UUIDUtil.create())
                    // 来源 outpatient:门诊 inpatient:住院
                    .sourceType(SourceType.INPATIENT.getCode())
                    // 类型 self:自费 medical:医保
                    .payType(CheckBillsPayType.SELF.getCode())
                    // 对账类型 改值 @服务端对账开发人员,按照服务端配置赋值
                    .statType(CheckBillsStatType.ONE.getCode())
                    // 支付渠道
                    .channelType(ChannelType.ALI.getCode())
                    .build());
        }
        /* 模拟数据,仅供无 HIS 接口时,工程测试使用 - end */
        return vos;
    }
}
