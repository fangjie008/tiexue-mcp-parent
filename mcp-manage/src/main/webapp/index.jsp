<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<%@ include file="/WEB-INF/views/include/global.jsp" %>
    <title>登录</title>
</head>
<body>

<div class="login-main">
    <header class="layui-elip">后台登录</header>
    <form class="layui-form">
        <div class="layui-input-inline">
            <input type="text" name="account" id="account" required  lay-verify="required" placeholder="邮箱" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-input-inline">
            <input type="password" name="password" id="password" required  lay-verify="required" placeholder="密码" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-input-inline login-btn">
            <button type="button" class="layui-btn" onclick="login()">登录</button>
        </div>
        <!-- <hr/>
        <div class="layui-input-inline">
            <button type="button" class="layui-btn layui-btn-primary">QQ登录</button>
        </div>
        <div class="layui-input-inline">
            <button type="button" class="layui-btn layui-btn-normal">微信登录</button>
        </div>
        <p><a href="javascript:;" class="fl">立即注册</a><a href="javascript:;" class="fr">忘记密码？</a></p> -->
    </form>
</div>

<script type="text/javascript">
	$(document).ready(function(){
	});
	
	function login(){
		var name = $("#account").val();
		var pwd = $("#password").val();
		var form = document.forms[0];
		form.action = '<%=path%>/mcphome/index';
		form.method = "post";
		form.submit();
	}
</script>

<%-- <script src="<%=path%>/static/frame/layui/layui.js"></script>
<script type="text/javascript">
    layui.use(['form'], function () {
        var form = layui.form(), $ = layui.jquery;


    });
</script> --%>
</body>
</html>