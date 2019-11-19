package com.yuntai.upp.access.fresh;

import com.alibaba.fastjson.JSON;
import com.yuntai.upp.client.basic.util.JaxbUtil;
import com.yuntai.upp.client.basic.util.LoggerUtil;
import com.yuntai.upp.client.basic.util.TraceIdUtil;
import com.yuntai.upp.client.config.cache.CacheInstance;
import com.yuntai.upp.client.config.constant.ConstantInstance;
import com.yuntai.upp.client.fresh.model.dto.network.NetworkDto;
import com.yuntai.upp.sdk.interfaces.Signable;
import lombok.extern.slf4j.Slf4j;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.cxf.Bus;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

@Slf4j
public class AbstractSoapui {

    private static final String WSDL_URL = "http://127.0.0.1:7000/services/fresh?wsdl";
    private static final String TARGET_NAME = "http://client.upp.yuntai.com/";


    @Autowired
    private Bus bus;

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

        Service service = new Service();
        Call call = (Call) service.createCall();
        call.setTargetEndpointAddress(WSDL_URL);
        // WSDL里面描述的接口名称
        call.setOperationName(new QName(TARGET_NAME, ConstantInstance.NETWORK));
        // 接口方法的参数名, 参数类型,参数模式  IN(输入), OUT(输出) or INOUT(输入输出)
        call.addParameter("network", XMLType.XSD_STRING, ParameterMode.IN);
        // 设置返回类型
        call.setReturnType(XMLType.XSD_STRING);
        // 调用方法
        NetworkDto model = new NetworkDto().toBuilder()
                .isvId(ConstantInstance.ISV_ID)
                .partnerId(ConstantInstance.PARTNER_ID)
                .version(Signable.VERSION)
                .timestamp(System.currentTimeMillis())
                .build();

        String result = (String) call.invoke(new Object[]{JaxbUtil.xml(model)});
        LoggerUtil.info(LOGGER, "返回结果<<<<<result={0}", result);
        System.out.println(JSON.toJSONString(result));


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
