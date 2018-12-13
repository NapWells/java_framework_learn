package com.yyh.mapper;


import com.yyh.model.ClResource;

import java.util.List;

public interface ClResourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ClResource record);

    int insertSelective(ClResource record);

    ClResource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClResource record);

    int updateByPrimaryKey(ClResource record);

    List<ClResource> selectResourceByPermissionId(Integer permissionId);

    List<ClResource> findAll();
}