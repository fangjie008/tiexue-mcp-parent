<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<%@ include file="/WEB-INF/views/include/global.jsp"%>
<title>新增用户</title>
</head>
<body calss="body">

	<fieldset class="layui-elem-field layui-field-title">
		<legend>
			<span class="layui-breadcrumb">修改用户</span>
		</legend>
	</fieldset>
	<form class="layui-form" method="post" commandName="user">
		<div class="layui-form-item">
			<label class="layui-form-label">用户名</label>
			<div class="layui-input-block">
				<input type="text" id="username" name="username"  autocomplete="off" lay-verify="name" class="layui-input" >
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">密码</label>
			<div class="layui-input-block">
				<input type="password" id="password" name="password"  autocomplete="off" lay-verify="password" class="layui-input" >
			</div>
		</div>
		 <div class="layui-form-item">
            <label class="layui-form-label">密码确认</label>
            <div class="layui-input-block">
                <input type="password" id="repassword" name="repassword" placeholder="请确认新密码" lay-verify="chekpassword" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
			<label class='layui-form-label'>权限</label>
			<div class="layui-input-inline">
				<input type="text" id="roles" name="roles"  autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class='layui-form-label'>用户类型：</label>
			<div class="layui-input-inline">
			<select id="type" name="type" lay-verify="required" lay-search="">
             ${type}
            </select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class='layui-form-label'>备注</label>
			<div class="layui-input-inline">
				<input type="text" id="intro" name="intro"  autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit="" lay-filter="demo1">提交</button>
				<button type="reset" onclick="javascript:history.go(-1);" class="layui-btn layui-btn-primary btn-back">返回</button>
			</div>
		</div>
	</form>

	<script type="text/javascript">
layui.use(['form','element','layer'], function(){
    var $ = layui.jquery,element = layui.element,layer = layui.layer;
    var  form = layui.form();
   //自定义验证规则
    form.verify({
    	username: function(value){
            if(value.length < 2){
                return '名称至少得2个字符啊';
            }
            
        },
        password: function(value){
    		if(value.length<6){
    			return '密码不能少于六个字符';
    		}
    		var password= $("#password").val();
    		var repassword= $("#repassword").val();
            if(password!=repassword){
                return '两次输入的密码不一致';
            }
            
        },
      chekpassword: function(value){
		if(value.length<6){
			return '密码不能少于六个字符';
		}
		var password= $("#password").val();
		var repassword= $("#repassword").val();
        if(password!=repassword){
            return '两次输入的密码不一致';
        }
        
    }
    });


	 $(".layui-form").submit(function(){
	 	 //your function l例如 表单验证
		 var isOk=checkName();
    	 if(!isOk){
    		 return false; 
    	 }
	 	 $(this).ajaxSubmit({
	 		url:'<%=path%>/admin/doAddUser',
	 		type:"POST",
	 		dataType:"json",
	     	success:function(data){
	     		if(data.ok){
	     			alert(data.msg);
	     			location.href = '<%=path%>/admin/list';
	     		}else{
	     			alert(data.msg);
	     			location.href = '<%=path%>/admin/list';
	     		}
	     	
	     	},
	     	error:function(data){
	     		alert('保存失败');
	     	}
	 	 });
	     return false;   //防止表单自动提交  
	});   
}); 

function checkName(){
	var isOk=true;
	var username=$("#username").val();
    $.ajax({
    	url:'<%=path%>/admin/checkName',
    	type:"POST",
    	async:false,
		dataType:"json",
		data:{'username':username},
    	success:function(data){
    		if(data.ok==true||data.ok=="true"){
    			alert("用户:"+username+".已存在！");
    			isOk=false;
    		}
    		else{
    			isOk=true;
    		}
    		
    	},
    	error:function(data){
    		isOk=true;
    	}
     
    });
    return isOk;
}

	</script>
</body>
</html>