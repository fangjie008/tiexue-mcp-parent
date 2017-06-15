package com.tiexue.mcp.core.entity;

import java.util.Date;

public class McpCommitRecord {
    private Integer id;

    private Boolean commitstatus;

    private Date committime;

    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getCommitstatus() {
        return commitstatus;
    }

    public void setCommitstatus(Boolean commitstatus) {
        this.commitstatus = commitstatus;
    }

    public Date getCommittime() {
        return committime;
    }

    public void setCommittime(Date committime) {
        this.committime = committime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}