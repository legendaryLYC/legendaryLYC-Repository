package com.design.background.service;

import com.design.background.entity.Comment;

import java.util.List;

/**
 * @ClassName: CommentService
 * @Description: TODO
 * @Author: HRX
 * @Date: 2019/3/9 14:49
 **/
public interface CommentService {

    int deleteByPrimaryKey(Long id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    List<Comment> selectAll(String projectName);

    List<Comment> selectAllRecord();

    Comment selectById(Long id);

}
