package com.yuntai.upp.access.fresh;

import com.alibaba.fastjson.JSON;
import com.yuntai.upp.client.basic.util.TraceIdUtil;
import com.yuntai.upp.client.config.cache.CacheInstance;
import com.yuntai.upp.client.config.constant.ConstantInstance;
import com.yuntai.upp.client.fresh.model.dto.network.NetworkDto;
import com.yuntai.upp.sdk.interfaces.Signable;
import com.yuntai.upp.sdk.util.SignUtil;
import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.namespace.QName;

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
        JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance(bus);
        Client client = factory.createClient(WSDL_URL);
        NetworkDto model = new NetworkDto().toBuilder()
                .isvId(ConstantInstance.ISV_ID)
                .partnerId(ConstantInstance.PARTNER_ID)
                .version(Signable.VERSION)
                .timestamp(System.currentTimeMillis())
                .build();
        model.setSign(SignUtil.signMd5(model, salt));

        QName q = new QName(TARGET_NAME, ConstantInstance.NETWORK);

        Object[] objects = client.invoke(q, model);
        System.out.println(JSON.toJSONString(objects));
    }
}
