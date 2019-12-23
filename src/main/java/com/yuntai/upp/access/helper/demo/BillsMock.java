package com.yuntai.upp.access.helper.demo;

import com.yuntai.upp.client.basic.enums.inner.InnerCmdType;
import com.yuntai.upp.client.basic.util.BigDecimalUtil;
import com.yuntai.upp.client.basic.util.UUIDUtil;
import com.yuntai.upp.client.config.strategy.StrategyContext;
import com.yuntai.upp.client.fresh.model.dto.bills.BillsDto;
import com.yuntai.upp.client.fresh.model.dto.yuntaibills.YuntaiBillsDto;
import com.yuntai.upp.client.fresh.model.vo.bills.BillsVo;
import com.yuntai.upp.client.fresh.model.vo.yuntaibills.YuntaiBillsVo;
import com.yuntai.upp.sdk.enums.BizType;
import com.yuntai.upp.sdk.enums.ChannelType;
import com.yuntai.upp.sdk.enums.CheckBillsPayType;
import com.yuntai.upp.sdk.enums.CheckBillsStatType;
import com.yuntai.upp.sdk.enums.SourceType;
import com.yuntai.upp.sdk.enums.TradeType;
import lombok.NonNull;
import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @description 账单下载数据模拟
 * @className BillsMock
 * @package com.yuntai.upp.access.helper.demo
 * @author jinren@hsyuntai.com
 * @date 2019-06-22 10:11
 * @copyright 版权归 HSYUNTAI 所有
 */
public class BillsMock {

    private static final Integer LENGTH = 100;

    /**
     * @description 数据模拟(真实场景禁止使用,仅供开发使用)
     * @param
     * @return java.util.List<com.yuntai.upp.client.fresh.model.vo.bills.BillsVo>
     * @author jinren@hsyuntai.com
     * @date 2019/12/23 16:24
     */
    public static List<BillsVo> mock() {
        List<BillsVo> vos = new ArrayList<>();

        /* 模拟数据,仅供无 HIS 接口时,工程测试使用 - start */
        for (int i = 0; i < LENGTH; i ++) {
            /*
             * 以下各值请务必按规则|枚举进行赋值(上层有规则校验限制)
             */
            vos.add(BillsVo.builder()
                    // 商户标识
                    // 该值可直接通过 BillHelper 中的 data 方法中的形参 dto 中直接获取
                    // partnerId = dto.getPartnerId()
                    // [不可为空, 参数返回后必校验]
                    .partnerId(999L)
                    // 账单日期
                    // 真实场景可通过起始时间进行转换
                    // end_time - start_time <= 24H && start_time >= 00:00
                    // [不可为空, 参数返回后必校验]
                    .billsDate(LocalDate.now())
                    // pay:支付 refundFee:退款
                    // [不可为空, 参数返回后必校验]
                    .tradeType(Math.random() > 0.5D ? TradeType.PAY.getCode() : TradeType.REFUND.getCode())
                    // 注意!!! 金额数字没有负数,取绝对值
                    // [不可为空, 参数返回后必校验]
                    .tradeFee(BigDecimalUtil.convert(Math.random() * 100).abs().setScale(2, BigDecimal.ROUND_HALF_UP))
                    // 交易时间
                    // [不可为空, 参数返回后必校验]
                    .tradeTime(LocalDateTime.now())
                    // 芸泰流水
                    // [不可为空, 参数返回后必校验]
                    .outTradeNo(UUIDUtil.create())
                    // 第三方流水
                    // [不可为空, 参数返回后必校验]
                    .inTradeNo(UUIDUtil.create())
                    // HIS 流水
                    // [不可为空, 参数返回后必校验]
                    .tradeNo(UUIDUtil.create())
                    // 业务类型 请参照 BizType 枚举类
                    // [不可为空, 参数返回后必校验]
                    .bizType(BizType.REGISTER.getCode())
                    // 院区标识(存在这赋值,部分商户需要)
                    // [可为空, 上层将校验非空时的参数值]
                    .districtId(RandomStringUtils.randomAlphanumeric(8))
                    // 来源 outpatient:门诊 inpatient:住院
                    // [可为空, 上层将校验非空时的参数值]
                    .sourceType(SourceType.INPATIENT.getCode())
                    // 类型 self:自费 medical:医保
                    // [可为空, 上层将校验非空时的参数值]
                    .payType(CheckBillsPayType.SELF.getCode())
                    // 对账类型 改值 @服务端对账开发人员,按照服务端配置赋值
                    // [可为空, 上层将校验非空时的参数值]
                    .statType(CheckBillsStatType.ONE.getCode())
                    // 支付渠道
                    // [可为空, 上层将校验非空时的参数值]
                    .channelType(ChannelType.ALI.getCode())
                    // 收款员工号
                    // [可为空]
                    .empno("")
                    // 收款设备编号
                    // [可为空]
                    .deviceNo("")
                    .build());
        }
        /* 模拟数据,仅供无 HIS 接口时,工程测试使用 - end */
        return vos;
    }

