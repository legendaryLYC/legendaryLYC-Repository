package com.design.background.service;

import java.util.List;

import com.design.background.entity.DesignerRelationship;
import com.design.background.form.DesignerRelationshipForm;

/**
 * 设计师投标记录相关服务
 * @author 孟晓冬
 */
public interface DesignerRelationshipService {

	/**
	 * 传入项目id获取该项目未确定的设计师种类code值
	 * @author 孟晓冬
	 */
	Integer[] getUnselectedDesigners(Long projectId);

    int deleteByPrimaryKey(Long id);

    int insert(DesignerRelationship record);

    int insertSelective(DesignerRelationship record);

    DesignerRelationship selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DesignerRelationship record);

    int updateByPrimaryKey(DesignerRelationship record);

    int deleteByUserIdAndProjectId(Long userId,Long projectId); // 取消报名

    /**
     * 用来判断用户是否报名此项目
     */
    DesignerRelationship selectJoinStatus(Long userId, Long projectId);

    /**
     * 给阶段验收的项目发送消息
     */
    boolean examineProject(Long projectId,String message);
    
    /**
    * @Author: 李永成
    * @Date: 2019/2/25 
    * @Description:  查出该项目是否选了带头人
    * @Param:  Long projectId
    * @return:  count
    */ 
    int selectCountByProjectId(Long projectId);

    /* 获取该项目的所有报名设计师
     * @author 孟晓冬
     * @param projectId
     * @return
     */
    List<DesignerRelationshipForm> selectDesignersByProjectId(Long projectId);
    
    /* 由component_code和项目id获取还未选择的设计师数组
     * @author 孟晓冬
     * @param projectId, String component
     * @return
     */
    Integer[] getUnselectedDesignersByProjectIdAndComponent(
    		String component, Long projectId);
}
