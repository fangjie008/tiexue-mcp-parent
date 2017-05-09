package com.tiexue.mcp.core.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tiexue.mcp.core.entity.McpBaseInfo;

public interface IMcpBaseInfoService {

    int deleteByPrimaryKey(Integer cpid);


    int insert(McpBaseInfo record);

    int insertSelective(McpBaseInfo record);


    McpBaseInfo selectByPrimaryKey(Integer cpid);

    int updateByPrimaryKeySelective(McpBaseInfo record);

    int updateByPrimaryKey(McpBaseInfo record);
    
    List<McpBaseInfo> getList(String strWhere,Integer pStart,Integer pSize);
    
    int getCount(String strWhere);
    
    McpBaseInfo getModelByName(String name);
    
    McpBaseInfo checkModelByName(String name,int cpid);
    
    
    int updatePassword(int cpid,String password);
}