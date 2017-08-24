package com.tiexue.mcp.web.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.tiexue.mcp.core.entity.WxConstants;
import com.tiexue.mcp.core.entity.WxUser;
import com.tiexue.mcp.core.service.IWxUserService;

import weixin.popular.api.SnsAPI;
import weixin.popular.bean.sns.SnsToken;
import weixin.popular.bean.user.User;

@Controller
@RequestMapping("/autologin")
public class WxAutoLoginController {
	private Logger logger = Logger.getLogger(WxAutoLoginController.class);
	@Resource
	IWxUserService userSer;

	/**
	 * 开始执行微信登录逻辑 OAuth2.0开始 页面跳转到
	 * open.weixin.qq.com/connect/oauth2/authorize(指定回调地址) 回调地址收到code -->
	 * 根据code换取access_token --> 根据access_token请求userinfo信息
	 * 请求到userinfo信息之后,将userinfo信息进行保存(数据库不存在openid插入)
	 * 将用户登录信息写入cookie及session,判断cookie中的_ref页面跳转回登录前网址,完成登录流程
	 */
	@RequestMapping("/logindo")
	public String wxLoginDo(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attr
			,@RequestHeader("User-Agent") String userAgent) {
		//非微信环境直接看
//		if(userAgent!=null&&!userAgent.isEmpty()&&!userAgent.toLowerCase().contains("micromessenger")){
//			return "redirect:" + ref;
//		}

		String oauthUrl = SnsAPI.connectOauth2Authorize(WxConstants.WxAppId, WxConstants.WxHideRedirectUrl, false,
				WxConstants.WxOauthState);
		logger.error("logindo start");
		return "redirect:" + oauthUrl;
	}

	/**
	 * 微信公众号授权回调页面,微信会返回code和state 拿到Code和state以后我们发起调用请求access_token请求
	 * 拿到access_token及openid后,我们发起请求, 请求微信的用户基础信息,包括昵称,性别等
	 * 请求到用户信息后保存用户信息,用户转到登录前页面
	 */
	@RequestMapping("wxoauthcallback")
	public String wxOAuthCallback(HttpServletRequest request, HttpServletResponse response,RedirectAttributes attr, 
			@CookieValue(value = "defaultbookrack", required = true, defaultValue = "") String rackCookie,
			@CookieValue(value ="wx_gzh_sign",required = true, defaultValue = "")String wx_gzh_sign) throws Exception {
		try {
			// 微信token
			SnsToken wxSnsToken = null;
			String code = request.getParameter("code");
			String state = request.getParameter("state");
		
			int userId=0;
			if (!state.equalsIgnoreCase(WxConstants.WxOauthState)) {
				logger.error("微信登录异常：");
				throw new Exception("state error");
			}
			// 获取access_token及openid等信息
			wxSnsToken = SnsAPI.oauth2AccessToken(WxConstants.WxAppId, WxConstants.WxAppSecret, code);
			// 根据access_token及openid等信息请求用户信息
			WxUser resUxUser= userSer.saveLoginMsgQuiet(wxSnsToken,wx_gzh_sign);
			if(resUxUser!=null&&resUxUser.getId()>0){
				userId=resUxUser.getId();
				String wx_gzh_token=userSer.setLoginInCookie(resUxUser);
				// todo:生成登录cookie写到客户端
				Cookie token_cookie = new Cookie("wx_gzh_token", wx_gzh_token); // 创建一个Cookie对象，并将用户名保存到Cookie对象中
				token_cookie.setMaxAge(5*365*24*60*60); // 设置Cookie的过期之前的时间，单位为秒
				token_cookie.setPath("/");
				response.addCookie(token_cookie); // 通过response的addCookie()方法将此Cookie对象
				
				logger.error("导量登录成功后保存的fm参数"+wx_gzh_sign);
			}else{
				logger.error("用户数据保存失败");
				return "redirect:login";
			}
			logger.error("login  success ");
		} catch (Exception e) {
			logger.error("登录报错："+e);
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return "redirect:/wxbook/list";
		}

		// 接下来跳转到一个专门处理登录后逻辑的页面
		return "redirect:wxloginhandle";
	}

	/*
	 * 专门用来处理登录后逻辑的页面
	 * 
	 */
	@RequestMapping("wxloginhandle")
	public String wxLoginHandle(HttpServletRequest request, HttpServletResponse response,RedirectAttributes attr,
			@CookieValue(value = "wx_gzh_token", required = true, defaultValue = "") String wx_gzh_token,
			@CookieValue(value = "_ref", required = true, defaultValue = "") String ref) {
		try {
			// todo:判断是否登录,理论上到这里都是登录后的
			// todo:跳转到登录前页面
			if (ref!=""&&!ref.isEmpty()) {
				if(ref.contains("wxUser/login")){
					return "redirect:/wxbook/list";
				}
				return "redirect:"+ref;
			}
			return "redirect:/wxbook/list";
		} catch (Exception e) {
			logger.error("登录报错：" + e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "redirect:/wxbook/list";
		}
		
	}

}
