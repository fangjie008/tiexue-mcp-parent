package com.tiexue.mcp.task.util;

import java.util.Date;

import com.tiexue.mcp.core.entity.McpBook;
import com.tiexue.mcp.core.entity.McpConstants;
import com.tiexue.mcp.task.entity.TaskBook;

public class ConvertBook {

	/**
	 * 填充采集的书籍信息，合并两次采集的书籍信息
	 * 
	 * @param oldBook
	 * @param newBook
	 * @return
	 */
	public static TaskBook fillTaskBook(TaskBook oldBook, TaskBook newBook) {
		if (newBook == null)
			return oldBook;
		if (oldBook == null)
			return newBook;
		if (newBook.getChaptersurl() != null)
			oldBook.setChaptersurl(newBook.getChaptersurl());
		if (newBook.getActors() != null)
			oldBook.setActors(newBook.getActors());
		if (newBook.getAuthor() != null)
			oldBook.setAuthor(newBook.getAuthor());
		if (newBook.getBookstatus() != null)
			oldBook.setBookstatus(newBook.getBookstatus());
		if (newBook.getChanneltype() != null)
			oldBook.setChanneltype(newBook.getChanneltype());
		if (newBook.getChaptercount() != null)
			oldBook.setChaptercount(newBook.getChaptercount());
		if (newBook.getChargemode() != null)
			oldBook.setChargemode(newBook.getChargemode());
		if (newBook.getClassify() != null)
			oldBook.setClassify(newBook.getClassify());
		if (newBook.getCoverimg() != null)
			oldBook.setCoverimg(newBook.getCoverimg());
		if (newBook.getCpbid() != null)
			oldBook.setCpbid(newBook.getCpbid());
		if (newBook.getCpid() != null)
			oldBook.setCpid(newBook.getCpid());
		if (newBook.getCpname() != null)
			oldBook.setCpname(newBook.getCpname());
		if (newBook.getCreatetime() != null)
			oldBook.setCreatetime(newBook.getCreatetime());
		if (newBook.getFeechapter() != null)
			oldBook.setFeechapter(newBook.getFeechapter());
		if (newBook.getIntro() != null)
			oldBook.setIntro(newBook.getIntro());
		if (newBook.getKeywords() != null)
			oldBook.setKeywords(newBook.getKeywords());
		if (newBook.getName() != null)
			oldBook.setName(newBook.getName());
		if (newBook.getPrice() != null)
			oldBook.setPrice(newBook.getPrice());
		if (newBook.getSubhead() != null)
			oldBook.setSubhead(newBook.getSubhead());
		if (newBook.getTags() != null)
			oldBook.setTags(newBook.getTags());
		if (newBook.getUpdatetime() != null)
			oldBook.setUpdatetime(newBook.getUpdatetime());
		if (newBook.getWords() != null)
			oldBook.setWords(newBook.getWords());
		// 不保存emoji图片
		if (oldBook.getActors() != null)
			oldBook.setActors(oldBook.getActors().replaceAll("[\\x{10000}-\\x{10FFFF}]", "").replaceAll("[\\x{00}-\\x{FF}]", ""));
		if (oldBook.getAuthor() != null)
			oldBook.setAuthor(oldBook.getAuthor().replaceAll("[\\x{10000}-\\x{10FFFF}]", "").replaceAll("[\\x{00}-\\x{FF}]", ""));
		if (oldBook.getIntro() != null)
			oldBook.setIntro(oldBook.getIntro().replaceAll("[\\x{10000}-\\x{10FFFF}]", "").replaceAll("[\\x{00}-\\x{FF}]", ""));
		if (oldBook.getName() != null)
			oldBook.setName(oldBook.getName().replaceAll("[\\x{10000}-\\x{10FFFF}]", "").replaceAll("[\\x{00}-\\x{FF}]", ""));
		if (oldBook.getCpname() != null)
			oldBook.setCpname(oldBook.getCpname().replaceAll("[\\x{10000}-\\x{10FFFF}]", "").replaceAll("[\\x{00}-\\x{FF}]", ""));
		return oldBook;
	}

