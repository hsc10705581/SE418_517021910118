package com.homework.demo;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SpringSecurityTests {
    @Autowired
    private MockMvc mvc;

    @Test
    public void test302() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .get("/")) //GET request for any uri instead of "/login"
                .andExpect(MockMvcResultMatchers.status().is(302))
                .andReturn();
        String res = mvcResult.getResponse().getContentAsString();
    }

    @Test
    public void testLogin() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .get("/") //GET request for any uri
                .with(user("admin").password("admin"))) //GET request for any uri instead of "/login"
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        System.out.println(res);
    }

    @Test
    public void testLoginError() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .get("/") //GET request for any uri
                .with(user("error").password("error"))) //GET request for any uri instead of "/login"
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        System.out.println(res);
    }
}
