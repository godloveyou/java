layui.config({
	base : "js/"
}).use(['form','layer','jquery','element'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		layedit = layui.layedit,
		laydate = layui.laydate,
		$ = layui.jquery;
	var element = layui.element();
	//创建一个编辑器
 	var editIndex = layedit.build('news_content');
 	var addNewsArray = [],addNews;
 	form.on("submit(addNews)",function(data){
 		var cname = $("#cname").val() || '';
 		if(tagname.length<1){
 			  layer.msg("请输入分类名称");
 			  return false;
 		}
 		return true;
 	})
})

