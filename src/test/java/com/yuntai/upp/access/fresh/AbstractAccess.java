package com.yuntai.upp.access.fresh;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.yuntai.hdp.access.RequestPack;
import com.yuntai.hdp.access.ResultPack;
import com.yuntai.upp.access.AbstractBasic;
import com.yuntai.upp.client.basic.enums.inner.InnerCmdType;
import com.yuntai.upp.client.basic.interfaces.SignConvert;
import com.yuntai.upp.client.basic.util.DateUtil;
import com.yuntai.upp.client.basic.util.UUIDUtil;
import com.yuntai.upp.client.fresh.export.access.ClientReceiver;
import com.yuntai.upp.sdk.enums.BizType;
import com.yuntai.upp.sdk.enums.ChannelProductType;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import java.util.EnumSet;

/**
 * @description 对接交互抽象类
 * @className AbstractAccess
 * @package com.yuntai.upp.access.fresh.access
 * @author jinren@hsyuntai.com
 * @date 2019/11/8 16:26
 * @copyright 版权归 HSYUNTAI 所有
 */
@Slf4j
public abstract class AbstractAccess<I extends SignConvert> extends AbstractBasic {

    protected static final EnumSet<BizType> BIZ_TYPE = EnumSet.of(BizType.AGG_SCAN_PAY, BizType.SCAN_PAY);
    protected static final EnumSet<ChannelProductType> CHANNEL_PRODUCT_TYPE = EnumSet.of(ChannelProductType.ALI_SCAN_CODE,
            ChannelProductType.UNI_SCAN_CODE,
            ChannelProductType.TEN_SCAN_CODE);

    @Resource(name = "clientReceiver")
    protected ClientReceiver receiver;

    @Value("${hdp.resource-id}")
    private String resourceId;

    /**
     * @description 对接交互工具
     * @param innerCmdType 接口
     * @param model 云端下发报文
     * @return com.yuntai.hdp.access.ResultPack
     * @author jinren@hsyuntai.com
     * @date 2019/11/19 09:40
     */
    protected ResultPack send(@NonNull InnerCmdType innerCmdType,
                              @NonNull I model) {
        RequestPack pack = new RequestPack();
        pack.setCmd(innerCmdType.getCode());
        pack.setSeqno(UUIDUtil.create());
        pack.setClientId(UUIDUtil.create());
        pack.setHosId(resourceId);
        pack.setSendTime(System.currentTimeMillis());
        pack.setBody(JSON.toJSONStringWithDateFormat(model, DateUtil.FORMAT_DATE_TIME, SerializerFeature.SortField));
        ResultPack result = this.receiver.getHospitalResult(pack);
        Assert.assertNotNull(result);
        return result;
    }

    public abstract void testNormal();

    public abstract void testDefect();
}
