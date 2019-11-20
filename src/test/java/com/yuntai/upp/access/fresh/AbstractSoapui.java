package com.yuntai.upp.access.fresh;

import com.alibaba.fastjson.JSON;
import com.yuntai.upp.client.basic.util.TraceIdUtil;
import com.yuntai.upp.client.config.cache.CacheInstance;
import com.yuntai.upp.client.config.constant.ConstantInstance;
import com.yuntai.upp.client.fresh.model.dto.network.NetworkDto;
import com.yuntai.upp.sdk.interfaces.Signable;
import com.yuntai.upp.sdk.util.SignUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.service.model.BindingInfo;
import org.apache.cxf.service.model.BindingOperationInfo;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import javax.xml.namespace.QName;

@Slf4j
public class AbstractSoapui {

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

    protected void send() throws Exception {
        String salt = CacheInstance.md5Salt(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID);
        Assert.assertNotNull(salt);

        JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
        Client client = factory.createClient(WSDL_URL);
        Endpoint endpoint = client.getEndpoint();
        NetworkDto model = new NetworkDto().toBuilder()
                .isvId(ConstantInstance.ISV_ID)
                .partnerId(ConstantInstance.PARTNER_ID)
                .version(Signable.VERSION)
                .timestamp(System.currentTimeMillis())
                .build();
        model.setSign(SignUtil.signMd5(model, salt));

        QName q = new QName(endpoint.getService().getName().getNamespaceURI(), ConstantInstance.NETWORK);

//        Object[] objects = client.invoke(q, model);

        BindingInfo bindingInfo = endpoint.getEndpointInfo().getBinding();

        System.out.println(client);
        if (bindingInfo.getOperation(q) == null) {
            for (BindingOperationInfo operationInfo : bindingInfo.getOperations()) {
                if (ConstantInstance.NETWORK.equals(operationInfo.getName().getLocalPart())) {
                    q = operationInfo.getName();
                    break;
                }
            }
        }

        System.out.println(JSON.toJSONString(q));

        Object[] res = null;

        try {
            res = client.invoke(q, model);
            String xml = (String) res[0];
            System.err.println("@@@@@@@@@@@@@@@@@"+xml);
        } catch (Exception e) {
            e.printStackTrace();
        }





//        Service service = new Service();
//        Call call = (Call) service.createCall();
//        call.setTargetEndpointAddress(WSDL_URL);
//        call.setOperationName(new QName(TARGET_NAME, ConstantInstance.NETWORK));
//        // 接口方法的参数名, 参数类型,参数模式  IN(输入), OUT(输出) or INOUT(输入输出)
//        call.addParameter(REQUEST, null, ParameterMode.IN);
//        // 设置返回类型
//        call.setReturnType(XMLType.XSD_STRING);
//        // 调用方法
//
//
//        Object result = call.invoke(new Object[]{JaxbUtil.xml(model).replace("<request>", "")
//                .replace("</request>", "")});
//        LoggerUtil.info(LOGGER, "返回结果<<<<<result={0}", JSON.toJSONString(result));
//        System.out.println(JSON.toJSONString(result));


//
//        JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance(bus);
//        Client client = factory.createClient(WSDL_URL);
//
//        model.setSign(SignUtil.signMd5(model, salt));
//
//        QName q = new QName(TARGET_NAME, ConstantInstance.NETWORK);
//
//        Object[] objects = client.invoke(q, model);
//        System.out.println(JSON.toJSONString(objects));
    }

}
