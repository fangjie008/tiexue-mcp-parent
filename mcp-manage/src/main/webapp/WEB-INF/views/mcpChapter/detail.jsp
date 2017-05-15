<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/views/include/global.jsp"%>
<title>章节详情信息</title>
</head>
<body class="body">
	<fieldset class="layui-elem-field layui-field-title">
		<legend>
			<span class="layui-breadcrumb"> <a href="javascript:;">章节详情信息</a>
				<a><cite>章节详情信息</cite></a>
			</span>
		</legend>
	</fieldset>

	<form class="layui-form" method="post">
		<c:if test="${mcpChapter!=null}">
			<div class="layui-form-item">
				<label class="layui-form-label">章节id</label>
				<div class="layui-input-inline">
					<input type="text" name="id" value="${mcpChapter.id}"
						readonly="true" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">章节名称</label>
				<div class="layui-input-inline">
					<input type="text" name="id" value="${mcpChapter.name}"
						readonly="true" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">章节内容</label>
				<div class="layui-input-block">
					<textarea placeholder="章节内容" class="layui-textarea">${mcpChapter.content}</textarea>
				</div>
			</div>
			<div class="layui-form-item">
	            <div class="layui-input-block">
	            	<button type="reset" onclick="javascript:history.go(-1);" class="layui-btn layui-btn-primary btn-back">返回</button>
	            </div>
            </div>
		</c:if>
	</form>
</body>
</html>