<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/WEB-INF/views/include/include_base.jsp"%>
<title>订阅章节</title>
</head>

<body>
<header class="nav wrap">
   <a class="ico52 back" href="javascript:history.go(-1);"></a>
   <a href="#" style="color:#fff;">章节订阅</a>
   <a href="<%=path%>/?fm=${fromurl}" class="ico52 home"></a>
</header>
<div class="mod_content pb">
<input type="hidden" id="userId" name="userId" value="${user.id}">
<input type="hidden" id="chapterId" name="chapterId" value="${chapter.id}">
<input type="hidden" id="bookId"  name="bookId" value="${chapter.bookid}">
	<ul>
            <li class="gray">章节：${chapter.title}</li>
            <li class="gray">字数：${chapter.contentlen }</li>
            <li class="gray">价格： <label class="orange">${chapter.pirce}</label> 小说币</li>
            <li class="gray">余额：  ${user.coin }</li>
            <li class="gray">账户：${user.name}</li>
            <li class="gray"><input id="autopay" name="autopay" type="checkbox" value="" readonly="readonly"  checked="checked" >自动购买，下一章不再提醒。</li>
            <li>
            <c:if test="${user.coin<chapter.pirce }"><a class="btn block" href="<%=path%>/wxPay/pay?bookid=${chapter.bookid}&chapterid=${chapter.id}&fm=${fromurl}"><font color="white">余额不足？花点小钱充值»</font></a></c:if>
            <c:if test="${user.coin>=chapter.pirce }"><a id="consume_id" class="btn block" href="#" onclick="onconsume()"><font color="white">订阅</font></a></c:if>
            </li>
                                
                
	</ul>
</div>
<div class="mod_content c1">
	<ul class="gray small">
		<li class="orange">温馨提示：</li>
<!-- 		<li style="color:red;font-size:14px;">首次充值还有加倍奖励！！！</li> -->
		<li>为了给您更好的阅读体验，如果您选择订阅，阅读本作品其他VIP章节将直接扣费，并不再提示该页面。</li>
	</ul>
</div>
<div style="background-color: rgba(50, 201, 186, 0.7);padding: 8px 10px;">
	<a style="color:white" href="http://t.cn/R6LUaeS">
		点击关注公众号“<span style="color:#2897ed;border-bottom:1px solid">五彩读书网</span>”方便下次继续阅读
	</a>
</div>
<%@ include file="/WEB-INF/views/include/include_footer.jsp"%>
</body>
<script type="text/javascript">
	function onconsume(){
		var autopay="0";
		if($("#autopay").prop('checked')){
			autopay="1";
		}
		var bookId= $("#bookId").val();
		var chapterId= $("#chapterId").val();
		var userId= $("#userId").val();
		var postData={'bookId':bookId,'chapterId':chapterId,'userId':userId,'autopay':autopay}
		$.ajax({
			url:"<%=path%>/wxConsume/handleOrder",
			data:postData,
			type:"post",
			dataType:"json",
			success:function(res){
				if(res.ok){
				window.location.href ='<%=path%>/wxChapterSub/index?bookId='+bookId+'&chapterId='+chapterId+'&userId='+userId;
				}
				else{
					alert("消费失败");
					console.log(res.msg);
				}
				
			},
			error:function(res){
				//alert("失败");
			}
		});
	}
</script>
</html>

