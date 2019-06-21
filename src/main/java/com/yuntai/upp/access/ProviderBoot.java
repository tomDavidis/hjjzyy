package com.yuntai.upp.access;

import com.yuntai.upp.support.util.LoggerUtil;
import com.yuntai.upp.support.util.TraceIdUtil;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author jinren@hsyuntai.com
 * @description 程序启动入口
 *              若客户端业务处理(如账单下载,无需经过 客户端 DB 数据整合)
 *              则
 *                  @SpringBootApplication(scanBasePackages = {"com.yuntai.upp.access", "com.yuntai.upp.client"},
 *                      exclude = {DataSourceAutoConfiguration.class})
 *              修改为
 *                  @SpringBootApplication(scanBasePackages = {"com.yuntai.upp.access", "com.yuntai.upp.client"},
 *                      exclude = {DataSourceAutoConfiguration.class, DynamicDataSourceAutoConfiguration.class})
 * @className SpringBootApplication
 * @package com.yuntai.upp.access
 * @date 2019-06-03 09:58
 * @copyright 版权归 HSYUNTAI 所有
 */

@Slf4j
@EnableAsync
@SpringBootApplication(scanBasePackages = {"com.yuntai.upp.access", "com.yuntai.upp.client"},
        exclude = {DataSourceAutoConfiguration.class})
@MapperScan("com.yuntai.upp.access.mapper")
public class ProviderBoot {

    public static void main(String[] args) {
        TraceIdUtil.createLocalTraceId("客户端主线程");
        System.setProperty("log4j.skipJansi", "false");
        SpringApplication.run(ProviderBoot.class, args);
        LoggerUtil.info(LOGGER, "对接程序正常启动");
    }
}
