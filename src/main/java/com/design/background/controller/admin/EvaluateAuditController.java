package com.design.background.controller.admin;

import com.design.background.entity.Comment;
import com.design.background.entity.SysUser;
import com.design.background.service.CommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: EvaluateAuditController
 * @Description: TODO
 * @Author: HRX
 * @Date: 2019/3/9 11:51
 **/
@Controller
@RequestMapping("/admin/evaluateAudit")
public class EvaluateAuditController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(value={"/",""})
    public String columnManagement(Model model,
                                   @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                   @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
                                   @RequestParam(value = "projectName", required = false, defaultValue = "") String projectName) {
        PageHelper.startPage(pageNo, pageSize);
        List<Comment> list = commentService.selectAll(projectName);
        PageInfo<Comment> pageInfo = new PageInfo<Comment>(list);
        if (pageNo > pageInfo.getPages()) {
            pageNo = pageInfo.getPages();
            PageHelper.startPage(pageNo, pageSize);
            list = commentService.selectAll(projectName);
            pageInfo = new PageInfo<>(list);
        }
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("projectName", projectName);
        return "admin/evaluateAudit/list";
    }

    @RequestMapping("/allList")
    public String allList(Model model,
                                   @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                   @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<Comment> list = commentService.selectAllRecord();
        PageInfo<Comment> pageInfo = new PageInfo<Comment>(list);
        if (pageNo > pageInfo.getPages()) {
            pageNo = pageInfo.getPages();
            PageHelper.startPage(pageNo, pageSize);
            list = commentService.selectAllRecord();
            pageInfo = new PageInfo<>(list);
        }
        model.addAttribute("pageInfo", pageInfo);
        return "admin/evaluateAudit/alllist";
    }

    @ResponseBody
    @RequestMapping("/pass/{id}")
    public int pass(Model model, @PathVariable("id") Long id) {
        SysUser user = (SysUser) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        Comment comment = new Comment();
        comment.setId(id);
        comment.setProcessTime(new Date());
        comment.setProcessorId(user.getId());
        comment.setProcessCode(1);  // 1代表通过
        int flag = commentService.updateByPrimaryKeySelective(comment);
        return flag;
    }

    @ResponseBody
    @RequestMapping("/defind/{id}")
    public int defind(Model model, @PathVariable("id") Long id) {
        SysUser user = (SysUser) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        Comment comment = new Comment();
        comment.setId(id);
        comment.setProcessTime(new Date());
        comment.setProcessorId(user.getId());
        comment.setProcessCode(0);  // 0代表不通过
        int flag = commentService.updateByPrimaryKeySelective(comment);
        return flag;
    }

    @RequestMapping("/detail/{id}")
    public String detail(Model model,@PathVariable("id") Long id) {
        model.addAttribute("comment",commentService.selectById(id));
        return "admin/evaluateAudit/detail";
    }
}

