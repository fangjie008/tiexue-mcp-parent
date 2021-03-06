package com.tiexue.mcp.core.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tiexue.mcp.core.entity.McpSettlement;
import com.tiexue.mcp.core.entity.WxConsume;
import com.tiexue.mcp.core.entity.WxPay;
import com.tiexue.mcp.core.mapper.WxConsumeMapper;
import com.tiexue.mcp.core.service.IWxConsumeService;

@Service("wxConsume")
public class WxConsumeServiceImpl implements IWxConsumeService {

	@Resource
	WxConsumeMapper consume;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return consume.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(WxConsume record) {
		return consume.insert(record);
	}

	@Override
	public int insertSelective(WxConsume record) {
		return consume.insertSelective(record);
	}

	@Override
	public WxConsume selectByPrimaryKey(Integer id) {
		return consume.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(WxConsume record) {
		return consume.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(WxConsume record) {
		return consume.updateByPrimaryKey(record);
	}

	@Override
	public List<WxConsume> getListByPage(int userId, int pageNo, int pageSize) {
		return consume.getListByPage(userId, pageNo, pageSize);
	}

	@Override
	public Integer getCountByUserId(int userId) {
		return consume.getCountByUserId(userId);
	}
	@Override
	public Integer judgeConsume(int userId,int charpterId) {
		return consume.judgeConsume(userId,charpterId);
	}

	@Override
	public Integer getCostCoinByCpId(Integer cpId,Date startTime,Date endTime) {
		return consume.getCostCoinByCpId(cpId, startTime, endTime);
	}

	@Override
	public Integer getCostCoinByBookId(Integer bookId,Date startTime,Date endTime) {
		return consume.getCostCoinByBookId(bookId, startTime, endTime);
	}

	@Override
	public List<Integer> getConsumeUserIdByBookId(int bookId, Date startTime,Date endTime) {
		return consume.getConsumeUserIdByBookId(bookId, startTime,endTime);
	}

	@Override
	public Integer  getSumCostcoinByBookIdAndSign(int bookId,String sign) {
		return consume.getSumCostcoinByBookIdAndSign(bookId, sign);
	}




}
