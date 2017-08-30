package com.tiexue.mcp.core.service;

import com.tiexue.mcp.core.dto.WxPayStatsDto;

public interface IWxPayStatsService {

	/**
	 * 推广数据统计
	 * @param sign
	 */
	WxPayStatsDto getFollowPayStats(String sign);
	
}
