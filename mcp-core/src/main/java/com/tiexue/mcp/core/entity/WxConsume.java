package com.tiexue.mcp.core.entity;

import java.util.Date;

public class WxConsume {
    private Integer id;

    private Integer userid;

    private Integer costcoin;

    private Integer bookid;

    private String bookname;

    private Integer charpterid;

    private String charptertitle;

    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getCostcoin() {
        return costcoin;
    }

    public void setCostcoin(Integer costcoin) {
        this.costcoin = costcoin;
    }

    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname == null ? null : bookname.trim();
    }

    public Integer getCharpterid() {
        return charpterid;
    }

    public void setCharpterid(Integer charpterid) {
        this.charpterid = charpterid;
    }

    public String getCharptertitle() {
        return charptertitle;
    }

    public void setCharptertitle(String charptertitle) {
        this.charptertitle = charptertitle == null ? null : charptertitle.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}