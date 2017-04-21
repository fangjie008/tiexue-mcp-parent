package com.tiexue.mcp.core.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.tiexue.mcp.core.entity.WxComment;

public interface WxCommentMapper {
    @Delete({
        "delete from wxcomment",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into wxcomment (Id, BookId, ",
        "BookName, ChapterId, ",
        "ChapterTitle, Status, ",
        "UserId, UserName, ",
        "UserIcon, CreateTime, ",
        "Content)",
        "values (#{id,jdbcType=INTEGER}, #{bookid,jdbcType=INTEGER}, ",
        "#{bookname,jdbcType=VARCHAR}, #{chapterid,jdbcType=INTEGER}, ",
        "#{chaptertitle,jdbcType=VARCHAR}, #{status,jdbcType=INTEGER}, ",
        "#{userid,jdbcType=INTEGER}, #{username,jdbcType=VARCHAR}, ",
        "#{usericon,jdbcType=VARCHAR}, #{createtime,jdbcType=TIMESTAMP}, ",
        "#{content,jdbcType=LONGVARCHAR})"
    })
    int insert(WxComment record);

    int insertSelective(WxComment record);

    @Select({
        "select",
        "Id, BookId, BookName, ChapterId, ChapterTitle, Status, UserId, UserName, UserIcon, ",
        "CreateTime, Content",
        "from wxcomment",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("ResultMapWithBLOBs")
    WxComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WxComment record);

    @Update({
        "update wxcomment",
        "set BookId = #{bookid,jdbcType=INTEGER},",
          "BookName = #{bookname,jdbcType=VARCHAR},",
          "ChapterId = #{chapterid,jdbcType=INTEGER},",
          "ChapterTitle = #{chaptertitle,jdbcType=VARCHAR},",
          "Status = #{status,jdbcType=INTEGER},",
          "UserId = #{userid,jdbcType=INTEGER},",
          "UserName = #{username,jdbcType=VARCHAR},",
          "UserIcon = #{usericon,jdbcType=VARCHAR},",
          "CreateTime = #{createtime,jdbcType=TIMESTAMP},",
          "Content = #{content,jdbcType=LONGVARCHAR}",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(WxComment record);

    @Update({
        "update wxcomment",
        "set BookId = #{bookid,jdbcType=INTEGER},",
          "BookName = #{bookname,jdbcType=VARCHAR},",
          "ChapterId = #{chapterid,jdbcType=INTEGER},",
          "ChapterTitle = #{chaptertitle,jdbcType=VARCHAR},",
          "Status = #{status,jdbcType=INTEGER},",
          "UserId = #{userid,jdbcType=INTEGER},",
          "UserName = #{username,jdbcType=VARCHAR},",
          "UserIcon = #{usericon,jdbcType=VARCHAR},",
          "CreateTime = #{createtime,jdbcType=TIMESTAMP}",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(WxComment record);
}