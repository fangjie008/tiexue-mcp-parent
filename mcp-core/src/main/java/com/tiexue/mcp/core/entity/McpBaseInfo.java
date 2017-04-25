package com.tiexue.mcp.core.entity;

import java.util.Date;

public class McpBaseInfo {
    private Integer cpid;

    private String name;

    private String contname;

    private String contemail;

    private String contqq;

    private String contphone;

    private String address;

    private String zipcode;

    private String bankname;

    private String bankaccountname;

    private String bankaccountnum;

    private Date registertime;

    private Date lastlogintime;

    private String password;

    private String appkey;

    private String interfaceurl1;

    private String interfaceurl2;

    private String interfaceurl3;

    private String interfaceurl4;

    public Integer getCpid() {
        return cpid;
    }

    public void setCpid(Integer cpid) {
        this.cpid = cpid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getContname() {
        return contname;
    }

    public void setContname(String contname) {
        this.contname = contname == null ? null : contname.trim();
    }

    public String getContemail() {
        return contemail;
    }

    public void setContemail(String contemail) {
        this.contemail = contemail == null ? null : contemail.trim();
    }

    public String getContqq() {
        return contqq;
    }

    public void setContqq(String contqq) {
        this.contqq = contqq == null ? null : contqq.trim();
    }

    public String getContphone() {
        return contphone;
    }

    public void setContphone(String contphone) {
        this.contphone = contphone == null ? null : contphone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode == null ? null : zipcode.trim();
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname == null ? null : bankname.trim();
    }

    public String getBankaccountname() {
        return bankaccountname;
    }

    public void setBankaccountname(String bankaccountname) {
        this.bankaccountname = bankaccountname == null ? null : bankaccountname.trim();
    }

    public String getBankaccountnum() {
        return bankaccountnum;
    }

    public void setBankaccountnum(String bankaccountnum) {
        this.bankaccountnum = bankaccountnum == null ? null : bankaccountnum.trim();
    }

    public Date getRegistertime() {
        return registertime;
    }

    public void setRegistertime(Date registertime) {
        this.registertime = registertime;
    }

    public Date getLastlogintime() {
        return lastlogintime;
    }

    public void setLastlogintime(Date lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey == null ? null : appkey.trim();
    }

    public String getInterfaceurl1() {
        return interfaceurl1;
    }

    public void setInterfaceurl1(String interfaceurl1) {
        this.interfaceurl1 = interfaceurl1 == null ? null : interfaceurl1.trim();
    }

    public String getInterfaceurl2() {
        return interfaceurl2;
    }

    public void setInterfaceurl2(String interfaceurl2) {
        this.interfaceurl2 = interfaceurl2 == null ? null : interfaceurl2.trim();
    }

    public String getInterfaceurl3() {
        return interfaceurl3;
    }

    public void setInterfaceurl3(String interfaceurl3) {
        this.interfaceurl3 = interfaceurl3 == null ? null : interfaceurl3.trim();
    }

    public String getInterfaceurl4() {
        return interfaceurl4;
    }

    public void setInterfaceurl4(String interfaceurl4) {
        this.interfaceurl4 = interfaceurl4 == null ? null : interfaceurl4.trim();
    }
}