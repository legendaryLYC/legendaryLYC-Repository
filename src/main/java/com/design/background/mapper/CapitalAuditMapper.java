package com.design.background.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.design.background.entity.Transaction;
import org.apache.ibatis.annotations.Param;

import com.design.background.entity.CapitalAudit;

public interface CapitalAuditMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CapitalAudit record);

    int insertSelective(CapitalAudit record);

    CapitalAudit selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CapitalAudit record);

    int updateByPrimaryKey(CapitalAudit record);

    int insertSelective(Transaction transaction);
    
    
    
    // 查询所有信息
    List<CapitalAudit> selectAll();
    
    /**
     * 根据name查询审核资金
     * @author 王喜壮
     * @return
     */
    
    List<CapitalAudit> select(CapitalAudit capitalAudit);
    /**
     * 通过id修改拒绝原因和状态
     * @param record
     * @return
     */
	int update(CapitalAudit record);
	
 	
 	/**
 	 * @description 查找上周的支出
 	 * @return BigDecimal
 	 * @param lastIndex 查询的那天的参数
 	 * @author 周天
 	 */
	Map<String,BigDecimal> selectStatisticalLastWeekExpend(List<Map<String,Object>> list);
 	
 	/**
 	 * @description 查找这周的支出
 	 * @return BigDecimal
 	 * @param thisIndex 查询的那天的参数
 	 * @author 周天
 	 */
	Map<String,BigDecimal> selectStatisticalThisWeekExpend(List<Map<String,Object>> list);
}