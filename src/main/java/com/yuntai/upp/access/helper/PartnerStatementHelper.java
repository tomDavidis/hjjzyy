package com.yuntai.upp.access.helper;

import com.yuntai.upp.client.config.constant.ConstantInstance;
import com.yuntai.upp.client.handler.active.operate.inherit.AbstractPartnerStatement;
import com.yuntai.upp.model.dto.access.statement.StatementDto;
import com.yuntai.upp.model.vo.access.statement.StatementVo;
import com.yuntai.upp.support.enums.BizType;
import com.yuntai.upp.support.enums.CheckBillsPayType;
import com.yuntai.upp.support.enums.CheckBillsStatType;
import com.yuntai.upp.support.enums.SourceType;
import com.yuntai.upp.support.enums.TradeType;
import com.yuntai.upp.support.util.EnumUtil;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @description 延时账单数据
 * @className PartnerStatementHelper
 * @package com.yuntai.upp.access.helper
 * @author jinren@hsyuntai.com
 * @date 2019-06-14 15:20
 * @copyright 版权归 HSYUNTAI 所有
 */

@Component
public class PartnerStatementHelper extends AbstractPartnerStatement {

    /**
     * @description 账单数据转换
     *              大多情况下,实时账单&延时账单均调用 HIS 相同视图
     *              若存在 HIS 实时账单与延时账单调用不同的视图、逻辑时,可通过 dto 中的 type 属性进行分类判断
     *              prompt : 实时
     *              delay : 延时
     *
     *              调用 HIS 接口获取账单包装成为 List<DilatoryVo>[非空]
     *              若接口无任何返回数据或调用结果出错,则直接抛出异常
     * @param dto 云端下载 bean
     * @return java.util.List<com.yuntai.upp.model.vo.access.dilatory.DilatoryVo>
     * @author jinren@hsyuntai.com
     * @date 2019-06-14 15:20
     */
    @Override
    protected List<StatementVo> data(@NonNull StatementDto dto) {
        List<StatementVo> vos = new ArrayList<>();

        /* 模拟数据,仅供无 HIS 接口时,工程测试使用 - start */
        for (int i = 0; i < 100; i ++) {
            /*
             * 以下各值请务必按规则|枚举进行赋值(上层有规则校验限制)
             */
            StatementVo vo = new StatementVo();
            // 商户标识
            vo.setPartner(ConstantInstance.PARTNER);
            // 账单日期
            vo.setDate(LocalDateTime.now());
            // pay:支付 refund:退款
            vo.setType(Math.random() > 0.3D ? TradeType.PAY.getCode() : TradeType.REFUND.getCode());
            // 注意!!! 金额数字没有负数,取绝对值
            vo.setTrade(new BigDecimal(Math.random() * 100).abs().setScale(2, BigDecimal.ROUND_HALF_UP));
            // 交易时间
            vo.setTime(LocalDateTime.now());

            // 芸泰流水
            vo.setOutSerialNo((TradeType.REFUND.equals(EnumUtil.getEnumByCode(vo.getType(), TradeType.class)) && Math.random() > 0.3D)
                    ? "YT_dad3d9ece5s145n25skr" : UUID.randomUUID().toString().replace("-", ""));
            // 第三方流水
            vo.setInSerialNo(UUID.randomUUID().toString().replace("-", ""));
            // HIS 流水
            vo.setSerialNo(Math.random() > 0.5D ? "028d6fd950ac487dad3d9ece5beb2446" : UUID.randomUUID().toString().replace("-", ""));

            // 业务类型 请参照 BizType 枚举类
            vo.setBussiness(BizType.REGISTER.getCode());
            // 院区标识(存在这赋值,部分商户需要)
            vo.setDistrict(UUID.randomUUID().toString().replace("-", ""));
            // 来源 outpatient:门诊 inpatient:住院
            vo.setSource(SourceType.INPATIENT.getCode());
            // 类型 self:自费 medical:医保
            vo.setModule(CheckBillsPayType.SELF.getCode());
            // 对账类型 改值 @服务端对账开发人员,按照服务端配置赋值
            vo.setStat(CheckBillsStatType.ONE.getCode());
            vos.add(vo);
        }
        /* 模拟数据,仅供无 HIS 接口时,工程测试使用 - end */

        return vos;
    }
}