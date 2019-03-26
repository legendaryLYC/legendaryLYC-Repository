package com.design.background.controller.admin;

import com.design.background.entity.*;
import com.design.background.mapper.SysUserMapper;
import com.design.background.model.ResultData;
import com.design.background.service.DesignerQualifyService;
import com.design.background.service.LeadingUserProjectorService;
import com.design.background.service.MessageManageService;
import com.design.background.service.ProfessionalCertificationService;
import com.design.background.util.StringToDate;
import com.design.background.util.Utility;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author： 李紫林
 * @Date： 2019/3/9
 * @Description： 设计师资质审核的controller
 */

@Controller
@RequestMapping("/admin/designerQualify")
public class DesignerQualifyController {


    @Autowired
    private DesignerQualifyService designerQualifyService;
    @Autowired
    private ProfessionalCertificationService professionalCertificationService;
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private MessageManageService messageManageService;
    @Autowired
    private LeadingUserProjectorService leadingUserProjectorService;

    /**
     * @Description: 用来展示设计师资质审核申请列表的方法
     * @Param: [model, pageNo, pageSize, startTime, endTime, title]
     * @return: java.lang.String
     * @Author: 李紫林
     * @Date: 2019/3/4
     */
    @RequestMapping("/showList")
    public String showList(Model model,
                           @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo, // 页码
                           @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
                           @RequestParam(value = "startTime", required = false) String startTime,
                           @RequestParam(value = "endTime", required = false) String endTime,
                           @RequestParam(value = "designerName", required = false) String designerName,
                           @RequestParam(value = "urgent", required = false) Integer urgent,
                           @RequestParam(value = "typeCode", required = false) Integer typeCode)
    {
        Date start = null;
        Date end = null;

        DesignerQualify designerQualify = new DesignerQualify();

        if (designerName != null && !"".equals(designerName.trim())) {
            designerQualify.setDesignerName(designerName.trim());
            model.addAttribute("designerName", designerName.trim());
        }
        if (urgent != null) {
            designerQualify.setUrgent(urgent);
            model.addAttribute("urgent", urgent);
        }
        if (typeCode != null) {
            designerQualify.setTypeCode(typeCode);
            model.addAttribute("typeCode", typeCode);
        }
        if (designerName != null && !"".equals(designerName.trim())) {
            designerQualify.setDesignerName(designerName.trim());
            model.addAttribute("designerName", designerName.trim());
        }
        if (startTime != null && !"".equals(startTime.trim())) {
            start = StringToDate.toUtilDate(startTime);
            model.addAttribute("startTime", startTime.trim());
        }
        if (endTime != null && !"".equals(endTime.trim())) {
            end = StringToDate.toUtilDate(endTime);
            model.addAttribute("endTime", endTime.trim());
        }

        PageHelper.startPage(pageNo, pageSize);
        List<DesignerQualify> designerQualifyList = designerQualifyService.selectQualifyList(designerQualify,start,end);
        PageInfo<DesignerQualify> pageInfo = new PageInfo<DesignerQualify>(designerQualifyList);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/designer_qualify/qualifyList";
    }

    /**
     * @Description: 用来跳转到资质审核页面的方法
     * @Param: [model, id]
     * @return: java.lang.String
     * @Author: 李紫林
     * @Date: 2019/3/4
     */
    @RequestMapping("/toqualify/{id}")
    public String toQualify(Model model, @PathVariable("id")Long id)
    {
        DesignerQualify designerQualify = designerQualifyService.selectByPrimaryKey(id);
        List<ProfessionalCertification> professionalCertificationList = professionalCertificationService.selectByQualifyId(id);
        model.addAttribute("professional",professionalCertificationList.get(0));
        model.addAttribute("professionalCertificationList",professionalCertificationList);
        model.addAttribute("designerQualify",designerQualify);
        return "/admin/designer_qualify/qualifyDetail";
    }


    /**
     * @Description: 资质审核通过
     * @Param: [id]
     * @return: com.design.background.model.ResultData
     * @Author: 李紫林
     * @Date: 2019/3/11
     */
    @ResponseBody
    @RequestMapping(value = "/qualify", produces = "application/json")
    public ResultData qualify(@Param("id") Long id,@Param("userId") Long userId,@Param("submitTime") String submitTime)
    {
        LeadingUser leadingUser = leadingUserProjectorService.selectById(userId);
        String tel = leadingUser.getTel();
        // 调用消息接口
        Long type = 1022L;
        Map<String,String> para= new HashMap();
        para.put("${submitTime}",submitTime);
        boolean messageResult = messageManageService.allNoticeAdd(tel,type, para);
        // 调用接口结束
        String loginUserName = Utility.getCurrentUsername();
        SysUser loginUser = userMapper.selectByUsername(loginUserName);
        DesignerQualify designerQualify = new DesignerQualify();
        designerQualify.setProcessCode(1);
        designerQualify.setProcessTime(new Date());
        designerQualify.setProcessorId(loginUser.getId());
        designerQualify.setId(id);
        int result = designerQualifyService.updateByPrimaryKeySelective(designerQualify);
        return ResultData.setCodeAndMessage(result,"成功","失败");
    }


    /**
     * @Description: 资质审核未通过
     * @Param: [id]
     * @return: com.design.background.model.ResultData
     * @Author: 李紫林
     * @Date: 2019/3/11
     */
    @ResponseBody
    @RequestMapping(value = "/refuse", produces = "application/json")
    public ResultData refuse(@Param("id") Long id,@Param("userId") Long userId,@Param("reason") String reason,@Param("submitTime") String submitTime)
    {
        if (reason != null && !"".equals(reason.trim())) {
            reason = "未知原因";
        }

        LeadingUser leadingUser = leadingUserProjectorService.selectById(userId);
        String tel = leadingUser.getTel();
        // 调用消息接口
        Long type = 1022L;
        Map<String,String> para= new HashMap();
        para.put("${submitTime}",submitTime);
        para.put("${reason}",reason);
        boolean messageResult = messageManageService.allNoticeAdd(tel,type, para);
        // 调用接口结束
        String loginUserName = Utility.getCurrentUsername();
        SysUser loginUser = userMapper.selectByUsername(loginUserName);
        DesignerQualify designerQualify = new DesignerQualify();
        designerQualify.setProcessCode(2);
        designerQualify.setProcessTime(new Date());
        designerQualify.setProcessorId(loginUser.getId());
        designerQualify.setId(id);
        int result = designerQualifyService.updateByPrimaryKeySelective(designerQualify);
        return ResultData.setCodeAndMessage(result,"成功","失败");
    }

}
