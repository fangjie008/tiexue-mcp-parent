package com.tiexue.mcp.manage.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
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
import com.tiexue.mcp.core.dto.McpSettlementDetailDto;
import com.tiexue.mcp.core.dto.McpSettlementDto;
import com.tiexue.mcp.core.entity.WxBook;
import com.tiexue.mcp.core.service.IMcpSettlementService;
import com.tiexue.mcp.core.service.IWxBookService;
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
	@Resource
	IWxBookService iWxBookService;
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
			Date nowTime=new Date();
			List<McpSettlementDto> list= getNearlyData(cpId,nowTime,3);
			request.setAttribute("list", list);
		}
		else{
			
			String newTime=dateTime.replace("-", "");
			List<McpSettlementDto> list= getData(cpId,newTime);
			request.setAttribute("list", list);
		}
		request.setAttribute("dateTime", dateTime);
		return "mcpSettlement/list";
	}
	
	
	@SuppressWarnings("unused")
	private List<McpSettlementDto> getNearlyData(int cpId,Date startDate,int nearlyNum){
		List<McpSettlementDto> mcpMonthStatements=new ArrayList<McpSettlementDto>();
		for(int i=0;i<nearlyNum;i++){
			Calendar calendar = Calendar.getInstance(); 
			calendar.add(Calendar.MONTH, -i);
			Date tempDate= calendar.getTime();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMM");
			String monthly=sdf.format(tempDate);
			McpSettlementDto tempModel=iMcpSettlementService.getData(cpId, monthly);
			mcpMonthStatements.add(tempModel);
		}
		return mcpMonthStatements;
	}
	
	private List<McpSettlementDto> getData(int cpId, String dateTime) {
		List<McpSettlementDto> mcpMonthStatements = new ArrayList<McpSettlementDto>();
		McpSettlementDto tempModel=iMcpSettlementService.getData(cpId, dateTime);
		mcpMonthStatements.add(tempModel);
		return mcpMonthStatements;
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
		}
		else{
			
			String newTime=dateTime.replace("-", "");
			Date startTime = DateUtil.getAnyMonth(dateTime, "yyyyMM", 0);
			Date endTime = DateUtil.getAnyMonth(dateTime, "yyyyMM", 1);
			List<McpSettlementDetailDto> list= getDetailData(cpId,startTime,endTime,newTime);
			request.setAttribute("list", list);
		}
		request.setAttribute("dateTime", dateTime);
		return "mcpSettlement/detail";
	}
	
	
	@SuppressWarnings({ "unused", "unchecked" })
	private List<McpSettlementDetailDto> getDetailData(int cpId, Date startTime,Date endTime,String dateTime) {
		List<McpSettlementDetailDto> mcpMonthStatements = new ArrayList<McpSettlementDetailDto>();

		List<WxBook> wxBooks= iWxBookService.getBookIdByCPId(cpId);
		if(wxBooks!=null&&!wxBooks.isEmpty()){
			for (WxBook wxBook : wxBooks) {
				  int id=wxBook.getId();
				  int amount=iWxConsumeService.getCostCoinByBookId(id, startTime, endTime);
				  if(amount>0){
					  McpSettlementDetailDto tempModel = new McpSettlementDetailDto();
					  tempModel.setBookName(wxBook.getName());
					  tempModel.setCpBId(wxBook.getCollectionid());
					  tempModel.setMonthly(dateTime);
					  tempModel.setConsume((float)(amount*0.01));
					  tempModel.setStartTime(DateUtil.date2Str(startTime,"yyyy-MM-dd"));
					  tempModel.setEndTime(DateUtil.date2Str(endTime, "yyyy-MM-dd"));
					  mcpMonthStatements.add(tempModel);
				  }
			}
		}
		//倒序
		if(!mcpMonthStatements.isEmpty()){
			Collections.sort(mcpMonthStatements);
			Collections.reverse(mcpMonthStatements);
		}
		return mcpMonthStatements;
	}
	
	
	/**
	 * 未结算数据信息(运营数据)
	 */
	@RequestMapping("operatelist")
	public String getUnSettlementdata(HttpServletRequest request,HttpServletResponse response){
		Integer cpId = 0;
		cpId=CommonUtil.getCpId();
		if (cpId == null || cpId == 0) {
			return "redirect:/login.jsp";
		}
		String startTime= request.getParameter("startTime");
		String endTime= request.getParameter("endTime");
		if((startTime==null||startTime.isEmpty())){
		}
		else{
			if(endTime==null||endTime.isEmpty()){
				endTime=DateUtil.date2Str(new Date());
			}
			
			List<McpSettlementDto> list= new ArrayList<McpSettlementDto>();
			list.add(getDataByTime(cpId,startTime,endTime));
			request.setAttribute("list", list);
		}
		request.setAttribute("startTime", startTime);
		request.setAttribute("endTime", endTime);
		return "mcpSettlement/operatelist";
	}
	
	/**
	 * 运营数据详细信息
	 */
	@RequestMapping("operatedetail")
	public String getoperateDetail(HttpServletRequest request,HttpServletResponse response){
		Integer cpId = 0;
		cpId=CommonUtil.getCpId();
		if (cpId == null || cpId == 0) {
			return "redirect:/login.jsp";
		}
		String startTime= request.getParameter("startTime");
		String endTime= request.getParameter("endTime");
		if((startTime==null||startTime.isEmpty())){
		}
		else{
			if(endTime==null||endTime.isEmpty()){
				endTime=DateUtil.date2Str(new Date());
			}
			Date startDate=DateUtil.str2Date(startTime, "yyyy-MM-dd");
			Date endDate=DateUtil.str2Date(endTime, "yyyy-MM-dd");
			Date newEndDate= DateUtil.AddDays(endDate, 1);
			List<McpSettlementDetailDto> list= getDetailData(cpId,startDate,newEndDate,"");
			request.setAttribute("list", list);
		}
		request.setAttribute("startTime", startTime);
		request.setAttribute("endTime", endTime);
		return "mcpSettlement/operatedetail";
	}
	
	private McpSettlementDto getDataByTime(int cpId, String startTime,String endTime) {
		Date startDate=DateUtil.str2Date(startTime, "yyyy-MM-dd");
		Date endDate=DateUtil.str2Date(endTime, "yyyy-MM-dd");
		McpSettlementDto tempModel=new McpSettlementDto();
		Date newEndDate= DateUtil.AddDays(endDate, 1);
		int amount= iWxConsumeService.getCostCoinByCpId(cpId, startDate, newEndDate);
		tempModel.setConsume((float)(amount*0.01));
		tempModel.setCpId(cpId);
		tempModel.setDivideconsume((float)(amount*0.5*0.01));
		tempModel.setStartTime(startTime);
		tempModel.setEndTime(endTime);
		return tempModel;
	}
}
