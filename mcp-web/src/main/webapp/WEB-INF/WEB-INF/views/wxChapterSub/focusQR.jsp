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
			<span>${wxChapterSub.title}</span>
		</nav>
		  <div style="text-align: left;margin-bottom:5px;font-size: 18px;margin-top: 10px;">
		【继续阅读】
	</div>
    <div style="text-align: center;margin-bottom:5px;font-size: 18px;margin-top: 10px;">
		关注微信公众号,继续阅读
	</div>
	<fieldset id="fllow_wximg">
				<legend class="tc">长按识别 即可添加</legend>
	</fieldset>
	<div style="text-align:center;">
    	<img style="width:242.5px;heigth:120.5px;" src="<%=path %>/static/image/QRCodeWuCai.png">
	</div> 	
	 <div style="text-align: center;margin-bottom:5px;font-size: 17px;margin-top: 10px;">
		如果无法识别二维码可在微信公众号中搜索：<span style="color: #fb6d6b">五彩读书网</span>关注后可继续阅读
	</div>
	</article>
</body>

<script type="text/javascript" src="<%=path %>/static/js/public.js"></script>
	<script>
		var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");
    	document.write(unescape("%3Cspan id='cnzz_stat_icon_1261514024'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "w.cnzz.com/q_stat.php%3Fid%3D1261514024' type='text/javascript'%3E%3C/script%3E"));
    </script>
</html>