<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/views/include/global.jsp" %>
    <title>新增基础信息</title>
</head>
<body class="body">
<fieldset class="layui-elem-field layui-field-title">
	<legend>
		<span class="layui-breadcrumb">
		    <a href="javascript:;">基础信息</a>
            <a><cite>新增</cite></a>
		</span>
	</legend>
</fieldset>
<form class="layui-form layui-form-pane" action="">
	<div class="layui-form-item">
		<label class="layui-form-label"><span style="color:red">*&nbsp;</span>合作者名称
		</label>
		 <div class="layui-input-block">
            <input type="text" name="name" autocomplete="off" placeholder="请输入合作者名称" lay-verify="required"
                   class="layui-input">
        </div>
	</div>
	<div class="layui-form-item">
		<label class='layui-form-label'>联系人姓名</label>
		<div class="layui-input-block">
			<input type="text" name="contName"  autocomplete="off" placeholder="请输入联系人姓名" lay-verify="required" class="layui-input">
		</div>
	</div>
</form>
<script type="text/javascript">
    layui.use(['element','layer'], function(){
        var $ = layui.jquery,element = layui.element,layer = layui.layer;

    });
</script>
</body>
</html>