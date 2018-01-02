<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>${article.title }</title>
    <meta name="author" content="Mukosame" />
    <meta name="renderer" content="webkit">
    <meta name="description" content="Everything about Mukosame" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
    <!-- <link rel="stylesheet" href="/css/default.css" type="text/css" /> -->
     <%@include file="common/header.jsp"%>
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
	<link rel="stylesheet" href="/js/prettify/prettify.css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/admin/editormd/css/editormd.css" media="all" />
	
<style type="text/css">
    @media screen and (max-width: 750px){
        body { background:#fff; }
    }
    @media screen and (max-width: 1020px){
        body { background:#fff; }
    }
</style>

</head>
<body>

    <div class="index-content blog">
     <div class="entry">
        <h1 class="entry-title"><a href="/about-simplify" title="${article.title }">${article.title }</a></h1>
        <p class="entry-date"><fmt:formatDate value="${article.publishDate}" pattern="yyyy-MM-dd HH:mm"/></p>
		<%-- <h2 id="section">${article.title }</h2> --%>
		 <div id="articleContentDiv" >${article.contentHtml}</div>
		 <div class="line"></div>
		  <div class="artical-list-tag" style="martin-top:10px">
		                	<c:forEach var="tags" items="${article.listBlogTags }">
		                		<a href="/tag/${tags.tagName}" class="tag"><span class="tag_name">${tags.tagName}</span></a>
		                	</c:forEach>
		 </div>
		 
		<!--PC和WAP自适应版-->
		<div id="SOHUCS" sid="${article.id }" ></div> 
		<script type="text/javascript"> 
			(function(){ 
			var appid = 'cyto4vUae'; 
			var conf = 'prod_caa2e4ba0c3587a2cb1d9b3e211615fa'; 
			var width = window.innerWidth || document.documentElement.clientWidth; 
			if (width < 960) { 
			window.document.write('<script id="changyan_mobile_js" charset="utf-8" type="text/javascript" src="http://changyan.sohu.com/upload/mobile/wap-js/changyan_mobile.js?client_id=' + appid + '&conf=' + conf + '"><\/script>'); } else { var loadJs=function(d,a){var c=document.getElementsByTagName("head")[0]||document.head||document.documentElement;var b=document.createElement("script");b.setAttribute("type","text/javascript");b.setAttribute("charset","UTF-8");b.setAttribute("src",d);if(typeof a==="function"){if(window.attachEvent){b.onreadystatechange=function(){var e=b.readyState;if(e==="loaded"||e==="complete"){b.onreadystatechange=null;a()}}}else{b.onload=a}}c.appendChild(b)};loadJs("http://changyan.sohu.com/upload/changyan.js",function(){window.changyan.api.config({appid:appid,conf:conf})}); } })(); 
		</script>
		
	</div>
 		

    
	  <!-- 左侧导航 -->
      <%@include file="common/left.jsp"%>
    
</div>

	<script src="<%=request.getContextPath()%>/resource/admin/editormd/lib/marked.min.js"></script>
	<script src="<%=request.getContextPath()%>/resource/admin/editormd/lib/prettify.min.js"></script>
	<script type="text/javascript"  src="<%=request.getContextPath()%>/resource/admin/editormd/editormd.js"></script>
	<script src="<%=request.getContextPath()%>/resource/admin/editormd/languages/zh.js"></script>
<script type="text/javascript">
    $(function(){
    	
    	editormd.markdownToHTML("articleContentDiv");
    	 
    	$(".artical-list li").on({
    		mouseover:function(){
        	    $(this).addClass('myborder');
        	    $(this).css('cursor','pointer');
        	},
    		mouseout : function(){  
    			  $(this).removeClass('myborder');
    		}
    	});
    	
    	$(".navul .navlist").click(function(){
    		$(this).addClass("current").siblings().removeClass("current");
    	});

    });
</script>

</body>
</html>
