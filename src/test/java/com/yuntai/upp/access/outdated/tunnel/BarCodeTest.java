package com.yuntai.upp.access.outdated.tunnel;

import com.alibaba.fastjson.TypeReference;
import com.yuntai.upp.access.outdated.AbstractSoapui;
import com.yuntai.upp.access.outdated.mock.BarCodeMock;
import com.yuntai.upp.client.basic.enums.outer.OuterBizCodeType;
import com.yuntai.upp.client.outdated.model.dto.barcode.BarCodeDto;
import com.yuntai.upp.client.outdated.model.vo.barcode.BarCodeVo;
import com.yuntai.upp.client.outdated.model.ws.ReceiverGeneric;
import org.junit.Test;

import javax.validation.constraints.NotNull;

/**
 * @description 单元测试-条码支付
 * @className BarCodeTest
 * @package com.yuntai.upp.access.outdated.tunnel
 * @author jinren@hsyuntai.com
 * @date 2019/12/2 16:47
 * @copyright 版权归 HSYUNTAI 所有
 */
public class BarCodeTest extends AbstractSoapui<BarCodeDto, ReceiverGeneric<BarCodeVo>> {

    /**
     * @description 字段缺失
     * @param
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/12/2 17:18
     */
    @Test
    @Override
    public void testDefect() {
//        Arrays.stream(BarCodeDto.class.getDeclaredFields())
//                .forEach(field -> Arrays.stream(field.getDeclaredAnnotations())
//                        .filter(MockUtil::filter)
//                        .forEach(annotation -> {
//                            BarCodeDto model = MockUtil.mock(BarCodeMock.normal(), field, annotation);
//                            Outcome<BarCodeVo> outcome = send(model, BAR_CODE, new TypeReference<Outcome<BarCodeVo>>() {});
//                            Assert.assertNotNull(outcome);
//                            Assert.assertEquals(FAIL, outcome.getKind());
//                            Assert.assertEquals(outcome.getMsg(), MessageUtil.message(model));
//                        }));
    }

    /**
     * @description 正常场景
     * @param
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/12/2 17:18
     */
//    @Ignore
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
     * @date 2019/12/2 17:18
     */
    @Test
    @Override
    public void testMock() {
//        BarCodeDto dto = BarCodeMock.normal();
//        UnitedPaymentResult result = BarCodeMock.mock(dto);
//        PowerMockito.when(HdpClientInstance.send(Mockito.any(InnerCmdType.class), Mockito.any(Signable.class), Mockito.any()))
//                .thenReturn(result);
//        execute(dto);
    }

    /**
     * @description 执行 & 校验(正常场景)
     * @param dto 入参模型
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/12/2 17:19
     */
    private void execute(@NotNull BarCodeDto dto) {
        ReceiverGeneric<BarCodeVo> outcome = send(dto, OuterBizCodeType.S0002, new TypeReference<ReceiverGeneric<BarCodeVo>>() {});
//        Assert.assertNotNull(outcome);
//        Assert.assertTrue(SignUtil.verifyMd5(outcome, CacheInstance.md5Salt(PARTNER_ID, ISV_ID)));
//        Assert.assertTrue(outcome.getBody());
//        Assert.assertEquals(SUCCESS, outcome.getKind());
    }
}
