package com.yuntai.upp.access.tunnel.impl;

import com.yuntai.upp.access.tunnel.DetailTunnel;
import com.yuntai.upp.client.basic.annotation.TraceId;
import com.yuntai.upp.client.basic.enums.inner.InnerCmdType;
import com.yuntai.upp.client.config.strategy.StrategyContext;
import com.yuntai.upp.client.fresh.model.bo.Outcome;
import com.yuntai.upp.client.fresh.model.dto.channel.ChannelDto;
import com.yuntai.upp.client.fresh.model.dto.merchant.MerchantDto;
import com.yuntai.upp.client.fresh.model.vo.merchant.MerchantVo;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.List;

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
        /**
         * 获取数据
         * DTO 类字段详情
         * isv_id       : ConstantInstance.ISV_ID
         * partner_id   : ConstantInstance.PARTNER_ID
         * timestamp    : System.currentTimeMillis()
         * version      : 1.0.0
         * startDate    : 起始日期
         * endDate      : 结束日期
         * sign         : 签名
         */
        /* 策略禁止修改 */
        List<MerchantVo> data = StrategyContext.operate(dto, InnerCmdType.MERCHANT);

        /**
         * 若需要调整参数格式, 这将 data -> 遍历生成所需对象模型
         * VO 类字段详情
         *
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
    @Override
    public Outcome channel(ChannelDto dto) {
        /**
         * 获取数据
         * DTO 类字段详情
         * isv_id       : ConstantInstance.ISV_ID
         * partner_id   : ConstantInstance.PARTNER_ID
         * timestamp    : System.currentTimeMillis()
         * version      : 1.0.0
         * startDate    : 起始日期
         * endDate      : 结束日期
         * sign         : 签名
         */
        /* 策略禁止修改 */
        List<MerchantVo> data = null;
        try {
            data = StrategyContext.operate(dto, InnerCmdType.CHANNEL);
        } catch (Exception exception) {
            return Outcome.fail(exception.getMessage());
        }

        /**
         * 若需要调整参数格式, 这将 data -> 遍历生成所需对象模型
         * VO 类字段详情
         *
         */
        return Outcome.success(data);
    }
}
