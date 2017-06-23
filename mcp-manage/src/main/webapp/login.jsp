<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
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
<body class="beg-login-bg">
	<div class="beg-login-box">
		<header>
			<h1>后台登录</h1>
			<div class="error">${error}</div>
		</header>
		<div class="beg-login-main">
			<form action="${ctx}/admin/doLogin" class="layui-form" method="post"
				id="form1">
				<div class="layui-form-item">
					<label class="beg-login-icon"> <i class="layui-icon">&#xe612;</i>
					</label> <input type="text" name="username" lay-verify="username"
						autocomplete="off" value="${username }" placeholder="这里输入登录名" class="layui-input">
				</div>
				<div class="layui-form-item">
					<label class="beg-login-icon"> <i class="layui-icon">&#xe642;</i>
					</label> <input type="password" name="password" lay-verify="password"
						autocomplete="off" value="${password }" placeholder="这里输入密码" class="layui-input">
				</div>
				<div class="layui-form-item">
					<!-- <button class="layui-btn" style="width:100%" lay-submit lay-filter="login">
                           <i class="layui-icon">&#xe650;</i>&nbsp;&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;&nbsp;
                       </button> -->
					<input type="submit" value="登录" class="layui-btn"
						style="width: 100%">
				</div>

			</form>
		</div>
		<footer>
			<p>五彩读书合作平台</p>
		</footer>
	</div>
	<script>
layui.use(['layer', 'form','jquery'], function() {
			var layer = layui.layer;
			var msg='${msg}';
			if(msg!=""&&msg!=undefined){
				// 提示信息
		        layer.msg(msg);
			}
});

$(function(){
	 //登录验证失败时跳转到首页时确保直接跳转
	 if (self != top) { 
		 parent.window.location.replace(window.location.href);  
		 }
}); 
//回车提交事件
$("body").keydown(function() {
    if (event.keyCode == "13") {//keyCode=13是回车键
    	document.form.submit(); 
    }
});   
</script>
</body>
</html>