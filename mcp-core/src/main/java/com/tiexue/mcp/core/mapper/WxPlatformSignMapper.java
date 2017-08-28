package com.tiexue.mcp.core.mapper;

import com.tiexue.mcp.core.entity.WxPlatformSign;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface WxPlatformSignMapper {
    @Delete({
        "delete from wxplatformsign",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into wxplatformsign (Id, Sign, ",
        "PlatformName, CreateTime, ",
        "NovelId, MoneyAll)",
        "values (#{id,jdbcType=INTEGER}, #{sign,jdbcType=VARCHAR}, ",
        "#{platformname,jdbcType=VARCHAR}, #{createtime,jdbcType=TIMESTAMP}, ",
        "#{novelid,jdbcType=INTEGER}, #{moneyall,jdbcType=DECIMAL})"
    })
    int insert(WxPlatformSign record);

    int insertSelective(WxPlatformSign record);

    @Select({
        "select",
        "Id, Sign, PlatformName, CreateTime, NovelId, MoneyAll",
        "from wxplatformsign",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    WxPlatformSign selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WxPlatformSign record);

    @Update({
        "update wxplatformsign",
        "set Sign = #{sign,jdbcType=VARCHAR},",
          "PlatformName = #{platformname,jdbcType=VARCHAR},",
          "CreateTime = #{createtime,jdbcType=TIMESTAMP},",
          "NovelId = #{novelid,jdbcType=INTEGER},",
          "MoneyAll = #{moneyall,jdbcType=DECIMAL}",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(WxPlatformSign record);
}