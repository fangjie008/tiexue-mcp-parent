<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/WEB-INF/views/include/include_base.jsp"%>
<title>书架</title>
</head>

<body>
<header class="nav wrap">
	<a class="ico52 back" href="javascript:history.go(-1);"></a>我的书架<a href="<%=path%>/?fm=${fromurl}" class="ico52 home"></a>
</header>
<div class="panel-bar">
<ul class="btn_area fn-clear">
<!-- 		<li><a class="btn  block " href="#">最近阅读</a></li>
		<li><a class="btn white block" href="#">收藏书架</a></li> -->
	</ul>
</div>
<div class="mod_tab_content shelf">
	<ul class="current">
	
	<c:forEach items="${bookracks}" var="racks">
			  <li id="${racks.getBookid() }">
		        <a href="<%=path%>/wxbook/detail?id=${racks.getBookid()}&fm=${fromurl}">
		      	<img  class="fn-left lazy" alt="" src="${racks.getCoverimgs()}">
		      	</a>
		      	<a  class="jxread" href="<%=path%>/wxbook/detail?id=${racks.getBookid()}">
		      	  <div>
		      	    <p class="nowrap">${racks.getBookname()}</p>
                    <p class="small nowrap">最后更新：<label>${racks.lastchaptertitle}</label></p>
                    <c:if test="${racks.chapterid==null||racks.chapterid<=0}">
                      <a href="<%=path %>/wxChapterSub/defualt?bookId=${racks.getBookid()}&fm=${fromurl}">
                    <p class="small nowrap">阅读进度：<label class="ccode">${racks.chaptertitle}<br>继续阅读</label></p>
                    </a>
                    </c:if>
                   <c:if test="${racks.chapterid>0}">
                      <a href="<%=path %>/wxChapterSub/index?bookId=${racks.getBookid()}&chapterId=${racks.chapterid}&fm=${fromurl}">
                    <p class="small nowrap">阅读进度：<label class="ccode">${racks.chaptertitle}<br>继续阅读</label></p>
                    </a>
                    </c:if>
		      	 </div>
		      	</a>
		      	<span class="del" data-bid="${racks.getBookid() }"><span class="verticalAlign"></span><label>删除</label></span>
		      </li>
		</c:forEach>
        	
        	</ul>
</div>
<div style="background-color: rgba(50, 201, 186, 0.7);padding: 8px 10px;">
	<a style="color:white" href="http://t.cn/R6LUaeS">
		点击关注公众号“<span style="color:#2897ed;border-bottom:1px solid">五彩读书网</span>”方便下次继续阅读
	</a>
</div>

<%@ include file="/WEB-INF/views/include/include_footer.jsp"%>
</body>
</html>

