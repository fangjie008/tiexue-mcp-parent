package com.tiexue.mcp.core.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.tiexue.mcp.core.entity.WxPresent;

/**
 * 赠送小说币
 * @author 
 *
 */
public interface WxPresentMapper {
    @Delete({
        "delete from wxpresent",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into wxpresent (Id, UserId, ",
        "Coin, GetTime)",
        "values (#{id,jdbcType=INTEGER}, #{userid,jdbcType=INTEGER}, ",
        "#{coin,jdbcType=INTEGER}, #{gettime,jdbcType=TIMESTAMP})"
    })
    int insert(WxPresent record);

    int insertSelective(WxPresent record);

    @Select({
        "select",
        "Id, UserId, Coin, GetTime",
        "from wxpresent",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    WxPresent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WxPresent record);

    @Update({
        "update wxpresent",
        "set UserId = #{userid,jdbcType=INTEGER},",
          "Coin = #{coin,jdbcType=INTEGER},",
          "GetTime = #{gettime,jdbcType=TIMESTAMP}",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(WxPresent record);
    
    
    @Select({
        "select",
        "Id, UserId, Coin, GetTime",
        "from wxpresent",
        "where UserId =${userId} and GetTime>'${startDt}'and GetTime<'${endDt}' "
    })
    @ResultMap("BaseResultMap")
    WxPresent getModelByGetTime(@Param("userId")Integer userId,@Param("startDt")String startDt,@Param("endDt")String endDt);
}