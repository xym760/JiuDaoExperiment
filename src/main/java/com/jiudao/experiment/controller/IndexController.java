package com.jiudao.experiment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @ResponseBody
    @GetMapping("/index/me")
    public String indexAboutMe(HttpServletRequest request){
        return request.getRemoteAddr();
    }

    @ResponseBody
    @GetMapping("/index/form")
    public String indexSubmit(HttpServletRequest request){
        return "Is security?";
    }
}
