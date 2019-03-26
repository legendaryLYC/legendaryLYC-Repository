package com.design.background.mapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.design.background.entity.ProjectDetail;
import com.design.background.form.BasicProjectForm;
import com.design.background.form.ProjectForm;
import com.design.background.model.MyProjectModel;
import com.design.background.model.ScreeningConditions;
import com.design.background.model.StatisticalSearchUserBalanceModel;
import com.design.background.model.StatisticalSearchUserCountModel;
import com.design.background.model.StatisticalSearchUserModel;

public interface ProjectDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProjectDetail record);

    int insertSelective(ProjectDetail record);

    ProjectDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProjectDetail record);

    int updateByPrimaryKey(ProjectDetail record);
    
    /**
     * 条件筛选符合条件的项目
     * @author 孟晓冬
     */
    List<ProjectForm> selectProjectsSelective(
    		@Param(value="projectDetail")ProjectDetail projectDetail,
    		@Param(value="from")Date from,
    		@Param(value="to")Date to);
    /**
    * @Author: 李永成
    * @Date: 2019/2/16
    * @Description:  查询详细项目信息显示在前台
    * @Param:  ProjectDetail projectDetail
    * @return:  ProjectForm project
    */
    ProjectForm selectProjectFormById(
            @Param(value="projectDetail")ProjectDetail projectDetail);
    /**
     * 条件筛选符合条件的项目简略信息+封面列表
     * @author 孟晓冬
     */
    List<BasicProjectForm> selectBasicProjectsSelective(@Param(value="statuses")int[] statuses,
    		@Param(value="screeningConditions")ScreeningConditions screeningConditions,
    		@Param(value="projectDetail")ProjectDetail projectDetail);
    
    /**
     * 
    * @Author HRX 
    * @Title: selectByUserId  
    * @Description: TODO 根据userId查询项目
    * @param @param id
    * @param @return   
    * @return List<MyProjectModel>    
    * @Date 2019年2月16日 下午11:08:20
    * @throws
     */
    List<MyProjectModel> selectByUserId(ProjectDetail project);


	/**
	 *
	 * @Author HRX
	 * @Title: selectByUserIdDraft
	 * @Description: TODO 根据userId查询草稿项目
	 * @param @param id
	 * @param @return
	 * @return List<MyProjectModel>
	 * @Date 2019年2月16日 下午11:08:20
	 * @throws
	 */
	List<MyProjectModel> selectByUserIdDraft(ProjectDetail project);
    
    /**
     * 
    * @Author HRX 
    * @Title: selectCounts  
    * @Description: TODO 查询属于当前用户的项目个数
    * @param @return   
    * @return Integer    
    * @Date 2019年2月17日 上午12:22:26
    * @throws
     */
    Integer selectCounts(Long userId);
    
    /**
 	 * @description 查找出所有新建项目、在建项目、结项项目项目的数量
 	 * @return List<StatisticalSearchUserModel>
 	 * @author 周天
 	 */
 	List<StatisticalSearchUserModel> selectStatisticalProjectAllTime();
 	
 	/**
 	 * @description 查找出一个周新建项目、在建项目、结项项目项目的数量
 	 * @return List<StatisticalSearchProjectModel>
 	 * @author 周天
 	 */
 	List<StatisticalSearchUserModel> selectStatisticalProjectOneWeek();
 	
 	/**
 	 * @description 查找出一个月内新建项目、在建项目、结项项目项目的数量
 	 * @return List<StatisticalSearchUserModel>
 	 * @author 周天
 	 */
 	List<StatisticalSearchUserModel> selectStatisticalProjectOneMonth();
 	
 	/**
 	 * @description 查找出项目数量最多前十的城市
 	 * @return StatisticalSearchUserBalanceModel
 	 * @author 周天
 	 */
 	List<StatisticalSearchUserBalanceModel> selectStatisticalProjectTopCity();
 	
 	/**
 	 * @description 查找完成项目量的前十用户
 	 * @return List<StatisticalSearchUserCountModel>
 	 * @author 周天
 	 */
 	 List<StatisticalSearchUserCountModel> selectStatisticalProjectTopBalance();
 	 
 	/**
 	 * @description 查找出上周内每天新建项目、在建项目、结项项目项目的数量
 	 * @return List<StatisticalSearchUserModel>
 	 * @author 周天
 	 */
 	List<StatisticalSearchUserModel> selectStatisticalProjectLastweek(List<Map<String,Object>> list);
 	
 	/**
 	 * @description 查找出这周每天内新建项目、在建项目、结项项目项目的数量
 	 * @return List<StatisticalSearchUserModel>
 	 * @author 周天
 	 */
 	List<StatisticalSearchUserModel> selectStatisticalProjectThisweek(List<Map<String,Object>> list);
 	
 	/**
 	 * @description 查找全部的收入
 	 * @return BigDecimal
 	 * @author 周天
 	 */
 	BigDecimal selectStatisticalAllCaptial();
 	
 	/**
 	 * @description 查找这个月的收入
 	 * @return BigDecimal
 	 * @author 周天
 	 */
 	BigDecimal selectStatisticalMonthCaptial();
 	
 	/**
 	 * @description 查找上周的收入
 	 * @return BigDecimal
 	 * @author 周天
 	 */
 	Map<String,BigDecimal> selectStatisticalLastWeekCaptial(List<Map<String,Object>> list);
 	
 	/**
 	 * @description 查找这周的收入
 	 * @return BigDecimal
 	 * @author 周天
 	 */
	Map<String,BigDecimal> selectStatisticalThisWeekCaptial(List<Map<String,Object>> list);
    
}