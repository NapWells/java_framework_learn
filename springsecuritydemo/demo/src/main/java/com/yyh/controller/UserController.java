package com.yyh.controller;

import com.yyh.mapper.ClUserMapper;
import com.yyh.model.ClUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;

import javax.annotation.Resource;

/**
 * @author lhy
 * @Date 2018/11/14 9:39
 * @JDK 1.7
 * @Description TODO
 */
@Controller
@RequestMapping("/web")
public class UserController {

    @GetMapping("/loginSuccess")
    public String loginSuccess(){
        return "home";
    }

    @GetMapping("/loginFailure")
    public String loginFailure(){
        return "loginFail";
    }

    @GetMapping({"/","/index"})
    public String index(){
        return "index";
    }

    @GetMapping("/accessDenied")
    public String accessDenied(){
        return "accessDenied";
    }
}
