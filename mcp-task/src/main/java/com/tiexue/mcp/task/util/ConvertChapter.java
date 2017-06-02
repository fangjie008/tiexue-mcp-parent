package com.tiexue.mcp.task.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import com.tiexue.mcp.base.util.StringUtils;
import com.tiexue.mcp.core.entity.McpChapter;
import com.tiexue.mcp.core.entity.McpConstants;
import com.tiexue.mcp.task.entity.TaskChapter;

public class ConvertChapter {

	/**
	 * 填充采集的章节信息，合并两次采集的章节信息
	 * 
	 * @param oldChapter
	 * @param newChapter
	 * @return
	 */
	public static TaskChapter fillTaskChapter(TaskChapter oldChapter, TaskChapter newChapter) {
		if (newChapter == null)
			return oldChapter;
		if (oldChapter == null)
			return newChapter;
		if (newChapter.getBookid() != null)
			oldChapter.setBookid(newChapter.getBookid());
		if (newChapter.getBookname() != null)
			oldChapter.setBookname(newChapter.getBookname());
		if (newChapter.getContent() != null)
			oldChapter.setContent(newChapter.getContent());
		if (newChapter.getCpbookid() != null)
			oldChapter.setCpbookid(newChapter.getCpbookid());
		if (newChapter.getCpchapterid() != null)
			oldChapter.setCpchapterid(newChapter.getCpchapterid());
		// if(newChapter.getCpid()!=null)
		// oldChapter.setCpid(newChapter.getCpid());
		if (newChapter.getIsvip() != null)
			oldChapter.setIsvip(newChapter.getIsvip());
		if (newChapter.getMd5() != null)
			oldChapter.setMd5(newChapter.getMd5());
		if (newChapter.getMd5() != null)
			oldChapter.setMd5(newChapter.getMd5());
		if (newChapter.getName() != null)
			oldChapter.setName(newChapter.getName());
		if (newChapter.getOrder() != null)
			oldChapter.setOrder(newChapter.getOrder());
		if (newChapter.getPrice() != null)
			oldChapter.setPrice(newChapter.getPrice());
		if (newChapter.getUpdatetime() != null)
			oldChapter.setUpdatetime(newChapter.getUpdatetime());
		if (newChapter.getWords() != null)
			oldChapter.setWords(newChapter.getWords());
		// 不保存emoji图片
		if (oldChapter.getBookname() != null)
			oldChapter.setBookname(oldChapter.getBookname().replaceAll("[\\x{10000}-\\x{10FFFF}]", "")
					);
		if (oldChapter.getName() != null)
			oldChapter.setName(oldChapter.getName().replaceAll("[\\x{10000}-\\x{10FFFF}]", "")
					);
		if (oldChapter.getContent() != null)
			oldChapter.setContent(oldChapter.getContent().replaceAll("[\\x{10000}-\\x{10FFFF}]", "")
					);

		return oldChapter;
	}

