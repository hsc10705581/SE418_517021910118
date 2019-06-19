package com.hsc.adjmatrixgraph;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping(value="/")
public class adjMatrixGraphController {

    boolean login = false;

    @RequestMapping(value = "/login/{username}/{password}")
    public ModelAndView doLoginPost(@PathVariable("username") String username, @PathVariable("password") String password)
            throws IOException, ClassNotFoundException, SQLException {
        if(username.equals("admin") && password.equals("admin"))
        {
            login = true;
            return new ModelAndView("redirect:/main");
        }
        else
            return new ModelAndView("redirect:http://localhost:8080/login");
    }

    @RequestMapping(value={"", "main"}, method = RequestMethod.GET)
    public ModelAndView jumpToLogin(){
        if(login)
            return new ModelAndView("main");
        else
            return new ModelAndView("redirect:http://localhost:8080/login");
    }

    @RequestMapping(value="main/prim", method = RequestMethod.POST)
    public ResponseEntity<String> ajaxRequest(@RequestParam String values, HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject js = JSONObject.parseObject(values);
        int amountOfNode = js.getInteger("amountOfNode");
        int amountOfLine = js.getInteger("amountOfLine");
        if(amountOfLine > amountOfNode * (amountOfNode - 1) / 2 )
            return new ResponseEntity<>("边的数量不能可能的边的最大数量！", HttpStatus.BAD_REQUEST);
        String[] nodes = new String[amountOfNode];
        for(int i = 0; i < amountOfNode; i++)
        {
            nodes[i] = (String)js.getJSONArray("nodes").toArray()[i];
        }
        adjMatrixGraph graph = new adjMatrixGraph(amountOfNode, nodes, 100);
        for (int i = 0; i < amountOfLine; i++)
        {
            String start = (String)js.getJSONArray("starts").toArray()[i];
            String end = (String)js.getJSONArray("ends").toArray()[i];
            int weight = (int)js.getJSONArray("weights").toArray()[i];
            if(graph.find(start) == -1 || graph.find(end) == -1 || weight < 0 || weight > 10)
                return new ResponseEntity<>("请输入正确的边的信息！", HttpStatus.BAD_REQUEST);
            graph.insert(start, end, weight);
        }
        String startPoint = js.getString("startPoint");
        if (!Arrays.asList(nodes).contains(startPoint))
            return new ResponseEntity<>("请输入正确的起点！", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(graph.prim(startPoint), HttpStatus.OK);
    }

}
