package com.hsc.feignrestconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableFeignClients
@EnableHystrix
public class FeignRestConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignRestConsumerApplication.class, args);
    }

}
