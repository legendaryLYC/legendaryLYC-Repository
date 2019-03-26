package com.design.background.pay.model;

import com.alipay.api.response.AlipayTradeQueryResponse;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @ClassName: TradeQueryModel
 * @Description: 交易查询结果模型类
 * @Author: lxt
 * @Date: 2019-03-06 14:25
 * @Version 1.0
 **/
public class TradeQueryModel implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 订单号(唯一)
     */
    private String outTradeNo;
    /**
     * 第三方支付交易号
     */
    private String tradeNo;
    /**
     * 订单状态
     */
    private String tradeStatus;
    /**
     * 订单状态描述
     */
    private String tradeStateDesc;

    /**
     * 交易总金额
     */
    private String totalAmount;
}
