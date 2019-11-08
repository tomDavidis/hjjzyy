package com.yuntai.upp.access.fresh.access;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.yuntai.hdp.access.RequestPack;
import com.yuntai.hdp.access.ResultKind;
import com.yuntai.hdp.access.ResultPack;
import com.yuntai.upp.client.basic.enums.inner.InnerCmdType;
import com.yuntai.upp.client.basic.interfaces.SignConvert;
import com.yuntai.upp.client.basic.util.DateUtil;
import com.yuntai.upp.client.basic.util.DesUtil;
import com.yuntai.upp.client.basic.util.TraceIdUtil;
import com.yuntai.upp.client.basic.util.UUIDUtil;
import com.yuntai.upp.client.config.cache.CacheInstance;
import com.yuntai.upp.client.config.constant.ConstantInstance;
import com.yuntai.upp.client.fresh.export.access.ClientReceiver;
import com.yuntai.upp.client.fresh.model.bo.Outcome;
import com.yuntai.upp.sdk.util.SignUtil;
import lombok.NonNull;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;

/**
 * @description 对接交互抽象类
 * @className AbstractAccess
 * @package com.yuntai.upp.access.fresh.access
 * @author jinren@hsyuntai.com
 * @date 2019/11/8 16:26
 * @copyright 版权归 HSYUNTAI 所有
 */
public abstract class AbstractAccess<I extends SignConvert, O> {

    @Resource(name = "clientReceiver")
    protected ClientReceiver receiver;

    @Value("${hdp.resource-id}")
    private String resourceId;

    @Before
    public void before() {
        TraceIdUtil.createLocalTraceId(this.getClass().getName());
    }

    @After
    public void after() {
        TraceIdUtil.clearLocalTraceId();
    }

    /**
     * @description
     * @param innerCmdType 接口
     * @param reference 前置机上推报文
     * @param model 云端下发报文
     * @return com.yuntai.upp.client.fresh.model.bo.Outcome<O>
     * @author jinren@hsyuntai.com
     * @date 2019/11/8 17:15
     */
    protected Outcome<O> send(@NonNull InnerCmdType innerCmdType,
                              @NonNull TypeReference<O> reference,
                              @NonNull I model,
                              @NonNull boolean encrypt) {
        String salt = CacheInstance.md5Salt(ConstantInstance.PARTNER_ID, ConstantInstance.ISV_ID);
        Assert.assertNotNull(salt);
        RequestPack pack = new RequestPack();
        pack.setCmd(innerCmdType.getCode());
        pack.setSeqno(UUIDUtil.create());
        pack.setClientId(UUIDUtil.create());
        pack.setHosId(resourceId);
        pack.setSendTime(System.currentTimeMillis());
        model.setSign(SignUtil.signMd5(model, salt));
        pack.setBody(encrypt ? DesUtil.encrypt(JSON.toJSONStringWithDateFormat(model,
                DateUtil.FORMAT_DATE_TIME,
                SerializerFeature.SortField,
                SerializerFeature.WriteNonStringValueAsString), ConstantInstance.DES_SALT) :
                JSON.toJSONStringWithDateFormat(model,
                        DateUtil.FORMAT_DATE_TIME,
                        SerializerFeature.SortField,
                        SerializerFeature.WriteNonStringValueAsString));
        ResultPack result = this.receiver.getHospitalResult(pack);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getMsg(), ResultKind.OK.getKind(), result.getKind());
        Outcome<O> outcome = JSON.parseObject(result.getBody(), new TypeReference<Outcome<O>>(){}, Feature.OrderedField);
        Assert.assertTrue("签名校验失败", SignUtil.verifyMd5(outcome, salt));
        return outcome;
    }

    public abstract void testNormal();
}
