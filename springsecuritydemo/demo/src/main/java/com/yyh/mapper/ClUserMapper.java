package com.yyh.mapper;

import com.yyh.model.ClUser;

import java.util.List;

public interface ClUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClUser record);

    int insertSelective(ClUser record);

    ClUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClUser record);

    int updateByPrimaryKey(ClUser record);

    ClUser findClUser(ClUser record);

    List<ClUser> findAll();

    ClUser  findByUsername(String username);
}