	/**
	 * 把采集的需要新增的书籍信息转化成McpBook对象
	 * 
	 * @param record
	 * @return
	 */
	public static McpBook toMcpBookDaoForInsert(TaskBook record) {
		if (record == null)
			return null;
		McpBook mcpBook = new McpBook();
		// 新增的书审核状态默认为未审核
		mcpBook.setAuditstatus(McpConstants.AuditStatus_UnAudit);
		mcpBook.setAuditinfo("");
		mcpBook.setAuthor(record.getAuthor() == null ? "" : record.getAuthor());
		mcpBook.setActors(record.getActors() == null ? "" : record.getActors());
		mcpBook.setBookstatus(record.getBookstatus() == null ? 0 : record.getBookstatus());
		mcpBook.setChanneltype(record.getChanneltype() == null ? 0 : record.getChanneltype());
		mcpBook.setChaptercount(record.getChaptercount() == null ? 0 : record.getChaptercount());
		mcpBook.setChargemode(record.getChargemode() == null ? 0 : record.getChargemode());
		mcpBook.setClassify(record.getClassify() == null ? 0 : record.getClassify());
		mcpBook.setCoverimg(record.getCoverimg() == null ? "" : record.getCoverimg());
		mcpBook.setCpbid(record.getCpbid() == null ? "" : record.getCpbid());
		mcpBook.setCpid(record.getCpid() == null ? 0 : record.getCpid());
		mcpBook.setCpname(record.getCpname() == null ? "" : record.getCpname());
		mcpBook.setCreatetime(record.getCreatetime() == null ? new Date(): record.getCreatetime());
		mcpBook.setFeechapter(record.getFeechapter() == null ? 0 : record.getFeechapter());
		mcpBook.setIntro(record.getIntro() == null ? "" : record.getIntro());
		mcpBook.setKeywords(record.getKeywords() == null ? "" : record.getKeywords());
		mcpBook.setName(record.getName() == null ? "" : record.getName());
		mcpBook.setPrice(record.getPrice() == null ? 0 : record.getPrice());
		mcpBook.setPublishtime(record.getCreatetime() == null ? new Date() : record.getCreatetime());
		// 新增的书上架状态默认为未上架
		mcpBook.setPutawaystatus(McpConstants.PutAwayStatus_UnPut);
		mcpBook.setPutawaytime(new Date());
		mcpBook.setSubhead(record.getSubhead() == null ? "" : record.getSubhead());
		mcpBook.setTags(record.getTags() == null ? "" : record.getTags());
		mcpBook.setUpdatetime(record.getUpdatetime() == null ? new Date() : record.getUpdatetime());
		mcpBook.setWords(record.getWords() == null ? 0 : record.getWords());
		// 不保存emoji图片
		if (mcpBook.getActors() != null)
			mcpBook.setActors(mcpBook.getActors().replaceAll("[\\x{10000}-\\x{10FFFF}]", "").replaceAll("[\\x{00}-\\x{FF}]", ""));
		if (mcpBook.getAuthor() != null)
			mcpBook.setAuthor(mcpBook.getAuthor().replaceAll("[\\x{10000}-\\x{10FFFF}]", "").replaceAll("[\\x{00}-\\x{FF}]", ""));
		if (mcpBook.getIntro() != null)
			mcpBook.setIntro(mcpBook.getIntro().replaceAll("[\\x{10000}-\\x{10FFFF}]", "").replaceAll("[\\x{00}-\\x{FF}]", ""));
		if (mcpBook.getName() != null)
			mcpBook.setName(mcpBook.getName().replaceAll("[\\x{10000}-\\x{10FFFF}]", "").replaceAll("[\\x{00}-\\x{FF}]", ""));
		if (mcpBook.getCpname() != null)
			mcpBook.setCpname(mcpBook.getCpname().replaceAll("[\\x{10000}-\\x{10FFFF}]", "").replaceAll("[\\x{00}-\\x{FF}]", ""));
		return mcpBook;
	}

	/**
	 * 把采集的需要更新的书籍信息转化成McpBook对象
	 * 
	 * @param oldRecord
	 * @param taskBook
	 * @return
	 */
	public static McpBook toMcpBookDaoForUpdate(McpBook mcpBook, TaskBook taskBook) {
		if (taskBook == null)
			return mcpBook;
		if (taskBook.getAuthor() != null)
			mcpBook.setAuthor(taskBook.getAuthor());
		if (taskBook.getActors() != null)
			mcpBook.setActors(taskBook.getActors());
		if (taskBook.getBookstatus() != null)
			mcpBook.setBookstatus(taskBook.getBookstatus());
		if (taskBook.getChanneltype() != null)
			mcpBook.setChanneltype(taskBook.getChanneltype());
		if (taskBook.getChaptercount() != null)
			mcpBook.setChaptercount(taskBook.getChaptercount());
		if (taskBook.getChargemode() != null)
			mcpBook.setChargemode(taskBook.getChargemode());
		if (taskBook.getClassify() != null)
			mcpBook.setClassify(taskBook.getClassify());
		if (taskBook.getCoverimg() != null)
			mcpBook.setCoverimg(taskBook.getCoverimg());
		if (taskBook.getFeechapter() != null)
			mcpBook.setFeechapter(taskBook.getFeechapter());
		if (taskBook.getIntro() != null)
			mcpBook.setIntro(taskBook.getIntro());
		if (taskBook.getKeywords() != null)
			mcpBook.setKeywords(taskBook.getKeywords());
		if (taskBook.getPrice() != null)
			mcpBook.setPrice(taskBook.getPrice());
		if (taskBook.getSubhead() != null)
			mcpBook.setSubhead(taskBook.getSubhead());
		if (taskBook.getTags() != null)
			mcpBook.setTags(taskBook.getTags());
		if (taskBook.getUpdatetime() != null)
			mcpBook.setUpdatetime(taskBook.getUpdatetime());
		if (taskBook.getWords() != null)
			mcpBook.setWords(taskBook.getWords());
		// 不保存emoji图片
		if (mcpBook.getActors() != null)
			mcpBook.setActors(mcpBook.getActors().replaceAll("[\\x{10000}-\\x{10FFFF}]", "").replaceAll("[\\x{00}-\\x{FF}]", ""));
		if (mcpBook.getAuthor() != null)
			mcpBook.setAuthor(mcpBook.getAuthor().replaceAll("[\\x{10000}-\\x{10FFFF}]", "").replaceAll("[\\x{00}-\\x{FF}]", ""));
		if (mcpBook.getIntro() != null)
			mcpBook.setIntro(mcpBook.getIntro().replaceAll("[\\x{10000}-\\x{10FFFF}]", "").replaceAll("[\\x{00}-\\x{FF}]", ""));
		if (mcpBook.getName() != null)
			mcpBook.setName(mcpBook.getName().replaceAll("[\\x{10000}-\\x{10FFFF}]", "").replaceAll("[\\x{00}-\\x{FF}]", ""));
		if (mcpBook.getCpname() != null)
			mcpBook.setCpname(mcpBook.getCpname().replaceAll("[\\x{10000}-\\x{10FFFF}]", "").replaceAll("[\\x{00}-\\x{FF}]", ""));
		return mcpBook;
	}

}
