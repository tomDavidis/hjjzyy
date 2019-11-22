package com.yuntai.upp.access.fresh.restapi;

import com.alibaba.fastjson.JSON;
import com.yuntai.upp.access.UppAccessApplication;
import com.yuntai.upp.access.fresh.mock.BarCodeMock;
import com.yuntai.upp.client.basic.util.HttpUtil;
import com.yuntai.upp.client.config.hdp.HdpClientInstance;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description 单元测试-条码支付
 * @className BarCodeTest
 * @package com.yuntai.upp.access.fresh.restapi.barcode
 * @author jinren@hsyuntai.com
 * @date 2019/11/19 17:02
 * @copyright 版权归 HSYUNTAI 所有
 */
@Slf4j
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringRunner.class)
@PowerMockIgnore({"javax.*.*", "com.sun.*", "org.*"})
@PrepareForTest({HdpClientInstance.class})
@SpringBootTest(classes = {UppAccessApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BarCodeTest {

    private static final String URL = "http://127.0.0.1:7000/access/barCode";

    @Test
    public void test() {
        HttpUtil.post(HttpUtil.Atom.builder()
                .url(URL)
                .content(HttpUtil.CONTENT_JSON)
                .accept(HttpUtil.ACCEPT_JSON)
                .data(JSON.toJSONString(BarCodeMock.normal()))
                .build());
    }
}
