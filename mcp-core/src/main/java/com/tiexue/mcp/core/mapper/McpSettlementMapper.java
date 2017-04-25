package com.tiexue.mcp.core.mapper;

import com.tiexue.mcp.core.entity.McpSettlement;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface McpSettlementMapper {
    @Delete({
        "delete from McpSettlement",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into McpSettlement (Id, CPId, ",
        "Consume, Month, DivideConsume, ",
        "SettlementStatus, CreateTime, ",
        "UpdateTime)",
        "values (#{id,jdbcType=INTEGER}, #{cpid,jdbcType=INTEGER}, ",
        "#{consume,jdbcType=INTEGER}, #{month,jdbcType=DATE}, #{divideconsume,jdbcType=INTEGER}, ",
        "#{settlementstatus,jdbcType=INTEGER}, #{createtime,jdbcType=TIMESTAMP}, ",
        "#{updatetime,jdbcType=TIMESTAMP})"
    })
    int insert(McpSettlement record);

    int insertSelective(McpSettlement record);

    @Select({
        "select",
        "Id, CPId, Consume, Month, DivideConsume, SettlementStatus, CreateTime, UpdateTime",
        "from McpSettlement",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    McpSettlement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(McpSettlement record);

    @Update({
        "update McpSettlement",
        "set CPId = #{cpid,jdbcType=INTEGER},",
          "Consume = #{consume,jdbcType=INTEGER},",
          "Month = #{month,jdbcType=DATE},",
          "DivideConsume = #{divideconsume,jdbcType=INTEGER},",
          "SettlementStatus = #{settlementstatus,jdbcType=INTEGER},",
          "CreateTime = #{createtime,jdbcType=TIMESTAMP},",
          "UpdateTime = #{updatetime,jdbcType=TIMESTAMP}",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(McpSettlement record);
}