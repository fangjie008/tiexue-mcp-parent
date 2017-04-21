package com.tiexue.mcp.core.dto;

public class WxUserDto {
    private Integer id;

    private String name;

    private String headericon;

    private String signature;

    private String pwd;

    private Integer usertype;
    
    private String usertypestr;
    
    private Integer coin;

    private String deadline;
    

    private String devicecode;

    private Integer status;
    
    private String statusStr;

    private String mobile;

    private String openid;

    private String weixintoken;

    private String token;

    private String lastactivetime;

    private String createtime;

    private String updatetime;

    private String autopurchase;
    
    private String fromname;

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
		this.openid = openid;
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

	public String getAutopurchase() {
        return autopurchase;
    }

    public void setAutopurchase(String autopurchase) {
        this.autopurchase = autopurchase == null ? null : autopurchase.trim();
    }

	public String getUsertypestr() {
		return usertypestr;
	}

	public void setUsertypestr(String usertypestr) {
		this.usertypestr = usertypestr;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public String getStatusStr() {
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public String getLastactivetime() {
		return lastactivetime;
	}

	public void setLastactivetime(String lastactivetime) {
		this.lastactivetime = lastactivetime;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
    
    
}