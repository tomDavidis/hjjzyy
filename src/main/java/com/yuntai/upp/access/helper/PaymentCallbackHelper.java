package com.yuntai.upp.access.helper;

import com.yuntai.upp.access.helper.demo.PaymentCallbackMock;
import com.yuntai.upp.client.fresh.handler.active.paymentcallback.AbstractPaymentCallback;
import com.yuntai.upp.client.fresh.model.bo.Outcome;
import com.yuntai.upp.client.fresh.model.dto.paymentcallback.PaymentCallbackDto;
import com.yuntai.upp.client.fresh.model.vo.paymentcallback.PaymentCallbackVo;
import lombok.NonNull;
import org.springframework.stereotype.Component;

/**
 * @description: 交易通知-真实实现(禁止修改 bean 名)
 * @className: CallbackHelper
 * @package: com.yuntai.upp.access.helper
 * @author: jinren@hsyuntai.com
 * @date: 2019-09-06 10:12
 * @copyright: 版权归 HSYUNTAI 所有
 */
@Component("paymentCallbackService")
public class PaymentCallbackHelper extends AbstractPaymentCallback {

    /**
     * @description 交易数据转换
     *              调用 HIS 接口获取账单包装成为 CallbackVo [可为空]
     *
     *              真实场景中大多存在以下 2 种方式获取数据
     *              1.使用视图获取数据
     *                  工程可执行按照需求配置启用|禁用 DB, 仅需要把对应数据源配置文件进行修改即可
     *              2.通过 TCP 接口进行访问
     *                  工程工具包内提供HttpUtil工具供网络访问,
     *                      实例:
     *                          HttpUtil.post(HttpUtil.Atom.builder()
     *                              .url("访问地址")
     *                               // 请求数据类型
     *                              .content(HttpUtil.CONTENT_TEXT)
     *                               // 响应数据类型
     *                              .accept(HttpUtil.ACCEPT_JSON)
     *                               // 数据对象
     *                              .data(new Object());
     * @param dto 报文实体
     * @return com.yuntai.upp.client.fresh.model.bo.Outcome<com.yuntai.upp.client.fresh.model.vo.paymentcallback.PaymentCallbackVo>
     * @author jinren@hsyuntai.com
     * @date 2019-09-06 10:54
     */
    @Override
    protected Outcome<PaymentCallbackVo> data(@NonNull PaymentCallbackDto dto) {
        try {
            /* 在此对 DTO 中的字段进行详细介绍
             *
             * 字段                      详细介绍
             *
             * partnerId                - 商户标识
             *                          非空必填(上层已校验)
             *
             * paymentNo                - HIS 流水
             *                          非空必填(上层已校验)
             *
             * bizType                  - 业务类型
             *                          非空必填(上层已校验)
             *
             * bizId                    - 业务唯一标识
             *                          非空必填(上层已校验)
             *
             * channelType              - 支付渠道
             *                          非空必填(上层已校验)
             *
             * channelProduct           - 渠道产品
             *                          非空必填(上层已校验)
             *
             * tradeFee                 - 实付订单金额
             *                          非空必填(上层已校验)
             *
             * tradeStatus              - 交易状态
             *                          非空必填(上层已校验)
             *
             * outPaymentNo             - 芸泰流水号
             *                          非空必填(上层已校验)
             *
             * inPaymentNo              - 渠道正交易流水
             *                          非空必填(上层已校验)
             *
             * paymentTime              - 支付时间
             *                          非空必填(上层已校验)
             *                          格式为yyyy-MM-dd HH:mm:ss, 无需要校验格式正确性(上层已校验)
             *
             * bizData                  - 业务数据
             *                          可空
             */
            return Outcome.success(PaymentCallbackMock.mock() ? PaymentCallbackVo.success() : PaymentCallbackVo.fail());
        } catch (Exception exception) {
            return Outcome.fail(exception.getMessage());
        }
    }
}
