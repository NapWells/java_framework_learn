package com.yyh.mapper;

import com.yyh.model.Permission;

/**
 * @author lhy
 * @Date 2018/10/22 10:59
 * @JDK 1.7
 * @Description TODO
 */
public interface BaseMapper<T> {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    int insertSelective(T record);

    T selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);
}
