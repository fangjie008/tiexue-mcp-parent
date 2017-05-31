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
<input type="hidden" id="fromurl" name="fromurl" value="${fromurl }">
	<header class="nav wrap">
		<a class="ico52 back" href="javascript:history.go(-1);"></a>充值小说币<a
			href="<%=path%>/?fm=${fromurl}" class="ico52 home"></a>
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
		    <span>3000+300小说币</span>
			<dl>30元</dl>
			<label>送3元(10%)</label>
			</span></li>
		<li ><span style="border: 1px solid #ff6600;" data-money="5000" data-premium="900" data-type="1">
		<div class="top_up_send">热</div>
			<span>5000+900小说币</span>
			<dl>50元</dl>
			<label>送9元(18%)</label>
			</span></li>
		<li><span data-money="10000" data-premium="2000" data-type="1">
			<span>10000+2000小说币</span>
			<dl>100元</dl>
			<label>送20元(20%)</label>
			</span></li>
		<li><span data-money="20000" data-premium="5000" data-type="1">
			<span>20000+5000小说币</span>
			<dl>200元</dl>
			<label>送50元(25%)</label>
		</span></li>
		<li><span data-money="50000" data-premium="15000" data-type="1">
			<span>50000+15000小说币</span>
			<dl>500元</dl>
			<label>送150元(30%)</label>
		</span></li>
		<li><span data-money="100000" data-premium="35000" data-type="1">
			<span>100000+35000小说币</span>
			<dl>1000元</dl>
			<label>送350元(35%)</label>
		</span></li>
	</ul>
	
	 <a class="btn block" href="#" onclick="defaultPay();">确认充值</a> 
<!-- 	<ul class="pay_money moneylist">
		<h1 style="padding-left: 10px; margin-top: 10px;">
			<font color="red">↓↓包年更划算，全站作品免费看🔥</font>
		</h1>
		<li><span class="" data-money="36500" data-premium="12"
			data-type="2">365元（包年）<br>
			<label>全站作品免费看</label></span></li>
		<li><span data-money="29800" data-premium="6" data-type="2">298元（半年）<br>
			<label>全站作品免费看</label></span></li>
	</ul> -->
	
	<div class="mod_content c1 gray">
	<ul class="gray small">
			<!-- <li style="color:red;font-size:20px;">首充优惠活动：
			</li>
			<li>用户第一次充值可获得小说币<span style="color:red;font-size:16px;">加倍</span>的奖励。即:充值30元即可获得6300小说币。以此类推。</li>
		</ul> -->
		<ul class="gray small">
			<li class="orange">温馨提示：
			</li>
<!-- 			<li>包年只能直接购买，不可以用小说币兑换。</li>
			<li>包年时间是365天，半年是180天，重复购买时间会累加。</li> -->
			<li>充值阅读权限仅限本站使用
			  <!--  测试充值 -->
				<span class="testPay">&nbsp;&nbsp;<span data-money="1" data-premium="0" data-type="1">
				<label>&nbsp;</label></span>
				</span> 
		   </li>
			<li>充值小说币为虚拟物品，不支持退款！</li>
		</ul>
	</div>

	 
<div style="background-color: rgba(50, 201, 186, 0.7);padding: 8px 10px;">
	<a style="color:white" href="http://t.cn/R6LUaeS">
		点击关注公众号“<span style="color:#2897ed;border-bottom:1px solid">五彩读书网</span>”方便下次继续阅读
	</a>
</div>
 
	<script>
		$(".pay_money li").click(function(){
			var bookid=$("#bookid").val();
			var chapterid=$("#chapterid").val();
			var fromurl=$("#fromurl").val();
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
			form.append($("<input>",{'type':'hidden','name':'fromurl','value':fromurl})); 
			form.submit();
		});
		
		
		function defaultPay(){
			var bookid=$("#bookid").val();
			var chapterid=$("#chapterid").val();
			var fromurl=$("#fromurl").val();
			var money = 5000;
			var premium =900;
			var type = 1;
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
			form.append($("<input>",{'type':'hidden','name':'fromurl','value':fromurl})); 
			form.submit();
		}
		
		$(".testPay ").click(function(){
			var bookid=$("#bookid").val();
			var chapterid=$("#chapterid").val();
			var fromurl=$("#fromurl").val();
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
			form.append($("<input>",{'type':'hidden','name':'fromurl','value':fromurl})); 
			form.submit();
		});
	</script>
</body>
</html>
