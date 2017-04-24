<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/WEB-INF/views/include/include_base.jsp"%>
<!-- 因为图片路径路径不能写单独的css-->
<%-- <link href="<%=path%>/static/css/wxChapter/fenye.css" type="text/css" rel="stylesheet" /> --%>
<style type="text/css">
	.fenye {
    margin: 10px 10px;
}
.fenye .fy {
    float: left;
    width: 96%;
    margin-left: 1%;
    margin-right: 2%;
    margin-left: 0px;
}
.fenye .showpage {
    position: fixed;
    display: none;
    width: 80%;
    height: 60%;
    top: 15%;
    left: 10%;
    background-color: #fff;
    z-index: 9999;
}
.r3 {
    border-radius: 3px;
}
#spagebg {
    display: none;
    position: absolute;
    left: 0px;
    right: 0px;
    top: 0px;
    bottom:0px;
    background-color: #000;
    z-index: 999;
}
.spage {
    width: 100%;
    height: 30px;
    line-height: 30px;
    border-style: solid;
    border-width: 1px;
    padding: 0px 5px;
    text-align: center;
    border-color: #1ABC9C;
    background: #fff no-repeat center right url(<%=path%>/static/list2.png);
    border-radius: 3px;
}
.fenye .showpage div {
    border-bottom-style: solid;
    border-bottom-width: 1px;
    height: 40px;
    line-height: 40px;
    text-align: center;
}
.bk {
    border-color: #CCC;
}
.fenye .showpage ul {
    position: absolute;
    top: 40px;
    bottom: 0px;
    left: 10px;
    right: 10px;
    overflow: auto;
}
.fenye .showpage a {
    display: block;
    border-bottom-style: solid;
    border-bottom-width: 1px;
    height: 40px;
    line-height: 40px;
    margin: 0px 10px;
    font-size: 12px;
}
.xbk {
    border-color: #E2E2E2;
}
.fenye .showpage .this {
    background: right center no-repeat url(<%=path%>/static/image/yes.gif);
}
.tb {
    color: #1ABC9C;
}

.fenye .desc {
    float: right;
    width: 18%;
    margin-right: 0%;
}
.fenye .desc a {
    display: block;
    color: #fff;
    text-align: center;
    height: 30px;
    line-height: 30px;
}
.dise {
    background-color: #1ABC9C;
}
	
	
	</style>
