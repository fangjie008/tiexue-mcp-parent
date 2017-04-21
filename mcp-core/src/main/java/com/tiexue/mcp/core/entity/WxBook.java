package com.tiexue.mcp.core.entity;

import java.util.Date;

public class WxBook {
    private Integer id;

    private String name;

    private String intr;

    private Integer publisherid;

    private String publishername;

    private String coverimgs;

    private String tag;

    private Integer mark;

    private Integer sort;

    private Integer status;

    private Integer viewcount;

    private Integer commentcount;

    private Integer dingcount;

    private Integer caicount;

    private Integer sharecount;

    private Integer contentlen;

    private Date createtime;

    private Date updatetime;
    
    private String uniqueflag;
    
    private int collectionid;

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

    public String getIntr() {
        return intr;
    }

    public void setIntr(String intr) {
        this.intr = intr == null ? null : intr.trim();
    }

    public Integer getPublisherid() {
        return publisherid;
    }

    public void setPublisherid(Integer publisherid) {
        this.publisherid = publisherid;
    }

    public String getPublishername() {
        return publishername;
    }

    public void setPublishername(String publishername) {
        this.publishername = publishername == null ? null : publishername.trim();
    }

    public String getCoverimgs() {
        return coverimgs;
    }

    public void setCoverimgs(String coverimgs) {
        this.coverimgs = coverimgs == null ? null : coverimgs.trim();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getViewcount() {
        return viewcount;
    }

    public void setViewcount(Integer viewcount) {
        this.viewcount = viewcount;
    }

    public Integer getCommentcount() {
        return commentcount;
    }

    public void setCommentcount(Integer commentcount) {
        this.commentcount = commentcount;
    }

    public Integer getDingcount() {
        return dingcount;
    }

    public void setDingcount(Integer dingcount) {
        this.dingcount = dingcount;
    }

    public Integer getCaicount() {
        return caicount;
    }

    public void setCaicount(Integer caicount) {
        this.caicount = caicount;
    }

    public Integer getSharecount() {
        return sharecount;
    }

    public void setSharecount(Integer sharecount) {
        this.sharecount = sharecount;
    }

    public Integer getContentlen() {
        return contentlen;
    }

    public void setContentlen(Integer contentlen) {
        this.contentlen = contentlen;
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

	public String getUniqueflag() {
		return uniqueflag;
	}

	public void setUniqueflag(String uniqueflag) {
		this.uniqueflag = uniqueflag;
	}

	public int getCollectionid() {
		return collectionid;
	}

	public void setCollectionid(int collectionid) {
		this.collectionid = collectionid;
	}
    
}