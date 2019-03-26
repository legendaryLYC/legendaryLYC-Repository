package com.design.background.controller.admin;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import com.design.background.service.impl.StatisticalAnalysisServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.design.background.entity.NotificationManage;
import com.design.background.entity.SysUser;
import com.design.background.model.ResultData;
import com.design.background.model.StatisticalProjectModel;
import com.design.background.model.StatisticalSearchTenderModel;
import com.design.background.model.StatisticalUserCaptialModel;
import com.design.background.model.StatisticalUserModel;
import com.design.background.service.StatisticalAnalysisService;
import com.design.background.util.Utility;

/**
 * @Author：周天
 * @Date： 2019/2/20
 * @Description：平台各数据统计分析处理的controller层
 */
@Controller
@Transactional
@RequestMapping("/admin/statistical")
public class StatisticalAnalysisController {
	
	private static final Logger logger = LoggerFactory.getLogger(StatisticalAnalysisController.class);
	
	@Autowired
	StatisticalAnalysisService statisticalAnalysisService;
	
	/**
     * @Description: 展示统计分析页面的controller
     * @Param: []
     * @return: java.lang.String
     * @Author: 周天
     * @Date: 2019/2/20
     */
	@RequestMapping(value = {"","/"})
	public String showStatistical() {
        return "welcome";
    }
	
	/**
	 * 在进入统计分析页面后，通过ajax请求数据，填出前端页面
	 *  时间:2019/2/22
	 * 最后修改时间:2019/2/22
	 * 注意事项:无
	* @author 周天
	*
	*/
	@ResponseBody
	@RequestMapping(value = {"/requestData"})
	public Map<String,Object> requestUserData() {
		
		Map<String,Object> resultData = new HashMap<>();
		StatisticalAnalysisServiceImpl.setLastWeekTimeList();
		StatisticalUserModel statisticalUser = statisticalAnalysisService.getUserStatistical();
		StatisticalProjectModel statisticalProject = statisticalAnalysisService.getProjectStatistical();
		StatisticalSearchTenderModel statisticalTender = statisticalAnalysisService.getTenderStatistical();
		StatisticalUserCaptialModel statisticalCaptial = statisticalAnalysisService.getCaptialStatistical();
		if(statisticalUser != null) {
			resultData.put("User", statisticalUser);
		}
		if(statisticalProject != null) {
			resultData.put("statisticalProject", statisticalProject);
		}
		if(statisticalTender != null) {
			resultData.put("statisticalTender", statisticalTender);
		}
		if(statisticalCaptial != null) {
			resultData.put("statisticalCaptial", statisticalCaptial);
		}
		return resultData;
	}

}
