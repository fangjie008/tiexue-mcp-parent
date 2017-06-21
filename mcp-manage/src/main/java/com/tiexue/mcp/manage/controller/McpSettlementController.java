package com.tiexue.mcp.manage.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean2a;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tiexue.mcp.base.util.DateUtil;
import com.tiexue.mcp.core.dto.McpMonthStatement;
import com.tiexue.mcp.core.service.IMcpSettlementService;
import com.tiexue.mcp.core.service.IWxConsumeService;

@Controller
@RequestMapping("mcpsettlement")
public class McpSettlementController {
	//日志
	private static Logger logger=Logger.getLogger(McpSettlementController.class);
	@Resource
	IMcpSettlementService iMcpSettlementService;
	@Resource
	IWxConsumeService iWxConsumeService;
	/**
	 * 结算信息
	 */
	@RequestMapping("list")
	public String getSettlementList(HttpServletRequest request,HttpServletResponse response){
		Integer cpId = 0;
		cpId=CommonUtil.getCpId();
		if (cpId == null || cpId == 0) {
			return "redirect:/login.jsp";
		}
		String dateTime= request.getParameter("dateTime");
		//默认取最近三个月的数据
		if(dateTime==null||dateTime.isEmpty()){
//			Date nowTime=new Date();
//			List<McpMonthStatement> list= getNearlyData(cpId,nowTime,3);
//			request.setAttribute("list", list);
		}
		else{
			
			String newTime=dateTime.replace("-", "");
			List<McpMonthStatement> list= getData(cpId,newTime);
			request.setAttribute("list", list);
		}
		request.setAttribute("dateTime", dateTime);
		return "mcpSettlement/list";
	}
	
	
	private List<McpMonthStatement> getNearlyData(int cpId,Date startDate,int nearlyNum){
		List<McpMonthStatement> mcpMonthStatements=new ArrayList<McpMonthStatement>();
		for(int i=0;i<nearlyNum;i++){
			Calendar calendar = Calendar.getInstance(); 
			calendar.add(Calendar.MONTH, -i);
			Date tempDate= calendar.getTime();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMM");
			String monthly=sdf.format(tempDate);
			Date startTime=DateUtil.getAnyMonth(monthly, "yyyyMM", 0);
			Date endTime=DateUtil.getAnyMonth(monthly, "yyyyMM", 1);
			int amount= iWxConsumeService.getCostCoinByCpId(cpId, startTime, endTime);
			McpMonthStatement tempModel=new McpMonthStatement();
			tempModel.setMonthly(monthly);
			tempModel.setAmount((float) (amount*0.01));
			mcpMonthStatements.add(tempModel);
		}
		return mcpMonthStatements;
	}
	
	private List<McpMonthStatement> getData(int cpId, String dateTime) {
		List<McpMonthStatement> mcpMonthStatements = new ArrayList<McpMonthStatement>();
		Date startTime = DateUtil.getAnyMonth(dateTime, "yyyyMM", 0);
		Date endTime = DateUtil.getAnyMonth(dateTime, "yyyyMM", 1);
		int amount = iWxConsumeService.getCostCoinByCpId(cpId, startTime, endTime);
		McpMonthStatement tempModel = new McpMonthStatement();
		tempModel.setMonthly(dateTime);
		tempModel.setAmount((float) (amount * 0.01));
		mcpMonthStatements.add(tempModel);
		return mcpMonthStatements;
	}
	
	/**
	 * 未结算数据信息(运营数据)
	 */
	@RequestMapping("data")
	public String getUnSettlementdata(HttpServletRequest request,HttpServletResponse response){
		
		return "mcpSettlement/data";
	}
	
	@RequestMapping("detail")
	public String getDetail(HttpServletRequest request,HttpServletResponse response){
		Integer cpId = 0;
		cpId=CommonUtil.getCpId();
		if (cpId == null || cpId == 0) {
			return "redirect:/login.jsp";
		}
		String dateTime= request.getParameter("monthly");
		//默认取最近三个月的数据
		if(dateTime==null||dateTime.isEmpty()){
//			Date nowTime=new Date();
//			List<McpMonthStatement> list= getNearlyData(cpId,nowTime,3);
//			request.setAttribute("list", list);
		}
		else{
			
			String newTime=dateTime.replace("-", "");
			List<McpMonthStatement> list= getData(cpId,newTime);
			request.setAttribute("list", list);
		}
		request.setAttribute("dateTime", dateTime);
		return "mcpSettlement/list";
	}
	
}
