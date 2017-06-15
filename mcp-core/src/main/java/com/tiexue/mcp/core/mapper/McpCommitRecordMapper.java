package com.tiexue.mcp.core.mapper;

import com.tiexue.mcp.core.entity.McpCommitRecord;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface McpCommitRecordMapper {
    @Delete({
        "delete from McpCommitRecord",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into McpCommitRecord (Id, CommitStatus, ",
        "CommitTime, CreateTime)",
        "values (#{id,jdbcType=INTEGER}, #{commitstatus,jdbcType=BIT}, ",
        "#{committime,jdbcType=TIMESTAMP}, #{createtime,jdbcType=TIMESTAMP})"
    })
    int insert(McpCommitRecord record);

    int insertSelective(McpCommitRecord record);

    @Select({
        "select",
        "Id, CommitStatus, CommitTime, CreateTime",
        "from McpCommitRecord",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    McpCommitRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(McpCommitRecord record);

    @Update({
        "update McpCommitRecord",
        "set CommitStatus = #{commitstatus,jdbcType=BIT},",
          "CommitTime = #{committime,jdbcType=TIMESTAMP},",
          "CreateTime = #{createtime,jdbcType=TIMESTAMP}",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(McpCommitRecord record);
    
    
    @Select({
        "select",
        "Id, CommitStatus, CommitTime, CreateTime",
        "from McpCommitRecord",
        "where CommitStatus=1 order by CommitTime desc limit 0,1"
    })
    @ResultMap("BaseResultMap")
    McpCommitRecord getNewest();
}