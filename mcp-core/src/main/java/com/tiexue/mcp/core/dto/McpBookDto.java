package com.tiexue.mcp.core.dto;

import java.util.Date;

import com.tiexue.mcp.core.entity.McpConstants;

public class McpBookDto {
	private Integer id;

	private Integer cpid;

	private String cpname;

	private String cpbid;

	private String name;

	private String subhead;

	private String author;

	private Integer channeltype;
	private String channeltypeDes;

	private Integer classify;
	private String classifyDes;

	private String tags;

	private String keywords;

	private String actors;

	private Integer bookstatus;
	private String bookstatusDes;

	private String coverimg;

	private String intro;

	private String publishtime;

	private Integer words;

	private Integer chaptercount;

	private String updatetime;

	private String putawaytime;

	private Integer chargemode;
	private String chargemodeDes;

	private Integer price;

	private Integer feechapter;

	private Integer putawaystatus;
	private String putawaystatusDes;

	private Integer auditstatus;
	private String auditstatusDes;

	private String auditinfo;

	private String createtime;

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
		String channelTypeDes_T = McpConstants.McpChannelTypeList.get(channeltype);
		if (channelTypeDes_T == null) {
			channelTypeDes_T = "频道错误";
		}
		setChanneltypeDes(channelTypeDes_T);
	}
	public String getChanneltypeDes(){
		return channeltypeDes;
	}
	public void setChanneltypeDes(String channeltypeDes){
		this.channeltypeDes = channeltypeDes;
	}
	
	
	
	public Integer getClassify() {
		return classify;
	}

	public void setClassify(Integer classify) {
		this.classify = classify;
		String classifyDes_T = McpConstants.ClassifyList.get(classify);
		if (classifyDes_T == null) {
			classifyDes_T = "未知类型";
		}
		setClassifyDes(classifyDes_T);
	}

	public String getClassifyDes(){
		return classifyDes;
	}
	public void setClassifyDes(String classifyDes){
		this.classifyDes = classifyDes;
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
		String bookstatusDes_T = McpConstants.BookStatusList.get(bookstatus);
		if (bookstatusDes_T == null) {
			bookstatusDes_T = "未知状态";
		}
		setBookstatusDes(bookstatusDes_T);
	}
	
	public String getBookstatusDes(){
		return bookstatusDes;
	}
	public void setBookstatusDes(String bookstatusDes){
		this.bookstatusDes = bookstatusDes;
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

	public String getPublishtime() {
		return publishtime;
	}

	public void setPublishtime(String publishtime) {
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

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public String getPutawaytime() {
		return putawaytime;
	}

	public void setPutawaytime(String putawaytime) {
		this.putawaytime = putawaytime;
	}

	public Integer getChargemode() {
		return chargemode;
	}

	public void setChargemode(Integer chargemode) {
		this.chargemode = chargemode;
		String chargemodeDes_T = McpConstants.ChargeModeList.get(chargemode);
		if (chargemodeDes_T == null) {
			chargemodeDes_T = "未知类型";
		}
		setChargemodeDes(chargemodeDes_T);
	}


	public String getChargemodeDes(){
		return chargemodeDes;
	}
	public void setChargemodeDes(String chargemodeDes){
		this.chargemodeDes = chargemodeDes;
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
		String putawaystatusDes_T = McpConstants.PutAwayStatusList.get(putawaystatus);
		if (putawaystatusDes_T == null) {
			putawaystatusDes_T = "未知上架状态";
		}
		setPutawaystatusDes(putawaystatusDes_T);
	}
	
	public String getPutawaystatusDes(){
		return putawaystatusDes;
	}
	public void setPutawaystatusDes(String putawaystatusDes){
		this.putawaystatusDes = putawaystatusDes;
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

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
}
