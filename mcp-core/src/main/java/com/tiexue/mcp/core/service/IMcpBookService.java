package com.tiexue.mcp.core.service;

import java.util.List;

import com.tiexue.mcp.core.entity.McpBook;

public interface IMcpBookService {

    int deleteByPrimaryKey(Integer id);

    int insert(McpBook record);

    int insertSelective(McpBook record);

    McpBook selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(McpBook record);

    int updateByPrimaryKey(McpBook record);
    
    List<McpBook> getList(Integer cpId);
    
}