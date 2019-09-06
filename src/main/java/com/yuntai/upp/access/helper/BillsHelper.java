package com.yuntai.upp.access.helper;

import com.yuntai.upp.access.demo.BillDataMock;
import com.yuntai.upp.client.fresh.handler.active.bills.AbstractBills;
import com.yuntai.upp.client.fresh.model.bo.Outcome;
import com.yuntai.upp.client.fresh.model.dto.bills.BillsDto;
import com.yuntai.upp.client.fresh.model.vo.bills.BillsVo;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @description 账单下载-真实实现(禁止修改 bean 名)
 * @className BillsHelper
 * @package com.yuntai.upp.access.helper
 * @author jinren@hsyuntai.com
 * @date 2019-06-14 15:20
 * @copyright 版权归 HSYUNTAI 所有
 */
@Component("billsService")
public class BillsHelper extends AbstractBills {

    /**
     * @description 账单数据转换
     *              调用 HIS 接口获取账单包装成为 List<BillVo> [可为空]
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
     * @return com.yuntai.upp.client.fresh.model.bo.ViewBo<java.util.List<com.yuntai.upp.client.fresh.model.vo.bills.BillVo>>
     * @author jinren@hsyuntai.com
     * @date 2019-08-12 09:05
     */
    @Override
    public Outcome<List<BillsVo>> data(@NonNull BillsDto dto) {
        /* 在此对 DTO 中的字段进行详细介绍
         *
         * 字段                      详细介绍
         *
         * partnerId                - 存放的为商户标识, 数据类型为 Long
         *                          非空必填(上层已校验)
         *
         * startTime                - 需要导出账单数据的起始时间, 数据类型为 LocalTimeDate[可使用DateUtil.formateDate()方法进行格式化]
         *                          非空必填(上层已校验)
         *                          格式为yyyy-MM-dd HH:mm:ss, 无需要校验格式正确性(上层已校验)
         *
         * endTime                  - 需要导出账单数据的终止时间, 数据类型为 LocalTimeDate[可使用DateUtil.formateDate()方法进行格式化]
         *                          非空必填(上层已校验)
         *                          格式为yyyy-MM-dd HH:mm:ss, 无需要校验格式正确性(上层已校验)
         */
        List<BillsVo> list = new ArrayList<>(2048);
        try {
            list.addAll(BillDataMock.mock());
        } catch (Exception exception) {
            return Outcome.fail(exception.getMessage());
        }
        return Outcome.success(list);
    }
}