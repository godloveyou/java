layui.config({
	base : "js/"
}).use(['form','layer','jquery','laypage'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		$ = layui.jquery;
	
	var msg = $("#tipmsg").text()|| '';
	if(msg.length>0){
		 layer.msg(msg);
	}
	
	laypage({
	curr: $("#curr").val(),
	cont : "page",
	pages : $("#totalPage").val(),
	jump : function(obj,first){
    	if(!first){
    		location.href="/boss/links/list?curr="+obj.curr;
    	}
	}
})
	
	//添加文章
	//改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
	$(window).one("resize",function(){
		$(".newsAdd_btn").click(function(){
			location.href="/boss/links/add";
		})
	}).resize();



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
					location.href= '/boss/links/delete/'+checkedIds.join(",");
				}
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
//	form.on('switch(isShow)', function(data){
//		var index = layer.msg('修改中，请稍候',{icon: 16,time:false,shade:0.8});
//        setTimeout(function(){
//            layer.close(index);
//			layer.msg("展示状态修改成功！");
//        },2000);
//	})

	$("body").on("click",".news_del",function(){  //删除
		var _this = $(this);
		layer.confirm('确定删除此信息？',{icon:3, title:'提示信息'},function(index){
			var aid = _this.attr("data-id");
			location.href='/boss/links/delete/'+aid;
			layer.close(index);
		});
	})
})
