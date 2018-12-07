package com.yyh.mapper;

import com.yyh.model.Permission;

import java.util.List;

/**
 * @author lhy
 * @Date 2018/10/19 15:31
 * @JDK 1.7
 * @Description TODO
 */

public interface PermissionMapper{

    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    List<Permission> findAll();

    List<Permission> findByAdminUserId(int userId);

    int countPermission();
}
