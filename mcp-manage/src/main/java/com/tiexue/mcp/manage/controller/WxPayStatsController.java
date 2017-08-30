package com.tiexue.mcp.manage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tiexue.mcp.core.dto.WxPayStatsDto;
import com.tiexue.mcp.core.service.IWxPayStatsService;

@Controller
@RequestMapping("paystats")
public class WxPayStatsController {

	@Resource
	IWxPayStatsService iWxPayStatsService;
	private static Logger logger=Logger.getLogger(WxPayStatsController.class);
	
	@RequestMapping("index")
	public String index(HttpServletRequest request,HttpServletResponse response){
		try {
			String sign=request.getParameter("sign");
			if(sign!=null&&!sign.isEmpty()){
				request.setAttribute("sign",sign);
				WxPayStatsDto wxPayStatsDto= iWxPayStatsService.getFollowPayStats(sign);
				if(wxPayStatsDto!=null){
					request.setAttribute("payStats", wxPayStatsDto);
				}
			}
		} catch (Exception e) {
			logger.error("paystats/index exception:");
			logger.error(e);
		}
		
		return "paystats/index";
	}

	

}
