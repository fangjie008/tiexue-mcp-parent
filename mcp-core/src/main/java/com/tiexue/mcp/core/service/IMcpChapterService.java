package com.tiexue.mcp.core.service;

import com.tiexue.mcp.core.entity.McpChapter;

public interface IMcpChapterService {

    int deleteByPrimaryKey(Integer id);

    int insert(McpChapter record);

    int insertSelective(McpChapter record);

    McpChapter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(McpChapter record);

    int updateByPrimaryKeyWithBLOBs(McpChapter record);

    int updateByPrimaryKey(McpChapter record);
}