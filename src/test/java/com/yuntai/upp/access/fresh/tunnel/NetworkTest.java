package com.yuntai.upp.access.fresh.tunnel;

import com.yuntai.upp.access.UppAccessApplication;
import com.yuntai.upp.access.fresh.AbstractSoapui;
import com.yuntai.upp.access.fresh.mock.NetworkMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.yuntai.upp.client.config.constant.ConstantInstance.NETWORK;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UppAccessApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class NetworkTest extends AbstractSoapui {

    @Test
    public void testNormal() throws Exception {
        send(NetworkMock.normal(), NETWORK);
    }
}
