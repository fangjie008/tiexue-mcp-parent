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
<body id="wait" class="body">
	<fieldset class="layui-elem-field layui-field-title"
		style="margin-top: 20px;">
		<legend>结算管理</legend>
	</fieldset>
	<div class="my-btn-box">
		<form class="layui-form" onsubmit="return validate(this);" action="<%=path%>/mcpsettlement/list" method="post">
		<span class="" style="margin-left:10px">结算月份</span>
		<span class="">
		<input type="text" class="Wdate" id="dateTime" name="dateTime" value="${dateTime }" onfocus="WdatePicker({dateFmt:'yyyy-MM',readOnly:true})" /> 
	   </span>
	   <span class="" style="margin-left:30px">
	   <button class="layui-btn" lay-submit="" lay-filter="demo1">搜索</button>
	    </span>
	   </form>
	</div>
	<table class="layui-table">
		<thead>
			<tr>
				<th>结算月份</th>
				<th>单月收入</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>

			<c:if test="${list!=null}">
				<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
				<c:forEach items="${list}" var="item">
					<tr>
						<td>${item.monthly}</td>
						<td>${item.amount}</td>
						<td>
							<button class="layui-btn layui-btn-small layui-btn-danger" onclick="detail('${item.monthly}')">详细信息</button>
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
    	var datetime=$("#dateTime").val();
        if(datetime=="")  {  
        	alert("请选择查询时间");
            return false;  
        }   
        _loading('数据查询中请耐心等待', 'wait');
        return true;  
    } 
    
    function detail((monthly){
    	location.href='<%=path%>/mcpsettlement/detail?monthly='+monthly;
    }
<%--   $(".layui-form").submit(function(){
	  var datetime=$("#dateTime").val();
	  if(datetime==""){
		  alert("请选择查询时间");
		  return false;
	  }
	  $(this).ajaxSubmit({
		  url:'<%=path%>/mcpsettlement/list',
		  type:'POST',
		  success:function(){}
	  });
	  _loading('数据查询中请耐心等待', 'wait');
  }); --%>

    
</script>
</body>
</html>