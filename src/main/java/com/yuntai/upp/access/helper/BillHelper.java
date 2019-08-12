package com.yuntai.upp.access.helper;

import com.yuntai.upp.access.demo.BillDataMock;
import com.yuntai.upp.client.fresh.handler.active.bill.AbstractBill;
import com.yuntai.upp.client.fresh.model.bo.ViewBo;
import com.yuntai.upp.client.fresh.model.dto.bill.BillDto;
import com.yuntai.upp.client.fresh.model.vo.bill.BillVo;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
     *              调用 HIS 接口获取账单包装成为 List<BillVo>[可为空]
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
     * @return com.yuntai.upp.client.fresh.model.bo.ViewBo<java.util.List<com.yuntai.upp.client.fresh.model.vo.bill.BillVo>>
     * @author jinren@hsyuntai.com
     * @date 2019-08-12 09:05
     */
    @Override
    public ViewBo<List<BillVo>> data(@NonNull BillDto dto) {
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
         *                          若存在 HIS 实时交易与延时账单调用不同的视图、逻辑时,可通过 dto 中的 checkBillsDownloadType 属性进行分类判断
         *                              prompt : 实时
         *                              delay : 延时
         */
        List<BillVo> list = new ArrayList<>(2048);
        try {
            list.addAll(BillDataMock.mock());
        } catch (Exception exception) {
            return ViewBo.error(exception.getMessage());
        }
        return ViewBo.success(list);
    }
}