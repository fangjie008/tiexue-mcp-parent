package com.tiexue.mcp.manage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("mcpbaseinfo")
public class McpBaseInfoController {
	//日志
	private static Logger logger=Logger.getLogger(McpBaseInfoController.class);
	
	
	
	
	/**
	 * 获取基础信息
	 */
	@RequestMapping("model")
	public String getbaseInfo(HttpServletRequest request,HttpServletResponse response){
		return "mcpBaseInfo/model";
	}
	
	/**
	 * 获取基础信息
	 */
	@RequestMapping("add")
	public String addbaseInfo(HttpServletRequest request,HttpServletResponse response){
		return "mcpBaseInfo/add";
	}
	
	
	/**
	 * 获取基础信息
	 */
	@RequestMapping("show")
	public String showbaseInfo(HttpServletRequest request,HttpServletResponse response){
		return "mcpBaseInfo/show";
	}
	

}
