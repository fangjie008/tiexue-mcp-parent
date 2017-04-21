package com.tiexue.mcp.core.service;



import com.tiexue.mcp.core.entity.WxPresent;
/**
 * 赠送小说币
 * @author 
 *
 */
public interface IWxPresentService {

	    int deleteByPrimaryKey(Integer id);


	    int insert(WxPresent record);

	    int insertSelective(WxPresent record);


	    WxPresent selectByPrimaryKey(Integer id);

	    int updateByPrimaryKeySelective(WxPresent record);


	    int updateByPrimaryKey(WxPresent record);
	    
	    /**
	     * 获取赠送小说币的记录
	     * @param userId
	     * @param startDt
	     * @param endDt
	     * @return
	     */
	    WxPresent getModelByGetTime(Integer userId,String startDt,String endDt);
	    /**
	     * 保存赠送小说币的记录，同时更新用户表中的小说币
	     * @param userId
	     * @param coin
	     * @return
	     */
	    boolean SaveRecord(int userId, int coin);
}
