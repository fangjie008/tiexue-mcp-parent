<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<%@ include file="/WEB-INF/views/include/global.jsp" %>
	<script src="<%=path%>/static/My97DatePicker/WdatePicker.js"></script>
	<script src="<%=path%>/static/wait/waitload.js"></script>
	<link rel="stylesheet" href="<%=path%>/static/wait/waitload.css">
    <title>结算管理</title>
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
		<form class="layui-form" onsubmit="return validate(this);" action="<%=path%>/mcppay/list" method="post">
		<span class="" style="margin-left:10px">小说Id</span>
		<span class="">
		<input type="text" id="bookId" name="bookId" value="${bookId }"/> 
	   </span>
		
		<span class="" style="margin-left:10px">查询时间</span>
		<span class="">
		<input type="text" class="Wdate" id="startTime" name="startTime" value="${startTime }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})" /> 
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
				<th>充值金额</th>
				<th>充值时间</th>
			</tr>
		</thead>
		<tbody>

			<c:if test="${wxPays!=null}">
				<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
				<c:forEach items="${wxPays}" var="item">
					<tr>
					    <td>${item.bookId}</td>
						<td>${item.bookName}</td>
						<td>${item.amount}</td>
						<td>${item.createtime}</td>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
<div class="layui-form">
		<span id="form_page"></span>总金额为：&nbsp;${count}&nbsp;
	</div>
<script type="text/javascript">

    layui.use(['element','layer','laypage'], function(){
        var $ = layui.jquery,element = layui.element,layer = layui.layer;
        var laypage = layui.laypage;

    });
    function validate(channelform)  { 
    	var datetime=$("#startTime").val();
    	var bookId=$("#bookId").val();
        if(datetime=="")  {  
        	alert("请选择查询时间");
            return false;  
        }
        if(bookId==""){
        	alert("请输入小说Id");
        	return false;
        }
        if(!isNumber(bookId)){
        	alert("小说Id必须为整数");
        	return false;
        }
        _loading('数据查询中请耐心等待', 'wait');
        return true;  
    } 
    
    function detail(monthly){
    	location.href='<%=path%>/mcpsettlement/detail?monthly='+monthly;
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