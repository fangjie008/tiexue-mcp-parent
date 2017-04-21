package com.tiexue.mcp.core.dto;

import java.util.Date;

public class WxBookDto {
	// 书籍Id
	private Integer id;
	// 书籍名称
	private String name;
	// 简介
	private String intr;
	// 发布者ID
	private Integer publisherId;
	// 发布者名称
	private String publisherName;
	// 封面图
	private String coverImgs;
	// tag
	private String tag;
	// mark
	private Integer mark;
	// 排序
	private Integer sort;
	// 状态
	private String status;
	// 点击量
	private Integer viewCount;
	// 评论量
	private Integer commentCount;
	// 顶
	private Integer dingCount;
	// 踩
	private Integer caiCount;
	// 分享
	private Integer shareCount;
	// 长度
	private String contentLen;
	// 创建时间
	private String createTime;
	// 更新时间
	private String updateTime;

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
		this.name = name;
	}

	public String getIntr() {
		return this.intr;
	}

	public void setIntr(String intr) {
		this.intr = intr;
	}

	public Integer getPublisherId() {
		return this.publisherId;
	}

	public void setPublisherId(Integer publisherId) {
		this.publisherId = publisherId;
	}

	public String getPublisherName() {
		return this.publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public String getCoverImgs() {
		return this.coverImgs;
	}

	public void setCoverImgs(String coverImgs) {
		this.coverImgs = coverImgs;
	}

	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Integer getMark() {
		return this.mark;
	}

	public void setMark(Integer mark) {
		this.mark = mark;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getViewCount() {
		return this.viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	public Integer getCommentCount() {
		return this.commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Integer getDingCount() {
		return this.dingCount;
	}

	public void setDingCount(Integer dingCount) {
		this.dingCount = dingCount;
	}

	public Integer getCaiCount() {
		return this.caiCount;
	}

	public void setCaiCount(Integer caiCount) {
		this.caiCount = caiCount;
	}

	public Integer getShareCount() {
		return this.shareCount;
	}

	public void setShareCount(Integer shareCount) {
		this.shareCount = shareCount;
	}

	public String getContentLen() {
		return this.contentLen;
	}

	public void setContentLen(String contentLen) {
		this.contentLen = contentLen;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
}
