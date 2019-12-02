package com.yuntai.upp.access.fresh.tunnel;

import com.alibaba.fastjson.TypeReference;
import com.yuntai.upp.access.fresh.AbstractSoapui;
import com.yuntai.upp.access.fresh.mock.FaceCodeMock;
import com.yuntai.upp.access.util.MessageUtil;
import com.yuntai.upp.access.util.MockUtil;
import com.yuntai.upp.client.basic.enums.inner.InnerCmdType;
import com.yuntai.upp.client.config.cache.CacheInstance;
import com.yuntai.upp.client.config.constant.ConstantInstance;
import com.yuntai.upp.client.config.hdp.HdpClientInstance;
import com.yuntai.upp.client.fresh.model.bo.Outcome;
import com.yuntai.upp.client.fresh.model.dto.facecode.FaceCodeDto;
import com.yuntai.upp.client.fresh.model.vo.facecode.FaceCodeVo;
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

import static com.yuntai.upp.client.config.constant.ConstantInstance.FACE_CODE;
import static com.yuntai.upp.client.config.constant.ConstantInstance.ISV_ID;
import static com.yuntai.upp.client.config.constant.ConstantInstance.PARTNER_ID;

/**
 * @description 单元测试-刷脸支付
 * @className FaceCodeTest
 * @package com.yuntai.upp.access.fresh.tunnel
 * @author jinren@hsyuntai.com
 * @date 2019/12/2 15:45
 * @copyright 版权归 HSYUNTAI 所有
 */
public class FaceCodeTest extends AbstractSoapui<FaceCodeDto, Outcome<FaceCodeVo>> {

    private static final EnumSet<ChannelProductType> CHANNEL = EnumSet.of(ChannelProductType.ALI_FACE_CODE,
            ChannelProductType.TEN_FACE_CODE);

    /**
     * @description 字段缺失
     * @param
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/12/2 15:46
     */
    @Test
    @Override
    public void testDefect() {
        Arrays.stream(FaceCodeDto.class.getDeclaredFields())
                .forEach(field -> Arrays.stream(field.getDeclaredAnnotations())
                        .filter(MockUtil::filter)
                        .forEach(annotation -> {
                            FaceCodeDto model = MockUtil.mock(FaceCodeMock.normal(), field, annotation);
                            Outcome<FaceCodeVo> outcome = send(model, FACE_CODE, new TypeReference<Outcome<FaceCodeVo>>() {});
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
     * @date 2019/12/2 15:46
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
                    execute(FaceCodeMock.normal(channel));
                });
    }

    /**
     * @description 正常场景
     * @param
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/12/2 15:46
     */
    @Test
    @Override
    public void testMock() {
        FaceCodeDto dto = FaceCodeMock.normal();
        UnitedPaymentResult result = FaceCodeMock.mock(dto);
        PowerMockito.when(HdpClientInstance.send(Mockito.any(InnerCmdType.class), Mockito.any(Signable.class), Mockito.any()))
                .thenReturn(result);
        execute(dto);
    }

    /**
     * @description 执行 & 校验(正常场景)
     * @param dto 入参模型
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/12/2 15:46
     */
    private void execute(@NotNull FaceCodeDto dto) {
        Outcome<FaceCodeVo> outcome = send(dto, FACE_CODE, new TypeReference<Outcome<FaceCodeVo>>() {});
        Assert.assertNotNull(outcome);
        Assert.assertTrue(SignUtil.verifyMd5(outcome, CacheInstance.md5Salt(PARTNER_ID, ISV_ID)));
        Assert.assertTrue(outcome.isResult());
        Assert.assertEquals(SUCCESS, outcome.getKind());
    }
}