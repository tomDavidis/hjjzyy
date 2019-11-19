package com.yuntai.upp.access.fresh.mock.barcode;

import com.yuntai.upp.client.basic.util.UUIDUtil;
import com.yuntai.upp.client.config.cache.CacheInstance;
import com.yuntai.upp.client.fresh.model.dto.barcode.BarCodeDto;
import com.yuntai.upp.sdk.util.SignUtil;
import org.junit.Assert;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.yuntai.upp.client.config.constant.ConstantInstance.ISV_ID;
import static com.yuntai.upp.client.config.constant.ConstantInstance.PARTNER_ID;
import static com.yuntai.upp.sdk.interfaces.Signable.VERSION;

/**
 * @description 数据模拟-条码支付
 * @className BarCodeMock
 * @package com.yuntai.upp.access.fresh.mock.barcode
 * @author jinren@hsyuntai.com
 * @date 2019/11/19 17:05
 * @copyright 版权归 HSYUNTAI 所有
 */
public class BarCodeMock {

    /**
     * @description 正常场景
     * @param
     * @return com.yuntai.upp.client.fresh.model.dto.barcode.BarCodeDto
     * @author jinren@hsyuntai.com
     * @date 2019/11/19 17:08
     */
    public static BarCodeDto normal() {
        BarCodeDto model = BarCodeDto.builder()
                .timestamp(System.currentTimeMillis())
                .version(VERSION)
                /*
                 * 临时使用(仅供单元测试, 实际场景禁止采用该方式)
                 */
                .isvId(ISV_ID)
                .partnerId(PARTNER_ID)
                .authCode("289827945342172000")
                .paymentNo(UUIDUtil.create())
                .tradeFee(new BigDecimal(0.01D).setScale(2, BigDecimal.ROUND_HALF_UP))
                .subject("条码支付(upp-client)-标题")
                .body("条码支付(upp-client)-内容")
                .expireTime(LocalDateTime.now().plusMinutes(5))
                .build();
        String salt = CacheInstance.md5Salt(PARTNER_ID, ISV_ID);
        Assert.assertNotNull(salt);
        model.setSign(SignUtil.signMd5Out(model, salt));
        return model;
    }
}
