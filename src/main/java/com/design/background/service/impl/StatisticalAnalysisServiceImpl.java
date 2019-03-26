package com.design.background.service.impl;

import com.design.background.mapper.CapitalAuditMapper;
import com.design.background.mapper.DesignerRelationshipMapper;
import com.design.background.mapper.LeadingUserMapper;
import com.design.background.mapper.ProjectDetailMapper;
import com.design.background.model.*;
import com.design.background.service.StatisticalAnalysisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 平台各数据统计分析serviceImpl层 
 * 时间:2019/2/20 
 * 最后修改时间:2019/2/22
 *  注意事项:无
 * @author 周天
 *
 */
@Service("StatisticalAnalysisService")
public class StatisticalAnalysisServiceImpl implements StatisticalAnalysisService {

	private static final Logger logger = LoggerFactory.getLogger(StatisticalAnalysisServiceImpl.class);

	@Autowired
	private LeadingUserMapper leadingUserMapper;

	@Autowired
	private ProjectDetailMapper projectDetailMapper;

	@Autowired
	private DesignerRelationshipMapper designerRelationshipMapper;
	
	@Autowired
	private CapitalAuditMapper capitalAuditMapper;

	private static List<Map<String,Object>> LastWeekTimeList;

	private static List<Map<String,Object>> thisWeekTimeList;

	public  static void  setLastWeekTimeList(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		LastWeekTimeList = new ArrayList<>();
		for (int i = 6; i >= 0; i--) {
			Calendar cal = Calendar.getInstance();
			int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
			cal.add(Calendar.DATE, - (i+week));
			String date = sdf.format(cal.getTime());
			Map<String,Object> map = new HashMap<>();
			map.put("startTime",date+" 00:00:00");
			map.put("endTime",date+" 23:59:59");
			LastWeekTimeList.add(map);
		}
		thisWeekTimeList = new ArrayList<>();
		for (int i = -1; i >=-7; i--) {
			Calendar cal = Calendar.getInstance();
			int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
			cal.add(Calendar.DATE, - (i+week));
			Map<String,Object> map = new HashMap<>();
			String date = sdf.format(cal.getTime());
			map.put("startTime",date+" 00:00:00");
			map.put("endTime",date+" 23:59:59");
			thisWeekTimeList.add(map);
		}
	}

