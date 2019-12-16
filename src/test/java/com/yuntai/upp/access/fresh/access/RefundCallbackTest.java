package com.yuntai.upp.access.fresh.access;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.yuntai.hdp.access.ResultKind;
import com.yuntai.hdp.access.ResultPack;
import com.yuntai.upp.access.fresh.AbstractAccess;
import com.yuntai.upp.access.fresh.mock.RefundCallbackMock;
import com.yuntai.upp.access.util.MockUtil;
import com.yuntai.upp.client.basic.enums.inner.InnerCmdType;
import com.yuntai.upp.client.config.cache.CacheInstance;
import com.yuntai.upp.client.fresh.model.bo.Outcome;
import com.yuntai.upp.client.fresh.model.dto.refundcallback.RefundCallbackDto;
import com.yuntai.upp.client.fresh.model.vo.refundcallback.RefundCallbackVo;
import com.yuntai.upp.sdk.util.SignUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static com.yuntai.upp.client.config.constant.ConstantInstance.ISV_ID;
import static com.yuntai.upp.client.config.constant.ConstantInstance.PARTNER_ID;

/**
 * @description 单元测试-交易通知(负交易)
 * @className PaymentCallbackTest
 * @package com.yuntai.upp.access.fresh.access
 * @author jinren@hsyuntai.com
 * @date 2019-09-06 11:13
 * @copyright 版权归 HSYUNTAI 所有
 */
public class RefundCallbackTest extends AbstractAccess<RefundCallbackDto> {

    /**
     * @description 正常场景-数据
     * @param
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/11/19 17:53
     */
    @Test
    @Override
    public void testNormal() {
        BIZ_TYPE.forEach(bizType -> CHANNEL_PRODUCT_TYPE.forEach(
                channelProductType -> {
                    ResultPack pack = super.send(InnerCmdType.REFUND_CALLBACK, RefundCallbackMock.normal(bizType, channelProductType));
                    Assert.assertEquals(pack.getMsg(), ResultKind.OK.getKind(), pack.getKind());
                    Outcome<RefundCallbackVo> outcome = JSON.parseObject(pack.getBody(), new TypeReference<Outcome<RefundCallbackVo>>(){}, Feature.OrderedField);
                    Assert.assertTrue(SignUtil.verifyMd5(outcome, CacheInstance.md5Salt(PARTNER_ID, ISV_ID)));
                    Assert.assertTrue(outcome.isResult());
                }
        ));
    }

    /**
     * @description 字段缺失
     * @param
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/11/19 17:56
     */
    @Test
    @Override
    public void testDefect() {
        Arrays.stream(RefundCallbackDto.class.getDeclaredFields())
                .forEach(field -> Arrays.stream(field.getDeclaredAnnotations())
                        .forEach(annotation -> {
                            RefundCallbackDto model = MockUtil.mock(RefundCallbackMock.normal(), field, annotation);
                            ResultPack pack = super.send(InnerCmdType.REFUND_CALLBACK, model);
                            Assert.assertEquals(pack.getKind(), ResultKind.ERROR_BUSINESS.getKind());
                        }));
    }
}
