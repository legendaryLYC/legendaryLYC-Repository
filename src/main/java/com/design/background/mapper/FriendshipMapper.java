package com.design.background.mapper;

import com.design.background.entity.Friendship;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FriendshipMapper {
    int deleteByPrimaryKey(Long id);

    boolean insert(@Param(value = "name")String name, @Param(value = "link")String link);

    boolean deleteList(@Param(value = "id")Long id);

    int insertSelective(Friendship record);

    Friendship selectbyLink(@Param(value = "name")String name, @Param(value = "link")String link);

    List<Friendship> selectList();

    int updateByPrimaryKeySelective(@Param(value = "id")Long id,@Param(value = "name")String name, @Param(value = "link")String link);

    int updateByPrimaryKey(Friendship record);

    Friendship selectByPrimaryKey(Long id);
}