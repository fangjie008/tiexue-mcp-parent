package com.tiexue.mcp.core.entity;

import java.util.Date;

public class McpSettlement {
    private Integer id;

    private Integer cpid;

    private Integer consume;

    private String month;

    private Integer divideconsume;

    private Integer settlementstatus;

    private Date createtime;

    private Date updatetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCpid() {
        return cpid;
    }

    public void setCpid(Integer cpid) {
        this.cpid = cpid;
    }

    public Integer getConsume() {
        return consume;
    }

    public void setConsume(Integer consume) {
        this.consume = consume;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getDivideconsume() {
        return divideconsume;
    }

    public void setDivideconsume(Integer divideconsume) {
        this.divideconsume = divideconsume;
    }

    public Integer getSettlementstatus() {
        return settlementstatus;
    }

    public void setSettlementstatus(Integer settlementstatus) {
        this.settlementstatus = settlementstatus;
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
}