<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>文章列表</title>
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
			<form class="layui-form"   action="<%=request.getContextPath()%>/boss/article/list" method="post"  id="queryForm">
		    <div class="layui-input-inline">
		    	<input type="text"   id="title"  name="title"   placeholder="请输入关键字"  class="layui-input search_input">
		    </div>
		    <a class="layui-btn search_btn">查询</a>
		    </form>
		</div>
		<div class="layui-inline">
			<a class="layui-btn layui-btn-normal newsAdd_btn">添加文章</a>
		</div>
	<!-- 	<div class="layui-inline">
			<a class="layui-btn recommend" style="background-color:#5FB878">推荐文章</a>
		</div> -->
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
				<col width="50">
				<col>
				<col width="9%">
				<col width="9%">
				<col width="9%">
				<col width="9%">
				<col width="9%">
				<col width="15%">
		    </colgroup>
		    <thead>
				<tr>
					<th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose" id="allChoose"></th>
					<th style="text-align:left;">文章标题</th>
					<th>点击</th>
					<th>是否置顶</th>
					<th>分类</th>
					<th>发布时间</th>
					<th>操作</th>
				</tr> 
		    </thead>
		    <tbody class="news_content">
		    	<c:forEach var="article" items="${articleList}">
			    	<tr>
						<td><input type="checkbox" name="checked" lay-skin="primary" lay-filter="choose"></td>
						<td align="left">${article.title }</td>
						<td>${article.clickCount}</td>
						<c:if test="${article.isRecommend==0}">
							<td><input type="checkbox" name="show" lay-skin="switch" lay-text="是|否" lay-filter="isShow" ></td>
						</c:if>
						<c:if test="${article.isRecommend==1}">
							<td><input type="checkbox" name="show" lay-skin="switch" lay-text="是|否" lay-filter="isShow"  checked></td>
						</c:if>
						<td>${article.blogCategory.cname}</td>
						<td><fmt:formatDate value="${article.publishDate}" pattern="yyyy-MM-dd HH:mm"/></td>
						<td>
							<a class="layui-btn layui-btn-mini news_edit"  href="<%=request.getContextPath()%>/boss/article/edit/${article.id}"><i class="iconfont icon-edit"></i> 编辑</a>
							<a class="layui-btn layui-btn-danger layui-btn-mini news_del"  data-id="${article.id}"><i class="layui-icon">&#xe640;</i> 删除</a>
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
	<script type="text/javascript" src="<%=request.getContextPath()%>/resource/admin/js/article//articleList.js"></script>
</body>
</html>