package com.tiexue.mcp.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tiexue.mcp.core.entity.WxPlatformSign;
import com.tiexue.mcp.core.mapper.WxPlatformSignMapper;
import com.tiexue.mcp.core.service.IWxPlatformSignService;

@Service
public class WxPlatformSignServiceImpl implements IWxPlatformSignService {

	@Resource
	WxPlatformSignMapper wxPlatformSignMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return wxPlatformSignMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(WxPlatformSign record) {
		return wxPlatformSignMapper.insert(record);
	}

	@Override
	public int insertSelective(WxPlatformSign record) {
		return wxPlatformSignMapper.insertSelective(record);
	}

	@Override
	public WxPlatformSign selectByPrimaryKey(Integer id) {
		return wxPlatformSignMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(WxPlatformSign record) {
		return wxPlatformSignMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(WxPlatformSign record) {
		return wxPlatformSignMapper.updateByPrimaryKey(record);
	}

	@Override
	public WxPlatformSign getModelBySign(String sign) {
		return wxPlatformSignMapper.getModelBySign(sign);
	}

	@Override
	public List<WxPlatformSign> getListByPage(String strWhere, int pageNo, int pageSize) {
		return wxPlatformSignMapper.getListByPage(strWhere, pageNo, pageSize);
	}

	@Override
	public int getCount(String strWhere) {
		return wxPlatformSignMapper.getCount(strWhere);
	}

}
