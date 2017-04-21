<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/WEB-INF/views/include/include_base.jsp"%>
<title>	${wxBook.getName()}</title>


</head>
<body>
<header class="nav wrap">
	<a class="ico52 back" href="javascript:history.go(-1);"></a>书籍信息<a href="<%=path%>/?fm=${fromurl}" class="ico52 home"></a>
</header>
<div class="book_title wrap">${wxBook.getName()}</div>
<input type="hidden" id="bookid" name="bookid" value="${wxBook.getId()}">
<input type="hidden" id="bookname" name="bookname" value="${wxBook.getName()}">
<div class="panel">
	<div class="fn-clear">
        <img class="cover fn-left lazy" style="margin-top:0px;" src="${wxBook.getCoverImgs()}" dataimg="${wxBook.getCoverImgs()}" width="80px" height="112px">
		<ul class="book_info">
             <li><label>专题：</label><a href="#">言情小说</a></li>
			<li><label>类别：</label><a href="#">${wxBook.tag}</a></li>
			<li><label>状态：</label>${wxBook.status}</li>
			<li><label>字数：</label>${wxBook.getContentLen()}</li>
			<li><label></label><span class="orange"></span> <a class="scrollTo" href="#comments"></a></li>
		</ul>
	</div>
	<ul class="btn_area fn-clear">
		<li>
		<c:if test="${bookrack.chapterid>0}">
		<a class="btn block start_read" href="<%=path %>/wxChapterSub/index?bookId=${wxBook.getId()}&chapterId=${bookrack.chapterid}&fm=${fromurl}">继续阅读</a>
		</c:if>
		<c:if test="${bookrack==null||bookrack.chapterid<=0}">
		<a class="btn block start_read" href="<%=path %>/wxChapterSub/defualt?bookId=${wxBook.getId()}&fm=${fromurl}">免费阅读</a>
		</c:if>
		</li>
		<li>
		<c:if test="${bookrack.bookid>0}">
		<span class="btn white block add-fav disabled" id="btn-addbookrack" data-bid="${wxBook.getId()}" data-name="${wxBook.getName()}">
		已添加</span>
		</c:if>
		<c:if test="${bookrack==null||bookrack.bookid<=0}">
		<span class="btn white block add-fav" id="btn-addbookrack" data-bid="${wxBook.getId()}" data-name="${wxBook.getName()}">
		加入书架</span>
		</c:if>
		
		</li>
	</ul>
        <a href="<%=path %>/wxChapter/index?bookId=${wxBook.getId()}&fm=${fromurl}" class="btn block white">全部章节目录</a>
	<ul class="continue_read fn-clear fn-hide">
		<li class="orange">上次读到：<span></span></li>
		<li><a class="fn-left" href="#">本章»</a><a class="fn-right" href="#">下章»</a></li>
	</ul>
</div>
<div class="book_intro">
	<p id="summary" style="height: 120px;">
	 ${wxBook.getIntr()}
	</p>
	<p id="showSummary" class="arrow"><a href="#" onclick="showmoreintr()">展开</a></p>
</div>

<div style="background-color: rgba(50, 201, 186, 0.7);padding: 8px 10px;">
	<a style="color:white" href="http://t.cn/R6LUaeS">
		点击关注公众号“<span style="color:#2897ed;border-bottom:1px solid">五彩读书网</span>”方便下次继续阅读
	</a>
</div>
<%@ include file="/WEB-INF/views/include/include_footer.jsp"%>
</body>
<script type="text/javascript" src="<%=path %>/static/js/public.js"></script>
<script type="text/javascript" src="<%=path %>/static/js/bookdetail.js"></script>
<script type="text/javascript">
$(function(){
	$("#btn-addbookrack").click(function(){
		var text=$("#btn-addbookrack").html();
		if(text=="已添加"){
			return;
		}
		var bookid=$("#bookid").val();
		var bookname=$("#bookname").val();
		var wx_gzh_token=getCookie("wx_gzh_token");
		if(wx_gzh_token==undefined||wx_gzh_token==""){
			addbookrack(bookid,0);
			$("#btn-addbookrack").css("disabled");
			$("#btn-addbookrack").html("已添加");
		}else{
			var postData={'bookId':bookid,'bookName':bookname}
			$.ajax({
				url:"<%=path%>/wxBookrack/addBookrack",
				data:postData,
				type:"post",
				dataType:"json",
				success:function(res){
					$("#btn-addbookrack").css("disabled");
					$("#btn-addbookrack").html("已添加");
					console.log(res.msg);
				},
				error:function(res){
					//alert("失败");
				}
			});
		}
		
	});
});

function showmoreintr(){
    var content=$("#showSummary a").html();
    if(content=="展开"){
    	$("#showSummary").addClass("up");
    	$("#showSummary a").html("折叠");
    	$("#summary").css("height","auto");
    	
    }else{
    	$("#showSummary").removeClass("up");
    	$("#summary").css("height","120px");
    	$("#showSummary a").html("展开");
    }
}
	
</script>
</html>
