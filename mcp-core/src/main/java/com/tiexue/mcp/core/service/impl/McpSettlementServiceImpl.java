package com.tiexue.mcp.core.service.impl;

import java.util.Date;


import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.tiexue.mcp.base.util.DateUtil;
import com.tiexue.mcp.core.dto.McpSettlementDto;
import com.tiexue.mcp.core.entity.McpConstants;
import com.tiexue.mcp.core.entity.McpSettlement;
import com.tiexue.mcp.core.mapper.McpSettlementMapper;
import com.tiexue.mcp.core.service.IMcpSettlementService;
import com.tiexue.mcp.core.service.IWxBookService;
import com.tiexue.mcp.core.service.IWxCommentService;
import com.tiexue.mcp.core.service.IWxConsumeService;

/**
 * 结算管理
 * @author 
 *
 */
@Service("McpSettlementSer")
public class McpSettlementServiceImpl implements IMcpSettlementService {

	private static Logger logger=Logger.getLogger(McpSettlementServiceImpl.class);
	@Resource 
	McpSettlementMapper mcpSettMapper;
	@Resource 
	IWxBookService iWxBookService;
	@Resource 
	IWxConsumeService iWxConsumeService;
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

	@Override
	public McpSettlementDto getData(int cpId, String monthly) {
		McpSettlement mcpSettlement=mcpSettMapper.getData(cpId, monthly);
		if(mcpSettlement==null){
			Date startTime=DateUtil.getAnyMonth(monthly, "yyyyMM", 0);
			Date endTime=DateUtil.getAnyMonth(monthly, "yyyyMM", 1);
			int amount= iWxConsumeService.getCostCoinByCpId(cpId, startTime, endTime);
			mcpSettlement=new McpSettlement();
			mcpSettlement.setConsume(amount);
			mcpSettlement.setCpid(cpId);
			mcpSettlement.setCreatetime(new Date());
			int divideconsume=0;
			if(amount>0){
				divideconsume=amount/2;
			}
			mcpSettlement.setDivideconsume(divideconsume);
			mcpSettlement.setMonth(monthly);
			mcpSettlement.setSettlementstatus(McpConstants.SettlementStatus_Un);
			mcpSettlement.setUpdatetime(new Date());
			int res=mcpSettMapper.insert(mcpSettlement);
			if(res>0){
				logger.info("新增结算数据。结算月份："+monthly);
			}
		}
		McpSettlementDto tempModel=new McpSettlementDto();
		
		tempModel.setConsume((float)(mcpSettlement.getConsume()*0.01));
		tempModel.setCpId(mcpSettlement.getCpid());
		tempModel.setDivideconsume((float)(mcpSettlement.getDivideconsume()*0.01));
		tempModel.setMonthly(monthly);
		tempModel.setSettlementstatus(McpConstants.SettlementStatus.get(mcpSettlement.getSettlementstatus()));
		return tempModel;
	}
	
	

}