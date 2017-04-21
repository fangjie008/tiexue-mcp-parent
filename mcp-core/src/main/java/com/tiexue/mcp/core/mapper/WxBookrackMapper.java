package com.tiexue.mcp.core.mapper;

import com.tiexue.mcp.core.dto.WxBookrackDto;
import com.tiexue.mcp.core.entity.WxBookrack;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface WxBookrackMapper {
    @Delete({
        "delete from wxbookrack",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into wxbookrack (Id, BookId, ",
        "BookName, ChapterId, ",
        "ChapterTitle, Location, ",
        "UserId, CreateTime)",
        "values (#{id,jdbcType=INTEGER}, #{bookid,jdbcType=INTEGER}, ",
        "#{bookname,jdbcType=VARCHAR}, #{chapterid,jdbcType=INTEGER}, ",
        "#{chaptertitle,jdbcType=VARCHAR}, #{location,jdbcType=INTEGER}, ",
        "#{userid,jdbcType=INTEGER}, #{createtime,jdbcType=TIMESTAMP})"
    })
    int insert(WxBookrack record);

    int insertSelective(WxBookrack record);

    @Select({
        "select",
        "Id, BookId, BookName, ChapterId, ChapterTitle, Location, UserId, CreateTime",
        "from wxbookrack",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    WxBookrack selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WxBookrack record);

    @Update({
        "update wxbookrack",
        "set BookId = #{bookid,jdbcType=INTEGER},",
          "BookName = #{bookname,jdbcType=VARCHAR},",
          "ChapterId = #{chapterid,jdbcType=INTEGER},",
          "ChapterTitle = #{chaptertitle,jdbcType=VARCHAR},",
          "Location = #{location,jdbcType=INTEGER},",
          "UserId = #{userid,jdbcType=INTEGER},",
          "CreateTime = #{createtime,jdbcType=TIMESTAMP}",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(WxBookrack record);
    
    
    @Select({
        "select",
        "Id, BookId, BookName, ChapterId, ChapterTitle, Location, UserId, CreateTime",
        "from wxbookrack",
        "where UserId = #{userId,jdbcType=INTEGER} and BookId = #{bookId,jdbcType=INTEGER} order by CreateTime desc LIMIT 0,1"
    })
    @ResultMap("BaseResultMap")
    WxBookrack getModelByBookId(@Param("userId")Integer userId,@Param("bookId")Integer bookId);
    
    
    @Select({
        "select",
        "Id, BookId, BookName, ChapterId, ChapterTitle, Location, UserId, CreateTime",
        "from wxbookrack",
        "where UserId = #{userId,jdbcType=INTEGER}  order by CreateTime desc LIMIT 0,1"
    })
    @ResultMap("BaseResultMap")
    WxBookrack getModelByUserId(@Param("userId")Integer userId);
    
    
    @Select({
        "select",
        "a.Id, a.BookId, a.BookName, a.ChapterId, a.ChapterTitle, a.Location, a.UserId, ",
        " b.CoverImgs,b.Intr,c.SortOrder",
        "from wxbookrack a inner join wxbook b on a.BookId=b.Id ",
        " left join wxchapter c on a.ChapterId=c.Id ",
        "where a.UserId = #{userId,jdbcType=INTEGER} order by a.CreateTime desc LIMIT 0,#{size}"
    })
    @ResultMap("joinBookResultMap")
    /**
     * 根据图书Id获取书架列表
     * @param bookId
     * @param size
     * @return
     */
    List<WxBookrackDto> getListByUserId(@Param("userId")Integer userId,@Param("size")Integer size);
    

}