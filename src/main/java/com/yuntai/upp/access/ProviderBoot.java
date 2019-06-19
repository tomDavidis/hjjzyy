package com.yuntai.upp.access;

import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceAutoConfiguration;
import com.yuntai.upp.support.util.LoggerUtil;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author jinren@hsyuntai.com
 * @description 程序启动入口
 *              若前置机业务处理(如账单下载,无需经过 前置机 DB 数据整合)
 *              则
 *                  @SpringBootApplication(scanBasePackages = {"com.yuntai.upp.access", "com.yuntai.upp.client"},
 *                      exclude = {DataSourceAutoConfiguration.class, DynamicDataSourceAutoConfiguration.class})
 *              修改为
 *                  @SpringBootApplication(scanBasePackages = {"com.yuntai.upp.access", "com.yuntai.upp.client"},
 *                      exclude = {DataSourceAutoConfiguration.class})
 * @className SpringBootApplication
 * @package com.yuntai.upp.access
 * @date 2019-06-03 09:58
 * @copyright 版权归 HSYUNTAI 所有
 */

@Slf4j
@EnableAsync
@SpringBootApplication(scanBasePackages = {"com.yuntai.upp.access", "com.yuntai.upp.client"},
        exclude = {DataSourceAutoConfiguration.class, DynamicDataSourceAutoConfiguration.class})
@MapperScan("com.yuntai.upp.access.mapper")
public class ProviderBoot {

    public static void main(String[] args) {
        System.setProperty("log4j.skipJansi", "false");
        SpringApplication.run(ProviderBoot.class, args);
        LoggerUtil.info(LOGGER, "对接程序正常启动");
    }
}
