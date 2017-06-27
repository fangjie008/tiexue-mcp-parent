package com.tiexue.mcp.manage.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tiexue.mcp.base.util.DateUtil;
import com.tiexue.mcp.core.dto.McpSettlementDetailDto;
import com.tiexue.mcp.core.dto.McpSettlementDto;
import com.tiexue.mcp.core.entity.WxBook;
import com.tiexue.mcp.core.service.IMcpSettlementService;
import com.tiexue.mcp.core.service.IWxBookService;
import com.tiexue.mcp.core.service.IWxConsumeService;
import com.tiexue.mcp.manage.util.ExportUtil;

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
	@RequestMapping("export")
	/**
	 * 导出结算信息
	 * @param request
	 * @param response
	 * @return
	 */
	public void exportExecl(HttpServletRequest request,HttpServletResponse response){
		List<McpSettlementDto> list=null;
		Integer cpId = 0;
		String newTime="";
		try {
			cpId=CommonUtil.getCpId();
			if (cpId == null || cpId == 0) {
			}
			
			String dateTime= request.getParameter("dateTime");
			//默认取最近三个月的数据
			if(dateTime==null||dateTime.isEmpty()){
				Date nowTime=new Date();
				list= getNearlyData(cpId,nowTime,3);
			}
			else{
				
			    newTime=dateTime.replace("-", "");
				list= getData(cpId,newTime);
			}
			response.setContentType("application/vnd.ms-execl");	
			String fileName=java.net.URLEncoder.encode(newTime+"结算统计","UTF-8");
			response.setHeader("content-disposition", "attachment;filename="+fileName+".xlsx");
			ServletOutputStream outputStream=response.getOutputStream();
			String[] titles={"结算月份","单月消费(元)","分成(元)","结算状态"};
			//导出
			exportSettlementExecl(list,titles,outputStream);
		} catch (Exception e) {
			logger.error("结算信息导出execl异常。信息："+e);
		}
	}
	
	@SuppressWarnings("unused")
	private List<McpSettlementDto> getNearlyData(int cpId,Date startDate,int nearlyNum){
		List<McpSettlementDto> mcpMonthStatements=new ArrayList<McpSettlementDto>();
		//不获取当月的
		for(int i=1;i<=nearlyNum;i++){
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
	/**
	 * 结算详细信息
	 * @param request
	 * @param response
	 * @return
	 */
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
	/**
	 * 导出结算详细信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("exportdetail")
	public void getExportDetail(HttpServletRequest request,HttpServletResponse response){
		Integer cpId = 0;
		String newTime="";
		List<McpSettlementDetailDto> list=null;
		try {
			cpId=CommonUtil.getCpId();
			if (cpId == null || cpId == 0) {
			}
			String dateTime= request.getParameter("monthly");
			//默认取最近三个月的数据
			if(dateTime==null||dateTime.isEmpty()){
			}
			else{
				
			    newTime=dateTime.replace("-", "");
				Date startTime = DateUtil.getAnyMonth(dateTime, "yyyyMM", 0);
				Date endTime = DateUtil.getAnyMonth(dateTime, "yyyyMM", 1);
				list= getDetailData(cpId,startTime,endTime,newTime);
				request.setAttribute("list", list);
			}
			request.setAttribute("dateTime", dateTime);
			
			response.setContentType("application/vnd.ms-execl");	
			String fileName=java.net.URLEncoder.encode(newTime+"结算详细数据","UTF-8");
			response.setHeader("content-disposition", "attachment;filename="+fileName+".xlsx");
			ServletOutputStream outputStream=response.getOutputStream();
			String[] titles={"结算月份","CPBId","小说","消费"};
			//导出
			exportSettlementDetailExecl(list,titles,outputStream);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
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
	 * 运营数据
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
	 * 运营数据导出
	 */
	@RequestMapping("exportoperate")
	public void exportUnSettlementdata(HttpServletRequest request,HttpServletResponse response){
		Integer cpId = 0;
		List<McpSettlementDto> list=null;
		cpId=CommonUtil.getCpId();
		if (cpId == null || cpId == 0) {
		}
		String startTime= request.getParameter("startTime");
		String endTime= request.getParameter("endTime");
		try {
			if((startTime==null||startTime.isEmpty())){
			}
			else{
				if(endTime==null||endTime.isEmpty()){
					endTime=DateUtil.date2Str(new Date());
				}
				
				list= new ArrayList<McpSettlementDto>();
				list.add(getDataByTime(cpId,startTime,endTime));
			}
			response.setContentType("application/vnd.ms-execl");	
			String fileName=java.net.URLEncoder.encode(startTime+"运营数据","UTF-8");
			response.setHeader("content-disposition", "attachment;filename="+fileName+".xlsx");
			ServletOutputStream outputStream=response.getOutputStream();
			String[] titles={"开始时间","结束时间","消费金额","分成"};
			//导出
			exportOperateExecl(list,titles,outputStream);
		} catch (Exception e) {
			logger.error("导出运营数据失败。"+e);
		}
	}
	/**
	 * 运营详细数据
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("operatedetail")
	public String exportoperateDetail(HttpServletRequest request,HttpServletResponse response){
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
	/**
	 * 导出运营数据详细信息
	 */
	@RequestMapping("exportoperatedetail")
	public void getoperateDetail(HttpServletRequest request,HttpServletResponse response){
		Integer cpId = 0;
		List<McpSettlementDetailDto> list=null;
		cpId=CommonUtil.getCpId();
		if (cpId == null || cpId == 0) {
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
			list= getDetailData(cpId,startDate,newEndDate,"");
			request.setAttribute("list", list);
		}
		try {
			response.setContentType("application/vnd.ms-execl");	
			String fileName=java.net.URLEncoder.encode(startTime+"运营数据","UTF-8");
			response.setHeader("content-disposition", "attachment;filename="+fileName+".xlsx");
			ServletOutputStream outputStream=response.getOutputStream();
			String[] titles={"开始时间","结束时间","CPBId","小说","消费"};
			//导出
			exportOperateDetailExecl(list,titles,outputStream);
		} catch (Exception e) {
			logger.error("导出运营详细数据出错."+e);
		}
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
	/**
	 * 结算信息导出
	 * @param sDtos
	 * @param titles
	 * @param outputStream
	 */
	private void exportSettlementExecl(List<McpSettlementDto> sDtos,String[] titles,ServletOutputStream outputStream){
		//创建workbook 对应的execl文件
		XSSFWorkbook workbook=new XSSFWorkbook();
		//创建一个sheet
		XSSFSheet sheet=workbook.createSheet("结算信息");
		ExportUtil exportUtil=new ExportUtil(workbook, sheet);
		//获取设置好的样式
		XSSFCellStyle headStyle= exportUtil.getHeadStyle();
		XSSFCellStyle bodyStyle= exportUtil.getBodyStyle();
		//新建表头
		XSSFRow headRow= sheet.createRow(0);
		//新建单元格
		XSSFCell cell=null;
		//给表头添加数据
		for (int i=0;i<titles.length;i++) {
			cell=headRow.createCell(i);
			cell.setCellStyle(headStyle);
			cell.setCellValue(titles[i]);
		}
		//构建表体数据
		if(sDtos!=null&&sDtos.size()>0){
			for(int j=0;j<sDtos.size();j++){
				XSSFRow bodyRow=sheet.createRow(j+1);
				McpSettlementDto settlementDto= sDtos.get(j);
				//结算月份数据
				cell=bodyRow.createCell(0);
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(settlementDto.getMonthly());
				//单月消费数据
				cell=bodyRow.createCell(1);
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(settlementDto.getConsume());
				//分成数据
				cell=bodyRow.createCell(2);
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(settlementDto.getDivideconsume());
				//结算状态数据
				cell=bodyRow.createCell(3);
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(settlementDto.getSettlementstatus());
			}
		}
		try {
			workbook.write(outputStream);
			outputStream.close();
			outputStream.flush();
		} catch (Exception e) {
			logger.error("结算导出异常。信息："+e);
		}
		finally{
			try {
				outputStream.close();
			} catch (IOException e) {
				logger.error("结算导出异常。信息："+e);
			}
		}
	}
	
	
	/**
	 * 结算详细信息导出
	 * @param sDtos
	 * @param titles
	 * @param outputStream
	 */
	private void exportSettlementDetailExecl(List<McpSettlementDetailDto> sDtos,String[] titles,ServletOutputStream outputStream){
		//创建workbook 对应的execl文件
		XSSFWorkbook workbook=new XSSFWorkbook();
		//创建一个sheet
		XSSFSheet sheet=workbook.createSheet("结算信息");
		ExportUtil exportUtil=new ExportUtil(workbook, sheet);
		//获取设置好的样式
		XSSFCellStyle headStyle= exportUtil.getHeadStyle();
		XSSFCellStyle bodyStyle= exportUtil.getBodyStyle();
		//新建表头
		XSSFRow headRow= sheet.createRow(0);
		//新建单元格
		XSSFCell cell=null;
		//给表头添加数据
		for (int i=0;i<titles.length;i++) {
			cell=headRow.createCell(i);
			cell.setCellStyle(headStyle);
			cell.setCellValue(titles[i]);
		}
		//构建表体数据
		if(sDtos!=null&&sDtos.size()>0){
			for(int j=0;j<sDtos.size();j++){
				XSSFRow bodyRow=sheet.createRow(j+1);
				McpSettlementDetailDto settlementDto= sDtos.get(j);
				//结算月份数据
				cell=bodyRow.createCell(0);
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(settlementDto.getMonthly());
				//CPBId数据
				cell=bodyRow.createCell(1);
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(settlementDto.getCpBId());
				//书籍名称
				cell=bodyRow.createCell(2);
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(settlementDto.getBookName());
				//消费
				cell=bodyRow.createCell(3);
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(settlementDto.getConsume());
			}
		}
		try {
			workbook.write(outputStream);
			outputStream.close();
			outputStream.flush();
		} catch (Exception e) {
			logger.error("结算导出异常。信息："+e);
		}
		finally{
			try {
				outputStream.close();
			} catch (IOException e) {
				logger.error("结算导出异常。信息："+e);
			}
		}
	}
	
	/**
	 * 运营信息导出
	 * @param sDtos
	 * @param titles
	 * @param outputStream
	 */
	private void exportOperateExecl(List<McpSettlementDto> sDtos,String[] titles,ServletOutputStream outputStream){
		//创建workbook 对应的execl文件
		XSSFWorkbook workbook=new XSSFWorkbook();
		//创建一个sheet
		XSSFSheet sheet=workbook.createSheet("结算信息");
		ExportUtil exportUtil=new ExportUtil(workbook, sheet);
		//获取设置好的样式
		XSSFCellStyle headStyle= exportUtil.getHeadStyle();
		XSSFCellStyle bodyStyle= exportUtil.getBodyStyle();
		//新建表头
		XSSFRow headRow= sheet.createRow(0);
		//新建单元格
		XSSFCell cell=null;
		//给表头添加数据
		for (int i=0;i<titles.length;i++) {
			cell=headRow.createCell(i);
			cell.setCellStyle(headStyle);
			cell.setCellValue(titles[i]);
		}
		//构建表体数据
		if(sDtos!=null&&sDtos.size()>0){
			for(int j=0;j<sDtos.size();j++){
				XSSFRow bodyRow=sheet.createRow(j+1);
				McpSettlementDto settlementDto= sDtos.get(j);
				//开始时间数据
				cell=bodyRow.createCell(0);
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(settlementDto.getStartTime());
				//结束时间数据
				cell=bodyRow.createCell(1);
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(settlementDto.getEndTime());
				//消费数据
				cell=bodyRow.createCell(2);
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(settlementDto.getConsume());
				//分成数据
				cell=bodyRow.createCell(3);
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(settlementDto.getDivideconsume());
			}
		}
		try {
			workbook.write(outputStream);
			outputStream.close();
			outputStream.flush();
		} catch (Exception e) {
			logger.error("结算导出异常。信息："+e);
		}
		finally{
			try {
				outputStream.close();
			} catch (IOException e) {
				logger.error("结算导出异常。信息："+e);
			}
		}
	}
	
	
	/**
	 * 运营详细信息导出
	 * @param sDtos
	 * @param titles
	 * @param outputStream
	 */
	private void exportOperateDetailExecl(List<McpSettlementDetailDto> sDtos,String[] titles,ServletOutputStream outputStream){
		//创建workbook 对应的execl文件
		XSSFWorkbook workbook=new XSSFWorkbook();
		//创建一个sheet
		XSSFSheet sheet=workbook.createSheet("结算信息");
		ExportUtil exportUtil=new ExportUtil(workbook, sheet);
		//获取设置好的样式
		XSSFCellStyle headStyle= exportUtil.getHeadStyle();
		XSSFCellStyle bodyStyle= exportUtil.getBodyStyle();
		//新建表头
		XSSFRow headRow= sheet.createRow(0);
		//新建单元格
		XSSFCell cell=null;
		//给表头添加数据
		for (int i=0;i<titles.length;i++) {
			cell=headRow.createCell(i);
			cell.setCellStyle(headStyle);
			cell.setCellValue(titles[i]);
		}
		//构建表体数据
		if(sDtos!=null&&sDtos.size()>0){
			for(int j=0;j<sDtos.size();j++){
				XSSFRow bodyRow=sheet.createRow(j+1);
				McpSettlementDetailDto settlementDto= sDtos.get(j);
				//开始时间数据
				cell=bodyRow.createCell(0);
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(settlementDto.getStartTime());
				//结束时间数据
				cell=bodyRow.createCell(1);
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(settlementDto.getEndTime());
				//CPBId数据
				cell=bodyRow.createCell(2);
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(settlementDto.getCpBId());
				//书籍名称
				cell=bodyRow.createCell(3);
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(settlementDto.getBookName());
				//消费
				cell=bodyRow.createCell(4);
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(settlementDto.getConsume());
			}
		}
		try {
			workbook.write(outputStream);
			outputStream.close();
			outputStream.flush();
		} catch (Exception e) {
			logger.error("结算导出异常。信息："+e);
		}
		finally{
			try {
				outputStream.close();
			} catch (IOException e) {
				logger.error("结算导出异常。信息："+e);
			}
		}
	}
}
