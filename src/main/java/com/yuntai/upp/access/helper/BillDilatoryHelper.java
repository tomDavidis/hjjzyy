package com.yuntai.upp.access.helper;

import com.yuntai.upp.client.handler.active.operate.inherit.AbstractBillDilatory;
import com.yuntai.upp.model.dto.access.dilatory.DilatoryDto;
import com.yuntai.upp.model.vo.access.dilatory.DilatoryVo;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @description 账单数据工具类
 * @className BillDilatoryHelper
 * @package com.yuntai.upp.access.helper
 * @author jinren@hsyuntai.com
 * @date 2019-06-14 15:20
 * @copyright 版权归 HSYUNTAI 所有
 */

@Component
public class BillDilatoryHelper extends AbstractBillDilatory {

    /**
     * @description 账单数据转换
     *              调用 HIS 接口获取账单包装成为 List<DilatoryVo>[非空]
     *              若接口无任何返回数据或调用结果出错,则直接抛出异常
     * @param dto 云端下载 bean
     * @return java.util.List<com.yuntai.upp.model.vo.access.dilatory.DilatoryVo>
     * @author jinren@hsyuntai.com
     * @date 2019-06-14 15:20
     */
    @Override
    protected List<DilatoryVo> data(@NonNull DilatoryDto dto) {
        List<DilatoryVo> vos = new ArrayList<>();

        /* 模拟数据,仅供无 HIS 接口时,工程测试使用 - start */
        for (int i = 0; i < 100; i++) {
            vos.add(new DilatoryVo());
        }
        /* 模拟数据,仅供无 HIS 接口时,工程测试使用 - end */

        return vos;
    }
}