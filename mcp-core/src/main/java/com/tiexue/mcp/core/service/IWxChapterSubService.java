package com.tiexue.mcp.core.service;





import com.tiexue.mcp.core.entity.WxChapterSub;

public interface IWxChapterSubService {

    int insert(WxChapterSub record);

    int insertSelective(WxChapterSub record);
    
    int updateByBookId(WxChapterSub record);

    //获取章节内容
    WxChapterSub selectByChapterId(Integer id);
    
    /**
     * 插入到wxChapterSub
     * @param id
     * @param uniqueflag
     * @return
     */
    int insertToWxChapterSub(Integer mcpChapterId,int wxChapterId);  
    /**
     * 根据bookId批量章节内容
     * @param id
     * @param uniqueflag
     * @return
     */
    int updateToWxChapterSub(Integer wxChapterId,String uniqueflag);
}
