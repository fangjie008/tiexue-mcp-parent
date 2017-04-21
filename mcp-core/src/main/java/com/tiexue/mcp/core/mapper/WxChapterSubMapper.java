package com.tiexue.mcp.core.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.tiexue.mcp.core.entity.WxChapterSub;

public interface WxChapterSubMapper {
    @Insert({
        "insert into wxchaptersub (Id, Content)",
        "values (#{id,jdbcType=INTEGER}, #{content,jdbcType=LONGVARCHAR})"
    })
    int insert(WxChapterSub record);

    int insertSelective(WxChapterSub record);
    
    @Update({" update wxchaptersub set ",
    	" Content=#{content,jdbcType=LONGVARCHAR} ",
    	" where id=#{id,jdbcType=INTEGER}"})
    int updateByBookId(WxChapterSub record);
    
    //获取章节内容
    @Select({" select Id,Content from wxchaptersub where id=#{id}"})
    @ResultMap("AllResultMap")
    WxChapterSub selectByChapterId(@Param("id")Integer id);
}