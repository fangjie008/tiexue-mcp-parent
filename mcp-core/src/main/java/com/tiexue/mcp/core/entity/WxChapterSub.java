package com.tiexue.mcp.core.entity;

public class WxChapterSub {
	//章节Id
    private Integer id;
    //章节内容
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}