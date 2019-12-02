package com.yuntai.upp.access.fresh;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.yuntai.upp.access.AbstractBasic;
import com.yuntai.upp.access.util.XmlUtil;
import com.yuntai.upp.client.basic.util.HttpUtil;
import com.yuntai.upp.client.basic.util.JaxbUtil;
import com.yuntai.upp.client.config.constant.ConstantInstance;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;

import static com.yuntai.upp.client.config.constant.ConstantInstance.RESPONSE;

@Slf4j
public abstract class AbstractSoapui<I, O> extends AbstractBasic {

    protected static final String SUCCESS = "0";
    protected static final String FAIL = "1";

    private static final String TEMPLATE = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">"
            + "<SOAP-ENV:Header/>"
            + "<SOAP-ENV:Body>"
            + "<dto:{0} xmlns:dto=\"" + AbstractSoapui.TARGET_NAME + "\" >{1}</dto:{0}>"
            + "</SOAP-ENV:Body>"
            + "</SOAP-ENV:Envelope>";
    private static final String TARGET_NAME = "http://client.upp.yuntai.com/";

    protected O send(@NonNull I model,
                     @NonNull String target,
                     @NonNull TypeReference<O> reference) {
        String result = HttpUtil.post(HttpUtil.Atom.builder()
                .url("http://localhost:" + ConstantInstance.PORT + "/services/fresh?wsdl")
                .content(HttpUtil.CONTENT_XML)
                .accept(HttpUtil.ACCEPT_XML)
                .data(MessageFormat.format(TEMPLATE, target, JaxbUtil.xml(model)))
                .build());
        String data = JSON.toJSONString(XmlUtil.xml2map(result));
        return JSON.parseObject(JSON.toJSONString(JSON.parseObject(data)
                .getJSONObject("Body")
                .getJSONObject(target + "Response")
                .getJSONObject(RESPONSE)), reference);
    }

//    protected O send(@NonNull I model,
//                     @NonNull String target,
//                     @NonNull TypeReference<O> reference) {
//        try {
//            System.out.println(mvc.perform(MockMvcRequestBuilders.post(WSDL_URL)
//                    .content(MessageFormat.format(TEMPLATE, target, JaxbUtil.xml(model)))
//                    .contentType(MediaType.TEXT_XML))
////                    .andExpect(status().isOk())
////                    .andExpect(content().contentType(MediaType.APPLICATION_XML))
//                    .andDo(MockMvcResultHandlers.print())
//                    .andReturn()
//                    .getResponse()
//                    .getContentAsString());
//            return JSON.parseObject("", reference);
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }
//        return null;
//    }

    public abstract void testDefect();

    public abstract void testNormal();

    public abstract void testMock();
}