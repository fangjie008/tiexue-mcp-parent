package com.tiexue.mcp.core.service;

import com.tiexue.mcp.core.dto.McpSettlementDto;
import com.tiexue.mcp.core.entity.McpSettlement;

public interface IMcpSettlementService {

    int deleteByPrimaryKey(Integer id);

    int insert(McpSettlement record);

    int insertSelective(McpSettlement record);

    McpSettlement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(McpSettlement record);

    int updateByPrimaryKey(McpSettlement record);
    
	/**
  	 * 获取结算数据
  	 * @param cpId
  	 * @param monthly
  	 * @return
  	 */
    McpSettlementDto getData(int cpId,String monthly);
}