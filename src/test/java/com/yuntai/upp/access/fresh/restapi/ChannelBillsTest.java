package com.yuntai.upp.access.fresh.restapi;

import com.alibaba.fastjson.TypeReference;
import com.yuntai.upp.access.fresh.AbstractRestapi;
import com.yuntai.upp.access.fresh.mock.ChannelBillsMock;
import com.yuntai.upp.access.util.MockUtil;
import com.yuntai.upp.client.basic.util.FtpUtil;
import com.yuntai.upp.client.fresh.model.bo.Outcome;
import com.yuntai.upp.client.fresh.model.dto.channelbills.ChannelBillsDto;
import com.yuntai.upp.client.fresh.model.vo.channelbills.ChannelBillsVo;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import javax.validation.constraints.NotNull;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * @description 单元测试-账单下载(渠道账单)
 * @className ChannelBillsTest
 * @package com.yuntai.upp.access.fresh.restapi
 * @author jinren@hsyuntai.com
 * @date 2019/12/2 10:04
 * @copyright 版权归 HSYUNTAI 所有
 */
public class ChannelBillsTest extends AbstractRestapi<ChannelBillsDto, Outcome<List<ChannelBillsVo>>> {

    private static final String URL = "/access/channelBills";

    /**
     * @description 字段缺失
     * @param
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/12/2 10:30
     */
    @Test
    @Override
    public void testDefect() {
        Arrays.stream(ChannelBillsDto.class.getDeclaredFields())
                .forEach(field -> Arrays.stream(field.getDeclaredAnnotations())
                        .filter(MockUtil::filter)
                        .forEach(annotation -> {
                            ChannelBillsDto model = MockUtil.mock(ChannelBillsMock.normal(), field, annotation);
                            Outcome<List<ChannelBillsVo>> outcome = send(URL, model, new TypeReference<Outcome<List<ChannelBillsVo>>>() {});
                            Assert.assertNotNull(outcome);
                            Assert.assertEquals(FAIL, outcome.getKind());
                        }));
    }

    /**
     * @description 正常场景
     * @param
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/12/2 10:16
     */
    @Ignore
    @Test
    @Override
    public void testNormal() {
        execute(ChannelBillsMock.normal());
    }

    /**
     * @description 正常场景 
     * @param  
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/12/2 10:30
     */
    @Test
    @Override
    public void testMock() {
        ChannelBillsDto dto = ChannelBillsMock.normal();
        InputStream result = ChannelBillsMock.mock(dto);
        PowerMockito.when(FtpUtil.download(Mockito.anyString()))
                .thenReturn(result);
        execute(dto);
    }

    /**
     * @description 执行 & 校验(正常场景)
     * @param dto 入参模型
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/12/2 10:29
     */
    private void execute(@NotNull ChannelBillsDto dto) {
        Outcome<List<ChannelBillsVo>> outcome = send(URL, dto, new TypeReference<Outcome<List<ChannelBillsVo>>>() {});
        Assert.assertNotNull(outcome);
        /*
         * 实体内含有非基础数据类型, 解析有点问题, 暂不支持验签
         */
        Assert.assertTrue(outcome.isResult());
        Assert.assertEquals(SUCCESS, outcome.getKind());
    }
}