<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>异常页面</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/admin/layui/css/layui.css" media="all" />
</head>
<body class="childrenBody">
	<div style="text-align: center; padding:11% 0;">
		<i class="layui-icon" style="line-height:13rem; font-size:10rem; color: #393D50;">&#xe635;</i>
		<p style="font-size: 40px; font-weight: 300; color: #999;">${exType}</p>
		<p style="font-size: 20px; font-weight: 300; color: #999;">${exMsg}</p>
	</div>
</body>
</html>