package com.yuntai.upp.access;

import com.yuntai.upp.client.basic.util.TraceIdUtil;
import com.yuntai.upp.client.config.hdp.HdpClientInstance;
import org.junit.After;
import org.junit.Before;
import org.powermock.api.mockito.PowerMockito;

/**
 * @description 顶级抽象方法
 * @className AbstractBaisc
 * @package com.yuntai.upp.access
 * @author jinren@hsyuntai.com
 * @date 2019/11/22 18:14
 * @copyright 版权归 HSYUNTAI 所有
 */
public abstract class AbstractBaisc {

    @Before
    public void before() {
        /*
         * 静态方法主动模拟
         */
        PowerMockito.mockStatic(HdpClientInstance.class);
        TraceIdUtil.createLocalTraceId(this.getClass().getName());
    }

    @After
    public void after() {
        TraceIdUtil.clearLocalTraceId();
    }
}
