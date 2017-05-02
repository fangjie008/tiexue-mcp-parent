<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<script type="text/javascript">
    layui.use(['element','layer'], function(){
        var $ = layui.jquery,element = layui.element,layer = layui.layer;

    });
</script>
</body>
</html>