package com.yuntai.upp.access.tunnel.impl;

import com.yuntai.upp.access.tunnel.DetailTunnel;
import com.yuntai.upp.client.basic.annotation.TraceId;
import com.yuntai.upp.client.basic.enums.inner.InnerCmdType;
import com.yuntai.upp.client.config.strategy.StrategyContext;
import com.yuntai.upp.client.fresh.model.bo.Outcome;
import com.yuntai.upp.client.fresh.model.dto.channel.ChannelDto;
import com.yuntai.upp.client.fresh.model.dto.merchant.MerchantDto;
import com.yuntai.upp.client.fresh.model.vo.channel.ChannelVo;
import com.yuntai.upp.client.fresh.model.vo.merchant.MerchantVo;
import org.springframework.stereotype.Service;

import javax.jws.WebService;

/**
 * @description 账单下载
 * @className DetailService
 * @package com.yuntai.upp.access.tunnel.impl
 * @author jinren@hsyuntai.com
 * @date 2019/10/23 18:00
 * @copyright 版权归 HSYUNTAI 所有
 */
@Service("detailTunnel")
@WebService(endpointInterface = "com.yuntai.upp.access.tunnel.DetailTunnel")
public class DetailService implements DetailTunnel {

    /**
     * @description 注意:
     * 若为老接口兼容, 请注解
     *      @TraceId(gateway = TraceId.GatewayType.OUTDATED_WEB_SREVICE)
     * 若为新接口集成, 请注解
     *      @TraceId(gateway = TraceId.GatewayType.FRESH_WEB_SERVICE)
     * @param dto 请求报文
     * @return com.yuntai.upp.client.fresh.model.bo.Outcome
     * @author jinren@hsyuntai.com
     * @date 2019/10/21 19:09
     */
    @TraceId(gateway = TraceId.GatewayType.OUTDATED_WEB_SREVICE)
    @Override
    public Outcome merchant(MerchantDto dto) {
        /*
         * 获取数据
         * DTO 类字段详情
         * isv_id           ConstantInstance.ISV_ID
         * partner_id       ConstantInstance.PARTNER_ID
         * timestamp        System.currentTimeMillis()
         * version          1.0.0
         * startDate        起始日期
         * endDate          结束日期
         * sign             签名
         */
        /* 策略禁止修改 */
        MerchantVo data = null;
        try {
            data = StrategyContext.operate(dto, InnerCmdType.MERCHANT);
        } catch (Exception exception) {
            return Outcome.fail(exception.getMessage());
        }

        /*
         * 若需要调整参数格式, 这将 data -> 遍历生成所需对象模型
         * VO 类字段详情
         * partner_id	    必填    商户标识
         * isv_id	        必填    ISV 标识
         * trade_type	    必填    交易类型
         * biz_type	        必填    业务类型
         * channel_type	    必填    交易渠道
         * channel_product  必填    交易所使用的渠道产品
         * bills_date	    必填    账单日
         * derate_fee	    必填    减免金额
         * trade_fee	    必填    实际交易金额
         * trade_time	    必填    实际交易时间
         * out_trade_no	    必填    芸泰交易流水
         * in_trade_no	    可选    渠道交易流水
         * trade_no	        可选    HIS交易流水
         * balance_no	    可选    冲正流水
         * district_id	    可选    院区标识
         * source_type	    可选    来源标识
         */
        return Outcome.success(data);
    }

    /**
     * 若为老接口兼容, 请注解
     *      @TraceId(gateway = TraceId.GatewayType.OUTDATED_WEB_SREVICE)
     * 若为新接口集成, 请注解
     *      @TraceId(gateway = TraceId.GatewayType.FRESH_WEB_SERVICE)
     * @param dto 请求报文
     * @return com.yuntai.upp.client.fresh.model.bo.Outcome
     * @author jinren@hsyuntai.com
     * @date 2019/10/21 19:49
     */
    @TraceId(gateway = TraceId.GatewayType.OUTDATED_WEB_SREVICE)
    @Override
    public Outcome channel(ChannelDto dto) {
        /*
         * 获取数据
         * DTO 类字段详情
         * isv_id           ConstantInstance.ISV_ID
         * partner_id       ConstantInstance.PARTNER_ID
         * timestamp        System.currentTimeMillis()
         * version          1.0.0
         * startDate        起始日期
         * endDate          结束日期
         * sign             签名
         */
        /* 策略禁止修改 */
        ChannelVo data = null;
        try {
            data = StrategyContext.operate(dto, InnerCmdType.CHANNEL);
        } catch (Exception exception) {
            return Outcome.fail(exception.getMessage());
        }

        /*
         * 若需要调整参数格式, 这将 data -> 遍历生成所需对象模型
         * VO 类字段详情
         * partner_id	    必填    商户标识
         * isv_id	        必填    ISV 标识
         * trade_type	    必填    交易类型
         * biz_type	        必填    业务类型
         * channel_type	    必填    交易渠道
         * channel_product	必填    交易所使用的渠道产品
         * bills_date	    必填    账单日
         * trade_fee	    必填    实际交易金额
         * trade_time	    必填    实际交易时间
         * out_trade_no	    必填    芸泰交易流水
         * in_trade_no	    可选    渠道交易流水
         * trade_no	        可选    HIS交易流水
         * balance_no	    可选    冲正流水
         */
        return Outcome.success(data);
    }
}
