package xyz.yuzh.cloud.eurekaconsumerfeign.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author zhangyu
 * @since 2018-12-16
 */
@FeignClient("eureka-client") // 指定要调用的微服务名
public interface DcClient {

    // 调用接口（不再是使用 restTemplate 的方式了 ）
    @GetMapping("/dc")
    String consumer();

}
