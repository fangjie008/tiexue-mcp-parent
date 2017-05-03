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
	<p>图书管理1</p>

	<ul class="menu">
		<c:if test="${mcpBooksDtos==null||fn:length(mcpBooksDtos)<=0}">
			<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
		</c:if>
		<c:forEach items="${mcpBooks}" var="mcpBooksDto">
			<li><a class="chapter"
				href="<%=path%>/mcpbook/detail?bookId=${mcpBooksDto.id}">${mcpBooksDto.name}</a>
				<span class="fn-right c999">${mcpBooksDto.author}</span> <span
				class="fn-right c999">${mcpBooksDto.bookstatus}</span> <span
				class="fn-right c999">${mcpBooksDto.chargemode}</span> <span
				class="fn-right c999">${mcpBooksDto.chaptercount}</span> <span
				class="fn-right c999">${mcpBooksDto.bookstatus}</span> <span
				class="fn-right c999">${mcpBooksDto.auditstatus}</span> <span
				class="fn-right c999">${mcpBooksDto.auditinfo}</span> <span
				class="fn-right c999">${mcpBooksDto.putawaystatus}</span></li>
		</c:forEach>

	</ul>


</body>
</html>