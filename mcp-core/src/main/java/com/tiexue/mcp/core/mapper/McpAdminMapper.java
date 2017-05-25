package com.tiexue.mcp.core.mapper;

import com.tiexue.mcp.core.entity.McpAdmin;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface McpAdminMapper {
    @Delete({
        "delete from McpAdmin",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into McpAdmin (Name, ",
        "Password, Intro, ",
        "Type, Auth)",
        "values (#{name,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR}, #{intro,jdbcType=VARCHAR}, ",
        "#{type,jdbcType=INTEGER}, #{auth,jdbcType=VARCHAR})"
    })
    int insert(McpAdmin record);

    int insertSelective(McpAdmin record);

    @Select({
        "select",
        "Id, Name, Password, Intro, Type, Auth",
        "from McpAdmin",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    McpAdmin selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "Id, Name, Password, Intro, Type, Auth",
        "from McpAdmin",
        "where Name = #{name,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    McpAdmin selectByName(String name);
    
    
    @Select({
    	"select",
    	"Id, Name, Password, Intro, Type, Auth",
        "from McpAdmin",
    	})
    @ResultMap("BaseResultMap")
    List<McpAdmin> selectList();
    
    int updateByPrimaryKeySelective(McpAdmin record);

    @Update({
        "update McpAdmin",
        "set Name = #{name,jdbcType=VARCHAR},",
          "Password = #{password,jdbcType=VARCHAR},",
          "Intro = #{intro,jdbcType=VARCHAR},",
          "Type = #{type,jdbcType=INTEGER},",
          "Auth = #{auth,jdbcType=VARCHAR}",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(McpAdmin record);
    
    
    
    
}