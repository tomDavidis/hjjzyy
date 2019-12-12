package com.yuntai.upp.access.fresh.restapi;

import com.alibaba.fastjson.TypeReference;
import com.yuntai.upp.access.fresh.AbstractRestapi;
import com.yuntai.upp.access.fresh.mock.YuntaiBillsMock;
import com.yuntai.upp.access.util.MockUtil;
import com.yuntai.upp.client.basic.enums.inner.InnerCmdType;
import com.yuntai.upp.client.config.hdp.HdpClientInstance;
import com.yuntai.upp.client.fresh.model.bo.Outcome;
import com.yuntai.upp.client.fresh.model.dto.yuntaibills.YuntaiBillsDto;
import com.yuntai.upp.client.fresh.model.vo.yuntaibills.YuntaiBillsVo;
import com.yuntai.upp.sdk.interfaces.Signable;
import com.yuntai.upp.sdk.result.UnitedYuntaiBillsResult;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * @description 单元测试-账单下载(芸泰账单)
 * @className YuntaiBillsTest
 * @package com.yuntai.upp.access.fresh.restapi
 * @author jinren@hsyuntai.com
 * @date 2019/12/2 10:05
 * @copyright 版权归 HSYUNTAI 所有
 */
public class YuntaiBillsTest extends AbstractRestapi<YuntaiBillsDto, Outcome<List<YuntaiBillsVo>>> {

    /**
     * 接口路径
     */
    private static final String URL = "/access/yuntaiBills";
    
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
                            Outcome<List<YuntaiBillsVo>> outcome = send(URL, model, new TypeReference<Outcome<List<YuntaiBillsVo>>>() {});
                            Assert.assertNotNull(outcome);
                            Assert.assertEquals(FAIL, outcome.getKind());
                        }));
    }

    /**
     * @description 正常场景
     * @param
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/11/22 15:28
     */
    @Ignore
    @Test
    @Override
    public void testNormal() {
        execute(YuntaiBillsMock.normal());
    }

    /**
     * @description 正常场景
     * @param
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/11/21 20:12
     */
    @Test
    @Override
    public void testMock() {
        YuntaiBillsDto dto = YuntaiBillsMock.normal();
        List<UnitedYuntaiBillsResult> result = YuntaiBillsMock.mock(dto);
        PowerMockito.when(HdpClientInstance.send(Mockito.any(InnerCmdType.class), Mockito.any(Signable.class), Mockito.any()))
                .thenReturn(result);
        execute(dto);
    }

    /**
     * @description 执行 & 校验(正常场景)
     * @param dto 入参模型
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/12/2 10:33
     */
    private void execute(@NotNull YuntaiBillsDto dto) {
        Outcome<List<YuntaiBillsVo>> outcome = send(URL, dto, new TypeReference<Outcome<List<YuntaiBillsVo>>>() {});
        Assert.assertNotNull(outcome);
        /*
         * 实体内含有非基础数据类型, 解析有点问题, 暂不支持验签
         */
        Assert.assertTrue(outcome.isResult());
        Assert.assertEquals(SUCCESS, outcome.getKind());
    }
}