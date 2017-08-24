package com.tiexue.mcp.core.mapper;

import com.tiexue.mcp.core.entity.WeiXinUserInfo;
import com.tiexue.mcp.core.entity.WxUser;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface WeiXinUserInfoMapper {
    @Delete({
        "delete from WeiXinUserInfo",
        "where UUId = #{uuid,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String uuid);

    @Insert({
        "insert into WeiXinUserInfo (UUId, OpenId, ",
        "UserId, unionid, ",
        "subscribe, nickname, ",
        "sex, city, country, ",
        "province, language, ",
        "subscribe_time, subscribeTime, ",
        "remark, groupid, ",
        "tagid_list, CreateTime, ",
        "RegisterInWeiXin, RegisterTime, ",
        "OpenIdCategory, PfFrom, ",
        "PfCurrent, headimgurl)",
        "values (#{uuid,jdbcType=VARCHAR}, #{openid,jdbcType=VARCHAR}, ",
        "#{userid,jdbcType=INTEGER}, #{unionid,jdbcType=VARCHAR}, ",
        "#{subscribe,jdbcType=INTEGER}, #{nickname,jdbcType=VARCHAR}, ",
        "#{sex,jdbcType=INTEGER}, #{city,jdbcType=VARCHAR}, #{country,jdbcType=VARCHAR}, ",
        "#{province,jdbcType=VARCHAR}, #{language,jdbcType=VARCHAR}, ",
        "#{subscribeTime,jdbcType=VARCHAR}, #{subscribetime,jdbcType=TIMESTAMP}, ",
        "#{remark,jdbcType=VARCHAR}, #{groupid,jdbcType=INTEGER}, ",
        "#{tagidList,jdbcType=VARCHAR}, #{createtime,jdbcType=TIMESTAMP}, ",
        "#{registerinweixin,jdbcType=INTEGER}, #{registertime,jdbcType=TIMESTAMP}, ",
        "#{openidcategory,jdbcType=INTEGER}, #{pffrom,jdbcType=VARCHAR}, ",
        "#{pfcurrent,jdbcType=VARCHAR}, #{headimgurl,jdbcType=LONGVARCHAR})"
    })
    int insert(WeiXinUserInfo record);

    int insertSelective(WeiXinUserInfo record);

    @Select({
        "select",
        "UUId, OpenId, UserId, unionid, subscribe, nickname, sex, city, country, province, ",
        "language, subscribe_time, subscribeTime, remark, groupid, tagid_list, CreateTime, ",
        "RegisterInWeiXin, RegisterTime, OpenIdCategory, PfFrom, PfCurrent, headimgurl",
        "from WeiXinUserInfo",
        "where UUId = #{uuid,jdbcType=VARCHAR}"
    })
    @ResultMap("ResultMapWithBLOBs")
    WeiXinUserInfo selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(WeiXinUserInfo record);

    @Update({
        "update WeiXinUserInfo",
        "set OpenId = #{openid,jdbcType=VARCHAR},",
          "UserId = #{userid,jdbcType=INTEGER},",
          "unionid = #{unionid,jdbcType=VARCHAR},",
          "subscribe = #{subscribe,jdbcType=INTEGER},",
          "nickname = #{nickname,jdbcType=VARCHAR},",
          "sex = #{sex,jdbcType=INTEGER},",
          "city = #{city,jdbcType=VARCHAR},",
          "country = #{country,jdbcType=VARCHAR},",
          "province = #{province,jdbcType=VARCHAR},",
          "language = #{language,jdbcType=VARCHAR},",
          "subscribe_time = #{subscribeTime,jdbcType=VARCHAR},",
          "subscribeTime = #{subscribetime,jdbcType=TIMESTAMP},",
          "remark = #{remark,jdbcType=VARCHAR},",
          "groupid = #{groupid,jdbcType=INTEGER},",
          "tagid_list = #{tagidList,jdbcType=VARCHAR},",
          "CreateTime = #{createtime,jdbcType=TIMESTAMP},",
          "RegisterInWeiXin = #{registerinweixin,jdbcType=INTEGER},",
          "RegisterTime = #{registertime,jdbcType=TIMESTAMP},",
          "OpenIdCategory = #{openidcategory,jdbcType=INTEGER},",
          "PfFrom = #{pffrom,jdbcType=VARCHAR},",
          "PfCurrent = #{pfcurrent,jdbcType=VARCHAR},",
          "headimgurl = #{headimgurl,jdbcType=LONGVARCHAR}",
        "where UUId = #{uuid,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKeyWithBLOBs(WeiXinUserInfo record);

    @Update({
        "update WeiXinUserInfo",
        "set OpenId = #{openid,jdbcType=VARCHAR},",
          "UserId = #{userid,jdbcType=INTEGER},",
          "unionid = #{unionid,jdbcType=VARCHAR},",
          "subscribe = #{subscribe,jdbcType=INTEGER},",
          "nickname = #{nickname,jdbcType=VARCHAR},",
          "sex = #{sex,jdbcType=INTEGER},",
          "city = #{city,jdbcType=VARCHAR},",
          "country = #{country,jdbcType=VARCHAR},",
          "province = #{province,jdbcType=VARCHAR},",
          "language = #{language,jdbcType=VARCHAR},",
          "subscribe_time = #{subscribeTime,jdbcType=VARCHAR},",
          "subscribeTime = #{subscribetime,jdbcType=TIMESTAMP},",
          "remark = #{remark,jdbcType=VARCHAR},",
          "groupid = #{groupid,jdbcType=INTEGER},",
          "tagid_list = #{tagidList,jdbcType=VARCHAR},",
          "CreateTime = #{createtime,jdbcType=TIMESTAMP},",
          "RegisterInWeiXin = #{registerinweixin,jdbcType=INTEGER},",
          "RegisterTime = #{registertime,jdbcType=TIMESTAMP},",
          "OpenIdCategory = #{openidcategory,jdbcType=INTEGER},",
          "PfFrom = #{pffrom,jdbcType=VARCHAR},",
          "PfCurrent = #{pfcurrent,jdbcType=VARCHAR}",
        "where UUId = #{uuid,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(WeiXinUserInfo record);
    
    
    @Select({
        "select",
        "UUId, OpenId, UserId, unionid, subscribe, nickname, sex, city, country, province, ",
        "language, subscribe_time, subscribeTime, remark, groupid, tagid_list, CreateTime, ",
        "RegisterInWeiXin, RegisterTime, OpenIdCategory, PfFrom, PfCurrent, headimgurl",
        "from WeiXinUserInfo",
        "where unionid = #{unionid,jdbcType=VARCHAR}"
    })
    @ResultMap("ResultMapWithBLOBs")
    WeiXinUserInfo getModelByUnionId(String unionid);
}