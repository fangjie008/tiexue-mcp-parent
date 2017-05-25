package com.tiexue.mcp.task.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tiexue.mcp.core.entity.McpBook;
import com.tiexue.mcp.core.entity.McpConstants;
import com.tiexue.mcp.core.service.IMcpBookService;
import com.tiexue.mcp.task.entity.TaskBook;
import com.tiexue.mcp.task.service.ITaskBookService;

@Service("TaskBookService")
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
		mcpBook.setCpname(record.getCpname()==null?"":record.getCpname());
		mcpBook.setCreatetime(record.getCreatetime()==null?new Date():record.getCreatetime());
		mcpBook.setFeechapter(record.getFeechapter()==null?0:record.getFeechapter());
		mcpBook.setIntro(record.getIntro()==null?"":record.getIntro());
		mcpBook.setKeywords(record.getKeywords()==null?"":record.getKeywords());
		mcpBook.setName(record.getName()==null?"":record.getName());
		mcpBook.setPrice(record.getPrice()==null?0:record.getPrice());
		mcpBook.setPublishtime(record.getCreatetime()==null?new Date():record.getCreatetime());
		//新增的书上架状态默认为未上架
		mcpBook.setPutawaystatus(McpConstants.PutAwayStatus_UnPut);
		mcpBook.setPutawaytime(new Date());
		mcpBook.setSubhead(record.getSubhead()==null?"":record.getSubhead());
		mcpBook.setTags(record.getTags()==null?"":record.getTags());
		mcpBook.setUpdatetime(record.getUpdatetime()==null?new Date():record.getUpdatetime());
		mcpBook.setWords(record.getWords()==null?0:record.getWords());
		int id= mcpBookService.insert(mcpBook);
		if(id>0){
			mcpBook.setId(id);
		}
		else{
			mcpBook.setId(0);
		}
		return mcpBook;
	}

	@Override
	public McpBook selectByCpBId(String cpBid) {
		return mcpBookService.selectByCpBId(cpBid);
	}


	@Override
	public McpBook update(McpBook oldRecord, TaskBook taskBook) {
		if(taskBook==null)
			return oldRecord;
		McpBook mcpBook=new McpBook();
		if(taskBook.getAuthor()!=null)
			mcpBook.setActors(taskBook.getAuthor());
		if(taskBook.getBookstatus()!=null)
			mcpBook.setBookstatus(taskBook.getBookstatus());
		if(taskBook.getChanneltype()!=null)
			mcpBook.setChanneltype(taskBook.getChanneltype());
		if(taskBook.getChaptercount()!=null)
			mcpBook.setChaptercount(taskBook.getChaptercount());
		if(taskBook.getChargemode()!=null)
			mcpBook.setChargemode(taskBook.getChargemode());
		if(taskBook.getClassify()!=null)
			mcpBook.setClassify(taskBook.getClassify());
		if(taskBook.getCoverimg()!=null)
			mcpBook.setCoverimg(taskBook.getCoverimg());
		if(taskBook.getFeechapter()!=null)
			mcpBook.setFeechapter(taskBook.getFeechapter());
		if(taskBook.getIntro()!=null)
			mcpBook.setIntro(taskBook.getIntro());
		if(taskBook.getKeywords()!=null)
		mcpBook.setKeywords(taskBook.getKeywords());
		if(taskBook.getPrice()!=null)
			mcpBook.setPrice(taskBook.getPrice());
		if(taskBook.getSubhead()!=null)
			mcpBook.setSubhead(taskBook.getSubhead());
		if(taskBook.getTags()!=null)
			mcpBook.setTags(taskBook.getTags());
		if(taskBook.getUpdatetime()!=null)
			mcpBook.setUpdatetime(taskBook.getUpdatetime());
		if(taskBook.getWords()!=null)
			mcpBook.setWords(taskBook.getWords());
		int id= mcpBookService.updateByPrimaryKey(mcpBook);
		return mcpBook;
	}

	

}
