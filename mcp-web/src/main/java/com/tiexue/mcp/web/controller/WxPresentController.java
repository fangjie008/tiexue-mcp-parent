package com.tiexue.mcp.web.controller;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.tiexue.mcp.base.util.DateUtil;
import com.tiexue.mcp.core.dto.PageUserDto;
import com.tiexue.mcp.core.entity.WxPresent;
import com.tiexue.mcp.core.service.IWxPresentService;
import com.tiexue.mcp.core.service.IWxUserService;

@Controller
@RequestMapping("wxPresent")
public class WxPresentController {
	// 打印日志
	private Logger logger = Logger.getLogger(WxPayController.class);
	@Resource
	IWxPresentService wxpresentSer;
	@Resource
	IWxUserService userSer;
	
	@RequestMapping("index")
	public String index(HttpServletRequest request,
			@CookieValue(value = "wx_gzh_token", required = true, defaultValue = "") String wx_gzh_token){
		String userIdStr = "";
		if (wx_gzh_token != "") {
			PageUserDto pageUser = userSer.getPageUserDto(wx_gzh_token);
			if (pageUser != null) {
				userIdStr = pageUser.getId();
			}
		}
		int userId=0;
		if(userIdStr!=null&&!userIdStr.isEmpty()){
			userId=Integer.parseInt(userIdStr);
		}
		String fm = request.getParameter("fm");
		String startDt= DateUtil.getDays("yyyy-MM-dd");
		Date dt=new Date();
		dt=DateUtil.AddDays(dt, 1);
		String endDt=DateUtil.date2Str(dt, "yyyy-MM-dd");
		WxPresent wxPres= wxpresentSer.getModelByGetTime(userId, startDt, endDt);
		boolean isget=false;
		if(wxPres!=null&&wxPres.getId()>0){
			isget=true;
		}
		request.setAttribute("isget", isget);
		request.setAttribute("userId", userId);
		request.setAttribute("fromurl", fm);
		return "wxPresent/index";
	}
	
	@RequestMapping("getCoin")
	@ResponseBody
	public void getCoin(HttpServletRequest request,HttpServletResponse response,
			@CookieValue(value = "wx_gzh_token", required = true, defaultValue = "") String wx_gzh_token){
		
		try {
			String userIdStr = "";
			JSONObject getObj = new JSONObject();
			if (wx_gzh_token != "") {
				PageUserDto pageUser = userSer.getPageUserDto(wx_gzh_token);
				if (pageUser != null) {
					userIdStr = pageUser.getId();
				}
			}
			int userId=0;
			if(userIdStr!=null&&!userIdStr.isEmpty()){
				userId=Integer.parseInt(userIdStr);
			}
			String startDt= DateUtil.getDays("yyyy-MM-dd");
			Date dt=new Date();
			dt=DateUtil.AddDays(dt, 1);
			String endDt=DateUtil.date2Str(dt, "yyyy-MM-dd");
			WxPresent wxPres= wxpresentSer.getModelByGetTime(userId, startDt, endDt);
			if(wxPres!=null&&wxPres.getId()>0){
				getObj.put("ok",true);
				getObj.put("msg", "今天已经领取,不能重复领取");
			}else{
				boolean res= wxpresentSer.SaveRecord(userId,15);
				getObj.put("ok",res);
				getObj.put("msg", "领取成功");
			}
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(getObj.toString());
		} catch (Exception e) {
			logger.error("领取小说币失败"+e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