	@Override
	public StatisticalUserModel getUserStatistical() {

		StatisticalUserModel statisticalUser = new StatisticalUserModel(); // 定义返回的StatisticalUserModel对象
		List<StatisticalSearchUserModel> statisticalSearchUser = null; // 定义数据库返回的model对象，默认值为null

		// 首先获取库中所有项目方、设计师、专家用户的数量，三者相加为平台所有用户的数量
		statisticalSearchUser = leadingUserMapper.selectStatisticalUserAllTime();
		statisticalUser.setAllDesignUser(statisticalSearchUser.get(0).getNum());
		statisticalUser.setAllProjectUser(statisticalSearchUser.get(1).getNum());
		statisticalUser.setAllExpertUser(statisticalSearchUser.get(2).getNum());
		// 再判断刚才获取的数据是否为null,如果为null说明数据库中没有数据,返回null给controller进行处理
		/*if (statisticalSearchUser != null) {
			for (StatisticalSearchUserModel iterator : statisticalSearchUser) { // 用foreach循环处理list集合
				if (iterator.getRoleCode() == 1001) { // 如果角色为1001,说明该角色为设计师，所以添加到设计师数量上
					statisticalUser.setAllDesignUser(iterator.getNum());
				} else if (iterator.getRoleCode() == 1002) { // 如果角色为1002,说明该角色为项目方，所以添加到项目方数量上
					statisticalUser.setAllProjectUser(iterator.getNum());
				} else if (iterator.getRoleCode() == 1003) { // 如果角色为1003,说明该角色为专家，所以添加到项目方数量上
					statisticalUser.setAllExpertUser(iterator.getNum());
				} else {
					logger.error("处理所有用户时leading_user库中role_code值出现问题，请及时检查！");
				}
			}
		} else {
			logger.error("leading_user库中没有数据，或者出现其他异常！");
			return null;
		}*/

		// 再将刚才获取到的数据进行相加，保存到要返回对象的allUser属性里,
		// 因为statisticalUser对象进行了初始化，并且所有用户数量的初始值为0，所以不需要判断空指针
		statisticalUser.setAllUser(statisticalUser.getAllDesignUser() + statisticalUser.getAllProjectUser()
				+ statisticalUser.getAllExpertUser());

		// 处理一周内各用户的数量
		statisticalSearchUser = null; // 再将保存数据库返回数据的对象置null，用来接收下面接收到的数据
		// 首先获取库中一周内所有项目方、设计师、专家用户的数量
		statisticalSearchUser = leadingUserMapper.selectStatisticalUserOneWeek();
		// 再判断刚才获取的数据是否为null,如果为null说明数据库中没有数据,返回null给controller进行处理
		if (statisticalSearchUser != null) {
			for (StatisticalSearchUserModel iterator : statisticalSearchUser) { // 用foreach循环处理list集合
				if (iterator.getRoleCode() == 1001) { // 如果角色为1001,说明该角色为设计师，所以添加到设计师数量上
					statisticalUser.setWeekDesignUser(iterator.getNum());
				} else if (iterator.getRoleCode() == 1002) { // 如果角色为1002,说明该角色为项目方，所以添加到项目方数量上
					statisticalUser.setWeekProjectUser(iterator.getNum());
				} else if (iterator.getRoleCode() == 1003) { // 如果角色为1003,说明该角色为设计师，所以添加到项目方数量上
					statisticalUser.setWeekExpertUser(iterator.getNum());
				} else {
					logger.error("处理一周内各用户时leading_user库中role_code值出现问题，请及时检查！");
				}
			}
		} else {
			logger.error("leading_user库中没有数据，或者出现其他异常！");
			return null;
		}

		// 处理一个月内各用户的数量
		statisticalSearchUser = null; // 再将保存数据库返回数据的对象置null，用来接收下面接收到的数据
		// 首先获取库中一个月内所有项目方、设计师、专家用户的数量
		statisticalSearchUser = leadingUserMapper.selectStatisticalUserOneMonth();
		// 再判断刚才获取的数据是否为null,如果为null说明数据库中没有数据,返回null给controller进行处理
		if (statisticalSearchUser != null) {
			for (StatisticalSearchUserModel iterator : statisticalSearchUser) { // 用foreach循环处理list集合
				if (iterator.getRoleCode() == 1001) { // 如果角色为1001,说明该角色为设计师，所以添加到设计师数量上
					statisticalUser.setMonthDesignUser(iterator.getNum());
				} else if (iterator.getRoleCode() == 1002) { // 如果角色为1002,说明该角色为项目方，所以添加到项目方数量上
					statisticalUser.setMonthProjectUser(iterator.getNum());
				} else if (iterator.getRoleCode() == 1003) { // 如果角色为1003,说明该角色为设计师，所以添加到项目方数量上
					statisticalUser.setMonthExpertUser(iterator.getNum());
				} else {
					logger.error("处理一个月的用户时leading_user库中role_code值出现问题，请及时检查！");
				}
			}
		} else {
			logger.error("leading_user库中没有数据，或者出现其他异常！");
			return null;
		}
		// 添加本月新增所有用户量
		statisticalUser.setAllMonthUser(statisticalUser.getMonthDesignUser() + statisticalUser.getMonthProjectUser()
				+ statisticalUser.getMonthExpertUser());

		// 查询出当前用户最多的十个城市
		List<StatisticalSearchUserBalanceModel> statisticalSearchUserBalance = null;
		statisticalSearchUserBalance = leadingUserMapper.selectStatisticalUserTopCity();
		// 为了前端更好处理，不满十个，凑默认值来填充
		if(statisticalSearchUserBalance.size() < 10) {
			for( int i = statisticalSearchUserBalance.size(); i <= 9; i++) {
				statisticalSearchUserBalance.add(i,new StatisticalSearchUserBalanceModel());
			}
		}
		statisticalUser.setTopTenCity(statisticalSearchUserBalance);

		// 查询当前用户余额前十的用户名和余额
		// 声明一个StatisticalSearchUserCountModel对象的list集合接收数据库中的数据
		List<StatisticalSearchUserCountModel> statisticalSearchUserCount = null;
		// 首先获取当前库中所有项目方、设计师、专家用户的数量
		statisticalSearchUserCount = leadingUserMapper.selectStatisticalUserTopBalance();
		// 为了前端更好处理，不满十个，凑默认值来填充
		if(statisticalSearchUserCount.size() < 10) {
			for( int i = statisticalSearchUserCount.size(); i <= 9; i++) {
				statisticalSearchUserCount.add(i,new StatisticalSearchUserCountModel());
			}
		}
		statisticalUser.setTopTenBalanceUser(statisticalSearchUserCount);
		List<StatisticalSearchUserModel> lastStatisticalSearchUserModel = leadingUserMapper.selectStatisticalUserLastWeek(LastWeekTimeList);
		statisticalUser.setLastStatisticalSearchUserModel(lastStatisticalSearchUserModel);
		List<StatisticalSearchUserModel> thisStatisticalSearchUserModel = leadingUserMapper.selectStatisticalUserThisWeek(thisWeekTimeList);
		statisticalUser.setThisStatisticalSearchUserModel(thisStatisticalSearchUserModel);
		/*// 还有周内各星期，上周内各天各用户的数据尚未获取，仍待完善

		// 先将接收数据的对象赋值为null
		List<StatisticalSearchUserModel> statisticalSearchUserCountList = null;
		// 首先处理上周的数据，上周七天肯定都有
		statisticalSearchUserCountList = leadingUserMapper.selectStatisticalUserLastWeek(7); // 传7表示查找星期一的
		if (statisticalSearchUserCountList != null) {
			statisticalSearchUserCountList = parseUserList(statisticalSearchUserCountList);
			statisticalUser.setLastWeekMon(statisticalSearchUserCountList);
			statisticalSearchUserCountList = null;
		}
		statisticalSearchUserCountList = leadingUserMapper.selectStatisticalUserLastWeek(6);
		if (statisticalSearchUserCountList != null) {
			statisticalSearchUserCountList = parseUserList(statisticalSearchUserCountList);
			statisticalUser.setLastWeekTues(statisticalSearchUserCountList);
			statisticalSearchUserCountList = null;
		}
		statisticalSearchUserCountList = leadingUserMapper.selectStatisticalUserLastWeek(5);
		if (statisticalSearchUserCountList != null) {
			statisticalSearchUserCountList = parseUserList(statisticalSearchUserCountList);
			statisticalUser.setLastWeekWed(statisticalSearchUserCountList);
			statisticalSearchUserCountList = null;
		}
		statisticalSearchUserCountList = leadingUserMapper.selectStatisticalUserLastWeek(4);
		if (statisticalSearchUserCountList != null) {
			statisticalSearchUserCountList = parseUserList(statisticalSearchUserCountList);
			statisticalUser.setLastWeekThurs(statisticalSearchUserCountList);
			statisticalSearchUserCountList = null;
		}
		statisticalSearchUserCountList = leadingUserMapper.selectStatisticalUserLastWeek(3);
		if (statisticalSearchUserCountList != null) {
			statisticalSearchUserCountList = parseUserList(statisticalSearchUserCountList);
			statisticalUser.setLastWeekFri(statisticalSearchUserCountList);
			statisticalSearchUserCountList = null;
		}
		statisticalSearchUserCountList = leadingUserMapper.selectStatisticalUserLastWeek(2);
		if (statisticalSearchUserCountList != null) {
			statisticalSearchUserCountList = parseUserList(statisticalSearchUserCountList);
			statisticalUser.setLastWeekSat(statisticalSearchUserCountList);
			statisticalSearchUserCountList = null;
		}
		statisticalSearchUserCountList = leadingUserMapper.selectStatisticalUserLastWeek(1); // 传1表示查找星期一的
		if (statisticalSearchUserCountList != null) {
			statisticalSearchUserCountList = parseUserList(statisticalSearchUserCountList);
			statisticalUser.setLastWeekSun(statisticalSearchUserCountList);
			statisticalSearchUserCountList = null;
		}*/

		/*// 再处理本周的数据
		// 获取到今天是本周的星期几
		Calendar calendar = Calendar.getInstance();
		int week;
		week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (week == 0) {
			week = 7;
		}

		// 获取到今天与星期一差几天
		week -= 1;
		// 将刚才获取到的差值保存到对象中
		statisticalUser.setWhatThisDay(week);
		for (int i = 0; i <= 6; i++) {
			statisticalSearchUserCountList = leadingUserMapper.selectStatisticalUserThisWeek(i);
			if (statisticalSearchUserCountList != null && i == 0) {
				statisticalSearchUserCountList = parseUserList(statisticalSearchUserCountList);
				statisticalUser.setThisWeekMon(statisticalSearchUserCountList);
				statisticalSearchUserCountList = null;
			}
			if (statisticalSearchUserCountList != null && i == 1) {
				statisticalSearchUserCountList = parseUserList(statisticalSearchUserCountList);
				statisticalUser.setThisWeekTues(statisticalSearchUserCountList);
				statisticalSearchUserCountList = null;
			}
			if (statisticalSearchUserCountList != null && i == 2) {
				statisticalSearchUserCountList = parseUserList(statisticalSearchUserCountList);
				statisticalUser.setThisWeekWed(statisticalSearchUserCountList);
				statisticalSearchUserCountList = null;
			}
			if (statisticalSearchUserCountList != null && i == 3) {
				statisticalSearchUserCountList = parseUserList(statisticalSearchUserCountList);
				statisticalUser.setThisWeekThurs(statisticalSearchUserCountList);
				statisticalSearchUserCountList = null;
			}
			if (statisticalSearchUserCountList != null && i == 4) {
				statisticalSearchUserCountList = parseUserList(statisticalSearchUserCountList);
				statisticalUser.setThisWeekFri(statisticalSearchUserCountList);
				statisticalSearchUserCountList = null;
			}
			if (statisticalSearchUserCountList != null && i == 5) {
				statisticalSearchUserCountList = parseUserList(statisticalSearchUserCountList);
				statisticalUser.setThisWeekSat(statisticalSearchUserCountList);
				statisticalSearchUserCountList = null;
			}
			if (statisticalSearchUserCountList != null && i == 6) {
				statisticalSearchUserCountList = parseUserList(statisticalSearchUserCountList);
				statisticalUser.setThisWeekSun(statisticalSearchUserCountList);
				statisticalSearchUserCountList = null;
			}
		}*/
		return statisticalUser;
	}

