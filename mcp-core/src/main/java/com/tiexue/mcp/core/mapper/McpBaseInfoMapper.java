package com.tiexue.mcp.core.mapper;

import com.tiexue.mcp.core.entity.McpBaseInfo;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface McpBaseInfoMapper {
    @Delete({
        "delete from McpBaseInfo",
        "where CPId = #{cpid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer cpid);

    @Insert({
        "insert into McpBaseInfo (CPId, Name, ",
        "ContName, ContEmail, ",
        "ContQQ, ContPhone, ",
        "Address, ZipCode, ",
        "BankName, BankAccountName, ",
        "BankAccountNum, RegisterTime, ",
        "LastLoginTime, Password, ",
        "AppKey, InterfaceUrl1, ",
        "InterfaceUrl2, InterfaceUrl3, ",
        "InterfaceUrl4)",
        "values (#{cpid,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{contname,jdbcType=VARCHAR}, #{contemail,jdbcType=VARCHAR}, ",
        "#{contqq,jdbcType=VARCHAR}, #{contphone,jdbcType=VARCHAR}, ",
        "#{address,jdbcType=VARCHAR}, #{zipcode,jdbcType=VARCHAR}, ",
        "#{bankname,jdbcType=VARCHAR}, #{bankaccountname,jdbcType=VARCHAR}, ",
        "#{bankaccountnum,jdbcType=VARCHAR}, #{registertime,jdbcType=TIMESTAMP}, ",
        "#{lastlogintime,jdbcType=TIMESTAMP}, #{password,jdbcType=VARCHAR}, ",
        "#{appkey,jdbcType=VARCHAR}, #{interfaceurl1,jdbcType=VARCHAR}, ",
        "#{interfaceurl2,jdbcType=VARCHAR}, #{interfaceurl3,jdbcType=VARCHAR}, ",
        "#{interfaceurl4,jdbcType=VARCHAR})"
    })
    int insert(McpBaseInfo record);

    int insertSelective(McpBaseInfo record);

    @Select({
        "select",
        "CPId, Name, ContName, ContEmail, ContQQ, ContPhone, Address, ZipCode, BankName, ",
        "BankAccountName, BankAccountNum, RegisterTime, LastLoginTime, Password, AppKey, ",
        "InterfaceUrl1, InterfaceUrl2, InterfaceUrl3, InterfaceUrl4",
        "from McpBaseInfo",
        "where CPId = #{cpid,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    McpBaseInfo selectByPrimaryKey(Integer cpid);

    int updateByPrimaryKeySelective(McpBaseInfo record);

    @Update({
        "update McpBaseInfo",
        "set Name = #{name,jdbcType=VARCHAR},",
          "ContName = #{contname,jdbcType=VARCHAR},",
          "ContEmail = #{contemail,jdbcType=VARCHAR},",
          "ContQQ = #{contqq,jdbcType=VARCHAR},",
          "ContPhone = #{contphone,jdbcType=VARCHAR},",
          "Address = #{address,jdbcType=VARCHAR},",
          "ZipCode = #{zipcode,jdbcType=VARCHAR},",
          "BankName = #{bankname,jdbcType=VARCHAR},",
          "BankAccountName = #{bankaccountname,jdbcType=VARCHAR},",
          "BankAccountNum = #{bankaccountnum,jdbcType=VARCHAR},",
          "RegisterTime = #{registertime,jdbcType=TIMESTAMP},",
          "LastLoginTime = #{lastlogintime,jdbcType=TIMESTAMP},",
          "Password = #{password,jdbcType=VARCHAR},",
          "AppKey = #{appkey,jdbcType=VARCHAR},",
          "InterfaceUrl1 = #{interfaceurl1,jdbcType=VARCHAR},",
          "InterfaceUrl2 = #{interfaceurl2,jdbcType=VARCHAR},",
          "InterfaceUrl3 = #{interfaceurl3,jdbcType=VARCHAR},",
          "InterfaceUrl4 = #{interfaceurl4,jdbcType=VARCHAR}",
        "where CPId = #{cpid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(McpBaseInfo record);
    
    
    @Select({
        "select",
        "CPId, Name, ContName, ContEmail, ContQQ, ContPhone, Address, ZipCode, BankName, ",
        "BankAccountName, BankAccountNum, RegisterTime, LastLoginTime, Password, AppKey, ",
        "InterfaceUrl1, InterfaceUrl2, InterfaceUrl3, InterfaceUrl4",
        "from McpBaseInfo",
        "where ${strWhere} limit ${pStart},${pSize}"
    })
    @ResultMap("BaseResultMap")
    List<McpBaseInfo> getList(@Param("strWhere")String strWhere,@Param("pStart")Integer pStart,@Param("pSize")Integer pSize);
    
    @Select({"select count(1) from McpBaseInfo where ${strWhere}"})
    int getCount(@Param("strWhere")String strWhere);
    
    
    @Select({
        "select",
        "CPId, Name, ContName, ContEmail, ContQQ, ContPhone, Address, ZipCode, BankName, ",
        "BankAccountName, BankAccountNum, RegisterTime, LastLoginTime, Password, AppKey, ",
        "InterfaceUrl1, InterfaceUrl2, InterfaceUrl3, InterfaceUrl4",
        "from McpBaseInfo",
        "where Name=#{Name} limit 0,1"
    })
    @ResultMap("BaseResultMap")
    McpBaseInfo getModelByName(@Param("Name")String name);
    
    
    @Select({
        "select",
        "CPId, Name, ContName, ContEmail, ContQQ, ContPhone, Address, ZipCode, BankName, ",
        "BankAccountName, BankAccountNum, RegisterTime, LastLoginTime, Password, AppKey, ",
        "InterfaceUrl1, InterfaceUrl2, InterfaceUrl3, InterfaceUrl4",
        "from McpBaseInfo",
        "where Name=#{Name} and CPId<>#{cpid} limit 0,1"
    })
    @ResultMap("BaseResultMap")
    McpBaseInfo checkModelByName(@Param("Name")String name,@Param("cpid")int cpid);
    
    @Update({
        "update McpBaseInfo",
        " set Password = #{password,jdbcType=VARCHAR}",
        " where CPId = #{cpid,jdbcType=INTEGER}"
    })
    int updatePassword(@Param("cpid")int cpid,@Param("password")String password);
    
    @Select({
        "select",
        "CPId, Name, ContName, ContEmail, ContQQ, ContPhone, Address, ZipCode, BankName, ",
        "BankAccountName, BankAccountNum, RegisterTime, LastLoginTime, Password, AppKey, ",
        "InterfaceUrl1, InterfaceUrl2, InterfaceUrl3, InterfaceUrl4",
        "from McpBaseInfo",
        "where ${strWhere} "
    })
    @ResultMap("BaseResultMap")
    List<McpBaseInfo> getAllList(@Param("strWhere")String strWhere);
    
}