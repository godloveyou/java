//var areaData = address;
var $form;
var form;
var $;
layui.config({
	base : "../../js/"
}).use(['form','layer','upload','laydate'],function(){
	form = layui.form();
	var layer = parent.layer === undefined ? layui.layer : parent.layer;
		$ = layui.jquery;
		$form = $('form');
		laydate = layui.laydate;

	//添加验证规则
    form.verify({
        newPwd : function(value, item){
            if(value.length < 6){
                return "密码长度不能小于6位";
            }
        },
        confirmPwd : function(value, item){
            if(!new RegExp($("#newPwd").val()).test(value)){
                return "两次输入密码不一致，请重新输入！";
            }
        }
    })



    //修改密码
    form.on("submit(changePwd)",function(data){
    	var index = layer.msg('提交中，请稍候',{icon: 16,time:false,shade:0.8});
    	$.ajax({
			url:'/boss/changePwd',dataType:'json',type:'post',
			data:{'username':data.field.username,'pwd':data.field.pwd,'oldPwd':data.field.oldPwd},
			success:function(data){
				layer.msg(data.msg);
			}
		});
    	return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    })

})
