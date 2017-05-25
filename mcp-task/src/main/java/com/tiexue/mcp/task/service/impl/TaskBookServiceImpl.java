package com.tiexue.mcp.task.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import com.tiexue.mcp.core.entity.McpBook;
import com.tiexue.mcp.core.entity.McpConstants;
import com.tiexue.mcp.core.service.IMcpBookService;
import com.tiexue.mcp.task.entity.TaskBook;
import com.tiexue.mcp.task.service.ITaskBookService;

public class TaskBookServiceImpl implements ITaskBookService {

	@Resource
	IMcpBookService mcpBookService;
	@Override
	public McpBook insert(TaskBook record) {
		if(record==null)
			return null;
		McpBook mcpBook=new McpBook();
		//新增的书审核状态默认为未审核
		mcpBook.setAuditstatus(McpConstants.AuditStatus_UnAudit);
		//新增的书上架状态默认为未上架
		mcpBook.setPutawaystatus(McpConstants.PutAwayStatus_UnPut);
		mcpBook.setAuditinfo("");
		mcpBook.setActors(record.getAuthor()==null?"":record.getAuthor());
		mcpBook.setBookstatus(record.getBookstatus()==null?0:record.getBookstatus());
		mcpBook.setChanneltype(record.getChanneltype()==null?0:record.getChanneltype());
		mcpBook.setChaptercount(record.getChaptercount()==null?0:record.getChaptercount());
		mcpBook.setChargemode(record.getChargemode()==null?0:record.getChargemode());
		mcpBook.setClassify(record.getClassify()==null?0:record.getClassify());
		mcpBook.setCoverimg(record.getCoverimg()==null?"":record.getCoverimg());
		mcpBook.setCpbid(record.getCpbid()==null?"":record.getCpbid());
		mcpBook.setCpid(record.getCpid()==null?0:record.getCpid());
		mcpBook.setCpname(record.getName()==null?"":record.getName());
		mcpBook.setCreatetime(record.getCreatetime()==null?new Date():record.getCreatetime());
		mcpBook.setFeechapter(record.getFeechapter()==null?0:record.getFeechapter());
		mcpBook.setIntro(record.getIntro()==null?"":record.getIntro());
		mcpBook.setKeywords(record.getKeywords()==null?"":record.getKeywords());
		
		return null;
	}

	@Override
	public McpBook selectByCpBId(String cpBid) {
		return mcpBookService.selectByCpBId(cpBid);
	}


	@Override
	public int update(McpBook oldRecord, TaskBook taskBook) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
