package com.design.background.controller.admin;

import com.design.background.entity.CompanyManagement;
import com.design.background.entity.LeadingUser;
import com.design.background.entity.RateManagement;
import com.design.background.service.RateManagementService;
import com.design.background.util.DesignFileUtils;
import com.design.background.util.Utility;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/ratemanagement")
public class RateManagementController {

    private static final Logger logger = LoggerFactory.getLogger(RateManagementController.class);

    @Autowired
    private RateManagementService rateManagementService;
    /**
     * 查询费率信息(测试成功)
     * @Param pageNo,pageSize,name
     * @author 任松
     * @return
     */

    @RequestMapping("/showlist")
    public String columnManagement(Model model,
                                   @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                   @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
                                   @RequestParam(value = "code", required = false, defaultValue = "") Integer code,HttpServletRequest request) {
        // code = code.trim();
        PageHelper.startPage(pageNo, pageSize);
        List<RateManagement> list = rateManagementService.selectAll(code);
        for(int i = 0; i < list.size(); i++) {
            RateManagement rateManagement = (RateManagement)list.get(i);
            list.set(i, rateManagement);
        }
        PageInfo<RateManagement> pageInfo = new PageInfo<RateManagement>(list);
        System.out.println(pageInfo.getPages());
        if (pageNo > pageInfo.getPages()) {
            pageNo = pageInfo.getPages();
            PageHelper.startPage(pageNo, pageSize);
            list = rateManagementService.selectAll(code);
            for(int i = 0; i < list.size(); i++) {
                RateManagement rateManagement = (RateManagement)list.get(i);
                list.set(i, rateManagement);
            }
            pageInfo = new PageInfo<>(list);
        }
        model.addAttribute("code",code);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/ratemanagement/showList";
    }

    /**
     *
     * @Author 任松
     * @Title: toaddcompany
     * @Description: TODO 跳转到添加费率页面
     * @param @return
     * @return String
     * @Date 2019年2月26日 下午4:34:20
     * @throws
     */
    @RequestMapping(value = "/toaddrate")
    public String toAddCompany() {
        return "admin/ratemanagement/add";
    }

    /**
     *
     * @Author 任松
     * @Title: addColumn
     * @Description: TODO 添加费率
     * @param @param model
     * @param @param dicColumn
     * @param @return
     * @return int
     * @Date 2019年2月26日 下午4:17:43
     * @throws
     */

    @ResponseBody
    @RequestMapping(value = "/addrate")
    public int addCompany(Model model, @ModelAttribute RateManagement rateManagement) {
        rateManagement.getProcessorId();
        int flag = rateManagementService.insert(rateManagement);
        UserDetails user = Utility.getCurrentUser();
        return flag;
    }


    @ResponseBody
    @RequestMapping(value = "/deleterateById/{id}")
    public int deleteColumById(@ModelAttribute RateManagement rateManagement) {
        int flag = rateManagementService.deleteByPrimaryKey(rateManagement.getId());
        return flag;
    }


    /**
     *
     * @Author 任松
     * @Title: deleteColum
     * @Description: TODO 批量删除费率
     * @param @param id
     * @param @return
     * @return String
     * @Date 2019年2月26日 下午3:56:14
     * @throws
     */

   @RequestMapping("/deleterateByIds")
    @ResponseBody
    public Integer delete(@RequestBody Long[] ids) {
        if (ids.length != 0 && ids != null) {
            for (long id : ids) {
                rateManagementService.deleteByPrimaryKey(id);
            }
            return 1;
        }
        return 0;
    }

    /**
     *
     * @Author 任松
     * @Title: toEditColumn
     * @Description: TODO  跳转到修改费率页面
     * @param @param model
     * @param @param dicColumn
     * @param @return
     * @return String
     * @Date 2019年2月15日 下午4:54:44
     * @throws
     */
    @RequestMapping("/torateedit/{id}")
    public String toCompanyEdit(Model model,@ModelAttribute RateManagement rateManagement) {
        rateManagement = rateManagementService.selectByPrimaryKey(rateManagement.getId());
        System.out.println(rateManagement.getId());
        model.addAttribute("rate",rateManagement);
        return "admin/ratemanagement/edit";
    }

    /**
     *
     * @Author 任松
     * @Title: editColumn
     * @Description: TODO 修改费率
     * @param @param model
     * @param @param dicColumn
     * @param @return
     * @return int
     * @Date 2019年2月15日 下午4:57:38
     * @throws
     */

    @ResponseBody
    @RequestMapping(value = "/editrate")
    public int editCompany(Model model, RateManagement rateManagement)
    {
        rateManagement.setChangeTime(new Date());
        int flag=rateManagementService.updateselective(rateManagement);
        return flag;
    }



}
