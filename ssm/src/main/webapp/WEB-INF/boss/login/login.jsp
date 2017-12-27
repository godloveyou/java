<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>登录</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/admin/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/admin/css/login.css" media="all" />
</head>
<body>
	<video class="video-player" preload="auto" autoplay="autoplay" loop="loop" data-height="1080" data-width="1920" height="1080" width="1920">
	    <source src="login.mp4" type="<%=request.getContextPath()%>/boss/login/login.mp4">
	</video>
	<div class="video_mask"></div>
	<div class="login">
	    <h1>管理登录</h1>
	    <input type="hidden" id="projectPath" value="<%=request.getContextPath()%>">
	    <form class="layui-form">
	    	<input type="hidden" name="token" value="${token}" />
	    	<div class="layui-form-item">
				<input class="layui-input" name="username" placeholder="用户名" lay-verify="required" type="text" autocomplete="off">
		    </div>
		    <div class="layui-form-item">
				<input class="layui-input" name="password" placeholder="密码" lay-verify="required" type="password" autocomplete="off">
		    </div>
		    <div class="layui-form-item form_code">
				<input class="layui-input"  id="vcode"  name="code" placeholder="验证码" lay-verify="required" type="text" autocomplete="off">
				<div class="code"><img src="<%=request.getContextPath()%>/validateCode/doGet"  width="146" height="36" id="kaptcha" onclick="this.src='<%=request.getContextPath()%>/validateCode/doGet'"></div>
		    </div>
			<button class="layui-btn login_btn" lay-submit="" lay-filter="login">登录</button>
		</form>
	</div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resource/admin/layui/layui.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resource/admin/js/login/login.js"></script>
</body>
</html>