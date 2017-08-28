package com.tiexue.mcp.core.weixin;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Case;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.tiexue.mcp.core.entity.WxConstants;
import com.tiexue.mcp.core.service.IWeiXinUserInfoService;

import weixin.popular.api.UserAPI;
import weixin.popular.bean.user.FollowResult;
import weixin.popular.bean.user.FollowResult.Data;
import weixin.popular.bean.user.User;

/**
 * 获取关注公众号的用户信息
 * @author zhangxiaowei
 *
 */
@Service("syncWxUserInfo")
public class SyncWxUserInfo {
	private static Logger logger=Logger.getLogger(SyncWxUserInfo.class);
	@Resource 
	IWeiXinUserInfoService iWeiXinUserInfoService;
	/**
	 * 获取用户列表
	 * @param appId
	 * @param appSecret
	 * @param access_token
	 */
	public void syncWxUser(String appId,String appSecret,String access_token,String nextOpenId){
		int num=0;
		try {
			FollowResult followResult= UserAPI.userGet(access_token,nextOpenId);
			if(followResult!=null&&followResult.getData()!=null){
				if(followResult.isSuccess()){
					Data data=followResult.getData();
					if(data!=null&&data.getOpenid()!=null)
					for(String openId : data.getOpenid()) {
						Thread.sleep(5);
						User user=getWxUserInfo(access_token,openId);
						if(user!=null){
							 iWeiXinUserInfoService.saveWerXinUser(user,getOpenIdCategory(appId));
							 num++;
						}
					}
					logger.error(String.format("公众号appId:%s。同步%s条用户记录",appId,num));
					//递归获取
					if(followResult.getNext_openid()!=null&&!followResult.getNext_openid().isEmpty()){
						syncWxUser(appId, appSecret, access_token,followResult.getNext_openid());
					}
					
				}
				else{
					logger.error(String.format("获取数据错误信息：%s.错误码：%s",followResult.getErrmsg(),followResult.getErrcode()));
				}
			}
			else{
				if(followResult==null)
					logger.error("获取的followResult数据为空。");
				else
					logger.error(String.format("获取数据错误信息：%s.错误码：%s",followResult.getErrmsg(),followResult.getErrcode()));
			}
		} catch (InterruptedException e) {
			logger.error(String.format("同步公众号appId:%s。异常",appId)+e);
			e.printStackTrace();
		}
		
	}
	/**
	 * 获取用户详细信息
	 * @param access_token
	 * @param openId
	 */
	public User getWxUserInfo(String access_token,String openId){
		User user= UserAPI.userInfo(access_token,openId);
		return user;
	}
	
	/**
	 * 获取用过分类
	 * @param appId
	 * @return
	 */
	private int getOpenIdCategory(String appId){
		int category=0;
		switch (appId) {
		case WxConstants.WxAppId:
			category=1;
			break;
		case WcWxConstant.wxAppId:
			category=2;
			break;
		default:
			break;
		}
		return category;
	}

}
