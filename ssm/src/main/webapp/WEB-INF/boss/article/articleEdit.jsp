<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>文章编辑</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/admin/layui/css/layui.css" media="all" />
</head>
<body class="childrenBody">
<blockquote class="layui-elem-quote">
<span class="layui-breadcrumb">
  <a href="/boss/article/list">文章列表</a>
  <a><cite>编辑文章</cite></a>
</span>
</blockquote>

	<form class="layui-form" action="<%=request.getContextPath()%>/boss/article/doEdit"  method="post">
		<div class="layui-form-item">
			<label class="layui-form-label">文章标题</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input newsName"  name="title"  lay-verify="required" placeholder="文章标题" value="${article.title}">
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">分类</label>
				<div class="layui-input-block">
				<select name="categoryId" class="newsLook" lay-filter="browseLook">
					<c:forEach var="category"   items="${listCategory}">
					   <option value="${category.cid}"  <c:if test="${category.cid == article.categoryId}">selected="selected"</c:if>>${category.cname }</option>
					</c:forEach>
				    </select>
				</div>
			</div>
			
			<div class="layui-inline">
				<label class="layui-form-label">是否置顶</label>
				<div class="layui-input-inline">
					<input type="checkbox"    name="isRecommend"  id="isRecommend"    lay-skin="switch"  lay-text="是|否"  <c:if test="${article.isRecommend==1}">checked</c:if> />
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
		  <input type="hidden"  name="articleContent"  id="articleContent">
		   <input type="hidden"  name="recommend"  id="recommend" value="0">
		   <input type="hidden"  name="id"  value="${article.id }" >
		<div class="layui-form-item">
			<label class="layui-form-label">内容摘要</label>
			<div class="layui-input-block">
				<textarea placeholder="请输入内容摘要" class="layui-textarea" name="blogDes">${article.blogDes}</textarea>
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">文章内容</label>
			<div class="layui-input-block">
				<textarea class="layui-textarea layui-hide" name="content"  lay-verify="content"   id="news_content">${article.content}</textarea>
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">标签</label>
			<div class="layui-input-block">
				<c:forEach var="blog_tag"   items="${listTag}">
							<input type="checkbox"  name="tagids"  data-tagid="${blog_tag.id }"  title="${blog_tag.tagName }"    />
				</c:forEach>
			</div>
		</div>
		<input type="hidden"   id="oldArticleTags" value="${articleTagIds}"/>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit="" lay-filter="addNews">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				<a href="/boss/article/list"  	class="layui-btn layui-btn-primary" >返回列表</a>
		    </div>
		</div>
	</form>
	<script type="text/javascript"  src="<%=request.getContextPath()%>/resource/admin//layui/layui.js"></script>
	<script type="text/javascript"  src="<%=request.getContextPath()%>/resource/admin/js/article/articleEdit.js"></script>
</body>
</html>