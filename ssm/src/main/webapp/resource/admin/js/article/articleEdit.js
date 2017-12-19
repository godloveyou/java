layui.config({
	base : "js/"
}).use(['form','layer','jquery','layedit','laydate','element'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		layedit = layui.layedit,
		laydate = layui.laydate,
		$ = layui.jquery;
	var element = layui.element();
	
	var oldTagIds = $("#oldArticleTags").val().split(",");
	var allTags = $("input[name='tagids']");
	
	for(var i=0;i<allTags.length;i++){
		var tag_i = $(allTags[i]);
		for(var j=0;j<oldTagIds.length;j++){
			var did = tag_i.attr("data-tagid");
			if(did==oldTagIds[j]){
				tag_i.attr("checked","");
			}
		}
	}
	form.render();
	
	//创建一个编辑器
 	var editIndex = layedit.build('news_content');
 	var addNewsArray = [],addNews;
 	
 	form.on("submit(addNews)",function(data){
 		var checkedTags =$('input[name="tagids"]:checked');
 		var checkedTagIds = getCheckedTagIds(checkedTags);
 		if(!checkedTagIds || checkedTagIds.length<1){
 			  layer.msg("请选择文章标签");
 			  return false;
 		}
 		$("#checkedTags").val(checkedTagIds);
 		var editContent = layedit.getContent(editIndex);
 		if(editContent.length<1){
 			  layer.msg("请编写文章内容");
 			  return false;
 		}
 		
 		$("#articleContent").val(editContent);
 		var c = $("#isRecommend").val();
 		if(c && c=='on'){
 			$("#recommend").val("1");
 		}else{
 			$("#recommend").val("0");
 		}
 		console.log(JSON.stringify(data.field));
 		return true;
 	})
 	
 	
 	function getCheckedTagIds(checkedTags){
 		if(!checkedTags){
 			return "";
 		}
 		var tagIdArray=[];
 		for(var i=0;i<checkedTags.length;i++){
 			var o = checkedTags[i];
 			var ov = $(o).attr("data-tagid");
 			tagIdArray.push(ov);
 		}
 		var tagids = tagIdArray.join(",");
 		return tagids;
 		
 	}
 	
	
})

