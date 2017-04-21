<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/WEB-INF/views/include/include_base.jsp"%>
<title>充值记录</title>
</head>
<script type="text/javascript" src="<%=path %>/static/js/jquery/jquery-1.10.2.min.js"></script>
<body>
<header class="nav wrap">
	<a class="ico52 back" href="javascript:history.go(-1);"></a>充值记录<a href="<%=path%>/?fm=${fromurl}" class="ico52 home"></a>
</header>
<div class="mod_tab_content shelf">
	<ul class="current my_orderlist" id="pay">
        <li><span>金额(元)</span><span>小说币</span><span>状态</span><span>时间</span></li>
        <c:forEach items="${wxpaylist}" var="wxpay">
	 	<li>
	 	 <span>${wxpay.amount}</span>
	 	 <c:if test="${wxpay.paytype==1 }">
	 	 <span>${wxpay.count}</span>
	 	 </c:if>
	 	 <c:if test="${wxpay.paytype==2 }">
	 	  <span>${wxpay.count}${wxpay.unitName}</span>
	 	 </c:if>
	 	 <span>${wxpay.orderstatusStr}</span>
	 	 <span>${wxpay.createtime}</span>
	 	</li>
	 </c:forEach>
    </ul>
</div>
<ul id="pager" class="pager">
      <li class="four"><a class="btn white start"
			href="<%=path %>/wxPay/index?pageNo=0&pageSize=${pager.pageSize }&fm=${fromurl}">第一页</a></li>
		<li class="four"><c:if test="${pager.prePage>=0 }">
				<a class="btn white prev" href="<%=path %>/wxPay/index?pageNo=${pager.prePage }&pageSize=${pager.pageSize }&fm=${fromurl}">上一页</a>
			</c:if> <c:if test="${pager.prePage<0 }">
				<a class="btn white next" href="#" disabled="disabled">上一页</a>
			</c:if></li>
		<li class="four"><c:if test="${pager.nextPage>0 }">
				<a class="btn white prev"
					href="<%=path %>/wxPay/index?pageNo=${pager.nextPage }&pageSize=${pager.pageSize }&fm=${fromurl}">下一页</a>
			</c:if> <c:if test="${pager.nextPage<=0 }">
				<a class="btn white next" href="#" disabled="disabled">上一页</a>
			</c:if></li>
		<li class="four"><a class="btn white end"
			href="<%=path %>/wxPay/index?pageNo=${pager.lastPageNo }&pageSize=${pager.pageSize }&fm=${fromurl}">最末页</a></li>
</ul>
<%@ include file="/WEB-INF/views/include/include_footer.jsp"%>
</body>
</html>