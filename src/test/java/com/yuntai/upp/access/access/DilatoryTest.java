package com.yuntai.upp.access.access;

import com.alibaba.fastjson.JSON;
import com.yuntai.hdp.access.RequestPack;
import com.yuntai.hdp.access.ResultPack;
import com.yuntai.upp.access.ProviderBoot;
import com.yuntai.upp.client.export.access.ClientReceiver;
import com.yuntai.upp.model.dto.access.dilatory.DilatoryDto;
import com.yuntai.upp.support.enums.AccessCmdType;
import org.junit.Test;
import org.junit.runner.Request;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @description 单元测试-账单下载(延时)
 * @className DilatoryTest
 * @package com.yuntai.upp.access.access
 * @author jinren@hsyuntai.com
 * @date 2019-06-14 13:47
 * @copyright 版权归 HSYUNTAI 所有
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProviderBoot.class})
public class DilatoryTest {

    @Resource(name = "clientReceiver")
    private ClientReceiver receiver;

    @Value("${hdp.hosid}")
    private String hosid;

    @Test
    public void test() throws Exception {
        RequestPack request = new RequestPack();

        request.setCmd(AccessCmdType.I0001.getCode());
        request.setSeqno(UUID.randomUUID().toString().replace("-", ""));
        request.setClientId(UUID.randomUUID().toString().replace("-", ""));
        request.setHosId(hosid);
        request.setSendTime(System.currentTimeMillis());
        request.setBody(JSON.toJSONString(DilatoryDto.builder()
                .date(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build()));

        receiver.getHospitalResult(request);

        // 需要预留一部分时候给异步线程处理&上传文件,否则 jvm 关闭后线程池生命周期也立即结束
        TimeUnit.SECONDS.sleep(60);
    }
}
