package com.design.background.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.design.background.entity.ExampleProject;
import com.design.background.entity.LeadingUser;
import com.design.background.form.DesignerForm;
import com.design.background.form.MyAccountForm;
import com.design.background.form.ProjectPayForm;
import com.design.background.form.ProjectorForm;
import com.design.background.model.AreaModel;
import com.design.background.model.StatisticalSearchUserBalanceModel;
import com.design.background.model.StatisticalSearchUserCountModel;
import com.design.background.model.StatisticalSearchUserModel;

public interface LeadingUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LeadingUser record);
    
    //插入并返回ID
    int insertreturnId(LeadingUser record);

    int insertSelective(LeadingUser record);

    LeadingUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LeadingUser record);

    int updateByPrimaryKeyWithBLOBs(LeadingUser record);

    int updateByPrimaryKey(LeadingUser record);

    //获取所有设计师
    List<LeadingUser> selectAll();
    List<DesignerForm> selectAlldesigner();
    
    //通过id获取设计师
    DesignerForm selectdesignerById(Long id);

	//通过id获取设计师
	DesignerForm finddesignerById(Long id);
    
    // 通过tel获取单个设计师
    LeadingUser selectByTel(String tel);
    
    // 通过tel获取单个设计师
    LeadingUser selectByMail(String mail);
    
    //通过name模糊查找designer
    List<DesignerForm> selectdesignerByname(@Param("username")String username);
    
    List<LeadingUser> selectByName(String name);
	
	List<LeadingUser> select(String name);

	LeadingUser selectLast();

	List<ProjectorForm> selectProjectorsSelective(LeadingUser leadingUser);
	/**

	 * 任松
	 * 
	 * 根据项目id获取项目中设计师相关信息
	 * @return
	 */
	List<ProjectPayForm> selectprojectdetails(Long projectId);
	

	/**任松
	 *通过账号（手机号）和密码 查找用户
	 * @param leadingUser
	 * @return
	 */
	LeadingUser selectUser(@Param("tel")String tel,@Param("password") String password);

//	int selectUser(@Param("tel")String tel,@Param("password") String password);
	/**
	 * 王喜壮
	 * 通过id查询信息
	 * @param Id
	 * @return
	 */
	MyAccountForm selectById(Long Id);

	
	
	/**
	 * 获取cityId和provinceId
	 * @param id
	 * @param areaId
	 * @return
	 */
	ProjectorForm selectUserAreaById(@Param("userId")Long userId,@Param("areaId")int areaId);

	List<DesignerForm> selectDesignerByPhone(@Param("phone")String phone,@Param("typeCode")int typeCode);

	/**
	 * @description 根据设计师的手机号查出设计师的信息
	 * @param String phone
	 * @param LeadingUser leadingUser
	 * @return
	 */
	LeadingUser selectDesignerByNum(String phone);
	
	/**
	 * @description 根据手机号查出是否有该手机号
	 * @param String tel
	 * @return int
	 * @author 周天
	 */
	int selectCountByTel(String tel);
	
	/**
	 * @description 根据邮箱地址查出是否有该邮箱地址
	 * @param String tel
	 * @return int
	 * @author 周天
	 */
	int selectCountByEmail(String email);
	

   /**
	 * @description 查找出所有项目方，设计师，专家用户的数量
	 * @return List<StatisticalSearchUserModel>
	 * @author 周天
	 */
	List<StatisticalSearchUserModel> selectStatisticalUserAllTime();
	
	/**
	 * @description 查找出一个月的项目方，设计师，专家用户的数量
	 * @return List<StatisticalSearchUserModel>
	 * @author 周天
	 */
	List<StatisticalSearchUserModel> selectStatisticalUserLastWeek(List<Map<String,Object>> list);
	
	/**
	 * @description 查找出一个月的项目方，设计师，专家用户的数量
	 * @return List<StatisticalSearchUserModel>
	 * @author 周天
	 */
	List<StatisticalSearchUserModel> selectStatisticalUserThisWeek(List<Map<String,Object>> list);
	
	/**
	 * @description 查找出一个月的项目方，设计师，专家用户的数量
	 * @return List<StatisticalSearchUserModel>
	 * @author 周天
	 */
	List<StatisticalSearchUserModel> selectStatisticalUserOneWeek();
	
	/**
	 * @description 查找出一个月内所有的项目方，设计师，专家用户的数量
	 * @return List<StatisticalSearchUserModel>
	 * @author 周天
	 */
	List<StatisticalSearchUserModel> selectStatisticalUserOneMonth();
	
	/**
	 * @description 查找出用户数量最多前十的城市
	 * @return StatisticalSearchUserBalanceModel
	 * @author 周天
	 */
	List<StatisticalSearchUserBalanceModel> selectStatisticalUserTopCity();
	
	/**
	 * @description 查找余额最多的前十用户
	 * @return List<StatisticalSearchUserCountModel>
	 * @author 周天
	 */
	 List<StatisticalSearchUserCountModel> selectStatisticalUserTopBalance();

	 /**
	  * 根据用户id获取地区信息
	  * @author 孟晓冬
	  * @param id
	  * @return
	  */
	 AreaModel selectAreaModelByUserId(Long id);
}

