package com.design.background.pay.module.alipay.service.impl;

import com.design.background.BackgroundApplicationTests;
import com.design.background.model.ResultData;
import com.design.background.pay.module.alipay.model.AliPayBillAllModel;
import com.design.background.pay.module.alipay.service.AliPayService;
import com.design.background.pay.model.RefundModel;
import com.design.background.pay.model.RefundQueryModel;
import com.design.background.pay.module.alipay.model.AliPayTradeQueryModel;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName: AliPayServiceImplTest
 * @Description: 支付宝实现测试类
 * @Author: lxt
 * @Date: 2019-02-22 14:18
 * @Version 1.0
 **/
public class AliPayServiceImplTest extends BackgroundApplicationTests {
    private final static Logger logger = LoggerFactory.getLogger(AliPayServiceImplTest.class);
    @Autowired
    private AliPayService alipayService;
    /**
     * @Title: testDownloadurl
     * @Description: 测试获取对账单地址
     * @Author: lxt 
     * @Date: 2019-02-22 14:19 
     * @throws: 
     */
    @Test
    public void testDownloadurl(){
        Assert.assertNotNull(alipayService.downloadUrl("2019-02-21").isOk());
    }
    /**
     * @Title: testAnalysisAccount
     * @Description: 测试分析对账单
     * @Author: lxt 
     * @Date: 2019-02-23 17:08 
     * @throws: 
     */
    @Test
    public void testGetBillAllModel(){
        ResultData<AliPayBillAllModel>  resultData = alipayService.getBillAllModel("2019-02-22");
        logger.info("分析结果："+resultData.getData());
        Assert.assertTrue(resultData.isOk());
    }
    /**
     * @Title: testTradeQuery
     * @Description: 测试交易查询
     * @Author: lxt 
     * @Date: 2019-02-22 15:00 
     * @throws: 
     */
    @Test
    public void testTradeQuery(){
        ResultData<AliPayTradeQueryModel> resultData = alipayService.tradeQuery("1231",null);
        logger.info("查询结果："+resultData.getData());
        Assert.assertTrue(resultData.isOk());
    }
    /**
     * @Title: testRefundQuery
     * @Description: 测试退款交易查询
     * @Author: lxt 
     * @Date: 2019-02-22 15:53 
     * @throws: 
     */
    @Test
    public void testRefundQuery(){

        RefundModel refundModel = new RefundModel().setOutTradeNo("20190226183551").setOutRequestNo("1234567891");
        ResultData<List<RefundQueryModel>> resultData = alipayService.refundQuery(refundModel);
        logger.info("查询结果："+resultData.getData());
        Assert.assertTrue(resultData.isOk());
    }
    /**
     * @Title: testCloseTrade
     * @Description: 下载对账单
     * @Author: lxt 
     * @Date: 2019-02-26 17:33 
     * @throws: 
     */
    @Test
    public void testCloseTrade(){
        Assert.assertTrue(alipayService.closeTrade("789456",null,null).isOk());
    }
}
