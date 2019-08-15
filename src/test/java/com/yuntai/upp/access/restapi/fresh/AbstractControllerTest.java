package com.yuntai.upp.access.restapi.fresh;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.yuntai.upp.client.basic.util.TraceIdUtil;
import com.yuntai.upp.client.fresh.model.bo.Outcome;
import org.junit.After;
import org.junit.Before;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @description 测试工具基类
 * @className BasicControllerTest
 * @package com.yuntai.upp.access.restapi.fresh
 * @author jinren@hsyuntai.com
 * @date 2019-07-29 10:01
 * @copyright 版权归 HSYUNTAI 所有
 */
public abstract class AbstractControllerTest<V> {

    private MockMvc mvc;

    @Resource
    private WebApplicationContext wac;
//    @Resource
//    private Mockito mockito;

    @Before
    public void setup() {
        TraceIdUtil.createLocalTraceId(this.getClass().getName());
        this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @After
    public void after() {
        TraceIdUtil.clearLocalTraceId();
    }

    protected Outcome tcp(String url, Object param, TypeReference<Outcome<V>> typeReference) {
        try {
            return JSON.parseObject(mvc.perform(post(url)
                    .content(JSON.toJSONString(param))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn()
                    .getResponse()
                    .getContentAsString(), typeReference);
        } catch (Exception exception) {
        }
        return null;
    }
}
