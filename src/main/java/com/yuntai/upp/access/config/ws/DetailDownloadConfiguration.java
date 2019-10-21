package com.yuntai.upp.access.config.ws;

import com.yuntai.upp.client.fresh.export.tunnel.FreshTunnel;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.xml.ws.Endpoint;

/**
 * @description WS 服务配置(账单下载)
 * @className DetailConfiguration
 * @package com.yuntai.upp.access.config.ws
 * @author jinren@hsyuntai.com
 * @date 2019/10/21 18:39
 * @copyright 版权归 HSYUNTAI 所有
 */
@Configuration
public class DetailDownloadConfiguration {

    private static final String LOCATION_URI = "";

    @Autowired
    private Bus bus;

    @Resource(name = "detailTunnel")
    FreshTunnel tunnel;

    /**
     * 站点服务
     */
    @Bean
    public Endpoint fresh() {
        EndpointImpl endpoint = new EndpointImpl(bus, tunnel);
        endpoint.publish(LOCATION_URI);
        return endpoint;
    }
}
