package com.tiexue.mcp.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.tiexue.mcp.core.entity.WxConsume;
import com.tiexue.mcp.core.entity.WxPay;

public interface WxConsumeMapper {
    @Delete({
        "delete from wxconsume",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into wxconsume (Id, UserId, ",
        "CostCoin, BookId, ",
        "BookName, CharpterId, ",
        "CharpterTitle, CreateTime)",
        "values (#{id,jdbcType=INTEGER}, #{userid,jdbcType=INTEGER}, ",
        "#{costcoin,jdbcType=INTEGER}, #{bookid,jdbcType=INTEGER}, ",
        "#{bookname,jdbcType=VARCHAR}, #{charpterid,jdbcType=INTEGER}, ",
        "#{charptertitle,jdbcType=VARCHAR}, #{createtime,jdbcType=TIMESTAMP})"
    })
    int insert(WxConsume record);

    int insertSelective(WxConsume record);

    @Select({
        "select",
        "Id, UserId, CostCoin, BookId, BookName, CharpterId, CharpterTitle, CreateTime",
        "from wxconsume",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    WxConsume selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WxConsume record);

    @Update({
        "update wxconsume",
        "set UserId = #{userid,jdbcType=INTEGER},",
          "CostCoin = #{costcoin,jdbcType=INTEGER},",
          "BookId = #{bookid,jdbcType=INTEGER},",
          "BookName = #{bookname,jdbcType=VARCHAR},",
          "CharpterId = #{charpterid,jdbcType=INTEGER},",
          "CharpterTitle = #{charptertitle,jdbcType=VARCHAR},",
          "CreateTime = #{createtime,jdbcType=TIMESTAMP}",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(WxConsume record);
    
    @Select({
        "select",
        "Id, UserId, CostCoin, BookId, BookName, CharpterId, CharpterTitle, CreateTime",
        "from wxconsume",
        "where UserId=#{userId} order by CreateTime desc LIMIT #{pageNo},#{pageSize}"
    })
    @ResultMap("BaseResultMap")
  	List<WxConsume> getListByPage(@Param("userId")int userId,@Param("pageNo") int pageNo,@Param("pageSize") int pageSize);

    @Select({
        "select",
        " count(1) ",
        "from wxconsume",
        "where UserId =#{userId} "
    })
  	Integer getCountByUserId(int userId);
    
    
    @Select({
        "select",
        " count(1) ",
        "from wxconsume",
        "where UserId =#{userId} and CharpterId=#{charpterId} "
    })
    /**
     * 判断某个章节用户是否消费
     * @param userId
     * @param charpterId
     * @return
     */
  	Integer judgeConsume(@Param("userId")int userId,@Param("charpterId")int charpterId);
}