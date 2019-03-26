package com.design.background.pay.module.unionpay.service;

import com.design.background.model.ResultData;
import com.design.background.pay.model.ProductModel;
import com.design.background.pay.model.RefundModel;
import com.design.background.pay.module.unionpay.model.UnionPayAllBillModel;
import com.design.background.pay.module.unionpay.model.UnionPayTradeQueryModel;

/**
 * @ClassName: UnionPayService
 * @Description: to do
 * @Author: lxt
 * @Date: 2019-02-25 11:29
 * @Version 1.0
 **/
public interface UnionPayService {
    /**
     * @Title: payPC
     * @Description: 银联电脑支付
     * @Author: lxt 
     * @param: productModel
     *      注：
     *          outTradeNo【必填】商户订单号
     *          txnTime【必填】订单发送时间，需要存储，用于后续查询
     *          totalFee【必填】订单金额 单位：元
     *
     * @Date: 2019-02-25 13:49 
     * @return: com.design.background.model.ResultData<java.lang.String>
     * @throws: 
     */
    ResultData<String> payPC(ProductModel productModel);
    /**
     * @Title: tradeQuery
     * @Description: 查询订单
     * @Author: lxt 
     * @param: orderId【必填】 商户订单号
     * @param: txnTime【必填】 发送下单时间 格式为YYYYMMDDhhmmss
     * @Date: 2019-02-27 13:49 
     * @return: com.design.background.model.ResultData<com.design.background.pay.unionpay.model.UnionPayTradeQueryModel>
     * @throws: 
     */
    ResultData<UnionPayTradeQueryModel> tradeQuery(String orderId, String txnTime);
    /**
     * @Title: closeTrade
     * @Description: 消费撤销(关闭订单)
     * @Author: lxt 
     * @param: txnAmt【必填】　撤销金额　消费撤销时必须和原消费金额相同
     * @param: origQryId【必填】　原消费交易返回的的queryId，可以从消费交易后台通知接口中或者交易状态查询接口中获取
     * @Date: 2019-02-27 14:46 
     * @return: com.design.background.model.ResultData<java.lang.String>
     * @throws:
     */
    ResultData<String> closeTrade(String txnAmt,String origQryId);

    /**
     * @Title: refund
     * @Description:
     * @Author: lxt
     * @param: RefundModel
     *              txnAmt【必填】 退款金额 退货金额，单位分，不要带小数点。退货金额小于等于原消费金额，当小于的时候可以多次退货至退货累计金额等于原消费金额
     *              origQryId【必填】 原始交易流水号
     * @Date: 2019-02-27 15:42
     * @return: com.design.background.model.ResultData<java.lang.String>
     * @throws:
     */
    ResultData<String> refund(RefundModel refundModel);
    /**
     * @Title: dowloadBill
     * @Description: 下载对账单
     * @Author: lxt
     * @param: settleDate【必填】 格式为MMDD
     * @param: profile【可选】 运行环境  DEV 为测试 其他均为线上环境
     * @Date: 2019-02-27 15:50
     * @return: com.design.background.model.ResultData<com.design.background.pay.unionpay.model.UnionPayAllBillModel>
     * @throws: 
     */
    ResultData<UnionPayAllBillModel> dowloadBill(String settleDate, String profile);
}
