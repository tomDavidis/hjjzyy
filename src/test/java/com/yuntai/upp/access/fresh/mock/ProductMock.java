package com.yuntai.upp.access.fresh.mock;

import com.yuntai.upp.sdk.enums.ChannelProductType;
import com.yuntai.upp.sdk.result.ChannelResult;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;

/**
 * @description 数据模拟
 * @className ProductMock
 * @package com.yuntai.upp.access.fresh.mock
 * @author jinren@hsyuntai.com
 * @date 2019/11/29 15:39
 * @copyright 版权归 HSYUNTAI 所有
 */
public class ProductMock {

    private static final EnumSet<ChannelProductType> CHANNEL_PRODUCT = EnumSet.of(ChannelProductType.TEN_SCAN_CODE,
            ChannelProductType.TEN_BAR_CODE,
            ChannelProductType.ALI_FACE_CODE,
            ChannelProductType.ALI_SCAN_CODE,
            ChannelProductType.ALI_BAR_CODE,
            ChannelProductType.ALI_FACE_CODE,
            ChannelProductType.UNI_SCAN_CODE,
            ChannelProductType.UNI_BAR_CODE);

    /**
     * @description 模拟云端返回
     * @param
     * @return java.util.List<com.yuntai.upp.sdk.result.ChannelResult>
     * @author jinren@hsyuntai.com
     * @date 2019/11/29 15:41
     */
    public static List<ChannelResult> mock() {
        return new ArrayList<ChannelResult>(16) {
            private static final long serialVersionUID = 4073511257377582661L;
            {
                CHANNEL_PRODUCT.forEach(product -> {
                    add(ChannelResult.builder()
                            .channelProduct(product.getCode())
                            .channelType(product.getChannelType().getCode())
                            .refId(new Random().nextLong())
                            .build());
                });
            }
        };
    }
}
