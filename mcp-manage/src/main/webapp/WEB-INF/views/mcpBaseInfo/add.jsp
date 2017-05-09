<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/global.jsp" %>
    <title>新增基础信息</title>
</head>
<body class="body">
<fieldset class="layui-elem-field layui-field-title">
	<legend>
		<span class="layui-breadcrumb">
		    <a href="javascript:;">基础信息</a>
            <a><cite>
            <c:if test="${mcpBaseInfo==null}">新增</c:if>
             <c:if test="${mcpBaseInfo!=null}">修改</c:if>            
           </cite></a>
		</span>
	</legend>
</fieldset>
<form class="layui-form"  method="post" >
	<div class="layui-form-item">
		<label class="layui-form-label">合作者Id
		</label>
		 <div class="layui-input-inline">
            <input type="text" id="cpid" name="cpid"  value="${mcpBaseInfo.cpid}"   readonly="true"  class="layui-input">
        </div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">合作者名称
		</label>
		 <div class="layui-input-block">
            <input type="text" id="name" name="name"  value="${mcpBaseInfo.name}" autocomplete="off" placeholder="请输入合作者名称" lay-verify="name"
                  class="layui-input">
        </div>
	</div>
	<div class="layui-form-item">
		<label class='layui-form-label'>联系人姓名</label>
		<div class="layui-input-inline">
			<input type="text" name="contname" value="${mcpBaseInfo.contname}"  autocomplete="off" placeholder="请输入联系人姓名" lay-verify="required" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class='layui-form-label'>联系人邮箱</label>
		<div class="layui-input-block">
			<input type="text" name="contemail" value="${mcpBaseInfo.contemail}"  autocomplete="off" placeholder="请输入联系人邮箱" lay-verify="contemail" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class='layui-form-label'>联系人QQ</label>
		<div class="layui-input-block">
			<input type="text" name="contqq" value="${mcpBaseInfo.contqq}"  autocomplete="off" placeholder="请输入联系人QQ" lay-verify="contqq" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class='layui-form-label'>联系人电话</label>
		<div class="layui-input-inline">
			<input type="text" name="contphone" value="${mcpBaseInfo.contphone}"  autocomplete="off" placeholder="请输入联系人电话" lay-verify="contphone" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class='layui-form-label'>通讯地址</label>
		<div class="layui-input-block">
			<input type="text" name="address" value="${mcpBaseInfo.address}"   autocomplete="off" placeholder="请输入通讯地址" lay-verify="required" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class='layui-form-label'>邮编</label>
		<div class="layui-input-inline">
			<input type="text" name="zipcode" value="${mcpBaseInfo.zipcode}"  autocomplete="off" placeholder="请输入邮编" lay-verify="zipcode" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class='layui-form-label'>开户行名称</label>
		<div class="layui-input-block">
			<input type="text" name="bankname" value="${mcpBaseInfo.bankname}"  autocomplete="off" placeholder="请输入开户行名称" lay-verify="required" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class='layui-form-label'>银行账号名称</label>
		<div class="layui-input-block">
			<input type="text" name="bankaccountname" value="${mcpBaseInfo.bankaccountname}"  autocomplete="off" placeholder="请输入银行账号名称" lay-verify="required" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class='layui-form-label'>银行账号</label>
		<div class="layui-input-block">
			<input type="text" name="bankaccountnum" value="${mcpBaseInfo.bankaccountnum}"  autocomplete="off" placeholder="请输入银行账号" lay-verify="required" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class='layui-form-label'>双方加密验证Key</label>
		<div class="layui-input-block">
			<input type="text" name="appkey" value="${mcpBaseInfo.appkey}"  autocomplete="off" placeholder="请输入双方加密验证Key" lay-verify="required" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class='layui-form-label'>接口1</label>
		<div class="layui-input-block">
			<input type="text" name="interfaceurl1" value="${mcpBaseInfo.interfaceurl1}"   autocomplete="off" placeholder="请输入接口1" lay-verify="required" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class='layui-form-label'>接口2</label>
		<div class="layui-input-block">
			<input type="text" name="interfaceurl2" value="${mcpBaseInfo.interfaceurl2}"  autocomplete="off" placeholder="请输入接口2" lay-verify="required" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class='layui-form-label'>接口3</label>
		<div class="layui-input-block">
			<input type="text" name="interfaceurl3"  value="${mcpBaseInfo.interfaceurl3}" autocomplete="off" placeholder="请输入接口3" lay-verify="required" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class='layui-form-label'>接口4</label>
		<div class="layui-input-block">
			<input type="text" name="interfaceurl4" value="${mcpBaseInfo.interfaceurl4}"  autocomplete="off" placeholder="请输入接口4" lay-verify="required" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
             <c:if test="${mcpBaseInfo==null}">
             <button type="reset" class="layui-btn layui-btn-primary">重置</button>
             </c:if>
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
        name: function(value){
            if(value.length < 2){
                return '合作者名称至少得2个字符啊';
            }
            
        }
        ,contemail:[/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/ ,'邮编格式不正确']
        ,contqq:[/^\d{5,20}$/,'qq号必须是数字']
        ,contphone: [ /^((0\d{2,3}-\d{7,8})|(1[3456789]\d{9}))$/, '联系电话不正确']
        ,zipcode:[/^\d{6,}$/,'邮编必须输六位数字']
       
    });

});
    $(".layui-form").submit(function(){
    	 //your function l例如 表单验证
    	 var isOk=checkName();
    	 if(!isOk){
    		 return false; 
    	 }
    		
    	 
    	 $(this).ajaxSubmit({
    		url:'<%=path%>/mcpbaseinfo/save.do',
    		type:"POST",
    		dataType:"json",
        	success:function(data){
        		if(data.ok){
        			alert(data.msg);
        			location.href = '<%=path%>/mcpbaseinfo/list.do?pindex='+"${pindex}";
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
    	var cpid=$("#cpid").val();
    	var name=$("#name").val();
        $.ajax({
        	url:'<%=path%>/mcpbaseinfo/checkName',
        	type:"POST",
        	async:false,
    		dataType:"json",
    		data:{'name':name,'cpid':cpid},
        	success:function(data){
        		if(data.ok==true||data.ok=="true"){
        			alert("合作者名称已存在！");
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