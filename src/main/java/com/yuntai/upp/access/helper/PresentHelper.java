package com.yuntai.upp.access.helper;

import com.yuntai.upp.access.demo.PresentDataMock;
import com.yuntai.upp.client.handler.active.present.AbstractPresent;
import com.yuntai.upp.client.basic.model.dto.present.PresentDto;
import com.yuntai.upp.client.basic.model.vo.present.PresentVo;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description 实时账单-真实实现(禁止修改 bean 名)
 * @className PresentHelper
 * @package com.yuntai.upp.access.helper
 * @author jinren@hsyuntai.com
 * @date 2019-06-21 15:32
 * @copyright 版权归 HSYUNTAI 所有
 */

@Component("presentHandler")
public class PresentHelper extends AbstractPresent {


    @Override
    protected List<PresentVo> data(@NonNull PresentDto dto) {
        /* 在此对 DTO 中的字段进行详细介绍
         *
         * 字段           - 详细介绍
         *
         * tradeType     - 交易类型, 数据类型为 String
         *               非空必填(上层已校验)
         *               有效枚举已校验(上层已校验), 可通过Enumable.getEnumByCode(dto.getTradeType(), TradeType.class)直接过去枚举类型
         *
         * outSerialNo   - 芸泰流水
         *               非必填(outSerialNo | inSerialNo | serialNo 3 流水要素必有一项非空)
         *
         * inSerialNo    - 商户流水
         *               非必填(outSerialNo | inSerialNo | serialNo 3 流水要素必有一项非空)
         *
         * serialNo      - HIS流水
         *               非必填(outSerialNo | inSerialNo | serialNo 3 流水要素必有一项非空)
         *
         * 针对outSerialNo & inSerialNo & serialNo 字段进行进一步具体例子解释
         *
         * outSerialNo      : 芸泰云端服务针对某笔订单的唯一流水标识
         * inSerialNo       : 商户(如支付宝、微信等)对某笔订单的唯一流水标识
         * serialNo         : HIS 内部服务队某笔订单的唯一流水标识
         *
         * 注意!!! 正交易与负交易的流水不一定相同的
         * 举例: 芸泰的正交易流水(YT 开头)与负交易流水(退款批次号[YTR 开头])
         */
        return PresentDataMock.mock();
    }
}
