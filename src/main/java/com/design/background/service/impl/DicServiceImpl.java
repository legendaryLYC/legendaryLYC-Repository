package com.design.background.service.impl;

import java.util.List;

import com.design.background.entity.*;
import com.design.background.mapper.*;
import com.design.background.service.DicProjectScaleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.design.background.service.DicService;

/**
 * @Author： 孟晓冬
 * @Date： 2019/2/13
 * @Description： 字典相关Service
 */
@Service
public class DicServiceImpl implements DicService {

	@Autowired
	private DicStageMapper dicStageMapper;
	@Autowired
	private DicDefaultRateMapper dicDefaultRateMapper;
	@Autowired
	private DicModelMapper dicModelMapper;
	@Autowired
	private DicProjectFeeMapper dicFeeMapper;
	@Autowired
	private DicProjectComponentMapper dicComponentMapper;
	@Autowired
	private DicProjectPeriodMapper dicPeriodMapper;
	@Autowired
	private DicProjectTypeMapper dicTypeMapper;
	@Autowired
	private DicDispatchMapper dicDispatchMapper;
	@Autowired
	private DicProjectStatusMapper dicProjectStatusMapper;
	@Autowired
	private DicAreaMapper dicAreaMapper;
	@Autowired
	private DicProvinceMapper dicProvinceMapper;
	@Autowired
	private DicCityMapper dicCityMapper;
	@Autowired
	private DicRoleMapper dicRoleMapper;
	@Autowired
	private DicReviewStatusMapper dicReviewStatusMapper;
	@Autowired
	ProjectDetailMapper projectDetailMapper;
	@Autowired
	MoneyDistributionMapper moneyDistributionMapper;
	@Autowired
	DicProjectScaleService dicProjectScaleService;

	private static final Logger logger = LoggerFactory.getLogger(DicServiceImpl.class);
	
	@Override
	public List<DicProjectScale> getModels() {
		List<DicProjectScale> models = null;
		try {
			models = dicProjectScaleService.getModels();
		} catch (Exception e) {
			logger.error("获取规模字典失败!"+e.getMessage());
		}
		return models;
	}

	@Override
	public List<DicProjectFee> getFees() {
		List<DicProjectFee> fees = null;
		try {
			fees = dicFeeMapper.selectAll();
		} catch (Exception e) {
			logger.error("获取规模字典失败!"+e.getMessage());
		}
		return fees;
	}

	@Override
	public List<DicProjectPeriod> getPeriods() {
		List<DicProjectPeriod> periods = null;
		try {
			periods = dicPeriodMapper.selectAll();
		} catch (Exception e) {
			logger.error("获取规模字典失败!"+e.getMessage());
		}
		return periods;
	}

	@Override
	public List<DicProjectComponent> getComponents() {
		List<DicProjectComponent> components = null;
		try {
			components = dicComponentMapper.selectAll();
		} catch (Exception e) {
			logger.error("获取规模字典失败!"+e.getMessage());
		}
		return components;
	}

	@Override
	public List<DicProjectType> getTypes() {
		List<DicProjectType> types = null;
		try {
			types = dicTypeMapper.selectAll();
		} catch (Exception e) {
			logger.error("获取类型字典失败!"+e.getMessage());
		}
		return types;
	}

	@Override
	public List<DicProjectStage> getStages() {
		List<DicProjectStage> stages = null;
		try {
			stages = dicStageMapper.selectAll();
		} catch (Exception e) {
			logger.error("获取项目状态字典失败!"+e.getMessage());
		}
		return stages;
	}

	@Override
	public List<DicDispatch> getDispatchs() {
		List<DicDispatch> dispath = null;
		try {
			dispath = dicDispatchMapper.selectAll();
		} catch (Exception e) {
			logger.error("获取项目分派方式字典失败!"+e.getMessage());
		}
		return dispath;
	}

	@Override
	public List<DicProjectStatus> getProcess() {
		List<DicProjectStatus> status = null;
		try {
			status = dicProjectStatusMapper.selectAll();
		} catch (Exception e) {
			logger.error("获取项目状态字典失败!"+e.getMessage());
		}
		return status;
	}

	@Override
	public List<DicArea> getAreas() {
		List<DicArea> areas = null;
		try {
			areas = dicAreaMapper.selectAllArea();
		} catch (Exception e) {
			logger.error("获取项目地区字典失败!"+e.getMessage());
		}
		return areas;
	}

	@Override
	public List<DicProvince> getProvinces() {
		List<DicProvince> provinces = null;
		try {
			provinces = dicProvinceMapper.selectAllProvince();
		} catch (Exception e) {
			logger.error("获取省字典失败!"+e.getMessage());
		}
		return provinces;
	}

	@Override
	public List<DicCity> getCitysByProvinceId(String id) {
		List<DicCity> citys = null;
		try {
			citys = dicCityMapper.selectCityWithProvince(id);
		} catch (Exception e) {
			logger.error("获取省份的城市字典失败!"+e.getMessage());
		}
		return citys;
	}

	@Override
	public List<DicArea> getAreaByCityId(String id) {
		List<DicArea> areas = null;
		try {
			areas = dicAreaMapper.selectAreaWithCity(id);
		} catch (Exception e) {
			logger.error("获取城市的地区字典失败!"+e.getMessage());
		}
		return areas;
	}
	/**
	 * 任松
	 * 获取角色字典
	 */
	@Override
	public List<DicRole> getRoles() {
		List<DicRole> roles = null;
		try {
			roles = dicRoleMapper.selectAllRoles();
		} catch (Exception e) {
			logger.error("获取角色字典失败!"+e.getMessage());
		}
		return roles;
	}

	@Override
	public List<DicReviewStatus> getReviewStatus() {
		List<DicReviewStatus> reviews = null;
		try {
			reviews = dicReviewStatusMapper.selectReviewStatus();
		} catch (Exception e) {
			logger.error("获取阶段审核状态字典失败!"+e.getMessage());
		}
		return reviews;
	}
/**
 * 
 * 任松
 * 获取设计师费率
 */
	@Override
	public DicDefaultRate selectRateByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return  dicDefaultRateMapper.selectByPrimaryKey(id);
	}
/**
 * 任松
 * 根据项目ID获取项目
 */
	@Override
	public ProjectDetail selectProjectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return  projectDetailMapper.selectByPrimaryKey(id);
	}
	/**任松
	 * 插入
	 */
	@Override
	public int insertSelective(MoneyDistribution record) {
		// TODO Auto-generated method stub
		
		return moneyDistributionMapper.insertSelective(record);
	}
	/**
	 * 任松
	 * 根据项目id和stageCode筛选记录
	 */
	@Override
	public MoneyDistribution selectbyStage(long projectId, int stageCode) {
		// TODO Auto-generated method stub
		return moneyDistributionMapper.selectByStage(projectId,stageCode);
	}
	@Override
	public MoneyDistribution findbyStage(MoneyDistribution MoneyDistribution) {
		// TODO Auto-generated method stub
		return moneyDistributionMapper.findbyStage(MoneyDistribution);
	}

	@Override
	@Cacheable(value = "default",keyGenerator = "classKey")
	public List<DicCity> getCitys() {
		List<DicCity> citys = null;
		try {
			citys = dicCityMapper.selectAllCity();
		} catch (Exception e) {
			logger.error("获取城市字典失败!"+e.getMessage());
		}
		return citys;
	}

	@Override
	public DicCity selectCityById(String id) {
		DicCity city = null;
		try {
			city = dicCityMapper.selectCityById(id);
		} catch (Exception e) {
			logger.error("获取城市失败!"+e.getMessage());
		}
		return city;
	}
}
