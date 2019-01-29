package com.yyh.mapper;


import com.yyh.dto.ClUser;

public interface ClUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ClUser record);

    int insertSelective(ClUser record);

    ClUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClUser record);

    int updateByPrimaryKey(ClUser record);
}