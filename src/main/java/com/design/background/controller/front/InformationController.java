package com.design.background.controller.front;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.design.background.common.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.design.background.config.FrontConfig;
import com.design.background.controller.admin.LeadingUserProjectorController;
import com.design.background.entity.DesigntypeExtend;
import com.design.background.entity.DicArea;
import com.design.background.entity.DicCity;
import com.design.background.entity.ExampleProject;
import com.design.background.entity.LeadingUser;
import com.design.background.model.ResultData;
import com.design.background.service.DesignerTypeExtendService;
import com.design.background.service.DicService;
import com.design.background.service.LeadingUserProjectorService;
import com.design.background.util.DesignFileUtils;


/**个人信息相关功能
 * @author 任松 
 * 
 */
@Controller
@RequestMapping("/center")
public class InformationController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(LeadingUserProjectorController.class);

	@Autowired
	private LeadingUserProjectorController leadingUserProjectorController;
	@Autowired
	private DesignerTypeExtendService designerTypeExtendService;
	@Autowired
	private HttpSession httpSession;
	@Autowired
	private DicService dicService;
	
	@Autowired
	private LeadingUserProjectorService leadingUserProjectorService; // 调用LeadingUserProjectorService管理接口。
	/**任松
	 * 修改用户基本信息
	 * @param model
	 * @param leadingUser
	 * @param headImage
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/informationupdate")
	public ResultData update(LeadingUser leadingUser,
			  @RequestParam(name="headImage",required=false) MultipartFile headImage,
			  @RequestParam(name= "areaId",required = false) Integer area,
			  @RequestParam(name= "provinceId",required = false) Integer provinceId,
			  @RequestParam(name= "cityId",required = false) Integer cityId
			  ) {
		String headImagePath=null;
		ResultData resultData=new ResultData();
		 int result = 0;
		 //获取用户id
		 LeadingUser NewleadingUser=null;
		 NewleadingUser = (LeadingUser) httpSession.getAttribute(FrontConfig.FONT_SESSION);
		 leadingUser.setId(NewleadingUser.getId());
		 leadingUser.setArea(area);
		 leadingUser.setCityId(cityId);
		 leadingUser.setProvinceId(provinceId);
		// 更新session
		 NewleadingUser.setArea(area);
		 NewleadingUser.setSexType(leadingUser.getSexType());
		 NewleadingUser.setTel(leadingUser.getTel());
		 NewleadingUser.setNickname(leadingUser.getNickname());
		 NewleadingUser.setIntroduction(leadingUser.getIntroduction());
		 NewleadingUser.setEmail(leadingUser.getEmail());
		 
		if(headImage != null){
            //上传到阿里云，从阿里云返回一个地址
            headImagePath = DesignFileUtils.uploadSingleFile(headImage);
            // 存入路径
            if(StringUtils.isNoneBlank(headImagePath)){
            	leadingUser.setPhoto(headImagePath); 
            	NewleadingUser.setPhoto(headImagePath);
            }
        }
		resultData=resultData.setCodeAndMessage(leadingUserProjectorService.updateById(leadingUser), "修改成功", "修改失败");
		httpSession.removeAttribute(FrontConfig.FONT_SESSION);
		httpSession.setAttribute(FrontConfig.FONT_SESSION, NewleadingUser);
		httpSession.setAttribute("user", leadingUser.getNickname());
		httpSession.removeAttribute("photo");
		httpSession.setAttribute("photo", leadingUser.getPhoto());
		//更新
		return resultData;
		//return ResultData.setCodeAndMessage(result,"插入成功","插入失败"); // 返回json
	}
	
	
	
	
	
	
	/**任松
	 * 更新设计师所属类别
	 * @param id
	 * @param typecode
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/majorinformationupdate")
	public ResultData Majorinformationupdate(@RequestParam(name= "userid",required = false) Long id,
			  @RequestParam(name= "typecode",required = false) String typecode
			  ) {
				
		ResultData resultData=new ResultData();
		DesigntypeExtend designtypeExtend=designerTypeExtendService.selectByUserid(id);
		resultData.setCode("999999");
		designtypeExtend.setCode(typecode);
		int key=designerTypeExtendService.updateByPrimaryKeySelective(designtypeExtend);
		if(key==1) {
			resultData.setCode("000000");
		}
		return resultData;
	}
	
	
	
	
	
	/**
	 * ajax返回要求的省市区字典
	 */
	@ResponseBody
	@RequestMapping(value =  "/getareadic", produces = "application/json;charset=utf-8",method ={RequestMethod.POST})
	public Object[] getAreaDic(
			@RequestParam(value = "who", required = false)String who,
			@RequestParam(value = "code", required = false)String code) {
		Object[] objects = new Object[0];
		//分类讨论是什么的code
		if("province".equals(who)) {
			List<DicCity> citys = dicService.getCitysByProvinceId(code);
			objects = citys.toArray();
		}else if("city".equals(who)) {
			List<DicArea> areas = dicService.getAreaByCityId(code);
			objects = areas.toArray();
		}
		return objects;
	}
	
}
