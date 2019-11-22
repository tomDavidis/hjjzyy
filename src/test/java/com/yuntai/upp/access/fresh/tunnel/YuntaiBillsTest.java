package com.yuntai.upp.access.fresh.tunnel;

import com.alibaba.fastjson.TypeReference;
import com.yuntai.upp.access.UppAccessApplication;
import com.yuntai.upp.access.fresh.AbstractSoapui;
import com.yuntai.upp.access.fresh.mock.YuntaiBillsMock;
import com.yuntai.upp.access.util.MessageUtil;
import com.yuntai.upp.access.util.MockUtil;
import com.yuntai.upp.client.fresh.model.bo.Outcome;
import com.yuntai.upp.client.fresh.model.dto.yuntaibills.YuntaiBillsDto;
import com.yuntai.upp.client.fresh.model.vo.yuntaibills.YuntaiBillsVo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static com.yuntai.upp.client.config.constant.ConstantInstance.CHANNEL_BILLS;
import static com.yuntai.upp.client.config.constant.ConstantInstance.YUNTAI_BILLS;

/**
 * @description 单元测试-账单下载(芸泰账单)
 * @className YuntaiBillsTest
 * @package com.yuntai.upp.access.fresh.tunnel
 * @author jinren@hsyuntai.com
 * @date 2019/11/21 20:11
 * @copyright 版权归 HSYUNTAI 所有
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UppAccessApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class YuntaiBillsTest extends AbstractSoapui<YuntaiBillsDto, Outcome<List<YuntaiBillsVo>>> {

    /**
     * @description 正常场景
     * @param
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/11/21 20:12
     */
    @Test
    @Override
    public void testNormal() {
        Outcome<List<YuntaiBillsVo>> outcome = send(YuntaiBillsMock.normal(), YUNTAI_BILLS, new TypeReference<Outcome<List<YuntaiBillsVo>>>() {});
        Assert.assertNotNull(outcome);
        /*
         * 实体内含有非基础数据类型, 解析有点问题, 暂不支持验签
         */
        Assert.assertTrue(outcome.isResult());
        Assert.assertEquals(SUCCESS, outcome.getKind());
    }

    /**
     * @description 字段缺失
     * @param
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/11/21 20:12
     */
    @Test
    @Override
    public void testDefect() {
        Arrays.stream(YuntaiBillsDto.class.getDeclaredFields())
                .forEach(field -> Arrays.stream(field.getDeclaredAnnotations())
                        .filter(MockUtil::filter)
                        .forEach(annotation -> {
                            YuntaiBillsDto model = MockUtil.mock(YuntaiBillsMock.normal(), field, annotation);
                            Outcome<List<YuntaiBillsVo>> outcome = send(model, CHANNEL_BILLS, new TypeReference<Outcome<List<YuntaiBillsVo>>>() {});
                            Assert.assertNotNull(outcome);
                            Assert.assertEquals(FAIL, outcome.getKind());
                            Assert.assertEquals(outcome.getMsg(), MessageUtil.message(model));
                        }));
    }
}