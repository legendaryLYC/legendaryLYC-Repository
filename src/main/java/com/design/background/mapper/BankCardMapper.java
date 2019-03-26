package com.design.background.mapper;

import java.util.List;

import com.design.background.entity.BankCard;

public interface BankCardMapper {
    int deleteByPrimaryKey(Long id);
    
    boolean deleteById(Long id);

    int insert(BankCard record);
    
    boolean insertCard(BankCard record);

    int insertSelective(BankCard record);

    BankCard selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BankCard record);

    int updateByPrimaryKey(BankCard record);
    
    /**
     * 根据userId查询搜索信息
     * @param userId
     * @return
     */
    List<BankCard> selectByUserId(Long userId);
}