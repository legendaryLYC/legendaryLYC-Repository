package com.design.background.pay.controller;

import com.design.background.model.ResultData;
import com.design.background.pay.model.ProductModel;
import com.design.background.pay.model.RefundModel;
import com.design.background.pay.service.PayService;
import com.design.background.pay.utill.PayUtil;
import com.design.background.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: PayController
 * @Description: 第三方支付控制层
 * @Author: lxt
 * @Date: 2019-03-06 11:21
 * @Version 1.0
 **/
@Controller
@RequestMapping("thirdPay")
public class ThirdPayController {
    private final static Logger logger = LoggerFactory.getLogger(ThirdPayController.class);
    private final static String BASE_URL="pay/";
    @Autowired
    private PayService payService;
    /**
     * @Title: test
     * @Description: 测试
     * @Author: lxt 
     * @param: model
     * @Date: 2019-03-09 10:14 
     * @return: java.lang.String
     * @throws: 
     */
    @RequestMapping("/test")
    public String test(Model model){
        model.addAttribute("orderId", PayUtil.getUniqueOrderId());
        model.addAttribute("txnTime", DateUtil.format(null,DateUtil.PATTERN_yyyyMMddHHmmss));
        return BASE_URL+"test-pay";
    }
    /**
     * @Title: payPC
     * @Description: 第三方支付方法
     * @Author: lxt 
     * @param: productModel
     * @Date: 2019-03-09 10:05 
     * @return: com.design.background.model.ResultData<java.lang.String>
     * @throws: 
     */
    @PostMapping("pay")
    public String pay(Model model,ProductModel productModel){
        model.addAttribute("result",  payService.payPC(productModel));
        return BASE_URL+"pay-start";
    }
    /**
     * @Title: refund
     * @Description: 退款方法
     * @Author: lxt 
     * @param: refundModel
     * @Date: 2019-03-09 11:30 
     * @return: com.design.background.model.ResultData
     * @throws: 
     */
    @RequestMapping(value = "/refund",method = {RequestMethod.POST})
    @ResponseBody
    public ResultData refund(@RequestBody RefundModel refundModel){
        return payService.refund(refundModel);
    }

}
