package com.yuntai.upp.access.access;

import com.alibaba.fastjson.JSON;
import com.yuntai.hdp.access.RequestPack;
import com.yuntai.upp.access.UppAccessApplication;
import com.yuntai.upp.client.basic.enums.inner.InnerCmdType;
import com.yuntai.upp.client.basic.util.TraceIdUtil;
import com.yuntai.upp.client.basic.util.UUIDUtil;
import com.yuntai.upp.client.fresh.export.access.ClientReceiver;
import com.yuntai.upp.client.fresh.model.bo.Isv;
import com.yuntai.upp.client.fresh.model.dto.present.PresentDto;
import com.yuntai.upp.sdk.interfaces.Signable;
import com.yuntai.upp.sdk.util.SignUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @description 单元测试-实时交易
 * @className PresentTest
 * @package com.yuntai.upp.access.access
 * @author jinren@hsyuntai.com
 * @date 2019-06-24 16:27
 * @copyright 版权归 HSYUNTAI 所有
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UppAccessApplication.class})
public class PresentTest {

    @Resource(name = "clientReceiver")
    private ClientReceiver receiver;

    @Value("${hdp.resource-id}")
    private String resourceId;

    @Before
    public void before() {
        TraceIdUtil.createLocalTraceId(this.getClass().getName());
    }

    @After
    public void after() {
        TraceIdUtil.clearLocalTraceId();
    }

    @Test
    public void test() throws Exception {
        RequestPack request = new RequestPack();

        request.setCmd(InnerCmdType.PRESENT.getCode());
        request.setSeqno(UUIDUtil.create());
        request.setClientId(UUIDUtil.create());
        request.setHosId(resourceId);
        request.setSendTime(System.currentTimeMillis());

        PresentDto dto = PresentDto.builder()
                .timestamp(System.currentTimeMillis())
                .version(Signable.VERSION)
                .isvId(20000L)
                .partnerId(999L)
                .outPaymentNo(UUIDUtil.create())
                .build();
        dto.setSign(SignUtil.signMd5(dto, Isv.get(dto.getPartnerId(), dto.getIsvId()).getMd5Salt()));

        request.setBody(JSON.toJSONString(dto));
        receiver.getHospitalResult(request);
        TimeUnit.SECONDS.sleep(3);
    }
}
