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
    <title>运营管理</title>
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
			<legend>
		<span class="layui-breadcrumb"> <a href="javascript:;">运营</a> <a><cite>详细信息</cite></a>
			</span>
			</legend>
	</fieldset>
	<div class="my-btn-box">
				<button type="reset" onclick="javascript:history.go(-1);" class="layui-btn layui-btn-primary btn-back">返回</button>
	 <span style="margin-left:100px">
	    <a  class="layui-btn" href="<%=path%>/mcpsettlement/exportoperatedetail?startTime=${startTime }&endTime=${endTime }">导出Execl</a>
	    </span>
	</div>
	<table class="layui-table">
		<thead>
			<tr>
				<th>开始时间</th>
				<th>结束时间</th>
				<th>CPBId</th>
				<th>书籍名称</th>
				<th>消费</th>
			</tr>
		</thead>
		<tbody>

			<c:if test="${list!=null}">
				<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
				<c:forEach items="${list}" var="item">
					<tr>
						<td>${item.startTime}</td>
						<td>${item.endTime}</td>
						<td>${item.cpBId}</td>
						<td>${item.bookName}</td>
						<td>${item.consume}</td>
				</c:forEach>
			</c:if>
		</tbody>
	</table>

<script type="text/javascript">

    layui.use(['element','layer','laypage'], function(){
        var $ = layui.jquery,element = layui.element,layer = layui.layer;
        var laypage = layui.laypage;

    });

    


    
</script>
</body>
</html>