<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<%@ include file="/WEB-INF/views/include/global.jsp" %>
    <title>五彩小说合作平台</title>
</head>
<body>

<!-- admin -->
<div class="layui-layout layui-layout-admin"> <!-- 添加skin-1类可手动修改主题为纯白，添加skin-2类可手动修改主题为蓝白 -->
    <!-- header -->
    <div class="layui-header my-header">
        <a href="<%=path%>/mcphome/homepage">
            <!--<img class="my-header-logo" src="" alt="logo">-->
            <div class="my-header-logo">五彩小说合作平台</div>
        </a>
        <div class="my-header-btn">
            <button class="layui-btn layui-btn-small btn-nav"><i class="layui-icon">&#xe620;</i></button>
        </div>

        <ul class="layui-nav my-header-user-nav" lay-filter="side-right">
          
            <li class="layui-nav-item">
                <a class="name" href="javascript:;"><i class="layui-icon">&#xe629;</i>主题</a>
                <dl class="layui-nav-child">
                    <dd data-skin="0"><a href="javascript:;">默认</a></dd>
                    <dd data-skin="1"><a href="javascript:;">纯白</a></dd>
                    <dd data-skin="2"><a href="javascript:;">蓝白</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a class="name" href="javascript:;"><img src="<%=path%>/static/image/code.png" alt="logo"> ${baseInfo.contname} </a>
                <dl class="layui-nav-child">
                    <dd><a href="<%=path%>/"><i class="layui-icon">&#x1006;</i>退出</a></dd>
                </dl>
            </li>
        </ul>
    </div>
    <!-- side -->
    <div class="layui-side my-side">
        <div class="layui-side-scroll">
            <ul class="layui-nav layui-nav-tree" lay-filter="side">

              <li class="layui-nav-item layui-nav-itemed"><!-- 去除 layui-nav-itemed 即可关闭展开 -->
                    <a href="javascript:;"><i class="layui-icon">&#xe620;</i>功能</a>
                    <dl class="layui-nav-child">
                       
                        <dd class="layui-nav-item"><a href="javascript:;" href-url="<%=path%>/mcpbaseinfo/list.do">
                        <i class="layui-icon">&#xe621;</i>基础信息</a></dd>
                         <dd class="layui-nav-item"><a href="javascript:;" href-url="<%=path%>/mcpbook/list">
                        <i class="layui-icon">&#xe621;</i>作品管理(cp)</a></dd>
                        <dd class="layui-nav-item"><a href="javascript:;" href-url="<%=path%>/mcphome/edit.do">
                        <i class="layui-icon">&#xe621;</i>修改密码</a></dd>
                        <dd class="layui-nav-item"><a href="javascript:;" href-url="<%=path%>/mcpsettlement/data">
                        <i class="layui-icon">&#xe621;</i>运营数据</a></dd>
                        <dd class="layui-nav-item"><a href="javascript:;" href-url="<%=path%>/mcpsettlement/list">
                        <i class="layui-icon">&#xe621;</i>结算数据</a></dd>
                        <dd class="layui-nav-item"><a href="javascript:;" href-url="<%=path%>/admin/list">
                        <i class="layui-icon">&#xe621;</i>用户管理</a></dd>
                       
                    </dl>
                </li>
            </ul>
        </div>
    </div>
    <!-- body -->
    <div class="layui-body my-body">
        <div class="layui-tab layui-tab-card my-tab" lay-filter="card" lay-allowClose="true">
            <ul class="layui-tab-title">
                <li class="layui-this" lay-id="0"><span>欢迎页</span></li>
            </ul>
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show">
                    <iframe id="iframe" src="<%=path%>/mcphome/welcome" frameborder="0"></iframe>
                </div>
            </div>
        </div>
    </div>
    <!-- footer -->
    <div class="layui-footer my-footer">
        <p>五彩小说合作平台</p>
        <p>2017 ©copyright </p>
    </div>
</div>
<script type="text/javascript" src="<%=path%>/static/frame/layui/layui.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/index.js"></script>
</body>
</html>