    /**
     * @description 数据模拟(真实场景禁止使用,仅供开发使用)
     * @param dto
     * @return java.util.List<com.yuntai.upp.client.fresh.model.vo.bills.BillsVo>
     * @author jinren@hsyuntai.com
     * @date 2019/12/23 16:24
     */
    public static List<BillsVo> mock(@NonNull BillsDto dto) {
        YuntaiBillsVo vo = StrategyContext.operate(YuntaiBillsDto.builder()
                .billsNo(dto.getBillsNo())
                .bizType(Arrays.stream(BizType.values()).map(BizType::getCode).collect(Collectors.toList()))
                .startDate(dto.getStartTime().toLocalDate())
                .endDate(dto.getStartTime().toLocalDate())
                .build(), InnerCmdType.YUNTAI_BILLS);
        return Optional.ofNullable(vo.getDetail())
                .orElse(Collections.emptyList())
                .stream()
                .map(bills -> BillsVo.builder()
                        // 商户标识
                        // 该值可直接通过 BillHelper 中的 data 方法中的形参 dto 中直接获取
                        // partnerId = dto.getPartnerId()
                        // [不可为空, 参数返回后必校验]
                        .partnerId(bills.getPartnerId())
                        // 账单日期
                        // 真实场景可通过起始时间进行转换
                        // end_time - start_time <= 24H && start_time >= 00:00
                        // [不可为空, 参数返回后必校验]
                        .billsDate(bills.getBillsDate())
                        // pay:支付 refundFee:退款
                        // [不可为空, 参数返回后必校验]
                        .tradeType(bills.getTradeType())
                        // 注意!!! 金额数字没有负数,取绝对值
                        // [不可为空, 参数返回后必校验]
                        .tradeFee(bills.getTradeFee())
                        // 交易时间
                        // [不可为空, 参数返回后必校验]
                        .tradeTime(bills.getTradeTime())
                        // 芸泰流水
                        // [不可为空, 参数返回后必校验]
                        .outTradeNo(bills.getOutTradeNo())
                        // 第三方流水
                        // [不可为空, 参数返回后必校验]
                        .inTradeNo(bills.getInTradeNo())
                        // HIS 流水
                        // [不可为空, 参数返回后必校验]
                        .tradeNo(bills.getTradeNo())
                        // 业务类型 请参照 BizType 枚举类
                        // [不可为空, 参数返回后必校验]
                        .bizType(bills.getBizType())
                        // 院区标识(存在这赋值,部分商户需要)
                        // [可为空, 上层将校验非空时的参数值]
                        .districtId(bills.getDistrictId())
                        // 来源 outpatient:门诊 inpatient:住院
                        // [可为空, 上层将校验非空时的参数值]
                        .sourceType(bills.getSourceType())
                        // 类型 self:自费 medical:医保
                        // [可为空, 上层将校验非空时的参数值]
                        .payType(CheckBillsPayType.SELF.getCode())
                        // 对账类型 改值 @服务端对账开发人员,按照服务端配置赋值
                        // [可为空, 上层将校验非空时的参数值]
                        .statType(CheckBillsStatType.ONE.getCode())
                        // 支付渠道
                        // [可为空, 上层将校验非空时的参数值]
                        .channelType(ChannelType.ALI.getCode())
                        // 收款员工号
                        // [可为空]
                        .empno("")
                        // 收款设备编号
                        // [可为空]
                        .deviceNo("")
                        .build())
                .collect(Collectors.toList());
    }
}
