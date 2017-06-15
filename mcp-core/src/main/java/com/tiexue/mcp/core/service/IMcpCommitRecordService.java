package com.tiexue.mcp.core.service;



import com.tiexue.mcp.core.entity.McpCommitRecord;

public interface IMcpCommitRecordService {

    int deleteByPrimaryKey(Integer id);

    int insert(McpCommitRecord record);

    int insertSelective(McpCommitRecord record);


    McpCommitRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(McpCommitRecord record);


    int updateByPrimaryKey(McpCommitRecord record);
    
    

    McpCommitRecord getNewest();
}
