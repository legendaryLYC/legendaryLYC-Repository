package com.design.background.mapper;

import com.beust.jcommander.Parameter;
import com.design.background.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    List<Comment> selectAll(@Param("projectName") String projectName);

    List<Comment> selectAllRecord();

    Comment selectById(Long id);
}