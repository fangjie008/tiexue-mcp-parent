package com.tiexue.mcp.task.entity;

import java.util.Date;

public class TaskChapter {

	/**
	 * 书籍url地址
	 */
	private String url;
	/**
	 * 书籍id
	 */
	private String cpbookid;
	
	/**
	 * 章节ID
	 */
	private String cpchapterid;
	/**
	 * 章节名称.如果有卷,那么将卷名附加到最前面
	 */
	private String name;
	/**
	 * 章节字数
	 */
	private String words;

	/**
	 * 书籍在自己平台的id
	 */
	private String bookid;

	/**
	 * 书籍名称
	 */
	private String bookname;

	/**
	 * 是否vip章节
	 */
	private String isvip;
	/**
	 * 价格,单章订阅时的订阅价格
	 */
	private String price;

	/**
	 * 章节最后更新时间
	 */
	private String updatetime;
	/**
	 * 章节正文MD5摘要
	 */
	private String md5;
	/**
	 * 章节内容
	 */
	private String content;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWords() {
		return words;
	}
	public void setWords(String words) {
		this.words = words;
	}
	public String getBookid() {
		return bookid;
	}
	public void setBookid(String bookid) {
		this.bookid = bookid;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getIsvip() {
		return isvip;
	}
	public void setIsvip(String isvip) {
		this.isvip = isvip;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
