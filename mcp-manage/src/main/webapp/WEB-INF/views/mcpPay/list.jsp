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
		<span class="" style="margin-left:10px">小说名</span>
		<span class="">
		<input type="text" id="bookName" name="bookName" value="${bookName }"/> 
	   </span>
	   <span class="" style="margin-left:10px">开始时间</span>
		<span class="">
		<input type="text" class="Wdate" id="startTime" name="startTime" value="${startTime }"
		 onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true,maxDate:'#F{$dp.$D(\'endTime\')}'})" /> 
		<span class="" style="margin-left:10px">结束时间</span>
		<input type="text" class="Wdate" id="endTime" name="endTime" value="${endTime }" 
		onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true,minDate:'#F{$dp.$D(\'startTime\')}'})" /> 
	   </span>
	   <span class="" style="margin-left:30px">
	   <button class="layui-btn" lay-submit="" lay-filter="demo1">搜索</button>
	    </span>
	   </form>
	</div>
	<table class="layui-table">
		<thead>
			<tr>
				<th>开始时间</th>
				<th>结束时间</th>
				<th>小说名</th>
				<th>充值金额</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			
			<c:if test="${wxPays!=null}">
				<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
					<tr>
						<td>${startTime}</td>
						<td>${endTime}</td>
						<td>${bookName}</td>
						<td>${count}</td>
						<td>
							<button class="layui-btn layui-btn-small layui-btn-danger" 
							onclick="detail('${startTime}','${endTime}','${bookId}')">充值详细信息</button>
						</td>
				  </tr>
			</c:if>
			<c:if test="${wxPays==null&&wxBook!=null}">
				<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
					<tr>
						<td>${startTime}</td>
						<td>${endTime}</td>
						<td>${bookName}</td>
						<td>0</td>
						<td>
							
						</td>
				  </tr>
			</c:if>
			<c:if test="${wxPays==null&&wxBook==null&&bookName!=null&&bookName!=''}">
			<tr>
			<td colspan="4">
			<li>查询的小说不存在</li>
				<td>
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
    	var startTime=$("#startTime").val();
    	var endTime=$("#endTime").val();
    	var bookId=$("#bookId").val();
        if(startTime=="")  {  
        	alert("请选择开始时间");
            return false;  
        }
        if(endTime=="")  {  
        	alert("请选择结束时间");
            return false;  
        }
        if(bookId==""){
        	alert("请输入小说Id");
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