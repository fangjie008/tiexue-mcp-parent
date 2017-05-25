package com.tiexue.mcp.core.mapper;

import com.tiexue.mcp.core.entity.McpBook;
import com.tiexue.mcp.core.entity.WxBook;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface McpBookMapper {
    @Delete({
        "delete from McpBook",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into McpBook (Id, CPId, ",
        "CPName, CPBId, Name, ",
        "Subhead, Author, ",
        "ChannelType, Classify, ",
        "Tags, KeyWords, ",
        "Actors, BookStatus, ",
        "CoverImg, Intro, ",
        "PublishTime, Words, ",
        "ChapterCount, UpdateTime, ",
        "PutawayTime, ChargeMode, ",
        "Price, FeeChapter, ",
        "PutAwayStatus, AuditStatus, ",
        "AuditInfo, CreateTime)",
        "values (#{id,jdbcType=INTEGER}, #{cpid,jdbcType=INTEGER}, ",
        "#{cpname,jdbcType=VARCHAR}, #{cpbid,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, ",
        "#{subhead,jdbcType=VARCHAR}, #{author,jdbcType=VARCHAR}, ",
        "#{channeltype,jdbcType=INTEGER}, #{classify,jdbcType=INTEGER}, ",
        "#{tags,jdbcType=VARCHAR}, #{keywords,jdbcType=VARCHAR}, ",
        "#{actors,jdbcType=VARCHAR}, #{bookstatus,jdbcType=INTEGER}, ",
        "#{coverimg,jdbcType=VARCHAR}, #{intro,jdbcType=VARCHAR}, ",
        "#{publishtime,jdbcType=TIMESTAMP}, #{words,jdbcType=INTEGER}, ",
        "#{chaptercount,jdbcType=INTEGER}, #{updatetime,jdbcType=TIMESTAMP}, ",
        "#{putawaytime,jdbcType=TIMESTAMP}, #{chargemode,jdbcType=INTEGER}, ",
        "#{price,jdbcType=INTEGER}, #{feechapter,jdbcType=INTEGER}, ",
        "#{putawaystatus,jdbcType=INTEGER}, #{auditstatus,jdbcType=INTEGER}, ",
        "#{auditinfo,jdbcType=VARCHAR}, #{createtime,jdbcType=TIMESTAMP})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "Id")
    int insert(McpBook record);

    int insertSelective(McpBook record);

    @Select({
        "select",
        "Id, CPId, CPName, CPBId, Name, Subhead, Author, ChannelType, Classify, Tags, ",
        "KeyWords, Actors, BookStatus, CoverImg, Intro, PublishTime, Words, ChapterCount, ",
        "UpdateTime, PutawayTime, ChargeMode, Price, FeeChapter, PutAwayStatus, AuditStatus, ",
        "AuditInfo, CreateTime",
        "from McpBook",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    McpBook selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "Id, CPId, CPName, CPBId, Name, Subhead, Author, ChannelType, Classify, Tags, ",
        "KeyWords, Actors, BookStatus, CoverImg, Intro, PublishTime, Words, ChapterCount, ",
        "UpdateTime, PutawayTime, ChargeMode, Price, FeeChapter, PutAwayStatus, AuditStatus, ",
        "AuditInfo, CreateTime",
        "from McpBook",
        "where CPId = ${CPId} limit ${pStart},${pSize}"
    })
    @ResultMap("BaseResultMap")
    List<McpBook> getList(@Param("CPId")Integer cpId,@Param("pStart")Integer pStart,@Param("pSize")Integer pSize);
    
    int updateByPrimaryKeySelective(McpBook record);

    @Update({
        "update McpBook",
        "set CPId = #{cpid,jdbcType=INTEGER},",
          "CPName = #{cpname,jdbcType=VARCHAR},",
          "CPBId = #{cpbid,jdbcType=VARCHAR},",
          "Name = #{name,jdbcType=VARCHAR},",
          "Subhead = #{subhead,jdbcType=VARCHAR},",
          "Author = #{author,jdbcType=VARCHAR},",
          "ChannelType = #{channeltype,jdbcType=INTEGER},",
          "Classify = #{classify,jdbcType=INTEGER},",
          "Tags = #{tags,jdbcType=VARCHAR},",
          "KeyWords = #{keywords,jdbcType=VARCHAR},",
          "Actors = #{actors,jdbcType=VARCHAR},",
          "BookStatus = #{bookstatus,jdbcType=INTEGER},",
          "CoverImg = #{coverimg,jdbcType=VARCHAR},",
          "Intro = #{intro,jdbcType=VARCHAR},",
          "PublishTime = #{publishtime,jdbcType=TIMESTAMP},",
          "Words = #{words,jdbcType=INTEGER},",
          "ChapterCount = #{chaptercount,jdbcType=INTEGER},",
          "UpdateTime = #{updatetime,jdbcType=TIMESTAMP},",
          "PutawayTime = #{putawaytime,jdbcType=TIMESTAMP},",
          "ChargeMode = #{chargemode,jdbcType=INTEGER},",
          "Price = #{price,jdbcType=INTEGER},",
          "FeeChapter = #{feechapter,jdbcType=INTEGER},",
          "PutAwayStatus = #{putawaystatus,jdbcType=INTEGER},",
          "AuditStatus = #{auditstatus,jdbcType=INTEGER},",
          "AuditInfo = #{auditinfo,jdbcType=VARCHAR},",
          "CreateTime = #{createtime,jdbcType=TIMESTAMP}",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(McpBook record);
    
    
    @Select({"select count(1) from McpBook where ${strWhere}"})
    int getCount(@Param("strWhere")String strWhere);
    
    
    @Select({
        "select",
        "Id, CPId, CPName, CPBId, Name, Subhead, Author, ChannelType, Classify, Tags, ",
        "KeyWords, Actors, BookStatus, CoverImg, Intro, PublishTime, Words, ChapterCount, ",
        "UpdateTime, PutawayTime, ChargeMode, Price, FeeChapter, PutAwayStatus, AuditStatus, ",
        "AuditInfo, CreateTime",
        "from McpBook",
        "where CPBId = #{CPBId}"
    })
    @ResultMap("BaseResultMap")
    McpBook selectByCpBId(@Param("cpBId")String cpBId);
}