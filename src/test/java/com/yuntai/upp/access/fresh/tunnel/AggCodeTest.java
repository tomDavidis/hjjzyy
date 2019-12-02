package com.yuntai.upp.access.fresh.tunnel;

import com.alibaba.fastjson.TypeReference;
import com.yuntai.upp.access.fresh.AbstractSoapui;
import com.yuntai.upp.access.fresh.mock.AggCodeMock;
import com.yuntai.upp.access.util.MessageUtil;
import com.yuntai.upp.access.util.MockUtil;
import com.yuntai.upp.client.basic.enums.inner.InnerCmdType;
import com.yuntai.upp.client.config.cache.CacheInstance;
import com.yuntai.upp.client.config.hdp.HdpClientInstance;
import com.yuntai.upp.client.fresh.model.bo.Outcome;
import com.yuntai.upp.client.fresh.model.dto.aggcode.AggCodeDto;
import com.yuntai.upp.client.fresh.model.vo.aggcode.AggCodeVo;
import com.yuntai.upp.sdk.interfaces.Signable;
import com.yuntai.upp.sdk.result.UnitedPreOrderResult;
import com.yuntai.upp.sdk.util.SignUtil;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import javax.validation.constraints.NotNull;
import java.util.Arrays;

import static com.yuntai.upp.client.config.constant.ConstantInstance.AGG_CODE;
import static com.yuntai.upp.client.config.constant.ConstantInstance.ISV_ID;
import static com.yuntai.upp.client.config.constant.ConstantInstance.PARTNER_ID;

/**
 * @description 单元测试-聚合支付
 * @className AggCodeTest
 * @package com.yuntai.upp.access.fresh.tunnel
 * @author jinren@hsyuntai.com
 * @date 2019/11/21 11:45
 * @copyright 版权归 HSYUNTAI 所有
 */
public class AggCodeTest extends AbstractSoapui<AggCodeDto, Outcome<AggCodeVo>> {

    /**
     * @description 字段缺失
     * @param
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/11/21 09:24
     */
    @Test
    @Override
    public void testDefect() {
        Arrays.stream(AggCodeDto.class.getDeclaredFields())
                .forEach(field -> Arrays.stream(field.getDeclaredAnnotations())
                        .filter(MockUtil::filter)
                        .forEach(annotation -> {
                            AggCodeDto model = MockUtil.mock(AggCodeMock.normal(), field, annotation);
                            Outcome<AggCodeVo> outcome = send(model, AGG_CODE, new TypeReference<Outcome<AggCodeVo>>() {});
                            Assert.assertNotNull(outcome);
                            Assert.assertEquals(outcome.getMsg(), MessageUtil.message(model));
                        }));
    }

    /**
     * @description 正常场景
     *              可将 @Ignore 注释掉, 用于正常场景的模拟测试
     *              禁止在 maven test 打开该单元测试用例
     * @param
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/11/22 15:22
     */
    @Ignore
    @Test
    @Override
    public void testNormal() {
        execute(AggCodeMock.normal());
    }

    /**
     * @description 正常场景
     * @param
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/11/21 09:21
     */
    @Test
    @Override
    public void testMock() {
        AggCodeDto dto = AggCodeMock.normal();
        UnitedPreOrderResult result = AggCodeMock.mock(dto);
        PowerMockito.when(HdpClientInstance.send(Mockito.any(InnerCmdType.class), Mockito.any(Signable.class), Mockito.any()))
                .thenReturn(result);
        execute(dto);
    }

    /**
     * @description 执行 & 校验(正常场景)
     * @param dto 入参模型
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/11/29 16:20
     */
    private void execute(@NotNull AggCodeDto dto) {
        Outcome<AggCodeVo> outcome = send(dto, AGG_CODE, new TypeReference<Outcome<AggCodeVo>>() {});
        Assert.assertNotNull(outcome);
        Assert.assertTrue(SignUtil.verifyMd5(outcome, CacheInstance.md5Salt(PARTNER_ID, ISV_ID)));
        Assert.assertTrue(outcome.isResult());
        Assert.assertEquals(SUCCESS, outcome.getKind());
    }
}
