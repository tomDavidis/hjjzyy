package com.yuntai.upp.access.access;

import com.alibaba.fastjson.JSON;
import com.yuntai.hdp.access.RequestPack;
import com.yuntai.upp.access.ProviderBoot;
import com.yuntai.upp.client.export.access.ClientReceiver;
import com.yuntai.upp.model.dto.access.bill.BillDto;
import com.yuntai.upp.support.enums.AccessCmdType;
import com.yuntai.upp.support.enums.YesOrNo;
import com.yuntai.upp.support.util.DateUtil;
import com.yuntai.upp.support.util.TraceIdUtil;
import com.yuntai.upp.support.util.UUIDUtil;
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
 * @description 单元测试-账单下载(延时)
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

    @Value("${server.hdp.hosid}")
    private String hosid;

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

        request.setCmd(AccessCmdType.I0001.getCode());
        request.setSeqno(UUIDUtil.createUUID());
        request.setClientId(UUIDUtil.createUUID());
        request.setHosId(hosid);
        request.setSendTime(System.currentTimeMillis());

        BillDto dto = BillDto.builder()
                .url("114.215.200.225")
                .port(21)
                .user("dzftp")
                .password("dzxqq")
                .path(MessageFormat.format("/upp/bills/{0}/current/triple/partner/{1}/", "{0}", DateUtil.formateDate(LocalDateTime.now(), DateUtil.FORMAT_YYMMDD)))
                .file(MessageFormat.format("{0}-triple-partner-{1}-bills.csv", "{0}", DateUtil.formateDate(LocalDateTime.now(), DateUtil.FORMAT_YYMMDD)))
                .partners(new ArrayList<Long>(10) {
                    private static final long serialVersionUID = -2147265207196198394L;
                    {
                        add(999L);
                    }
                })
                .trace(UUIDUtil.createUUID())
                .isMerge(YesOrNo.YES.getCode())
                .start(LocalDateTime.now())
                .end(LocalDateTime.now().minusHours(2))
                .trace(UUIDUtil.createUUID())
                .build();
        request.setBody(JSON.toJSONString(dto));

        receiver.getHospitalResult(request);
        // 需要预留一部分时候给异步线程处理&上传文件,否则 jvm 关闭后线程池生命周期也立即结束
        TimeUnit.SECONDS.sleep(60);
    }
}
