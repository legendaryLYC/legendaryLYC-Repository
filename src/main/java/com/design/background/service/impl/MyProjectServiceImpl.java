package com.design.background.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.design.background.entity.*;
import com.design.background.form.MoneyListForm;
import com.design.background.mapper.*;
import com.design.background.service.*;
import com.design.background.util.DateUtil;
import com.design.background.util.DesignFileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.design.background.config.FrontConfig;
import com.design.background.model.AreaModel;
import com.design.background.model.MyProjectModel;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service("/myProjectService")
public class MyProjectServiceImpl implements MyProjectService{

	@Autowired
	private ProjectDetailMapper projectDetailMapper;

	@Autowired
    private HttpServletRequest request;

	@Autowired
	private DesignerRelationshipMapper designerRelationshipMapper;

	@Autowired
    private DicAreaMapper dicAreaMapper;

	@Autowired
	private DicDefaultRateMapper dicDefaultRateMapper;

	@Autowired
	private MoneyDistributionMapper moneyDistributionMapper;

	@Autowired
	private DesignerRelationshipService designerRelationshipService;

	@Autowired
	private ProjectFilesService projectFilesService;

	@Autowired
	private HttpSession httpSession;
	@Autowired
	private ProjectFilesMapper projectFilesMapper;

	@Autowired
	private DicProjectTypeService dicProjectTypeService;

	/**
	 * 
	* @Author HRX 
	* @Title: deleteByPrimaryKey  
	* @Description: TODO 通过主键删除项目
	* @param @param id
	* @param @return      
	* @Date 2019年2月16日 下午11:45:14
	* @throws
	 */
	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return projectDetailMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 
	* @Author HRX 
	* @Title: insert  
	* @Description: TODO 添加项目
	* @param @param record
	* @param @return      
	* @Date 2019年2月16日 下午11:45:32
	* @throws
	 */
	@Override
	public int insert(ProjectDetail record) {
		// TODO Auto-generated method stub
		return projectDetailMapper.insert(record);
	}
	
	/**
	 * 
	* @Author HRX 
	* @Title: insertSelective  
	* @Description: TODO 选择性插入项目
	* @param @param record
	* @param @return      
	* @Date 2019年2月16日 下午11:45:50
	* @throws
	 */
	@Override
	public int insertSelective(ProjectDetail record) {
		// TODO Auto-generated method stub
		return projectDetailMapper.insertSelective(record);
	}
	
	/**
	 * 
	* @Author HRX 
	* @Title: selectByPrimaryKey  
	* @Description: TODO 通过主键查询项目
	* @param @param id
	* @param @return      
	* @Date 2019年2月16日 下午11:46:01
	* @throws
	 */
	@Override
	public ProjectDetail selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return projectDetailMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 
	* @Author HRX 
	* @Title: updateByPrimaryKeySelective  
	* @Description: TODO 条件选择项目进行更新
	* @param @param record
	* @param @return      
	* @Date 2019年2月16日 下午11:46:46
	* @throws
	 */
	@Override
	public int updateByPrimaryKeySelective(ProjectDetail record) {
		// TODO Auto-generated method stub
		return projectDetailMapper.updateByPrimaryKeySelective(record);
	}
	
	/**
	 * 
	* @Author HRX 
	* @Title: updateByPrimaryKey  
	* @Description: TODO 通过主键修改项目
	* @param @param record
	* @param @return      
	* @Date 2019年2月16日 下午11:47:11
	* @throws
	 */
	@Override
	public int updateByPrimaryKey(ProjectDetail record) {
		// TODO Auto-generated method stub
		return projectDetailMapper.updateByPrimaryKey(record);
	}
	
