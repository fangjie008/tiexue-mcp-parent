package com.tiexue.mcp.core.mapper;

import com.tiexue.mcp.core.dto.WxBookrackDto;
import com.tiexue.mcp.core.entity.WxBook;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface WxBookMapper {
    @Delete({
        "delete from wxbook",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into wxbook (Id, Name, ",
        "Intr, PublisherId, ",
        "PublisherName, CoverImgs, ",
        "Tag, Mark, Sort, ",
        "Status, ViewCount, ",
        "CommentCount, DingCount, ",
        "CaiCount, ShareCount, ",
        "ContentLen, CreateTime, ",
        "UpdateTime,UniqueFlag,CollectionId)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{intr,jdbcType=VARCHAR}, #{publisherid,jdbcType=INTEGER}, ",
        "#{publishername,jdbcType=VARCHAR}, #{coverimgs,jdbcType=VARCHAR}, ",
        "#{tag,jdbcType=VARCHAR}, #{mark,jdbcType=INTEGER}, #{sort,jdbcType=INTEGER}, ",
        "#{status,jdbcType=INTEGER}, #{viewcount,jdbcType=INTEGER}, ",
        "#{commentcount,jdbcType=INTEGER}, #{dingcount,jdbcType=INTEGER}, ",
        "#{caicount,jdbcType=INTEGER}, #{sharecount,jdbcType=INTEGER}, ",
        "#{contentlen,jdbcType=INTEGER}, #{createtime,jdbcType=TIMESTAMP}, ",
        "#{updatetime,jdbcType=TIMESTAMP},#{uniqueflag,jdbcType=VARCHAR},#{collectionid,jdbcType=INTEGER})"
    })
    int insert(WxBook record);

    int insertSelective(WxBook record);

    @Select({
        "select",
        "Id, Name, Intr, PublisherId, PublisherName, CoverImgs, Tag, Mark, Sort, Status, ",
        "ViewCount, CommentCount, DingCount, CaiCount, ShareCount, ContentLen, CreateTime, ",
        "UpdateTime,UniqueFlag,CollectionId",
        "from wxbook",
        "where Id = #{id}"
    })
    @ResultMap("BaseResultMap")
    WxBook selectByPrimaryKey(Integer id);
    
    @Select({
        "select",
        "Id, Name, Intr, PublisherId, PublisherName, CoverImgs, Tag, Mark, Sort, Status, ",
        "ViewCount, CommentCount, DingCount, CaiCount, ShareCount, ContentLen, CreateTime, ",
        "UpdateTime,UniqueFlag,CollectionId",
        "from wxbook",
        "where UniqueFlag = #{uniqueFlag} limit 0,1"
    })
    @ResultMap("BaseResultMap")
    WxBook selectByUniqueFlag(String uniqueFlag);
    
    @Select({
        "select",
        "Id, Name, Intr, PublisherId, PublisherName, CoverImgs, Tag, Mark, Sort, Status, ",
        "ViewCount, CommentCount, DingCount, CaiCount, ShareCount, ContentLen, CreateTime, ",
        "UpdateTime,UniqueFlag,CollectionId",
        "from wxbook",
        "where ${strWhere} order by ViewCount desc  LIMIT 0,#{size} "
    })
    @ResultMap("BaseResultMap")
    List<WxBook> getList(@Param("strWhere")String strWhere,@Param("orderStr")String orderStr,@Param("size")Integer size);

    int updateByPrimaryKeySelective(WxBook record);

    @Update({
        "update wxbook",
        "set Name = #{name,jdbcType=VARCHAR},",
          "Intr = #{intr,jdbcType=VARCHAR},",
          "PublisherId = #{publisherid,jdbcType=INTEGER},",
          "PublisherName = #{publishername,jdbcType=VARCHAR},",
          "CoverImgs = #{coverimgs,jdbcType=VARCHAR},",
          "Tag = #{tag,jdbcType=VARCHAR},",
          "Mark = #{mark,jdbcType=INTEGER},",
          "Sort = #{sort,jdbcType=INTEGER},",
          "Status = #{status,jdbcType=INTEGER},",
          "ViewCount = #{viewcount,jdbcType=INTEGER},",
          "CommentCount = #{commentcount,jdbcType=INTEGER},",
          "DingCount = #{dingcount,jdbcType=INTEGER},",
          "CaiCount = #{caicount,jdbcType=INTEGER},",
          "ShareCount = #{sharecount,jdbcType=INTEGER},",
          "ContentLen = #{contentlen,jdbcType=INTEGER},",
          "CreateTime = #{createtime,jdbcType=TIMESTAMP},",
          "UpdateTime = #{updatetime,jdbcType=TIMESTAMP},",
          "UniqueFlag = #{uniqueflag,jdbcType=VARCHAR},",
          "CollectionId = #{collectionid,jdbcType=INTEGER}",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(WxBook record);
    
    /**
     * 插入到wxBook
     * @param id
     * @param uniqueflag
     * @return
     */
    @Insert({
    	"INSERT INTO wxbook(`NAME`,Intr,PublisherId,PublisherName,CoverImgs,Tag,Mark,Sort,`STATUS`,ViewCount",
    	",CommentCount,DingCount,CaiCount,ShareCount,ContentLen,CreateTime,UpdateTime,UniqueFlag,CollectionId) ",
    	"SELECT `NAME`,Intro,0,Author,CoverImg,tags,0,0,BookStatus,0,0,0,0,0",
    	",Words,NOW(),UpdateTime,UniqueFlag,CPBId",
    	"FROM McpBook WHERE  id=#{mcpBookId,jdbcType=INTEGER} AND  NOT EXISTS ",
    	"(SELECT * FROM wxbook WHERE UniqueFlag = #{uniqueflag,jdbcType=VARCHAR});",
    })
    @Options(useGeneratedKeys=true,keyProperty="wxBook.id")
    int insertToWxBook(@Param("wxBook")WxBook wxBook,@Param("mcpBookId")Integer mcpBookId,@Param("uniqueflag")String uniqueflag);
    @Update({
    	"UPDATE wxbook a INNER JOIN McpBook b ON a.UniqueFlag=b.UniqueFlag ",
		"SET ",
		"a.`Name`=b.`Name`,",
		"a.Intr=b.Intro,",
		"a.UpdateTime=b.UpdateTime,",
		"a.CoverImgs=b.CoverImg,",
		"a.ContentLen=b.Words",
		"WHERE b.id=#{mcpBookId,jdbcType=INTEGER} ",
    })
    /**
     * 更新到wxBook
     * @param id
     * @param uniqueflag
     * @return
     */
    int updateToWxBook(@Param("mcpBookId")Integer mcpBookId,@Param("uniqueflag")String uniqueflag);
    
    /**
     * 根据合作方Id获取合作方小说
     * @param cpId
     * @return
     */
    @Select({
    	" SELECT  Id, Name, Intr, PublisherId, PublisherName, CoverImgs, Tag, Mark, Sort, Status, ",
        "ViewCount, CommentCount, DingCount, CaiCount, ShareCount, ContentLen, CreateTime, ",
        "UpdateTime,UniqueFlag,CollectionId  FROM wxbook WHERE uniqueflag IN",
    	"  (SELECT UniqueFlag FROM McpBook WHERE CPId=#{CPId}); ",
    })
    List<WxBook> getBookIdByCPId(@Param("CPId")Integer cpId);
}