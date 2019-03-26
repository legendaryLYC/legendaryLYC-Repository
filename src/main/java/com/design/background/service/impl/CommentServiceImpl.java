package com.design.background.service.impl;

import com.design.background.entity.Comment;
import com.design.background.mapper.CommentMapper;
import com.design.background.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: CommentServiceImpl
 * @Description: TODO
 * @Author: HRX
 * @Date: 2019/3/9 14:50
 **/
@Service("commentService")
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return commentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Comment record) {
        return commentMapper.insert(record);
    }

    @Override
    public int insertSelective(Comment record) {
        return commentMapper.insertSelective(record);
    }

    @Override
    public Comment selectByPrimaryKey(Long id) {
        return commentMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Comment record) {
        return commentMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Comment record) {
        return commentMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Comment> selectAll(String projectName) {
        return commentMapper.selectAll(projectName);
    }

    @Override
    public List<Comment> selectAllRecord() {
        return commentMapper.selectAllRecord();
    }

    @Override
    public Comment selectById(Long id) {
        return commentMapper.selectById(id);
    }
}
