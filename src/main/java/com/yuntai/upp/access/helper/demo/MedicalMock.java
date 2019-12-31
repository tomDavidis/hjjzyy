package com.yuntai.upp.access.helper.demo;

import com.yuntai.upp.client.basic.util.UUIDUtil;
import com.yuntai.upp.client.fresh.model.vo.medical.MedicalVo;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * @description 基本信息数据模拟
 * @className MedicalMock
 * @package com.yuntai.upp.access.helper.demo
 * @author jinren@hsyuntai.com
 * @date 2019/12/31 15:24
 * @copyright 版权归 HSYUNTAI 所有
 */
public class MedicalMock {

    /**
     数据模拟(真实场景禁止使用,仅供开发使用)
     * @return com.yuntai.upp.client.fresh.model.vo.medical.MedicalVo
     * @author jinren@hsyuntai.com
     * @date 2019/12/31 15:24
     */
    public static MedicalVo mock() {
        return MedicalVo.builder()
                // 操作员编号
                // [不可为空, 参数返回后必校验]
                .operateId(RandomStringUtils.randomAlphanumeric(32))
                // 业务周期号
                // [不可为空, 参数返回后必校验]
                .businessCycleNo(UUIDUtil.create())
                .build();
    }
}
