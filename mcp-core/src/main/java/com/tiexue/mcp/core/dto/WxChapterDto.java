package com.tiexue.mcp.core.dto;


public class WxChapterDto {
	//章节Id
    private Integer id;
    //对应的小说Id
    private Integer bookid;
    //章节号
    private Integer sortorder;
    //章节名称
    private String title;
    //章节类型
    private Integer chaptertype;
    //章节类型名称
    private String chaptertypeName;
    //章节价格
    private Integer pirce;
    //章节状态
    private Integer status;
    //章节字节		
    private Integer contentlen;
    //章节类型
    private Integer showtype;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public Integer getSortorder() {
        return sortorder;
    }

    public void setSortorder(Integer sortorder) {
        this.sortorder = sortorder;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getChaptertype() {
        return chaptertype;
    }

    public void setChaptertype(Integer chaptertype) {
        this.chaptertype = chaptertype;
    }

    public Integer getPirce() {
        return pirce;
    }

    public void setPirce(Integer pirce) {
        this.pirce = pirce;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getContentlen() {
        return contentlen;
    }

    public void setContentlen(Integer contentlen) {
        this.contentlen = contentlen;
    }

	public String getChaptertypeName() {
		return chaptertypeName;
	}

	public void setChaptertypeName(String chaptertypeName) {
		this.chaptertypeName = chaptertypeName;
	}

	public Integer getShowtype() {
		return showtype;
	}

	public void setShowtype(Integer showtype) {
		this.showtype = showtype;
	}



}
