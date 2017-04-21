package com.tiexue.mcp.core.dto;

public class ResultMsg {

	/**
	 * 状态,True 成功,失败
	 */
	private Boolean status;
	
	/**
	 * 返回数字
	 */
	private Integer num;
	
	/**
	 * 返回消息
	 */
	private String Msg;

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getMsg() {
		return Msg;
	}

	public void setMsg(String msg) {
		Msg = msg;
	}

}
