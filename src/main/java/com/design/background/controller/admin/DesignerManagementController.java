package com.design.background.controller.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.design.background.entity.DicProjectComponent;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.design.background.entity.DesigntypeExtend;
import com.design.background.entity.LeadingUser;
import com.design.background.form.DesignerForm;
import com.design.background.form.ProjectorForm;
import com.design.background.mapper.DesigntypeExtendMapper;
import com.design.background.mapper.DicProjectComponentMapper;
import com.design.background.mapper.LeadingUserMapper;
import com.design.background.model.ResultData;
import com.design.background.service.DesignerManagementService;
import com.design.background.service.DicProjectComponentService;
import com.design.background.service.DicService;
import com.design.background.service.LeadingUserProjectorService;
import com.design.background.util.DesignFileUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
/**
 * @Author：宋博通
 * @Date： 2019/2/13
 * @Description： 这是设计师列表的增删改查controller
 */
@Controller
@RequestMapping("/admin/Usermanagement")
public class DesignerManagementController {
	private static final Logger logger = LoggerFactory.getLogger(DesignerManagementController.class);
	@Autowired
	private DesignerManagementService designerManageService;
	
	@Autowired
	private LeadingUserMapper leadingUserMapper; 
	
	@Autowired
	private DesigntypeExtendMapper designtypeExtendMapper;
	
	@Autowired
	private DicService dicService;
	
	@Autowired
	private DicProjectComponentMapper dicProjectComponentMapper;
	@RequestMapping(value = { "", "/designer" })
    public String showProjectExampleList(Model model,
    		@RequestParam(value = "TimeString", required = false) String TimeString,
    		@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
    		@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize)
    {  
    	//分页
    	PageHelper.startPage(pageNo, pageSize);
    	List<DesignerForm> result = leadingUserMapper.selectAlldesigner();
    	PageInfo<DesignerForm> pageInfo = new PageInfo<DesignerForm>(result);
    	if (pageNo > pageInfo.getPages()) {
    		pageNo = pageInfo.getPages();
    		PageHelper.startPage(pageNo, pageSize);
    		result = leadingUserMapper.selectAlldesigner();
    		pageInfo = new PageInfo<DesignerForm>(result);
    	}
    	//列出所有设计师
    	List<DesignerForm> alldesigners = leadingUserMapper.selectAlldesigner();
    	
        model.addAttribute("alldesigners",alldesigners);
        model.addAttribute("pageInfo",pageInfo);
        
        return "admin/designermanagement/designerlist";
    }
	
	//查找
	@RequestMapping(value = { "", "/designer/search" })
    public String SerchList(Model model,String designername,
    		@RequestParam(value = "TimeString", required = false) String TimeString,
    		@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
    		@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize)
    {      	
		//分页
    	PageHelper.startPage(pageNo, pageSize);
    	List<DesignerForm> result = leadingUserMapper.selectdesignerByname(designername);
    	PageInfo<DesignerForm> pageInfo = new PageInfo<DesignerForm>(result);
    	if (pageNo > pageInfo.getPages()) {
    		pageNo = pageInfo.getPages();
    		PageHelper.startPage(pageNo, pageSize);
    		result = leadingUserMapper.selectdesignerByname(designername);
    		pageInfo = new PageInfo<DesignerForm>(result);
    	}
 
    	model.addAttribute("designername",designername);
        model.addAttribute("alldesigners",result);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/designermanagement/designerlist";
    }
	
	 //批量删除
    @ResponseBody
    @RequestMapping(value = "/delete", produces = "application/json", consumes = "application/json")
    public Integer delete(@RequestBody Long[] ids) {
        
            for (long id : ids) {
            	designerManageService.deleteDesigner(id);// 删除leading表
            	designtypeExtendMapper.deleteByuserid(id);// 删除扩展表
            }
            return 1;
    }
  //单个删除
    @ResponseBody
    @RequestMapping(value = "/deleteone")
    public Integer deleteone(Long id) {
    		designerManageService.deleteDesigner(id);// 删除leading表
        	designtypeExtendMapper.deleteByuserid(id);// 删除扩展表
            return 1;
    }
    
    //设计师编辑页面
    @RequestMapping(value = { "", "/designeredit" })
    public String designeredit(Model model,Long id)
    {  
    	DesignerForm designerdetail = leadingUserMapper.finddesignerById(id);
		String code = designerdetail.getDesignerTypeCode();
		String[] codes = code.split(",");
    	//查询当前修改设计师信息
		LeadingUser leadingUser=new LeadingUser(); 
		leadingUser=leadingUserMapper.selectByPrimaryKey(id);
		//获取地区选择的字典表
		ProjectorForm projectorForm = new ProjectorForm();
		try { 
			projectorForm = leadingUserProjectorService.selectUserAreaById(id,leadingUser.getArea()); 
		} 
		catch (Exception e) {
		logger.info("id获取失败"+e.getMessage()); 
		}
		List<String> isCheck = new ArrayList<>();
		List<DicProjectComponent>lsit= dicProjectComponentMapper.selectAll();
		Map<String,Object> map = new HashMap<>();
		for (int i = 0; i <lsit.size() ; i++) {
			isCheck.add("0");
			for (int j = 0; j <codes.length ; j++) {
				if(codes[j].equals(lsit.get(i).getCode()+"")){
					isCheck.set(i,"1");
				}
			}
		}
		model.addAttribute("isCkecked",isCheck);
		model.addAttribute("list",lsit);
		//加载下拉菜单的字典List
		model.addAttribute("codes",codes);
		model.addAttribute("projectorForm",projectorForm); 
		model.addAttribute("roles",dicService.getRoles());
		model.addAttribute("provinces",dicService.getProvinces());
    	model.addAttribute("designerdetail",designerdetail);
    	model.addAttribute("leadingUser",leadingUser);
    	return "admin/designermanagement/designeredit";
    }
    
