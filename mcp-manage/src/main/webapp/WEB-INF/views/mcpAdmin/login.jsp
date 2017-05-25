<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
	String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
	request.setAttribute("error", error);
	System.out.println(error);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@ include file="/WEB-INF/views/include/global.jsp"%>
<link rel="stylesheet" href="<%=path%>/static/mcp/css/login.css" />
<title>登录</title>
<style>
.error {
	color: red;
}
</style>
</head>
<body>
	<h4>Login Page</h4>
	<div class="error">${error}</div>
	<form action="doLogin" method="post">
		用户名:<input type="text" id="username" name="username" value="<shiro:principal/>" /><br/>
		密码:<input type="password" id="password" name="password" /><br/>
		<input type="submit" value="登录">
	</form>
</body>
</html>
