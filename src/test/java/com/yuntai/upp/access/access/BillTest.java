package com.yuntai.upp.access.access;

import com.alibaba.fastjson.JSON;
import com.yuntai.hdp.access.RequestPack;
import com.yuntai.upp.access.ProviderBoot;
import com.yuntai.upp.client.export.access.ClientReceiver;
import com.yuntai.upp.client.basic.model.dto.bill.BillDto;
import com.yuntai.upp.client.basic.enums.CmdType;
import com.yuntai.upp.client.basic.enums.YesOrNo;
import com.yuntai.upp.client.basic.util.DESUtil;
import com.yuntai.upp.client.basic.util.DateUtil;
import com.yuntai.upp.client.basic.util.TraceIdUtil;
import com.yuntai.upp.client.basic.util.UUIDUtil;
import com.yuntai.upp.sdk.enums.CheckBillsDownloadType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @description 单元测试-账单下载
 * @className BillTest
 * @package com.yuntai.upp.access.access
 * @author jinren@hsyuntai.com
 * @date 2019-06-14 13:47
 * @copyright 版权归 HSYUNTAI 所有
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProviderBoot.class})
public class BillTest {

    @Resource(name = "clientReceiver")
    private ClientReceiver receiver;

    @Value("${server.hdp.resource-id}")
    private String resourceId;

    @Value("${server.constant.security}")
    private String security;


    @Before
    public void before() {
        TraceIdUtil.createLocalTraceId();
    }

    @After
    public void after() {
        TraceIdUtil.clearLocalTraceId();
    }

    @Test
    public void test() throws Exception {
        RequestPack request = new RequestPack();

        request.setCmd(CmdType.I0001.getCode());
        request.setSeqno(UUIDUtil.createUUID());
        request.setClientId(UUIDUtil.createUUID());
        request.setHosId(resourceId);
        request.setSendTime(System.currentTimeMillis());

        BillDto dto = BillDto.builder()
                .ftpIp("114.215.200.225")
                .ftpPort(21)
                .ftpUser("dzftp")
                .ftpPwd("dzxqq")
                .filePath(MessageFormat.format("/upp/bills/{0}/current/triple/partnerId/{1}/", "{0}", DateUtil.formateDate(LocalDateTime.now(), DateUtil.FORMAT_YYMMDD)))
                .fileName(MessageFormat.format("{0}-triple-partnerId-{1}-bills.csv", "{0}", DateUtil.formateDate(LocalDateTime.now(), DateUtil.FORMAT_YYMMDD)))
                .partnerIds(new ArrayList<Long>(10) {
                    private static final long serialVersionUID = -2147265207196198394L;
                    {
                        add(999L);
                    }
                })
                .traceId(UUIDUtil.createUUID())
                .isMergeRefund(YesOrNo.YES.getCode())
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().minusHours(2))
                .traceId(UUIDUtil.createUUID())
                .checkBillsDownloadType(CheckBillsDownloadType.DIRECT.getCode())
                .build();
        request.setBody(DESUtil.encrypt(JSON.toJSONString(dto), security));
        receiver.getHospitalResult(request);
        // 需要预留一部分时候给异步线程处理&上传文件,否则 jvm 关闭后线程池生命周期也立即结束
        TimeUnit.SECONDS.sleep(60);
    }
}
