<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String pathFooter = request.getContextPath();
String basePathFooter = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pathFooter+"/";
%>
<footer>
	<p style="line-height: 20px"> 
	<a href="<%=pathFooter%>/?fm=${fromurl}">首页</a><span class="sp"></span>
	<a href="<%=pathFooter%>/wxBookrack/list/?fm=${fromurl}" title="书架">书架</a>
	<span class="sp"></span><a href="#">客服</a>
	<span class="sp"></span><a href="#">帮助</a> 
	</p>
	<p style="line-height: 22px;"> 
	<span class="small">©2016 五彩小说城</span> <span class="small">
	京ICP备16021213号-1</span> </p>
	<script>
		var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");
    	document.write(unescape("%3Cspan id='cnzz_stat_icon_1261514024'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "w.cnzz.com/q_stat.php%3Fid%3D1261514024' type='text/javascript'%3E%3C/script%3E"));
    </script>
</footer>