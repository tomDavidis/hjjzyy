package com.yuntai.upp.access.fresh.access;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.yuntai.hdp.access.ResultKind;
import com.yuntai.hdp.access.ResultPack;
import com.yuntai.upp.access.fresh.AbstractAccess;
import com.yuntai.upp.access.fresh.mock.PresentMock;
import com.yuntai.upp.access.util.MockUtil;
import com.yuntai.upp.client.basic.enums.inner.InnerCmdType;
import com.yuntai.upp.client.config.cache.CacheInstance;
import com.yuntai.upp.client.fresh.model.bo.Outcome;
import com.yuntai.upp.client.fresh.model.dto.present.PresentDto;
import com.yuntai.upp.sdk.util.SignUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.yuntai.upp.client.config.constant.ConstantInstance.ISV_ID;
import static com.yuntai.upp.client.config.constant.ConstantInstance.PARTNER_ID;

/**
 * @description 单元测试-实时交易
 * @className PresentTest
 * @package com.yuntai.upp.access.fresh.access
 * @author jinren@hsyuntai.com
 * @date 2019-06-24 16:27
 * @copyright 版权归 HSYUNTAI 所有
 */
public class PresentTest extends AbstractAccess<PresentDto> {

    /**
     * @description 正常场景-数据
     * @param
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/11/8 17:36
     */
    @Test
    @Override
    public void testNormal() {
        ResultPack pack = super.send(InnerCmdType.PRESENT, PresentMock.normal(), Boolean.FALSE);
        Assert.assertEquals(pack.getMsg(), ResultKind.OK.getKind(), pack.getKind());
        Outcome<List<PresentDto>> outcome = JSON.parseObject(pack.getBody(), new TypeReference<Outcome<List<PresentDto>>>(){}, Feature.OrderedField);
        Assert.assertTrue(SignUtil.verifyMd5(outcome, CacheInstance.md5Salt(PARTNER_ID, ISV_ID)));
        Assert.assertTrue(outcome.isResult());
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
        Arrays.stream(PresentDto.class.getDeclaredFields())
                .forEach(field -> Arrays.stream(field.getDeclaredAnnotations())
                        .forEach(annotation -> {
                            PresentDto model = MockUtil.mock(PresentMock.normal(), field, annotation);
                            ResultPack pack = super.send(InnerCmdType.PRESENT, model, Boolean.FALSE);
                            Assert.assertEquals(pack.getKind(), ResultKind.ERROR_BUSINESS.getKind());
                        }));
    }
}
