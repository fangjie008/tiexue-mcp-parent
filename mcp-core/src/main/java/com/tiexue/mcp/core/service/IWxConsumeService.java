package com.tiexue.mcp.core.service;

import java.util.List;

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
}
