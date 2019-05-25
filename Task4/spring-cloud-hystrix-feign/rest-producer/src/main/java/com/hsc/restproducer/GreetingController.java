package com.hsc.restproducer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface GreetingController {
    @GetMapping("/greeting/{username}")
    void greeting(@PathVariable("username") String username, HttpServletResponse response) throws InterruptedException, IOException;
}