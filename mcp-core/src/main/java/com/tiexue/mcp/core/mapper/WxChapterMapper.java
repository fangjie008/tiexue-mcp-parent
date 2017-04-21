package com.tiexue.mcp.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.tiexue.mcp.core.entity.WxChapter;

public interface WxChapterMapper {
    @Delete({
        "delete from wxchapter",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into wxchapter (Id, BookId, ",
        "Intro, SortOrder, ",
        "Title, ChapterType, ",
        "Pirce, Status, ContentLen, ",
        "CreateTime, UpdateTime, ",
        "Remark,UniqueFlag)",
        "values (#{id,jdbcType=INTEGER}, #{bookid,jdbcType=INTEGER}, ",
        "#{intro,jdbcType=VARCHAR}, #{sortorder,jdbcType=INTEGER}, ",
        "#{title,jdbcType=VARCHAR}, #{chaptertype,jdbcType=INTEGER}, ",
        "#{pirce,jdbcType=INTEGER}, #{status,jdbcType=INTEGER}, #{contentlen,jdbcType=INTEGER}, ",
        "#{createtime,jdbcType=TIMESTAMP}, #{updatetime,jdbcType=TIMESTAMP}, ",
        "#{remark,jdbcType=VARCHAR},#{uniqueflag,jdbcType=VARCHAR})"
    })
    int insert(WxChapter record);

    int insertSelective(WxChapter record);

    @Select({
        "select",
        "Id, BookId, Intro, SortOrder, Title, ChapterType, Pirce, Status, ContentLen, ",
        "CreateTime, UpdateTime, Remark,UniqueFlag",
        "from wxchapter",
        "where Id = #{id,jdbcType=INTEGER} and Status=#{status}"
    })
    @ResultMap("BaseResultMap")
    WxChapter selectByPrimaryKey(@Param("id") Integer id,@Param("status")Integer status);
    
    @Select({"select",
        "Id, BookId, SortOrder, Title, ChapterType, Pirce,ContentLen ",
        "from wxchapter",
        "where BookId = #{bookId,jdbcType=INTEGER} and Status=#{status}",
        " order by SortOrder asc,Id asc  LIMIT #{pageNo},#{pageSize}"})
    @ResultMap("ResultListMap")
    List<WxChapter> selectByBookId(@Param("bookId")Integer bookId,@Param("status")Integer status,@Param("pageNo")Integer pageNo,@Param("pageSize")Integer pageSize);

    int updateByPrimaryKeySelective(WxChapter record);

    @Update({
        "update wxchapter",
        "set BookId = #{bookid,jdbcType=INTEGER},",
          "Intro = #{intro,jdbcType=VARCHAR},",
          "SortOrder = #{sortorder,jdbcType=INTEGER},",
          "Title = #{title,jdbcType=VARCHAR},",
          "ChapterType = #{chaptertype,jdbcType=INTEGER},",
          "Pirce = #{pirce,jdbcType=INTEGER},",
          "Status = #{status,jdbcType=INTEGER},",
          "ContentLen = #{contentlen,jdbcType=INTEGER},",
          "CreateTime = #{createtime,jdbcType=TIMESTAMP},",
          "UpdateTime = #{updatetime,jdbcType=TIMESTAMP},",
          "Remark = #{remark,jdbcType=VARCHAR},",
          "UniqueFlag = #{uniqueflag,jdbcType=VARCHAR},",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(WxChapter record);

    //获取章节总数
    @Select({" select count(1) from wxchapter where BookId=#{bookId} and Status=#{status}"})
    int getCountByBookId(@Param("bookId")Integer bookId,@Param("status")Integer status);
    
    
    //获取第一章内容
    @Select({"select  ",
        "Id, BookId, SortOrder, Title, ChapterType, Pirce,ContentLen ",
        "from wxchapter",
        "where bookId=#{bookId} and Status=#{status}",
        " order by SortOrder asc,Id asc  LIMIT 0,1 "})
    @ResultMap("ResultListMap")
    WxChapter getFirstChapter(@Param("bookId")Integer bookId,@Param("status")Integer status);
    
    //获取上一章内容
    @Select({"select  ",
        "Id, BookId, SortOrder, Title, ChapterType, Pirce,ContentLen ",
        "from wxchapter",
        "where bookId=#{bookId} and SortOrder < #{chapterId,jdbcType=INTEGER} and Status=#{status}",
        " order by SortOrder desc,Id desc  LIMIT 0,1 "})
    @ResultMap("ResultListMap")
    WxChapter getPreChapter(@Param("bookId")Integer bookId,@Param("chapterId")Integer chapterId,@Param("status")Integer status);
    
    //获取下一章内容
    @Select({"select ",
        "Id, BookId, SortOrder, Title, ChapterType, Pirce,ContentLen ",
        "from wxchapter",
        "where bookId=#{bookId} and SortOrder > #{chapterId,jdbcType=INTEGER} and Status=#{status}",
        " order by SortOrder asc,Id asc    LIMIT 0,1 "})
    @ResultMap("ResultListMap")
    WxChapter getNextChapter(@Param("bookId")Integer bookId,@Param("chapterId")Integer chapterId,@Param("status")Integer status);
    
    //获取最新一章内容
    @Select({"select  ",
        "Id, BookId, SortOrder, Title, ChapterType, Pirce,ContentLen ",
        "from wxchapter",
        "where bookId=#{bookId} and Status=#{status}",
        " order by SortOrder desc,Id desc   LIMIT 0,1 "})
    @ResultMap("ResultListMap")
    WxChapter getLastChapter(@Param("bookId")Integer bookId,@Param("status")Integer status);
}