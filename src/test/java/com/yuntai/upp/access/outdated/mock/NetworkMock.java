package com.yuntai.upp.access.outdated.mock;
import com.yuntai.upp.client.outdated.model.dto.network.NetworkDto;

import java.time.LocalDateTime;

/**
 * @description 数据模拟-网络链路
 * @className NetworkMock
 * @package com.yuntai.upp.access.outdated.mock
 * @author jinren@hsyuntai.com
 * @date 2019/12/2 16:43
 * @copyright 版权归 HSYUNTAI 所有
 */
public class NetworkMock {

    /**
     * @description 正常场景
     * @param
     * @return java.lang.Object
     * @author jinren@hsyuntai.com
     * @date 2019/12/3 10:37
     */
    public static NetworkDto normal() {
        return NetworkDto.builder()
                .field(LocalDateTime.now())
                .build();
    }
}
