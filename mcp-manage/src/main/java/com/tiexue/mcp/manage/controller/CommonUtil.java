package com.tiexue.mcp.manage.controller;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.tiexue.mcp.core.dto.McpShiroSubject;

public class CommonUtil {

	//日志
	private static Logger logger=Logger.getLogger(McpSettlementController.class);
	/**
	 * 如果登录用户是合作者返回合作者的Id(CPId)
	 * @return
	 */
	public static Integer getCpId(){
		Integer cpId = 0;
		try {
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			McpShiroSubject subject = (McpShiroSubject)session.getAttribute("user");
			if(subject==null){
				return 0;
			}
			
			if (subject.getMcpUserType() == 2) { //cp用户
				cpId = subject.getMcpBaseInfo().getCpid();
			}
		} catch (Exception e) {
			logger.debug("获取登录人信息出错；出错信息："+e.getMessage());
			return cpId;
		}
		
		return cpId;
	}
	
	/**
	 * 如果登录用户是合作者返回合作者的Id(CPId)
	 * @return
	 */
	public static McpShiroSubject getMcpShiroSubject(){
		Integer cpId = 0;
		try {
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			McpShiroSubject subject = (McpShiroSubject)session.getAttribute("user");
			if(subject==null){
				return null;
			}
			return subject;
		} catch (Exception e) {
			logger.debug("获取登录人信息出错；出错信息："+e.getMessage());
			return null;
		}
		
		
	}
	
}
