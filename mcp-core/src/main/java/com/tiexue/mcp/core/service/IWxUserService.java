package com.tiexue.mcp.core.service;


import org.apache.ibatis.annotations.Param;

import com.tiexue.mcp.core.dto.PageUserDto;
import com.tiexue.mcp.core.entity.WxConsume;
import com.tiexue.mcp.core.entity.WxUser;

import weixin.popular.bean.sns.SnsToken;
import weixin.popular.bean.user.User;

public interface IWxUserService {

    int deleteByPrimaryKey(Integer id);
    
    int insert(WxUser record);

    int insertSelective(WxUser record);


    WxUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WxUser record);


    int updateByPrimaryKeyWithBLOBs(WxUser record);

    int updateByPrimaryKey(WxUser record);
    
    int updateCoin(Integer id,Integer coin);
    
    /**
     * 更新小说币 添加消费记录
     * @param id
     * @param coin
     * @param updatetime
     * @return
     */
    boolean updateCoin(WxUser record,WxConsume cons);
    
    /**
     * 根据用户微信openId获取用户信息
     * @param OpenId
     * @return
     */
    WxUser getModelByOpenId(String openId);
    
    /**
     * 根据用户微信unionId获取用户信息
     * @param unionId
     * @return
     */
    WxUser getModelByUnionId(String unionId);
    
    /**
     * 保存登录信息
     * @param user
     * @param wxSnsUser
     * @return
     */
    WxUser saveLoginMsg(SnsToken user,User wxSnsUser,String fm);
   
    /**
     * 保存登录信息(静默)
     * @param user
     * @param wxSnsUser
     * @return
     */
    WxUser saveLoginMsgQuiet(SnsToken user,String fm);
    
    /**
     * 生成登录后的cookie
     * @param wxUser
     * @return
     */
    String setLoginInCookie(WxUser wxUser);
    
    /**
     * 把cookie中用户登录信息转化成PageUserDto
     * @param wx_gzh_token
     * @return
     */
    PageUserDto getPageUserDto(String wx_gzh_token);
    
    /**
     * 保存当前推广的fm参数
     * @param id
     * @param pfcurrent
     * @return
     */
    int updatePfCurrent(@Param("id")Integer id,@Param("pfcurrent")String pfcurrent);
 
}
