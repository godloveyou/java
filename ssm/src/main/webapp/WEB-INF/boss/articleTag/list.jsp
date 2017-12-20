<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>文章标签列表</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/admin/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="//at.alicdn.com/t/font_tnyc012u2rlwstt9.css" media="all" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/admin/css/news.css" media="all" />
</head>
<body class="childrenBody">
	<blockquote class="layui-elem-quote news_search">
		<div class="layui-inline">
			<a class="layui-btn layui-btn-normal newsAdd_btn">添加标签</a>
		</div>
		<div class="layui-inline">
			<a class="layui-btn layui-btn-danger batchDel">批量删除</a>
		</div>
		<div class="layui-inline">
			<div class="layui-form-mid layui-word-aux"  ><span id="tipmsg">${msg}</span></div>
		</div>
	</blockquote>
	<div class="layui-form news_list">
	  	<table class="layui-table">
		    <colgroup>
				<col width="10%">
				<col width="30%">
				<col width="20%">
		    </colgroup>
		    <thead>
				<tr>
					<th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose" id="allChoose"></th>
					<th style="text-align:left;">标签ID</th>
					<th>标签名称</th>
					<th>操作</th>
				</tr> 
		    </thead>
		    <tbody class="news_content">
		    	<c:forEach var="tag" items="${list}">
			    	<tr>
						<td><input type="checkbox" name="checked" lay-skin="primary" lay-filter="choose"></td>
						<td align="left">${tag.id }</td>
						<td>${tag.tagName}</td>
						<td>
							<a class="layui-btn layui-btn-mini news_edit"  href="<%=request.getContextPath()%>/boss/articleTag/edit/${tag.id}"><i class="iconfont icon-edit"></i> 编辑</a>
							<a class="layui-btn layui-btn-danger layui-btn-mini news_del"  data-id="${tag.id}"><i class="layui-icon">&#xe640;</i> 删除</a>
						</td>
					</tr>
				</c:forEach>
		    </tbody>
		</table>
	</div>
	<input type="hidden"  id="totalPage"  value="${totalPage}" />
	<input type="hidden"  id="curr"  value="${curr}" />
	<div id="page"></div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resource/admin/layui/layui.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resource/admin/js/articleTag/list.js"></script>
</body>
</html>