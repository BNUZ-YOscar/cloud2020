package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService {

    @Override
    public String paymentInfoOk(Integer id) {
        return "----PaymentFallbackService fall back paymentInfo_OK,o(╥ ﹏ ╥)o";
    }

    @Override
    public String paymentInfoTimeOut(Integer id) {
        return "----PaymentFallbackService fall back paymentInfo_Timeout,o(╥ ﹏ ╥)o";
    }
}
