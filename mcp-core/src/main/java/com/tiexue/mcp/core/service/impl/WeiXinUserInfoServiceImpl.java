package com.tiexue.mcp.core.service.impl;

import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tiexue.mcp.base.util.DateUtil;
import com.tiexue.mcp.core.entity.WeiXinUserInfo;
import com.tiexue.mcp.core.entity.WxUser;
import com.tiexue.mcp.core.mapper.WeiXinUserInfoMapper;
import com.tiexue.mcp.core.service.IWeiXinUserInfoService;
import com.tiexue.mcp.core.service.IWxUserService;

import weixin.popular.bean.user.User;

@Service("weiXinUserInfoSer")
public class WeiXinUserInfoServiceImpl implements IWeiXinUserInfoService {

	@Resource 
	WeiXinUserInfoMapper weiXinUserInfoMapper;
	@Resource 
	IWxUserService iWxUserService;
	@Override
	public int deleteByPrimaryKey(String uuid) {
		return weiXinUserInfoMapper.deleteByPrimaryKey(uuid);
	}

	@Override
	public int insert(WeiXinUserInfo record) {
		return weiXinUserInfoMapper.insert(record);
	}

	@Override
	public int insertSelective(WeiXinUserInfo record) {
		return weiXinUserInfoMapper.insertSelective(record);
	}

	@Override
	public WeiXinUserInfo selectByPrimaryKey(String uuid) {
		return weiXinUserInfoMapper.selectByPrimaryKey(uuid);
	}

	@Override
	public int updateByPrimaryKeySelective(WeiXinUserInfo record) {
		return weiXinUserInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(WeiXinUserInfo record) {
		return weiXinUserInfoMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(WeiXinUserInfo record) {
		return weiXinUserInfoMapper.updateByPrimaryKey(record);
	}

	@Override
	public WeiXinUserInfo getModelByUnionId(String unionid) {
		return weiXinUserInfoMapper.getModelByUnionId(unionid);
	}
	
	@Override
	public int saveWerXinUser(User user,int openIdCategory) {
		WeiXinUserInfo wInfo=null;
		if(user!=null){
			WxUser wxUser=null;
			if(user.getUnionid()!=null&&!user.getUnionid().isEmpty()){
				wInfo=getModelByUnionId(user.getUnionid());
				wxUser=iWxUserService.getModelByUnionId(user.getUnionid());
			}
			//新增
			if(wInfo==null){
				wInfo=new WeiXinUserInfo();
				toWeiXinUserInfoForInsert(user, openIdCategory, wInfo, wxUser);
				return insert(wInfo);
			}
			//更新
			else{
				toWeiXinUserInfoForUpdate(user, openIdCategory, wInfo, wxUser);
				return updateByPrimaryKeyWithBLOBs(wInfo);
			}
			
			
		}
		return 0;
	}

	private void toWeiXinUserInfoForInsert(User user, int openIdCategory, WeiXinUserInfo wInfo, WxUser wxUser) {
		String uuId=UUID.randomUUID().toString().replace("-","");
		wInfo.setUuid(uuId);
		wInfo.setCity(user.getCity()==null?"":user.getCity());
		wInfo.setCountry(user.getCountry()==null?"":user.getCountry());
		wInfo.setCreatetime(new Date());
		wInfo.setGroupid(user.getGroupid()==null?0:user.getGroupid());
		wInfo.setHeadimgurl(user.getHeadimgurl()==null?"":user.getHeadimgurl());
		wInfo.setLanguage(user.getLanguage()==null?"":user.getLanguage());
		wInfo.setNickname(user.getNickname()==null?"":user.getNickname().replaceAll("[\\x{10000}-\\x{10FFFF}]", ""));
		wInfo.setOpenid(user.getOpenid()==null?"":user.getOpenid());
		wInfo.setOpenidcategory(openIdCategory);
		wInfo.setProvince(user.getProvince()==null?"":user.getProvince());
		wInfo.setRegisterinweixin(0);
		wInfo.setRegistertime(new Date());
		wInfo.setRemark(user.getRemark()==null?"":user.getRemark());
		wInfo.setSex(user.getSex()==null?0:user.getSex());
		wInfo.setSubscribe(user.getSubscribe()==null?0:user.getSubscribe());
		if(user.getSubscribe_time()!=null){
			String timeStr= user.getSubscribe_time().toString();
			Date date= DateUtil.secondTimestamp2Date(timeStr, 0);
			wInfo.setSubscribetime(date);
			wInfo.setSubscribeTime(timeStr);
		}
		else{
			wInfo.setSubscribetime(new Date());
			wInfo.setSubscribeTime("");
		}
		if(user.getTagid_list()!=null){
			String tagIds="";
			for (Integer tagId : user.getTagid_list()) {
				tagIds+=tagId+",";
			}
			if(tagIds.length()>1)
				tagIds.substring(0, tagIds.length()-1);
			wInfo.setTagidList(tagIds);
		}
		else{
			wInfo.setTagidList("");
		}
		wInfo.setUnionid(user.getUnionid()==null?"":user.getUnionid());
		if(wxUser!=null){
			wInfo.setUserid(wxUser.getId());
			wInfo.setPffrom(wxUser.getPffrom());
			wInfo.setPfcurrent(wxUser.getPfcurrent());
		}
		else{
			wInfo.setUserid(0);
			wInfo.setPffrom("");
			wInfo.setPfcurrent("");
		}
	}

	private void toWeiXinUserInfoForUpdate(User user, int openIdCategory, WeiXinUserInfo wInfo, WxUser wxUser) {
		wInfo.setCity(user.getCity()==null?"":user.getCity());
		wInfo.setCountry(user.getCountry()==null?"":user.getCountry());
		wInfo.setCreatetime(new Date());
		wInfo.setGroupid(user.getGroupid()==null?0:user.getGroupid());
		wInfo.setHeadimgurl(user.getHeadimgurl()==null?"":user.getHeadimgurl());
		wInfo.setLanguage(user.getLanguage()==null?"":user.getLanguage());
		wInfo.setNickname(user.getNickname()==null?"":user.getNickname().replaceAll("[\\x{10000}-\\x{10FFFF}]", ""));
		wInfo.setOpenid(user.getOpenid()==null?"":user.getOpenid());
		wInfo.setOpenidcategory(openIdCategory);
		wInfo.setProvince(user.getProvince()==null?"":user.getProvince());
		wInfo.setRemark(user.getRemark()==null?"":user.getRemark());
		wInfo.setSex(user.getSex()==null?0:user.getSex());
		wInfo.setSubscribe(user.getSubscribe()==null?0:user.getSubscribe());
		if(user.getSubscribe_time()!=null){
			String timeStr= user.getSubscribe_time().toString();
			Date date= DateUtil.secondTimestamp2Date(timeStr, 0);
			wInfo.setSubscribetime(date);
			wInfo.setSubscribeTime(timeStr);
		}
		if(user.getTagid_list()!=null){
			String tagIds="";
			for (Integer tagId : user.getTagid_list()) {
				tagIds+=tagId+",";
			}
			if(tagIds.length()>1)
				tagIds.substring(0, tagIds.length()-1);
			wInfo.setTagidList(tagIds);
		}
		wInfo.setUnionid(user.getUnionid()==null?"":user.getUnionid());
		if(wxUser!=null){
			wInfo.setUserid(wxUser.getId());
			wInfo.setPffrom(wxUser.getPffrom());
			wInfo.setPfcurrent(wxUser.getPfcurrent());
		}
	}



}
