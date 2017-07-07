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
 .box-readmore{
	box-shadow:0 -3px 10px -4px rgba(0,0,0,0.3);
	position:relative;
	height:50px;
}
.content{
	max-height: 700px;
	word-break: break-word;
	overflow: hidden;
}
fieldset { border:none; border-top:1px solid #ccc; padding: 0.25rem; margin:0 0.75rem; font-size: 0.975rem; }
</style>
<title>${wxChapterSub.bookName}</title>
</head>
<body>
<article class="theme1">
		<nav>
			<span>${wxChapterSub.title}</span>
		</nav>
   <div class="content" style="font-size: 18px;">
			${wxChapterSub.content}</div>
	<div class="box-readmore" style="height:400px">
		<div style="text-align: left;margin-bottom:5px;font-size: 18px;margin-top: 10px;">
			 <p style="text-indent: 2em;">由于篇幅限制，本页面只能更新到这！</p>
		</div>
	    <div style="text-align: center;margin-bottom:5px;font-size: 18px;margin-top: 10px;">
			<p style="color:red;">后续更多内容，请长按下图二维码，</p>
			<p style="color:red;">识别关注公众号，就可以继续阅读了哦！</p>
		</div>
		<div style="text-align:center;">
	    	<img style="width:242.5px;heigth:120.5px;" src="<%=path %>/static/image/QRCodeWuCai.png">
		</div> 	
		 <div style="text-align: center;margin-bottom:5px;font-size: 17px;margin-top: 10px;">
			如果无法识别二维码可在微信公众号中搜索：<span style="color: #fb6d6b">五彩读书网</span>关注后可继续阅读
		</div>
		</div>
	</article>
</body>
<script>


</script>
<script type="text/javascript" src="<%=path %>/static/js/public.js"></script>
<script>
		var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");
    	document.write(unescape("%3Cspan id='cnzz_stat_icon_1261514024'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "w.cnzz.com/q_stat.php%3Fid%3D1261514024' type='text/javascript'%3E%3C/script%3E"));
</script>
</html>