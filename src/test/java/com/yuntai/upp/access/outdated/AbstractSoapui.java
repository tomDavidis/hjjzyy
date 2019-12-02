package com.yuntai.upp.access.outdated;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.yuntai.upp.access.AbstractBasic;
import com.yuntai.upp.access.util.XmlUtil;
import com.yuntai.upp.client.basic.enums.outer.OuterBizCodeType;
import com.yuntai.upp.client.basic.util.HttpUtil;
import com.yuntai.upp.client.basic.util.JaxbUtil;
import com.yuntai.upp.client.basic.util.UUIDUtil;
import com.yuntai.upp.client.config.constant.ConstantInstance;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;

import static com.yuntai.upp.client.config.constant.ConstantInstance.RESPONSE;

@Slf4j
public abstract class AbstractSoapui<I, O> extends AbstractBasic {

    protected static final String SUCCESS = "0";
    protected static final String FAIL = "4444";

    private static final String TEMPLATE = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">"
            + "<SOAP-ENV:Header/>"
            + "<SOAP-ENV:Body>"
            + "<dto:wsFacePayService xmlns:dto=\"" + AbstractSoapui.TARGET_NAME + "\" >"
            + "<FacePayServiceRequest><BizCode>{0}</BizCode><RequestData>"
            + "<![CDATA[<Request><Header><RequestSn>" + UUIDUtil.create() + "</RequestSn><BizName>{1}</BizName><SecurityCode></SecurityCode></Header>{2}</Request>]]>"
            + "</RequestData></FacePayServiceRequest>"
            + "</dto:wsFacePayService>"
            + "</SOAP-ENV:Body>"
            + "</SOAP-ENV:Envelope>";
    private static final String TARGET_NAME = "http://service.webservice.openservice.yuntai.com/";

    protected O send(@NonNull I model,
                     @NonNull OuterBizCodeType type,
                     @NonNull TypeReference<O> reference) {
        String result = HttpUtil.post(HttpUtil.Atom.builder()
                .url("http://localhost:" + ConstantInstance.PORT + "/services/facePayWebService?wsdl")
                .content(HttpUtil.CONTENT_XML)
                .accept(HttpUtil.ACCEPT_XML)
                .data(MessageFormat.format(TEMPLATE, type.getCode(), type.getInnerCmdType().getDesc(), JaxbUtil.xml(model)))
                .build());
        String data = JSON.toJSONString(XmlUtil.xml2map(result));
        return JSON.parseObject(JSON.toJSONString(JSON.parseObject(data)
                .getJSONObject("Body")
                .getJSONObject("wsFacePayServiceResponse")
                .getJSONObject("FacePayServiceResponse")
                .getJSONObject("ResponseData")
                .getJSONObject(RESPONSE)), reference);
    }

    public abstract void testDefect();

    public abstract void testNormal();

    public abstract void testMock();
}