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
				<th>备注</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${userList}" var="user">
				<tr>
					<td>${user.name}</td>
					<td>${user.type}</td>
					<td>${user.auth}</td>
					<td>${user.intro}</td>
					<td>
						<a class="layui-btn layui-btn-small layui-btn-normal" href="${pageContext.request.contextPath}/admin/${user.id}/update">修改</a> 
						<a class="layui-btn layui-btn-small layui-btn-danger" onclick="deleteUser('${user.id}')" href="#">删除</a> 
						<a class="layui-btn layui-btn-small layui-btn-normal" href="${pageContext.request.contextPath}/admin/${user.id}/changepassword">改密</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
<script type="text/javascript">
//当前页   
var pindex=1;
    layui.use(['element','layer','laypage'], function(){
        var $ = layui.jquery,element = layui.element,layer = layui.layer;
        var laypage = layui.laypage;
    });


    function deleteUser(id){
    	$.ajax({
    		url:'<%=path%>/admin/' + id + '/del'
    	    ,type:"post"
    	    ,dataType:"json"
    	    ,success:function(data){
    	    	if(data.ok){
        			alert(data.msg);
        			location.href ='<%=path%>/admin/list';
        		}else{
        			alert(data.msg);
        			//超时
        			location.href ='<%=path%>/admin/list';
				}
    	    },
				error : function(e) {
					alert('失败');
				}
    	    
			});
		}
    
	</script>
</body>
</html>