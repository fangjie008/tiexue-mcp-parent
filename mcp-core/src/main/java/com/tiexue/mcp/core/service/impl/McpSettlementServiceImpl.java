package com.tiexue.mcp.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tiexue.mcp.core.entity.McpSettlement;
import com.tiexue.mcp.core.mapper.McpSettlementMapper;
import com.tiexue.mcp.core.service.IMcpSettlementService;

/**
 * 结算管理
 * @author 
 *
 */
@Service("McpSettlementSer")
public class McpSettlementServiceImpl implements IMcpSettlementService {

	@Resource McpSettlementMapper mcpSettMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return mcpSettMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(McpSettlement record) {
		return mcpSettMapper.insert(record);
	}

	@Override
	public int insertSelective(McpSettlement record) {
		return mcpSettMapper.insertSelective(record);
	}

	@Override
	public McpSettlement selectByPrimaryKey(Integer id) {
		return mcpSettMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(McpSettlement record) {
		return mcpSettMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(McpSettlement record) {
		return mcpSettMapper.updateByPrimaryKey(record);
	}

}