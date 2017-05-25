<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<%@ include file="/WEB-INF/views/include/global.jsp"%>
<title>用户管理</title>
</head>
<body class="body">

	<fieldset class="layui-elem-field layui-field-title">
		<legend>
			<span class="layui-breadcrumb"> <a href="javascript:;">用户管理</a> <a><cite>查看</cite></a>
			</span>
		</legend>
	</fieldset>

	<shiro:hasRole name="admin">
		<div class="my-btn-box">
			<span class="f1"> <a class="layui-btn btn-add btn-default" href="${ctx}/admin/add">新增用户</a>
			</span>
		</div>
	</shiro:hasRole>

	<table id="dateTable" class="layui-table">
		<thead>
			<tr>
				<th>用户名</th>
				<th>类型(1:管理员,2:权限用户)</th>
				<th>角色列表</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${userList}" var="user">
				<tr>
					<td>${user.name}</td>
					<td>${user.type}</td>
					<td>${user.auth}</td>
					<td>
						<a class="layui-btn layui-btn-small layui-btn-normal" href="${pageContext.request.contextPath}/admin/${user.id}/update">修改</a> 
						<a class="layui-btn layui-btn-small layui-btn-danger" href="${pageContext.request.contextPath}/admin/${user.id}/del">删除</a> 
						<a class="layui-btn layui-btn-small layui-btn-normal" href="${pageContext.request.contextPath}/admin/${user.id}/changePassword">改密</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>