<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>修改密码</title>
    <%@ include file="/WEB-INF/views/include/global.jsp"%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css.css">
</head>
<body class="body">
<fieldset class="layui-elem-field layui-field-title">
		<legend>
			<span class="layui-breadcrumb"></a>
			</span>
		</legend>
	</fieldset>
    <form class="layui-form" method="post">
     <div class="layui-form-item">
            <label class="layui-form-label">新密码：</label>
            <div class="layui-input-block">
                <input type="text" id="newPassword" name="newPassword" class="layui-input"/>
            </div>
        </div>
        <div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" type="submit" value="${op}">修改</button>
			</div>
		</div>
    </form>
<script type="text/javascript">

    layui.use(['element','layer','laypage'], function(){
        var $ = layui.jquery,element = layui.element,layer = layui.layer;
        var laypage = layui.laypage;

    });

    


    
</script>
</body>

</html>