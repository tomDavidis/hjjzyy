package com.yuntai.upp.access.helper;

import com.yuntai.upp.access.helper.demo.PresentMock;
import com.yuntai.upp.client.fresh.handler.downstream.present.AbstractPresent;
import com.yuntai.upp.client.fresh.model.bo.Outcome;
import com.yuntai.upp.client.fresh.model.dto.present.PresentDto;
import com.yuntai.upp.client.fresh.model.vo.present.PresentVo;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @description 实时交易-真实实现
 * @className PresentHelper
 * @package com.yuntai.upp.access.helper
 * @author jinren@hsyuntai.com
 * @date 2019-06-21 15:32
 * @copyright 版权归 HSYUNTAI 所有
 */
@Component("presentService")
public class PresentHelper extends AbstractPresent {

    /**
     * @description 交易数据转换
     *              调用 HIS 接口获取账单包装成为 List<PresentVo> [可为空]
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
     * @param dto 云端下载 bean
     * @return com.yuntai.upp.client.fresh.model.bo.ViewBo<java.util.List<com.yuntai.upp.client.fresh.model.vo.present.PresentVo>>
     * @author jinren@hsyuntai.com
     * @date 2019-08-12 09:06
     */
    @Override
    public Outcome<List<PresentVo>> data(@NonNull PresentDto dto) {
        /*
         * 在此对 DTO 中的字段进行详细介绍
         *
         * 字段           - 详细介绍
         *
         * partnerId     - 商户标识(必填)
         *
         * outPaymentNo  - 芸泰流水(必填)
         *
         * inPaymentNo   - 商户流水(可空)
         *
         * payemntNo     - HIS流水(必填)
         *
         * gmtCreate     - 交易创建时间, 数据类型为 LocalDate [可使用DateUtil.formateDate()方法进行格式化]
         *               非空必填(上层已校验)
         *               格式为 yyyy-MM-dd HH:mm:ss, 无需要校验格式正确性(上层已校验)
         *
         * outPaymentNo & inPaymentNo & payemntNo 字段进行进一步具体例子解释
         *
         * outPaymentNo      : 芸泰云端服务针对某笔订单的唯一流水标识
         * inPaymentNo       : 商户(如支付宝、微信等)对某笔订单的唯一流水标识
         * payemntNo         : HIS 内部服务队某笔订单的唯一流水标识
         */
        List<PresentVo> list = new ArrayList<>(8);
        try {
            list.addAll(PresentMock.mock());
        } catch (Exception exception) {
            return Outcome.fail(exception.getMessage());
        }
        return Outcome.success(list);
    }
}