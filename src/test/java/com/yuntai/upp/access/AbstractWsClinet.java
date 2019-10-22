package com.yuntai.upp.access;

import com.yuntai.upp.client.basic.util.TraceIdUtil;
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

    protected static final String URL_WS_SERVICE = "http://127.0.0.1:7000/hs-access-facepay/services/facePayWebService";

    protected static final String URL_WS_DETAIL = "http://127.0.0.1:7000/services/detail";

    protected static final String TEMPLATE_NETWORK =
            "<soapenv:Envelope xmlns:ser=\"http://service.webservice.openservice.yuntai.com/\" \n" +
            "  xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
            "  <soapenv:Header/>\n" +
            "  <soapenv:Body>\n" +
            "    <ns2:wsNetTest xmlns:ns2=\"http://service.webservice.openservice.yuntai.com/\">\n" +
            "       {0}" +
            "    </ns2:wsNetTest>\n" +
            "   </soapenv:Body>\n" +
            "</soapenv:Envelope>";

    protected static final String TEMPLATE_MERCHANT =
            "<soapenv:Envelope xmlns:ser=\"http://service.webservice.openservice.yuntai.com/\" \n" +
                    "  xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                    "  <soapenv:Header/>\n" +
                    "  <soapenv:Body>\n" +
                    "    <ns2:merchant xmlns:ns2=\"http://service.webservice.openservice.yuntai.com/\">\n" +
                    "       {0}" +
                    "    </ns2:merchant>\n" +
                    "   </soapenv:Body>\n" +
                    "</soapenv:Envelope>";

    protected static final String TEMPLATE_CHANNEL =
            "<soapenv:Envelope xmlns:ser=\"http://service.webservice.openservice.yuntai.com/\" \n" +
                    "  xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                    "  <soapenv:Header/>\n" +
                    "  <soapenv:Body>\n" +
                    "    <ns2:channel xmlns:ns2=\"http://service.webservice.openservice.yuntai.com/\">\n" +
                    "       {0}" +
                    "    </ns2:channel>\n" +
                    "   </soapenv:Body>\n" +
                    "</soapenv:Envelope>";

    protected static final String TEMPLATE_SERVICE =
            "<soapenv:Envelope xmlns:ser=\"http://service.webservice.openservice.yuntai.com/\" \n" +
                    "  xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                    "  <soapenv:Header/>\n" +
                    "  <soapenv:Body>\n" +
                    "    <ns2:wsFacePayService xmlns:ns2=\"http://service.webservice.openservice.yuntai.com/\">\n" +
                    "       {0}" +
                    "    </ns2:wsFacePayService>\n" +
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
        TraceIdUtil.createLocalTraceId(this.getClass().getName());
    }

    @After
    public void after() {
        TraceIdUtil.clearLocalTraceId();
    }

}
