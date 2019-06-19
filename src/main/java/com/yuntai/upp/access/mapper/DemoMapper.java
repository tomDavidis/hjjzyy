package com.yuntai.upp.access.mapper;

import org.springframework.stereotype.Repository;

/**
 * @description Mapper 样例
 * @className DemoMapper
 * @package com.yuntai.upp.access.mapper
 * @author jinren@hsyuntai.com
 * @date 2019-06-19 09:21
 * @copyright 版权归 HSYUNTAI 所有
 */

@Repository
public interface DemoMapper {

    /**
     * @description 测试数据库连接是否正常
     * @param
     * @return java.lang.String
     * @author jinren@hsyuntai.com
     * @date 2019-06-19 09:26
     */
    String ping();
}
