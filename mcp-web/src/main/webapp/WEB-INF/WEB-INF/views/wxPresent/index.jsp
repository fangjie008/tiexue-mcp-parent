<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/WEB-INF/views/include/include_base.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>签到送小说币</title>
</head>

<body>
<header class="nav wrap"> 
 <a class="ico52 back" href="javascript:history.go(-1);"></a>签到送币<a href="<%=path%>/?fm=${fromurl}" class="ico52 home"></a>
</header>
<div class="wrap" >
		<div class="mod_content c1 gray">
	<ul class="gray small">
			<li style="color:red;font-size:20px;">温馨提示：
			</li>
		    <li>用户每天可领取一次小说币</li>
		    <li>每次可领取15小说币</li>
		</ul>
		
	</div>
	<c:if test="${userId>0}">
		 <c:if test="${isget==true}">
		  <p id="showmsg" class="panel margin-top-10" style="text-align: center;color: red;font-size: 16px;">您今天已领去小说币,请明天再来！</p>
		 </c:if>
		 <c:if test="${isget==false}">
		  <div id="showmsg" class="panel margin-top-10" style="display:none;text-align: center;color: red;font-size: 16px;"></div>
		   <div id="togetcoin">
		   <a class="btn block" href="#" onclick="getcoin()">领取小说币</a> 
		   </div>
		 </c:if>
	</c:if>
	<c:if test="${userId<=0}">
	 <p class="panel margin-top-10" style="text-align: center;color: red;font-size: 16px;">请先登录再领小说币！</p>
	  <a class="btn block" href="<%=path%>/wxUser/login?fm=${fromurl}">登录</a> 
	</c:if>
	<a class="btn block" href="<%=path%>/wxUser/content?fm=${fromurl}">个人中心</a> 
	<a class="btn block" href="<%=path%>/?fm=${fromurl}">返回看书</a> 
	
	 </div>
<%@ include file="/WEB-INF/views/include/include_footer.jsp"%>
</body>
<script type="text/javascript">
function getcoin(){
	$.ajax({
		url:"<%=path%>/wxPresent/getCoin",
		type:"post",
		async:false,
		dataType:'json',  
		success:function(data){
			if(data.ok==true){
				$("#showmsg").html("领取成功!");
				$("#showmsg").show();
				$("#togetcoin").hide();
			}else{
				$("#showmsg").html("领取失败!");
				$("#showmsg").show();
				$("#togetcoin").hide();
			}
		},
		error:function(data){
			
		}
	})
}
</script>
</html>