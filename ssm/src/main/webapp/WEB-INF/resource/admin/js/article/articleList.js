layui.config({
	base : "js/"
}).use(['form','layer','jquery','laypage'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		$ = layui.jquery;
	
	var msg = $("#tipmsg").text()|| '';
	if(msg.length>0){
		// layer.msg(msg,{ area: ['100px', '60px'],offset: 'rt'});
		 layer.msg(msg);
	}
	
	laypage({
	curr: $("#curr").val(),
	cont : "page",
	pages : $("#totalPage").val(),
	jump : function(obj,first){
//    	console.log("obj=="+obj.curr);
//    	console.log("limit=="+obj.limit);
//    	console.log("first=="+first);
    	if(!first){
    		location.href="/boss/article/list?curr="+obj.curr;
    	}
	}
})
	
 	form.on("submit(queryArticle)",function(data){
 		
 	});
	
//查询
$(".search_btn").click(function(){
	var _title = $("#title").val() || '';
	if(_title.length<1){
		return;
	}
	$("#queryForm").submit();
});
	//添加文章
	//改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
	$(window).one("resize",function(){
		$(".newsAdd_btn").click(function(){
			location.href="/boss/article/add";
//			var index = layui.layer.open({
//				title : "添加文章",
//				type : 2,
//				content : "/boss/article/add",
//				success : function(layero, index){
//					setTimeout(function(){
//						layui.layer.tips('点击此处返回文章列表', '.layui-layer-setwin .layui-layer-close', {
//							tips: 3
//						});
//					},500)
//				}
//			})			
//			layui.layer.full(index);
		})
	}).resize();

	//推荐文章
//	$(".recommend").click(function(){
//		var $checkbox = $(".news_list").find('tbody input[type="checkbox"]:not([name="show"])');
//		if($checkbox.is(":checked")){
//			var index = layer.msg('推荐中，请稍候',{icon: 16,time:false,shade:0.8});
//            setTimeout(function(){
//                layer.close(index);
//				layer.msg("推荐成功");
//            },2000);
//		}else{
//			layer.msg("请选择需要推荐的文章");
//		}
//	})


	//批量删除
	$(".batchDel").click(function(){
		var $checkbox = $('.news_list tbody input[type="checkbox"][name="checked"]');
		var $checked = $('.news_list tbody input[type="checkbox"][name="checked"]:checked');
		var checkedIds = [];
		if($checkbox.is(":checked")){
			layer.confirm('确定删除选中的信息？',{icon:3, title:'提示信息'},function(index){
				var index = layer.msg('删除中，请稍候',{icon: 16,time:false,shade:0.8});
				checkedIds = [];
				for(var j=0;j<$checked.length;j++){
					var aid = $checked.eq(j).parents("tr").find(".news_del").attr("data-id");
					checkedIds.push(aid);
            	}
				if(checkedIds.length>0){
					 layer.close(index);
					location.href= '/boss/article/delete/'+checkedIds.join(",");
				}
					
//	            setTimeout(function(){
//	            	//删除数据
//	            	for(var j=0;j<$checked.length;j++){
//	            		for(var i=0;i<newsData.length;i++){
//							if(newsData[i].newsId == $checked.eq(j).parents("tr").find(".news_del").attr("data-id")){
//								newsData.splice(i,1);
//								newsList(newsData);
//							}
//						}
//	            	}
//	            	$('.news_list thead input[type="checkbox"]').prop("checked",false);
//	            	form.render();
//	                layer.close(index);
//					layer.msg("删除成功");
//	            },2000);
	        })
		}else{
			layer.msg("请选择需要删除的文章");
		}
	})

	//全选
	form.on('checkbox(allChoose)', function(data){
		var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="show"])');
		child.each(function(index, item){
			item.checked = data.elem.checked;
		});
		form.render('checkbox');
	});
	

	//通过判断文章是否全部选中来确定全选按钮是否选中
	form.on("checkbox(choose)",function(data){
		var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="show"])');
		var childChecked = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="show"]):checked')
		if(childChecked.length == child.length){
			$(data.elem).parents('table').find('thead input#allChoose').get(0).checked = true;
		}else{
			$(data.elem).parents('table').find('thead input#allChoose').get(0).checked = false;
		}
		form.render('checkbox');
	})

	//是否展示
	form.on('switch(isShow)', function(data){
		var index = layer.msg('修改中，请稍候',{icon: 16,time:false,shade:0.8});
        setTimeout(function(){
            layer.close(index);
			layer.msg("展示状态修改成功！");
        },2000);
	})


//	$("body").on("click",".news_collect",function(){  //收藏.
//		if($(this).text().indexOf("已收藏") > 0){
//			layer.msg("取消收藏成功！");
//			$(this).html("<i class='layui-icon'>&#xe600;</i> 收藏");
//		}else{
//			layer.msg("收藏成功！");
//			$(this).html("<i class='iconfont icon-star'></i> 已收藏");
//		}
//	})

	$("body").on("click",".news_del",function(){  //删除
		var _this = $(this);
		layer.confirm('确定删除此信息？',{icon:3, title:'提示信息'},function(index){
			var aid = _this.attr("data-id");
			location.href='/boss/article/delete/'+aid;
			layer.close(index);
		});
	})

//	function newsList(that){
//		//渲染数据
//		function renderDate(data,curr){
//			var dataHtml = '';
//			if(!that){
//				currData = newsData.concat().splice(curr*nums-nums, nums);
//			}else{
//				currData = that.concat().splice(curr*nums-nums, nums);
//			}
//			if(currData.length != 0){
//				for(var i=0;i<currData.length;i++){
//					dataHtml += '<tr>'
//			    	+'<td><input type="checkbox" name="checked" lay-skin="primary" lay-filter="choose"></td>'
//			    	+'<td align="left">'+currData[i].newsName+'</td>'
//			    	+'<td>'+currData[i].newsAuthor+'</td>';
//			    	if(currData[i].newsStatus == "待审核"){
//			    		dataHtml += '<td style="color:#f00">'+currData[i].newsStatus+'</td>';
//			    	}else{
//			    		dataHtml += '<td>'+currData[i].newsStatus+'</td>';
//			    	}
//			    	dataHtml += '<td>'+currData[i].newsLook+'</td>'
//			    	+'<td><input type="checkbox" name="show" lay-skin="switch" lay-text="是|否" lay-filter="isShow"'+currData[i].isShow+'></td>'
//			    	+'<td>'+currData[i].newsTime+'</td>'
//			    	+'<td>'
//					+  '<a class="layui-btn layui-btn-mini news_edit"><i class="iconfont icon-edit"></i> 编辑</a>'
//					+  '<a class="layui-btn layui-btn-normal layui-btn-mini news_collect"><i class="layui-icon">&#xe600;</i> 收藏</a>'
//					+  '<a class="layui-btn layui-btn-danger layui-btn-mini news_del" data-id="'+data[i].newsId+'"><i class="layui-icon">&#xe640;</i> 删除</a>'
//			        +'</td>'
//			    	+'</tr>';
//				}
//			}else{
//				dataHtml = '<tr><td colspan="8">暂无数据</td></tr>';
//			}
//		    return dataHtml;
//		}
//
//		//分页
//		var nums = 13; //每页出现的数据量
//		if(that){
//			newsData = that;
//		}
//		laypage({
//			cont : "page",
//			pages : Math.ceil(newsData.length/nums),
//			jump : function(obj){
//				$(".news_content").html(renderDate(newsData,obj.curr));
//				$('.news_list thead input[type="checkbox"]').prop("checked",false);
//		    	form.render();
//			}
//		})
//	}
})
