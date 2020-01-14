package com.yuntai.upp.access.helper;

import com.yuntai.upp.access.helper.demo.MedicalMock;
import com.yuntai.upp.client.fresh.handler.downstream.medical.AbstractMedical;
import com.yuntai.upp.client.fresh.model.bo.Outcome;
import com.yuntai.upp.client.fresh.model.dto.medical.MedicalDto;
import com.yuntai.upp.client.fresh.model.vo.medical.MedicalVo;
import lombok.NonNull;
import org.springframework.stereotype.Component;

/**
 * @description 基本信息-真实实现
 * @className MedicalHelper
 * @package com.yuntai.upp.access.helper
 * @author jinren@hsyuntai.com
 * @date 2019/12/31 15:14
 * @copyright 版权归 HSYUNTAI 所有
 */
@Component("medicalService")
public class MedicalHelper extends AbstractMedical {

    /**
     * @description 交易数据转换
     * @param dto 云端下载 bean
     * @return com.yuntai.upp.client.fresh.model.bo.Outcome<com.yuntai.upp.client.fresh.model.vo.medical.MedicalVo>
     * @author jinren@hsyuntai.com
     * @date 2019/12/31 15:15
     */
    @Override
    public Outcome<MedicalVo> data(@NonNull MedicalDto dto) {
        /*
         * 在此对 DTO 中的字段进行详细介绍
         *
         * 字段           - 详细介绍
         *
         * partnerId     - 商户标识(必填)
         *
         * isvId         - ISV 标识(必填)
         *
         * billsDate     - 账单日期, 数据类型为 LocalDate [可使用DateUtil.formateDate()方法进行格式化]
         *               非空必填(上层已校验)
         *               格式为 yyyy-MM-dd, 无需要校验格式正确性(上层已校验)
         */
        try {
            return Outcome.success(MedicalMock.mock());
        } catch (Exception exception) {
            return Outcome.fail(exception.getMessage());
        }
    }
}