	@Override
	public StatisticalProjectModel getProjectStatistical() {

		StatisticalProjectModel statisticalProject = new StatisticalProjectModel(); // 定义返回的StatisticalUserModel对象
		List<StatisticalSearchUserModel> statisticalSearchProject = null; // 定义数据库返回的model对象，默认值为null

		// 首先获取库中所有新建状态、在建状态、结项状态的项目数量，三者相加为平台所有项目的数量
		statisticalSearchProject = projectDetailMapper.selectStatisticalProjectAllTime();
		// 再判断刚才获取的数据是否为null,如果为null说明数据库中没有数据,返回null给controller进行处理
		if (statisticalSearchProject != null) {
			for (StatisticalSearchUserModel iterator : statisticalSearchProject) { // 用foreach循环处理list集合
				if (iterator.getRoleCode() == 1004) { // 如果类型为1004,说明该项目为新建状态，所以添加到新建状态的数量上
					statisticalProject.setAllNewProject(iterator.getNum());
				} else if (iterator.getRoleCode() == 1005) { // 如果类型为1005,说明该项目为在建状态，所以添加到在建状态的数量上
					statisticalProject.setAllMiddelProject(iterator.getNum());
				} else if (iterator.getRoleCode() == 1006) { // 如果类型为1006,说明该项目为结项状态，所以添加到结项状态的数量上
					statisticalProject.setAllEndProject(iterator.getNum());
				} else {
					logger.error("处理项目所有项目数量时project_detail库中process_code值出现问题，请及时检查！");
				}
			}
		} else {
			logger.error("project_detail库中没有数据，或者出现其他异常！");
			return null;
		}

		// 再将刚才获取到的数据进行相加，保存到要返回对象的allUser属性里,
		// 因为statisticalProject对象进行了初始化，并且所有项目数量的初始值为0，所以不需要判断空指针
		statisticalProject.setAllProject(statisticalProject.getAllNewProject() + statisticalProject.getAllMiddelProject()
				+ statisticalProject.getAllEndProject());

		// 处理一周内各项目的数量
		statisticalSearchProject = null; // 再将保存数据库返回数据的对象置null，用来接收下面接收到的数据
		// 首先获取库中一周内所有项目方、设计师、专家用户的数量
		statisticalSearchProject = projectDetailMapper.selectStatisticalProjectOneWeek();
		// 再判断刚才获取的数据是否为null,如果为null说明数据库中没有数据,返回null给controller进行处理
		if (statisticalSearchProject != null) {
			for (StatisticalSearchUserModel iterator : statisticalSearchProject) { // 用foreach循环处理list集合
				if (iterator.getRoleCode() == 1004) { // 如果角色为1004,说明该角色为新建项目，所以添加到设计师数量上
					statisticalProject.setWeekNewProject(iterator.getNum());
				} else if (iterator.getRoleCode() == 1005) { // 如果角色为1005,说明该角色为在建项目，所以添加到项目方数量上
					statisticalProject.setWeekMiddelProject(iterator.getNum());;
				} else if (iterator.getRoleCode() == 1006) { // 如果角色为1006,说明该角色为结项项目，所以添加到项目方数量上
					statisticalProject.setWeekEndProject(iterator.getNum());
				} else {
					logger.error("project_detail库中role_code值出现问题，请及时检查！");
					return null; // 如果出现了另外的值，说明库中数据有问题直接返回
				}
			}
		} else {
			logger.error("project_detail库中没有数据，或者出现其他异常！");
			return null;
		}

		// 处理一个月内各用户的数量
		statisticalSearchProject = null; // 再将保存数据库返回数据的对象置null，用来接收下面接收到的数据
		// 首先获取库中一个月内所有项目方、设计师、专家用户的数量
		statisticalSearchProject = projectDetailMapper.selectStatisticalProjectOneMonth();
		// 再判断刚才获取的数据是否为null,如果为null说明数据库中没有数据,返回null给controller进行处理
		if (statisticalSearchProject != null) {
			for (StatisticalSearchUserModel iterator : statisticalSearchProject) { // 用foreach循环处理list集合
				if (iterator.getRoleCode() == 1004) { // 如果角色为1004,说明该角色为设计师，所以添加到设计师数量上
					statisticalProject.setMonthNewProject(iterator.getNum());
				} else if (iterator.getRoleCode() == 1005) { // 如果角色为1005,说明该角色为项目方，所以添加到项目方数量上
					statisticalProject.setMonthMiddelProject(iterator.getNum());
				} else if (iterator.getRoleCode() == 1006) { // 如果角色为1006,说明该角色为设计师，所以添加到项目方数量上
					statisticalProject.setMonthEndProject(iterator.getNum());;
				} else {
					logger.error("project_detail库中role_code值出现问题，请及时检查！");
					return null; // 如果出现了另外的值，说明库中数据有问题直接返回
				}
			}
		} else {
			logger.error("project_detail库中没有数据，或者出现其他异常！");
			return null;
		}
		// 添加本月新增所有用户量
		statisticalProject.setAllMonthProject(statisticalProject.getMonthNewProject()
				+ statisticalProject.getMonthMiddelProject() + statisticalProject.getMonthEndProject());

		// 查询出当前项目量最多的十个城市
		List<StatisticalSearchUserBalanceModel> statisticalSearchProjectBalance = null;
		statisticalSearchProjectBalance = projectDetailMapper.selectStatisticalProjectTopCity();
		// 再判断刚才获取的数据是否为null,如果为null说明数据库中数据有问题,返回null给controller进行处理
		// 为了前端更好处理，不满十个，凑默认值来填充
		if(statisticalSearchProjectBalance.size() < 10) {
			for( int i = statisticalSearchProjectBalance.size(); i <= 9; i++) {
				statisticalSearchProjectBalance.add(i,new StatisticalSearchUserBalanceModel());
			}
		}
		statisticalProject.setTopTenCity(statisticalSearchProjectBalance);

		// 查询当前设计师完成项目量前十的用户名和数量
		// 声明一个StatisticalSearchUserCountModel对象的list集合接收数据库中的数据
		List<StatisticalSearchUserCountModel> statisticalSearchProjectCount = null;
		// 首先获取当前库中所有项目方、设计师、专家用户的数量
		statisticalSearchProjectCount = projectDetailMapper.selectStatisticalProjectTopBalance();
		// 为了前端更好处理，不满十个，凑默认值来填充
		if(statisticalSearchProjectCount.size() < 10) {
			for( int i = statisticalSearchProjectCount.size(); i <= 9; i++) {
				statisticalSearchProjectCount.add(i,new StatisticalSearchUserCountModel());
			}
		}
		statisticalProject.setTopTenBalanceUser(statisticalSearchProjectCount);
		List<StatisticalSearchUserModel> lastStatisticalProjectModel = projectDetailMapper.selectStatisticalProjectLastweek(LastWeekTimeList);
		statisticalProject.setLastStatisticalSearchUserModel(lastStatisticalProjectModel);
		List<StatisticalSearchUserModel> thisStatisticalProjectModel = projectDetailMapper.selectStatisticalProjectThisweek(thisWeekTimeList);
		statisticalProject.setThisStatisticalSearchUserModel(thisStatisticalProjectModel);
		/*// 还有获取周内各星期，上周内各天各项目的数据，仍待测试

		// 先将接收数据的对象赋值为null
		List<StatisticalSearchUserModel> statisticalSearchProjectCountList = null;
		// 首先处理上周的数据，上周七天肯定都有
		statisticalSearchProjectCountList = projectDetailMapper.selectStatisticalProjectLastweek(7); // 传7表示查找星期一的
		if (statisticalSearchProjectCountList != null) {
			statisticalSearchProjectCountList = parseProjectList(statisticalSearchProjectCountList);
			statisticalProject.setLastWeekMon(statisticalSearchProjectCountList);
			statisticalSearchProjectCountList = null;
		}
		statisticalSearchProjectCountList = projectDetailMapper.selectStatisticalProjectLastweek(6);
		if (statisticalSearchProjectCountList != null) {
			statisticalSearchProjectCountList = parseProjectList(statisticalSearchProjectCountList);
			statisticalProject.setLastWeekTues(statisticalSearchProjectCountList);
			statisticalSearchProjectCountList = null;
		}
		statisticalSearchProjectCountList = projectDetailMapper.selectStatisticalProjectLastweek(5);
		if (statisticalSearchProjectCountList != null) {
			statisticalSearchProjectCountList = parseProjectList(statisticalSearchProjectCountList);
			statisticalProject.setLastWeekWed(statisticalSearchProjectCountList);
			statisticalSearchProjectCountList = null;
		}
		statisticalSearchProjectCountList = projectDetailMapper.selectStatisticalProjectLastweek(4);
		if (statisticalSearchProjectCountList != null) {
			statisticalSearchProjectCountList = parseProjectList(statisticalSearchProjectCountList);
			statisticalProject.setLastWeekThurs(statisticalSearchProjectCountList);
			statisticalSearchProjectCountList = null;
		}
		statisticalSearchProjectCountList = projectDetailMapper.selectStatisticalProjectLastweek(3);
		if (statisticalSearchProjectCountList != null) {
			statisticalSearchProjectCountList = parseProjectList(statisticalSearchProjectCountList);
			statisticalProject.setLastWeekFri(statisticalSearchProjectCountList);
			statisticalSearchProjectCountList = null;
		}
		statisticalSearchProjectCountList = projectDetailMapper.selectStatisticalProjectLastweek(2);
		if (statisticalSearchProjectCountList != null) {
			statisticalSearchProjectCountList = parseProjectList(statisticalSearchProjectCountList);
			statisticalProject.setLastWeekSat(statisticalSearchProjectCountList);
			statisticalSearchProjectCountList = null;
		}
		statisticalSearchProjectCountList = projectDetailMapper.selectStatisticalProjectLastweek(1); // 传1表示查找星期一的
		if (statisticalSearchProjectCountList != null) {
			statisticalSearchProjectCountList = parseProjectList(statisticalSearchProjectCountList);
			statisticalProject.setLastWeekSun(statisticalSearchProjectCountList);
			statisticalSearchProjectCountList = null;
		}

		// 再处理本周的数据
		// 获取到今天是本周的星期几
		Calendar calendar = Calendar.getInstance();
		int week;
		week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (week == 0) {
			week = 7;
		}

		// 获取到今天与星期一差几天
		week -= 1;
		// 将刚才获取到的差值保存到对象中
		statisticalProject.setWhatThisDay(week);
		for (int i = 0; i <= 6; i++) {
			statisticalSearchProjectCountList = projectDetailMapper.selectStatisticalProjectThisweek(i);
			if (statisticalSearchProjectCountList != null && i == 0) {
				statisticalSearchProjectCountList = parseProjectList(statisticalSearchProjectCountList);
				statisticalProject.setThisWeekMon(statisticalSearchProjectCountList);
				statisticalSearchProjectCountList = null;
			}
			if (statisticalSearchProjectCountList != null && i == 1) {
				statisticalSearchProjectCountList = parseProjectList(statisticalSearchProjectCountList);
				statisticalProject.setThisWeekTues(statisticalSearchProjectCountList);
				statisticalSearchProjectCountList = null;
			}
			if (statisticalSearchProjectCountList != null && i == 2) {
				statisticalSearchProjectCountList = parseProjectList(statisticalSearchProjectCountList);
				statisticalProject.setThisWeekWed(statisticalSearchProjectCountList);
				statisticalSearchProjectCountList = null;
			}
			if (statisticalSearchProjectCountList != null && i == 3) {
				statisticalSearchProjectCountList = parseProjectList(statisticalSearchProjectCountList);
				statisticalProject.setThisWeekThurs(statisticalSearchProjectCountList);
				statisticalSearchProjectCountList = null;
			}
			if (statisticalSearchProjectCountList != null && i == 4) {
				statisticalSearchProjectCountList = parseProjectList(statisticalSearchProjectCountList);
				statisticalProject.setThisWeekFri(statisticalSearchProjectCountList);
				statisticalSearchProjectCountList = null;
			}
			if (statisticalSearchProjectCountList != null && i == 5) {
				statisticalSearchProjectCountList = parseProjectList(statisticalSearchProjectCountList);
				statisticalProject.setThisWeekSat(statisticalSearchProjectCountList);
				statisticalSearchProjectCountList = null;
			}
			if (statisticalSearchProjectCountList != null && i == 6) {
				statisticalSearchProjectCountList = parseProjectList(statisticalSearchProjectCountList);
				statisticalProject.setThisWeekSun(statisticalSearchProjectCountList);
				statisticalSearchProjectCountList = null;
			}
		}*/
		return statisticalProject;
	}

