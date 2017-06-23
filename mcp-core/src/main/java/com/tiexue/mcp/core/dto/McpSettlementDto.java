package com.tiexue.mcp.core.dto;



public class McpSettlementDto {

	/**
	 * 月度
	 */
	private String monthly;
	/**
	 * 消费金额
	 */
	private float consume;
	/**
	 * 结算金额
	 */
	private float divideconsume;
	/**
	 * 结算状态
	 */
	private String settlementstatus;
	
	/**
	 * 查询开始时间
	 */
	private String startTime;
	/**
	 * 查询结束时间
	 */
	private String endTime;
	
	
	private Integer cpId;

	public String getMonthly() {
		return monthly;
	}

	public void setMonthly(String monthly) {
		this.monthly = monthly;
	}

	public float getConsume() {
		return consume;
	}

	public void setConsume(float consume) {
		this.consume = consume;
	}

	public float getDivideconsume() {
		return divideconsume;
	}

	public void setDivideconsume(float divideconsume) {
		this.divideconsume = divideconsume;
	}

	public String getSettlementstatus() {
		return settlementstatus;
	}

	public void setSettlementstatus(String settlementstatus) {
		this.settlementstatus = settlementstatus;
	}

	public Integer getCpId() {
		return cpId;
	}

	public void setCpId(Integer cpId) {
		this.cpId = cpId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
	
	
}
