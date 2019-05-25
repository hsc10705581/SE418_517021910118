package com.hsc.restconsumer;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GreetingService {
    @HystrixCommand(fallbackMethod = "defaultGreeting", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String getGreeting(String username) {
        return new RestTemplate()
                .getForObject("http://localhost:9090/greeting/{username}",
                        String.class, username);
    }

    private String defaultGreeting(String username) {
        return "Time out. No response in 3 seconds";
    }
}