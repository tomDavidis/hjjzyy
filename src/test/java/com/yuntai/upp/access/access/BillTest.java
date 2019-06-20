package com.yuntai.upp.access.access;

import com.alibaba.fastjson.JSON;
import com.yuntai.hdp.access.RequestPack;
import com.yuntai.upp.access.ProviderBoot;
import com.yuntai.upp.client.export.access.ClientReceiver;
import com.yuntai.upp.model.dto.access.bill.BillDto;
import com.yuntai.upp.support.enums.AccessCmdType;
import com.yuntai.upp.support.enums.SignType;
import com.yuntai.upp.support.enums.YesOrNo;
import com.yuntai.upp.support.util.DateUtil;
import com.yuntai.upp.support.util.SignUtil;
import com.yuntai.upp.support.util.TraceIdUtil;
import lombok.Data;
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
import java.util.HashMap;
import java.util.UUID;
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
        String security = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCH0ejH+W876OTwyccRrPs71TpUuaOKshjYE1MEF/COzjCfkmJT5xCk6B/8TAwowPWAhZ6r9eSgG+Dp/9CQ/uI+pd8LQKrRNgeo8qn7DwN8zrjaR6kIXHeZKzlXc99Pd7U1jfhVkwMlx8jvGVIHRPHn4YAZz3hpfawhoeDeAkQ2hJOHVRVHaeBNqSVk+kyZaGh2GEQy9akLLSKJAcPeHHEviRxPt146Q6+DHtiArtc0lp4Yafsg55vvXa1CzVCharCWh7JXN3D9OnEba6iT1YVXWoACYLE4KDqGolBfCzx6VYyP463F1G0Y+D4ZmXwciKS+31DR8pJNH3GUfu7XjTZlAgMBAAECggEAECeMa3SkoViTGny1e8m6XCSBeqBV7mI+iDzMK/l4+FNX/zLXFSmvlh2x6D1XWOWSjcXW2Jc3qT+Hoe12bjiSymbKP7liV934TUUHqL/hZ0/jNrs9RKxS4Vn9yQQUNIyFdig7uFnzDuRTZvT5B7pQl4b/yJIfvVRgS+PILj/QwzcXQURaSFJzYL4ybs4SbBHYoMrKIhT+OgNmcUL7A5GtyL8MYCYQzCiSXDVtzd+JUxBn0kGIFj7q5AScSaHMXxLgTmdS/kWzAVA9eh4FY0zuQQxkMGS+g96aRQ/eFQOr/0ia6g3YkUo7sTRdUB1TFgkWG0Q0siXNANuuG8x+jBnugQKBgQC/oHDbziPHLlRXxQgDzjqpTzt7UX0W36iIjPXAouaU/fDRwiJ/M8ge+D+H9auTh7uGsjdsRtHqxu54a4qFFCjEuUKIWHk0OE0vULVvpGcmACNu++h5RgYOSb6uD/dGD+VgEAg7kvBRW0loFLC7p1s8oLiUZtfEnshcxD92B5iV4QKBgQC1ci96tS+DU13DYM7wemEvvpwOcYKLb/BwMDfhQUvRzUT32f1Gu0vUK6OCfDClGK79K39+mkX/WxescR7umRKEu43UUx+JrwvW4XzMe1vMeVD4f+t+1KNNH46ipCVNcoe/YMwF4gJB69s4hzQd2OlatPMT1L1dPiGEjr33XTlpBQKBgBJ9tOZA+graWaEujhtv5xE3aBH6RwvSAT1L/Nrd3Uo7RuW2ygVkRcdXRSoldq0ByAKzZ82298Kj95b/KZq120lAZz8o6mkzlZ8fNqLTRnDQSpsHC+PH8gWm8BNzzvSfzA58/Y5iG3Z+TJMSjAtPluo+yBDp66A5gjQRa95D1WGBAoGAR5UPtCpM31OBVzYXDfpJ4ysgp/YSGW6E/c2YDg7TmaaQwfNHH6N+i+rXbAAQtOFjD9AcUVcNrGtEkKxkFp9fFexkTNKqS4g6dICq2oUEN0vZLOBSiMvczO1ZOULm0IIRmefXNa7vUxB4gPmeDvRnQ3jFuRohxupfEMu7mB44Kv0CgYBfn0x/ZQwd55+9Ne8PhbOwAoCqiVKhAza/zbDSb9ph0pAFLm5KupdNqwKWi4laxbvIq6AlcvHWE7JT0wJ8OQoDZwL2/T9Ua5DwPwRLwdv0Ghna0LqzAKD6OQrA0NGTYVPmDJsSXBiEhWkh/ma9CZYEiLP5HVXcspB1A70QId1Hew==";

        RequestPack request = new RequestPack();

        request.setCmd(AccessCmdType.I0001.getCode());
        request.setSeqno(UUID.randomUUID().toString().replace("-", ""));
        request.setClientId(UUID.randomUUID().toString().replace("-", ""));
        request.setHosId(hosid);
        request.setSendTime(System.currentTimeMillis());

        BillDto dto = BillDto.builder()
                .url("114.215.200.225")
                .port(21)
                .user("dzftp")
                .password("dzxqq")
                .path(MessageFormat.format("/upp/bills/{0}/current/triple/partner/{1}/", "999", DateUtil.formateDate(LocalDateTime.now(), DateUtil.FORMAT_YYMMDD)))
                .file(MessageFormat.format("{0}-triple-partner-{1}-bills.csv", "999", DateUtil.formateDate(LocalDateTime.now(), DateUtil.FORMAT_YYMMDD)))
                .partners(new ArrayList<String>(10) {
                    private static final long serialVersionUID = -2147265207196198394L;
                    {
                        add("999");
                    }
                })
                .trace(UUID.randomUUID().toString().replace("-", ""))
                .isMerge(YesOrNo.YES.getCode())
                .start(LocalDateTime.now())
                .end(LocalDateTime.now().minusHours(2))
                .trace(UUID.randomUUID().toString().replace("-", ""))
                .build();
        dto.setSign(SignUtil.signRSA(dto, security, SignType.RSA2));
        request.setBody(JSON.toJSONString(dto));

        receiver.getHospitalResult(request);
        // 需要预留一部分时候给异步线程处理&上传文件,否则 jvm 关闭后线程池生命周期也立即结束
        TimeUnit.SECONDS.sleep(60);
    }
}
