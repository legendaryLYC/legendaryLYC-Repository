package com.design.background.controller.admin;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.design.background.entity.DicAdvertisingLocation;
import com.design.background.entity.SysUser;
import com.design.background.model.ResultData;
import com.design.background.service.DicAdvertLocationService;
import com.design.background.util.Utility;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @Author：周天
 * @Date： 2019/2/13
 * @Description：广告类型字典表的controller层
 */

@Controller
@Transactional
@RequestMapping("/admin/advertLocation")
public class DicAdvertLocationController {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageManageController.class);
	
	@Autowired
	DicAdvertLocationService dicAdvertLocationService;
	
	 /**
     * @Description: 展示广告类型字典表列表的controller
     * @Param: []
     * @return: java.lang.String
     * @Author: 周天
     * @Date: 2019/2/13
     */
	@RequestMapping(value = {"","/"})
	public String showALLMessage  (Model model ,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo ,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize ,
			DicAdvertisingLocation advert) {
		PageHelper.startPage(pageNo, pageSize);
		List<DicAdvertisingLocation> advertList = dicAdvertLocationService.showAllAdvertLocation(advert);
		//进行分页显示的代码部分
		PageInfo<DicAdvertisingLocation>  pageInfo = new PageInfo<>(advertList);
		if (pageNo > pageInfo.getPages()) {
			pageNo = pageInfo.getPages();
			PageHelper.startPage(pageNo, pageSize);
			advertList = dicAdvertLocationService.showAllAdvertLocation(advert);
			pageInfo = new PageInfo<DicAdvertisingLocation>(advertList);
		}
		model.addAttribute("advert", advert);
		model.addAttribute("pageInfo", pageInfo);
        return "admin/dic/show-advertlocation";
    }
	/**
	 * 进入广告类型字典表发布页面,添加新的广告类型字典表
	 *  时间:2019/2/14
	 * 最后修改时间:2019/2/14
	 * 注意事项:无
	* @author 周天
	*
	*/
	@RequestMapping(value = "/addAdvertHtml")
	public String addAdvertHtml(Model model) {
		return "admin/dic/add-advertlocation";
	}
	
	/**
	 * 在发布广告类型字典表页面,添加新的广告类型字典表
	 *  时间:2019/2/14
	 * 最后修改时间:2019/2/14
	 * 注意事项:无
	* @author 周天
	*
	*/
	@ResponseBody
	@RequestMapping(value = {"/addAdvert"})
	public ResultData addAdvert(DicAdvertisingLocation advert) {
		
		 ResultData result = new ResultData<Integer>();
		 int flag = dicAdvertLocationService.allAdvertLocationAdd(advert);
		 if(flag > 0) {
			 result.setCode("000000");
		 }
		 else {
			 result.setMessage("添加失败");
		 }
		return result;
	}
	/**
	 * 进入广告类型字典表删除操作,删除单一或选定的一些的广告类型字典表
	 *  时间:2019/1/26
	 * 最后修改时间:2019/1/13
	 * 注意事项:无
	* @author 周天
	*
	*/
	@ResponseBody
	@RequestMapping(value = { "/deleteAdvert"}, produces = "application/json", consumes = "application/json")
	public ResultData deleteAdvert(@RequestBody Long[] id ) {	
		 ResultData result = new ResultData<Integer>();
		int flag = -1;
		for(int i = 0;i < id.length; i++) { // 循环遍历传过来需要删除的id数组
			flag = dicAdvertLocationService.deleteAdvertLocation(id[i]);
			if(flag <= 0)
			{
				logger.info("====调用deletePhone方法删除操作失败====");
				return result;
			}
		}
		result.setCode("000000");
		return result;
	}
	
	/**
	 * 进入广告类型字典表编辑页面,编辑广告类型字典表信息
	 *  时间:2019/2/14
	 * 最后修改时间:2019/2/14
	 * 注意事项:无
	* @author 周天
	*
	*/
	@RequestMapping(value = "/editAdvertHtml/{id}")
	public String editAdvertHtml(Model model, @PathVariable("id") Long id) {		
		DicAdvertisingLocation advert = dicAdvertLocationService.selectById(id); // 通过分割得到的id进行查找		
		model.addAttribute("advert", advert);
		return "admin/dic/editor-advertlocation";
	}
	
	/**
	 * 进入广告类型字典表信息更新操作,更新单一广告类型字典表
	 *  时间:2019/1/26
	 * 最后修改时间:2019/1/26
	 * 注意事项:无
	* @author 周天
	*
	*/
	@ResponseBody
	@RequestMapping(value = { "/updateAdvert"} , produces = "application/json;charset=UTF-8",method ={RequestMethod.POST})
	public ResultData updateAdvert(DicAdvertisingLocation advert) {
		ResultData result = new ResultData<Integer>();
		int flag = -1;
		 flag = dicAdvertLocationService.updateAdvertLocation(advert);
		if(flag <= 0) {
			return result;
		}else {
			result.setCode("000000");
		}
		return result;
	}

}
