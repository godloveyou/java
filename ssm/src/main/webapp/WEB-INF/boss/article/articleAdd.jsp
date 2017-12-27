<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>文章添加</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/admin/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/admin/editormd2.5/css/editormd.css" media="all" />
</head>
<body class="childrenBody">
<!-- <blockquote class="layui-elem-quote"> <a href="http://www.layui.com" class="layui-btn">返回列表</a></blockquote> -->
<blockquote class="layui-elem-quote">
<span class="layui-breadcrumb">
  <a href="/boss/article/list">文章列表</a>
  <a><cite>发布文章</cite></a>
</span>
</blockquote>

	<form class="layui-form" action="<%=request.getContextPath()%>/boss/article/doAdd"  method="post">
		<div class="layui-form-item">
			<label class="layui-form-label">文章标题</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input newsName"  name="title"  lay-verify="required" placeholder="文章标题">
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">分类</label>
				<div class="layui-input-block">
				<select name="categoryId" class="newsLook" lay-filter="browseLook">
					<c:forEach var="category"   items="${listCategory}">
					   <option value="${category.cid}">${category.cname }</option>
					</c:forEach>
				    </select>
				</div>
			</div>
			
			<div class="layui-inline">
				<label class="layui-form-label">是否置顶</label>
				<div class="layui-input-inline">
					<input type="checkbox"    name="isRecommend"  id="isRecommend"    lay-skin="switch"  lay-text="是|否" />
				</div>
			</div>
		</div>
<!-- 		<div class="layui-form-item">
			<label class="layui-form-label">关键字</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input" placeholder="请输入文章关键字">
			</div>
		</div>
		 -->
		 <input type="hidden"  name="checkedTags"  id="checkedTags">
	<!-- 	  <input type="hidden"  name="articleContent"  id="articleContent"> -->
		   <input type="hidden"  name="recommend"  id="recommend" value="0">
		<div class="layui-form-item">
			<label class="layui-form-label">内容摘要</label>
			<div class="layui-input-block">
				<textarea placeholder="请输入内容摘要" class="layui-textarea" name="blogDes"></textarea>
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">文章内容</label>
			<div class="layui-input-block"  id="article_content">
				<textarea class="layui-textarea layui-hide"  name="content"     id="news_content"></textarea>
				<!-- 第二个隐藏文本域，用来构造生成的HTML代码，方便表单POST提交，这里的name可以任意取，后台接受时以这个name键为准 -->
 			   <textarea class="editormd-html-textarea"  name="articleContent"  id="articleContentText"></textarea>
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">标签</label>
			<div class="layui-input-block">
				<c:forEach var="blog_tag"   items="${listTag}">
					<input type="checkbox"  name="tagids"  data-tagid="${blog_tag.id }"  title="${blog_tag.tagName }">
				</c:forEach>
			</div>
		</div>
		
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit=""  lay-filter="addNews">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				 <a href="/boss/article/list"  	class="layui-btn layui-btn-primary" >返回列表</a>
		    </div>
		</div>
	</form>
	<script type="text/javascript"  src="<%=request.getContextPath()%>/resource/admin//layui/layui.js"></script>
	<script type="text/javascript"  src="<%=request.getContextPath()%>/resource/admin/editormd2.5/js/jquery.min.js"></script>
	<script type="text/javascript"  src="<%=request.getContextPath()%>/resource/admin/editormd2.5/editormd.js"></script>
	<script src="<%=request.getContextPath()%>/resource/admin/editormd2.5/languages/zh.js"></script>
	<script type="text/javascript"  src="<%=request.getContextPath()%>/resource/admin/js/article/articleAdd.js"></script>
	<script>
	var mdEditor;
	$(function(){
		mdEditor =  editormd("article_content", {
	         width   : "90%",
	         height  : 640,
	         syncScrolling : "single",
	         //你的lib目录的路径，我这边用JSP做测试的
	         path    : "<%=request.getContextPath()%>/resource/admin/editormd2.5/lib/",
	         //这个配置在simple.html中并没有，但是为了能够提交表单，使用这个配置可以让构造出来的HTML代码直接在第二个隐藏的textarea域中，方便post提交表单。
	         saveHTMLToTextarea : true,
	         imageUpload : true,
	         imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
             imageUploadURL : "/uploadImg"
             /*
             上传的后台只需要返回一个 JSON 数据，结构如下：
             {
                success : 0 | 1,           // 0 表示上传失败，1 表示上传成功
                message : "提示的信息，上传成功或上传失败及错误信息等。",
                url     : "图片地址"        // 上传成功时才返回
             }
             */
	     });
	})
	
	</script>
</body>
</html>