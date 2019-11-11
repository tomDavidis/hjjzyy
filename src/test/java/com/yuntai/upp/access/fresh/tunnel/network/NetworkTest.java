package com.yuntai.upp.access.fresh.tunnel.network;

import com.yuntai.upp.access.UppAccessApplication;
import com.yuntai.upp.access.fresh.tunnel.AbstractSoapui;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UppAccessApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class NetworkTest extends AbstractSoapui {

    @Test
    public void testNormal() throws Exception {
        send();
    }
}
