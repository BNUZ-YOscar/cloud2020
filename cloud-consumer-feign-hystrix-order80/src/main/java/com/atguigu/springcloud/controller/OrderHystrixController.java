package com.atguigu.springcloud.controller;

import cn.hutool.core.util.IdUtil;
import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "paymentInfo_Global_FallbackMethod")
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.paymentInfoOk(id);
        return result;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5500")}
    )
//    @HystrixCommand
    public String paymentInfo_Timeout(@PathVariable("id") Integer id) {
//        int age = 10 / 0;
        String result = paymentHystrixService.paymentInfoTimeOut(id);
        return result;
    }

    public String paymentInfo_TimeoutHandler(Integer id) {
        return "/(ToT)/ 我是消费者 80 ，调用 8001 支付系统繁忙，请 10 秒钟后重新尝试、 \t ";
    }

    // 下面是全局 fallback 方法 
    public String paymentInfo_Global_FallbackMethod() {
        return "Global 异常处理信息，请稍后再试， /(ToT)/";
    }


}
