package com.yyh.mapper;


import com.yyh.model.ClRole;

import java.util.List;

public interface ClRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClRole record);

    int insertSelective(ClRole record);

    ClRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClRole record);

    int updateByPrimaryKey(ClRole record);

    List<ClRole> selectAllRole();
}