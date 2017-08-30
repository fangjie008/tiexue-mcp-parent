package com.tiexue.mcp.core.weixin;

import org.apache.log4j.Logger;
import org.aspectj.weaver.ast.Var;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitterReturnValueHandler;

import com.tiexue.mcp.core.cache.CacheKey;
import com.tiexue.mcp.core.cache.MemoryCache;

/**
 * 获取五彩读书网公共号信息的类
 * @author zhangxiaowei
 *
 */
public class WcWxConstant {


	/**
	 * 五彩读书网微信公众号Appid
	 */
	public static final String wxAppId="wx5e401defd89d8e75";
	/**
	 * 五彩读书网AppSecret
	 */
	public static final String wxAppSecret="3098a9d84bb99df23fe807a939b0f737";
}
