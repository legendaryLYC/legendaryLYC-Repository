package com.design.background.controller.admin;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.multipart.MultipartFile;

import com.design.background.entity.AdvertisingManagement;
import com.design.background.entity.DicAdvertisingLocation;
import com.design.background.entity.SysUser;
import com.design.background.mapper.DicAdvertisingLocationMapper;
import com.design.background.mapper.SysUserMapper;
import com.design.background.model.ResultData;
import com.design.background.service.AdvertManageService;
import com.design.background.util.DesignFileUtils;
import com.design.background.util.StringToDate;
import com.design.background.util.Utility;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @Author：周天
 * @Date： 2019/2/26
 * @Description：广告栏位发布与管理的controller层
 */

@Controller
@Transactional
@RequestMapping("/admin/advert")
public class AdvertManageController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdvertManageController.class);
	
	@Autowired
	AdvertManageService advertManageService;
	
	@Autowired
	private SysUserMapper userMapper;
	
	@Autowired
	private DicAdvertisingLocationMapper dicAdvertisingLocationMapper;
	
	  /**
     * @Description: 展示广告列表的controller
     * @Param: []
     * @return: java.lang.String
     * @Author: 周天
     * @Date: 2019/2/26
     */
	@RequestMapping(value = {"","/"})
	public String showALLAdvert  (Model model ,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo ,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize ,
			AdvertisingManagement advert) {
		PageHelper.startPage(pageNo, pageSize);
		List<AdvertisingManagement> advertList = advertManageService.showAllAdvert(advert);
		//进行分页显示的代码部分
		PageInfo<AdvertisingManagement>  pageInfo = new PageInfo<>(advertList);
		if (pageNo > pageInfo.getPages()) {
			pageNo = pageInfo.getPages();
			PageHelper.startPage(pageNo, pageSize);
			advertList = advertManageService.showAllAdvert(advert);
			pageInfo = new PageInfo<AdvertisingManagement>(advertList);
		}
		List<DicAdvertisingLocation> locationList = dicAdvertisingLocationMapper.selectAll(new DicAdvertisingLocation());
		
		model.addAttribute("locationInfo",locationList);
		model.addAttribute("advert", advert);
		model.addAttribute("pageInfo", pageInfo);
        return "admin/advert/list";
    }

	/**
	 * 进入广告发布页面,添加新的广告
	 *  时间:2019/2/14
	 * 最后修改时间:2019/2/14
	 * 注意事项:无
	* @author 周天
	*
	*/
	@RequestMapping(value = "/addAdvertHtml")
	public String addAdvertHtml(Model model) {		
		List<DicAdvertisingLocation> locationList = dicAdvertisingLocationMapper.selectAll(new DicAdvertisingLocation());
		model.addAttribute("locationInfo",locationList);
		return "admin/advert/add";
	}
	
	/**
	 * 在发布广告页面,添加新的广告
	 *  时间:2019/2/14
	 * 最后修改时间:2019/2/14
	 * 注意事项:无
	* @author 周天
	*
	*/
	@ResponseBody
	@RequestMapping(value = {"/addAdvert"})
	public ResultData addAdvert(AdvertisingManagement advert ,
			@RequestParam(value = "creatTimeString", required = false) String creatTimeString ,
			@RequestParam(value = "endTimeString", required = false) String endTimeString , 
			@RequestParam(value="coverImage", required=false) MultipartFile coverImage) {
		
		ResultData resultData = new ResultData<Integer>();
		//日期字符转化为Date型并set到order对象
		java.util.Date creatDate = StringToDate.toUtilDate(creatTimeString);
		advert.setCreatTime(creatDate);
		java.util.Date endDate = StringToDate.toUtilDate(endTimeString);
		advert.setTerm(endDate);
		// 获取操作的用户名存到对象的sender
		String loginUserName = Utility.getCurrentUsername();
		SysUser loginUser = userMapper.selectByUsername(loginUserName);
		advert.setUsername(loginUser.getUsername());
		
		
		// 将文件上传到阿里云
				String coverImagePath = null;
		        if(coverImage != null){
		            //上传到阿里云
		            coverImagePath = DesignFileUtils.uploadSingleFile(coverImage);
		            if( coverImagePath == null){
		    			resultData.setCode("999999"); 
		    			resultData.setMessage("上传文件失败,请重新上传！");// 如果上传失败返回code"999999",并返回错误信息
		    			return resultData;
		            }
		        }
		     advert.setPictureUrl(coverImagePath);
		 int flag = advertManageService.allAdvertAdd(advert);
		 if(flag > 0) {
			 resultData.setCode("000000");
		 }
		 else {
			 resultData.setMessage("添加失败!");
		 }
		return resultData;
	}
	/**
	 * 进入广告删除操作,删除单一或选定的一些的广告
	 *  时间:2019/1/10
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
			flag = advertManageService.delateAdvert(id[i]);
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
	 * 进入广告编辑页面,编辑广告信息
	 *  时间:2019/2/14
	 * 最后修改时间:2019/2/14
	 * 注意事项:无
	* @author 周天
	*
	*/
	@RequestMapping(value = "/editAdvertHtml/{id}")
	public String editAdvertHtml(Model model, @PathVariable("id") Long id) {
		AdvertisingManagement record = new AdvertisingManagement();
		AdvertisingManagement advert = null;
		if(id != null) {
			record.setId(id);
			 advert = advertManageService.selcetByAdvert(record); // 通过分割得到的id进行查找		
		}	
		model.addAttribute("advert", advert);
		List<DicAdvertisingLocation> locationList = dicAdvertisingLocationMapper.selectAll(new DicAdvertisingLocation());		
		model.addAttribute("locationInfo",locationList);
		return "admin/advert/edit";
	}
	
	/**
	 * 进入广告信息更新操作,更新单一广告
	 *  时间:2019/2/27
	 * 最后修改时间:2019/2/27
	 * 注意事项:无
	* @author 周天
	*
	*/
	@ResponseBody
	@RequestMapping(value = { "/updateAdvert"} , produces = "application/json;charset=UTF-8",method ={RequestMethod.POST})
	public ResultData updateAdvert(AdvertisingManagement advert,
			@RequestParam(value = "creatTimeString", required = false) String creatTimeString ,
			@RequestParam(value = "endTimeString", required = false) String endTimeString , 
			@RequestParam(value="coverImage", required=false) MultipartFile coverImage) {
		ResultData result = new ResultData<Integer>();
		
		//日期字符转化为Date型并set到order对象
		java.util.Date creatDate = StringToDate.toUtilDate(creatTimeString);
		advert.setCreatTime(creatDate);
		java.util.Date endDate = StringToDate.toUtilDate(endTimeString);
		advert.setTerm(endDate);
		// 获取操作的用户名存到对象的sender
		String loginUserName = Utility.getCurrentUsername();
		SysUser loginUser = userMapper.selectByUsername(loginUserName);
		advert.setUsername(loginUser.getUsername());
		
		
		// 将文件上传到阿里云
				String coverImagePath = null;
		        if(coverImage != null){
		            //上传到阿里云
		            coverImagePath = DesignFileUtils.uploadSingleFile(coverImage);
		            if( coverImagePath == null){
		    			result.setCode("999999"); 
		    			result.setMessage("上传文件失败,请重新上传！");// 如果上传失败返回code"999999",并返回错误信息
		    			return result;
		            }
		        }
		        advert.setPictureUrl(coverImagePath);
		int flag = -1;
		 flag = advertManageService.updateAdvert(advert);
		if(flag <= 0) {
			return result;
		}else {
			result.setCode("000000");
		}
		return result;
	}

	/**
	 * 选中广告
	 * @author 孟晓冬
	 */
	@ResponseBody
	@RequestMapping(value = { "/set"}, produces = "application/json", consumes = "application/json")
	public Integer set(Long id) {

		int result = advertManageService.selectAdvertising(id);

		return result;
	}
}
