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
	<style>
		.show{
			display:block;
		}
	</style>
</head>
<body >
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
		  <%--   <div class="layui-form-item form_code">
				<input class="layui-input"  id="vcode"  name="code" placeholder="验证码" lay-verify="required" type="text" autocomplete="off">
				<div class="code"><img src="<%=request.getContextPath()%>/geetCaptcha/doGet"  width="146" height="36" id="kaptcha" onclick="this.src='<%=request.getContextPath()%>/geetCaptcha/doGet'"></div>
		    </div> --%>
		    
		    <div class="layui-form-item form_code">
		        <div id="captcha1">
		            <div id="wait1" class="show">
			            <div class="layui-progress layui-progress-big" lay-showPercent="true">
						  <div class="layui-progress-bar layui-bg-blue" lay-percent="80%"></div>
						</div>
					</div>
		        </div>
		    </div>
			<button class="layui-btn login_btn" lay-submit="" lay-filter="login">登录</button>
		</form>
	</div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resource/admin/layui/layui.js"></script>
	<script src="http://apps.bdimg.com/libs/jquery/1.9.1/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resource/admin/js/login/gt.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resource/admin/js/login/login.js"></script>
	<script>
	   var handler1 = function (captchaObj) {
	        $("#submit1").click(function (e) {
	            var result = captchaObj.getValidate();
	            if (!result) {
	                $("#notice1").show();
	                setTimeout(function () {
	                    $("#notice1").hide();
	                }, 2000);
	                e.preventDefault();
	            }
	        });
	        // 将验证码加到id为captcha的元素里，同时会有三个input的值用于表单提交
	        captchaObj.appendTo("#captcha1");
	        captchaObj.onReady(function () {
	            $("#wait1").hide();
	        });
	        // 更多接口参考：http://www.geetest.com/install/sections/idx-client-sdk.html
	    };
	    
	  $.ajax({
	        url: "/geetCaptcha/doGet?t=" + (new Date()).getTime(), // 加随机数防止缓存
	        type: "get",
	        dataType: "json",
	        success: function (data) {
	            // 调用 initGeetest 初始化参数
	            // 参数1：配置参数
	            // 参数2：回调，回调的第一个参数验证码对象，之后可以使用它调用相应的接口
	            initGeetest({
	                gt: data.gt,
	                challenge: data.challenge,
	                new_captcha: data.new_captcha, // 用于宕机时表示是新验证码的宕机
	                offline: !data.success, // 表示用户后台检测极验服务器是否宕机，一般不需要关注
	                product: "float", // 产品形式，包括：float，popup
	                width: "100%"
	                // 更多配置参数请参见：http://www.geetest.com/install/sections/idx-client-sdk.html#config
	            }, handler1);
	        }
	    });
	</script>
</body>
</html>