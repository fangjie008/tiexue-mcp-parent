package com.tiexue.mcp.core.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tiexue.mcp.core.entity.McpBook;

public interface IMcpBookService {

    int deleteByPrimaryKey(Integer id);

    int insert(McpBook record);

    int insertSelective(McpBook record);

    McpBook selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(McpBook record);

    int updateByPrimaryKey(McpBook record);
    
    List<McpBook> getList(Integer cpId,Integer pStart,Integer pSize);
    
    int getCount(String strWhere);
    
    McpBook selectByCpBId(String cpBId);
    /**
     * 新增采集的书籍
     * @param record
     * @return
     */
    McpBook taskInsert(McpBook record);
    /**
     * 更新采集的书籍
     * @param record
     * @return
     */
	McpBook taskUpdate(McpBook record);
}