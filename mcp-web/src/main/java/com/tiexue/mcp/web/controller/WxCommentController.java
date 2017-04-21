package com.tiexue.mcp.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tiexue.mcp.core.service.IWxCommentService;

@Controller
@RequestMapping("wxComment")
public class WxCommentController {

	
	@Resource
	IWxCommentService commentService;
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("list")
	public String getList(HttpServletRequest request){
		return "";
	}

}
