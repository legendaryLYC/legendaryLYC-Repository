package com.design.background.pay.module.wxpay.service.impl;

import com.design.background.BackgroundApplicationTests;
import com.design.background.model.ResultData;
import com.design.background.pay.model.RefundModel;
import com.design.background.pay.model.RefundQueryModel;
import com.design.background.pay.module.wxpay.model.WxPayTradeQueryModel;
import com.design.background.pay.module.wxpay.service.WxPayService;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName: WxPayServiceImplTest
 * @Description: 微信支付测试类
 * @Author: lxt 
 * @Date: 2019-02-26 14:59
 * @Version 1.0
 **/
public class WxPayServiceImplTest extends BackgroundApplicationTests {
    private final static Logger logger = LoggerFactory.getLogger(WxPayServiceImplTest.class);
    @Autowired
    private WxPayService wxPayService;
    /**
     * @Title: testOrderQuery
     * @Description: 测试查询订单
     * @Author: lxt 
     * @Date: 2019-02-26 15:11 
     * @throws: 
     */
    @Test
    public void testOrderQuery(){
        ResultData<WxPayTradeQueryModel> resultData =  wxPayService.orderQuery(null,"20190226141912");
        logger.info("查询结果：{}",resultData);
        Assert.assertTrue(resultData.isOk());
    }
    /**
     * @Title: testCloseOrder
     * @Description: 测试关闭订单
     * @Author: lxt 
     * @Date: 2019-02-26 15:12 
     * @throws: 
     */
    @Test
    public void testCloseOrder(){
        ResultData<String> resultData =  wxPayService.closeOrder("20190226141912");
        logger.info("关闭结果：{}",resultData);
        Assert.assertTrue(resultData.isOk());
    }

    /**
     * @Title: testRefund
     * @Description: 退款
     * @Author: lxt 
     * @Date: 2019-02-26 16:34 
     * @throws: 
     */
    @Test
    public void testRefund(){
        RefundModel refundModel = new  RefundModel();
        /**
         * 参数赋值
         */
        refundModel.setOutTradeNo("20190226181609")
                .setOutRequestNo("201902261816099")
                .setTotalAmount("1")
                .setRefundAmount("0.01");
        ResultData<String> resultData =  wxPayService.refund(refundModel);
        logger.info("退款结果：{}",resultData);
        Assert.assertTrue(resultData.isOk());
    }
    /**
     * @Title: testRefundQuery
     * @Description: 退款查询
     * @Author: lxt 
     * @Date: 2019-02-26 16:34 
     * @throws: 
     */
    @Test
    public void testRefundQuery(){
        RefundModel refundModel = new  RefundModel();
        /**
         * 参数赋值
         */
        refundModel.setOutTradeNo("20190226181609");
//                .setOutRequestNo("20190226141912666");
//                .setRefundId("50000609672019022608529265153");
        ResultData<List<RefundQueryModel>> resultData =  wxPayService.refundQuery(refundModel);
        logger.info("退款查询结果：{}",resultData);
        Assert.assertTrue(resultData.isOk());
    }
    /**
     * @Title: testDownloadBill
     * @Description: 对账单下载
     * @Author: lxt 
     * @Date: 2019-02-26 16:35 
     * @throws: 
     */
    @Test
    public void testDownloadBill(){
        ResultData resultData = wxPayService.downloadBill("20190225");
        logger.info("对账单解析结果：{}",resultData);
        Assert.assertTrue(resultData.isOk());
    }

}
