package com.yuntai.upp.access.fresh.access.present;

import com.yuntai.upp.client.basic.util.UUIDUtil;
import com.yuntai.upp.client.fresh.model.dto.present.PresentDto;
import com.yuntai.upp.sdk.interfaces.Signable;

import java.time.LocalDateTime;

/**
 * @description 数据模拟
 * @className PresentMock
 * @package com.yuntai.upp.access.fresh.access.present
 * @author jinren@hsyuntai.com
 * @date 2019/11/8 17:30
 * @copyright 版权归 HSYUNTAI 所有
 */
public class PresentMock {

    /**
     * @description 正常场景-数据
     * @param
     * @return com.yuntai.upp.client.fresh.model.dto.present.PresentDto
     * @author jinren@hsyuntai.com
     * @date 2019/11/8 17:37
     */
    protected static PresentDto normal() {
        return PresentDto.builder()
                .timestamp(System.currentTimeMillis())
                .version(Signable.VERSION)
                .isvId(20000L)
                .partnerId(999L)
                .outPaymentNo(UUIDUtil.create())
                .paymentNo(UUIDUtil.create())
                .gmtCreate(LocalDateTime.now().minusDays(1))
                .build();
    }
}
