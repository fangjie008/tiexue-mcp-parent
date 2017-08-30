package com.tiexue.mcp.core.mapper;

import com.tiexue.mcp.core.entity.WxPlatformSign;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
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
        "NovelId, MoneyAll, ",
        "Remark)",
        "values (#{id,jdbcType=INTEGER}, #{sign,jdbcType=VARCHAR}, ",
        "#{platformname,jdbcType=VARCHAR}, #{createtime,jdbcType=TIMESTAMP}, ",
        "#{novelid,jdbcType=INTEGER}, #{moneyall,jdbcType=DECIMAL}, ",
        "#{remark,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(WxPlatformSign record);

    int insertSelective(WxPlatformSign record);

    @Select({
        "select",
        "Id, Sign, PlatformName, CreateTime, NovelId, MoneyAll, Remark",
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
          "MoneyAll = #{moneyall,jdbcType=DECIMAL},",
          "Remark = #{remark,jdbcType=VARCHAR}",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(WxPlatformSign record);
    
    
    @Select({
        "select",
        "Id, Sign, PlatformName, CreateTime, NovelId, MoneyAll, Remark",
        "from wxplatformsign",
        "where Sign = #{sign,jdbcType=VARCHAR}"
    })
    @ResultMap("BaseResultMap")
    WxPlatformSign getModelBySign(@Param("sign")String sign);
    
    @Select({
        "select",
        "Id, Sign, PlatformName, CreateTime, NovelId, MoneyAll, Remark",
        "from wxplatformsign",
        " where ${strWhere} order by CreateTime desc LIMIT #{pageNo},#{pageSize}"
    })
    @ResultMap("BaseResultMap")
    List<WxPlatformSign> getListByPage(@Param("strWhere")String strWhere,@Param("pageNo") int pageNo,@Param("pageSize") int pageSize);
    @Select({
        "select",
        " count(1)",
        "from wxplatformsign",
        " where ${strWhere}"
    })
    int getCount(@Param("strWhere")String strWhere);
}