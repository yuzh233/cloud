package xyz.yuzh.cloud.eurekaconsumerfeign.web.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.yuzh.cloud.eurekaconsumerfeign.service.DcClient;

import javax.annotation.Resource;

/**
 * @author zhangyu
 * @since 2018-12-16
 */
@RestController
public class DcController {

    @Resource
    private DcClient dcClient;

    @GetMapping("/consumer")
    public String dc() {
        return dcClient.consumer();
    }
}