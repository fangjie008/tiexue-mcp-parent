package com.tiexue.mcp.web.controller;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tiexue.mcp.core.dto.PageUserDto;
import com.tiexue.mcp.core.entity.WxUser;
import com.tiexue.mcp.core.service.IUserConsService;
import com.tiexue.mcp.core.service.IWxBookService;
import com.tiexue.mcp.core.service.IWxBookrackService;
import com.tiexue.mcp.core.service.IWxChapterService;
import com.tiexue.mcp.core.service.IWxChapterSubService;
import com.tiexue.mcp.core.service.IWxUserService;


@Controller
@RequestMapping("outshow")
public class WxOutShowController {
	private static Logger logger = Logger.getLogger(WxOutShowController.class);
	int pageSize = 20;
	
	@Resource
	IWxChapterSubService chapterSubSer;
	@Resource
	IWxChapterService chapterService;
	@Resource
	IWxBookService bookService;
	@Resource
	IUserConsService userConsService;
	@Resource
	IWxUserService userSer;
	@Resource
	IWxBookrackService bookrackService;

	@RequestMapping("/index")
	public String getContentUnlogin(HttpServletRequest request,HttpServletResponse response, RedirectAttributes attr,
			@CookieValue(value = "defaultbookrack", required = true, defaultValue = "") String rackCookie,
			@CookieValue(value = "wx_gzh_token", required = true, defaultValue = "") String wx_gzh_token
			,@CookieValue(value ="from_name",required = true, defaultValue = "")String from_name)
			throws UnsupportedEncodingException {
		String userIdStr = "";
		if (wx_gzh_token != "") {
			PageUserDto pageUser = userSer.getPageUserDto(wx_gzh_token);
			if (pageUser != null) {
				userIdStr = pageUser.getId();
			}
		}
		String bookIdStr = request.getParameter("bookId");
		String chapterIdStr = request.getParameter("chapterId");
		String fm = request.getParameter("fm");
		String autofurl = "/wxChapterSub/show?bookId=" + bookIdStr + "&chapterId=" + chapterIdStr + "&fm=" + fm;
		
		Cookie token_cookie = new Cookie("_ref", autofurl); // 创建一个Cookie对象，并将用户名保存到Cookie对象中
		token_cookie.setMaxAge(2*60); // 设置Cookie的过期之前的时间，单位为秒
		token_cookie.setPath("/");
		response.addCookie(token_cookie); // 通过response的addCookie()方法将此Cookie对象
		int userId = 0;
		if (userIdStr != null && !userIdStr.isEmpty()) {
			userId = Integer.parseInt(userIdStr);
		}
		if(userId>=0){
			WxUser userModel = userSer.selectByPrimaryKey(userId);
			if(userModel!=null){
				return "redirect:"+autofurl;
			}
		}
		
		logger.error("redirect:/autologin/logindo");
		return "redirect:/autologin/logindo";
		

	}
	


	




}
