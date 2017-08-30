package com.tiexue.mcp.core.define;

import java.util.HashMap;
import java.util.Map;

public class McpAdminType {


	/**
	 * 管理员
	 */
	public static final Integer AdminType_Admin=1;
	/**
	 * 用户
	 */
	public static final Integer AdminType_User=2;
	
	/**
	 * 用户类型组
	 */
	public static Map<Integer, String> AdminTypeMap=new HashMap<Integer,String>(){
		{
			put(AdminType_Admin,"管理员");
			put(AdminType_User, "用户");
		}
	};

}
