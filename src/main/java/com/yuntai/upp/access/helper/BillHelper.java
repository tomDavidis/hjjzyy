package com.yuntai.upp.access.helper;

import com.yuntai.upp.access.demo.BillDataMock;
import com.yuntai.upp.client.handler.active.bill.serivce.impl.AbstractBill;
import com.yuntai.upp.client.basic.model.dto.bill.BillDto;
import com.yuntai.upp.client.basic.model.vo.bill.BillVo;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description 账单下载-真实实现(禁止修改 bean 名)
 * @className BillHelper
 * @package com.yuntai.upp.access.helper
 * @author jinren@hsyuntai.com
 * @date 2019-06-14 15:20
 * @copyright 版权归 HSYUNTAI 所有
 */
@Component("billService")
public class BillHelper extends AbstractBill {

    /**
     * @description 账单数据转换
     *              调用 HIS 接口获取账单包装成为 List<DilatoryVo>[非空]
     *              若接口无任何返回数据或调用结果出错,则直接抛出异常
     * @param dto 云端下载 bean
     * @return java.util.List<com.yuntai.upp.client.basic.model.vo.dilatory.DilatoryVo>
     * @author jinren@hsyuntai.com
     * @date 2019-06-14 15:20
     */
    @Override
    protected List<BillVo> data(@NonNull BillDto dto) {
        /* 在此对 DTO 中的字段进行详细介绍
         *
         * 字段                      详细介绍
         *
         * partnerId                - 存放的为商户标识, 数据类型为 Long
         *
         * startTime                - 需要导出账单数据的起始时间, 数据类型为 LocalTimeDate[可使用DateUtil.formateDate()方法进行格式化]
         *                          非空必填(上层已校验)
         *                          格式为yyyy-MM-dd HH:mm:ss, 无需要校验格式正确性(上层已校验)
         *
         * endTime                  - 需要导出账单数据的终止时间, 数据类型为 LocalTimeDate[可使用DateUtil.formateDate()方法进行格式化]
         *                          非空必填(上层已校验)
         *                          格式为yyyy-MM-dd HH:mm:ss, 无需要校验格式正确性(上层已校验)
         *
         * [checkBillsDownloadType] - 账单下载类型()大多情况下,实时交易&延时账单均调用 HIS 相同视图
         *                          若存在 HIS 实时交易与延时账单调用不同的视图、逻辑时,可通过 dto 中的 tradeType 属性进行分类判断
         *                              prompt : 实时
         *                              delay : 延时
         */
        return BillDataMock.mock();
    }
}