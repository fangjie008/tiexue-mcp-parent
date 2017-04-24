<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/WEB-INF/views/include/include_base.jsp"%>
<title>支付失败</title>
</head>

<body>
<header class="nav wrap"> 
 <a class="ico52 back" href="javascript:history.go(-1);"></a>微信支付<a href="<%=path%>/?fm=${fromurl}" class="ico52 home"></a>
</header>
<div class="wrap">
	<p class="panel margin-top-10">充值成功</p>
	 <a class="btn block" href="<%=path%>/wxPay/pay?fm=${fromurl}">再次充值</a> 
	 <a class="btn block" href="<%=path%>/?fm=${fromurl}">返回看书</a> 
</div>

<div style="background-color: rgba(50, 201, 186, 0.7);padding: 8px 10px;">
	<a style="color:white" href="http://t.cn/R6LUaeS">
		点击关注公众号“<span style="color:#2897ed;border-bottom:1px solid">五彩读书网</span>”方便下次继续阅读
	</a>
</div>

<%@ include file="/WEB-INF/views/include/include_footer.jsp"%>
</body>
</html>