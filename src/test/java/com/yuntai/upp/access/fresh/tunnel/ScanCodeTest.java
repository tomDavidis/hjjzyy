package com.yuntai.upp.access.fresh.tunnel;

import com.alibaba.fastjson.TypeReference;
import com.yuntai.upp.access.fresh.AbstractSoapui;
import com.yuntai.upp.access.fresh.mock.ScanCodeMock;
import com.yuntai.upp.access.util.MessageUtil;
import com.yuntai.upp.access.util.MockUtil;
import com.yuntai.upp.client.basic.enums.inner.InnerCmdType;
import com.yuntai.upp.client.config.cache.CacheInstance;
import com.yuntai.upp.client.config.constant.ConstantInstance;
import com.yuntai.upp.client.config.hdp.HdpClientInstance;
import com.yuntai.upp.client.fresh.model.bo.Outcome;
import com.yuntai.upp.client.fresh.model.dto.scancode.ScanCodeDto;
import com.yuntai.upp.client.fresh.model.vo.scancode.ScanCodeVo;
import com.yuntai.upp.sdk.enums.ChannelProductType;
import com.yuntai.upp.sdk.interfaces.Signable;
import com.yuntai.upp.sdk.result.UnitedPaymentResult;
import com.yuntai.upp.sdk.util.SignUtil;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.EnumSet;

import static com.yuntai.upp.client.config.constant.ConstantInstance.ISV_ID;
import static com.yuntai.upp.client.config.constant.ConstantInstance.PARTNER_ID;
import static com.yuntai.upp.client.config.constant.ConstantInstance.SCAN_CODE;

/**
 * @description 单元测试-扫码支付
 * @className ScanCodeTest
 * @package com.yuntai.upp.access.fresh.tunnel
 * @author jinren@hsyuntai.com
 * @date 2019/11/21 13:29
 * @copyright 版权归 HSYUNTAI 所有
 */
public class ScanCodeTest extends AbstractSoapui<ScanCodeDto, Outcome<ScanCodeVo>> {

    private static final EnumSet<ChannelProductType> CHANNEL = EnumSet.of(ChannelProductType.ALI_SCAN_CODE,
            ChannelProductType.TEN_SCAN_CODE);

    /**
     * @description 字段缺失
     * @param
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/11/21 13:58
     */
    @Test
    @Override
    public void testDefect() {
        Arrays.stream(ScanCodeDto.class.getDeclaredFields())
                .forEach(field -> Arrays.stream(field.getDeclaredAnnotations())
                        .filter(MockUtil::filter)
                        .forEach(annotation -> {
                            ScanCodeDto model = MockUtil.mock(ScanCodeMock.normal(), field, annotation);
                            Outcome<ScanCodeVo> outcome = send(model, SCAN_CODE, new TypeReference<Outcome<ScanCodeVo>>() {});
                            Assert.assertNotNull(outcome);
                            Assert.assertEquals(FAIL, outcome.getKind());
                            Assert.assertEquals(outcome.getMsg(), MessageUtil.message(model));
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
        /*
         * 对已配置的渠道均发起一次正常数据的交互
         */
        CacheInstance.partner(ConstantInstance.PARTNER_ID)
                .getChannel()
                .stream()
                .filter(channel -> CHANNEL.contains(channel.getChannelProductType()))
                .forEach(channel -> {
                    execute(ScanCodeMock.normal(channel));
                });
    }


    /**
     * @description 正常场景
     * @param
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/11/21 13:58
     */
    @Test
    @Override
    public void testMock() {
        ScanCodeDto dto = ScanCodeMock.normal();
        UnitedPaymentResult result = ScanCodeMock.mock(dto);
        PowerMockito.when(HdpClientInstance.send(Mockito.any(InnerCmdType.class), Mockito.any(Signable.class), Mockito.any()))
                .thenReturn(result);
        execute(dto);
    }

    /**
     * @description 执行 & 校验(正常场景)
     * @param dto 入参模型
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/11/29 16:38
     */
    private void execute(@NotNull ScanCodeDto dto) {
        Outcome<ScanCodeVo> outcome = send(dto, SCAN_CODE, new TypeReference<Outcome<ScanCodeVo>>() {});
        Assert.assertNotNull(outcome);
        Assert.assertTrue(SignUtil.verifyMd5(outcome, CacheInstance.md5Salt(PARTNER_ID, ISV_ID)));
        Assert.assertTrue(outcome.isResult());
        Assert.assertEquals(SUCCESS, outcome.getKind());
    }
}
