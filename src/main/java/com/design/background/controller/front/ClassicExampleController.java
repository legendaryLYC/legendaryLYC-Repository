package com.design.background.controller.front;

import com.design.background.common.controller.BaseController;
import com.design.background.entity.ExampleProject;
import com.design.background.service.ExampleProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @ClassName: ClassicExampleController
 * @Description: 前台经典案例
 * @Author: lxt
 * @Date: 2019-02-16 14:39
 * @Version 1.0
 **/
@Controller
public class ClassicExampleController extends BaseController {
    @Autowired
    private ExampleProjectService exampleProjectService;
    /**
     * @Title: getExample
     * @Description: 用于在首页点击经典案例后获取数据并跳转
     * @Author: lxt
     * @param: model
     * @Date: 2019-02-16 14:41
     * @return: java.lang.String
     * @throws:
     */
    @RequestMapping("/classicexample")
    public String getExample(Model model)
    {
        startPage();
        List<ExampleProject> exampleModelList = exampleProjectService.selectClassicExample();
        model.addAttribute("pageInfo",getPageInfo(exampleModelList));
        model.addAttribute("nav","li3");
        return "front/classic-example";
    }

    /**
     * @Description: 用来显示案例项目详情和最新项目详情的方法
     * @Param: [model, exampleId]
     * @return: java.lang.String
     * @Author: 李紫林
     * @Date: 2019/2/14
     */
    @RequestMapping("/detail")
    public String getDetail(Model model,@RequestParam(value = "exampleid")Long exampleId)
    {
        model.addAttribute("nav","li3");

        ExampleProject exampleProject = exampleProjectService.selectByPrimaryKey(exampleId); // 根据id查询案例

        List<ExampleProject> exampleModelList = exampleProjectService.selectNewExample(0,3);

        model.addAttribute("exampleProject",exampleProject);
        model.addAttribute("exampleModelList",exampleModelList);
        return "front/index-1-detail";
    }
}
