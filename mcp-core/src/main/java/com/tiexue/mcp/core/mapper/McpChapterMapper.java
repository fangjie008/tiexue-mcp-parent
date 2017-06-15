package com.tiexue.mcp.core.mapper;

import com.tiexue.mcp.core.entity.McpBook;
import com.tiexue.mcp.core.entity.McpChapter;
import com.tiexue.mcp.core.entity.WxBook;
import com.tiexue.mcp.core.entity.WxChapter;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface McpChapterMapper {
    @Delete({
        "delete from McpChapter",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into McpChapter (Id, Name, ",
        "Words, BookId, BookName, ",
        "AuditStatus, AuditInfo, ",
        "CPId, CPBookId, ",
        "CPChapterId, `Order`, ",
        "UpdateTime, CreateTime, ",
        "IsVip, Price, Md5, ",
        "Content,UniqueFlag)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{words,jdbcType=INTEGER}, #{bookid,jdbcType=INTEGER}, #{bookname,jdbcType=VARCHAR}, ",
        "#{auditstatus,jdbcType=INTEGER}, #{auditinfo,jdbcType=VARCHAR}, ",
        "#{cpid,jdbcType=INTEGER}, #{cpbookid,jdbcType=VARCHAR}, ",
        "#{cpchapterid,jdbcType=VARCHAR}, #{order,jdbcType=INTEGER}, ",
        "#{updatetime,jdbcType=TIMESTAMP}, #{createtime,jdbcType=TIMESTAMP}, ",
        "#{isvip,jdbcType=INTEGER}, #{price,jdbcType=INTEGER}, #{md5,jdbcType=VARCHAR}, ",
        "#{content,jdbcType=LONGVARCHAR},#{uniqueflag,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(McpChapter record);

    int insertSelective(McpChapter record);

    @Select({
        "select",
        "Id, Name, Words, BookId, BookName, AuditStatus, AuditInfo, CPId, CPBookId, CPChapterId, ",
        "`Order`, UpdateTime, CreateTime, IsVip, Price, Md5, Content,UniqueFlag",
        "from McpChapter",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("ResultMapWithBLOBs")
    McpChapter selectByPrimaryKey(Integer id);
    
    @Select({"select",
        "Id, Name, Words, BookId, BookName, AuditStatus, AuditInfo, CPId, CPBookId, CPChapterId, ",
        "`Order`, UpdateTime, CreateTime, IsVip, Price, Md5, Content,UniqueFlag",
        "from McpChapter",
        "where BookId = #{bookId,jdbcType=INTEGER} limit ${pStart},${pSize}"})
    @ResultMap("BaseResultMap")
    List<McpChapter> selectList(@Param("bookId")Integer bookId,@Param("pStart")Integer pStart,@Param("pSize")Integer pSize);
    
    

    int updateByPrimaryKeySelective(McpChapter record);

    @Update({
        "update McpChapter",
        "set Name = #{name,jdbcType=VARCHAR},",
          "Words = #{words,jdbcType=INTEGER},",
          "BookId = #{bookid,jdbcType=INTEGER},",
          "BookName = #{bookname,jdbcType=VARCHAR},",
          "AuditStatus = #{auditstatus,jdbcType=INTEGER},",
          "AuditInfo = #{auditinfo,jdbcType=VARCHAR},",
          "CPId = #{cpid,jdbcType=INTEGER},",
          "CPBookId = #{cpbookid,jdbcType=VARCHAR},",
          "CPChapterId = #{cpchapterid,jdbcType=VARCHAR},",
          "`Order` = #{order,jdbcType=INTEGER},",
          "UpdateTime = #{updatetime,jdbcType=TIMESTAMP},",
          "CreateTime = #{createtime,jdbcType=TIMESTAMP},",
          "IsVip = #{isvip,jdbcType=INTEGER},",
          "Price = #{price,jdbcType=INTEGER},",
          "Md5 = #{md5,jdbcType=VARCHAR},",
          "Content = #{content,jdbcType=LONGVARCHAR},",
          "UniqueFlag = #{uniqueflag,jdbcType=VARCHAR}",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(McpChapter record);

    @Update({
        "update McpChapter",
        "set Name = #{name,jdbcType=VARCHAR},",
          "Words = #{words,jdbcType=INTEGER},",
          "BookId = #{bookid,jdbcType=INTEGER},",
          "BookName = #{bookname,jdbcType=VARCHAR},",
          "AuditStatus = #{auditstatus,jdbcType=INTEGER},",
          "AuditInfo = #{auditinfo,jdbcType=VARCHAR},",
          "CPId = #{cpid,jdbcType=INTEGER},",
          "CPBookId = #{cpbookid,jdbcType=VARCHAR},",
          "CPChapterId = #{cpchapterid,jdbcType=VARCHAR},",
          "`Order` = #{order,jdbcType=INTEGER},",
          "UpdateTime = #{updatetime,jdbcType=TIMESTAMP},",
          "CreateTime = #{createtime,jdbcType=TIMESTAMP},",
          "IsVip = #{isvip,jdbcType=INTEGER},",
          "Price = #{price,jdbcType=INTEGER},",
          "Md5 = #{md5,jdbcType=VARCHAR},",
          "UniqueFlag = #{uniqueflag,jdbcType=VARCHAR}",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(McpChapter record);
    
    @Select({"select count(1) from McpChapter where ${strWhere}"})
    int getCount(@Param("strWhere")String strWhere);
    
    
    @Select({"select",
        "Id, Name, Words, BookId, BookName, AuditStatus, AuditInfo, CPId, CPBookId, CPChapterId, ",
        "`Order`, UpdateTime, CreateTime, IsVip, Price, Md5, Content,UniqueFlag",
        "from McpChapter",
        " where UniqueFlag= #{uniqueFlag} limit 0,1"})
    @ResultMap("BaseResultMap")
    McpChapter selectByCpBId(@Param("uniqueFlag")String uniqueFlag);
    
    /**
     * 获取未上架的小说的章节
     * 查找 AuditStatus(安审状态)=2(审核通过)的的章节
     * @return
     */
    @Select({
    	"select Id,BookId,UniqueFlag From McpChapter WHERE BookId=#{mcpBookId} AND AuditStatus=2",
    })
    List<McpChapter> getUnCommitChapter(@Param("mcpBookId")Integer mcpBookId);
    
    @Select({"select",
        "Id, Name, Words, BookId, BookName, AuditStatus, AuditInfo, CPId, CPBookId, CPChapterId, ",
        "`Order`, UpdateTime, CreateTime, IsVip, Price, Md5, Content,UniqueFlag",
        "from McpChapter",
        " where BookId=#{mcpBookId} AND UpdateTime> #{updateTime}"})
    /**
     * 获取已上架需要更新的章节
     * 查找最后更新时间>上架时间的的章节
     * @return
     */
    List<McpChapter> getNeedUpdateMcpChapter(@Param("mcpBookId")Integer mcpBookId,@Param("updateTime")Date updateTime);
    
    
 
    
    
   
    
    
}