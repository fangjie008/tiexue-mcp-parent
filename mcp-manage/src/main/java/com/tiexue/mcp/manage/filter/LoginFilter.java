package com.tiexue.mcp.manage.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;

public class LoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 判断用户是否登录
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		  // 获得在下面代码中要用的request,response,session对象
		  HttpServletRequest servletRequest = (HttpServletRequest) request;
		  HttpServletResponse servletResponse = (HttpServletResponse) response;
		  HttpSession session = servletRequest.getSession();
		  // 获得用户请求的URI
		  String path = servletRequest.getRequestURI();
		  //获取项目路径
	      String contextPath=servletRequest.getContextPath();
	      //如果requestType能拿到值，并且值为XMLHttpRequest,表示客户端的请求为异步请求，那自然是ajax请求了，反之如果为null,则是普通的请求 
	      String requestType = servletRequest.getHeader("X-Requested-With"); 
		  //System.out.println(path);
		  // 从session里取员工工号信息
		  String userID = (String) session.getAttribute("userId");
		  /*创建类Constants.java，里面写的是无需过滤的页面
		  for (int i = 0; i < Constants.NoFilter_Pages.length; i++) {
		   if (path.indexOf(Constants.NoFilter_Pages[i]) > -1) {
		    chain.doFilter(servletRequest, servletResponse);
		    return;
		   }
		  }*/
		  // 登陆页面无需过滤
		  if(path.indexOf("login") > -1) {
		   chain.doFilter(servletRequest, servletResponse);
		   return;
		  }
		  // 判断如果没有取到员工信息,就跳转到登陆页面
		  if (userID == null || "".equals(userID)) {
			//ajax请求
		   if (requestType != null && "XMLHttpRequest".equals(requestType)){
			   JSONObject jObject=new JSONObject();
			   jObject.put("ok",false);
			   jObject.put("loginStatus","-1");
			   jObject.put("msg", "登录超时请重新登录！");
			   PrintWriter writer= servletResponse.getWriter();
			   writer.print(jObject.toString());
			   writer.close();
			   writer = null;
		   }   
		   else{
			   // 跳转到登陆页面
			   servletResponse.sendRedirect(contextPath+"/login.jsp");    
		   }  
			  
		   
		   
		  } else {
		   // 已经登陆,继续此次请求
		   chain.doFilter(request, response);
		  }
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
