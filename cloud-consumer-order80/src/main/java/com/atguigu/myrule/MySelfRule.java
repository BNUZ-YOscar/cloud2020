package com.atguigu.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

@Configurable
public class MySelfRule {

    @Bean
    public IRule myRule() {
        return new RandomRule();// 随机负载均衡
    }

}
