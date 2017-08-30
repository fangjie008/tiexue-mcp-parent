package com.tiexue.mcp.core.service;



import java.util.List;

import com.tiexue.mcp.core.entity.WxPlatformSign;

/**
 * 推广信息管理
 * @author zhangxiaowei
 *
 */
public interface IWxPlatformSignService {

    int deleteByPrimaryKey(Integer id);


    int insert(WxPlatformSign record);

    int insertSelective(WxPlatformSign record);


    WxPlatformSign selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WxPlatformSign record);


    int updateByPrimaryKey(WxPlatformSign record);
    
    

    WxPlatformSign getModelBySign(String sign);
    

    List<WxPlatformSign> getListByPage(String strWhere,int pageNo,int pageSize);
    
    
    int getCount(String strWhere);
}
