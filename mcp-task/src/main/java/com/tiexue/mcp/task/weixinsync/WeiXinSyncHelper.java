package com.tiexue.mcp.task.weixinsync;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.tiexue.mcp.core.entity.WxConstants;
import com.tiexue.mcp.core.weixin.SyncWxUserInfo;
import com.tiexue.mcp.core.weixin.WcWxConstant;

import weixin.popular.support.TokenManager;

public class WeiXinSyncHelper {

	private static Logger logger=Logger.getLogger(WeiXinSyncHelper.class);
	@Resource 
	SyncWxUserInfo syncWxUserInfo;
	public void getUserInfo(){
		try {
			logger.info(" WeiXinSync start: "+new Date()+"sleep 30 second. ");
			//初始化获取微信公共号的accesstoken
			WxAppInit.getInstance();
			Thread.sleep(30000);
			//同步五彩读书网的用户
			String wcduwToken= TokenManager.getToken(WcWxConstant.wxAppId);
			if(wcduwToken!=null&&!wcduwToken.isEmpty()){
				logger.error("五彩读书网用户开始更新.appId"+WcWxConstant.wxAppId);
				syncWxUserInfo.syncWxUser(WcWxConstant.wxAppId, WcWxConstant.wxAppSecret,wcduwToken,"");
				logger.error("五彩读书网用户更新成功");
			}else {
				logger.error("没有获取到五彩读书网的accessToken");
			}
			Thread.sleep(10000);
			//同步五彩读书城的用户
			String wcducToken= TokenManager.getToken(WxConstants.WxAppId);
			if(wcducToken!=null&&!wcducToken.isEmpty()){
				logger.error("五彩读书城用户开始更新。AppId:"+WxConstants.WxAppId);
				syncWxUserInfo.syncWxUser(WxConstants.WxAppId, WxConstants.WxAppSecret,wcducToken,"");
				logger.error("五彩读书城用户更新成功");
			}else {
				logger.error("没有获取到五彩读书城的accessToken");
			}
			logger.info(" WeiXinSync end!");
			
		} catch (Exception e) {
			logger.info(" getUserInfo exception: "+e);
			e.printStackTrace();
		}
		
	}
	
	public void getAccessToken(){
		
	}

}
