package com.yuntai.upp.access;

import com.yuntai.upp.support.util.TraceIdUtil;
import org.junit.After;
import org.junit.Before;

/**
 * @description 单元测试抽象类
 * @className AbstractWsClinet
 * @package com.yuntai.upp.access
 * @author jinren@hsyuntai.com
 * @date 2019-06-14 13:38
 * @copyright 版权归 HSYUNTAI 所有
 */

public abstract class AbstractWsClinet {

    protected static final String TEMPLATE =
            "<soapenv:Envelope xmlns:ser=\"http://service.webservice.openservice.yuntai.com/\" \n" +
            "   xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
            "   <soapenv:Header/>\n" +
            "   <soapenv:Body>\n" +
            "       {0}" +
            "   </soapenv:Body>\n" +
            "</soapenv:Envelope>";
    protected  static final String REQUEST =
            "<![CDATA[\n" +
            "   <Request>\n" +
            "       <Header>\n" +
            "           <RequestSn>{0}</RequestSn>\n" +
            "           <BizName>{1}</BizName>\n" +
            "           <SecurityCode>{2}</SecurityCode>\n" +
            "       </Header>\n" +
            "       {3}\n" +
            "   </Request>\n" +
            "]]>\n";


    @Before
    public void before() {
        TraceIdUtil.createLocalTraceId();
    }

    @After
    public void after() {
        TraceIdUtil.clearLocalTraceId();
    }

}
