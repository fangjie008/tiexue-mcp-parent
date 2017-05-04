package com.tiexue.mcp.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tiexue.mcp.core.entity.McpBaseInfo;
import com.tiexue.mcp.core.mapper.McpBaseInfoMapper;
import com.tiexue.mcp.core.service.IMcpBaseInfoService;

/**
 * 基础资料
 * @author 
 *
 */
@Service("McpBaseInfoSer")
public class McpBaseInfoServiceImpl implements IMcpBaseInfoService {

   @Resource  McpBaseInfoMapper mcpBaseMapp;
	@Override
   public int deleteByPrimaryKey(Integer cpid){
	   return mcpBaseMapp.deleteByPrimaryKey(cpid);
   }

	@Override
    public int insert(McpBaseInfo record){
    	return mcpBaseMapp.insert(record);
    }
	@Override
    public int insertSelective(McpBaseInfo record){
    	return mcpBaseMapp.insertSelective(record);
    }

	@Override
    public McpBaseInfo selectByPrimaryKey(Integer cpid){
    	return mcpBaseMapp.selectByPrimaryKey(cpid);
    }
	@Override
    public int updateByPrimaryKeySelective(McpBaseInfo record){
    	return mcpBaseMapp.updateByPrimaryKeySelective(record);
    }
	@Override
    public int updateByPrimaryKey(McpBaseInfo record){
    	return mcpBaseMapp.updateByPrimaryKey(record);
    }
	
	@Override
    public List<McpBaseInfo> getList(String strWhere){
		return mcpBaseMapp.getList(strWhere);
	}
}