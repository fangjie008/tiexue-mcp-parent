package com.tiexue.mcp.task.entity;

import java.util.Date;

public class TaskChapter {

	/**
	 * 章节url地址
	 */
	private String chapterUrl;
	/**
	 * 章节名称.如果有卷,那么将卷名附加到最前面
	 */
	private String name;
	/**
	 * 章节内容
	 */
	private String content;
	
	/**
	 * 书籍在自己平台的id
	 */
	private Integer bookid;

	/**
	 * 书籍名称
	 */
	private String bookname;
		
	/**
	 * 书籍在自己平台的合作者id
	 */
    private Integer cpid;	
	/**
	 * 章节排序
	 */
	private Integer order;
	/**
	 * 书籍id
	 */
	private String cpbookid;
	/**
	 * 章节ID
	 */
	private String cpchapterid;

	/**
	 * 章节字数
	 */
	private Integer words;
	
	/**
	 * 章节最后更新时间
	 */
	private Date updatetime;
	/**
	 * 创建时间
	 */
    private Date createtime;
	
	/**
	 * 是否vip章节
	 */
	private Integer isvip;
	/**
	 * 价格,单章订阅时的订阅价格
	 */
	private Integer price;
	/**
	 * 章节正文MD5摘要
	 */
	private String md5;

	private String uniqueflag;
	
	public String getChapterUrl() {
		return chapterUrl;
	}
	public void setChapterUrl(String chapterUrl) {
		this.chapterUrl = chapterUrl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
		this.bookname = bookname;
	}
	public Integer getCpid() {
		return cpid;
	}
	public void setCpid(Integer cpid) {
		this.cpid = cpid;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public String getCpbookid() {
		return cpbookid;
	}
	public void setCpbookid(String cpbookid) {
		this.cpbookid = cpbookid;
	}
	public String getCpchapterid() {
		return cpchapterid;
	}
	public void setCpchapterid(String cpchapterid) {
		this.cpchapterid = cpchapterid;
	}
	public Integer getWords() {
		return words;
	}
	public void setWords(Integer words) {
		this.words = words;
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
	public Integer getIsvip() {
		return isvip;
	}
	public void setIsvip(Integer isvip) {
		this.isvip = isvip;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public String getUniqueflag() {
		return uniqueflag;
	}
	public void setUniqueflag(String uniqueflag) {
		this.uniqueflag = uniqueflag;
	}

	

}
