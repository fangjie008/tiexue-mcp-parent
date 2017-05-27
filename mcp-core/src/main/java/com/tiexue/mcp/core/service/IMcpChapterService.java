package com.tiexue.mcp.core.service;

import java.util.List;

import com.tiexue.mcp.core.entity.McpBook;
import com.tiexue.mcp.core.entity.McpChapter;

public interface IMcpChapterService {

    int deleteByPrimaryKey(Integer id);

    int insert(McpChapter record);

    int insertSelective(McpChapter record);

    McpChapter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(McpChapter record);

    int updateByPrimaryKeyWithBLOBs(McpChapter record);

    int updateByPrimaryKey(McpChapter record);
    
    List<McpChapter> selectList(Integer bookId,Integer pStart,Integer pSize);
    
    int getCount(String strWhere);
    
    /**
     * 根据合作者Id,合作方书籍Id,合作方章节Id获取记录是否存在
     * @param cpId
     * @param cpBId
     * @param cpCId
     * @return
     */
    McpChapter selectByCpBId(Integer cpId,String cpBId,String cpCId);
    /**
     * 新增采集的章节
     * @param record
     * @return
     */
    McpChapter taskInsert(McpChapter record);
    /**
     * 更新采集的章节
     * @param record
     * @return
     */
    int taskUpdate(McpChapter record);
}