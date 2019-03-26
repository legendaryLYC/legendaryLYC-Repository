package com.design.background.service;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.design.background.entity.MoneyDistribution;
import com.design.background.entity.ProjectDetail;
import com.design.background.form.BasicProjectForm;
import com.design.background.form.MoneyListForm;
import com.design.background.form.ProjectForm;
import com.design.background.model.ScreeningConditions;

/**
 * @Author： 孟晓冬
 * @Date： 2019/2/13
 * @Description： 项目管理Service
 */
public interface ProjectManageService {
	
	/**
	 * 按条件查询项目列表
	 * @param 
	 * @return
	 */
	List<ProjectForm> selectProjectsSelective(ProjectDetail projectDetail, Date from, Date to);

	/**
	* @Author: 李永成
	* @Date: 2019/2/16
	* @Description:  根据项目的id组装成form传给前台显示
	* @Param:  ProjectDetail order
	* @return:  ProjectorForm detail
	*/
	ProjectForm selectProjectsFormById(ProjectDetail projectDetail);

	/**
	 * 删除该id的项目
	 * @param 
	 * @return
	 */
	Boolean deleteProjectById(Long id);
	
	/**
	 * 更新该id的项目
	 * @param 
	 * @return
	 */
	Boolean updateProjectAll(ProjectDetail order);
	
	/**
	 *置顶该id的项目
	 * @param 
	 * @return
	 */
	Boolean topProjectById(Long id,Integer isTop);

	/**
	 * 根据id查出审核记录，在controller判断属于哪种审核类型
	 * @param id
	 * @return ProjectDetail
	 */
	ProjectDetail selectByPrimaryKey(Long id);
	
	/**
	 * 条件筛选项目简略信息列表
	 * @param id
	 * @return boolean result
	 */
	List<BasicProjectForm> selectBasicProjectsSelective(ProjectDetail projectDetail,
			ScreeningConditions screeningConditions,int... statuses);

	/**
	 * 通过id更新项目详情
	 */
	boolean updateById(ProjectDetail projectDetail);

	/**
	 * 更新项目
	 */
	int updateByPrimaryKeySelective(ProjectDetail project);

	/**
	 * 判断用户id和项目id判断当前用户是否有权限下载项目文件
	 * @author 孟晓冬
	 * @param userId
	 * @param projectId
	 * @return
	 */
	Boolean isHavaPermission(Long userId,Long projectId);
	
	/**
	 * 修改项目
	 * @author 孟晓冬
	 * @param project
	 * @param designerTypes
	 * @param files
	 * @param moneyListForm
	 * @param image
	 */
	void updateProject(ProjectDetail project, String[] designerTypes
            , MultipartFile[] files, MoneyListForm moneyListForm,MultipartFile image,String deleteIds);
	
	/**
	 * 选择性修改
	 * @author 孟晓冬
	 * @param record
	 * @return
	 */
	public int updateByProjectIdSelective(MoneyDistribution record) ;
}
