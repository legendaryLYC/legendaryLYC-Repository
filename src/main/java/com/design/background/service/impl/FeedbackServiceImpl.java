package com.design.background.service.impl;

import com.design.background.entity.Feedback;
import com.design.background.entity.FeedbackWithBLOBs;
import com.design.background.mapper.FeedbackMapper;
import com.design.background.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author： 李紫林
 * @Date： 2019/2/25
 * @Description：
 */
@Service("feedbackService")
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    private FeedbackMapper feedbackMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return feedbackMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(FeedbackWithBLOBs record) {
        return feedbackMapper.insert(record);
    }

    @Override
    public int insertSelective(FeedbackWithBLOBs record) {
        return feedbackMapper.insertSelective(record);
    }

    @Override
    public FeedbackWithBLOBs selectByPrimaryKey(Long id) {
        return feedbackMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(FeedbackWithBLOBs record) {
        return feedbackMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(FeedbackWithBLOBs record) {
        return feedbackMapper.updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public int updateByPrimaryKey(Feedback record) {
        return feedbackMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<FeedbackWithBLOBs> selectAllFeedback(String feedbackTitle, Date start, Date end, Date handleStartDate, Date handleEndDate) {
        return feedbackMapper.selectAllFeedback(feedbackTitle,start,end,handleStartDate,handleEndDate);
    }
}
