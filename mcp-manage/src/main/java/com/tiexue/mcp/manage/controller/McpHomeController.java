package com.tiexue.mcp.manage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mcphome")
public class McpHomeController {

	//日志
	private static Logger logger=Logger.getLogger(McpHomeController.class); 
	/**
	 * 登录后入口
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/index")
	public String index(HttpServletRequest request,HttpServletResponse response){
		String userName = request.getParameter("account");
		String password = request.getParameter("password");
		System.out.println("userName is:"+ userName + ";password is:" + password);  
		boolean flag = checkUser(userName, password);
		return "/homePage";
	}
	
	
	/**
	 * 欢迎页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/welcome")
	public String welcome(HttpServletRequest request,HttpServletResponse response){
		return "/mcpHome/welcome";
	}
	
	/**
	 * 修改密码
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("edit")
	public String loginEdit(HttpServletRequest request,HttpServletResponse response){
		return "mcpHome/edit";
	}
	
	
	private boolean checkUser(String userName, String password){
		return true;
	}
}
