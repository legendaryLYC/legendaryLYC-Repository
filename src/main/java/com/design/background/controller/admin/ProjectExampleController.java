package com.design.background.controller.admin;

import com.design.background.entity.ExampleProject;
import com.design.background.entity.SysUser;
import com.design.background.mapper.SysUserMapper;
import com.design.background.model.ResultData;
import com.design.background.service.ExampleProjectService;
import com.design.background.util.DesignFileUtils;
import com.design.background.util.StringToDate;
import com.design.background.util.Utility;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * @Author： 李紫林
 * @Date： 2019/2/13
 * @Description： 这是后台案例项目的增删改查controller
 */
@Controller
public class ProjectExampleController {
    /**
     * @Description: 展示案例项目列表的controller
     * @Param: []
     * @return: java.lang.String
     * @Author: 李紫林
     * @Date: 2019/2/13
     */
    @Autowired
    private ExampleProjectService exampleProjectService;

    @Autowired
    private SysUserMapper userMapper;

    @RequestMapping("/admin/example/showExample")
    public String showProjectExampleList(Model model,
                                         @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo, // 页码
                                         @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, // 页面容纳条数
                                         @RequestParam(value = "exampleName", required = false, defaultValue = "") String exampleName,
                                         @RequestParam(value = "startDate", required = false) String startDate, // 项目案例开始日期范围
                                         @RequestParam(value = "endDate", required = false) String endDate ) // 项目案例结束日期范围
    {
        PageHelper.startPage(pageNo, pageSize);
        List<ExampleProject> exampleList = null;
        if ((exampleName != null && !"".equals(exampleName.trim()))||(startDate != null && !"".equals(startDate.trim()))||(endDate != null && !"".equals(endDate.trim())))
        {
            Date start = StringToDate.toUtilDate(startDate);
            Date end = StringToDate.toUtilDate(endDate);
            exampleList = exampleProjectService.selectExample(exampleName,start,end); // 模糊查询案例
            model.addAttribute("startDate", startDate.trim());
            model.addAttribute("endDate", endDate.trim());
            model.addAttribute("exampleName", exampleName.trim());
        }
        else
        {
            exampleList = exampleProjectService.selectExample(); // 查询此页案例列表
        }
        PageInfo<ExampleProject> pageInfo = new PageInfo<ExampleProject>(exampleList);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/exampleProject/exampleList";
    }



    /**
     * @Description: 用来显示案例项目详情和最新项目详情的方法
     * @Param: [model, exampleId]
     * @return: java.lang.String
     * @Author: 李紫林
     * @Date: 2019/2/14
     */
    @RequestMapping("/admin/example/detail")
    public String getDetail(Model model,@RequestParam(value = "exampleid")Long exampleId)
    {
        model.addAttribute("nav","li3");

        ExampleProject exampleProject = exampleProjectService.selectByPrimaryKey(exampleId); // 根据id查询案例

        List<ExampleProject> exampleModelList = exampleProjectService.selectNewExample(0,3);

        model.addAttribute("exampleProject",exampleProject);
        model.addAttribute("exampleModelList",exampleModelList);
        return "admin/exampleProject/exampleDetail";
    }


    /**
     * @Description: 用来弹出添加案例窗口的方法
     * @Param: []
     * @return: java.lang.String
     * @Author: 李紫林
     * @Date: 2019/2/14
     */
    @RequestMapping("/admin/example/addExample")
    public String addExample()
    {
        return "admin/exampleProject/add-example";
    }

