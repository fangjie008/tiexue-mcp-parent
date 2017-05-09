<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<%@ include file="/WEB-INF/views/include/global.jsp" %>
    <title>修改密码</title>
</head>
<body class="body">
<fieldset class="layui-elem-field layui-field-title">
	<legend>
		<span class="layui-breadcrumb">
		 <a href="javascript:;">修改密码</a>
		</span>
	</legend>
</fieldset>
<form class="layui-form"  method="post" >
    <div class="layui-form-item">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-block">
                <input type="text"  name="username" placeholder="请输入用户名" value="${baseInfo.name }" class="layui-input" readonly="">
                <input type="hidden" id="cpid" name="cpid" value="${baseInfo.cpid}">
                <input type="hidden" id="md5password" name="md5password" value="${baseInfo.password}">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">原密码</label>
            <div class="layui-input-block">
                <input type="password" id="oldpassword"  name="oldpassword" placeholder="请输入原密码" lay-verify="required" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">新密码</label>
            <div class="layui-input-block">
                <input type="password" id="password" name="password" placeholder="请输入新密码" lay-verify="newpassword" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">新密码确认</label>
            <div class="layui-input-block">
                <input type="password" id="repassword" name="repassword" placeholder="请确认新密码" lay-verify="chekpassword" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="demo1">修改</button>
       </div>
    </div>
</form>
<script type="text/javascript">
layui.use(['form','element','layer'], function(){
    var $ = layui.jquery,element = layui.element,layer = layui.layer;
    var  form = layui.form();
   //自定义验证规则
    form.verify({
    	newpassword: function(value){
    		if(value.length<6){
    			return '新密码不能少于六个字符';
    		}
    		var password= $("#password").val();
    		var repassword= $("#repassword").val();
            if(password!=repassword){
                return '两次输入的密码不一致';
            }
            
        },
     chekpassword: function(value){
		if(value.length<6){
			return '新密码不能少于六个字符';
		}
		var password= $("#password").val();
		var repassword= $("#repassword").val();
        if(password!=repassword){
            return '两次输入的密码不一致';
        }
        
    }
       
    });

});
    $(".layui-form").submit(function(){
    	 //your function l例如 表单验证
    	 var isOk=checkName();
    	 if(!isOk){
    		 return false; 
    	 }
    	 $(this).ajaxSubmit({
    		url:'<%=path%>/mcphome/savepassword.do',
    		type:"POST",
    		dataType:"json",
        	success:function(data){
        		if(data.ok){
        			alert(data.msg);
        			location.href = '<%=path%>/';
        		}else{
        			alert(data.msg);
        			//登录超时
        			if(data.loginStatus!=undefined&&data.loginStatus=="-1"){
        				location.href = '<%=path%>/';
        			}
        		}
        	
        	},
        	error:function(data){
        		alert('保存失败');
        	}
        	});   
         return false;   //防止表单自动提交  
    });  
    
    function checkName(){
    	var isOk=true;
    	var md5password=$("#md5password").val();
    	var oldpassword=$("#oldpassword").val();
        $.ajax({
        	url:'<%=path%>/mcphome/check',
        	type:"POST",
        	async:false,
    		dataType:"json",
    		data:{'oldpassword':oldpassword,'md5password':md5password},
        	success:function(data){
        		if(data.ok==false||data.ok=="false"){
        			alert("密码错误！");
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