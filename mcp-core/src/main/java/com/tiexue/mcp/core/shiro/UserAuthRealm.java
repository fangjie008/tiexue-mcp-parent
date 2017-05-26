package com.tiexue.mcp.core.shiro;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tiexue.mcp.core.contants.SessionConstants;
import com.tiexue.mcp.core.dto.McpShiroSubject;
import com.tiexue.mcp.core.entity.McpAdmin;
import com.tiexue.mcp.core.entity.McpBaseInfo;
import com.tiexue.mcp.core.service.IMcpAdminService;
import com.tiexue.mcp.core.service.IMcpBaseInfoService;

/**
 * Function:shiro权限控制. <br/>
 *
 * @author qiao
 * @date:nnn
 * @version:
 * @since:JDK 1.7
 */
@Service("userAuthRealm")
public class UserAuthRealm extends AuthorizingRealm {
	private static final Logger logger = LoggerFactory.getLogger(UserAuthRealm.class);

	// 管理员用户
	@Resource
	private IMcpAdminService mcpAdminSer;

	// 合作者用户
	@Resource
	private IMcpBaseInfoService mcpBaseInfoSer;

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用. 角色查询设置
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		String username = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

		McpAdmin userAdmin = mcpAdminSer.getByName(username);
		McpBaseInfo userCp = mcpBaseInfoSer.getModelByName(username);
		if (userAdmin == null && userCp == null) {
			throw new UnknownAccountException(); // 没找到对应的用户在管理员和合作者中都没有找到
		}

		if (userAdmin != null) {
			// 角色控制权限
			authorizationInfo.setRoles(mcpAdminSer.findRoles(username));
		} else {
			Set<String> roleCp = new HashSet<String>();
			roleCp.add("CP");
			authorizationInfo.setRoles(roleCp);
		}
		// authorizationInfo.setStringPermissions(userService.findPermissions(username));
		// System.out.println(userService.findPermissions(username));

		// McpAdmin userDO = mcpAdminSer.getUser(username);
		// Integer userId = userDO.getId();

		int userType = userAdmin == null ? 2 : 1;
		McpShiroSubject subject = new McpShiroSubject();
		subject.setMcpUserType(userType);
		subject.setMcpAdmin(userAdmin);
		subject.setMcpBaseInfo(userCp);

		/**
		 * 把principals放session中 key=userId value=principals
		 */
		// SecurityUtils.getSubject().getSession().setAttribute(String.valueOf(userDO.getId()),
		// SecurityUtils.getSubject().getPrincipals());
		return authorizationInfo;
	}

	/**
	 * 认证回调函数,登录时调用. 登录判断逻辑
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		McpAdmin userAdmin = mcpAdminSer.getByName(username);
		McpBaseInfo userCp = mcpBaseInfoSer.getModelByName(username);
		if (userAdmin == null && userCp == null) {
			throw new UnknownAccountException(); // 没找到对应的用户在管理员和合作者中都没有找到
		}
		String password = "";
		if (((UsernamePasswordToken) token).getPassword() != null) {
			password = new String(((UsernamePasswordToken) token).getPassword());
		}

		int userType = userAdmin == null ? 2 : 1;
		McpShiroSubject subject = new McpShiroSubject();
		subject.setMcpUserType(userType);
		subject.setMcpAdmin(userAdmin);
		subject.setMcpBaseInfo(userCp);

		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		session.setAttribute("user", subject);

		// if (!password.equals(subject.getPassword())) {
		// throw new UnknownAccountException();
		// }
		// UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		// ShiroUser shiroUser = new ShiroUser(user.getId(),
		// user.getUsername(), user.getUsername());
		// this.initSession(user);
		System.out.print("this is testing");

		// 用户密码验证,交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
		SimpleAuthenticationInfo authInfo = new SimpleAuthenticationInfo(subject.getUserName(), subject.getPassword(),
				ByteSource.Util.bytes(""), subject.getUserName());
		return authInfo;
	}

	// /**
	// * 设置用户session
	// */
	// private void initSession(McpAdmin user) {
	// logger.info("sessionTimeOut:" + SessionConstants.sessionTimeOut);
	// Session session = SecurityUtils.getSubject().getSession();
	// session.setTimeout(SessionConstants.sessionTimeOut);
	// session.setAttribute(SessionConstants.SESSION_USER_KEY, user);
	// }
	//
	// /**
	// * 设定Password校验.
	// */
	// @PostConstruct
	// public void initCredentialsMatcher() {
	// /**
	// * 自定义密码验证
	// */
	// setCredentialsMatcher(new CustomizedCredentialsMatcher());
	// }
	//
	// /**
	// * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
	// */
	// public static class ShiroUser implements Serializable {
	// private static final long serialVersionUID = -1373760761780840081L;
	// public Long id;
	// public String username;
	// public String name;
	//
	// public ShiroUser(Long id, String username, String name) {
	// this.id = id;
	// this.username = username;
	// this.name = name;
	// }
	//
	// public Long getId() {
	// return id;
	// }
	//
	// public String getName() {
	// return name;
	// }
	//
	// /**
	// * 本函数输出将作为默认的<shiro:principal/>输出.
	// */
	// @Override
	// public String toString() {
	// return username;
	// }
	//
	// /**
	// * 重载hashCode.
	// */
	// @Override
	// public int hashCode() {
	// return Objects.hashCode(username);
	// }
	//
	// /**
	// * 重载equals.
	// */
	// @Override
	// public boolean equals(Object obj) {
	// if (this == obj) {
	// return true;
	// }
	// if (obj == null) {
	// return false;
	// }
	// if (getClass() != obj.getClass()) {
	// return false;
	// }
	// ShiroUser other = (ShiroUser) obj;
	// if (username == null) {
	// if (other.username != null) {
	// return false;
	// }
	// } else if (!username.equals(other.username)) {
	// return false;
	// }
	// return true;
	// }
	// }

	@Override
	public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
	}

	@Override
	public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		super.clearCachedAuthenticationInfo(principals);
	}

	@Override
	public void clearCache(PrincipalCollection principals) {
		super.clearCache(principals);
	}

	public void clearAllCachedAuthorizationInfo() {
		getAuthorizationCache().clear();
	}

	public void clearAllCachedAuthenticationInfo() {
		getAuthenticationCache().clear();
	}

	public void clearAllCache() {
		this.clearAllCachedAuthenticationInfo();
		this.clearAllCachedAuthorizationInfo();
	}

}
