package com.tiexue.mcp.core.shiro;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tiexue.mcp.core.entity.McpAdmin;

import weixin.popular.bean.user.User;

/**
 * <p>
 * User: Zhang Kaitao
 * <p>
 * Date: 14-1-28
 * <p>
 * Version: 1.0
 */
@Service
public class PasswordHelper {

	private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

	// @Value("${password.algorithmName}")
	private String algorithmName = "md5";
	// @Value("${password.hashIterations}")
	private int hashIterations = 2;

	public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
		this.randomNumberGenerator = randomNumberGenerator;
	}

	public void setAlgorithmName(String algorithmName) {
		this.algorithmName = algorithmName;
	}

	public void setHashIterations(int hashIterations) {
		this.hashIterations = hashIterations;
	}

	public void encryptPassword(McpAdmin user) {

		// user.setSalt(randomNumberGenerator.nextBytes().toHex());

		String newPassword = new SimpleHash(algorithmName, user.getPassword(), ByteSource.Util.bytes(""),
				hashIterations).toHex();

		user.setPassword(newPassword);
	}

	/**
	 * 密码加密
	 **/
	public String encryptPassword(String password) {
		String newPassword = new SimpleHash(algorithmName, password, ByteSource.Util.bytes(""), hashIterations).toHex();
		return newPassword;
	}
}
