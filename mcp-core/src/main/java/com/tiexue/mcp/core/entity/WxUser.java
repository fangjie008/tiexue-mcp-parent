package com.tiexue.mcp.core.entity;

import java.util.Date;

public class WxUser {
    private Integer id;

    private String name;

    private String headericon;

    private String signature;

    private String pwd;

    private Integer sex;

    private String city;

    private String province;

    private Integer usertype;

    private Integer coin;

    private Date deadline;

    private String devicecode;

    private Integer status;

    private String mobile;

    private String openid;

    private String weixintoken;

    private String token;
    
    private String fromname;

    private Date lastactivetime;

    private Date createtime;

    private Date updatetime;

    private String autopurchase;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getHeadericon() {
        return headericon;
    }

    public void setHeadericon(String headericon) {
        this.headericon = headericon == null ? null : headericon.trim();
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature == null ? null : signature.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }

    public Integer getCoin() {
        return coin;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getDevicecode() {
        return devicecode;
    }

    public void setDevicecode(String devicecode) {
        this.devicecode = devicecode == null ? null : devicecode.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getWeixintoken() {
        return weixintoken;
    }

    public void setWeixintoken(String weixintoken) {
        this.weixintoken = weixintoken == null ? null : weixintoken.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public String getFromname() {
		return fromname;
	}

	public void setFromname(String fromname) {
		this.fromname = fromname;
	}

	public Date getLastactivetime() {
        return lastactivetime;
    }

    public void setLastactivetime(Date lastactivetime) {
        this.lastactivetime = lastactivetime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getAutopurchase() {
        return autopurchase;
    }

    public void setAutopurchase(String autopurchase) {
        this.autopurchase = autopurchase == null ? null : autopurchase.trim();
    }
}