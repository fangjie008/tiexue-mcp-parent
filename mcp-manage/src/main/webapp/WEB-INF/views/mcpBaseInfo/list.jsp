<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
	<%@ include file="/WEB-INF/views/include/global.jsp" %>
    <title>基础信息</title>
</head>
<body class="body">
<fieldset class="layui-elem-field layui-field-title">
	<legend>
		<span class="layui-breadcrumb">
		 <a href="javascript:;">基础信息</a>
            <a><cite>查看</cite></a>
		</span>
	</legend>
</fieldset>

<div class="my-btn-box">
<span class="f1">
	<a class="layui-btn btn-add btn-default"  href="<%=path%>/mcpbaseinfo/add">新增基础信息</a>
</span>
</div>
<table id="dateTable" class="layui-table">
    <thead>
    <tr>
        <th><input type="checkbox" class="my-checkbox" /></th>
        <th>合作者名称</th>
        <th>联系人姓名</th>
        <th>联系人邮箱</th>
        <th>联系人QQ</th>
        <th>联系人电话</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${baseInfos}" var="info">
    <tr>
        <td><input type="checkbox" class="my-checkbox" data-id="1" /></td>
        <td>${info.name}</td>
        <td>${info.contname}</td>
        <td>${info.contemail}</td>
        <td>${info.contqq}</td>
        <td>${info.contphone}</td>
        <td>
            <button class="layui-btn layui-btn-small layui-btn-normal" onclick="edit('${info.cpid}')">编辑</button>
            <button class="layui-btn layui-btn-small layui-btn-danger" onclick="deleteBaseInfo('${info.cpid}')">删除</button>
        </td>
    </tr>
    </c:forEach>
    </tbody>
</table>

<script type="text/javascript">
    layui.use(['element','layer'], function(){
        var $ = layui.jquery,element = layui.element,layer = layui.layer;

    });
    function show(cpid){
    	location.href='<%=path%>/mcpbaseinfo/show?cpid='+cpid;
    }
    function edit(cpid){
    	location.href='<%=path%>/mcpbaseinfo/add?cpid='+cpid;
    }
    function deleteBaseInfo(cpid){
    	$.ajax({
    		url:'<%=path%>/mcpbaseinfo/deleteModel?cpid='+cpid
    	    ,type:"post"
    	    ,dataType:"json"
    	    ,success:function(data){
    	    	if(data.ok){
        			alert(data.msg);
        			location.href = '<%=path%>/mcpbaseinfo/list';
        		}else{
        			alert(data.msg);
        		}
    	    },
    	    error:function(e){
    	    	alert('保存失败');
    	    }
    				
    	});
    }
</script>
</body>
</html>