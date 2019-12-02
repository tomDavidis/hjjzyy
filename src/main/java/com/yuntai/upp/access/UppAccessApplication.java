package com.yuntai.upp.access;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.yuntai.upp.client.basic.util.LoggerUtil;
import com.yuntai.upp.client.basic.util.TraceIdUtil;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import java.util.Objects;

/**
 * @author jinren@hsyuntai.com
 * @description 程序启动入口[禁止修改本类]
 *              mvn clean install test -D druid.registerToSysProperty=true -P (指定环境)
 *              如若需要开启 DB 配置
 *                  请在配置文件内将 spring.database.enabled=true
 *                  并进行相应 database 及 mybatis 配置, 按照 demo 所示
 * @className UppAccessApplication
 * @package com.yuntai.upp.access
 * @date 2019-06-03 09:58
 * @copyright 版权归 HSYUNTAI 所有
 */
@Slf4j
@SpringBootApplication(scanBasePackages = {"com.yuntai.upp.access"},
        exclude = {DruidDataSourceAutoConfigure.class,
                DataSourceAutoConfiguration.class,
                MybatisAutoConfiguration.class})
@MapperScan("com.yuntai.upp.access.mapper")
public class UppAccessApplication {

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource("cache.yml"));
        configurer.setProperties(Objects.requireNonNull(yaml.getObject()));
        return configurer;
    }

    public static void main(String[] args) {
        TraceIdUtil.createLocalTraceId("客户端主线程");
        System.setProperty("log4j.skipJansi", "false");
        SpringApplication.run(UppAccessApplication.class, args);
        LoggerUtil.info(LOGGER, "UppAccessApplication start successful ...");
    }
}
