package com.yyh.controller;

import com.yyh.mapper.PermissionMapper;
import com.yyh.model.Permission;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * @author lhy
 * @Date 2018/10/22 9:46
 * @JDK 1.7
 * @Description TODO
 */
@RestController
public class TestController {
    @Resource
    private PermissionMapper permissionMapper;

    @GetMapping("api/allPermission")
    public List<Permission> allPermission(){
        return permissionMapper.findAll();
    }

    @GetMapping("/hi/world")
    public String hi(){
        return "hello world";
    }

    @GetMapping("/test/test")
    public String test(){
        return "test";
    }

    @PreAuthorize("permitAll()")
    @PermitAll
    @GetMapping("/study/study")
    public String  study(){
        return "study";
    }

    @RolesAllowed("ADMIN")
//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/learn/learn")
    public String  learn(){
        return "learn";
    }
}
