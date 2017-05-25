package com.tiexue.mcp.task.service;

import java.util.List;

import com.tiexue.mcp.core.entity.McpBook;
import com.tiexue.mcp.task.entity.TaskBook;



public interface ITaskBookService {

	/**
	 * 插入采集的书籍数据
	 * @param record
	 * @return
	 */
	McpBook insert(TaskBook record);
	/**
	 * 根据合作方的小说ID查找小说是否存在
	 * @param cpBid
	 * @return
	 */
	McpBook selectByCpBId(String cpBid);
	/**
	 * 更新小说信息
	 * @param record
	 * @return
	 */
	McpBook update(McpBook record,TaskBook taskBook);

}
