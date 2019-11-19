package com.yuntai.upp.access.fresh.access;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.yuntai.hdp.access.RequestPack;
import com.yuntai.hdp.access.ResultPack;
import com.yuntai.upp.client.basic.enums.inner.InnerCmdType;
import com.yuntai.upp.client.basic.interfaces.SignConvert;
import com.yuntai.upp.client.basic.util.DateUtil;
import com.yuntai.upp.client.basic.util.DesUtil;
import com.yuntai.upp.client.basic.util.TraceIdUtil;
import com.yuntai.upp.client.basic.util.UUIDUtil;
import com.yuntai.upp.client.config.constant.ConstantInstance;
import com.yuntai.upp.client.fresh.export.access.ClientReceiver;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public abstract class AbstractAccess<I extends SignConvert> {

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
     * @description 对接交互工具
     * @param innerCmdType 接口
     * @param model 云端下发报文
     * @param encrypt 入参时候加密
     * @return com.yuntai.hdp.access.ResultPack
     * @author jinren@hsyuntai.com
     * @date 2019/11/19 09:40
     */
    protected ResultPack send(@NonNull InnerCmdType innerCmdType,
                              @NonNull I model,
                              @NonNull boolean encrypt) {
        RequestPack pack = new RequestPack();
        pack.setCmd(innerCmdType.getCode());
        pack.setSeqno(UUIDUtil.create());
        pack.setClientId(UUIDUtil.create());
        pack.setHosId(resourceId);
        pack.setSendTime(System.currentTimeMillis());
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
        return result;
    }

    public abstract void testNormal();
}