	/**
	 * 把采集的需要新增的章节信息转化成McpChapter对象
	 * 
	 * @param record
	 * @return
	 */
	public static McpChapter toMcpChapterDaoForInsert(TaskChapter record) {
		if (record == null)
			return null;
		McpChapter mcpChapter = new McpChapter();
		// 新增的书审核状态默认为未审核
		mcpChapter.setAuditstatus(McpConstants.AuditStatus_UnAudit);
		mcpChapter.setAuditinfo("");
		mcpChapter.setBookid(record.getBookid() == null ? 0 : record.getBookid());
		mcpChapter.setBookname(record.getBookname() == null ? "" : record.getBookname());
		mcpChapter.setContent(record.getContent() == null ? "" : record.getContent());
		mcpChapter.setCpbookid(record.getCpbookid() == null ? "" : record.getCpbookid());
		mcpChapter.setCpid(record.getCpid() == null ? 0 : record.getCpid());
		mcpChapter.setCpchapterid(record.getCpchapterid() == null ? "" : record.getCpchapterid());
		mcpChapter.setCreatetime(record.getCreatetime() == null ? new Date() : record.getCreatetime());
		mcpChapter.setIsvip(record.getIsvip() == null ? 0 : record.getIsvip());
		mcpChapter.setMd5(record.getMd5() == null ? "" : record.getMd5());
		mcpChapter.setName(record.getName() == null ? "" : record.getName());
		mcpChapter.setOrder(record.getOrder() == null ? 0 : record.getOrder());
		mcpChapter.setPrice(record.getPrice() == null ? 0 : record.getPrice());
		mcpChapter.setUpdatetime(record.getUpdatetime() == null ? new Date() : record.getUpdatetime());
		mcpChapter.setWords(record.getWords() == null ? 0 : record.getWords());
		//设置唯一标识
		String uniqueflag=mcpChapter.getCpid()+"-"+mcpChapter.getCpbookid()+"-"+mcpChapter.getCpchapterid();
		mcpChapter.setUniqueflag(uniqueflag);
		// 不保存emoji图片
		if (mcpChapter.getBookname() != null)
			mcpChapter.setBookname(mcpChapter.getBookname().replaceAll("[\\x{10000}-\\x{10FFFF}]", "")
					);
		if (mcpChapter.getName() != null)
			mcpChapter.setName(mcpChapter.getName().replaceAll("[\\x{10000}-\\x{10FFFF}]", "")
					);
		if (mcpChapter.getContent() != null)
			mcpChapter.setContent(mcpChapter.getContent().replaceAll("[\\x{10000}-\\x{10FFFF}]", "")
					);
		// content为Text类型(text最大为65535)
		try {
			if (mcpChapter.getContent() != null && mcpChapter.getContent().getBytes("UTF-8").length > 65535) {
				String tempStr=StringUtils.subStr(mcpChapter.getContent(), 65533);
				mcpChapter.setContent(tempStr);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return mcpChapter;
	}

	/**
	 * 把采集的需要更新的章节信息转化成McpChapter对象
	 * 
	 * @param oldRecord
	 * @param taskChapter
	 * @return
	 */
	public static McpChapter toMcpChapterDaoForUpdate(McpChapter mcpChapter, TaskChapter taskChapter) {
		if (taskChapter == null)
			return mcpChapter;
		if (taskChapter.getBookid() != null)
			mcpChapter.setBookid(taskChapter.getBookid());
		if (taskChapter.getBookname() != null)
			mcpChapter.setBookname(taskChapter.getBookname());
		if (taskChapter.getContent() != null)
			mcpChapter.setContent(taskChapter.getContent());
//		if (taskChapter.getCpbookid() != null)
//			mcpChapter.setCpbookid(taskChapter.getCpbookid());
		// if(taskChapter.getCpid()!=null)
		// mcpChapter.setCpid(taskChapter.getCpid());
//		if (taskChapter.getCpchapterid() != null)
//			mcpChapter.setCpchapterid(taskChapter.getCpchapterid());
		if (taskChapter.getIsvip() != null)
			mcpChapter.setIsvip(taskChapter.getIsvip());
		if (taskChapter.getMd5() != null)
			mcpChapter.setMd5(taskChapter.getMd5());
		if (taskChapter.getMd5() != null)
			mcpChapter.setMd5(taskChapter.getMd5());
		if (taskChapter.getName() != null)
			mcpChapter.setName(taskChapter.getName());
		if (taskChapter.getOrder() != null)
			mcpChapter.setOrder(taskChapter.getOrder());
		if (taskChapter.getPrice() != null)
			mcpChapter.setPrice(taskChapter.getPrice());
		if (taskChapter.getUpdatetime() != null)
			mcpChapter.setUpdatetime(taskChapter.getUpdatetime());
		if (taskChapter.getWords() != null)
			mcpChapter.setWords(taskChapter.getWords());
		// 不保存emoji图片
		if (mcpChapter.getBookname() != null)
			mcpChapter.setBookname(mcpChapter.getBookname().replaceAll("[\\x{10000}-\\x{10FFFF}]", "")
					);
		if (mcpChapter.getName() != null)
			mcpChapter.setName(mcpChapter.getName().replaceAll("[\\x{10000}-\\x{10FFFF}]", "")
					);
		if (mcpChapter.getContent() != null)
			mcpChapter.setContent(mcpChapter.getContent().replaceAll("[\\x{10000}-\\x{10FFFF}]", "")
					);
		// content为Text类型(text最大为65535)
		try {
			if (mcpChapter.getContent() != null && mcpChapter.getContent().getBytes("UTF-8").length > 65535) {
				String tempStr=StringUtils.subStr(mcpChapter.getContent(), 65533);
				mcpChapter.setContent(tempStr);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return mcpChapter;
	}

}
