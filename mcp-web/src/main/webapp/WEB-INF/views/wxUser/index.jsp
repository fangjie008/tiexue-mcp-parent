<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/WEB-INF/views/include/include_base.jsp"%>
<title>用户中心</title>
</head>
<body>
<input type="hidden" id="fromurl" name="fromurl" value="${fromurl }">
	<header class="nav wrap">
		<a class="ico52 back" href="javascript:history.go(-1);"></a>个人中心<a href="<%=path%>/?fm=${fromurl}" class="ico52 home"></a>
	</header>
	<div class="panel my">
		<dl>
			<dt>
				<img src="${user.getHeadericon()}" class="avatar large">
			</dt>
			<dd>
				<ul>
					<li><span class="nick blue fn-left fn-text-overflow">${user.getName()}</span>
						<span class="fn-right">${user.getId()}</span></li>
					<c:if test="${deadline!=null&&!deadline.isEmpty()}">
					<li><span class="badge hot">包年VIP (${deadline})</span>
					</li>
					</c:if>
					<c:if test="${deadline==null||deadline.isEmpty()}">
					<li><span class=" badge normal">${user.getUsertypestr()}</span>
					</li>
					</c:if>
					<li><span class="">小说币&nbsp;&nbsp;&nbsp;<label class="orange">${user.getCoin() }</label><label
							class="orange"></label></span></li>
						
				</ul>
			</dd>
		</dl>
	</div>
	<div class="mod_tab_content">
		<p class="solid"></p>
		<div class="">
			<ul class="class_list">
				<li><a href="<%=path%>/wxPay/pay?fm=${fromurl}"><img
						src="<%=path%>/static/image/user/icon_pay.png" class="icon"><span
						class="txt">充值小说币</span><img
						src="<%=path%>/static/image/user/arrow_r.png"
						class="arrow_r"></a></li>
				<li><a href="<%=path%>/wxPay/index/?fm=${fromurl}"><img
						src="<%=path%>/static/image/user/icon_payrecord.png" class="icon"><span
						class="txt">充值记录</span><img
						src="<%=path%>/static/image/user/arrow_r.png"
						class="arrow_r"></a></li>
				<li><a href="<%=path%>/wxConsume/index/?fm=${fromurl}"><img
						src="<%=path%>/static/image/user/icon_order.png" class="icon"><span
						class="txt">消费记录</span><img
						src="<%=path%>/static/image/user/arrow_r.png"
						class="arrow_r"></a></li>
			</ul>
		</div>
		<p class="solid"></p>
		<div class="">
			<ul class="class_list">
				<%-- <li><a href="#"><img
						src="<%=path%>/static/image/user/icon_nearread.png" class="icon"><span
						class="txt">最近阅读</span><img
						src="<%=path%>/static/image/user/arrow_r.png"
						class="arrow_r"></a></li> --%>
				<li><a href="<%=path%>/wxBookrack/list/?fm=${fromurl}"><img
						src="<%=path%>/static/image/user/icon_feedback.png" class="icon"><span
						class="txt">我的书架</span><img
						src="<%=path%>/static/image/user/arrow_r.png"
						class="arrow_r"></a></li>
			<li><a href="#" onclick="loginout()"><img
						src="<%=path%>/static/image/user/icon_help.png" class="icon"><span
						class="txt">退出登录</span><img
						src="<%=path%>/static/image/user/arrow_r.png"
						class="arrow_r"></a></li>

			</ul>
		</div>
		<p class="solid"></p>
		<div class="">
			<ul class="class_list">
			</ul>
		</div>
		<p class="solid"></p>
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
function loginout(){
	var fromurl=$("#fromurl").val();
	setCookie("wx_gzh_token","",-1);
	window.location.href="<%=path %>/?fm="+fromurl; 
}
</script>
</html>
