package com.tiexue.mcp.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.tiexue.mcp.core.entity.WxPay;

public interface WxPayMapper {
    @Delete({
        "delete from wxpay",
        "where OrderNum = #{ordernum,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String ordernum);

    @Insert({
        "insert into wxpay (OrderNum, UserId, ",
        "OpenId, BookId, ",
        "ChapterId, OrderStatus, ",
        "WxOrderNum, PayChannel, ",
        "PayType, Amount, ",
        "Count, CreateTime, ",
        "Unit)",
        "values (#{ordernum,jdbcType=VARCHAR}, #{userid,jdbcType=INTEGER}, ",
        "#{openid,jdbcType=VARCHAR}, #{bookid,jdbcType=INTEGER}, ",
        "#{chapterid,jdbcType=INTEGER}, #{orderstatus,jdbcType=INTEGER}, ",
        "#{wxordernum,jdbcType=VARCHAR}, #{paychannel,jdbcType=INTEGER}, ",
        "#{paytype,jdbcType=INTEGER}, #{amount,jdbcType=INTEGER}, ",
        "#{count,jdbcType=INTEGER}, #{createtime,jdbcType=TIMESTAMP}, ",
        "#{unit,jdbcType=INTEGER})"
    })
    int insert(WxPay record);

    int insertSelective(WxPay record);

    @Select({
        "select",
        "OrderNum, UserId, OpenId, BookId, ChapterId, OrderStatus, WxOrderNum, PayChannel, ",
        "PayType, Amount, Count, CreateTime, Unit",
        "from wxpay",
        "where OrderNum = #{ordernum,jdbcType=VARCHAR}"
    })
    @ResultMap("BaseResultMap")
    WxPay selectByPrimaryKey(String ordernum);

    int updateByPrimaryKeySelective(WxPay record);

    @Update({
        "update wxpay",
        "set UserId = #{userid,jdbcType=INTEGER},",
          "OpenId = #{openid,jdbcType=VARCHAR},",
          "BookId = #{bookid,jdbcType=INTEGER},",
          "ChapterId = #{chapterid,jdbcType=INTEGER},",
          "OrderStatus = #{orderstatus,jdbcType=INTEGER},",
          "WxOrderNum = #{wxordernum,jdbcType=VARCHAR},",
          "PayChannel = #{paychannel,jdbcType=INTEGER},",
          "PayType = #{paytype,jdbcType=INTEGER},",
          "Amount = #{amount,jdbcType=INTEGER},",
          "Count = #{count,jdbcType=INTEGER},",
          "CreateTime = #{createtime,jdbcType=TIMESTAMP},",
          "Unit = #{unit,jdbcType=INTEGER}",
        "where OrderNum = #{ordernum,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(WxPay record);
    
    @Select({"select ordernum,UserId,PayType,Amount,Count,CreateTime,Unit,OrderStatus" 
		," from wxpay"
		," where UserId=#{userId} order by CreateTime desc LIMIT #{pageNo},#{pageSize}"})
  	List<WxPay> getListByPage(@Param("userId")int userId,@Param("pageNo")int pageNo,@Param("pageSize")int pageSize);
  	
    @Select({"select count(1) from wxpay where UserId =#{userId} "})
  	Integer getCountByUserId(int userId);
    
    @Select({"select count(1) from wxpay where  UserId =#{userId} and CreateTime>'2017-03-28 17:19' and OrderStatus=#{orderStatus} and OrderNum<>'${orderNum}' "})
    int getPayCountByUserId(@Param("userId")int userId,@Param("orderStatus")int orderStatus,@Param("orderNum")String orderNum);
}