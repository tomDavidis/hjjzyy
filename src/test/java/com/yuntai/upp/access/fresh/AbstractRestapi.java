package com.yuntai.upp.access.fresh;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.yuntai.upp.access.AbstractBasic;
import lombok.NonNull;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public abstract class AbstractRestapi<I, O> extends AbstractBasic {

    protected static final String SUCCESS = "0";
    protected static final String FAIL = "1";

    protected O send(@NonNull String url,
                     @NonNull I model,
                     @NonNull TypeReference<O> reference) {
        try {
            return JSON.parseObject(mvc.perform(MockMvcRequestBuilders.post(url)
                    .content(JSON.toJSONString(model))
                    .contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn()
                    .getResponse()
                    .getContentAsString(), reference);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public abstract void testDefect();

    public abstract void testNormal();

    public abstract void testMock();
}
