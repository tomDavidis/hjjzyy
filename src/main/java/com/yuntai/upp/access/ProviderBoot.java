package com.yuntai.upp.access;

import com.yuntai.upp.support.util.LoggerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author jinren@hsyuntai.com
 * @description 程序启动入口
 * @className SpringBootApplication
 * @package com.yuntai.upp.access
 * @date 2019-06-03 09:58
 * @copyright 版权归 HSYUNTAI 所有
 */

@Slf4j
@SpringBootApplication(scanBasePackages = {"com.yuntai.upp.access", "com.yuntai.upp.client"})
public class ProviderBoot {

    public static void main(String[] args) {
        System.setProperty("log4j.skipJansi", "false");
        SpringApplication.run(ProviderBoot.class, args);
        LoggerUtil.info(LOGGER, "对接程序正常启动");
    }
}
