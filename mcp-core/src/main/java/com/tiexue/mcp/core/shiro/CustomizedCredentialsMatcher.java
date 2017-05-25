package com.tiexue.mcp.core.shiro;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import com.tiexue.mcp.base.util.Md5Utils;

/**
 * Function:shrio密码校验扩展类. <br/>
 * Date: 2016年5月26日 下午5:59:08 <br/>
 * 
 * @author weiguo.liu
 * @version
 * @since JDK 1.7
 * @see
 */
public class CustomizedCredentialsMatcher extends HashedCredentialsMatcher {// SimpleCredentialsMatcher
																			// {
	// @Override
	// public boolean doCredentialsMatch(AuthenticationToken authcToken,
	// AuthenticationInfo info) {
	// UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
	// Object tokenCredentials = encrypt(String.valueOf(token.getPassword()));
	// Object accountCredentials = getCredentials(info);
	// // 将密码加密与系统加密后的密码校验，内容一致就返回true,不一致就返回false
	// return equals(tokenCredentials, accountCredentials);
	// }
	//
	// /**
	// * 将传进来密码加密方法
	// *
	// * @param data
	// * @return
	// */
	// private String encrypt(String data) {
	// String encryData;
	// encryData = Md5Utils.getStringMD5(data);
	//
	// return encryData;
	// }
	//
	private Cache<String, AtomicInteger> passwordRetryCache;

	public CustomizedCredentialsMatcher(CacheManager cacheManager) {
		passwordRetryCache = cacheManager.getCache("passwordRetryCache");
	}

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		String username = (String) token.getPrincipal();
		// retry count + 1
		AtomicInteger retryCount = passwordRetryCache.get(username);
		if (retryCount == null) {
			retryCount = new AtomicInteger(0);
			passwordRetryCache.put(username, retryCount);
		}
		if (retryCount.incrementAndGet() > 5) {
			// if retry count > 5 throw
			throw new ExcessiveAttemptsException();
		}

		boolean matches = super.doCredentialsMatch(token, info);
		if (matches) {
			// clear retry count
			passwordRetryCache.remove(username);
		}
		return matches;
	}

}
