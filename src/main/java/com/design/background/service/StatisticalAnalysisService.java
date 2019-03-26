package com.design.background.service;

import com.design.background.model.StatisticalProjectModel;
import com.design.background.model.StatisticalSearchTenderModel;
import com.design.background.model.StatisticalUserCaptialModel;
import com.design.background.model.StatisticalUserModel;

/**
 *平台各数据统计分析service层
 *  时间:2019/2/20
 * 最后修改时间:2019/2/20
 * 注意事项:无
* @author 周天
*
*/
public interface StatisticalAnalysisService {
	
	/**
	*  查询出当前库中关于用户的所有统计数据并进行处理后返回给前端controller层
	*  时间:2019/2/20
	*  @return StatisticalUserModel 计算成功返回对象，计算失败返回null，controller层进行处理
	*  @author 周天
	*
	*/
	public StatisticalUserModel getUserStatistical();
	
	/**
	*  查询出当前库中关于项目的所有统计数据并进行处理后返回给前端controller层
	*  时间:2019/2/21
	*  @return StatisticalUserModel 计算成功返回对象，计算失败返回null，controller层进行处理
	*  @author 周天
	*
	*/
	public StatisticalProjectModel getProjectStatistical();
	
	/**
	*  查询出当前库中关于投标量的所有统计数据并进行处理后返回给前端controller层
	*  时间:2019/2/21
	*  @return StatisticalSearchTenderModel 计算成功返回对象，计算失败返回null，controller层进行处理
	*  @author 周天
	*
	*/          
	public StatisticalSearchTenderModel getTenderStatistical();
	
	/**
	*  查询出当前库中关于资金交易的所有统计数据并进行处理后返回给前端controller层
	*  时间:2019/2/21
	*  @return StatisticalUserCaptialModel 计算成功返回对象，计算失败返回null，controller层进行处理
	*  @author 周天
	*
	*/          
	public StatisticalUserCaptialModel getCaptialStatistical();

}
