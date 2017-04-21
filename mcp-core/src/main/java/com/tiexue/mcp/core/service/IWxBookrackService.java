package com.tiexue.mcp.core.service;



import java.util.List;

import com.tiexue.mcp.core.dto.WxBookrackDto;
import com.tiexue.mcp.core.entity.WxBookrack;

public interface IWxBookrackService {
	
	    int deleteByPrimaryKey(Integer id);
	    
	    int insert(WxBookrack record);

	    int insertSelective(WxBookrack record);
	    
	    WxBookrack selectByPrimaryKey(Integer id);

	    int updateByPrimaryKeySelective(WxBookrack record);
	    
	    int updateByPrimaryKey(WxBookrack record);
	    //根据图书Id获取最新书架
	    WxBookrack getModelByBookId(Integer userId,Integer bookId);
	    /**
	     * 根据图书Id获取书架列表
	     * @param userId 用户Id
	     * @param size  数据数量
	     * @return
	     */
	    List<WxBookrackDto> getListByUserId(Integer userId,Integer size);
	    
	    /**
	     * 根据图书Id获取最近阅读的书
	     * @param userId 用户Id
	     * @param size  数据数量
	     * @return
	     */
	    WxBookrack getModelByUserId(Integer userId);
	    /**
	     * 保存书架
	     * @param userId
	     * @param bookId
	     * @param bookName
	     * @param chapterId
	     * @param chapterTitle
	     * @return
	     */
	    boolean saveBookrack(int userId,int bookId,String bookName,Integer chapterId,String chapterTitle);

}
