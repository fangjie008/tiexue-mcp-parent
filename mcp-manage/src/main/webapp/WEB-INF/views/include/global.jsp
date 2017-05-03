<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
%>
<%--path--%>
<c:set var="path" value="${path}" />

<meta charset="UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="<%=path%>/static/frame/layui/css/layui.css">
<link rel="stylesheet" href="<%=path%>/static/css/style.css">
<link rel="stylesheet" href="<%=path%>/static/frame/layui/css/modules/layer/default/layer.css?v=3.0.3303">
<link rel="icon" href="<%=path%>/static/image/code.png">
<script type="text/javascript" src="<%=path%>/static/frame/layui/layui.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/jquery/jquery-1.10.2.min.js"></script>
