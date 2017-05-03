package com.tiexue.mcp.manage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("mcpchapter")
public class McpChapterController {
	//日志
	private static Logger logger=Logger.getLogger(McpChapterController.class);
	
	/**
	 * 获取章节信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("list")
	public String getChapterList(HttpServletRequest request,HttpServletResponse response){
		return "mcpChapter/list";
	}

}