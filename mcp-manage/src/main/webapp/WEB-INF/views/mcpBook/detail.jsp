<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div>图书详情</div>
	<ul class="menu">
		<c:if test="${mcpBook==null}">
			<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
		</c:if>

		<li><span class="fn-right c999">${mcpBook.id}</span> <span
			class="fn-right c999">${mcpBook.name}</span> <span
			class="fn-right c999">${mcpBook.author}</span> <span
			class="fn-right c999">${mcpBook.keywords}</span> <span
			class="fn-right c999">${mcpBook.tags}</span> <span
			class="fn-right c999">${mcpBook.cpname}</span> <span
			class="fn-right c999">${mcpBook.bookstatus}</span> <span
			class="fn-right c999">${mcpBook.coverimg}</span> <span
			class="fn-right c999">${mcpBook.intro}</span><span
			class="fn-right c999">${mcpBook.words}</span><span
			class="fn-right c999">${mcpBook.updatetime}</span><span
			class="fn-right c999">${mcpBook.putawaystatus}</span><span
			class="fn-right c999">${mcpBook.auditstatus}</span><span
			class="fn-right c999">${mcpBook.auditinfo}</span><span
			class="fn-right c999">${mcpBook.createtime}</span></li>
	</ul>

</body>
</html>