package com.design.background.pay.module.unionpay.service.impl;

import com.design.background.BackgroundApplicationTests;
import com.design.background.model.ResultData;
import com.design.background.pay.model.RefundModel;
import com.design.background.pay.module.unionpay.model.UnionPayAllBillModel;
import com.design.background.pay.module.unionpay.model.UnionPayTradeQueryModel;
import com.design.background.pay.module.unionpay.service.UnionPayService;
import com.design.background.pay.module.unionpay.util.UnionPayConstants;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName: UnionPayServiceImplTest
 * @Description: to do
 * @Author: lxt
 * @Date: 2019-02-27 14:22
 * @Version 1.0
 **/
public class UnionPayServiceImplTest extends BackgroundApplicationTests {
    private final static Logger logger = LoggerFactory.getLogger(UnionPayServiceImplTest.class);
    @Autowired
    private UnionPayService unionPayService;
    /**
     * @Title: testTradeQuery
     * @Description: 测试查询订单
     * @Author: lxt 
     * @Date: 2019-02-27 14:23 
     * @throws:
     */
    @Test
    public void testTradeQuery(){
        ResultData<UnionPayTradeQueryModel> resultData = unionPayService.tradeQuery("20190227180121","20190227180132");
        logger.info("查询结果："+resultData);
        Assert.assertTrue(resultData.isOk());
    }
    /**
     * @Title: testcloseTrade
     * @Description: 测试撤销交易
     * @Author: lxt 
     * @Date: 2019-02-27 15:05 
     * @throws: 
     */
    @Test
    public void testCloseTrade(){
        ResultData<String> resultData = unionPayService.closeTrade("10","491902281405388716258");
        logger.info("撤销结果："+resultData);
        Assert.assertTrue(resultData.isOk());
    }
    /**
     * @Title: testRefund
     * @Description: 测试退款
     * @Author: lxt 
     * @Date: 2019-02-27 17:20 
     * @throws:
     */
    @Test
    public void testRefund(){
        ResultData<String> resultData = unionPayService.refund(new RefundModel().setRefundAmount("10").setOrigQryId("991902271801324772518"));
        logger.info("退款结果："+resultData);
        Assert.assertTrue(resultData.isOk());
    }
    /**
     * @Title: testDownloadBill
     * @Description: 测试对账单下载
     * @Author: lxt 
     * @Date: 2019-02-27 17:23 
     * @throws: 
     */
    @Test
    public void testDownloadBill(){
        ResultData<UnionPayAllBillModel> resultData = unionPayService.dowloadBill(null, UnionPayConstants.PROFILE_DEV);
        logger.info("对账单下载结果："+resultData);
        Assert.assertTrue(resultData.isOk());
    }
}
