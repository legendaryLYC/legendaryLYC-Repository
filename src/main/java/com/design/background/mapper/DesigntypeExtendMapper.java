package com.design.background.mapper;

import com.design.background.entity.DesigntypeExtend;

public interface DesigntypeExtendMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DesigntypeExtend record);

    int insertSelective(DesigntypeExtend record);

    DesigntypeExtend selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DesigntypeExtend record);

    int updateByPrimaryKey(DesigntypeExtend record);
    
    //删除设计师扩展表
    int deleteByuserid(Long userId);
    
    //搜索设计师扩展表
    DesigntypeExtend selectByUserid(Long userid);
}