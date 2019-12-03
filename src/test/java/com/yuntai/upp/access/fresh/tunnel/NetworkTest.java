package com.yuntai.upp.access.fresh.tunnel;

import com.alibaba.fastjson.TypeReference;
import com.yuntai.upp.access.fresh.AbstractSoapui;
import com.yuntai.upp.access.fresh.mock.NetworkMock;
import com.yuntai.upp.access.util.MockUtil;
import com.yuntai.upp.client.config.cache.CacheInstance;
import com.yuntai.upp.client.fresh.model.bo.Outcome;
import com.yuntai.upp.client.fresh.model.dto.network.NetworkDto;
import com.yuntai.upp.client.fresh.model.vo.network.NetworkVo;
import com.yuntai.upp.sdk.util.SignUtil;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import javax.validation.constraints.NotNull;
import java.util.Arrays;

import static com.yuntai.upp.client.config.constant.ConstantInstance.ISV_ID;
import static com.yuntai.upp.client.config.constant.ConstantInstance.NETWORK;
import static com.yuntai.upp.client.config.constant.ConstantInstance.PARTNER_ID;

/**
 * @description 单元测试-网络链路
 * @className NetworkTest
 * @package com.yuntai.upp.access.fresh.tunnel
 * @author jinren@hsyuntai.com
 * @date 2019/11/20 20:20
 * @copyright 版权归 HSYUNTAI 所有
 */
public class NetworkTest extends AbstractSoapui<NetworkDto, Outcome<NetworkVo>> {

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
        Arrays.stream(NetworkDto.class.getDeclaredFields())
                .forEach(field -> Arrays.stream(field.getDeclaredAnnotations())
                        .filter(MockUtil::filter)
                        .forEach(annotation -> {
                            NetworkDto model = MockUtil.mock(NetworkMock.normal(), field, annotation);
                            Outcome<NetworkVo> outcome = send(model, NETWORK, new TypeReference<Outcome<NetworkVo>>() {});
                            Assert.assertNotNull(outcome);
                            Assert.assertEquals(FAIL, outcome.getKind());
                        }));
    }

    /**
     * @description 正常场景
     * @param
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/11/22 15:26
     */
    @Ignore
    @Test
    @Override
    public void testNormal() {
        execute(NetworkMock.normal());
    }

    /**
     * @description 正常场景
     * @param
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/11/21 13:59
     */
    @Test
    @Override
    public void testMock() {
        execute(NetworkMock.normal());
    }

    /**
     * @description 执行 & 校验(正常场景)
     * @param dto 入参模型
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/11/29 16:26
     */
    private void execute(@NotNull NetworkDto dto) {
        Outcome<NetworkVo> outcome = send(dto, NETWORK, new TypeReference<Outcome<NetworkVo>>() {});
        Assert.assertNotNull(outcome);
        Assert.assertTrue(SignUtil.verifyMd5(outcome, CacheInstance.md5Salt(PARTNER_ID, ISV_ID)));
        Assert.assertTrue(outcome.isResult());
        Assert.assertEquals(SUCCESS, outcome.getKind());
    }
}
