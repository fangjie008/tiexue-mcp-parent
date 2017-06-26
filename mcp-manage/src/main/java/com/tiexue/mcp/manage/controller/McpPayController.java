package com.tiexue.mcp.manage.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tiexue.mcp.base.util.DateUtil;
import com.tiexue.mcp.core.dto.WxPayDto;
import com.tiexue.mcp.core.entity.EnumType;
import com.tiexue.mcp.core.entity.WxBook;
import com.tiexue.mcp.core.entity.WxPay;
import com.tiexue.mcp.core.service.IWxBookService;
import com.tiexue.mcp.core.service.IWxPayService;

@Controller
@RequestMapping("mcppay")
public class McpPayController {
	Logger logger=Logger.getLogger(McpPayController.class);
	@Resource
	IWxBookService iWxBookService;
	@Resource
	IWxPayService iWxPayService;
	
	
	@RequestMapping("list")
	public String getPay(HttpServletRequest request,HttpServletResponse response){
		
		try {
			String bookIdStr=request.getParameter("bookId");
			String startTimeStr=request.getParameter("startTime");
			if(bookIdStr!=null&&!bookIdStr.isEmpty()&&startTimeStr!=null&&!startTimeStr.isEmpty()){
				int bookId=0;
				bookId=Integer.parseInt(bookIdStr);
				Date startTime=DateUtil.str2Date(startTimeStr,"yyyy-MM-dd HH:mm:ss");
				WxBook wxBook= iWxBookService.selectByPrimaryKey(bookId);
				List<WxPay> wxPays=iWxPayService.getPaysByBookId(bookId, startTime);
				if(wxPays!=null&&!wxPays.isEmpty()){
			    List<WxPayDto> wxPayDtos=  wxPayFill(wxPays,wxBook);
			    float count=0;
			    for (WxPayDto wxPayDto : wxPayDtos) {
					count+=wxPayDto.getAmount();
				}
			    request.setAttribute("bookId", wxBook.getId());
			    request.setAttribute("bookName", wxBook.getName());
			    request.setAttribute("count", count);
				request.setAttribute("wxPays", wxPayDtos);
				}
			}
			request.setAttribute("bookId",bookIdStr);
			request.setAttribute("startTime",startTimeStr);
		} catch (Exception e) {
			logger.error("McpPayController.getPay error. msg:"+e);
		}
		return "mcpPay/list";
	}
	
	
	@RequestMapping("detail")
	public String getPayDetail(HttpServletRequest request,HttpServletResponse response){
		
		try {
			String bookIdStr=request.getParameter("bookId");
			String startTimeStr=request.getParameter("startTime");
			if(bookIdStr!=null&&!bookIdStr.isEmpty()&&startTimeStr!=null&&!startTimeStr.isEmpty()){
				int bookId=0;
				bookId=Integer.parseInt(bookIdStr);
				Date startTime=DateUtil.str2Date(startTimeStr,"yyyy-MM-dd HH:mm:ss");
				WxBook wxBook= iWxBookService.selectByPrimaryKey(bookId);
				List<WxPay> wxPays=iWxPayService.getPaysByBookId(bookId, startTime);
				if(wxPays!=null&&!wxPays.isEmpty()){
			    List<WxPayDto> wxPayDtos=  wxPayFill(wxPays,wxBook);
			    float count=0;
			    for (WxPayDto wxPayDto : wxPayDtos) {
					count+=wxPayDto.getAmount();
				}
			    request.setAttribute("bookId", wxBook.getId());
			    request.setAttribute("bookName", wxBook.getName());
			    request.setAttribute("count", count);
				request.setAttribute("wxPays", wxPayDtos);
				}
			}
			request.setAttribute("bookId",bookIdStr);
			request.setAttribute("startTime",startTimeStr);
		} catch (Exception e) {
			logger.error("McpPayController.getPay error. msg:"+e);
		}
		return "mcpPay/detail";
	}
	
	private List<WxPayDto> wxPayFill(List<WxPay> wxpays,WxBook wxBook) {
		List<WxPayDto> wxPayDtos = new ArrayList<WxPayDto>();
		if (wxpays != null) {
			for (WxPay pay : wxpays) {
				WxPayDto payDto = new WxPayDto();
				payDto.setOrdernum(pay.getOrdernum());
				payDto.setPaytype(pay.getPaytype());
				payDto.setPaytypeName(EnumType.PayType.get(pay.getPaytype()));
				if(pay.getAmount()!=null){
					float amount=(float)(pay.getAmount()*0.01);
					payDto.setAmount(amount);
				}
				payDto.setCount(pay.getCount());
				payDto.setOrderstatus(pay.getOrderstatus());
				payDto.setOrderstatusStr(EnumType.OrderStatus.get(pay.getOrderstatus()));
				payDto.setCreatetime(DateUtil.date2Str(pay.getCreatetime(), "yyyy-MM-dd HH:mm:ss"));
				payDto.setUnit(pay.getUnit());
				payDto.setUnitName(EnumType.PayUnit.get(pay.getUnit()));
				if(wxBook!=null){
					payDto.setBookId(wxBook.getId());
					payDto.setBookName(wxBook.getName());
				}
				wxPayDtos.add(payDto);
			}
		}
		return wxPayDtos;
	}

}
