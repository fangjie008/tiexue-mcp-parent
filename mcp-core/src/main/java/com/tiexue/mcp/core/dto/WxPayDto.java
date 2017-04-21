package com.tiexue.mcp.core.dto;

public class WxPayDto {

	// 充值Id
	private String ordernum;
	// 充值类型
	private Integer paytype;
	// 充值类型名称
	private String paytypeName;
	// 充值金额
	private String amount;
	// 小说币
	private Integer Count;
	// 充值时间
	private String createtime;
	// 单位
	private Integer unit;
	// 单位对应名称
	private String unitName;
	// 充值状态
	private Integer orderstatus;
	// 充值状态字符
	private String orderstatusStr;

	public String getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}

	public Integer getPaytype() {
		return paytype;
	}

	public void setPaytype(Integer paytype) {
		this.paytype = paytype;
	}

	public String getPaytypeName() {
		return paytypeName;
	}

	public void setPaytypeName(String paytypeName) {
		this.paytypeName = paytypeName;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Integer getCount() {
		return Count;
	}

	public void setCount(Integer count) {
		Count = count;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public Integer getUnit() {
		return unit;
	}

	public void setUnit(Integer unit) {
		this.unit = unit;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public Integer getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(Integer orderstatus) {
		this.orderstatus = orderstatus;
	}

	public String getOrderstatusStr() {
		return orderstatusStr;
	}

	public void setOrderstatusStr(String orderstatusStr) {
		this.orderstatusStr = orderstatusStr;
	}

}
