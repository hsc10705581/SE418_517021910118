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

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DemoApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void test1() throws Exception {
        String jsonString = "{\"amountOfNode\":3,\"amountOfLine\":2,\"nodes\":[\"a\",\"b\",\"c\"],\"starts\":[\"a\",\"a\"],\"ends\":[\"b\",\"c\"],\"weights\":[1, 2],\"startPoint\":\"a\"}";
        String result = "最小生成树的边为:(a,b,1)\t(a,c,2)\t";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .post("/main/prim")
                .param("values", jsonString)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .with(user("admin").password("admin")))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();
        String res = mvcResult.getResponse().getContentAsString();

        Assert.assertEquals(res, result);
    }

    @Test
    public void test2() throws Exception {
        String jsonString = "{\"amountOfNode\":3,\"amountOfLine\":3,\"nodes\":[\"a\",\"b\",\"c\"],\"starts\":[\"a\",\"a\",\"b\"],\"ends\":[\"b\",\"c\",\"c\"],\"weights\":[1,2,3],\"startPoint\":\"d\"}\n";
        String result = "请输入正确的起点！";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .post("/main/prim")
                .param("values", jsonString)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .with(user("admin").password("admin")))
                .andExpect(MockMvcResultMatchers.status().is(400))
                .andReturn();
        String res = mvcResult.getResponse().getContentAsString();

        Assert.assertEquals(res, result);
    }

    @Test
    public void test3() throws Exception {
        String jsonString = "{\"amountOfNode\":3,\"amountOfLine\":3,\"nodes\":[\"a\",\"b\",\"c\"],\"starts\":[\"a\",\"a\",\"b\"],\"ends\":[\"d\",\"c\",\"c\"],\"weights\":[1,2,3],\"startPoint\":\"a\"}";
        String result = "请输入正确的边的信息！";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .post("/main/prim")
                .param("values", jsonString)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .with(user("admin").password("admin")))
                .andExpect(MockMvcResultMatchers.status().is(400))
                .andReturn();
        String res = mvcResult.getResponse().getContentAsString();

        Assert.assertEquals(res, result);
    }

    @Test
    public void test4() throws Exception {
        String jsonString = "{\"amountOfNode\":3,\"amountOfLine\":4,\"nodes\":[\"a\",\"b\",\"c\"],\"starts\":[\"a\",\"a\",\"b\",\"a\"],\"ends\":[\"b\",\"c\",\"c\",\"b\"],\"weights\":[1,2,3,2],\"startPoint\":\"a\"}";
        String result = "边的数量不能可能的边的最大数量！";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .post("/main/prim")
                .param("values", jsonString)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .with(user("admin").password("admin")))
                .andExpect(MockMvcResultMatchers.status().is(400))
                .andReturn();
        String res = mvcResult.getResponse().getContentAsString();

        Assert.assertEquals(res, result);
    }

}
