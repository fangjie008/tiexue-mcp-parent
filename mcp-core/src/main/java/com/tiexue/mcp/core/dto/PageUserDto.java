package com.tiexue.mcp.core.dto;
/**
 * 页面cookie中获取的用户登录信息
 * @author zhangxiaowei
 *
 */
public class PageUserDto {

	private String id;

    private String name;

    private String openid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}



}