	@Override
	public StatisticalSearchTenderModel getTenderStatistical() {

		StatisticalSearchTenderModel statisticalSearchTender = new StatisticalSearchTenderModel();
		; // 创建返回对象
		// 首先获取当前是几月份
		Calendar cale = Calendar.getInstance();
		int month = cale.get(Calendar.MONTH);
		// 再获取截至到本月前每个月的投标量
		List<Integer> twelveMonth = new ArrayList<>(); // 新建一个list来保存数据，默认顺序是从一月到十二月的数据
		for (int i = month; i >= 0; i--) {
			twelveMonth.add(designerRelationshipMapper.selectStatisticalTenderByMonth(i));
		}
		// 再把今年以后月的值赋 0
		for (int i = 12 - month; i > 1; i--) {
			twelveMonth.add(0);
		}
		statisticalSearchTender.setTwelveMonth(twelveMonth);
		// 再获取本周和上周的投标量
		// 先处理上周数据
		List<Integer> lastWeek = new ArrayList<>();
		for (int i = 7; i >= 1; i--) {
			lastWeek.add(designerRelationshipMapper.selectStatisticalTenderLastweek(i));
		}
		statisticalSearchTender.setLastWeek(lastWeek);
		// 再处理本周数据
		// 获取到今天是本周的星期几
		int week;
		week = cale.get(Calendar.DAY_OF_WEEK) - 1;
		if (week == 0) {
			week = 7;
		}
		week -= 1; // 获取到今天与星期一差几天
		List<Integer> thisWeek = new ArrayList<>();
		for (int i = 0; i <= week; i++) {
			thisWeek.add(designerRelationshipMapper.selectStatisticalTenderThisweek(i));
		}
		for (int i = 0; i < 7 - week - 1; i++) { // 对本周以后的几天默认添加数据0
			thisWeek.add(0);
		}
		statisticalSearchTender.setThisWeek(thisWeek);

		// 获取到当前投标量前十的设计师
		List<StatisticalSearchUserBalanceModel> statisticalSearchTenderBalance = null;
		statisticalSearchTenderBalance = designerRelationshipMapper.selectStatisticalTenderTopDesign();
		// 为了前端更好处理，不满十个，凑默认值来填充
		if(statisticalSearchTenderBalance.size() < 10) {
			for( int i = statisticalSearchTenderBalance.size(); i <= 9; i++) {
				statisticalSearchTenderBalance.add(i,new StatisticalSearchUserBalanceModel());
			}
		}
		if (statisticalSearchTenderBalance != null) {
			statisticalSearchTender.setDesignName(statisticalSearchTenderBalance);
		}

		// 获取到当前投标量前十的项目方
		statisticalSearchTenderBalance = null;
		statisticalSearchTenderBalance = designerRelationshipMapper.selectStatisticalTenderTopProject();
		// 为了前端更好处理，不满十个，凑默认值来填充
		if(statisticalSearchTenderBalance.size() < 10) {
			for( int i = statisticalSearchTenderBalance.size(); i <= 9; i++) {
				statisticalSearchTenderBalance.add(i,new StatisticalSearchUserBalanceModel());
			}
		}
		if (statisticalSearchTenderBalance != null) {
			statisticalSearchTender.setProjectName(statisticalSearchTenderBalance);
		}

		return statisticalSearchTender;
	}

