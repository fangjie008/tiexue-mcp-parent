package com.tiexue.mcp.core.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tiexue.mcp.core.entity.WxChapter;

public interface IWxChapterService  {
	
	    int deleteByPrimaryKey(Integer id);

	    int insert(WxChapter record);

	    int insertSelective(WxChapter record);

	    WxChapter selectByPrimaryKey(Integer id,Integer status);
	    
	    List<WxChapter> selectByBookId(Integer bookId,Integer status,Integer pageNo,Integer pageSize);

	    int updateByPrimaryKeySelective(WxChapter record);

	    int updateByPrimaryKey(WxChapter record);
	    
	    int getCountByBookId(Integer bookId,Integer status);
	    
	    //获取上一章内容
	    WxChapter getPreChapter(Integer bookId,Integer chapterId,Integer status);
	    
	    //获取下一章内容
	    WxChapter getNextChapter(Integer bookId,Integer chapterId,Integer status);
	    
	    WxChapter getFirstChapter(Integer bookId,Integer status);
	    /**
	     * 或许最新章节
	     * @param bookId
	     * @param status
	     * @return
	     */
	    WxChapter getLastChapter(Integer bookId,Integer status);
}

