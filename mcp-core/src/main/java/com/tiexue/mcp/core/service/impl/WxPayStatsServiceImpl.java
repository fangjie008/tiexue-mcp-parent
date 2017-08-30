package com.tiexue.mcp.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tiexue.mcp.core.dto.WxPayStatsDto;
import com.tiexue.mcp.core.entity.WxBook;
import com.tiexue.mcp.core.entity.WxPlatformSign;
import com.tiexue.mcp.core.service.IWeiXinUserInfoService;
import com.tiexue.mcp.core.service.IWxBookService;
import com.tiexue.mcp.core.service.IWxConsumeService;
import com.tiexue.mcp.core.service.IWxPayService;
import com.tiexue.mcp.core.service.IWxPayStatsService;
import com.tiexue.mcp.core.service.IWxPlatformSignService;
import com.tiexue.mcp.core.service.IWxUserService;

@Service
public class WxPayStatsServiceImpl implements IWxPayStatsService {

	@Resource 
	IWxBookService iWxBookSer;
	@Resource
	IWxPayService iWxPaySer;
	@Resource
	IWxUserService iWxUserSer;
	@Resource 
	IWxConsumeService iWxConsumeSer;
	@Resource 
	IWxPlatformSignService iWxPlatformSignSer;
	@Resource 
	IWeiXinUserInfoService iWeiXinUserInfoSer;
	@Override
	public WxPayStatsDto getFollowPayStats(String sign) {
		WxPayStatsDto resultDto=new  WxPayStatsDto();
		int loginCount= iWxUserSer.getLoginCount(sign);
		resultDto.setAnonymityLogin(loginCount);
		int followCount=iWeiXinUserInfoSer.getFollowCount(sign);
		resultDto.setFollowCount(followCount);
		int totalPayMoney=iWxPaySer.getTotalPayMoneyBySign(sign);
		resultDto.setTotalPayMoney(totalPayMoney);
		WxPlatformSign wxPlatformSign= iWxPlatformSignSer.getModelBySign(sign);
		if(wxPlatformSign!=null){
			resultDto.setNovelId(wxPlatformSign.getNovelid());
			int totalConsumeMoney=iWxConsumeSer.getSumCostcoinByBookIdAndSign(wxPlatformSign.getNovelid(), sign);
			resultDto.setTotalConsumeMoney(totalConsumeMoney);
			WxBook wxBook= iWxBookSer.selectByPrimaryKey(wxPlatformSign.getNovelid());
			if(wxBook!=null){
				resultDto.setTitle(wxBook.getName());
			}
		}
		return resultDto;
	}


}
