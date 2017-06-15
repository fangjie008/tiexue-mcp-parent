package com.tiexue.mcp.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tiexue.mcp.core.entity.WxChapterSub;
import com.tiexue.mcp.core.mapper.WxChapterMapper;
import com.tiexue.mcp.core.mapper.WxChapterSubMapper;
import com.tiexue.mcp.core.service.IWxChapterSubService;

@Service("wxChapterSubService")
public class WxChapterSubServiceImpl implements IWxChapterSubService {
	@Resource
	private WxChapterSubMapper wxChapterSubMapper;
	
	@Override
	public int insert(WxChapterSub record) {
		return wxChapterSubMapper.insert(record);
	}

	@Override
	public int insertSelective(WxChapterSub record) {
		return wxChapterSubMapper.insertSelective(record);
	}

	@Override
	public int updateByBookId(WxChapterSub record) {
		return wxChapterSubMapper.updateByBookId(record);
	}

	@Override
	public WxChapterSub selectByChapterId(Integer id) {
		return wxChapterSubMapper.selectByChapterId(id);
	}

	@Override
	public int insertToWxChapterSub(Integer mcpChapterId,int wxChapterId) {
		return wxChapterSubMapper.insertToWxChapterSub(mcpChapterId, wxChapterId);
	}

	@Override
	public int updateToWxChapterSub(Integer wxChapterId, String uniqueflag) {
		return wxChapterSubMapper.updateToWxChapterSub(wxChapterId, uniqueflag);
	}

}
