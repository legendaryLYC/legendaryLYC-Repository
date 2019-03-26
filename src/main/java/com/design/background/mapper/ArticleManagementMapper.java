package com.design.background.mapper;

import java.util.List;

import com.design.background.entity.ArticleManagement;

public interface ArticleManagementMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ArticleManagement record);

    int insertSelective(ArticleManagement record);

    ArticleManagement selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ArticleManagement record);

    int updateByPrimaryKeyWithBLOBs(ArticleManagement record);

    int updateByPrimaryKey(ArticleManagement record);
    
    List<ArticleManagement> selectAll();
}