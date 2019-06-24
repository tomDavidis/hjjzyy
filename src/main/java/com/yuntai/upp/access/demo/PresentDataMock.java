package com.yuntai.upp.access.demo;

import com.yuntai.upp.client.basic.model.vo.present.PresentVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @description 实时账单数据模拟
 * @className PresentDataMock
 * @package com.yuntai.upp.access.demo
 * @author jinren@hsyuntai.com
 * @date 2019-06-22 10:16
 * @copyright 版权归 HSYUNTAI 所有
 */

public class PresentDataMock {

    /**
     * @description 数据模拟(真实场景禁止使用,仅供开发使用)
     * @param
     * @return java.util.List<com.yuntai.upp.client.basic.model.vo.present.PresentVo>
     * @author jinren@hsyuntai.com
     * @date 2019-06-22 10:17
     */
    public static List<PresentVo> mock() {
        List<PresentVo> vos = new ArrayList<>();

        /* 模拟数据,仅供无 HIS 接口时,工程测试使用 - start */
        vos.add(PresentVo.builder()
                .build());
        /* 模拟数据,仅供无 HIS 接口时,工程测试使用 - end */

        return vos;
    }
}
