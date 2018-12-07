package com.yyh.mapper;

import com.yyh.domain.ClSortRiskLog;

public interface ClSortRiskLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClSortRiskLog record);

    int insertSelective(ClSortRiskLog record);

    ClSortRiskLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClSortRiskLog record);

    int updateByPrimaryKey(ClSortRiskLog record);
}