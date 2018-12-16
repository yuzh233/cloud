package xyz.yuzh.cloud.eurekaclient.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangyu
 * @since 2018-12-15
 */
@RestController
public class DcController {

    @Autowired
    DiscoveryClient discoveryClient;

    /**
     * 服务发现：获取注册中心所有在注册的服务列表
     *
     * @return
     */
    @GetMapping("/dc")
    public String dc() {
        String services = "Services: " + discoveryClient.getServices();
        System.out.println(services);
        return "eureka-client1: " + services;
    }

}
