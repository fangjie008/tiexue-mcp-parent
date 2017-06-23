package com.tiexue.mcp.core.mapper;

import com.tiexue.mcp.core.entity.McpSettlement;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface McpSettlementMapper {
    @Delete({
        "delete from McpSettlement",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * 这么写为了防止重复插入
     * @param record
     * @return
     */
    @Insert({
        "insert into McpSettlement (Id, CPId, ",
        "Consume, Month, DivideConsume, ",
        "SettlementStatus, CreateTime, ",
        "UpdateTime)",
        "SELECT #{id,jdbcType=INTEGER}, #{cpid,jdbcType=INTEGER}, ",
        "#{consume,jdbcType=INTEGER}, #{month,jdbcType=VARCHAR}, #{divideconsume,jdbcType=INTEGER}, ",
        "#{settlementstatus,jdbcType=INTEGER}, #{createtime,jdbcType=TIMESTAMP}, ",
        "#{updatetime,jdbcType=TIMESTAMP} FROM DUAL ",
        " WHERE NOT EXISTS(SELECT Id FROM McpSettlement WHERE CPId=#{cpid,jdbcType=INTEGER} and Month=#{month,jdbcType=VARCHAR} );"
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
          "Month = #{month,jdbcType=VARCHAR},",
          "DivideConsume = #{divideconsume,jdbcType=INTEGER},",
          "SettlementStatus = #{settlementstatus,jdbcType=INTEGER},",
          "CreateTime = #{createtime,jdbcType=TIMESTAMP},",
          "UpdateTime = #{updatetime,jdbcType=TIMESTAMP}",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(McpSettlement record);
    
    /**
     * 获取结算数据
     * @param cpId
     * @param monthly
     * @return
     */
    @Select({
        "select",
        "Id, CPId, Consume, Month, DivideConsume, SettlementStatus, CreateTime, UpdateTime",
        "from McpSettlement",
        "where CPId = #{cpId,jdbcType=INTEGER} and Month=#{monthly,jdbcType=VARCHAR} limit 0,1"
    })
    @ResultMap("BaseResultMap")
    McpSettlement getData(@Param("cpId")int cpId,@Param("monthly")String monthly);
    
    
}