    /**
     * @Description: 用来插入新案例的方法
     * @Param: [model, projectId, designerName, projecterName, projectName, content]
     * @return: com.design.background.model.ResultData
     * @Author: 李紫林
     * @Date: 2019/2/14
     */
    @ResponseBody
    @RequestMapping(value = "/admin/example/insertExample",produces = "application/json;charset=UTF-8")
    public ResultData insertExample(Model model, @RequestParam(value = "projectId",required = false)Long projectId,
                                    @RequestParam(value = "designerName")String designerName,
                                    @RequestParam(value = "projecterName")String projecterName,
                                    @RequestParam(value = "projectName")String projectName,
                                    @RequestParam("coverImage") MultipartFile coverImage,
                                    @RequestParam(value = "content")String content)
    {
        // 获取当前登录的管理员
        String loginUserName = Utility.getCurrentUsername();
        SysUser loginUser = userMapper.selectByUsername(loginUserName);
        String coverImagePath = null;
        int result = 0;
        if(coverImage != null){
            //上传到阿里云
            coverImagePath = DesignFileUtils.uploadSingleFile(coverImage);
            if(StringUtils.isNoneBlank(coverImagePath)){
                ExampleProject exampleProject = new ExampleProject(projectId,designerName,projecterName,projectName,new Date(),loginUser.getId(),content)
                        .setCoverImage(coverImagePath);
                result = exampleProjectService.insertSelective(exampleProject);
            }
        }
         // 插入新的案例
        return ResultData.setCodeAndMessage(result,"插入成功","插入失败"); // 返回json
    }

    /**
     * @Description: 用来删除案例项目的方法
     * @Param: [model, exampleId]
     * @return: com.design.background.model.ResultData
     * @Author: 李紫林
     * @Date: 2019/2/14
     */
    @ResponseBody
    @RequestMapping(value = "/admin/example/delete",produces = "application/json;charset=UTF-8")
    public ResultData deleteProjectExample(Model model,@RequestParam(value = "id")Long exampleId)
    {
        int result = exampleProjectService.deleteByPrimaryKey(exampleId); // 根据id删除案例
        return ResultData.setCodeAndMessage(result,"插入成功","插入失败"); // 返回json
    }

    @ResponseBody
    @RequestMapping(value = "/admin/example/deleteAll", produces = "application/json", consumes = "application/json")
    public Integer delete(@RequestBody Long[] ids) {
        try {
            for (long id : ids) {
                exampleProjectService.deleteByPrimaryKey(id); // 批量删除案例
            }
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }


    /**
     * @Description: 用来加载编辑信息然后显示在编辑案例项目窗口中的方法
     * @Param: [model, exampleId]
     * @return: java.lang.String
     * @Author: 李紫林
     * @Date: 2019/2/14
     */
    @RequestMapping("/admin/example/editExample")
     public String editExample(Model model,@RequestParam(value = "exampleid")Long exampleId)
    {
        ExampleProject exampleProject = exampleProjectService.selectByPrimaryKey(exampleId); // 根据案例id查询案例详情
        model.addAttribute("example",exampleProject);
        return "admin/exampleProject/edit_example";
    }


    /**
     * @Description: 用来提交编辑信息的方法
     * @Param: [model, id, projectId, designerName, projecterName, projectName, content]
     * @return: com.design.background.model.ResultData
     * @Author: 李紫林
     * @Date: 2019/2/14
     */
    @ResponseBody
    @RequestMapping(value = "/admin/example/updateExample",produces = "application/json;charset=UTF-8")
    public ResultData updateExample(Model model,@RequestParam(value = "id")Long id,
                                    @RequestParam(value = "projectId",required = false)Long projectId,
                                    @RequestParam(value = "designerName")String designerName,
                                    @RequestParam(value = "projecterName")String projecterName,
                                    @RequestParam(value = "projectName")String projectName,
                                    @RequestParam(value ="coverImage",required = false) MultipartFile coverImage,
                                    @RequestParam(value = "content")String content){
        // 获取当前登录的管理员
        String loginUserName = Utility.getCurrentUsername();
        SysUser loginUser = userMapper.selectByUsername(loginUserName);
        ExampleProject exampleProject = new ExampleProject(id,projectId,designerName,projecterName,projectName,new Date(),loginUser.getId(),content);
        int  result = 0;
        if(coverImage != null){
            //上传到阿里云
            String coverImagePath = DesignFileUtils.uploadSingleFile(coverImage);
            if(StringUtils.isNoneBlank(coverImagePath)){
                //修改封面
                exampleProject.setCoverImage(coverImagePath);
            }else{
                return ResultData.setCodeAndMessage(result,"插入成功","插入失败");
            }
        }
        result = exampleProjectService.updateByPrimaryKeySelective(exampleProject); // 更新数据库中的案例
        return ResultData.setCodeAndMessage(result,"插入成功","插入失败");
    }
}
