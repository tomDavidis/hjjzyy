package com.yuntai.upp.access;

import com.yuntai.upp.client.basic.util.TraceIdUtil;
import org.junit.After;
import org.junit.Before;

/**
 * @description 单元测试抽象类
 * @className AbstractClient
 * @package com.yuntai.upp.access.ws
 * @author jinren@hsyuntai.com
 * @date 2019-06-13 17:40
 * @copyright 版权归 HSYUNTAI 所有
 */

public abstract class AbstractRestapiClient {

    protected static final String TEMPLATE =
            "BizCode={0}&RequestData={1}";

    protected  static final String REQUEST =
            "<Request>\n" +
            "    <Header>\n" +
            "        <RequestSn>{0}</RequestSn>\n" +
            "        <BizName>{1}</BizName>\n" +
            "        <SecurityCode>{2}</SecurityCode>\n" +
            "    </Header>\n" +
            "    {3}\n" +
            "</Request>\n";


    @Before
    public void before() {
        TraceIdUtil.createLocalTraceId();
    }

    @After
    public void after() {
        TraceIdUtil.clearLocalTraceId();
    }
}
