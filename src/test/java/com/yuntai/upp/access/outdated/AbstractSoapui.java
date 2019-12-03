package com.yuntai.upp.access.outdated;

import com.alibaba.fastjson.JSON;
import com.yuntai.upp.access.AbstractBasic;
import com.yuntai.upp.access.util.XmlUtil;
import com.yuntai.upp.client.basic.enums.outer.OuterBizCodeType;
import com.yuntai.upp.client.basic.util.HttpUtil;
import com.yuntai.upp.client.basic.util.JaxbUtil;
import com.yuntai.upp.client.basic.util.UUIDUtil;
import com.yuntai.upp.client.config.constant.ConstantInstance;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBContext;
import java.io.StringReader;
import java.text.MessageFormat;

@Slf4j
public abstract class AbstractSoapui<I, O> extends AbstractBasic {

    protected static final Integer SUCCESS = 0;
    protected static final Integer FAIL = 4444;

    private static final String TEMPLATE = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">"
            + "<SOAP-ENV:Header/>"
            + "<SOAP-ENV:Body>"
            + "<dto:wsFacePayService xmlns:dto=\"" + AbstractSoapui.TARGET_NAME + "\" >"
            + "<FacePayServiceRequest><BizCode>{0}</BizCode><RequestData>"
            + "<![CDATA[<Request>"
            + "<Header>"
            + "<RequestSn>" + UUIDUtil.create() + "</RequestSn>"
            + "<BizName>{1}</BizName>"
            + "<SecurityCode>" + UUIDUtil.create() + "</SecurityCode>"
            + "</Header>"
            + "{2}"
            + "</Request>]]>"
            + "</RequestData></FacePayServiceRequest>"
            + "</dto:wsFacePayService>"
            + "</SOAP-ENV:Body>"
            + "</SOAP-ENV:Envelope>";
    private static final String TARGET_NAME = "http://service.webservice.openservice.yuntai.com/";

    protected Object send(@NonNull I model,
                     @NonNull OuterBizCodeType type,
                     @NonNull Class<?> cls) {
        String result = HttpUtil.post(HttpUtil.Atom.builder()
                .url("http://localhost:" + ConstantInstance.PORT + "/services/facePayWebService?wsdl")
                .content(HttpUtil.CONTENT_XML)
                .accept(HttpUtil.ACCEPT_XML)
                .data(MessageFormat.format(TEMPLATE, type.getCode(), type.getInnerCmdType().getDesc(), JaxbUtil.xml(model)))
                .build());
        try {
            return JAXBContext.newInstance(cls)
                    .createUnmarshaller()
                    .unmarshal(new StringReader(JSON.parseObject(JSON.toJSONString(XmlUtil.xml2map(result)))
                            .getJSONObject("Body")
                            .getJSONObject("wsFacePayServiceResponse")
                            .getJSONObject("FacePayServiceResponse")
                            .getString("ResponseData")));
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public abstract void testDefect();

    public abstract void testNormal();

    public abstract void testMock();
}