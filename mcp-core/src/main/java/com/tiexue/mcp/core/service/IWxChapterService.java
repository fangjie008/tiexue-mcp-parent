package com.tiexue.mcp.core.service;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.tiexue.mcp.core.entity.WxBook;
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
	     * 获取最新章节
	     * @param bookId
	     * @param status
	     * @return
	     */
	    WxChapter getLastChapter(Integer bookId,Integer status);
	    
	    
	    /**
	     * 根据唯一标识查询章节数据(查看章节是否存在)
	     * @param uniqueFlag
	     * @return
	     */
	    WxChapter selectByUniqueFlag(String uniqueFlag);
	    /**
	     * 插入到wxChapter
	     * @param id
	     * @param uniqueflag
	     * @return
	     */
	    int insertToWxChapter(WxChapter wxChapter,Integer mcpBookId,Integer wxBookId,String uniqueflag);
	    
	    /**
	     * 根据mcpChapterId更新章节
	     * @param id
	     * @param uniqueflag
	     * @return
	     */
	    int updateToWxChapter(Integer mcpChapterId,String uniqueflag);
}

