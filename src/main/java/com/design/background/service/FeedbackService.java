package com.design.background.service;

import com.design.background.entity.Feedback;
import com.design.background.entity.FeedbackWithBLOBs;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author： 李紫林
 * @Date： 2019/2/25
 * @Description： 用来处理用户反馈的service
 */

public interface FeedbackService {
    int deleteByPrimaryKey(Long id);

    int insert(FeedbackWithBLOBs record);

    int insertSelective(FeedbackWithBLOBs record);

    FeedbackWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FeedbackWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(FeedbackWithBLOBs record);

    int updateByPrimaryKey(Feedback record);

    default List<FeedbackWithBLOBs> selectAllFeedback(String feedbackTitle, Date start, Date end, Date handleStartDate, Date handleEndDate) {
        return null;
    }
}
