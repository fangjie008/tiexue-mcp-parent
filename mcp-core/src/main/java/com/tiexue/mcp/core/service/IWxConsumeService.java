package com.tiexue.mcp.core.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tiexue.mcp.core.entity.McpSettlement;
import com.tiexue.mcp.core.entity.WxConsume;


public interface IWxConsumeService {

	    int deleteByPrimaryKey(Integer id);

	    int insert(WxConsume record);

	    int insertSelective(WxConsume record);

	    WxConsume selectByPrimaryKey(Integer id);

	    int updateByPrimaryKeySelective(WxConsume record);

	    int updateByPrimaryKey(WxConsume record);
	    
	    List<WxConsume> getListByPage(int userId,int pageNo,int pageSize);

	  	Integer getCountByUserId(int userId);
	    /**
	     * 判断某个章节用户是否消费
	     * @param userId
	     * @param charpterId
	     * @return
	     */
	  	Integer judgeConsume(int userId,int charpterId);
	  	
	  	/**
	  	 * 根据日期查询合作方小说的消费金额
	  	 * @param cpId
	  	 * @param startTime
	  	 * @param endTime
	  	 * @return
	  	 */
	  	Integer getCostCoinByCpId(Integer cpId,Date startTime,Date endTime);
	  	
	  	/**
	  	 * 根据日期查询某本小说的消费金额
	  	 * @param bookId
	  	 * @param startTime
	  	 * @param endTime
	  	 * @return
	  	 */
	  	Integer getCostCoinByBookId(Integer bookId,Date startTime,Date endTime);
	  	
	  	List<Integer> getConsumeUserIdByBookId(int bookId,Date startTime,Date endTime);
	  	
	  	
	  	/**
	  	 * 根据推广标记和小说Id获取
	  	 * @param bookId
	  	 * @param sign
	  	 * @return
	  	 */
	  	Integer getSumCostcoinByBookIdAndSign(int bookId,String sign);
}
