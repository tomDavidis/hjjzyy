package com.yuntai.upp.access.fresh.access;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.yuntai.hdp.access.ResultKind;
import com.yuntai.hdp.access.ResultPack;
import com.yuntai.upp.access.fresh.AbstractAccess;
import com.yuntai.upp.access.fresh.mock.MedicalMock;
import com.yuntai.upp.access.util.MockUtil;
import com.yuntai.upp.client.basic.enums.inner.InnerCmdType;
import com.yuntai.upp.client.config.cache.CacheInstance;
import com.yuntai.upp.client.fresh.model.bo.Outcome;
import com.yuntai.upp.client.fresh.model.dto.medical.MedicalDto;
import com.yuntai.upp.client.fresh.model.vo.medical.MedicalVo;
import com.yuntai.upp.sdk.util.SignUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

import static com.yuntai.upp.client.config.constant.ConstantInstance.ISV_ID;
import static com.yuntai.upp.client.config.constant.ConstantInstance.PARTNER_ID;

/**
 * @description 基本信息-账单下载
 * @className MedicalTest
 * @package com.yuntai.upp.access.fresh.access
 * @author jinren@hsyuntai.com
 * @date 2019/12/31 15:27
 * @copyright 版权归 HSYUNTAI 所有
 */
public class MedicalTest extends AbstractAccess<HashMap<String, Object>> {

    /**
     * @description 正常场景
     * @param
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/12/31 15:27
     */
    @Test
    @Override
    public void testNormal() {
        ResultPack pack = super.send(InnerCmdType.MEDICAL, MedicalMock.normal());
        Assert.assertEquals(pack.getMsg(), ResultKind.OK.getKind(), pack.getKind());
        Outcome<MedicalVo> outcome = JSON.parseObject(pack.getBody(), new TypeReference<Outcome<MedicalVo>>(){}, Feature.OrderedField);
        Assert.assertTrue(SignUtil.verifyMd5(outcome, CacheInstance.md5Salt(PARTNER_ID, ISV_ID)));
        Assert.assertTrue(outcome.isResult());
    }

    /**
     * @description 字段缺失
     * @param
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/12/31 15:27
     */
    @Test
    @Override
    public void testDefect() {
        Arrays.stream(MedicalDto.class.getDeclaredFields())
                .forEach(field -> Arrays.stream(field.getDeclaredAnnotations())
                        .forEach(annotation -> {
                            HashMap<String, Object> model = MockUtil.mock(MedicalMock.normal(), field, annotation);
                            ResultPack pack = super.send(InnerCmdType.MEDICAL, model);
                            Assert.assertEquals(pack.getKind(), ResultKind.ERROR_BUSINESS.getKind());
                        }));
    }
}
