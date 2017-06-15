package com.tiexue.mcp.core.mapper;

import com.tiexue.mcp.core.entity.McpBook;
import com.tiexue.mcp.core.entity.WxBook;

import java.util.Date;
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
        "AuditInfo, CreateTime,CollectionStatus,UniqueFlag)",
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
        "#{auditinfo,jdbcType=VARCHAR}, #{createtime,jdbcType=TIMESTAMP},",
        "#{collectionstatus,jdbcType=INTEGER},#{uniqueflag,jdbcType=VARCHAR})",
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(McpBook record);

    int insertSelective(McpBook record);

    @Select({
        "select",
        "Id, CPId, CPName, CPBId, Name, Subhead, Author, ChannelType, Classify, Tags, ",
        "KeyWords, Actors, BookStatus, CoverImg, Intro, PublishTime, Words, ChapterCount, ",
        "UpdateTime, PutawayTime, ChargeMode, Price, FeeChapter, PutAwayStatus, AuditStatus, ",
        "AuditInfo, CreateTime,CollectionStatus,UniqueFlag",
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
        "AuditInfo, CreateTime,CollectionStatus,UniqueFlag",
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
          "CreateTime = #{createtime,jdbcType=TIMESTAMP},",
          "CollectionStatus = #{collectionstatus,jdbcType=INTEGER},",
          "UniqueFlag = #{uniqueflag,jdbcType=VARCHAR}",
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
        "AuditInfo, CreateTime,CollectionStatus,UniqueFlag",
        "from McpBook",
        "where UniqueFlag= #{uniqueFlag} limit 0,1"
    })
    @ResultMap("BaseResultMap")
    McpBook selectByCpBId(@Param("uniqueFlag")String uniqueFlag);
    
    @Update({
        "update McpBook",
        "set ",
        "CollectionStatus = #{collectionstatus,jdbcType=INTEGER}",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int updateCollectionStatus(@Param("id")Integer Id,@Param("collectionstatus")Integer collectionStatus);
    
    @Select({
    	"select",
        "Id, CPId, CPName, CPBId, Name, Subhead, Author, ChannelType, Classify, Tags, ",
        "KeyWords, Actors, BookStatus, CoverImg, Intro, PublishTime, Words, ChapterCount, ",
        "UpdateTime, PutawayTime, ChargeMode, Price, FeeChapter, PutAwayStatus, AuditStatus, ",
        "AuditInfo, CreateTime,CollectionStatus,UniqueFlag",
        "from McpBook",
        "where putAwayStatus=1 and AuditStatus=2  "
    		+ ""})
    /**
     * 获取未上架的小说
     * 查找 putAwayStatus(上架状态)=1(未上架)并且AuditStatus(安审状态)=2(审核通过)的小说
     * @return
     */
    List<McpBook> getUnCommitBook();
    
    @Select({
    	"select",
        "Id, CPId, CPName, CPBId, Name, Subhead, Author, ChannelType, Classify, Tags, ",
        "KeyWords, Actors, BookStatus, CoverImg, Intro, PublishTime, Words, ChapterCount, ",
        "UpdateTime, PutawayTime, ChargeMode, Price, FeeChapter, PutAwayStatus, AuditStatus, ",
        "AuditInfo, CreateTime,CollectionStatus,UniqueFlag",
        "from McpBook",
        "where putAwayStatus=2 and AuditStatus=2 and  UpdateTime>#{UpdateTime,jdbcType=TIMESTAMP} "
    		+ ""})
    /**
     * 获取已上架需要更新的小说
     * 查找 putAwayStatus(上架状态)=2(已上架)并且AuditStatus(安审状态)=2(审核通过)并且最后更新时间>上架时间的小说
     * @return
     */
    List<McpBook> getNeedUpdateMcpBook(@Param("UpdateTime")Date UpdateTime);
    
 
 
    @Update({
    	"UPDATE McpBook SET putAwayStatus=2,PutawayTime=NOW() WHERE id=#{mcpBookId,jdbcType=INTEGER};",
    })
    /**
     * 更新到wxBook
     * @param id
     * @param uniqueflag
     * @return
     */
    int updatePutAwayStatus(@Param("mcpBookId")Integer mcpBookId,@Param("uniqueflag")String uniqueflag);
    
    
    
}