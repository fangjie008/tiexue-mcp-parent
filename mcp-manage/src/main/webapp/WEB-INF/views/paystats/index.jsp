<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en">
<head>
	<%@ include file="/WEB-INF/views/include/global.jsp" %>
	<script src="<%=path%>/static/My97DatePicker/WdatePicker.js"></script>
	<script src="<%=path%>/static/wait/waitload.js"></script>
	<link rel="stylesheet" href="<%=path%>/static/wait/waitload.css">
<title>充值统计</title>
</head>
<style>
.layui-table th{
text-align:center;
}
.layui-table td{
text-align:center;
}
</style>
<body id="wait" class="body">
	<fieldset class="layui-elem-field layui-field-title"
		style="margin-top: 20px;">
		<legend>充值查询</legend>
	</fieldset>
	<div class="my-btn-box">
		<form class="layui-form" onsubmit="return validate(this);" action="<%=path%>/paystats/index" method="post">
		<span class="" style="margin-left:10px">推广标识</span>
		<span class="">
		<input type="text" id="sign" name="sign" value="${sign }"/> 
	   </span>
	   <span class="" style="margin-left:30px">
	   <button class="layui-btn" lay-submit="" lay-filter="demo1">搜索</button>
	    </span>
	   </form>
	</div>
	<table class="layui-table">
		<thead>
			<tr>
				<th>小说Id</th>
				<th>小说名</th>
				<th>浏览用户</th>
				<th>关注公众号人数</th>
				<th>充值金额</th>
				<th>消费金额</th>
			</tr>
		</thead>
		<tbody>
			
			<c:if test="${payStats!=null}">
					<tr>
						<td>${payStats.novelId}</td>
						<td>${payStats.title}</td>
						<td>${payStats.anonymityLogin}</td>
						<td>${payStats.followCount}</td>
						<td>${payStats.totalPayMoney/100.00}</td>
						<td>${payStats.totalConsumeMoney/100.00}</td>

				  </tr>
			</c:if>

		</tbody>
	</table>

<script type="text/javascript">

    layui.use(['element','layer','laypage'], function(){
        var $ = layui.jquery,element = layui.element,layer = layui.layer;
        var laypage = layui.laypage;

    });
    function validate(channelform)  { 
    	var sign=$("#sign").val();
        if(sign=="")  {  
        	alert("请输入推广标识");
            return false;  
        }
        _loading('数据查询中请耐心等待', 'wait');
        return true;  
    } 
    
    function detail(startTime,endTime,bookId){
    	location.href='<%=path%>/mcppay/detail?startTime='+startTime+'&endTime='+endTime+'&bookId='+bookId;
    }
    function isNumber(value) {
        var patrn = /^[0-9]*$/;
        if (patrn.exec(value) == null || value == "") {
            return false
        } else {
            return true
        }
    }
</script>
</body>
</html>