package com.tiexue.mcp.core.service.impl;


import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tiexue.mcp.base.util.CyptoUtils;
import com.tiexue.mcp.base.util.DateUtil;
import com.tiexue.mcp.core.dto.PageUserDto;
import com.tiexue.mcp.core.entity.EnumType;
import com.tiexue.mcp.core.entity.WxConsume;
import com.tiexue.mcp.core.entity.WxUser;
import com.tiexue.mcp.core.mapper.WxUserMapper;
import com.tiexue.mcp.core.service.IWxUserService;

import weixin.popular.bean.sns.SnsToken;
import weixin.popular.bean.user.User;
@Service("wxUserService")
public class WxUserServiceImpl implements IWxUserService{
	// 日志
	private Logger logger = Logger.getLogger(WxUserServiceImpl.class);
	@Resource
	WxUserMapper userMapper;
	@Resource
	WxConsumeServiceImpl consSerImpl;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(WxUser record) {
		return userMapper.insert(record);
	}

	@Override
	public int insertSelective(WxUser record) {
		return userMapper.insertSelective(record);
	}

	@Override
	public WxUser selectByPrimaryKey(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(WxUser record) {
		return userMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(WxUser record) {
		return userMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(WxUser record) {
		return userMapper.updateByPrimaryKeyWithBLOBs(record);
	}
	
	
	@Override
	public int updateCoin(Integer id,Integer coin){
		return userMapper.updateCoin(id, coin);
	}

	/**
	 * 更新操作 
	 */
	@Override
	@Transactional
	public boolean updateCoin(WxUser record, WxConsume cons) {
		boolean resUpdate=false;
		try {
			userMapper.updateByPrimaryKey(record);
		    consSerImpl.insert(cons);
		    resUpdate=true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			//必须抛出异常 否则事务就不能正常执行
			throw e;
		}
		return resUpdate;
	}

	@Override
	public WxUser getModelByOpenId(String openId){
		return userMapper.getModelByOpenId(openId);
	}
	
	@Override
	public WxUser saveLoginMsg(SnsToken user,User wxSnsUser,String fm){
		if(user==null||wxSnsUser==null)
			return null;
	    WxUser wxUser;
	    wxUser=getModelByOpenId(wxSnsUser.getOpenid());
	    if(wxUser!=null&&wxUser.getId()>0){
	    	wxUser.setWeixintoken(user.getAccess_token());
	    	wxUser.setToken(user.getRefresh_token());
	    	wxUser.setLastactivetime(DateUtil.fomatCurrentDate("yyyy-MM-dd HH:mm:ss"));
	        updateByPrimaryKey(wxUser);
	        return wxUser;
	    }else{
	    	if(fm==null)
	    		fm="";
	    	wxUser=new WxUser();
	    	wxUser.setAutopurchase("");
	    	wxUser.setCity(wxSnsUser.getCity());
	    	wxUser.setCoin(0);
	    	wxUser.setPwd("");
	    	wxUser.setCreatetime(DateUtil.fomatCurrentDate("yyyy-MM-dd HH:mm:ss"));
	    	wxUser.setDeadline(DateUtil.fomatCurrentDate("yyyy-MM-dd HH:mm:ss"));
	    	wxUser.setDevicecode("");
	    	wxUser.setHeadericon(wxSnsUser.getHeadimgurl());
	    	wxUser.setLastactivetime(DateUtil.fomatCurrentDate("yyyy-MM-dd HH:mm:ss"));
	    	wxUser.setMobile("");
	    	wxUser.setName(wxSnsUser.getNickname().replaceAll("[\\x{10000}-\\x{10FFFF}]", ""));
	    	wxUser.setOpenid(wxSnsUser.getOpenid());
	    	wxUser.setProvince(wxSnsUser.getProvince());
	    	wxUser.setSex(wxSnsUser.getSex());
	    	wxUser.setSignature("");
	    	wxUser.setStatus(EnumType.UserStatus_Normal);
	    	wxUser.setWeixintoken(user.getAccess_token());
	    	wxUser.setToken(user.getRefresh_token());
	    	wxUser.setUpdatetime(DateUtil.fomatCurrentDate("yyyy-MM-dd HH:mm:ss"));
	    	wxUser.setUsertype(EnumType.UserType_Normal);
	    	wxUser.setFromname(fm);
	    	int wxUserId=insert(wxUser);
	    	
	    	if(wxUser.getId()==null){
	    		logger.error("保存登录信息后返回的Id为null");
	    		wxUser.setId(0);
	    	}
	    	//logger.error("保存登录信息后返回的Id："+wxUser.toString());
	    	//wxUser.setId(wxUserId);
	        return wxUser;
	    }
	}
	
	public String setLoginInCookie(WxUser wxUser){
		String wx_gzh_token=wxUser.getOpenid()+","+wxUser.getId()+","+wxUser.getName();
		wx_gzh_token= CyptoUtils.encode(EnumType.Des_Key,wx_gzh_token);
		return wx_gzh_token;
	}
	
	@Override
	public PageUserDto getPageUserDto(String wx_gzh_token){
		PageUserDto pageUser=new PageUserDto();
		if(wx_gzh_token==null||wx_gzh_token.isEmpty())
			return null;
		logger.error("解析前cookie："+wx_gzh_token);
		wx_gzh_token=CyptoUtils.decode(EnumType.Des_Key,wx_gzh_token);
		logger.error("解析后cookie："+wx_gzh_token);
		String[] tokens=wx_gzh_token.split(",");
		if(tokens.length>=3){
			pageUser.setOpenid(tokens[0]);
			pageUser.setId(tokens[1]);
			pageUser.setName(tokens[2]);
		}
		return pageUser;
	}
	
}