<title>${wxBook.name}</title>
</head>
<body>
<input type="hidden" name="bookId" id="bookId" value="${bookId}">
<input type="hidden" name="fromurl" id="fromurl" value="${fromurl}">
	<header class="nav wrap">
		<a class="ico52" href="<%=path%>/wxbook/detail?id=${bookId}&fm=${fromurl}"></a>
		<div class="header-center">${wxBook.name}</div>
		<a href="<%=path%>/?fm=${fromurl}" class="ico52 home"></a>
	</header>
	<div id="zjlb">
		<div class="fenye">
			<div class="fy">
				<div id="spagebg"></div>
				<div class="spage">
					<c:forEach items="${chapNaviDtos }" var="navi">
						<c:if test="${navi.isActive==true}">
							<a class="xbk this tb">${navi.name} </a>
						</c:if>
					</c:forEach>
				</div>
				<div class="showpage r3"
					style="transform-origin: 0px 0px 0px; opacity: 1; transform: scale(1, 1); display: none;">
					<div class="bk">请选择章节</div>
					<ul>
						<c:forEach items="${chapNaviDtos }" var="navi">
							<li>
							<c:if test="${navi.isActive==true}">
									<a class="xbk this tb">${navi.name} </a>
								</c:if> <c:if test="${navi.isActive==false}">
									<a class="xbk" href="<%=path%>${navi.url}">${navi.name}</a>
								</c:if>
								</li>
						</c:forEach>
						<li><a class="xbk">没有更多分页了！</a></li>
					</ul>
				</div>
				<div id="spagebg"></div>
			</div>
			<div class="cc"></div>
		</div>
	</div>
	<ul class="menu">
		<c:if test="${wxChapters==null||fn:length(wxChapters)<=0}">
			<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
		</c:if>
		<c:forEach items="${wxChapters}" var="chapters">
			<li><c:if test="${chapters.getChaptertype()==0}">
			    <a class="chapter" onclick="addbookrack('${wxBook.id}','${chapters.id}')"
				href="<%=path%>/wxChapterSub/index?bookId=${wxBook.id}&chapterId=${chapters.id}&orderNum=${sortorder}&fm=${fromurl}">
					${chapters.title} </a>
					<span class="fn-right c999">免费</span>
				</c:if>
				<c:if test="${chapters.getChaptertype()!=0}">
			    <a class="chapter" onclick="addbookrack('${wxBook.id}','${chapters.id}')"
				href="<%=path%>/wxChapterSub/vip?bookId=${wxBook.id}&chapterId=${chapters.id}&orderNum=${sortorder}&fm=${fromurl}">
					${chapters.title} </a>
				</c:if>
		    </li>
		</c:forEach>

	</ul>

	<ul id="pager" class="pager">

		<li class="four"><a class="btn white start"
			href="<%=path %>/wxChapter/index?bookId=${bookId}&pageNo=0">第一页</a></li>
		<li class="four"><c:if test="${pager.prePage>=0 }">
				<a class="btn white prev"
					href="<%=path %>/wxChapter/index?bookId=${bookId}&pageNo=${pager.prePage }&fm=${fromurl}">上一页</a>
			</c:if> <c:if test="${pager.prePage<0 }">
				<a class="btn white prev" href="#" disabled="disabled">上一页</a>
			</c:if></li>
		<li class="four"><c:if test="${pager.nextPage>0 }">
				<a class="btn white next"
					href="<%=path %>/wxChapter/index?bookId=${bookId}&pageNo=${pager.nextPage }&fm=${fromurl}">下一页</a>
			</c:if> <c:if test="${pager.nextPage<=0 }">
				<a class="btn white next" href="#" disabled="disabled">上一页</a>
			</c:if></li>
		<li class="four"><a class="btn white end"
			href="<%=path %>/wxChapter/index?bookId=${bookId}&pageNo=${pager.lastPageNo }&fm=${fromurl}">最末页</a></li>
	</ul>
	<div
		style="background: none repeat scroll 0 0 #F9F8F8; padding-left: 10px; padding-bottom: 10px">
		跳转至 <input type="hidden" id="totalPage" value="${totalRecord }">
		<input type="hidden" id="pageSize" value="${pager.pageSize }">
		<input type="hidden" id="preUrl"
			value="<%=path %>/wxChapter/index?bookId=${bookId}&pageNo=&fm=${fromurl}">
		<input id="jump_page"
			style="width: 40px; border: none; border-bottom: 1px solid #ccc; text-align: center"
			type="text" name="p" value="${jumpPage }">页(共${totalRecord}) <input
			id="jump_to" onclick="jumpPage()" class="btn white"
			style="height: 35px; line-height: 35px" type="button" name="jumpto"
			value="跳转">
	</div>
	
<div style="background-color: rgba(50, 201, 186, 0.7);padding: 8px 10px;">
	<a style="color:white" href="http://t.cn/R6LUaeS">
		点击关注公众号“<span style="color:#2897ed;border-bottom:1px solid">五彩读书网</span>”方便下次继续阅读
	</a>
</div>
	<%@ include file="/WEB-INF/views/include/include_footer.jsp"%>
</body>
<script type="text/javascript" src="<%=path %>/static/js/public.js"></script>
<script type="text/javascript">
	function jumpPage() {
		var jumpPage = $("#jump_page").val();
		var totalPage = $("#totalPage").val();
		var pageSize = $("#pageSize").val();
		var fromurl = $("#fromurl").val();
		var bookId = $("#bookId").val();
		if (isNaN(jumpPage) || jumpPage <= 0) {
			alert("请输入大于0的数字");
			return;
		}
		if (jumpPage > totalPage) {
			alert("超出最大页数");
		} else {
			var pageNo = (jumpPage - 1) * pageSize;
			window.location.href="<%=path%>/wxChapter/index?bookId="
					+bookId +"&pageNo="+ pageNo+"&jumpPage="+jumpPage+"&fm="+fromurl;
		}
	}
	$("#zjlb .spage").click(
			function() {
				$("#zjlb .showpage").show(300);
				$("#spagebg").css("opacity", "0.7").fadeIn(500);
			});
	$("#spagebg").click(function() {
		$(this).fadeOut(700);
		$("#zjlb .showpage").hide(300);
	});
</script>
</html>





