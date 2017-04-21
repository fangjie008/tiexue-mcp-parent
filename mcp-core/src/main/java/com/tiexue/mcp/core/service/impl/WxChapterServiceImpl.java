package com.tiexue.mcp.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tiexue.mcp.core.entity.WxChapter;
import com.tiexue.mcp.core.mapper.WxChapterMapper;
import com.tiexue.mcp.core.service.IWxChapterService;

@Service("wxChapterService")
public class WxChapterServiceImpl implements IWxChapterService {

	@Resource
	private WxChapterMapper wxChapterMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return wxChapterMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(WxChapter record) {
		return wxChapterMapper.insert(record);
	}

	@Override
	public int insertSelective(WxChapter record) {
		return wxChapterMapper.insertSelective(record);
	}

	@Override
	public WxChapter selectByPrimaryKey(Integer id, Integer status) {
		return wxChapterMapper.selectByPrimaryKey(id, status);
	}

	@Override
	public List<WxChapter> selectByBookId(Integer bookId, Integer status, Integer pageNo, Integer pageSize) {
		return wxChapterMapper.selectByBookId(bookId, status, pageNo, pageSize);
	}

	@Override
	public int updateByPrimaryKeySelective(WxChapter record) {
		return wxChapterMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(WxChapter record) {
		return wxChapterMapper.updateByPrimaryKey(record);
	}

	@Override
	public int getCountByBookId(Integer bookId, Integer status) {
		return wxChapterMapper.getCountByBookId(bookId, status);
	}

	@Override
	public WxChapter getPreChapter(Integer bookId,Integer chapterId, Integer status) {
		return wxChapterMapper.getPreChapter(bookId,chapterId, status);
	}

	@Override
	public WxChapter getNextChapter(Integer bookId,Integer chapterId, Integer status) {
		return wxChapterMapper.getNextChapter(bookId,chapterId, status);
	}

	@Override
	public WxChapter getFirstChapter(Integer bookId,Integer status) {
		return wxChapterMapper.getFirstChapter(bookId,status);
	}

	@Override
	public WxChapter getLastChapter(Integer bookId, Integer status) {
		return wxChapterMapper.getLastChapter(bookId, status);
	}

}
