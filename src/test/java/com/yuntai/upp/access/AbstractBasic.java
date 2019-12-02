package com.yuntai.upp.access;

import com.yuntai.upp.access.fresh.mock.ProductMock;
import com.yuntai.upp.client.basic.enums.inner.InnerCmdType;
import com.yuntai.upp.client.basic.util.TraceIdUtil;
import com.yuntai.upp.client.config.hdp.HdpClientInstance;
import com.yuntai.upp.client.config.strategy.StrategyContext;
import com.yuntai.upp.sdk.param.ChannelParam;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.After;
import org.junit.Before;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

/**
 * @description 顶级抽象方法
 * @className AbstractBasic
 * @package com.yuntai.upp.access
 * @author jinren@hsyuntai.com
 * @date 2019/11/22 18:14
 * @copyright 版权归 HSYUNTAI 所有
 */
public abstract class AbstractBasic {

    @Before
    public void before() {
        TraceIdUtil.createLocalTraceId(this.getClass().getName());
        PowerMockito.mockStatic(HdpClientInstance.class);
        PowerMockito.when(HdpClientInstance.send(Mockito.any(InnerCmdType.class), Mockito.any(ChannelParam.class), Mockito.any()))
                .thenReturn(ProductMock.mock());
        /*
         * 模拟支付产品配置并刷新缓存
         * 若运行 testNormal 方法, 则主动注释掉该部分模拟代码
         */
        StrategyContext.operate(ObjectUtils.NULL, InnerCmdType.PRODUCT);
    }

    @After
    public void after() {
        TraceIdUtil.clearLocalTraceId();
    }
}