	@Override 
	public StatisticalUserCaptialModel getCaptialStatistical() {
		StatisticalUserCaptialModel statisticalUserCaptial = new StatisticalUserCaptialModel(); // new一个返回对象
		BigDecimal testValue = null;
		// 先计算所有关于资金的收入的数据，即项目方打款的钱
		testValue = projectDetailMapper.selectStatisticalAllCaptial();
		if(null != testValue) {
			statisticalUserCaptial.setAllCaptial(testValue);
			testValue = null;
		}		
		// 再计算一个月关于资金的收入的数据
		testValue = projectDetailMapper.selectStatisticalMonthCaptial();
		if(null != testValue) {
			statisticalUserCaptial.setThisMonthCaptial(testValue);
			testValue = null;
		}
		// 再计算上周每天资金收入的数据
		Map<String,BigDecimal> lastWeekIncomeMap = projectDetailMapper.selectStatisticalLastWeekCaptial(LastWeekTimeList);
		List<BigDecimal> lastWeekIncome = new ArrayList<>();
		for (BigDecimal value : lastWeekIncomeMap.values()) {
			lastWeekIncome.add(value);
		}
		statisticalUserCaptial.setLastWeekIncome(lastWeekIncome);
		// 再计算上周每天关于资金支出的数据，即用户提现的资金
		Map<String,BigDecimal> lastWeekExpendMap = capitalAuditMapper.selectStatisticalLastWeekExpend(LastWeekTimeList);
		List<BigDecimal> lastWeekExpend = new ArrayList<>();
		for (BigDecimal value : lastWeekExpendMap.values()) {
			lastWeekExpend.add(value);
		}
		statisticalUserCaptial.setLastWeekExpend(lastWeekExpend);
		/*List<BigDecimal> lastWeekIncome = new ArrayList<>();
		for (int i = 7; i >= 1; i--) {
			BigDecimal temp = projectDetailMapper.selectStatisticalLastWeekCaptial(i);
			if(null != temp) {
				lastWeekIncome.add(temp);
			}else {
				lastWeekIncome.add(new BigDecimal("0"));
			}
		}
		statisticalUserCaptial.setLastWeekIncome(lastWeekIncome);*/

		// 获取到今天是本周的星期几
		// 再计算本周每天资金收入的数据 ， 没有的默认填为0
		Map<String,BigDecimal> thisWeekIncomeMap = projectDetailMapper.selectStatisticalThisWeekCaptial(thisWeekTimeList);
		List<BigDecimal> thisWeekIncome = new ArrayList<>();
		for (BigDecimal value : thisWeekIncomeMap.values()) {
			thisWeekIncome.add(value);
		}
		statisticalUserCaptial.setThisWeekIncome(thisWeekIncome);
		// 查找这周的支出
		// 再计算本周每天资金支出的数据 ， 没有的默认填为0
		Map<String,BigDecimal> thisWeekExpendMap = capitalAuditMapper.selectStatisticalThisWeekExpend(thisWeekTimeList);
		List<BigDecimal> thisWeekExpend = new ArrayList<>();
		for (BigDecimal value : thisWeekExpendMap.values()) {
			thisWeekExpend.add(value);
		}
		statisticalUserCaptial.setThisWeekExpend(thisWeekExpend);
		/*Calendar cale = Calendar.getInstance();
		int week;
		week = cale.get(Calendar.DAY_OF_WEEK) - 1;
		if (week == 0) {
			week = 7;
		}
		week -= 1; // 获取到今天与星期一差几天
		List<BigDecimal> thisWeekIncome = new ArrayList<>();
		for (int i = 0; i <= week; i++) {
			BigDecimal temp = projectDetailMapper.selectStatisticalThisWeekCaptial(i);
			if(null != temp) {
				thisWeekIncome.add(temp);
			}else {
				thisWeekIncome.add(new BigDecimal("0"));
			}
		}
		for (int i = 0; i < 7 - week - 1; i++) { // 对本周以后的几天默认添加数据0
			thisWeekIncome.add(new BigDecimal("0"));
		}
		statisticalUserCaptial.setThisWeekIncome(thisWeekIncome);
		
		// 再计算上周每天关于资金支出的数据，即用户提现的资金
		List<BigDecimal> lastWeekExpend = new ArrayList<>();
		for (int i = 7; i >= 1; i--) {
			BigDecimal temp = capitalAuditMapper.selectStatisticalLastWeekExpend(i);
			if(null != temp) {
				lastWeekExpend.add(temp);
			}else {
				lastWeekExpend.add(new BigDecimal("0"));
			}
		}
		statisticalUserCaptial.setLastWeekExpend(lastWeekExpend);
		// 再计算本周每天资金收入的数据 ， 没有的默认填为0
		List<BigDecimal> thisWeekExpend = new ArrayList<>();
		for (int i = 0; i <= week; i++) {
			BigDecimal temp = capitalAuditMapper.selectStatisticalThisWeekExpend(i);
			if(null != temp) {
				thisWeekExpend.add(temp);
			}else {
				thisWeekExpend.add(new BigDecimal("0"));
			}
		}
		for (int i = 0; i < 7 - week - 1; i++) { // 对本周以后的几天默认添加数据0
			thisWeekExpend.add(new BigDecimal("0"));
		}
		statisticalUserCaptial.setThisWeekExpend(thisWeekExpend);
		*/
		return statisticalUserCaptial;
	}
	
	
	/**
	*  StatisticalAnalysisServiceImpl静态方法，对传过来的list进行填充0数据，让前端方便处理,处理的是用户量前端数据
	*       时间:2019/2/22
	*  @return List<StatisticalSearchUserModel>
	*  @param  List<StatisticalSearchUserModel>
	*  @author 周天
	*
	*/
	private  static List<StatisticalSearchUserModel> parseUserList (List<StatisticalSearchUserModel> statisticalSearchUserCountList ){
			// 如果这个list集合为3，就直接返回
			if(statisticalSearchUserCountList.size() == 3) {
				return statisticalSearchUserCountList;
			}
			// 如果这个list集合为0，就自定义一个list填充进去
			if(statisticalSearchUserCountList.size() == 0) {
				statisticalSearchUserCountList.add(0, new StatisticalSearchUserModel(1001,0)); // 填充默认值
				statisticalSearchUserCountList.add(1, new StatisticalSearchUserModel(1002,0)); // 填充默认值
				statisticalSearchUserCountList.add(2, new StatisticalSearchUserModel(1003,0)); // 填充默认值
				return statisticalSearchUserCountList;
			}
			// 如果这个list集合的长度为1
			if(statisticalSearchUserCountList.size() == 1) {
				if(statisticalSearchUserCountList.get(0).getRoleCode() == 1001) {
					statisticalSearchUserCountList.add(1, new StatisticalSearchUserModel(1002,0)); // 填充默认值
					statisticalSearchUserCountList.add(2, new StatisticalSearchUserModel(1003,0)); // 填充默认值
				}
				if(statisticalSearchUserCountList.get(0).getRoleCode() == 1002) {
					statisticalSearchUserCountList.add(1, new StatisticalSearchUserModel(1001,0)); // 填充默认值
					statisticalSearchUserCountList.add(2, new StatisticalSearchUserModel(1003,0)); // 填充默认值
				}
				if(statisticalSearchUserCountList.get(0).getRoleCode() == 1003) {
					statisticalSearchUserCountList.add(1, new StatisticalSearchUserModel(1001,0)); // 填充默认值
					statisticalSearchUserCountList.add(2, new StatisticalSearchUserModel(1002,0)); // 填充默认值
				}
			}
			// 如果这个list集合的长度为2
				if(statisticalSearchUserCountList.size() == 2) {
					if(statisticalSearchUserCountList.get(0).getRoleCode() == 1001 && 
							statisticalSearchUserCountList.get(1).getRoleCode() == 1002) {
						statisticalSearchUserCountList.add(2, new StatisticalSearchUserModel(1003,0)); // 填充默认值
					}
					if(statisticalSearchUserCountList.get(0).getRoleCode() == 1001 && 
							statisticalSearchUserCountList.get(1).getRoleCode() == 1003) {
						statisticalSearchUserCountList.add(2, new StatisticalSearchUserModel(1002,0)); // 填充默认值
					}
					if(statisticalSearchUserCountList.get(0).getRoleCode() == 1002 && 
							statisticalSearchUserCountList.get(1).getRoleCode() == 1003) {
						statisticalSearchUserCountList.add(2, new StatisticalSearchUserModel(1001,0)); // 填充默认值
					}
				}
			// 对list集合里面三个元素进行排序
				statisticalSearchUserCountList.sort(new Comparator<StatisticalSearchUserModel>() {//Comparator 比较器. 需要实现比较方法
		            @Override
		            public int compare(StatisticalSearchUserModel o1, StatisticalSearchUserModel o2) {
		                return o1.getRoleCode()-o2.getRoleCode();//从小到大 , 如果是o2.getRoleCode()-o1.getRoleCode() 则表示从大到小
		            }
		        });
		return statisticalSearchUserCountList;
		
	}
	/**
	*  StatisticalAnalysisServiceImpl静态方法，对传过来的list进行填充0数据，让前端方便处理,处理的是项目量前端数据
	*       时间:2019/2/22
	*  @return List<StatisticalSearchUserModel>
	*  @param  List<StatisticalSearchUserModel>
	*  @author 周天
	*
	*/
	private  static List<StatisticalSearchUserModel> parseProjectList (List<StatisticalSearchUserModel> statisticalSearchProjectCountList ){
		// 如果这个list集合为3，就直接返回
		if(statisticalSearchProjectCountList.size() == 3) {
			return statisticalSearchProjectCountList;
		}
		// 如果这个list集合为0，就自定义一个list填充进去
		if(statisticalSearchProjectCountList.size() == 0) {
			statisticalSearchProjectCountList.add(0, new StatisticalSearchUserModel(1004,0)); // 填充默认值
			statisticalSearchProjectCountList.add(1, new StatisticalSearchUserModel(1005,0)); // 填充默认值
			statisticalSearchProjectCountList.add(2, new StatisticalSearchUserModel(1006,0)); // 填充默认值
			return statisticalSearchProjectCountList;
		}
		// 如果这个list集合的长度为1
		if(statisticalSearchProjectCountList.size() == 1) {
			if(statisticalSearchProjectCountList.get(0).getRoleCode() == 1004) {
				statisticalSearchProjectCountList.add(1, new StatisticalSearchUserModel(1005,0)); // 填充默认值
				statisticalSearchProjectCountList.add(2, new StatisticalSearchUserModel(1006,0)); // 填充默认值
			}
			if(statisticalSearchProjectCountList.get(0).getRoleCode() == 1005) {
				statisticalSearchProjectCountList.add(1, new StatisticalSearchUserModel(1004,0)); // 填充默认值
				statisticalSearchProjectCountList.add(2, new StatisticalSearchUserModel(1006,0)); // 填充默认值
			}
			if(statisticalSearchProjectCountList.get(0).getRoleCode() == 1006) {
				statisticalSearchProjectCountList.add(1, new StatisticalSearchUserModel(1004,0)); // 填充默认值
				statisticalSearchProjectCountList.add(2, new StatisticalSearchUserModel(1005,0)); // 填充默认值
			}
		}
		// 如果这个list集合的长度为2
			if(statisticalSearchProjectCountList.size() == 2) {
				if(statisticalSearchProjectCountList.get(0).getRoleCode() == 1004 && 
						statisticalSearchProjectCountList.get(1).getRoleCode() == 1005) {
					statisticalSearchProjectCountList.add(2, new StatisticalSearchUserModel(1006,0)); // 填充默认值
				}
				if(statisticalSearchProjectCountList.get(0).getRoleCode() == 1004 && 
						statisticalSearchProjectCountList.get(1).getRoleCode() == 1006) {
					statisticalSearchProjectCountList.add(2, new StatisticalSearchUserModel(1005,0)); // 填充默认值
				}
				if(statisticalSearchProjectCountList.get(0).getRoleCode() == 1005 && 
						statisticalSearchProjectCountList.get(1).getRoleCode() == 1006) {
					statisticalSearchProjectCountList.add(2, new StatisticalSearchUserModel(1004,0)); // 填充默认值
				}
			}
		// 对list集合里面三个元素进行排序
			statisticalSearchProjectCountList.sort(new Comparator<StatisticalSearchUserModel>() {//Comparator 比较器. 需要实现比较方法
	            @Override
	            public int compare(StatisticalSearchUserModel o1, StatisticalSearchUserModel o2) {
	                return o1.getRoleCode()-o2.getRoleCode();//从小到大 , 如果是o2.getRoleCode()-o1.getRoleCode() 则表示从大到小
	            }
	        });
			
	return statisticalSearchProjectCountList;
	
  }
}
