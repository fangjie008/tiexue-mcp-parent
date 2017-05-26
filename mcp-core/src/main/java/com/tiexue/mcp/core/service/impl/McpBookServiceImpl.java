package com.tiexue.mcp.core.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.tiexue.mcp.core.entity.McpBook;
import com.tiexue.mcp.core.entity.McpConstants;
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
	
	/**
	 * 查询合作方其下所有书籍信息列表
	 * 目前暂时不分页
	 * */
	@Override
	public List<McpBook> getList(Integer cpId,Integer pStart,Integer pSize) {
		return mcpBookMapper.getList(cpId,pStart,pSize);
	}
	
	public int getCount(String strWhere){
		return mcpBookMapper.getCount(strWhere);
	}
	@Override
	public McpBook selectByCpBId(String cpBId) {
		return mcpBookMapper.selectByCpBId(cpBId);
	}
	@Override
	public McpBook taskInsert(McpBook record) {
		if(record==null)
			return null;
		int id= mcpBookMapper.insert(record);
		if(id>0){
			record.setId(id);
		}
		else{
			record.setId(0);
		}
		return record;
	}
	@Override
	public McpBook taskUpdate(McpBook record) {
		if(record==null)
			return record;
		int id= mcpBookMapper.updateByPrimaryKey(record);
		return record;
	}
	
}