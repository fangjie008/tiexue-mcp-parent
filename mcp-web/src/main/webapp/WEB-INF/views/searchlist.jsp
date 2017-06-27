<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/WEB-INF/views/include/include_base.jsp"%>

<title>首页</title>
</head>

<body>
<header class="nav wrap">
 <a class="ico52 back" href="javascript:history.go(-1);"></a>
 搜索小说<a href="<%=path%>/?fm=${fromurl}" class="ico52 home"></a>
</header>


<div class="mod_block"></div>
<form method="post" action="<%=path%>/wxbook/searchlist">
		<div class="searchbox">
			<div class="searchd1">
				<input type="text" class="searchtxt" placeholder="输入书名/作者/关键字..."
					id="iptsearch" name="iptsearch" value="${iptsearch}">
			</div>
			<div class="searchd2">
				<input type="submit" class="searchbtn" value="">
			</div>
		</div>
</form>
<c:choose>
<c:when test="${iptsearch!=null&&iptsearch!=''}">
	<c:choose>
		<c:when test="${wxSearchBooks!=null&&wxSearchBooks.size()>0 }">
			<div class="mod_content">
			<ul>
			<c:forEach items="${wxSearchBooks}" var="books">
		      <li>
		        
		      	<a onclick="addbookrack('${books.id}','0')" href="<%=path%>/wxbook/detail?id=${books.id}&fm=${fromurl}"> 
		      	<img class="fn-left lazy" src="${books.coverImgs}" dataimg="${books.coverImgs}" alt="${books.name}">
				<div>
					<p>${books.name}</p>
					<p class="intro">${books.getIntr()}</p>
				</div>
				</a> 
		      </li>
		     </c:forEach>
				</ul>
			</div>
		</c:when>
		<c:otherwise>
			<div class="mod_content">
			<ul>
				<li>没有搜索到 ${iptsearch} 相关小说</li>
				</ul>
			</div>
			</c:otherwise>
	</c:choose>
</c:when>
<c:otherwise>
<div class="mod_title">
			<h1 class="f17">
				<i class="home-icon-tit home-icon-tit-b"></i> 热门搜索
			</h1>
		</div>
<div class="mod_content">
	<ul>
		<c:forEach items="${wxBooks}" var="books">
	      <li>
	        
	      	<a onclick="addbookrack('${books.id}','0')" href="<%=path%>/wxbook/detail?id=${books.id}&fm=${fromurl}"> 
	      	<img class="fn-left lazy" src="${books.coverImgs}" dataimg="${books.coverImgs}" alt="${books.name}">
			<div>
				<p>${books.name}</p>
				<p class="intro">${books.getIntr()}</p>
			</div>
			</a> 
	      </li>
	     </c:forEach>
	</ul>
</div>
</c:otherwise>
</c:choose>

<div class="mod_block"></div>

<!-- <div>
<a href="#" onclick="testaddcookie()">测试登录</a>
</div> -->

<div style="background-color: rgba(50, 201, 186, 0.7);padding: 8px 10px;">
	<a style="color:white" href="http://t.cn/R6LUaeS">
		点击关注公众号“<span style="color:#2897ed;border-bottom:1px solid">五彩读书网</span>”方便下次继续阅读
	</a>
</div>

<%@ include file="/WEB-INF/views/include/include_footer.jsp"%>
</body>
<script type="text/javascript" src="<%=path %>/static/js/public.js"></script>
<script type="text/javascript">
function testaddcookie(){
	setCookie("wx_gzh_token","9519B0A907BB60F8ACADA7184CA0EEA5C984F95C9E7BE0956612C4F3B65B0F0239DB04E0BFC5BB36","1")
}
</script>
</html>

