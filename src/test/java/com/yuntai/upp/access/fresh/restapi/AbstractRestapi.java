package com.yuntai.upp.access.fresh.restapi;

import com.yuntai.upp.client.basic.util.TraceIdUtil;
import org.junit.After;
import org.junit.Before;

public class AbstractRestapi {

    @Before
    public void before() {
        TraceIdUtil.createLocalTraceId(this.getClass().getName());
    }

    @After
    public void after() {
        TraceIdUtil.clearLocalTraceId();
    }
}
