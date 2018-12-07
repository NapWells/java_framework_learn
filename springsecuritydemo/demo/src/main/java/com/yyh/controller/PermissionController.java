package com.yyh.controller;

import com.yyh.mapper.PermissionMapper;
import com.yyh.model.Permission;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lhy
 * @Date 2018/10/22 9:46
 * @JDK 1.7
 * @Description TODO
 */
@RestController
public class PermissionController {
    @Resource
    private PermissionMapper permissionMapper;

    @GetMapping("api/allPermission")
    public List<Permission> allPermission(){
        return permissionMapper.findAll();
    }

    @GetMapping("api/onePermission")
    public Permission onePermission(){
        return permissionMapper.selectByPrimaryKey(1L);
    }

    @GetMapping("api/countPermission")
    public int  insertPermission(){
        return permissionMapper.countPermission();
    }

    @GetMapping("/hi/world")
    public String hi(){
        return "hello world";
    }

    @GetMapping("/test/test")
    public String test(){
        return "test";
    }
}
