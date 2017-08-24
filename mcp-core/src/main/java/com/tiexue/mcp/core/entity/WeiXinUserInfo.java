package com.tiexue.mcp.core.entity;

import java.util.Date;

public class WeiXinUserInfo {
    private String uuid;

    private String openid;

    private Integer userid;

    private String unionid;

    private Integer subscribe;

    private String nickname;

    private Integer sex;

    private String city;

    private String country;

    private String province;

    private String language;

    private String subscribeTime;

    private Date subscribetime;

    private String remark;

    private Integer groupid;

    private String tagidList;

    private Date createtime;

    private Integer registerinweixin;

    private Date registertime;

    private Integer openidcategory;

    private String pffrom;

    private String pfcurrent;

    private String headimgurl;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid == null ? null : unionid.trim();
    }

    public Integer getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Integer subscribe) {
        this.subscribe = subscribe;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language == null ? null : language.trim();
    }

    public String getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(String subscribeTime) {
        this.subscribeTime = subscribeTime == null ? null : subscribeTime.trim();
    }

    public Date getSubscribetime() {
        return subscribetime;
    }

    public void setSubscribetime(Date subscribetime) {
        this.subscribetime = subscribetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public String getTagidList() {
        return tagidList;
    }

    public void setTagidList(String tagidList) {
        this.tagidList = tagidList == null ? null : tagidList.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getRegisterinweixin() {
        return registerinweixin;
    }

    public void setRegisterinweixin(Integer registerinweixin) {
        this.registerinweixin = registerinweixin;
    }

    public Date getRegistertime() {
        return registertime;
    }

    public void setRegistertime(Date registertime) {
        this.registertime = registertime;
    }

    public Integer getOpenidcategory() {
        return openidcategory;
    }

    public void setOpenidcategory(Integer openidcategory) {
        this.openidcategory = openidcategory;
    }

    public String getPffrom() {
        return pffrom;
    }

    public void setPffrom(String pffrom) {
        this.pffrom = pffrom == null ? null : pffrom.trim();
    }

    public String getPfcurrent() {
        return pfcurrent;
    }

    public void setPfcurrent(String pfcurrent) {
        this.pfcurrent = pfcurrent == null ? null : pfcurrent.trim();
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl == null ? null : headimgurl.trim();
    }
}