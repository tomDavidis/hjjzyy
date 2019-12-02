package com.yuntai.upp.access.fresh.mock;
import com.yuntai.upp.client.basic.enums.inner.InnerBizType;
import com.yuntai.upp.client.basic.util.BigDecimalUtil;
import com.yuntai.upp.client.config.cache.CacheInstance;
import com.yuntai.upp.client.fresh.model.dto.aggcode.AggCodeDto;
import com.yuntai.upp.sdk.result.UnitedPreOrderResult;
import com.yuntai.upp.sdk.util.SignUtil;
import lombok.NonNull;
import org.junit.Assert;

import java.time.LocalDateTime;

import static com.yuntai.upp.access.CustomConstant.PAYMENT_NO;
import static com.yuntai.upp.access.CustomConstant.TRADE_FEE;
import static com.yuntai.upp.client.config.constant.ConstantInstance.ISV_ID;
import static com.yuntai.upp.client.config.constant.ConstantInstance.PARTNER_ID;
import static com.yuntai.upp.sdk.interfaces.Signable.VERSION;

/**
 * @description 数据模拟-聚合支付
 * @className AggCodeMock
 * @package com.yuntai.upp.access.fresh.mock
 * @author jinren@hsyuntai.com
 * @date 2019/11/21 11:47
 * @copyright 版权归 HSYUNTAI 所有
 */
public class AggCodeMock {

    /**
     * @description 正常场景
     * @param
     * @return com.yuntai.upp.client.fresh.model.dto.aggcode.AggCodeDto
     * @author jinren@hsyuntai.com
     * @date 2019/11/21 11:47
     */
    public static AggCodeDto normal() {
        AggCodeDto model = new AggCodeDto().toBuilder()
                .timestamp(System.currentTimeMillis())
                .version(VERSION)
                /*
                 * 临时使用(仅供单元测试, 实际场景禁止采用该方式)
                 */
                .isvId(ISV_ID)
                .partnerId(PARTNER_ID)
                .paymentNo(PAYMENT_NO)
                .tradeFee(TRADE_FEE)
                .subject("聚合支付(upp-client)-标题")
                .body("聚合支付(upp-client)-内容")
                .expireTime(LocalDateTime.now().plusMinutes(5))
                .build();
        String salt = CacheInstance.md5Salt(PARTNER_ID, ISV_ID);
        Assert.assertNotNull(salt);
        model.setSign(SignUtil.signMd5(model, salt));
        return model;
    }

    /**
     * @description 模拟云端返回
     * @param dto 请求入参模型(模拟数据)
     * @return com.yuntai.upp.sdk.result.UnitedPreOrderResult
     * @author jinren@hsyuntai.com
     * @date 2019/11/25 09:51
     */
    public static UnitedPreOrderResult mock(@NonNull AggCodeDto dto) {
        return UnitedPreOrderResult.builder()
                /*
                 * 临时使用(仅供单元测试, 实际场景禁止采用该方式)
                 */
                .isvId(ISV_ID)
                .partnerId(PARTNER_ID)
                .scanUrl("这里是聚合支付的码串地址")
                .paymentTime(LocalDateTime.now())
                .tradeFee(BigDecimalUtil.convert(dto.getTradeFee()))
                .bizType(InnerBizType.AGG_CODE.getCode())
                .bizId(dto.getPaymentNo())
                .orderId(0L).build();
    }
}
