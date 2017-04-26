package com.tiexue.mcp.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tiexue.mcp.core.entity.McpBook;
import com.tiexue.mcp.core.mapper.McpBookMapper;
import com.tiexue.mcp.core.service.IMcpBookService;

/**
 * 作品管理
 * @author 
 *
 */
@Service("McpBookSer")
public class McpBookServiceImpl implements IMcpBookService {

	
	@Resource McpBookMapper mcpBookMapper;
	@Override
    public int deleteByPrimaryKey(Integer id){
    	return mcpBookMapper.deleteByPrimaryKey(id);
    }
	@Override
    public int insert(McpBook record){
    	return mcpBookMapper.insert(record);
    }
	@Override
    public int insertSelective(McpBook record){
    	return mcpBookMapper.insertSelective(record);
    }
	@Override
    public McpBook selectByPrimaryKey(Integer id){
    	return mcpBookMapper.selectByPrimaryKey(id);
    }
	@Override
    public int updateByPrimaryKeySelective(McpBook record){
    	return mcpBookMapper.updateByPrimaryKeySelective(record);
    }
	@Override
    public int updateByPrimaryKey(McpBook record){
    	return mcpBookMapper.updateByPrimaryKey(record);
    }
}