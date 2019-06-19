package com.yuntai.upp.access.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.yuntai.upp.access.mapper.DemoMapper;
import com.yuntai.upp.access.service.DemoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description Service 实现样例
 *              当前 demo 值支持多数据源&主从库模式
 *              若使用有特殊要求需要在 service 层上添加注释@DS(value="指定数据源")
 * @className DemoServiceImpl
 * @package com.yuntai.upp.access.service.impl
 * @author jinren@hsyuntai.com
 * @date 2019-06-19 09:22
 * @copyright 版权归 HSYUNTAI 所有
 */

@DS("master")
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoMapper demoMapper;

    /**
     * @description 测试数据库连接是否正常
     * @param
     * @return java.lang.Boolean
     * @author jinren@hsyuntai.com
     * @date 2019-06-19 09:26
     */
    @Override
    public Boolean ping() {
        try {
            return StringUtils.isNotBlank(demoMapper.ping());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return Boolean.FALSE;
    }
}
