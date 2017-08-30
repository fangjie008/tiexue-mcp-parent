<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<%@ include file="/WEB-INF/views/include/global.jsp"%>
<title>推广列表</title>

<script>
	function getUrl(sign, bookId) {

		var c = localStorage.getItem(bookId + "_chapterId") || "";

		var chapterId = prompt("输入落地章节的ID，小说ID=" + bookId, c);
		if (!chapterId)
			return;
		if (!/^\d+$/.test(chapterId)) {
			alert("章节id必须是数字");
			return;
		}

		localStorage.setItem(bookId + "_chapterId", chapterId);

		var txt = "http://wx.top657.cn/outshow/index?bookId=" + bookId
				+ "&chapterId=" + chapterId + "&fm="+sign
		var div = document.createElement("p");
		div.innerHTML = txt;
		document.body.insertBefore(div, document.body.childNodes[0]);
	}
</script>
</head>
<body class="body">
	<fieldset class="layui-elem-field layui-field-title">
		<legend>
			<span class="layui-breadcrumb"> <a href="javascript:;">推广列表</a> <a><cite>查看</cite></a>
			</span>
		</legend>
	</fieldset>

	<div class="my-btn-box">
		<span class="f1"> <a class="layui-btn btn-add btn-default" href="<%=path%>/wxplatform/detail">新增推广</a>
		</span>
	</div>
	<table id="dateTable" class="layui-table">
		<thead>
			<tr>
				<th>ID</th>
				<th>推广标识</th>
				<th>推广公共号</th>
				<th>小说id</th>
				<th>小说名</th>
				<th>推广费用</th>
				<th>备注</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${platformSigns}" var="info">
				    <td>${info.id}</td>
					 <td>${info.sign}</td>
					<td>${info.platformname}</td>
					<td>${info.novelid}</td>
					<td>${info.novelname}</td>
					<td>${info.moneyall}</td>
					<td>${info.remark}</td>
					<td>
						<button class="layui-btn layui-btn-small layui-btn-normal" onclick="stats('${info.sign}')">统计</button>
						<button class="layui-btn layui-btn-small layui-btn-normal" onclick="edit('${info.id}')">编辑</button>
						<button class="layui-btn layui-btn-small layui-btn-danger" onclick="deleteBaseInfo('${info.id}')">删除</button>
				    	<button class="layui-btn layui-btn-small layui-btn-danger" onclick="getUrl('${info.sign}','${ info.novelid}')">生成推广链接</button>
						
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="layui-form">
		<span id="form_page"></span> &nbsp;&nbsp;每页&nbsp;${paging.psize}&nbsp;行,共&nbsp;${paging.ptotalpages}&nbsp;页,共&nbsp;${paging.pcount}&nbsp;条数据
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
                	location.href='<%=path%>/wxplatform/list?pindex='+obj.curr;
                }
            }
        });
    });
    function stats(sign){
    	location.href='<%=path%>/paystats/index?sign='+sign;
    }
    function edit(id){
    	location.href='<%=path%>/wxplatform/detail?id='+id+'&pindex='+pindex;
    }
    function deleteBaseInfo(id){
    	$.ajax({
    		url:'<%=path%>/wxplatform/deleteModel?id='+id
    	    ,type:"post"
    	    ,dataType:"json"
    	    ,success:function(data){
    	    	if(data.ok){
        			alert(data.msg);
        			location.href ='<%=path%>/wxplatform/list?pindex='+pindex;
        		}else{
        			alert(data.msg);
        			//登录超时
        			if(data.loginStatus!=undefined&&data.loginStatus=="-1"){
        				location.href = '<%=path%>/';
						}
					}
				},
				error : function(e) {
					alert('保存失败');
				}

			});
		}
   
	</script>
</body>
</html>