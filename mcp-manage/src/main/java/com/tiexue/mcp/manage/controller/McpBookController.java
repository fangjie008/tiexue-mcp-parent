package com.tiexue.mcp.manage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("mcpbook")
public class McpBookController {
	//日志
	private static Logger logger=Logger.getLogger(McpBookController.class);
	
	/**
	 * 获取第三方的图书信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("list")
	public String getMcpBookList(HttpServletRequest request,HttpServletResponse response){
		return "mcpBook/list";
	}
}
