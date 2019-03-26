package com.design.background.service;

import com.design.background.entity.Friendship;

import java.util.List;


public interface FriendShipService {

    List<Friendship> selectList ();

    boolean insert(String name,String link);

    boolean deleteList(Long id);

    Friendship selectbyLink(String name,String link);  //通过信息查找链接

    int updateByPrimaryKeySelective(Long id,String name,String link);

    Friendship selectByPrimaryKey(Long id);
}
