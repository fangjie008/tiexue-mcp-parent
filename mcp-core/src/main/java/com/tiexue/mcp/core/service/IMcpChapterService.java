package com.tiexue.mcp.core.service;

import java.util.Date;
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
    McpChapter selectByCpBId(String uniqueFlag);
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
    
    
    
    /**
     * 获取未上架的小说的章节(在插入数据时判断是否已经同步到线上)
     * 查找 AuditStatus(安审状态)=2(审核通过)的的章节
     * @return
     */
    List<McpChapter> getUnCommitChapter(Integer mcpBookId);
    

    /**
     * 获取已上架需要更新的章节
     * 查找最后更新时间>上架时间的的章节
     * @return
     */
    List<McpChapter> getNeedUpdateMcpChapter(Integer mcpBookId,Date updateTime);
}