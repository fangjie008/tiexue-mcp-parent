package com.tiexue.mcp.core.service;

import java.util.List;

import com.tiexue.mcp.core.entity.WxPay;
import com.tiexue.mcp.core.entity.WxUser;

import weixin.popular.bean.paymch.MchPayNotify;
import weixin.popular.bean.paymch.UnifiedorderResult;

public interface IWxPayService {
	
	    int deleteByPrimaryKey(String ordernum);
	    
	    int insert(WxPay record);

	    int insertSelective(WxPay record);

	    WxPay selectByPrimaryKey(String ordernum);

	    int updateByPrimaryKeySelective(WxPay record);

	    int updateByPrimaryKey(WxPay record);
	    
	    /**
	     * 获取用户充值成功的次数
	     * @param userId
	     * @param orderStatus
	     * @return
	     */
	    int getPayCountByUserId(int userId,int orderStatus,String orderNum);
	    
		/**
		 * 根据userId获取支付记录
		 * @param userId
		 * @param pageNo
		 * @param pageSize
		 * @return
		 */
		List<WxPay> getListByPage(int userId,int pageNo,int pageSize);
		/**
		 * 获取总数
		 * @param userId
		 * @return
		 */
		Integer getCountByUserId(int userId);
		
		/**
		 * 统一下单接口
		 * @param wxUser
		 * @param type
		 * @param money
		 * @param bookId
		 * @param chapterId
		 * @param remoteAdd
		 * @return
		 */
		UnifiedorderResult createUnifiedorder(WxUser wxUser, int type,int money,int coin, int bookId, int chapterId, String remoteAdd);

		/**
		 * 处理支付成功请求
		 * @param payNotify
		 * @return
		 */
		boolean handlePayNotify(MchPayNotify payNotify);
		
		/**
		 * 测试使用
		 * @param openid
		 * @param out_trade_no
		 * @param wxOrderNo
		 * @return
		 */
		boolean testHandlePayNotify(String openid,String out_trade_no,String wxOrderNo);

}
