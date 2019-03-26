package com.design.background.common.controller;

import com.design.background.common.contants.DesignConstants;
import com.design.background.service.BasicInformationService;
import com.design.background.service.FriendShipService;
import com.design.background.util.ServletUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

/**
 * @ClassName: BaseController
 * @Description: Web层通用Controller
 * @Author: lxt
 * @Date: 2019-02-16 13:35
 * @Version 1.0
 **/
public class BaseController {

    @Autowired
    private FriendShipService friendShipService;
    @Autowired
    private BasicInformationService basicInformationService;

    /**
     * 加载公共部分的动态设置:友情链接, 网站基础信息设置
     * @Author: 孟晓冬
     * @param model
     */
    @ModelAttribute
    public void populateModel( Model model) {
        model.addAttribute("setting", basicInformationService.getSelectedSetting());
        model.addAttribute("linkList", friendShipService.selectList());
    }

    /**
     * @Title: startPage
     * @Description:  设置请求分页数据
     * @Author: lxt 
     * @Date: 2019-02-16 13:52 
     * @throws: 
     */
    protected void startPage()
    {
        Integer pageNum = ServletUtils.getParameterToInt(DesignConstants.PAGE_NO,1);
        Integer pageSize = ServletUtils.getParameterToInt(DesignConstants.PAGE_SIZE,DesignConstants.PAGE_SIZE_DEFAULT);
        if (pageNum != null && pageSize != null)
        {
            PageHelper.startPage(pageNum, pageSize);
        }
    }
    /**
     * @Title: getPageInfo
     * @Description:  获取分页数据
     * @Author: lxt 
     * @param: list
     * @Date: 2019-02-16 13:52 
     * @return: com.github.pagehelper.PageInfo<?>
     * @throws: 
     */
    protected PageInfo<?> getPageInfo(List<?> list){
        return new PageInfo(list);
    }
}
