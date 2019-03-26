package com.design.background.controller.front;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.design.background.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.design.background.entity.DicColumn;
import com.design.background.service.impl.BindAboutServiceImpl;

/**
 * 
* @ClassName: AboutUsController  
* @Description: TODO 用于绑定关于我们里边的栏目
* @author HRX  
* @date 2019年2月16日  
*
 */
@Controller
@RequestMapping("/bindAbout")
public class BindAboutController extends BaseController {
	
	@Autowired
	private BindAboutServiceImpl bindAboutService;
	
	/**
     * 
    * @Author HRX 
    * @Title: aboutUs  
    * @Description: TODO 跳转到关于我们页面
    * @param @param model
    * @param @param req
    * @param @return   +
    * @return String    
    * @Date 2019年2月15日 下午5:36:06
    * @throws
     */
    @RequestMapping("/aboutUs")
    public String aboutUs(Model model, HttpServletRequest req) {
    	model.addAttribute("nav","li4");
    	List<DicColumn> list = bindAboutService.selectAll(null);
    	model.addAttribute("list",list);
    	model.addAttribute("column",list.get(0));
        return "front/aboutUs/us";
    }
    
    /**
     * 
    * @Author HRX 
    * @Title: problem  
    * @Description: TODO 跳转到常见问题界面
    * @param @param model
    * @param @param req
    * @param @return   
    * @return String    
    * @Date 2019年2月15日 下午5:36:24
    * @throws
     */
    @RequestMapping("/problem")
    public String problem(Model model, HttpServletRequest req) {
    	model.addAttribute("nav","li4");
    	List<DicColumn> list = bindAboutService.selectAll(null);
    	model.addAttribute("list",list);
    	model.addAttribute("column",list.get(1));
        return "front/aboutUs/problem";
    }
    
    /**
     * 
    * @Author HRX 
    * @Title: help  
    * @Description: TODO 帮助我们界面
    * @param @param model
    * @param @param req
    * @param @return   
    * @return String    
    * @Date 2019年2月15日 下午5:36:51
    * @throws
     */
    @RequestMapping("/help")
    public String help(Model model, HttpServletRequest req) {
    	model.addAttribute("nav","li4");
    	List<DicColumn> list = bindAboutService.selectAll(null);
    	model.addAttribute("list",list);
    	model.addAttribute("column",list.get(2));
    	return "front/aboutUs/help";
    }
    
    /**
     * 
    * @Author HRX 
    * @Title: hefeedbacklp  
    * @Description: TODO 跳转到反馈界面
    * @param @param model
    * @param @param req
    * @param @return   
    * @return String    
    * @Date 2019年2月16日 下午3:52:25
    * @throws
     */
    @RequestMapping("/feedback")
    public String hefeedbacklp(Model model, HttpServletRequest req) {
    	model.addAttribute("nav","li4");
    	List<DicColumn> list = bindAboutService.selectAll(null);
    	model.addAttribute("list",list);
    	model.addAttribute("column",list.get(6));
        return "front/aboutUs/feedback";

    }
    
    /**
     * 
    * @Author HRX 
    * @Title: law  
    * @Description: TODO 跳转到法律声明界面
    * @param @param model
    * @param @param req
    * @param @return   
    * @return String    
    * @Date 2019年2月16日 下午3:52:41
    * @throws
     */
    @RequestMapping("/law")
    public String law(Model model, HttpServletRequest req) {
    	model.addAttribute("nav","li4");
    	List<DicColumn> list = bindAboutService.selectAll(null);
    	model.addAttribute("list",list);
    	model.addAttribute("column",list.get(3));
        return "front/aboutUs/law";
    }
    
    /**
     * 
    * @Author HRX 
    * @Title: contact  
    * @Description: TODO 跳转到联系我们界面
    * @param @param model
    * @param @param req
    * @param @return   
    * @return String    
    * @Date 2019年2月16日 下午3:53:09
    * @throws
     */
    @RequestMapping("/contact")
    public String contact(Model model, HttpServletRequest req) {
    	model.addAttribute("nav","li4");
    	List<DicColumn> list = bindAboutService.selectAll(null);
    	model.addAttribute("list",list);
    	model.addAttribute("column",list.get(4));
        return "front/aboutUs/contact";
    }
    
    /**
     * 
    * @Author HRX 
    * @Title: join  
    * @Description: TODO 跳转到加入我们界面
    * @param @param model
    * @param @param req
    * @param @return   
    * @return String    
    * @Date 2019年2月16日 下午3:54:27
    * @throws
     */
    @RequestMapping("/join")
    public String join(Model model, HttpServletRequest req) {
    	model.addAttribute("nav","li4");
    	List<DicColumn> list = bindAboutService.selectAll(null);
    	model.addAttribute("list",list);
    	model.addAttribute("column",list.get(5));
        return "front/aboutUs/join";
    }
}
