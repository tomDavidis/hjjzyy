package com.yuntai.upp.access.access;

import com.alibaba.fastjson.JSON;
import com.yuntai.hdp.access.RequestPack;
import com.yuntai.upp.access.UppAccessApplication;
import com.yuntai.upp.client.basic.enums.inner.InnerCmdType;
import com.yuntai.upp.client.basic.util.DateUtil;
import com.yuntai.upp.client.basic.util.DesUtil;
import com.yuntai.upp.client.basic.util.TraceIdUtil;
import com.yuntai.upp.client.basic.util.UUIDUtil;
import com.yuntai.upp.client.config.constant.ConstantInstance;
import com.yuntai.upp.client.fresh.export.access.ClientReceiver;
import com.yuntai.upp.client.fresh.model.bo.Isv;
import com.yuntai.upp.client.fresh.model.dto.bill.BillDto;
import com.yuntai.upp.sdk.enums.CheckBillsDownloadType;
import com.yuntai.upp.sdk.enums.YesOrNo;
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
import java.text.MessageFormat;
import java.time.LocalDateTime;
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
@SpringBootTest(classes = {UppAccessApplication.class})
public class BillTest {

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

        request.setCmd(InnerCmdType.BILL.getCode());
        request.setSeqno(UUIDUtil.create());
        request.setClientId(UUIDUtil.create());
        request.setHosId(resourceId);
        request.setSendTime(System.currentTimeMillis());

        BillDto dto = BillDto.builder()
                .timestamp(System.currentTimeMillis())
                .version(Signable.VERSION)
                .ftpIp("114.215.200.225")
                .ftpPort(21)
                .ftpUser("dzftp")
                .ftpPwd("dzxqq")
                .filePath(MessageFormat.format("/upp/bills/{0}/current/triple/partner/{1}/", 999L,DateUtil.format(LocalDateTime.now(), DateUtil.FORMAT_DATE)))
                .fileName(MessageFormat.format("{0}-triple-partner-{1}-bills.csv", 999L, DateUtil.format(LocalDateTime.now(), DateUtil.FORMAT_DATE)))
                .partnerId(999L)
                .isMergeRefund(YesOrNo.YES.getCode())
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().minusHours(2))
                .checkBillsDownloadType(CheckBillsDownloadType.DIRECT.getCode())
                .build();
        dto.setSign(SignUtil.signMd5(dto, Isv.get(dto.getPartnerId(), dto.getIsvId()).getMd5Salt()));
        request.setBody(DesUtil.encrypt(JSON.toJSONString(dto), ConstantInstance.DES_SALT));
        receiver.getHospitalResult(request);
        // 需要预留一部分时候给异步线程处理&上传文件,否则 jvm 关闭后线程池生命周期也立即结束
        TimeUnit.SECONDS.sleep(60);
    }
}
