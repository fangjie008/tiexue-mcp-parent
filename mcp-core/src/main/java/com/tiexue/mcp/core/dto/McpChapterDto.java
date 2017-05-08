package com.tiexue.mcp.core.dto;

import java.util.Date;

import com.tiexue.mcp.core.entity.McpConstants;

public class McpChapterDto {
	private Integer id;

	private String name;

	private Integer words;

	private Integer bookid;

	private String bookname;

	private Integer auditstatus;
	private String auditstatusDes;

	private String auditinfo;

	private Integer cpid;

	private String cpbookid;

	private String cpchapterid;

	private Integer order;

	private Date updatetime;

	private Date createtime;

	private Integer isvip;

	private Integer price;

	private String md5;

	private String content;

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

	public Integer getWords() {
		return words;
	}

	public void setWords(Integer words) {
		this.words = words;
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

	public Integer getAuditstatus() {
		return auditstatus;
	}

	public void setAuditstatus(Integer auditstatus) {
		this.auditstatus = auditstatus;
		String auditstatusDes_T = McpConstants.AuditStatusList.get(auditstatus);
		if (auditstatusDes_T == null) {
			auditstatusDes_T = "未知状态";
		}
		setAuditstatusDes(auditstatusDes_T);
	}
	
	public String getAuditstatusDes(){
		return auditstatusDes;
	}
	public void setAuditstatusDes(String auditstatusDes){
		this.auditstatusDes = auditstatusDes;
	}
	

	public String getAuditinfo() {
		return auditinfo;
	}

	public void setAuditinfo(String auditinfo) {
		this.auditinfo = auditinfo == null ? null : auditinfo.trim();
	}

	public Integer getCpid() {
		return cpid;
	}

	public void setCpid(Integer cpid) {
		this.cpid = cpid;
	}

	public String getCpbookid() {
		return cpbookid;
	}

	public void setCpbookid(String cpbookid) {
		this.cpbookid = cpbookid == null ? null : cpbookid.trim();
	}

	public String getCpchapterid() {
		return cpchapterid;
	}

	public void setCpchapterid(String cpchapterid) {
		this.cpchapterid = cpchapterid == null ? null : cpchapterid.trim();
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
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
		this.md5 = md5 == null ? null : md5.trim();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}
}
