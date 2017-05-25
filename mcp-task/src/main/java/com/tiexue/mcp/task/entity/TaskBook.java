package com.tiexue.mcp.task.entity;

import java.util.Date;

public class TaskBook {


	/**
	 * 书籍url地址
	 */
	private String url;
	/**
	 * 书籍对应章节url地址
	 */
	private String chaptersurl;
	/**
	 * 合作方Id
	 */
	private Integer cpid;
	
	/**
	 * 书籍名称
	 */
	private String name;

	/**
	 * CP方管理的具有唯一性的书籍id
	 */
	private String cpbid;
	/**
	 * 小说完结状态 1：连载中 2：完结 3：停止更新
	 */
	private Integer bookstatus;

	/**
	 * 书籍作者名称
	 */
	private String author;
	
	/**
	 * 频道1:男频,2:女频
	 */
	private Integer channeltype;
	/**
	 * CP中文名称
	 */
	private String cpname;
	/**
	 * 书籍简介
	 */
	private String intro;

	/**
	 * 书籍封面图片地址
	 */
	private String coverimg;
	/**
	 * 书籍类别参考附录图书类型
	 */
	private Integer classify;
	/**
	 * 书籍关键字
	 */
	private String keywords;
	/**
	 * 总章节数
	 */
	private Integer chaptercount;
	/**
	 * 小说字数
	 */
	private Integer words;
	/**
	 * 书籍计费类型1:免费,2:整本收费,3:章节收费
	 */
	private Integer chargemode;
	/**
	 * 书籍计费价格,对应整本价格或单章价格
	 */
	private Integer price;
	/**
	 * 计费点,起始收费章节
	 */
	private Integer feechapter;
	/**
	 * 创建时间
	 */
	private Date createtime;
	/**
	 * 更新时间
	 */
	private Date updatetime;
	private String subhead;
    private String tags;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
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
		this.name = name;
	}
	public String getCpbid() {
		return cpbid;
	}
	public void setCpbid(String cpbid) {
		this.cpbid = cpbid;
	}
	public Integer getBookstatus() {
		return bookstatus;
	}
	public void setBookstatus(Integer bookstatus) {
		this.bookstatus = bookstatus;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Integer getChanneltype() {
		return channeltype;
	}
	public void setChanneltype(Integer channeltype) {
		this.channeltype = channeltype;
	}
	public String getCpname() {
		return cpname;
	}
	public void setCpname(String cpname) {
		this.cpname = cpname;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getCoverimg() {
		return coverimg;
	}
	public void setCoverimg(String coverimg) {
		this.coverimg = coverimg;
	}
	public Integer getClassify() {
		return classify;
	}
	public void setClassify(Integer classify) {
		this.classify = classify;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public Integer getChaptercount() {
		return chaptercount;
	}
	public void setChaptercount(Integer chaptercount) {
		this.chaptercount = chaptercount;
	}
	public Integer getWords() {
		return words;
	}
	public void setWords(Integer words) {
		this.words = words;
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
	public String getChaptersurl() {
		return chaptersurl;
	}
	public void setChaptersurl(String chaptersurl) {
		this.chaptersurl = chaptersurl;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getSubhead() {
		return subhead;
	}
	public void setSubhead(String subhead) {
		this.subhead = subhead;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	
}
