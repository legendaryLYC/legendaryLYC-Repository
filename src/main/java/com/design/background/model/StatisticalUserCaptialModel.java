package com.design.background.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * 统计分析资金交易数据保存返回给前端的实体类
 *  时间:2019/2/21
 * 最后修改时间:2019/2/21
 * 注意事项:无
* @author 周天
*
*/
public class StatisticalUserCaptialModel {

	private BigDecimal allCaptial = new BigDecimal("0"); // 平台总交易额，默认值为0
	
	private BigDecimal thisMonthCaptial = new BigDecimal("0"); // 这个月的平台的交易额，默认值为0
	
	private BigDecimal allAlipayIncome = new BigDecimal("0"); // 平台的支付宝收入额，默认值为0
	
	private BigDecimal allWeChatIncome = new BigDecimal("0"); // 平台的微信收入额，默认值为0
	
	private BigDecimal allUnionpayIncome = new BigDecimal("0"); // 平台的银联收入额，默认值为0
	
	private BigDecimal allAlipayExpend = new BigDecimal("0"); // 平台的支付宝支出额，默认值为0
	
	private BigDecimal allWeChatExpend = new BigDecimal("0"); // 平台的微信支出额，默认值为0
	
	private BigDecimal allUnionpayExpend = new BigDecimal("0"); // 平台的银联支出额，默认值为0
	
	private List<BigDecimal> lastWeekIncome; // 上周每天的收入 
	
	private List<BigDecimal> lastWeekExpend; // 上周每天的支出 
	
	private List<BigDecimal> thisWeekIncome; // 本周每天的收入 
	
	private List<BigDecimal> thisWeekExpend; // 本周每天的支出 

	public BigDecimal getAllCaptial() {
		return allCaptial;
	}

	public void setAllCaptial(BigDecimal allCaptial) {
		this.allCaptial = allCaptial;
	}

	public BigDecimal getThisMonthCaptial() {
		return thisMonthCaptial;
	}

	public void setThisMonthCaptial(BigDecimal thisMonthCaptial) {
		this.thisMonthCaptial = thisMonthCaptial;
	}

	public BigDecimal getAllAlipayIncome() {
		return allAlipayIncome;
	}

	public void setAllAlipayIncome(BigDecimal allAlipayIncome) {
		this.allAlipayIncome = allAlipayIncome;
	}

	public BigDecimal getAllWeChatIncome() {
		return allWeChatIncome;
	}

	public void setAllWeChatIncome(BigDecimal allWeChatIncome) {
		this.allWeChatIncome = allWeChatIncome;
	}

	public BigDecimal getAllUnionpayIncome() {
		return allUnionpayIncome;
	}

	public void setAllUnionpayIncome(BigDecimal allUnionpayIncome) {
		this.allUnionpayIncome = allUnionpayIncome;
	}

	public BigDecimal getAllAlipayExpend() {
		return allAlipayExpend;
	}

	public void setAllAlipayExpend(BigDecimal allAlipayExpend) {
		this.allAlipayExpend = allAlipayExpend;
	}

	public BigDecimal getAllWeChatExpend() {
		return allWeChatExpend;
	}

	public void setAllWeChatExpend(BigDecimal allWeChatExpend) {
		this.allWeChatExpend = allWeChatExpend;
	}

	public BigDecimal getAllUnionpayExpend() {
		return allUnionpayExpend;
	}

	public void setAllUnionpayExpend(BigDecimal allUnionpayExpend) {
		this.allUnionpayExpend = allUnionpayExpend;
	}

	public List<BigDecimal> getLastWeekIncome() {
		return lastWeekIncome;
	}

	public void setLastWeekIncome(List<BigDecimal> lastWeekIncome) {
		this.lastWeekIncome = lastWeekIncome;
	}

	public List<BigDecimal> getLastWeekExpend() {
		return lastWeekExpend;
	}

	public void setLastWeekExpend(List<BigDecimal> lastWeekExpend) {
		this.lastWeekExpend = lastWeekExpend;
	}

	public List<BigDecimal> getThisWeekIncome() {
		return thisWeekIncome;
	}

	public void setThisWeekIncome(List<BigDecimal> thisWeekIncome) {
		this.thisWeekIncome = thisWeekIncome;
	}

	public List<BigDecimal> getThisWeekExpend() {
		return thisWeekExpend;
	}

	public void setThisWeekExpend(List<BigDecimal> thisWeekExpend) {
		this.thisWeekExpend = thisWeekExpend;
	}

	@Override
	public String toString() {
		return "StatisticalUserCaptialModel [allCaptial=" + allCaptial + ", thisMonthCaptial=" + thisMonthCaptial
				+ ", allAlipayIncome=" + allAlipayIncome + ", allWeChatIncome=" + allWeChatIncome
				+ ", allUnionpayIncome=" + allUnionpayIncome + ", allAlipayExpend=" + allAlipayExpend
				+ ", allWeChatExpend=" + allWeChatExpend + ", allUnionpayExpend=" + allUnionpayExpend
				+ ", lastWeekIncome=" + lastWeekIncome + ", lastWeekExpend=" + lastWeekExpend + ", thisWeekIncome="
				+ thisWeekIncome + ", thisWeekExpend=" + thisWeekExpend + "]";
	}
	
	
	
}
