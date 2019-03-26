package com.design.background.mapper;

import java.util.List;

import com.design.background.entity.DicReviewStatus;

public interface DicReviewStatusMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DicReviewStatus record);

    int insertSelective(DicReviewStatus record);

    DicReviewStatus selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DicReviewStatus record);

    int updateByPrimaryKey(DicReviewStatus record);
    
    /**
     * @author 获取字典全部数据
     * @return List<DicReviewStatus>
     */
    List<DicReviewStatus> selectReviewStatus();
}