package com.tiexue.mcp.core.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tiexue.mcp.core.entity.WxConsume;
import com.tiexue.mcp.core.entity.WxPresent;
import com.tiexue.mcp.core.entity.WxUser;
import com.tiexue.mcp.core.mapper.WxPresentMapper;
import com.tiexue.mcp.core.service.IWxPresentService;
import com.tiexue.mcp.core.service.IWxUserService;


@Service("wxPresentSer")
public class WxPresentServiceImpl implements IWxPresentService {

	// 日志
    private Logger logger = Logger.getLogger(WxPresentServiceImpl.class);
    @Resource
    WxPresentMapper presentSerMap;
    @Resource
    IWxUserService wxUserSerImpl;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return presentSerMap.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(WxPresent record) {
		return presentSerMap.insert(record);
	}

	@Override
	public int insertSelective(WxPresent record) {
		return presentSerMap.insertSelective(record);
	}

	@Override
	public WxPresent selectByPrimaryKey(Integer id) {
		return presentSerMap.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(WxPresent record) {
		return presentSerMap.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKey(WxPresent record) {
		return presentSerMap.updateByPrimaryKey(record);
	}

	@Override
	public WxPresent getModelByGetTime(Integer userId, String startDt, String endDt) {
		return presentSerMap.getModelByGetTime(userId, startDt, endDt);
	}

	/**
	 * 更新操作 
	 */
	@Override
	@Transactional
	public boolean SaveRecord(int userId, int coin) {
		boolean resUpdate=false;
		try {
			WxPresent pre=new WxPresent();
			pre.setUserid(userId);
			pre.setCoin(coin);
			pre.setGettime(new Date());
			presentSerMap.insert(pre);
			wxUserSerImpl.updateCoin(userId,coin);
		    resUpdate=true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			//必须抛出异常 否则事务就不能正常执行
			throw e;
		}
		return resUpdate;
	}

}
