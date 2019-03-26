package com.design.background.mapper;

import com.design.background.entity.UserAccount;

public interface UserAccountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserAccount record);

    int insertSelective(UserAccount record);

    UserAccount selectByPrimaryKey(Long id);
	
	UserAccount selectByUserId(Long userId);

    UserAccount findByUserId(long id);

    int updateByPrimaryKeySelective(UserAccount record);

    int updateByPrimaryKey(UserAccount record);
	
	int update(UserAccount record);
}