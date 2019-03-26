package com.design.background.mapper;

import com.design.background.entity.Tcode;

public interface TcodeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Tcode record);

    int insertSelective(Tcode record);

    Tcode selectByPrimaryKey(Long id);

    Integer updateByPrimaryKeySelective(Tcode record);

    Integer updateByPrimaryKey(Tcode record);
    
    Tcode selectByEmail(String email);
    
    Tcode selectByCode(Tcode record);

    Integer selectByPhoneNumber(String phoneNumber);
    
    Integer selectDesignVerificationCode(String phoneNumber);

}