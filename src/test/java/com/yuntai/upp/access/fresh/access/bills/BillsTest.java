package com.yuntai.upp.access.fresh.access.bills;

import com.alibaba.fastjson.TypeReference;
import com.yuntai.upp.access.UppAccessApplication;
import com.yuntai.upp.access.fresh.access.AbstractAccess;
import com.yuntai.upp.client.basic.enums.inner.InnerCmdType;
import com.yuntai.upp.client.fresh.model.bo.Outcome;
import com.yuntai.upp.client.fresh.model.dto.bills.BillsDto;
import com.yuntai.upp.client.fresh.model.vo.bills.BillsVo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


/**
 * @description 单元测试-账单下载
 * @className BillsTest
 * @package com.yuntai.upp.access.fresh.access.bills;
 * @author jinren@hsyuntai.com
 * @date 2019-06-14 13:47
 * @copyright 版权归 HSYUNTAI 所有
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UppAccessApplication.class})
public class BillsTest extends AbstractAccess<BillsDto, List<BillsVo>> {

    /***
     * @description 正常场景
     * @param
     * @return void
     * @author jinren@hsyuntai.com
     * @date 2019/11/8 17:30
     */
    @Test
    @Override
    public void testNormal() {
        Outcome<List<BillsVo>> outcome = super.send(InnerCmdType.BILLS,
                new TypeReference<List<BillsVo>>(){},
                BillsMock.normal(),
                Boolean.TRUE);
        Assert.assertTrue(outcome.isResult());
    }
}
