<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/WEB-INF/views/include/global.jsp"%>
<title>章节信息</title>
</head>
<body>
	<fieldset class="layui-elem-field layui-field-title"
		style="margin-top: 20px;">
		<legend>图书列表</legend>
	</fieldset>
	<table class="layui-table">
		<thead>
			<tr>
				<th>Id</th>
				<th>名称</th>
				<th>章节字数</th>
				<th>审核状态</th>
				<th>审核意见</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${mcpChapters!=null&&fn:length(mcpChapters)>0}">
				<c:forEach items="${mcpChapters}" var="mcpChapterDto">
					<tr>
						<td>${mcpChapterDto.id}</td>
						<td><a class="chapter"
							href="<%=path%>/mcpchapter/detail?chapterId=${mcpChapterDto.id}">${mcpChapterDto.name}</a></td>
						<td>${mcpChapterDto.words}</td>
						<td>${mcpChapterDto.auditstatusDes}</td>
						<td>${mcpChapterDto.auditinfo}</td>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
</body>
</html>