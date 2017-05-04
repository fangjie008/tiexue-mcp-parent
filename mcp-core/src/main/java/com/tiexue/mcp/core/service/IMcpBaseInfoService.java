package com.tiexue.mcp.core.service;

import java.util.List;

import com.tiexue.mcp.core.entity.McpBaseInfo;

public interface IMcpBaseInfoService {

    int deleteByPrimaryKey(Integer cpid);


    int insert(McpBaseInfo record);

    int insertSelective(McpBaseInfo record);


    McpBaseInfo selectByPrimaryKey(Integer cpid);

    int updateByPrimaryKeySelective(McpBaseInfo record);

    int updateByPrimaryKey(McpBaseInfo record);
    
    List<McpBaseInfo> getList(String strWhere);
}