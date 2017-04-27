package com.tiexue.mcp.manage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("mcpsettlement")
public class McpSettlementController {
	//日志
	private static Logger logger=Logger.getLogger(McpSettlementController.class);
	
	/**
	 * 结算信息
	 */
	@RequestMapping("list")
	public String getSettlementList(HttpServletRequest request,HttpServletResponse response){
		return "mcpSettlement/list";
	}
	
	
	/**
	 * 未结算数据信息(运营数据)
	 */
	@RequestMapping("data")
	public String getUnSettlementdata(HttpServletRequest request,HttpServletResponse response){
		return "mcpSettlement/data";
	}
	
}
