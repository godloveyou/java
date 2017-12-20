<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>标签编辑</title>
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
  <a href="/boss/articleTag/list">标签管理</a>
  <a><cite>编辑标签</cite></a>
</span>
</blockquote>

	<form class="layui-form" action="<%=request.getContextPath()%>/boss/articleTag/doEdit"  method="post">
		<div class="layui-form-item">
			<label class="layui-form-label">标签名称</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input newsName"  name="tagName"  id="tagname" lay-verify="required" placeholder="标签名称" value="${articleTag.tagName}">
			</div>
		</div>
		<input type="hidden"  name="id"  value="${articleTag.id }" >
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit="" lay-filter="addNews">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				<a href="/boss/articleTag/list"  	class="layui-btn layui-btn-primary" >返回列表</a>
		    </div>
		</div>
	</form>
	<script type="text/javascript"  src="<%=request.getContextPath()%>/resource/admin//layui/layui.js"></script>
	<script type="text/javascript"  src="<%=request.getContextPath()%>/resource/admin/js/articleTag/edit.js"></script>
</body>
</html>