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
		<legend>运营管理</legend>
	</fieldset>
	<div class="my-btn-box">
		<form class="layui-form" onsubmit="return validate(this);" action="<%=path%>/mcpsettlement/operatelist" method="post">
		<span class="" style="margin-left:10px">开始时间</span>
		<span class="">
		<input type="text" class="Wdate" id="startTime" name="startTime" value="${startTime }"
		 onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,maxDate:'#F{$dp.$D(\'endTime\')}'})" /> 
		<span class="" style="margin-left:10px">结束时间</span>
		<input type="text" class="Wdate" id="endTime" name="endTime" value="${endTime }" 
		onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,minDate:'#F{$dp.$D(\'startTime\')}'})" /> 
	   </span>
	   <span class="" style="margin-left:30px">
	   <button class="layui-btn" lay-submit="" lay-filter="demo1">搜索</button>
	    </span>
	    <span style="margin-left:100px">
	    <a  class="layui-btn" href="<%=path%>/mcpsettlement/exportoperate?startTime=${startTime }&endTime=${endTime }">导出Execl</a>
	    </span>
	   </form>
	</div>
	<table class="layui-table">
		<thead>
			<tr>
				<th>开始时间</th>
				<th>结束时间</th>
				<th>消费金额</th>
				<th>分成</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>

			<c:if test="${list!=null}">
				<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
				<c:forEach items="${list}" var="item">
					<tr>
						<td>${item.startTime}</td>
						<td>${item.endTime}</td>
						<td>${item.consume}</td>
						<td>${item.divideconsume}</td>
						<td>
							<button class="layui-btn layui-btn-small layui-btn-danger" onclick="detail('${item.startTime}','${item.endTime}')">详细信息</button>
						</td>
				</c:forEach>
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
        if(startTime==""||endTime=="")  {  
        	alert("请选择查询时间");
            return false;  
        }   
        _loading('数据查询中请耐心等待', 'wait');
        return true;  
    } 
    
    function detail(startTime,endTime){
    	location.href='<%=path%>/mcpsettlement/operatedetail?startTime='+startTime+'&endTime='+endTime;
    }

    
</script>
</body>
</html>