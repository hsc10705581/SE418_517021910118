package com.homework.demo;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/graph")
public class adjMatrixGraphController {

    @RequestMapping(value="prim")
    @ResponseBody
    public String ajaxRequest(@RequestParam String values, HttpServletRequest request, HttpServletResponse response) throws IOException {
        int amountOfNode = (int)JSON.parseArray(values).toArray()[0];
        int amountOfLine = (int)JSON.parseArray(values).toArray()[1];
        char[] nodes = new char[amountOfNode];
        for(int i = 0; i < amountOfNode; i++)
        {
            nodes[i] = ((String)JSON.parseArray(values).toArray()[i+2]).charAt(0);
        }
        adjMatrixGraph graph = new adjMatrixGraph(amountOfNode, nodes, 100);
        for (int i = 0; i < amountOfLine; i++)
        {
            graph.insert(((String)JSON.parseArray(values).toArray()[3*i+amountOfLine+2]).charAt(0), ((String)JSON.parseArray(values).toArray()[3*i+amountOfLine+3]).charAt(0), ((String)JSON.parseArray(values).toArray()[3*i+amountOfLine+4]).charAt(0) - '0');
        }
        return graph.prim(((String)JSON.parseArray(values).toArray()[2+amountOfNode+3*amountOfLine]).charAt(0));
    }

}
