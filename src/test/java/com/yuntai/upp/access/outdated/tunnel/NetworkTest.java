package com.yuntai.upp.access.outdated.tunnel;

import com.yuntai.upp.access.outdated.AbstractSoapui;
import com.yuntai.upp.access.outdated.mock.NetworkMock;
import com.yuntai.upp.access.util.MockUtil;
import com.yuntai.upp.client.basic.enums.outer.OuterBizCodeType;
import com.yuntai.upp.client.outdated.model.dto.network.NetworkDto;
import com.yuntai.upp.client.outdated.model.vo.network.NetworkVo;
import com.yuntai.upp.client.outdated.model.ws.ReceiverGeneric;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import javax.validation.constraints.NotNull;
import java.util.Arrays;

/**
 * @description 单元测试-网络链路
 * @className NetworkTest
 * @package com.yuntai.upp.access.outdated.tunnel
 * @author jinren@hsyuntai.com
 * @date 2019/12/2 16:48
 * @copyright 版权归 HSYUNTAI 所有
 */
public class NetworkTest extends AbstractSoapui<NetworkDto, ReceiverGeneric<NetworkVo>> {

    /**
     * @description 字段缺失
     * @param
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/12/3 10:36
     */
    @Test
    @Override
    public void testDefect() {
        Arrays.stream(NetworkDto.class.getDeclaredFields())
                .forEach(field -> Arrays.stream(field.getDeclaredAnnotations())
                        .filter(MockUtil::filter)
                        .forEach(annotation -> {
                            NetworkDto model = MockUtil.mock(NetworkMock.normal(), field, annotation);
                            Object object = send(model, OuterBizCodeType.S0000, ReceiverGeneric.class);
                            Assert.assertNotNull(object);
                            assert object instanceof ReceiverGeneric;
                            ReceiverGeneric<NetworkVo> outcome = (ReceiverGeneric<NetworkVo>) object;
                            Assert.assertNotNull(outcome);
                            Assert.assertNotNull(outcome.getHeader());
                            Assert.assertEquals(outcome.getHeader().getMsg(), FAIL, outcome.getHeader().getCode());
                            Assert.assertNull(outcome.getBody());
                        }));
    }

    /**
     * @description 正常场景
     * @param
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/12/3 10:36
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
     * @date 2019/12/3 10:36
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
     * @date 2019/12/3 10:36
     */
    private void execute(@NotNull NetworkDto dto) {
        Object object = send(dto, OuterBizCodeType.S0000, ReceiverGeneric.class);
        Assert.assertNotNull(object);
        assert object instanceof ReceiverGeneric;
        ReceiverGeneric<NetworkVo> outcome = (ReceiverGeneric<NetworkVo>) object;
        Assert.assertNotNull(outcome);
        Assert.assertNotNull(outcome.getHeader());
        Assert.assertEquals(outcome.getHeader().getMsg(), SUCCESS, outcome.getHeader().getCode());
        Assert.assertNotNull(outcome.getBody());
    }
}