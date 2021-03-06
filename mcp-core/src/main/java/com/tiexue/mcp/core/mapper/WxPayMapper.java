package com.tiexue.mcp.core.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

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
        "Unit,Sign)",
        "values (#{ordernum,jdbcType=VARCHAR}, #{userid,jdbcType=INTEGER}, ",
        "#{openid,jdbcType=VARCHAR}, #{bookid,jdbcType=INTEGER}, ",
        "#{chapterid,jdbcType=INTEGER}, #{orderstatus,jdbcType=INTEGER}, ",
        "#{wxordernum,jdbcType=VARCHAR}, #{paychannel,jdbcType=INTEGER}, ",
        "#{paytype,jdbcType=INTEGER}, #{amount,jdbcType=INTEGER}, ",
        "#{count,jdbcType=INTEGER}, #{createtime,jdbcType=TIMESTAMP}, ",
        "#{unit,jdbcType=INTEGER},#{sign,jdbcType=VARCHAR})"
    })
    int insert(WxPay record);

    int insertSelective(WxPay record);

    @Select({
        "select",
        "OrderNum, UserId, OpenId, BookId, ChapterId, OrderStatus, WxOrderNum, PayChannel, ",
        "PayType, Amount, Count, CreateTime, Unit,Sign",
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
          "Unit = #{unit,jdbcType=INTEGER},",
          "Sign = #{sign,jdbcType=TIMESTAMP}",
        "where OrderNum = #{ordernum,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(WxPay record);
    
    @Select({"select ordernum,UserId,PayType,Amount,Count,CreateTime,Unit,OrderStatus" 
		," from wxpay"
		," where UserId=#{userId} order by CreateTime desc LIMIT #{pageNo},#{pageSize}"})
  	List<WxPay> getListByPage(@Param("userId")int userId,@Param("pageNo")int pageNo,@Param("pageSize")int pageSize);
  	
    @Select({"select count(1) from wxpay where UserId =#{userId} "})
  	Integer getCountByUserId(int userId);
    
    @Select({"select count(1) from wxpay where  UserId =#{userId}  and OrderStatus=#{orderStatus} and OrderNum<>'${orderNum}' "})
    int getPayCountByUserId(@Param("userId")int userId,@Param("orderStatus")int orderStatus,@Param("orderNum")String orderNum);
    
    
    /**
     * 查询某本书的充值金额
     * @param bookId
     * @param time
     * @return
     */
    @Select("<script>"
            + "SELECT * FROM wxpay  WHERE OrderStatus=3 and userId in "
            + "<foreach item='item' index='index' collection='userIds' open='(' separator=',' close=')'>"
                + "#{item}"
            + "</foreach>"
            +" <![CDATA[ AND (bookId=#{bookId} OR bookId=0) AND CreateTime>#{startTime} and CreateTime<#{endTime}]]>"
        + "</script>")
    List<WxPay> getPaysByBookId(@Param("bookId")int bookId,@Param("startTime")Date startTime
    		,@Param("endTime")Date endTime,@Param("userIds")List<Integer> userIds);
    
    
    @Select({"select IFNULL(SUM(Amount),0) from wxpay where OrderStatus=3 and Sign= #{sign} "})
  	Integer getTotalPayMoneyBySign(String sign);
    
    @Select({" SELECT COUNT(1) FROM ( "+
    	" SELECT userId FROM wxpay WHERE OrderStatus=3 and Sign= #{sign} GROUP BY userId ) a "})
  	Integer getPayCountBySign(String sign);
}