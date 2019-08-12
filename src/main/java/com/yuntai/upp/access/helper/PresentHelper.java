package com.yuntai.upp.access.helper;

import com.yuntai.upp.access.demo.PresentDataMock;
import com.yuntai.upp.client.fresh.handler.active.present.AbstractPresent;
import com.yuntai.upp.client.fresh.model.bo.ViewBo;
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
     *              调用 HIS 接口获取账单包装成为 List<PresentVo>[可为空]
     * @param dto 云端下载 bean
     * @return com.yuntai.upp.client.fresh.model.bo.ViewBo<java.util.List<com.yuntai.upp.client.fresh.model.vo.present.PresentVo>>
     * @author jinren@hsyuntai.com
     * @date 2019-08-12 09:06
     */
    @Override
    public ViewBo<List<PresentVo>> data(@NonNull PresentDto dto) {
        /*
         * 在此对 DTO 中的字段进行详细介绍
         *
         * 字段           - 详细介绍
         *
         * outPaymentNo  - 芸泰流水(必填)
         *
         * inPaymentNo   - 商户流水(可空)
         *
         * payemntNo     - HIS流水(可空)
         *
         * outPaymentNo & inPaymentNo & payemntNo 字段进行进一步具体例子解释
         *
         * outPaymentNo      : 芸泰云端服务针对某笔订单的唯一流水标识
         * inPaymentNo       : 商户(如支付宝、微信等)对某笔订单的唯一流水标识
         * payemntNo         : HIS 内部服务队某笔订单的唯一流水标识
         */
        List<PresentVo> list = new ArrayList<>(8);
        try {
            list.addAll(PresentDataMock.mock());
        } catch (Exception exception) {
            return ViewBo.error(exception.getMessage());
        }
        return ViewBo.success(list);
    }
}