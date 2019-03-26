package com.design.background.service;

import java.util.List;

import com.design.background.entity.*;

/**
 * @Author： 孟晓冬
 * @Date： 2019/2/13
 * @Description： 字典相关Service
 */
public interface DicService {
	
	/**
	 * 获取阶段审核状态字典
	 * @param 
	 * @return
	 */
	List<DicReviewStatus> getReviewStatus();
	
	/**
	 * 获取工程规模字典
	 * @param 
	 * @return
	 */
	List<DicProjectScale> getModels();

	/**
	 * 获取工程类型字典
	 * @param
	 * @return
	 */
	List<DicProjectType> getTypes();

	/**
	 * 获取工程费用字典
	 * @param
	 * @return
	 */
	List<DicProjectFee> getFees();

	/**
	 * 获取工程工期字典
	 * @param
	 * @return
	 */
	List<DicProjectPeriod> getPeriods();

	/**
	 * 获取工程阶段字典
	 * @param 
	 * @return
	 */
	List<DicProjectStage> getStages();

	/**
	 * 获取组建分类字典
	 * @param
	 * @return
	 */
	List<DicProjectComponent> getComponents();
	
	/**
	 * 获取项目分派方式字典
	 * @param 
	 * @return
	 */
	List<DicDispatch> getDispatchs();
	
	/**
	 * 获取工程状态字典
	 * @param 
	 * @return
	 */
	List<DicProjectStatus> getProcess();
	
	/**
	 * 获取地区字典
	 * @param 
	 * @return
	 */
	List<DicArea> getAreas();
	
	/**
	 * 获取城市字典
	 * @param 
	 * @return
	 */
	List<DicCity> getCitys();
	
	/**
	 * 获取省字典
	 * @param 
	 * @return
	 */
	List<DicProvince> getProvinces();
	
	/**
	 * 选择某省份的所有市
	 */
	List<DicCity> getCitysByProvinceId(String id);
	

	
	/**
	 * 选择某城市的所有区
	 */
	List<DicArea> getAreaByCityId(String id);
	
	/**任松
	 * 获取角色字典
	 * 
	 * @return
	 */
	List<DicRole>getRoles();
	
	/**
	 * 获取设计师费率
	 * @param id
	 * @return
	 */
	DicDefaultRate selectRateByPrimaryKey(Long id);
	
	/**
	 * 根据项目id获取项目详情
	 * @param id
	 * @return
	 */
	ProjectDetail selectProjectByPrimaryKey(Long id);
	/**
	 * 插入支付薪酬记录
	 * @param record
	 * @return
	 */
	int insertSelective(MoneyDistribution record);
	
    MoneyDistribution selectbyStage(long projectId,int stageCode);

    MoneyDistribution findbyStage(MoneyDistribution MoneyDistribution);
    
    /**
	 * 根据id获取字典项
	 * @param id
	 * @return
	 */
    DicCity selectCityById(String id);
}
