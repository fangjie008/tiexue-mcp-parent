package com.tiexue.mcp.web.listener;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.tiexue.mcp.core.entity.WxConstants;

import weixin.popular.support.TicketManager;
import weixin.popular.support.TokenManager;

public class WxAppInitListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		// WEB容器 关闭时调用
				TicketManager.destroyed();
				TicketManager.destroyed();
	}

	public void contextInitialized(ServletContextEvent arg0) {
		
		// WEB容器 初始化时调用
				// TicketManager 依赖 TokenManager，确保TokenManager.init 先被调用
				TokenManager.init(WxConstants.WxAppId, WxConstants.WxAppSecret);
				// 睡眠一定时间后启动
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				TicketManager.init(WxConstants.WxAppId);
	}
	
	
}
