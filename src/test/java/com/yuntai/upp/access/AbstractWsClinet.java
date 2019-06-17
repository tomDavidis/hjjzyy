package com.yuntai.upp.access;

/**
 * @description 单元测试抽象类
 * @className AbstractWsClinet
 * @package com.yuntai.upp.access
 * @author jinren@hsyuntai.com
 * @date 2019-06-14 13:38
 * @copyright 版权归 HSYUNTAI 所有
 */

public abstract class AbstractWsClinet {

    protected static final String PREFIX = "<![CDATA[";
    protected static final String SUFFIX = "]]>";

    protected static final String TEMPLATE =
            "<soapenv:Envelope xmlns:ser=\"http://service.webservice.openservice.yuntai.com/\" \n" +
            "\txmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
            "\t<soapenv:Header/>\n" +
            "\t<soapenv:Body>\n" +
            "{0}" +
            "\t</soapenv:Body>\n" +
            "</soapenv:Envelope>";
}
