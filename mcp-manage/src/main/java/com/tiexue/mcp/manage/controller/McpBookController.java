package com.tiexue.mcp.manage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tiexue.mcp.core.service.IMcpBookService;
import com.tiexue.mcp.core.service.IWxBookService;

@Controller
@RequestMapping("mcpbook")
public class McpBookController {
	//日志
	private static Logger logger=Logger.getLogger(McpBookController.class);
	
	@Resource
	IMcpBookService mcpBookService;
	
	/**
	 * 获取第三方的图书信息
	 * 逻辑:需要判断用户是否登录,登录的话需要拿到用户的Id信息,然后根据id查询对应的图书列表数据
	 * 前期先不分页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("list")
	public String getMcpBookList(HttpServletRequest request,HttpServletResponse response){
		//每个合作方可以查看自己的作品列表
		
		return "mcpBook/list";
	}
	
	
	
	
	
	
	
}
