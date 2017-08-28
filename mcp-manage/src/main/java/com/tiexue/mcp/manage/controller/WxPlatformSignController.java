package com.tiexue.mcp.manage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("platform")
public class WxPlatformSignController {
	private static Logger logger=Logger.getLogger(WxPlatformSignController.class);
	
	
	@RequestMapping("index")
	public String index(HttpServletRequest request,HttpServletResponse response){
		return "platform/index";
	}
	
}
