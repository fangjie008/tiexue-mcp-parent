package com.tiexue.mcp.core.service;



import com.tiexue.mcp.core.entity.WxChapterSub;

public interface IWxChapterSubService {

    int insert(WxChapterSub record);

    int insertSelective(WxChapterSub record);
    
    int updateByBookId(WxChapterSub record);

    //获取章节内容
    WxChapterSub selectByChapterId(Integer id);
}
