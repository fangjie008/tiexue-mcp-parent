<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增用户</title>
<link rel="stylesheet" type="text/css" href="/js/jquery-easyui-1.4/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="/js/jquery-easyui-1.4/themes/icon.css">
<script type="text/javascript" src="/js/jquery-easyui-1.4/jquery.min.js"></script>
<script type="text/javascript" src="/js/jquery-easyui-1.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/jquery-easyui-1.4/locale/easyui-lang-zh_CN.js"></script>
</head>
<script type="text/javascript">
	$(function() {
		alert("5");
	});
</script>
<body>
	<form id="userForm" action="doAddUser" method="post">
		<table cellpadding="5">
			<tr>
				<td>姓名:</td>
				<td><input type="text" name="username"></input></td>
			</tr>
			<tr>
				<td>密码:</td>
				<td><input type="text" name="password"></input></td>
			</tr>
			<tr>
				<td>介绍:</td>
				<td><input type="text" name="intro"></input></td>
			</tr>
			<tr>
				<td>角色权限:</td>
				<td><input type="text" name="roles"></input></td>
			</tr>
			<tr>
				<td><input id="submit" type="submit" value="提交" /></td>
			</tr>
		</table>
	</form>
</body>
</html>