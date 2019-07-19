package com.yuntai.upp.access.helper;

import com.yuntai.upp.access.demo.PresentDataMock;
import com.yuntai.upp.client.basic.model.dto.present.PresentDto;
import com.yuntai.upp.client.basic.model.vo.present.PresentVo;
import com.yuntai.upp.client.handler.active.present.AbstractPresent;
import lombok.NonNull;
import org.springframework.stereotype.Component;

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

    @Override
    public List<PresentVo> data(@NonNull PresentDto dto) {
        /*
         * 在此对 DTO 中的字段进行详细介绍
         *
         * 字段           - 详细介绍
         *
         * outPaymentNo  - 芸泰流水
         *               非必填(outPaymentNo | inPaymentNo | payemntNo 3 流水要素必有一项非空)
         *
         * inPaymentNo   - 商户流水
         *               非必填(outPaymentNo | inPaymentNo | payemntNo 3 流水要素必有一项非空)
         *
         * payemntNo     - HIS流水
         *               非必填(outPaymentNo | inPaymentNo | payemntNo 3 流水要素必有一项非空)
         *
         * outPaymentNo & inPaymentNo & payemntNo 字段进行进一步具体例子解释
         *
         * outPaymentNo      : 芸泰云端服务针对某笔订单的唯一流水标识
         * inPaymentNo       : 商户(如支付宝、微信等)对某笔订单的唯一流水标识
         * payemntNo         : HIS 内部服务队某笔订单的唯一流水标识
         */
        return PresentDataMock.mock();
    }
}
