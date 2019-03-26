package com.design.background.controller.admin;

import com.design.background.entity.NewTransaction;
import com.design.background.service.TransactionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**

 * @Author     :高红亮

 * @Date       :2019/2/16

 * @Description:交易记录的controller

*/

@Controller
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    /**

     * @return    :返回交易记录列表

     * @Description:获取交易记录的方法

     * @Date       :2019/2/14

    */

    @RequestMapping("/admin/payrecord")
    public String shows(Model model,
                                         @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo, // 页码
                                         @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) // 页面容纳条数
    {
        PageHelper.startPage(pageNo, pageSize);
        List<NewTransaction> showList = transactionService.selectRecord();
        PageInfo<NewTransaction> pageInfo = new PageInfo<NewTransaction>(showList);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/transaction/showList";
    }
}
