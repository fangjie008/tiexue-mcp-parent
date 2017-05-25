package com.tiexue.mcp.manage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.tiexue.mcp.base.util.CyptoUtils;
import com.tiexue.mcp.base.util.Md5Utils;
import com.tiexue.mcp.core.entity.McpBaseInfo;
import com.tiexue.mcp.core.entity.McpConstants;
import com.tiexue.mcp.core.service.IMcpBaseInfoService;
import com.tiexue.mcp.core.shiro.PasswordHelper;

@Controller
@RequestMapping("/mcphome")
public class McpHomeController {

	//日志
	private static Logger logger=Logger.getLogger(McpHomeController.class); 
	@Resource 
	IMcpBaseInfoService mcpBaseInfoSer;
	
	@Autowired
    private PasswordHelper passwordHelper;
	
	
	/**
	 * 登录后入口
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/login")
	@ResponseBody
	public String index(HttpServletRequest request,HttpServletResponse response) {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		JSONObject jObject=new JSONObject();
		try {
			logger.error("userName is:"+ userName + ";password is:" + password);  
			String passwordMd5= Md5Utils.ToBit32(password,McpConstants.Mcp_Md5_Key).toLowerCase();
			McpBaseInfo mcpBaseInfo= mcpBaseInfoSer.getModelByName(userName);
			if(mcpBaseInfo==null||mcpBaseInfo.getCpid()<=0){
				jObject.put("state", "error");
				jObject.put("msg", "登录名不存在");
			}
			else if(mcpBaseInfo.getPassword().toLowerCase().equals(passwordMd5)){
				jObject.put("state", "ok");
				jObject.put("msg", "登录成功");
				HttpSession session=request.getSession();
				session.setAttribute("userId", mcpBaseInfo.getCpid().toString());
				//以秒为单位，即在没有活动30分钟后，session将失效
				session.setMaxInactiveInterval(60*60);
			}
			else{
				jObject.put("state", "error");
				jObject.put("msg", "密码错误");
			}
			
		} catch (Exception e) {
			logger.error("login error"+e.getMessage());
			jObject.put("state", "error");
			jObject.put("msg", "登录报错");
		}
		return jObject.toString();
	}
	
	
	
	/**
	 * 主页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresUser
	@RequestMapping("/homepage")
	public String homePage(HttpServletRequest request,HttpServletResponse response){
		HttpSession session= request.getSession();
		String userIdStr= (String)session.getAttribute("userId");
		int userId=0;
		if(userIdStr!=null&&!userIdStr.isEmpty()){
			userId=Integer.parseInt(userIdStr);
			McpBaseInfo baseInfo= mcpBaseInfoSer.selectByPrimaryKey(userId);
			request.setAttribute("baseInfo", baseInfo);
		}
		return "/mcpHome/homePage";
	}
	
	
	
	/**
	 * 欢迎页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresAuthentication
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
	@RequestMapping("edit.do")
	public String loginEdit(HttpServletRequest request,HttpServletResponse response){
		HttpSession session= request.getSession();
		String userIdStr= (String)session.getAttribute("userId");
		int userId=0;
		if(userIdStr!=null&&!userIdStr.isEmpty()){
			userId=Integer.parseInt(userIdStr);
			McpBaseInfo baseInfo= mcpBaseInfoSer.selectByPrimaryKey(userId);
			request.setAttribute("baseInfo", baseInfo);
		}
		return "mcpHome/edit";
	}
	
	/**
	 * 保存修改的密码
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/savepassword.do",method=RequestMethod.POST)
    @ResponseBody
	public String savePassword(HttpServletRequest request,HttpServletResponse response) throws Exception{
		JSONObject jObject=new JSONObject();
		String password=request.getParameter("password");
		String cpidStr=request.getParameter("cpid");
		int cpid=0;
		if(password!=null&&cpidStr!=null){
			if(!cpidStr.isEmpty()){
				cpid=Integer.parseInt(cpidStr);
			}
			//password= Md5Utils.ToBit32(password,McpConstants.Mcp_Md5_Key);
			//加密方法统一
			password=passwordHelper.encryptPassword(password);
			int resultNum= mcpBaseInfoSer.updatePassword(cpid,password);
			if(resultNum>0){
				jObject.put("ok", true);
				jObject.put("msg", "保存成功");
			}
			else{
				jObject.put("ok", false);
				jObject.put("msg","保存失败");
			}
		}
		return jObject.toString();
	}
	/**
	 * 验证密码是否正确
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("check")
	@ResponseBody
	public String checkPassword(HttpServletRequest request,HttpServletResponse response) throws Exception{
		JSONObject jObject=new JSONObject();
		String oldpassword=request.getParameter("oldpassword");
		String md5password=request.getParameter("md5password");
		if(oldpassword!=null&&md5password!=null){
			oldpassword= Md5Utils.ToBit32(oldpassword,McpConstants.Mcp_Md5_Key).toLowerCase();
			if(oldpassword.equals(md5password.toLowerCase())){
				jObject.put("ok", true);
			}
			else{
				jObject.put("ok", false);
			}
		}
		return jObject.toString();
	}
	
}
