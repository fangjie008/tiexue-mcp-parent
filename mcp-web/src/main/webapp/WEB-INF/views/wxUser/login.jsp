<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/WEB-INF/views/include/include_base.jsp"%>
<title>用户登录</title>
</head>

<body>
<header class="nav wrap">
	<a class="ico52 back" href="javascript:history.go(-1);"></a>用户登录<a href="<%=path%>/?fm=${fromurl}" class="ico52 home"></a>
</header>
<div class="panel">
	
	<div class="other_login">
        <div class="dsflogin"><span>免注册快速登录</span></div>
		<ul>
			<li><a class="btn block btn_login" href="<%=path%>/wxUser/wxlogindo/?fm=${fromurl}"><span class="bgs login_wxpng"></span>微信登录</a></li>
		</ul>
		<br>
	</div>
</div>
<div class="mod_content c1">
	<ul>
        <li class="orange"><strong>温馨提示：</strong></li>
		<li>微信和手机方式登录的账号相互独立，各账号内的小说币和小说豆不能共用！</li>       
		
	</ul>
</div>
<%@ include file="/WEB-INF/views/include/include_footer.jsp"%>
</body>
</html>
