package com.tiexue.mcp.core.service;

import java.util.Date;
import java.util.List;



import com.tiexue.mcp.core.entity.McpBook;
import com.tiexue.mcp.core.entity.WxBook;

public interface IMcpBookService {

    int deleteByPrimaryKey(Integer id);

    int insert(McpBook record);

    int insertSelective(McpBook record);

    McpBook selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(McpBook record);

    int updateByPrimaryKey(McpBook record);
    
    List<McpBook> getList(Integer cpId,Integer pStart,Integer pSize);
    
    int getCount(String strWhere);
    
    McpBook selectByCpBId(String uniqueFlag);
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
    int taskUpdate(McpBook record);
    
    /**
     * 更新采集状态为采集完成
     * @param Id
     * @param collectionStatus
     * @return
     */
    int updateCollectionStatus(Integer Id);
    
    /**
     * 获取未上架的小说
     * 查找 putAwayStatus(上架状态)=1(未上架)并且AuditStatus(安审状态)=2(审核通过)的小说
     * @return
     */
    List<McpBook> getUnCommitBook();
    

    /**
     * 获取已上架需要更新的小说
     * 查找 putAwayStatus(上架状态)=2(已上架)并且AuditStatus(安审状态)=2(审核通过)并且最后更新时间>上架时间的小说
     * @return
     */
    List<McpBook> getNeedUpdateMcpBook(Date PutawayTime);
    
    /**
     * 更新到wxBook
     * @param id
     * @param uniqueflag
     * @return
     */
    int updatePutAwayStatus(Integer mcpBookId,String uniqueflag);
    
   
}