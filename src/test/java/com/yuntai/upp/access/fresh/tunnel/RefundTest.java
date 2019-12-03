package com.yuntai.upp.access.fresh.tunnel;

import com.alibaba.fastjson.TypeReference;
import com.yuntai.upp.access.fresh.AbstractSoapui;
import com.yuntai.upp.access.fresh.mock.RefundMock;
import com.yuntai.upp.access.util.MockUtil;
import com.yuntai.upp.client.basic.enums.inner.InnerCmdType;
import com.yuntai.upp.client.config.cache.CacheInstance;
import com.yuntai.upp.client.config.hdp.HdpClientInstance;
import com.yuntai.upp.client.fresh.model.bo.Outcome;
import com.yuntai.upp.client.fresh.model.dto.refund.RefundDto;
import com.yuntai.upp.client.fresh.model.vo.refund.RefundVo;
import com.yuntai.upp.sdk.interfaces.Signable;
import com.yuntai.upp.sdk.result.UnitedRefundResult;
import com.yuntai.upp.sdk.util.SignUtil;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import javax.validation.constraints.NotNull;
import java.util.Arrays;

import static com.yuntai.upp.client.config.constant.ConstantInstance.ISV_ID;
import static com.yuntai.upp.client.config.constant.ConstantInstance.PARTNER_ID;
import static com.yuntai.upp.client.config.constant.ConstantInstance.REFUND;

/**
 * @description 单元测试-交易退款
 * @className RefundTest
 * @package com.yuntai.upp.access.fresh.tunnel
 * @author jinren@hsyuntai.com
 * @date 2019/11/22 08:51
 * @copyright 版权归 HSYUNTAI 所有
 */
public class RefundTest extends AbstractSoapui<RefundDto, Outcome<RefundVo>> {

    /**
     * @description 字段缺失
     * @param
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/11/22 08:52
     */
    @Test
    @Override
    public void testDefect() {
        Arrays.stream(RefundDto.class.getDeclaredFields())
                .forEach(field -> Arrays.stream(field.getDeclaredAnnotations())
                        .filter(MockUtil::filter)
                        .forEach(annotation -> {
                            RefundDto model = MockUtil.mock(RefundMock.normal(), field, annotation);
                            Outcome<RefundVo> outcome = send(model, REFUND, new TypeReference<Outcome<RefundVo>>() {});
                            Assert.assertNotNull(outcome);
                            Assert.assertEquals(FAIL, outcome.getKind());
                        }));
    }

    /**
     * @description 正常场景
     * @param
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/11/22 15:27
     */
    @Ignore
    @Test
    @Override
    public void testNormal() {
        execute(RefundMock.normal());
    } 

    /**
     * @description 正常场景
     * @param
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/11/22 08:52
     */
    @Test
    @Override
    public void testMock() {
        RefundDto dto = RefundMock.normal();
        UnitedRefundResult result = RefundMock.mock(dto);
        PowerMockito.when(HdpClientInstance.send(Mockito.any(InnerCmdType.class), Mockito.any(Signable.class), Mockito.any()))
                .thenReturn(result);
        execute(dto);
    }

    /**
     * @description 执行 & 校验(正常场景)
     * @param dto 入参模型
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/11/29 16:37
     */
    private void execute(@NotNull RefundDto dto) {
        Outcome<RefundVo> outcome = send(dto, REFUND, new TypeReference<Outcome<RefundVo>>() {});
        Assert.assertNotNull(outcome);
        Assert.assertTrue(SignUtil.verifyMd5(outcome, CacheInstance.md5Salt(PARTNER_ID, ISV_ID)));
        Assert.assertTrue(outcome.isResult());
        Assert.assertEquals(SUCCESS, outcome.getKind());
    }
}
