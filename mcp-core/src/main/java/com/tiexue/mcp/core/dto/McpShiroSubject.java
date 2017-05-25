package com.tiexue.mcp.core.dto;

import com.tiexue.mcp.core.entity.McpAdmin;
import com.tiexue.mcp.core.entity.McpBaseInfo;

public class McpShiroSubject {
	
	//类型
	private Integer mcpUserType;
	//管理员
	private McpAdmin mcpAdmin;
	//合作者
	private McpBaseInfo mcpBaseInfo;
	//用户名
	private String userName;
	//密码
	private String password;
	
	
	public Integer getMcpUserType()
	{
		return mcpUserType;
	}
	public void setMcpUserType(Integer value)
	{
		this.mcpUserType = value;
	}
	
	public McpAdmin getMcpAdmin()
	{
		return mcpAdmin;
	}
	
	public void setMcpAdmin(McpAdmin value)
	{
		mcpAdmin = value;
		if (value !=null) {
			this.userName = value.getName();
			this.password = value.getPassword();
		}
	}

	public McpBaseInfo getMcpBaseInfo()
	{
		return mcpBaseInfo;
	}
	
	public void setMcpBaseInfo(McpBaseInfo value)
	{
		this.mcpBaseInfo = value;
		if (value !=null) {
			this.userName = value.getName();
			this.password = value.getPassword();
		}
	}
	
	public String getUserName()
	{
		return this.userName;
	}
	public void setUserName(String value)
	{
		this.userName = value;
	}
	
	public String getPassword()
	{
		return this.password;
	}
	public void setPassword(String value)
	{
		this.password = value;
	}
}
