package com.design.background.pay.service.impl;

import com.design.background.model.ResultData;
import com.design.background.pay.model.ProductModel;
import com.design.background.pay.model.RefundModel;
import com.design.background.pay.model.RefundQueryModel;
import com.design.background.pay.module.alipay.service.AliPayService;
import com.design.background.pay.module.unionpay.service.UnionPayService;
import com.design.background.pay.module.wxpay.service.WxPayService;
import com.design.background.pay.service.PayService;
import com.design.background.pay.utill.PayConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: PayServiceImpl
 * @Description: 第三方支付 业务实现类
 * @Author: lxt
 * @Date: 2019-03-06 11:23
 * @Version 1.0
 **/
@Service
public class PayServiceImpl implements PayService{
    @Autowired
    private AliPayService aliPayService;
    @Autowired
    private WxPayService wxPayService;
    @Autowired
    private UnionPayService unionPayService;
    /**
     * @Title: payPC
     * @Description: 支付方法
     * @Author: lxt
     * @param: productModel
     * 参数说明： 参数名称      描述           是否必填        示例      备注
     *          payType       支付方式       必填                   1:支付宝 2:微信 3:银联
     *          outTradeNo    商户订单号      必填
     *          txnTime       订单发送时间    银联必填                格式为YYYYMMDDhhmmss，必须取当前时间，否则会报txnTime无效
     *          totalFee      支付金额（元）  必填                   最多两位小数
     *          subject       订单标题   支付宝、微信必填     两个苹果
     * @Date: 2019-03-06 11:47
     * @return: com.design.background.model.ResultData<java.lang.String>
     * 返回值说明：           成功                      失败
     *            code      000000                   999999
     *          message   html标签或二维码地址（微信） 失败信息
     * @throws:
     */
    @Override
    public ResultData<String> payPC(ProductModel productModel) {
        switch (productModel.getPayType()){
            case PayConstants.PAY_TYPE_ALIPAY:
                return aliPayService.payPC(productModel);
            case PayConstants.PAY_TYPE_WXPAY:
                return wxPayService.payPC(productModel);
            case PayConstants.PAY_TYPE_UNIONPAY:
                return unionPayService.payPC(productModel);
            default:
                return ResultData.fail("支付方式有误！");
        }
    }
    /**
     * @Title: refund
     * @Description:  （部分）退款功能，退款金额小于订单金额为部分退款，等于为全额退款
     * @Author: lxt
     * @param: refundModel
     * 参数说明：参数名称      描述               是否必填                示例
     *          payType       支付方式              必填             1:支付宝 2:微信 3:银联
     *          outTradeNo    商户订单号         微信和支付宝必填
     *          outRequestNo  退款单号           微信和支付宝必填
     *          totalAmount   订单金额（元）      微信必填
     *          refundAmount  退款金额（元）       必填
     *          origQryId     原交易流水号        银联必填
     * @Date: 2019-03-06 12:05
     * @return: com.design.background.model.ResultData<java.lang.String>
     * 返回值说明：           成功                      失败
     *            code      000000                   999999
     *          message    成功信息提示                 失败信息
     * @throws:
     */
    @Override
    public ResultData<String> refund(RefundModel refundModel) {
        switch (refundModel.getPayType()) {
            case PayConstants.PAY_TYPE_ALIPAY:
                return aliPayService.refund(refundModel);
            case PayConstants.PAY_TYPE_WXPAY:
                return wxPayService.refund(refundModel);
            case PayConstants.PAY_TYPE_UNIONPAY:
                return unionPayService.refund(refundModel);
            default:
                return ResultData.fail("支付方式有误！");
        }
    }
    /**
     * @Title: tradeQuery
     * @Description: 交易查询
     * @Author: lxt
     * @param: payType    【必填】 支付方式       1:支付宝 2:微信 3:银联
     * @param: outTradeNo 【必填】 商户订单号
     * @param: txnTime    【银联必填】 订单发送时间    发送下单时间 格式为YYYYMMDDhhmmss
     * @Date: 2019-03-06 14:22
     * @return: com.design.background.model.ResultData
     * @throws:
     */
    @Override
    public ResultData tradeQuery(String payType, String outTradeNo, String txnTime) {
        switch (payType) {
            case PayConstants.PAY_TYPE_ALIPAY:
                return aliPayService.tradeQuery(outTradeNo,null);
            case PayConstants.PAY_TYPE_WXPAY:
                return wxPayService.orderQuery(outTradeNo,null);
            case PayConstants.PAY_TYPE_UNIONPAY:
                return unionPayService.tradeQuery(outTradeNo,txnTime);
            default:
                return ResultData.fail("支付方式有误！");
        }
    }
    /**
     * @Title: refundQuery
     * @Description: 退款查询接口（支付宝和微信）
     * @Author: lxt
     * @param: refundModel
     * 参数说明：参数名称      描述               是否必填    示例
     *          payType       支付方式           必填    1:支付宝 2:微信
     *          outTradeNo    商户订单号         必填
     *          outRequestNo  退款单号          支付宝必填
     * @Date: 2019-03-06 14:32
     * @return: com.design.background.model.ResultData<java.util.List<com.design.background.pay.model.RefundQueryModel>>
     * 返回值说明：           成功      失败         备注
     *            code      000000   999999
     *          message              失败信息
     *            data：同一订单下，支付宝只查询某次退款，微信不传【退款单号】可查询到该订单所有退款记录
     * @throws:
     */
    @Override
    public ResultData<List<RefundQueryModel>> refundQuery(RefundModel refundModel) {
        switch (refundModel.getPayType()){
            case PayConstants.PAY_TYPE_ALIPAY:
                return aliPayService.refundQuery(refundModel);
            case PayConstants.PAY_TYPE_WXPAY:
                return wxPayService.refundQuery(refundModel);
            case PayConstants.PAY_TYPE_UNIONPAY:
                return ResultData.fail("银联暂不支持退款查询！");
            default:
                return ResultData.fail("支付方式有误！");
        }
    }
    /**
     * @Title: closeTrade
     * @Description: 统一收单交易关闭
     * @Author: lxt
     * @param: payType   【必填】 支付方式       1:支付宝 2:微信 3:银联
     * @param: outTradeNo 【支付宝和微信必填】 商户订单号
     * @param: txnAmt     【银联必填】 撤销金额  消费撤销时必须和原消费金额相同
     * @param: origQryId  【银联必填】 下单返回的queryId  　，可以从消费交易后台通知接口中或者交易状态查询接口中获取
     * @Date: 2019-03-06 14:32
     * @return: com.design.background.model.ResultData<java.lang.String>
     * 返回值说明：           成功      失败         备注
     *            code      000000   999999
     *          message     成功信息  失败信息
     * @throws:
     */
    @Override
    public ResultData<String> closeTrade(String payType, String outTradeNo, String txnAmt, String origQryId) {
        switch (payType){
            case PayConstants.PAY_TYPE_ALIPAY:
                return aliPayService.closeTrade(outTradeNo,null,null);
            case PayConstants.PAY_TYPE_WXPAY:
                return wxPayService.closeOrder(outTradeNo);
            case PayConstants.PAY_TYPE_UNIONPAY:
                return unionPayService.closeTrade(txnAmt,origQryId);
            default:
                return ResultData.fail("支付方式有误！");
        }
    }
}
