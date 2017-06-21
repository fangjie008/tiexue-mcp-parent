package com.tiexue.mcp.core.dto;

import java.time.Month;

public class McpMonthStatement {

	/**
	 * 月度
	 */
	private String monthly;
	/**
	 * 消费金额
	 */
	private float Amount;
	public String getMonthly() {
		return monthly;
	}
	public void setMonthly(String monthly) {
		this.monthly = monthly;
	}
	public float getAmount() {
		return Amount;
	}
	public void setAmount(float amount) {
		Amount = amount;
	}
	
	
}
