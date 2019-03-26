package com.design.background.mapper;

import com.design.background.entity.Feedback;
import com.design.background.entity.FeedbackWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface FeedbackMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FeedbackWithBLOBs record);

    int insertSelective(FeedbackWithBLOBs record);

    FeedbackWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FeedbackWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(FeedbackWithBLOBs record);

    int updateByPrimaryKey(Feedback record);

    List<FeedbackWithBLOBs> selectAllFeedback(@Param("feedbackTitle") String feedbackTitle,@Param("start") Date start,@Param("end") Date end,@Param("handleStart") Date handleStartDate,@Param("handleEnd") Date handleEndDate);
}