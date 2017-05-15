<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>

<html lang="en">
<head>
<%@ include file="/WEB-INF/views/include/global.jsp"%>
<title>图书管理</title>
</head>
<body class="body">
	<fieldset class="layui-elem-field layui-field-title"
		style="margin-top: 20px;">
		<legend>图书列表</legend>
	</fieldset>
	<table class="layui-table">
		<colgroup>
			<col width="150">
			<col width="150">
			<col width="200">
			<col>
		</colgroup>
		<thead>
			<tr>
				<th>Id</th>
				<th>书名</th>
				<th>作者</th>
				<th>作品状态</th>
				<th>是否收费</th>
				<th>章节数</th>
				<th>审核状态</th>
				<th>审核意见</th>
				<th>上架状态</th>
				<th>章节信息</th>
			</tr>
		</thead>
		<tbody>

			<c:if test="${mcpBooks!=null&&fn:length(mcpBooks)>0}">
				<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
				<c:forEach items="${mcpBooks}" var="mcpBooksDto">
					<tr>
						<td>${mcpBooksDto.id}</td>
						<td>
						<%-- <a class="chapter" href="<%=path%>/mcpbook/detail?bookId=${mcpBooksDto.id}">${mcpBooksDto.name}</a> --%>
					  <button class="layui-btn layui-btn-small layui-btn-normal" onclick="detail('${mcpBooksDto.id}')">${mcpBooksDto.name}
					  </button></td>
						<td>${mcpBooksDto.author}</td>
						<td>${mcpBooksDto.bookstatusDes}</td>
						<td>${mcpBooksDto.chargemodeDes}</td>
						<td>${mcpBooksDto.chaptercount}</td>
						<td>${mcpBooksDto.auditstatusDes}</td>
						<td>${mcpBooksDto.auditinfo}</td>
						<td>${mcpBooksDto.putawaystatusDes}</td>
						<td>
						<%-- <a class="chapter" href="<%=path%>/mcpchapter/list?bookId=${mcpBooksDto.id}">章节信息</a> --%>
							<button class="layui-btn layui-btn-small layui-btn-danger" onclick="chapterlist('${mcpBooksDto.id}')">章节信息</button>
							</td>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
<div class="layui-form">
    <span id="form_page"></span>
    &nbsp;&nbsp;每页&nbsp;${paging.psize}&nbsp;行,共&nbsp;${paging.ptotalpages}&nbsp;页,共&nbsp;${paging.pcount}&nbsp;条数据
</div>
<script type="text/javascript">
//当前页   
var pindex=1;
    layui.use(['element','layer','laypage'], function(){
        var $ = layui.jquery,element = layui.element,layer = layui.layer;
        var laypage = layui.laypage;
        pindex = "${paging.pindex}";// 当前页
        var ptotalpages = "${paging.ptotalpages}";// 总页数
        var pcount = "${paging.pcount}";// 总记录数
        var psize = "${paging.psize}";// 每一页的记录数
        // 分页
        laypage({
            cont : 'form_page', // 页面上的id
            pages : ptotalpages,//总页数
            curr : pindex,//当前页。
            skip : true,
            jump : function(obj, first) {

                $("#pindex").val(obj.curr);//设置当前页
                //防止无限刷新,
                //只有监听到的页面index 和当前页不一样是才出发分页查询
                if (pindex!=""&&pindex!="0"&&(obj.curr != pindex)) {
                	location.href='<%=path%>/mcpbook/list?pindex='+obj.curr;
                }
            }
        });
    });

    function detail(id){
    	location.href='<%=path%>/mcpbook/detail?bookId='+id+'&pindex='+pindex;
    }

    function chapterlist(id){
    	location.href='<%=path%>/mcpchapter/list?bookId='+id+'&pindex='+pindex;
    }

    
</script>
</body>
</html>