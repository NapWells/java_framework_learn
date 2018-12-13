package com.yyh.model;

import lombok.Data;

/**
 * @author lhy
 * @Date 2018/10/19 15:29
 * @JDK 1.7
 * @Description TODO
 */
@Data
public class Permission {
    private Integer id;
    //权限名称
    private String name;

    //权限描述
    private String description;

    //授权链接
    private String url;

    //父节点id
    private Integer pid;
}
