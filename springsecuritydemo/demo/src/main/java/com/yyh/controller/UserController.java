package com.yyh.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * @author lhy
 * @Date 2018/11/14 9:39
 * @JDK 1.7
 * @Description TODO
 */
@Controller
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken){
            return "index";
        }else {
            return "home";
        }
    }

    @GetMapping("/accessDenied")
    public String accessDenied(){
        return "accessDenied";
    }
}
