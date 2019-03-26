package com.design.background.service.impl;

import com.design.background.entity.Friendship;
import com.design.background.mapper.FriendshipMapper;
import com.design.background.service.FriendShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("friendShipService")
public class FriendShipServiceImpl implements FriendShipService {

    @Autowired

    private FriendshipMapper friendshipMapper;

    @Override
    public List <Friendship> selectList() {
        List<Friendship> result = friendshipMapper.selectList();
        return result;
    }

    @Override
    public boolean insert(String name,String link) {
        boolean flag = false;
        try {
            flag = friendshipMapper.insert(name,link);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean deleteList(Long id) {
        boolean flag=false;
        try {
            flag = friendshipMapper.deleteList(id);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
    @Override
    public int updateByPrimaryKeySelective(Long id,String name,String link) {
        return friendshipMapper.updateByPrimaryKeySelective(id,name,link);
    }
    @Override
    public Friendship selectByPrimaryKey(Long id){
        return friendshipMapper.selectByPrimaryKey(id);
    }
    @Override
    public Friendship selectbyLink(String name,String link) {
        Friendship friendship = friendshipMapper.selectbyLink(name,link);
        return friendship;
    }
}