	/**
	 * 
	* @Author HRX 
	* @Title: selectByUserId  
	* @Description: TODO 通过用户id查询其项目
	* @param @param userId
	* @param @return      
	* @Date 2019年2月16日 下午11:47:23
	* @throws
	 */
	@Override
	public List<MyProjectModel> selectByUserId(ProjectDetail project) {
		// TODO Auto-generated method stub
		List<MyProjectModel> list = new ArrayList<>();
		LeadingUser leadingUser = (LeadingUser)request.getSession().getAttribute(FrontConfig.FONT_SESSION);
		project.setUserId(leadingUser.getId());
    	if(1001 == leadingUser.getRoleCode()) {
    		list = designerRelationshipMapper.selectByUserId(project);
    	}
    	if(1002 == leadingUser.getRoleCode()) {

    		list = projectDetailMapper.selectByUserId(project);
    		for(int i = 0; i < list.size(); i++){
    			if(null != list.get(i).getTypeCode()){
    				System.out.println(dicProjectTypeService.selectByCode(list.get(i).getTypeCode()));
					list.get(i).setType(dicProjectTypeService.selectByCode(list.get(i).getTypeCode()));
				}
			}
    		//判断是否展示开始建设按钮,孟晓冬
    		for(MyProjectModel model : list) {
    			//判断是否选了负责人
				boolean flag = false;
				int count =  designerRelationshipService.selectCountByProjectId(model.getId());
				if(count > 0){
					flag = true;
				}
    			//获取该项目还需要的设计师数组
    			Integer[] designers = designerRelationshipService.getUnselectedDesigners(model.getId());
    			//状态为已发布且设计师集齐,已选择设计师带头人
    			if(designers != null && designers.length == 0 && model.getStausCode() == 1004 && flag == true) {
    				model.setShowBeginBuild(true);
    			}else {
    				model.setShowBeginBuild(false);
    			}
    		}
    	}
		return list;
	}
	
	
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
	@Override
	public Integer selectCounts(Long userId ) {
		// TODO Auto-generated method stub
		return projectDetailMapper.selectCounts(userId);
	}

	
	/**
     * 
    * @Author HRX 
    * @Title: getProjectArea  
    * @Description: TODO 通过地区的area_id查询所属省市 
    * @param @param areaId
    * @param @return   
    * @return AreaModel    
    * @Date 2019年2月20日 下午6:01:43
    * @throws
     */
	@Override
	public AreaModel getProjectArea(Integer areaId) {
		// TODO Auto-generated method stub
		return dicAreaMapper.getProjectArea(areaId);
	}
	
