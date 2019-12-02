package com.yuntai.upp.access.fresh.tunnel;

import com.alibaba.fastjson.TypeReference;
import com.yuntai.upp.access.UppAccessApplication;
import com.yuntai.upp.access.fresh.AbstractSoapui;
import com.yuntai.upp.access.fresh.mock.BarCodeMock;
import com.yuntai.upp.access.util.MessageUtil;
import com.yuntai.upp.access.util.MockUtil;
import com.yuntai.upp.client.basic.enums.inner.InnerCmdType;
import com.yuntai.upp.client.config.cache.CacheInstance;
import com.yuntai.upp.client.config.hdp.HdpClientInstance;
import com.yuntai.upp.client.fresh.model.bo.Outcome;
import com.yuntai.upp.client.fresh.model.dto.barcode.BarCodeDto;
import com.yuntai.upp.client.fresh.model.vo.barcode.BarCodeVo;
import com.yuntai.upp.sdk.interfaces.Signable;
import com.yuntai.upp.sdk.result.UnitedPaymentResult;
import com.yuntai.upp.sdk.util.SignUtil;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.NotNull;
import java.util.Arrays;

import static com.yuntai.upp.client.config.constant.ConstantInstance.BAR_CODE;
import static com.yuntai.upp.client.config.constant.ConstantInstance.ISV_ID;
import static com.yuntai.upp.client.config.constant.ConstantInstance.PARTNER_ID;

/**
 * @description 单元测试-交易退款
 * @className BarCodeTest
 * @package com.yuntai.upp.access.fresh.tunnel
 * @author jinren@hsyuntai.com
 * @date 2019/11/22 08:51
 * @copyright 版权归 HSYUNTAI 所有
 */
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringRunner.class)
@PowerMockIgnore({"javax.*.*", "com.sun.*", "org.*"})
@PrepareForTest({HdpClientInstance.class})
@ActiveProfiles(value = {"deploy/dev.properties"})
@SpringBootTest(classes = {UppAccessApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BarCodeTest extends AbstractSoapui<BarCodeDto, Outcome<BarCodeVo>> {

    /**
     * @description 字段缺失
     * @param
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/11/22 09:11
     */
    @Test
    @Override
    public void testDefect() {
        Arrays.stream(BarCodeDto.class.getDeclaredFields())
                .forEach(field -> Arrays.stream(field.getDeclaredAnnotations())
                        .filter(MockUtil::filter)
                        .forEach(annotation -> {
                            BarCodeDto model = MockUtil.mock(BarCodeMock.normal(), field, annotation);
                            Outcome<BarCodeVo> outcome = send(model, BAR_CODE, new TypeReference<Outcome<BarCodeVo>>() {});
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
     * @date 2019/11/22 15:25
     */
    @Ignore
    @Test
    @Override
    public void testNormal() {
        execute(BarCodeMock.normal());
    }

    /**
     * @description 正常场景
     * @param
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/11/22 09:11
     */
    @Test
    @Override
    public void testMock() {
        BarCodeDto dto = BarCodeMock.normal();
        UnitedPaymentResult result = BarCodeMock.mock(dto);
        PowerMockito.when(HdpClientInstance.send(Mockito.any(InnerCmdType.class), Mockito.any(Signable.class), Mockito.any()))
                .thenReturn(result);
        execute(dto);
    }

    /**
     * @description 执行 & 校验(正常场景)
     * @param dto 入参模型
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/11/29 16:23
     */
    private void execute(@NotNull BarCodeDto dto) {
        Outcome<BarCodeVo> outcome = send(dto, BAR_CODE, new TypeReference<Outcome<BarCodeVo>>() {});
        Assert.assertNotNull(outcome);
        Assert.assertTrue(SignUtil.verifyMd5(outcome, CacheInstance.md5Salt(PARTNER_ID, ISV_ID)));
        Assert.assertTrue(outcome.isResult());
        Assert.assertEquals(SUCCESS, outcome.getKind());
    }
}
