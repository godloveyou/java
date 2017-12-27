layui.config({
	base : "js/"
}).use(['form','layer'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		$ = layui.jquery;
	//video背景
	$(window).resize(function(){
		if($(".video-player").width() > $(window).width()){
			$(".video-player").css({"height":$(window).height(),"width":"auto","left":-($(".video-player").width()-$(window).width())/2});
		}else{
			$(".video-player").css({"width":$(window).width(),"height":"auto","left":-($(".video-player").width()-$(window).width())/2});
		}
	}).resize();
	
	
	$("#vcode").change(function(){
		var code = $("#vcode").val() || '';
		var userverificationcoderegex = /^(-{0,1}\d+){1,3}$/;  
		if(!userverificationcoderegex.test(code)){
			layer.msg("验证码不合法");
			 $("#vcode").val("");
			return false;
		}
	});
	
	//登录按钮事件
	form.on("submit(login)",function(data){
		console.log(JSON.stringify(data));
		//验证验证码合法性
		var code = $("#vcode").val() || '';
		var userverificationcoderegex = /^(-{0,1}\d+){1,3}$/;  
		if(!userverificationcoderegex.test(code)){
			layer.msg("验证码不合法");
			return false;
		}
		
		$.ajax({
			url:'/boss/doLogin',dataType:'json',type:'post',
			data:{'username':data.field.username,'password':data.field.password,"vcode":data.field.code},
			success:function(data){
				if(data.success){
					layer.msg(data.msg);
					window.location.href = "/boss/index";
				}else{
					$("#kaptcha").attr('src',"/validateCode/doGet");
					layer.msg(data.msg);
				}
			}
		});
		return false;
	})
	
})
