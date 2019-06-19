package com.hsc.security;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class JumpController {

    @RequestMapping(value="/useAdj", method = RequestMethod.GET)
    public ModelAndView jumpToAdj(){
        return new ModelAndView("redirect:http://localhost:3030/login/" + "admin" + "/" + "admin");
    }
}
