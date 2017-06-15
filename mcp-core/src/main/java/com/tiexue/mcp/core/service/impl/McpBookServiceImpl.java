package com.tiexue.mcp.core.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.tiexue.mcp.core.entity.McpBook;
import com.tiexue.mcp.core.entity.McpConstants;
import com.tiexue.mcp.core.entity.WxBook;
import com.tiexue.mcp.core.mapper.McpBookMapper;
import com.tiexue.mcp.core.service.IMcpBookService;


/**
 * 作品管理
 * @author 
 *
 */
@Service("McpBookSer")
public class McpBookServiceImpl implements IMcpBookService {

	// 日志
	private Logger logger = Logger.getLogger(McpBookServiceImpl.class);
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
	public McpBook selectByCpBId(String uniqueFlag) {
		return mcpBookMapper.selectByCpBId(uniqueFlag);
	}
	@Override
	public McpBook taskInsert(McpBook record) {
		if(record==null)
			return null;
		record.setCollectionstatus(McpConstants.Book_OnCollection);
		int id= mcpBookMapper.insert(record);
		if(record.getId()==null){
    		logger.error("保存书籍信息后，没有返回保存的Id");
    		record.setId(0);
    	}
		return record;
	}
	@Override
	public int taskUpdate(McpBook record) {
		if(record==null)
			return 0;
		record.setCollectionstatus(McpConstants.Book_OnCollection);
		int id= mcpBookMapper.updateByPrimaryKey(record);
		return id;
	}
	/**
	 * 更新采集状态为采集完成
	 */
	@Override
	public int updateCollectionStatus(Integer Id) {
		return mcpBookMapper.updateCollectionStatus(Id, McpConstants.Book_FinishCollection);
	}
	@Override
	public List<McpBook> getUnCommitBook() {
		return mcpBookMapper.getUnCommitBook();
	}
	@Override
	public List<McpBook> getNeedUpdateMcpBook(Date PutawayTime) {
		return mcpBookMapper.getNeedUpdateMcpBook(PutawayTime);
	}
	@Override
	public int updatePutAwayStatus(Integer mcpBookId, String uniqueflag) {
		return mcpBookMapper.updatePutAwayStatus(mcpBookId, uniqueflag);
	}

	
	
	
}