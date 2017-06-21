package com.tiexue.mcp.core.service;

import java.util.List;



import com.tiexue.mcp.core.entity.WxBook;

public interface IWxBookService {
	
    int deleteByPrimaryKey(Integer id);   
    int insert(WxBook record);

    int insertSelective(WxBook record);

    WxBook selectByPrimaryKey(Integer id);
    
    List<WxBook> getList(String strWhere,String orderStr,Integer size);

    int updateByPrimaryKeySelective(WxBook record);
    int updateByPrimaryKey(WxBook record);
    
    /**
     * 根据唯一标识查询小说数据(查看小说是否存在)
     * @param uniqueFlag
     * @return
     */
    WxBook selectByUniqueFlag(String uniqueFlag);
    
    /**
     * 插入到wxBook
     * @param id
     * @param uniqueflag
     * @return
     */
    int insertToWxBook(WxBook wxBook,Integer mcpBookId,String uniqueflag);

    /**
     * 更新到wxBook
     * @param id
     * @param uniqueflag
     * @return
     */
    int updateToWxBook(Integer mcpBookId,String uniqueflag);
    
    
    /**
     * 根据合作方Id获取合作方小说
     * @param cpId
     * @return
     */
    List<WxBook> getBookIdByCPId(Integer cpId);
}
