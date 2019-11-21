package com.yuntai.upp.access.fresh.tunnel;

import com.yuntai.upp.access.UppAccessApplication;
import com.yuntai.upp.access.fresh.AbstractSoapui;
import com.yuntai.upp.access.fresh.mock.ChannelBillsMock;
import com.yuntai.upp.access.util.MessageUtil;
import com.yuntai.upp.access.util.MockUtil;
import com.yuntai.upp.client.config.cache.CacheInstance;
import com.yuntai.upp.client.config.constant.ConstantInstance;
import com.yuntai.upp.client.fresh.model.bo.Outcome;
import com.yuntai.upp.client.fresh.model.dto.channelbills.ChannelBillsDto;
import com.yuntai.upp.client.fresh.model.vo.channelbills.ChannelBillsVo;
import com.yuntai.upp.sdk.util.SignUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static com.yuntai.upp.client.config.constant.ConstantInstance.CHANNEL_BILLS;

/**
 * @description 单元测试-账单下载(渠道账单)
 * @className ChannelBillsTest
 * @package com.yuntai.upp.access.fresh.tunnel
 * @author jinren@hsyuntai.com
 * @date 2019/11/21 13:57
 * @copyright 版权归 HSYUNTAI 所有
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UppAccessApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ChannelBillsTest extends AbstractSoapui<ChannelBillsDto, ChannelBillsVo> {

    /**
     * @description 正常场景
     * @param
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/11/21 13:59
     */
    @Test
    @Override
    public void testNormal() {
        Outcome<ChannelBillsVo> outcome = send(ChannelBillsMock.normal(), CHANNEL_BILLS);
        Assert.assertNotNull(outcome);
        Assert.assertTrue(SignUtil.verifyMd5(outcome, CacheInstance.md5Salt(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID)));
        Assert.assertTrue(outcome.isResult());
        Assert.assertEquals(SUCCESS, outcome.getKind());
    }

    /**
     * @description 字段缺失
     * @param
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/11/21 13:59
     */
    @Test
    @Override
    public void testDefect() {
        Arrays.stream(ChannelBillsDto.class.getDeclaredFields())
                .forEach(field -> Arrays.stream(field.getDeclaredAnnotations())
                        .filter(MockUtil::filter)
                        .forEach(annotation -> {
                            ChannelBillsDto model = MockUtil.mock(ChannelBillsMock.normal(), field, annotation);
                            Outcome<ChannelBillsVo> outcome = send(model, CHANNEL_BILLS);
                            Assert.assertNotNull(outcome);
                            Assert.assertEquals(FAIL, outcome.getKind());
                            Assert.assertEquals(outcome.getMsg(), MessageUtil.message(model));
                        }));
    }
}