package xyz.yuzh.cloud.eurekaconsumerribbon.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhangyu
 * @since 2018-12-15
 */
@RestController
public class DcController {

    @Autowired
    RestTemplate restTemplate;

    // 用来消费服务提供方的接口
    @GetMapping("/consumer")
    public String dc() {
        /*
         * 参数 url：
         *Spring Cloud Ribbon有一个拦截器，它能够在这里进行实际调用的时候，自动的去选取服务实例，
         * 并将实际要请求的IP地址和端口替换这里的服务名，从而完成服务接口的调用
         */
        return restTemplate.getForObject("http://eureka-client/dc", String.class);
    }
}