    //设计师详情页面
    @RequestMapping(value = { "", "/designerdetail" })
    public String designerdetail(Model model,Long id)
    {  
    	DesignerForm designerdetail = leadingUserMapper.selectdesignerById(id);
    	//查询当前修改设计师信息
		LeadingUser leadingUser = leadingUserMapper.selectByPrimaryKey(id);
    	//获取地区选择的字典表
    	ProjectorForm projectorForm = new ProjectorForm();
    	try { 
    		projectorForm = leadingUserProjectorService.selectUserAreaById(id,designerdetail.getArea()); 
    	} 
    	catch (Exception e) {
    		logger.info("id获取失败"+e.getMessage()); 
    	}
    	//加载下拉菜单的字典List
    	model.addAttribute("projectorForm",projectorForm); 
    	model.addAttribute("provinces",dicService.getProvinces());
    	model.addAttribute("designerdetail",designerdetail);
    	model.addAttribute("leadingUser",leadingUser);
    	return "admin/designermanagement/designerdetail";
    }
    
    @Autowired
	private LeadingUserProjectorService leadingUserProjectorService; // 调用LeadingUserProjectorService管理接口。
    
    //设计师提交修改保存
    @ResponseBody
    @RequestMapping(value = { "", "/designersave" })
    public ResultData designersave(LeadingUser leadingUser,String [] goodatcode,
    		@RequestParam(value="headImage",required=false) MultipartFile headImage,
    		@RequestParam(value="areaId",required=false) Integer areaId,
    		@RequestParam(value="cityId",required=false) Integer cityId,
    		@RequestParam(value="provinceId",required=false) Integer provinceId)
    {  
    	/**
    	 * Long id,String username,String nickname,String goodat,String tel,String email,String createtime,
    		String introduction,String commonAddress,String AddressNumber,String photo,String sexType,String qualifyScore,String roleCode,
    		String password
    	 */
		System.err.println(headImage);
    	String headImagePath=null;
		if(headImage != null){
            //上传到阿里云，从阿里云返回一个地址
            headImagePath = DesignFileUtils.uploadSingleFile(headImage);
			System.err.println("返回阿里云地址"+headImagePath);
            // 存入路径
            if(StringUtils.isNoneBlank(headImagePath)){
            	leadingUser.setPhoto(headImagePath); 
            }
        }
		
    	System.out.println(goodatcode.length);
    	String typeCode = "";
    	//字符串存储
		//typeCode = StringUtils.join(goodatcode, "|");
    	typeCode =goodatcode[0];
    	System.out.println(typeCode);
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date date = new Date();
    	String time = sdf.format(date);
    	Date updatatime = new Date();
		try {
			updatatime = sdf.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        ResultData result=new ResultData();
        
        leadingUser.setUpdateTime(updatatime);
        leadingUser.setArea(areaId);
        leadingUser.setCityId(cityId);
        leadingUser.setProvinceId(provinceId);
        leadingUserMapper.updateByPrimaryKeySelective(leadingUser);

        Long id = leadingUser.getId();

        DesigntypeExtend designtypeExtend = designtypeExtendMapper.selectByUserid(id);
        System.out.println(designtypeExtend.getCode());
        designtypeExtend.setCode(typeCode);
        designtypeExtendMapper.updateByPrimaryKeySelective(designtypeExtend);
        
        result.setCode("1");
        return result;
    }
    //设计师添加页面
    @RequestMapping(value = { "", "/designereadd" })
    public String designereadd(Model model)
    {  
    	model.addAttribute("provinces",dicService.getProvinces());	
    	model.addAttribute("designertype",dicProjectComponentMapper.selectAll());
    	return "admin/designermanagement/designeraddpage";
    }
    //设计师提交添加保存
    @ResponseBody
    @RequestMapping(value = { "", "/designersaveadd" })
    public ResultData designersaveadd(Model model,DesignerForm designerForm,LeadingUser leadingUser,String [] goodatcode,
    		@RequestParam(value="headImage",required=false) MultipartFile headImage,
    		@RequestParam(value="areaId",required=false) Integer areaId,
    		@RequestParam(value="cityId",required=false) Integer cityId,
    		@RequestParam(value="provinceId",required=false) Integer provinceId)
    {  
    	String headImagePath=null;
		if(headImage != null){
            //上传到阿里云，从阿里云返回一个地址
            headImagePath = DesignFileUtils.uploadSingleFile(headImage);
            // 存入路径
            if(StringUtils.isNoneBlank(headImagePath)){
            	leadingUser.setPhoto(headImagePath); 
            }
        }

    	String typeCode = "";
    	//字符串存储
		//typeCode = StringUtils.join(goodatcode, "|");
    	typeCode =goodatcode[0];
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date date = new Date();
    	String time = sdf.format(date);
    	Date createtime = new Date();
		try {
			createtime = sdf.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        ResultData result=new ResultData();
        leadingUser.setCreateTime(createtime);
        leadingUser.setArea(areaId);
        leadingUser.setCityId(cityId);
        leadingUser.setProvinceId(provinceId);
        leadingUserMapper.insertreturnId(leadingUser);
        
        DesigntypeExtend designtypeExtend =new DesigntypeExtend();
        Long userid = leadingUser.getId();
        designtypeExtend.setUserId(userid);
        designtypeExtend.setCode(typeCode);

        designtypeExtendMapper.insert(designtypeExtend);
        result.setCode("1");
        return result;
    }

}
