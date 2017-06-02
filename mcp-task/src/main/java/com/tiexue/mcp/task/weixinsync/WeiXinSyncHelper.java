package com.tiexue.mcp.task.weixinsync;

import org.apache.log4j.Logger;

public class WeiXinSyncHelper {

	private static Logger logger=Logger.getLogger(WeiXinSyncHelper.class);
	//公共平台-开发-基本配置----订阅号
	private static String weiXinAppId="a";
	//
	private static String weiXinSecret="b";
	
	
	public void getUserInfo(){
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("info WeiXinSync Start!");
	}
	
	public void getAccessToken(){
		
	}

}
