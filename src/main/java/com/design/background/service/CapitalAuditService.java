package com.design.background.service;

import java.util.List;

import com.design.background.entity.CapitalAudit;
import com.design.background.entity.SysUser;
import com.design.background.entity.Transaction;

/**
 * @Author： 王喜壮
 * @Date： 2019/2/13
 * @Description：资金审核service层
 */

public interface CapitalAuditService {

	
	 /**
     * 查询所有资金审核信息
     * @author 王喜壮
     * @return
     */
	
	List<CapitalAudit> selectAll();
    
	 /**
     * 根据name查询审核资金
     * @author 王喜壮
     * @return
     */ 
    List<CapitalAudit> select(CapitalAudit capitalAudit);
    /**
     * 通过id查找信息
     * @param id
     * @return
     */
    CapitalAudit selectById(Long id);
    /**
     * 通过id修改信息
     * @param record
     * @return
     */
    int updateById(CapitalAudit record);
    /**
     * 通过id修改拒绝原因和状态
     * @param record
     * @return
     */
    int update(CapitalAudit record);

    int insertSelective(Transaction transaction);
    
    /**
     * 通过申请后该变余额的服务
     * @author 孟晓冬
     * @return
     */
    Boolean applicationApproved(Long auditId);
}
