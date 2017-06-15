package com.tiexue.mcp.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tiexue.mcp.core.entity.McpCommitRecord;
import com.tiexue.mcp.core.mapper.McpCommitRecordMapper;
import com.tiexue.mcp.core.service.IMcpCommitRecordService;

@Service("mcpCommitRecordServiceImpl")
public class McpCommitRecordServiceImpl implements IMcpCommitRecordService {

	@Resource 
	McpCommitRecordMapper mcpCommitRecordMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return mcpCommitRecordMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(McpCommitRecord record) {
		return mcpCommitRecordMapper.insert(record);
	}

	@Override
	public int insertSelective(McpCommitRecord record) {
		return mcpCommitRecordMapper.insertSelective(record);
	}

	@Override
	public McpCommitRecord selectByPrimaryKey(Integer id) {
		return mcpCommitRecordMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(McpCommitRecord record) {
		return mcpCommitRecordMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(McpCommitRecord record) {
		return mcpCommitRecordMapper.updateByPrimaryKey(record);
	}

	@Override
	public McpCommitRecord getNewest() {
		return mcpCommitRecordMapper.getNewest();
	}

}
