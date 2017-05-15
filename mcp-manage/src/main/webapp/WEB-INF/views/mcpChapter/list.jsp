<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/WEB-INF/views/include/global.jsp"%>
<title>章节信息</title>
</head>
<body class="body">
	<fieldset class="layui-elem-field layui-field-title"
		style="margin-top: 20px;">
		<legend>章节列表</legend>
	</fieldset>
	<div class="my-btn-box">
<span class="f1">
	<a class="layui-btn btn-add btn-default"  href="#" onclick="javascript:history.go(-1);">返回</a>
</span>
</div>
<input type="hidden" id="bookId" name="bookId" value="${bookId}">
	<table class="layui-table">
		<thead>
			<tr>
				<th>Id</th>
				<th>名称</th>
				<th>章节字数</th>
				<th>审核状态</th>
				<th>审核意见</th>
			</tr>
		</thead>
		<tbody>
		
			<c:if test="${mcpChapters!=null&&fn:length(mcpChapters)>0}">
				<c:forEach items="${mcpChapters}" var="mcpChapterDto">
					<tr>
						<td>${mcpChapterDto.id}</td>
						<td>
						<%-- <a class="chapter"  href="<%=path%>/mcpchapter/detail?chapterId=${mcpChapterDto.id}">${mcpChapterDto.name}</a> --%>
						<button class="layui-btn layui-btn-small layui-btn-danger" onclick="detail('${mcpChapterDto.id}')">${mcpChapterDto.name}</button>
						</td>
						<td>${mcpChapterDto.words}</td>
						<td>${mcpChapterDto.auditstatusDes}</td>
						<td>${mcpChapterDto.auditinfo}</td>
						</tr>
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
                	location.href='<%=path%>/mcpchapter/list?bookId='+$("#bookId").val()+'&pindex='+obj.curr;
                }
            }
        });
    });

    function detail(id){
    	location.href='<%=path%>/mcpchapter/detail?chapterId='+id+'&pindex='+pindex;
    }


    
</script>
</body>
</html>