<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>

<html lang="en">
<head>
<%@ include file="/WEB-INF/views/include/global.jsp"%>
<title>图书管理</title>
</head>
<body>
	<fieldset class="layui-elem-field layui-field-title"
		style="margin-top: 20px;">
		<legend>图书列表</legend>
	</fieldset>
	<table class="layui-table">
		<colgroup>
			<col width="150">
			<col width="150">
			<col width="200">
			<col>
		</colgroup>
		<thead>
			<tr>
				<th>Id</th>
				<th>书名</th>
				<th>作者</th>
				<th>作品状态</th>
				<th>是否收费</th>
				<th>章节数</th>
				<th>审核状态</th>
				<th>审核意见</th>
				<th>上架状态</th>
				<th>章节信息</th>
			</tr>
		</thead>
		<tbody>

			<c:if test="${mcpBooksDtos==null||fn:length(mcpBooksDtos)<=0}">
				<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
				<c:forEach items="${mcpBooks}" var="mcpBooksDto">
					<tr>
						<td>${mcpBooksDto.id}</td>
						<td><a class="chapter"
							href="<%=path%>/mcpbook/detail?bookId=${mcpBooksDto.id}">${mcpBooksDto.name}</a></td>
						<td>${mcpBooksDto.author}</td>
						<td>${mcpBooksDto.bookstatus}</td>
						<td>${mcpBooksDto.chargemode}</td>
						<td>${mcpBooksDto.chaptercount}</td>
						<td>${mcpBooksDto.bookstatus}</td>
						<td>${mcpBooksDto.auditstatus}</td>
						<td>${mcpBooksDto.auditinfo}</td>
						<td>${mcpBooksDto.putawaystatus}</td>
				</c:forEach>
			</c:if>
		</tbody>
	</table>


</body>
</html>