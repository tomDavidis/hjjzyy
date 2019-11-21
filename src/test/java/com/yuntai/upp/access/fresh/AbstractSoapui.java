package com.yuntai.upp.access.fresh;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.yuntai.upp.access.util.XmlUtil;
import com.yuntai.upp.client.basic.util.HttpUtil;
import com.yuntai.upp.client.basic.util.JaxbUtil;
import com.yuntai.upp.client.basic.util.TraceIdUtil;
import com.yuntai.upp.client.fresh.model.bo.Outcome;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;

import java.text.MessageFormat;

import static com.yuntai.upp.client.config.constant.ConstantInstance.RESPONSE;

@Slf4j
public abstract class AbstractSoapui<I, O> {

    protected static final String SUCCESS = "0";
    protected static final String FAIL = "1";

    private static final String TEMPLATE = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">"
            + "<SOAP-ENV:Header/>"
            + "<SOAP-ENV:Body>"
            + "<dto:{0} xmlns:dto=\"" + AbstractSoapui.TARGET_NAME + "\" >{1}</dto:{0}>"
            + "</SOAP-ENV:Body>"
            + "</SOAP-ENV:Envelope>";

    private static final String WSDL_URL = "http://127.0.0.1:7000/services/fresh?wsdl";
    private static final String TARGET_NAME = "http://client.upp.yuntai.com/";

    @Before
    public void before() {
        TraceIdUtil.createLocalTraceId(this.getClass().getName());
    }

    @After
    public void after() {
        TraceIdUtil.clearLocalTraceId();
    }

    protected Outcome<O> send(@NonNull I model,
                     @NonNull String target) {
        String result = HttpUtil.post(HttpUtil.Atom.builder()
                .url(WSDL_URL)
                .content(HttpUtil.CONTENT_XML)
                .accept(HttpUtil.ACCEPT_XML)
                .data(MessageFormat.format(TEMPLATE, target, JaxbUtil.xml(model)))
                .build());
        return JSON.parseObject(JSON.toJSONString(JSON.parseObject(JSON.toJSONString(XmlUtil.xml2map(result)))
                .getJSONObject("Body")
                .getJSONObject(target + "Response")
                .getJSONObject(RESPONSE)), new TypeReference<Outcome<O>>() {});
    }

    public abstract void testNormal();

    public abstract void testDefect();
}
