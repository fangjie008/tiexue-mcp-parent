package com.tiexue.mcp.core.service;


import com.tiexue.mcp.core.entity.WeiXinUserInfo;
import com.tiexue.mcp.core.entity.WxUser;

import weixin.popular.bean.user.User;

public interface IWeiXinUserInfoService {

	    int deleteByPrimaryKey(String uuid);
	    int insert(WeiXinUserInfo record);
	    int insertSelective(WeiXinUserInfo record);
	    WeiXinUserInfo selectByPrimaryKey(String uuid);
	    int updateByPrimaryKeySelective(WeiXinUserInfo record);
	    int updateByPrimaryKeyWithBLOBs(WeiXinUserInfo record);
	    int updateByPrimaryKey(WeiXinUserInfo record);
	    
	    WeiXinUserInfo getModelByUnionId(String unionid);
	    
	    /**
	     * 保存微信公共号获取的用户
	     * @param user
	     * @return
	     */
	    int saveWerXinUser(User user,int openIdCategory);
	    
	    /**
	     * 获取推广带来的关注公共号用户
	     * @param sign
	     * @return
	     */
	    int getFollowCount(String sign);
}
