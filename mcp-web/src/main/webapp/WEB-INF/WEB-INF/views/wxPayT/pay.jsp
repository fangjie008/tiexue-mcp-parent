<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/WEB-INF/views/include/include_base.jsp"%>
<title>充值</title>
</head>

<body>
<input type="hidden" id="bookid" name="bookid" value="${bookid }">
<input type="hidden" id="chapterid" name="chapterid" value="${chapterid }">
	<header class="nav wrap">
		<a class="ico52 back" href="javascript:history.go(-1);"></a>充值小说币<a
			href="<%=path%>/" class="ico52 home"></a>
	</header>
	<div class="mod_content panel c1  "
		style="padding-top: 8px; padding-bottom: 2px;">
		<ul class="gray">
			<li>充值账号：<label class="orange">${user.name }</label><br>
				用户余额：<label class="orange">${user.coin}</label>小说币
			</li>
		</ul>
	</div>
	<div class="mod_content gray c1 ">
		<h1 style="border: none">选择充值渠道</h1>
		<ul class="channellist channellistnew" style="width: 100%;">
			<a href="#">
				<li class="paychannel" style="width: 100%"><span
					class="current btn-paychannel" data-channel="13"> <img
						src="<%=path%>/static/image/user/weixin.png" alt=""
						class="charge_wx"> <img
						src="<%=path%>/static/image/user/orangeTriangle.png" alt=""
						class="charge_wayImg"> | 微信支付
				</span></li>
			</a>
		</ul>
	</div>
	<ul class="pay_money moneylist margin-top-10">
		
		<li><span data-money="3000" data-premium="300" data-type="1">
		    3000+300小说币<br>
			<label>30元</label><br>
			<label>送3元(10%)</label></span></li>
		<li><span data-money="5000" data-premium="900" data-type="1">5000+900小说币<br>
			<label>50元</label>
			<label>送9元(18%)</label>
			</span></li>
		<li><span data-money="10000" data-premium="2000" data-type="1">10000+2000小说币<br>
			<label>100元</label>
			<label>送20元(20%)</label>
			</span></li>
		<li><span data-money="20000" data-premium="5000" data-type="1">20000+5000小说币<br>
			<label>200元</label>
			<label>送50元(25%)</label>
		</span></li>
		<li><span data-money="50000" data-premium="15000" data-type="1">50000+15000小说币<br>
			<label>500元</label>
			<label>送150元(30%)</label>
		</span></li>
		<li><span data-money="100000" data-premium="35000" data-type="1">100000+35000小说币<br>
			<label>1000元</label>
			<label>送350元(35%)</label>
		</span></li>
	</ul>
	
	
	<div class="mod_content c1 gray">
		<ul class="gray small">
			<li class="orange">温馨提示：
			</li>
			<li>包年只能直接购买，不可以用小说币兑换。</li>
			<li>包年时间是365天，半年是180天，重复购买时间会累加。</li>
			<li>充值阅读权限仅限本站使用
			  <!--  测试充值 -->
				<span class="testPay">&nbsp;&nbsp;<span data-money="1" data-premium="10" data-type="1">
				<label>&nbsp;</label></span>
				<span data-money="3" data-premium="10" data-type="1">
				<label>&nbsp;</label></span>
				</span> 
		   </li>
			<li>充值小说币为虚拟物品，不支持退款！</li>
		</ul>
	</div>

	 
<div style="background-color: rgba(50, 201, 186, 0.7);padding: 8px 10px;">
	<a href="http://t.cn/R6LUaeS">
		点击关注公众号“<span style="color:#2897ed;border-bottom:1px solid">五彩读书网</span>”阅读更多精彩美文
	</a>
</div>
 
	<script>
		$(".pay_money li").click(function(){
			var bookid=$("#bookid").val();
			var chapterid=$("#chapterid").val();
			var span = $(this).find("span");
			var money = span.attr("data-money");
			var premium = span.attr("data-premium");
			var type = span.attr("data-type");
			
			var form = $("<form></form>",{ 
				'method':'post', 
				'action':'ipay_now', 
				'style':'display:none' 
				}).appendTo($("body")); 
			
			form.append($("<input>",{'type':'hidden','name':'money','value':money})); 
			form.append($("<input>",{'type':'hidden','name':'premium','value':premium})); 
			form.append($("<input>",{'type':'hidden','name':'type','value':type})); 
			form.append($("<input>",{'type':'hidden','name':'bookid','value':bookid})); 
			form.append($("<input>",{'type':'hidden','name':'chapterid','value':chapterid})); 
			form.submit();
		});
		
		
		$(".testPay ").click(function(){
			var bookid=$("#bookid").val();
			var chapterid=$("#chapterid").val();
			var span = $(this).find("span");
			var money = span.attr("data-money");
			var premium = span.attr("data-premium");
			var type = span.attr("data-type");
			
			var form = $("<form></form>",{ 
				'method':'post', 
				'action':'ipay_now', 
				'style':'display:none' 
				}).appendTo($("body")); 
			
			form.append($("<input>",{'type':'hidden','name':'money','value':money})); 
			form.append($("<input>",{'type':'hidden','name':'premium','value':premium})); 
			form.append($("<input>",{'type':'hidden','name':'type','value':type})); 
			form.append($("<input>",{'type':'hidden','name':'bookid','value':bookid})); 
			form.append($("<input>",{'type':'hidden','name':'chapterid','value':chapterid})); 
			form.submit();
		});
	</script>
</body>
</html>