	/**
	 * 
	* @Author HRX 
	* @Title: selectRateByPrimaryKey  
	* @Description: TODO 通过id查询各种类费率
	* @param @param id
	* @param @return      
	* @Date 2019年2月21日 上午11:16:37
	* @throws
	 */
	@Override
	public DicDefaultRate selectRateByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return dicDefaultRateMapper.selectByPrimaryKey(id);
	}

	@Override
	public int insertSelective(MoneyDistribution record) {
		// TODO Auto-generated method stub
		return moneyDistributionMapper.insertSelective(record);
	}

    @Override
    public int updateByProjectIdSelective(MoneyDistribution record) {
        return moneyDistributionMapper.updateByProjectIdSelective(record);
    }

    @Override
	public List<MoneyDistribution> selectMoneyByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return moneyDistributionMapper.selectByProjectId(id);
	}

	@Transactional
	@Override
	public void creatProject(ProjectDetail project, String[] designerTypes, MultipartFile[] files, MoneyListForm moneyListForm, MultipartFile image) {
		LeadingUser leadingUser = (LeadingUser)httpSession.getAttribute(FrontConfig.FONT_SESSION);
		String  designerType= StringUtils.join(designerTypes,",");
		project.setUserId(leadingUser.getId());
		project.setComponentCode(designerType);
		project = init(project);
		project.setCreateTime(new Date());
		if(StringUtils.isNotBlank(image.getOriginalFilename())){
			//上传到阿里云
			String coverImagePath = DesignFileUtils.uploadSingleFile(image);
			if(StringUtils.isNoneBlank(coverImagePath)){
				project.setCoverImage(coverImagePath);
			}
		}

		insertSelective(project);

		//插入阶段的费用划分
		insertSelective(moneyListForm.getMoneyStage1(project.getId()));
		insertSelective(moneyListForm.getMoneyStage2(project.getId()));

		for(MultipartFile file : files) {
			if(StringUtils.isNotBlank(file.getOriginalFilename())){
				//上传到阿里云
				String coverImagePath = DesignFileUtils.uploadSingleFile(file);
				if(StringUtils.isNoneBlank(coverImagePath)){
					projectFilesService.insert(new ProjectFiles(project.getId(),coverImagePath,file.getOriginalFilename()));
				}
			}
		}
	}

	@Transactional
	@Override
	public void updateProject(ProjectDetail project, String[] designerTypes, MultipartFile[] files, MoneyListForm moneyListForm, MultipartFile image,String deleteIds) {
		LeadingUser leadingUser = (LeadingUser)httpSession.getAttribute(FrontConfig.FONT_SESSION);
		String  designerType= StringUtils.join(designerTypes,",");
		project.setUserId(leadingUser.getId());
		project.setComponentCode(designerType);
		project = init(project);
		if(image != null && StringUtils.isNotBlank(image.getOriginalFilename())){
			//上传到阿里云
			String coverImagePath = DesignFileUtils.uploadSingleFile(image);
			if(StringUtils.isNoneBlank(coverImagePath)){
				project.setCoverImage(coverImagePath);
			}
		}
		updateByPrimaryKeySelective(project);

		//插入阶段的费用划分
		updateByProjectIdSelective(moneyListForm.getMoneyStage1(project.getId()));
		updateByProjectIdSelective(moneyListForm.getMoneyStage2(project.getId()));


		for(MultipartFile file : files) {
			if(StringUtils.isNotBlank(file.getOriginalFilename())){
				//上传到阿里云
				String coverImagePath = DesignFileUtils.uploadSingleFile(file);
				if(StringUtils.isNoneBlank(coverImagePath)){
					projectFilesService.insert(new ProjectFiles(project.getId(),coverImagePath,file.getOriginalFilename()));
				}
			}
		}
		if(StringUtils.isNoneBlank(deleteIds)){
			String[] strIds = deleteIds.split(",");
			Long[] longIds = new Long[strIds.length];
			for (int i = 0; i < strIds.length; i++) {
				longIds[i] = Long.valueOf(strIds[i]);
			}
			projectFilesMapper.deleteByIds(longIds );
		}
	}

	@Transactional
	@Override
	public void draft(ProjectDetail project, String[] designerTypes, MultipartFile[] files, MoneyListForm moneyListForm, MultipartFile image) {
		LeadingUser leadingUser = (LeadingUser)httpSession.getAttribute(FrontConfig.FONT_SESSION);
		String  designerType= StringUtils.join(designerTypes,",");
		project.setUserId(leadingUser.getId());
		project.setComponentCode(designerType);
		project = init(project);
		project.setProcessCode(1000);
		project.setCreateTime(new Date());
		if(image != null && StringUtils.isNotBlank(image.getOriginalFilename())){
			//上传到阿里云
			String coverImagePath = DesignFileUtils.uploadSingleFile(image);
			if(StringUtils.isNoneBlank(coverImagePath)){
				project.setCoverImage(coverImagePath);
			}
		}

		insertSelective(project);

		//插入阶段的费用划分
		if(moneyListForm != null){
			insertSelective(moneyListForm.getMoneyStage1(project.getId()));
			insertSelective(moneyListForm.getMoneyStage2(project.getId()));
		}

		for(MultipartFile file : files) {
			if(StringUtils.isNotBlank(file.getOriginalFilename())){
				//上传到阿里云
				String coverImagePath = DesignFileUtils.uploadSingleFile(file);
				if(StringUtils.isNoneBlank(coverImagePath)){
					projectFilesService.insert(new ProjectFiles(project.getId(),coverImagePath,file.getOriginalFilename()));
				}
			}
		}
	}


	public ProjectDetail init(ProjectDetail project) {
		project.setProcessCode(1001);
		project.setStageCode(1000);
		project.setIsTop(0);
		project.setIsDelete(0);
		project.setCompleteAudting(0);
		project.setProjectProgress(0);

		project.setDispatchCode(1000);
		if(null != project.getStartupDate() && null != project.getDeadline()){
			project.setConstructionPeriod(DateUtil.differentDays(project.getStartupDate(),project.getDeadline()));
		}
		return project;
	}
}
