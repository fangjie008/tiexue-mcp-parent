package com.tiexue.mcp.core.entity;

import java.util.Date;

public class McpBook {
	private Integer id;

	private Integer cpid;

	private String cpname;

	private String cpbid;

	private String name;

	private String subhead;

	private String author;

	private Integer channeltype;

	private Integer classify;

	private String tags;

	private String keywords;

	private String actors;

	private Integer bookstatus;

	private String coverimg;

	private String intro;

	private Date publishtime;

	private Integer words;

	private Integer chaptercount;

	private Date updatetime;

	private Date putawaytime;

	private Integer chargemode;

	private Integer price;

	private Integer feechapter;

	private Integer putawaystatus;

	private Integer auditstatus;

	private String auditinfo;

	private Date createtime;

	private Integer collectionstatus;

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

	public String getCpname() {
		return cpname;
	}

	public void setCpname(String cpname) {
		this.cpname = cpname == null ? null : cpname.trim();
	}

	public String getCpbid() {
		return cpbid;
	}

	public void setCpbid(String cpbid) {
		this.cpbid = cpbid == null ? null : cpbid.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getSubhead() {
		return subhead;
	}

	public void setSubhead(String subhead) {
		this.subhead = subhead == null ? null : subhead.trim();
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author == null ? null : author.trim();
	}

	public Integer getChanneltype() {
		return channeltype;
	}

	public void setChanneltype(Integer channeltype) {
		this.channeltype = channeltype;
	}

	public Integer getClassify() {
		return classify;
	}

	public void setClassify(Integer classify) {
		this.classify = classify;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags == null ? null : tags.trim();
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords == null ? null : keywords.trim();
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors == null ? null : actors.trim();
	}

	public Integer getBookstatus() {
		return bookstatus;
	}

	public void setBookstatus(Integer bookstatus) {
		this.bookstatus = bookstatus;
	}

	public String getCoverimg() {
		return coverimg;
	}

	public void setCoverimg(String coverimg) {
		this.coverimg = coverimg == null ? null : coverimg.trim();
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro == null ? null : intro.trim();
	}

	public Date getPublishtime() {
		return publishtime;
	}

	public void setPublishtime(Date publishtime) {
		this.publishtime = publishtime;
	}

	public Integer getWords() {
		return words;
	}

	public void setWords(Integer words) {
		this.words = words;
	}

	public Integer getChaptercount() {
		return chaptercount;
	}

	public void setChaptercount(Integer chaptercount) {
		this.chaptercount = chaptercount;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public Date getPutawaytime() {
		return putawaytime;
	}

	public void setPutawaytime(Date putawaytime) {
		this.putawaytime = putawaytime;
	}

	public Integer getChargemode() {
		return chargemode;
	}

	public void setChargemode(Integer chargemode) {
		this.chargemode = chargemode;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getFeechapter() {
		return feechapter;
	}

	public void setFeechapter(Integer feechapter) {
		this.feechapter = feechapter;
	}

	public Integer getPutawaystatus() {
		return putawaystatus;
	}

	public void setPutawaystatus(Integer putawaystatus) {
		this.putawaystatus = putawaystatus;
	}

	public Integer getAuditstatus() {
		return auditstatus;
	}

	public void setAuditstatus(Integer auditstatus) {
		this.auditstatus = auditstatus;
	}

	public String getAuditinfo() {
		return auditinfo;
	}

	public void setAuditinfo(String auditinfo) {
		this.auditinfo = auditinfo == null ? null : auditinfo.trim();
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getCollectionstatus() {
		return collectionstatus;
	}

	public void setCollectionstatus(Integer collectionstatus) {
		this.collectionstatus = collectionstatus;
	}

}