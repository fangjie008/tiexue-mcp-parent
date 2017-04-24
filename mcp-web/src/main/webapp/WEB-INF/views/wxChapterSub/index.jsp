<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/WEB-INF/views/include/include_base.jsp"%>
<style type="text/css">
 .body1{
   background: rgb(230, 224, 189);
 }
 .body2{
   background: rgb(56, 56, 56);
 }
 .tc { text-align: center; }
fieldset { border:none; border-top:1px solid #ccc; padding: 0.25rem; margin:0 0.75rem; font-size: 0.975rem; }
</style>
<title>${wxChapterSub.bookName}</title>
</head>
<body>
	<article class="theme1">
		<nav>
			<span>${wxChapterSub.title}</span><a id="badge_menu" class="badge menu"
				href="javascript:;">菜单</a>
		</nav>
		<div class="content" style="font-size: 18px;">
			${wxChapterSub.content}</div>
		<ul class="chapter_pager">

			<li class="pn">
			<c:if test="${wxChapterSub.preId>0 }">
			     <c:if test="${wxChapterSub.preType!=0}">
			      <a class="chapter_prev btn white block"
						data-cid="${wxChapterSub.preId}"
						href="<%=path %>/wxChapterSub/vip?bookId=${wxChapterSub.bookId}&chapterId=${wxChapterSub.preId}&fm=${fromurl}">上一章</a>
				 </c:if>
				<c:if test="${wxChapterSub.preType==0}">
			      <a class="chapter_prev btn white block"
						data-cid="${wxChapterSub.preId}"
						href="<%=path %>/wxChapterSub/index?bookId=${wxChapterSub.bookId}&chapterId=${wxChapterSub.preId}&fm=${fromurl}">上一章</a>
				 </c:if>
			</c:if> <c:if test="${wxChapterSub.preId<=0 }">
					<a class="chapter_prev btn white block" href="#"
						disabled="disabled">上一章</a>
				</c:if></li>
			<li class="mulu"><a class="btn white block"
				href="<%=path %>/wxChapter/index?bookId=${wxChapterSub.bookId}&pageNo=${pageNo}&fm=${fromurl}">目录</a></li>
			<li class="pn">
			<c:if test="${wxChapterSub.nextId>0 }">
			     <c:if test="${wxChapterSub.nextType!=0}">
			      <a  onclick="addbookrack('${wxChapterSub.bookId}','${wxChapterSub.nextId}')"  class="chapter_next btn block" data-cid="${wxChapterSub.nextId}"
						href="<%=path %>/wxChapterSub/vip?bookId=${wxChapterSub.bookId}&chapterId=${wxChapterSub.nextId}&fm=${fromurl}">下一章</a>
				 </c:if>
				 <c:if test="${wxChapterSub.nextType==0}">
			       <a  onclick="addbookrack('${wxChapterSub.bookId}','${wxChapterSub.nextId}')"  class="chapter_next btn block" data-cid="${wxChapterSub.nextId}"
						href="<%=path %>/wxChapterSub/index?bookId=${wxChapterSub.bookId}&chapterId=${wxChapterSub.nextId}&fm=${fromurl}">下一章</a>
				 </c:if>
			</c:if> 
				<c:if test="${wxChapterSub.nextId<=0 }">
					<a class="chapter_next btn block" href="#" disabled="disabled">下一章</a>
				</c:if></li>
		</ul>

		<div class="tool_mask"></div>
		<div class="tool_option">
			<ul>
				<li id="background"><label>背景：</label> 
				<span class="theme1" data-theme="1">Aa</span> 
				<span class="theme2" data-theme="2">Aa</span>
				<span class="theme3" data-theme="3">Aa</span>
				</li>
				<li id="font-size"><label>文字：</label> <span
					style="font-size: 30px" data-font="30" class="">A</span> <span
					style="font-size: 26px" data-font="26" class="">A</span> <span
					style="font-size: 22px" data-font="22" class="">A</span> <span
					style="font-size: 18px" data-font="18" class="current">A</span> <span
					style="font-size: 14px" data-font="14" class="">A</span></li>
			</ul>
		</div>
		<div class="tool_top" style="display: none;">
			<ul>
				<li><a href="<%=path%>/wxbook/detail?id=${wxChapterSub.bookId}&fm=${fromurl}"><i
						class="ico40 back back2"></i>书页</a></li>
				<li class="center-li"><a class=""
					href="<%=path %>/wxChapter/index?bookId=${wxChapterSub.bookId}&pageNo=${pageNo}&fm=${fromurl}">目录</a></li>
				<li><a
					href="<%=path%>/wxBookrack/list?fm=${fromurl}">书架
						<i class="ico40 bookshelf"></i>
				</a></li>
			</ul>
		</div>
		<div class="tool_bottom" style="display: none;">
			<ul>
			<li class="pn">
			<c:if test="${wxChapterSub.preId>0 }">
			     <c:if test="${wxChapterSub.preType==0}">
			        <a class="chapter_prev"
						data-cid="${wxChapterSub.preId}"
						href="<%=path %>/wxChapterSub/index?bookId=${wxChapterSub.bookId}&chapterId=${wxChapterSub.preId}">
						<i class="ico40 fastprev"></i>上章
					</a>
				 </c:if>
				<c:if test="${wxChapterSub.preType!=0}">
			        <a class="chapter_prev"
						data-cid="${wxChapterSub.preId}"
						href="<%=path %>/wxChapterSub/vip?bookId=${wxChapterSub.bookId}&chapterId=${wxChapterSub.preId}">
						<i class="ico40 fastprev"></i>上章
					</a>
				 </c:if>
                  
			</c:if> <c:if test="${wxChapterSub.preId<=0 }">
					<a class="chapter_prev" href="#"
						disabled="disabled"><i
						class="ico40 fastprev"></i>上章</a>
				</c:if></li>
			<li><a class="option" href="javascript:;"> <i
						class="ico40 option"></i>设置
				</a></li>
			<li class="pn">
			<c:if test="${wxChapterSub.nextId>0 }">
			     <c:if test="${wxChapterSub.nextType==0}">
			       <a class="chapter_next" onclick="addbookrack('${wxChapterSub.bookId}','${wxChapterSub.nextId}')" data-cid="${wxChapterSub.nextId}"
						href="<%=path %>/wxChapterSub/index?bookId=${wxChapterSub.bookId}&chapterId=${wxChapterSub.nextId}&fm=${fromurl}">下章 <i
						class="ico40 fastnext"></i></a>
				 </c:if>
				 <c:if test="${wxChapterSub.nextType!=0}">
			       <a class="chapter_next" onclick="addbookrack('${wxChapterSub.bookId}','${wxChapterSub.nextId}')" data-cid="${wxChapterSub.nextId}"
						href="<%=path %>/wxChapterSub/vip?bookId=${wxChapterSub.bookId}&chapterId=${wxChapterSub.nextId}&fm=${fromurl}">下章 <i
						class="ico40 fastnext"></i></a>
				 </c:if>
				</c:if> <c:if test="${wxChapterSub.nextId<=0 }">
					<a class="chapter_next" href="#" disabled="disabled">下章 <i
						class="ico40 fastnext"></i></a>
				</c:if></li>
			</ul>
		</div>
		
    <div style="text-align: center;margin-bottom:5px;font-size: 18px;">
		【关注微信公众号,方便下次阅读】
	</div>
	<fieldset id="fllow_wximg">
				<legend class="tc">长按识别 即可添加</legend>
	</fieldset>
	<div style="text-align:center;">
    	<img style="width:242.5px;heigth:120.5px;" src="<%=path %>/static/image/QRCodeWuCai.png">
	</div> 	
	<%-- 	<!--收藏本书开始-->
		<div class="alertbk alertbk-fav" id="collect">
		   <input type="hidden" id="bookid" name="bookid" value="${wxChapterSub.bookId}">
		   <input type="hidden" id="bookname" name="bookname" value="${wxChapterSub.bookName}">
            <input type="hidden" id="chapterid" name="chapterid" value="${wxChapterSub.id}">
            <input type="hidden" id="chaptername" name="chaptername" value="${wxChapterSub.title}">     
			<center >
				<span>请加收藏，方便下次阅读</span> 
				<a id="btn-addbookrack" class="okbtn add-fav" 	dden="ok" data-bid="14438">确定</a>
			</center>
		</div> --%>
		<!--收藏本书结束-->


<%@ include file="/WEB-INF/views/include/include_footer.jsp"%>
	</article>
</body>

<script type="text/javascript" src="<%=path %>/static/js/public.js"></script>
<script type="text/javascript">
var chaptersub_article_fontsize="chaptersub_article_fontsize";
var chaptersub_body_background="chaptersub_body_background";
$(function(){
	var body_background=getCookie(chaptersub_body_background);
	var article_fontsize=getCookie(chaptersub_article_fontsize);
	if(body_background!=undefined&&body_background!=""){
	 	 $("body article").attr("class",body_background);
	}
	if(article_fontsize!=undefined&&article_fontsize!=""){
		$(".content").css("font-size",article_fontsize+"px");
	}
});


$("#btn-addbookrack").click(function(){
	var text=$("#btn-addbookrack").html();
	if(text=="已添加"){
		return;
	}
	var bookid=$("#bookid").val();
	var bookname=$("#bookname").val();
	var chapterid=$("#chapterid").val();
	var chaptername=$("#chaptername").val();
	var wx_gzh_token=getCookie("wx_gzh_token");
	if(wx_gzh_token==undefined||wx_gzh_token==""){
		addbookrack(bookid,chapterid);
		$("#btn-addbookrack").css("disabled");
		$("#btn-addbookrack").html("已添加");
		$("#collect").hide();
	}else{
		var postData={'bookId':bookid,'bookName':bookname,'chapterId':chapterid,'chapterName':chaptername}
		$.ajax({
			url:"<%=path%>/wxBookrack/updateBookrack",
			data:postData,
			type:"post",
			dataType:"json",
			success:function(res){
				$("#btn-addbookrack").css("disabled");
				$("#btn-addbookrack").html("已添加");
				$("#collect").hide();
				console.log(res.msg);
			},
			error:function(res){
				//alert("失败");
			}
		});
	}
	
});

$("#badge_menu").click(function(){
	$(".tool_top").show();
	$(".tool_bottom").show();
});
$(".content").click(function(){
	var display =$('.tool_top').css('display');
	if(display == 'none'){
		$(".tool_top").show();
		$(".tool_bottom").show();
	}else{
		$(".tool_top").hide();
		$(".tool_bottom").hide();
		$(".tool_option").hide();
	}
	
});

$(".option").click(function(){
	var display =$('.tool_option').css('display');
	if(display == 'none'){
		$(".tool_option").show();

	}else{
		$(".tool_option").hide();
	}
	
});

$("span[data-font]").click(function(){
	var _this = $(this);
	var fontsize = _this.attr("data-font");
	
	$("span[data-font]").each(function(index,element){
		if(fontsize==$(element).attr("data-font")){
			$(element).addClass("current");
		}
		else{
			$(element).removeClass("current");
		}
	});
	$(".content").css("font-size",fontsize+"px");
	setCookie("chaptersub_article_fontsize",fontsize,3650);
	console.log(fontsize);
});

$("span[data-theme]").click(function(){
	var _this=$(this);
	var className=_this.attr("class");
	$("span[data-theme]").each(function(index,element){
		if(className==$(element).attr("class")){
			$(element).addClass("current");
		}
		else{
			$(element).removeClass("current");
		}
	});
	$("body article").attr("class",className);
	setCookie("chaptersub_body_background",className,3650);
	
});

	
</script>
</html>