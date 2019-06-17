package com.yuntai.upp.access;

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
 * @description 单元测试抽象类
 * @className AbstractClient
 * @package com.yuntai.upp.access.ws
 * @author jinren@hsyuntai.com
 * @date 2019-06-13 17:40
 * @copyright 版权归 HSYUNTAI 所有
 */

public abstract class AbstractRestapiClient {

    private MockMvc mock;

    @Resource
    private WebApplicationContext context;

    @Before
    public void setup() {
        this.mock = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    protected String done(String url, String data) {
        try {
            return mock.perform(post(url)
                    .content(data)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn()
                    .getResponse()
                    .getContentAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
