<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/views/include/global.jsp"%>
<title>图书详情信息</title>
</head>
<body class="body">

	<fieldset class="layui-elem-field layui-field-title">
		<legend>
			<span class="layui-breadcrumb"> <a href="javascript:;">基础信息</a>
				<a><cite>图书详情信息</cite></a>
			</span>
		</legend>
	</fieldset>

	<form class="layui-form" method="post">
		<c:if test="${mcpBook!=null}">
			<div class="layui-form-item">
				<label class="layui-form-label">封面图</label>
				<div class="layui-input-inline">
					<img src="${mcpBook.coverimg}" height="100" width="80"/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">Id</label>
				<div class="layui-input-inline">
					<input type="text" name="id" value="${mcpBook.id}"
						readonly="true" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">书名</label>
				<div class="layui-input-inline">
					<input type="text" name="name" value="${mcpBook.name}"
						readonly="true" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">作者</label>
				<div class="layui-input-inline">
					<input type="text" name="author" value="${mcpBook.author}"
						readonly="true" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">关键词</label>
				<div class="layui-input-inline">
					<input type="text" name="keywords" value="${mcpBook.keywords}"
						readonly="true" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">tags</label>
				<div class="layui-input-inline">
					<input type="text" name="tags" value="${mcpBook.tags}"
						readonly="true" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">合作者名称</label>
				<div class="layui-input-inline">
					<input type="text" name="cpname" value="${mcpBook.cpname}"
						readonly="true" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">图书状态</label>
				<div class="layui-input-inline">
					<input type="text" name="bookstatus" value="${mcpBook.bookstatusDes}"
						readonly="true" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">图书状态</label>
				<div class="layui-input-inline">
					<input type="text" name="bookstatus" value="${mcpBook.bookstatusDes}"
						readonly="true" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">图书介绍</label>
				<div class="layui-input-inline">
					<input type="text" name="intro" value="${mcpBook.intro}"
						readonly="true" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">总字数</label>
				<div class="layui-input-inline">
					<input type="text" name="words" value="${mcpBook.words}"
						readonly="true" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">更新时间</label>
				<div class="layui-input-inline">
					<input type="text" name="updatetime" value="${mcpBook.updatetime}"
						readonly="true" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">上架状态</label>
				<div class="layui-input-inline">
					<input type="text" name="putawaystatus" value="${mcpBook.putawaystatusDes}"
						readonly="true" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">审核状态</label>
				<div class="layui-input-inline">
					<input type="text" name="auditstatus" value="${mcpBook.auditstatusDes}"
						readonly="true" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">审核信息</label>
				<div class="layui-input-inline">
					<input type="text" name="auditinfo" value="${mcpBook.auditinfo}"
						readonly="true" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">创建时间</label>
				<div class="layui-input-inline">
					<input type="text" name="createtime" value="${mcpBook.createtime}"
						readonly="true" class="layui-input">
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