package com.design.background.common.util;

import com.design.background.config.FrontConfig;
import com.design.background.entity.*;
import com.design.background.mapper.DicAreaMapper;
import com.design.background.mapper.DicCityMapper;
import com.design.background.mapper.DicProjectComponentMapper;
import com.design.background.mapper.DicProjectFeeMapper;
import com.design.background.mapper.DicProjectPeriodMapper;
import com.design.background.mapper.DicProjectScaleMapper;
import com.design.background.mapper.DicProjectStageMapper;
import com.design.background.mapper.DicProjectStatusMapper;
import com.design.background.mapper.DicProjectTypeMapper;
import com.design.background.service.FriendShipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * 数据缓存
 * @description:
 * @author: wpw
 * @date: 2019-02-26 上午10:41
 */
@Component
@Service("DesignDataBaseCache")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class DesignDataBaseCache {

    private static final Logger logger = LoggerFactory.getLogger(DesignDataBaseCache.class);

    @Autowired
    private DicProjectScaleMapper projectScaleMapper; // 工程规模
    @Autowired
    private DicProjectTypeMapper projectTypeMapper; // 工程类型
    @Autowired
    private DicProjectFeeMapper projectFeeMapper; // 项目大厅筛选条件中工程费用字典
    @Autowired
    private DicProjectPeriodMapper projectPeriodMapper; // 项目大厅筛选条件中工程期限字典
    @Autowired
    private DicProjectStageMapper projectStageMapper; // 项目阶段字典
    @Autowired
    private DicProjectComponentMapper projectComponentMapper; // 组建分类字典
    @Autowired
    private DicAreaMapper areaMapper; // 地区字典
    @Autowired
    private DicCityMapper cityMapper; // 城市字典
    @Autowired
    private DicProjectStatusMapper projectStatusMapper; // 项目状态字典表


    
    /**
     * 工程规模字典数据
     * key:工程规模code值,value:工程规模
     */
    public static Map<Integer, DicProjectScale> projectScaleMap = new HashMap<Integer, DicProjectScale>();
    /**
     * 工程类型字典数据
     * key:工程类型code值,value:工程类型
     */
    private static Map<Integer, DicProjectType> projectTypeMap = new HashMap<Integer, DicProjectType>();
    /**
     * 工程费用字典数据
     * key:工程费用code值,value:工程费用
     */
    private static Map<Integer, DicProjectFee> projectFeeMap = new HashMap<Integer, DicProjectFee>();
    /**
     * 工程期限字典数据
     * key:工程期限code值,value:工程期限
     */
    private static Map<Integer, DicProjectPeriod> projectPeriodMap = new HashMap<Integer, DicProjectPeriod>();
    /**
     * 工程阶段字典数据
     * key:工程阶段code值,value:工程阶段
     */
    private static Map<Integer, DicProjectStage> projectStageMap = new HashMap<Integer, DicProjectStage>();
    /**
     * 组建分类字典数据
     * key:组建分类code值,value:组建分类
     */
    private static Map<Integer, DicProjectComponent> projectComponentMap = new HashMap<Integer, DicProjectComponent>();
    /**
     * 地区字典数据
     * key:地区id值,value:地区实体类
     */
    private static Map<String, DicArea> areaMap = new HashMap<String, DicArea>();
    /**
     *  城市字典数据
     * key:城市id值,value:地区实体类
     */
    private static Map<String, DicCity> cityMap = new HashMap<String, DicCity>();
    /**
     * 项目状态字典数据
     * key: 项目状态code值,value: 项目状态
     */
    private static Map<Integer, DicProjectStatus> projectStatusMap = new HashMap<Integer, DicProjectStatus>();

    @PostConstruct
    public void init() {
        // 初始化文件处理配置
        initProjectScale();
        initProjectType();
        initProjectFee();
        initProjectPeriod();
        initProjectStage();
        initProjectComponent();
        initArea();
        initCity();
        initProjectStatus();

    }
    /**
     * 初始化项目状态字典表
     */
    private void initProjectStatus() {
        if(!projectStatusMap.isEmpty()){
        	projectStatusMap.clear();
        }
        List<DicProjectStatus> projectStatusList = projectStatusMapper.selectAll();
        if(projectStatusList != null && projectStatusList.size() > 0){
        	projectStatusList.forEach(pStatus->{
            	projectStatusMap.put(pStatus.getCode(),pStatus);
            });
        }
    }
    /**
     * 初始化地区字典表
     */
    private void initCity() {
        if(!cityMap.isEmpty()){
        	cityMap.clear();
        }
        List<DicCity> cityList = cityMapper.selectAllCity();
        if(cityList != null && cityList.size() > 0){
        	cityList.forEach(city->{
        		cityMap.put(city.getCityId(),city);
            });
        }
    }
    /**
     * 初始化地区字典表
     */
    private void initArea() {
        if(!areaMap.isEmpty()){
        	areaMap.clear();
        }
        List<DicArea> areaList = areaMapper.selectAllArea();
        if(areaList != null && areaList.size() > 0){
        	areaList.forEach(area->{
        		areaMap.put(area.getAreaId(),area);
            });
        }
    }
    /**
     * 初始化组建分类字典表
     */
    private void initProjectComponent() {
        if(!projectComponentMap.isEmpty()){
        	projectComponentMap.clear();
        }
        List<DicProjectComponent> projectComponentList = projectComponentMapper.selectAll();
        if(projectComponentList != null && projectComponentList.size() > 0){
        	projectComponentList.forEach(pComponent->{
            	projectComponentMap.put(pComponent.getCode(),pComponent);
            });
        }
    }
    /**
     * 初始化工程阶段字典表
     */
    private void initProjectStage() {
        if(!projectStageMap.isEmpty()){
        	projectStageMap.clear();
        }
        List<DicProjectStage> projectStageList = projectStageMapper.selectAll();
        if(projectStageList != null && projectStageList.size() > 0){
        	projectStageList.forEach(pStage->{
            	projectStageMap.put(pStage.getCode(),pStage);
            });
        }
    }
    /**
     * 初始化工程期限字典表
     */
    private void initProjectPeriod() {
        if(!projectPeriodMap.isEmpty()){
        	projectPeriodMap.clear();
        }
        List<DicProjectPeriod> projectPeriodList = projectPeriodMapper.selectAll();
        if(projectPeriodList != null && projectPeriodList.size() > 0){
        	projectPeriodList.forEach(pPeriod->{
        		projectPeriodMap.put(pPeriod.getCode(),pPeriod);
            });
        }
    }
    /**
     * 初始化工程费用字典表
     */
    private void initProjectFee() {
        if(!projectFeeMap.isEmpty()){
        	projectFeeMap.clear();
        }
        List<DicProjectFee> projectFeeList = projectFeeMapper.selectAll();
        if(projectFeeList != null && projectFeeList.size() > 0){
        	projectFeeList.forEach(pFee->{
        		projectFeeMap.put(pFee.getCode(),pFee);
            });
        }
    }
    /**
     * 初始化工程类型字典表
     */
    private void initProjectType() {
        if(!projectTypeMap.isEmpty()){
        	projectTypeMap.clear();
        }
        List<DicProjectType> projectTypeList = projectTypeMapper.selectAll();
        if(projectTypeList != null && projectTypeList.size() > 0){
        	projectTypeList.forEach(pType->{
        		projectTypeMap.put(pType.getCode(),pType);
            });
        }
    }
    /**
     * 初始化工程规模字典表
     */
    private void initProjectScale() {
        if(!projectScaleMap.isEmpty()){
            projectScaleMap.clear();
        }
        List<DicProjectScale> projectScaleList = projectScaleMapper.selectAll();
        if(projectScaleList != null && projectScaleList.size() > 0){
            projectScaleList.forEach(pScale->{
                projectScaleMap.put(pScale.getCode(),pScale);
            });
        }
    }
    /**
     * 查询所有项目状态字典信息
     * @return
     */
    public static List<DicProjectStatus> getAllDicProjectStatus(){
        return new ArrayList<DicProjectStatus>(projectStatusMap.values());
    }
    /**
     * 查询所有城市字典信息
     * @return
     */
    public static List<DicCity> getAllDicCity(){
        return new ArrayList<DicCity>(cityMap.values());
    }
    /**
     * 查询所有地区字典信息
     * @return
     */
    public static List<DicArea> getAllDicArea(){
        return new ArrayList<DicArea>(areaMap.values());
    }
    /**
     * 查询所有组建分类字典信息
     * @return
     */
    public static List<DicProjectComponent> getAllDicProjectComponent(){
        return new ArrayList<DicProjectComponent>(projectComponentMap.values());
    }
    /**
     * 查询所有项目阶段字典信息
     * @return
     */
    public static List<DicProjectStage> getAllDicProjectStage(){
        return new ArrayList<DicProjectStage>(projectStageMap.values());
    }
    /**
     * 查询所有项目期限字典信息
     * @return
     */
    public static List<DicProjectPeriod> getAllDicProjectPeriod(){
        return new ArrayList<DicProjectPeriod>(projectPeriodMap.values());
    }
    /**
     * 查询所有项目费用字典信息
     * @return
     */
    public static List<DicProjectFee> getAllDicProjectFee(){
        return new ArrayList<DicProjectFee>(projectFeeMap.values());
    }
    /**
     * 查询所有工程类型字典信息
     * @return
     */
    public static List<DicProjectType> getAllDicProjectType(){
        return new ArrayList<DicProjectType>(projectTypeMap.values());
    }
    /**
     * 查询所有项目规模字典信息
     * @return
     */
    public static List<DicProjectScale> getAllDicProjectScale(){
        return new ArrayList<DicProjectScale>(projectScaleMap.values());
    }
    /**
     * 根据code值查询对应项目状态
     * @param code
     * @return
     */
    public static DicProjectStatus getDicProjectStatus(Integer code){
        return projectStatusMap.containsKey(code)? projectStatusMap.get(code) : null;
    }
    /**
     * 根据cityId值查询对应城市
     * @param cityId
     * @return
     */
    public static DicCity getDicCity(String cityId){
        return cityMap.containsKey(cityId)? cityMap.get(cityId) : null;
    }
    /**
     * 根据areaId值查询对应地区
     * @param areaId
     * @return
     */
    public static DicArea getDicArea(String areaId){
        return areaMap.containsKey(areaId)? areaMap.get(areaId) : null;
    }
    /**
     * 根据cityId值查询对应所有地区
     * @param areaId
     * @return
     */
    public static List<String> getDicAreasByCityId(String cityId){
    	if(cityId == null) {
    		return null;
    	}
    	List<String> result = new ArrayList<>();
    	//查找cityId
    	for(DicArea area : areaMap.values()) {
    		if(cityId.equals(area.getCityId())) {
    			result.add(area.getAreaId());
    		}
    	}
    	return result;
    }
    /**
     * 根据code值查询对应组建分类
     * @param code
     * @return
     */
    public static DicProjectComponent getDicProjectComponent(Integer code){
        return projectComponentMap.containsKey(code)? projectComponentMap.get(code) : null;
    }
    /**
     * 根据code值查询对应工程阶段
     * @param code
     * @return
     */
    public static DicProjectStage getDicProjectStage(Integer code){
        return projectStageMap.containsKey(code)? projectStageMap.get(code) : null;
    }
    /**
     * 根据code值查询对应工程期限
     * @param code
     * @return
     */
    public static DicProjectPeriod getDicProjectPeriod(Integer code){
        return projectPeriodMap.containsKey(code)? projectPeriodMap.get(code) : null;
    }
    /**
     * 根据code值查询对应工程费用
     * @param code
     * @return
     */
    public static DicProjectFee getDicProjectFee(Integer code){
        return projectFeeMap.containsKey(code)? projectFeeMap.get(code) : null;
    }
    /**
     * 根据code值查询对应工程类型
     * @param code
     * @return
     */
    public static DicProjectType getDicProjectType(Integer code){
        return projectTypeMap.containsKey(code)? projectTypeMap.get(code) : null;
    }
    /**
     * 根据code值查询对应工程规模
     * @param code
     * @return
     */
    public static DicProjectScale getDicProjectScale(Integer code){
        return projectScaleMap.containsKey(code)? projectScaleMap.get(code) : null;
    }
    /**
     * 项目状态添加值/更新值
     * @param dicProjectStatus
     * @Description:
     */
    public static void setDicProjectStatus(DicProjectStatus dicProjectStatus) {
        projectStatusMap.put(dicProjectStatus.getCode(),dicProjectStatus);
    }
    /**
     * 城市添加值/更新值
     * @param dicCity
     * @Description:
     */
    public static void setDicCity(DicCity dicCity) {
        cityMap.put(dicCity.getCityId(),dicCity);
    }
    /**
     * 地区添加值/更新值
     * @param dicArea
     * @Description:
     */
    public static void setDicArea(DicArea dicArea) {
        areaMap.put(dicArea.getAreaId(),dicArea);
    }
    /**
     * 组建分类添加值/更新值
     * @param dicProjectComponent
     * @Description:
     */
    public static void setDicProjectComponent(DicProjectComponent dicProjectComponent) {
        projectComponentMap.put(dicProjectComponent.getCode(),dicProjectComponent);
    }
    /**
     * 工程阶段添加值/更新值
     * @param dicProjectStage
     * @Description:
     */
    public static void setDicProjectStage(DicProjectStage dicProjectStage) {
        projectStageMap.put(dicProjectStage.getCode(),dicProjectStage);
    }
    /**
     * 工程期限添加值/更新值
     * @param dicProjectPeriod
     * @Description:
     */
    public static void setDicProjectPeriod(DicProjectPeriod dicProjectPeriod) {
        projectPeriodMap.put(dicProjectPeriod.getCode(),dicProjectPeriod);
    }
    /**
     * 工程费用添加值/更新值
     * @param dicProjectFee
     * @Description:
     */
    public static void setDicProjectFee(DicProjectFee dicProjectFee) {
        projectFeeMap.put(dicProjectFee.getCode(),dicProjectFee);
    }
    /**
     * 工程类型添加值/更新值
     * @param dicProjectType
     * @Description:
     */
    public static void setDicProjectType(DicProjectType dicProjectType) {
        projectTypeMap.put(dicProjectType.getCode(),dicProjectType);
    }
    /**
     * 工程规模添加值/更新值
     * @param dicProjectScale
     * @Description:
     */
    public static void setDicProjectScale(DicProjectScale dicProjectScale) {
        projectScaleMap.put(dicProjectScale.getCode(),dicProjectScale);
    }
    /**
     * 项目状态删除值
     * @param code
     */
    public static void removeDicProjectStatus(Integer code){
        projectStatusMap.remove(code);
    }
    /**
     * 城市删除值
     * @param code
     */
    public static void removeDicCity(String cityId){
        cityMap.remove(cityId);
    }/**
     * 地区删除值
     * @param code
     */
    public static void removeDicArea(String areaId){
        areaMap.remove(areaId);
    }
    /**
     * 组建分类删除值
     * @param code
     */
    public static void removeDicProjectComponent(Integer code){
        projectComponentMap.remove(code);
    }
    /**
     * 项目阶段删除值
     * @param code
     */
    public static void removeDicProjectStage(Integer code){
        projectStageMap.remove(code);
    }
    /**
     * 工程期限删除值
     * @param code
     */
    public static void removeDicProjectPeriod(Integer code){
        projectPeriodMap.remove(code);
    }
    /**
     * 工程费用删除值
     * @param code
     */
    public static void removeDicProjectFee(Integer code){
        projectFeeMap.remove(code);
    }
    /**
     * 项目类型删除值
     * @param code
     */
    public static void removeDicProjectType(Integer code){
        projectTypeMap.remove(code);
    }
    /**
     * 工程规模删除值
     * @param code
     */
    public static void removeDicProjectScale(Integer code){
        projectScaleMap.remove(code);
    }

}
