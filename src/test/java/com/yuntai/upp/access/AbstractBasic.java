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
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @description 顶级抽象方法
 * @className AbstractBasic
 * @package com.yuntai.upp.access
 * @author jinren@hsyuntai.com
 * @date 2019/11/22 18:14
 * @copyright 版权归 HSYUNTAI 所有
 */
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringRunner.class)
@PowerMockIgnore({"javax.*.*", "com.sun.*", "org.*"})
@PrepareForTest({HdpClientInstance.class})
@ActiveProfiles(value = {"deploy/dev.properties"})
@SpringBootTest(classes = {UppAccessApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractBasic {

    protected MockMvc mvc;

    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void before() {
        // 初始化 MockMvc 对象
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
        TraceIdUtil.createLocalTraceId(this.getClass().getName());
        PowerMockito.mockStatic(HdpClientInstance.class);
        PowerMockito.when(HdpClientInstance.send(Mockito.any(InnerCmdType.class), Mockito.any(ChannelParam.class), Mockito.any()))
                .thenReturn(ProductMock.mock());
        /*
         * 模拟支付产品配置并刷新缓存
         * 若运行 testNormal 方法, 则主动注释掉该部分模拟代码
         */
        StrategyContext.passive(ObjectUtils.NULL, InnerCmdType.PRODUCT);
    }

    @After
    public void after() {
        TraceIdUtil.clearLocalTraceId();
    }
}
