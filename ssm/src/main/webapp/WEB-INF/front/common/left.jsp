<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	request.setCharacterEncoding("utf-8");
	String contextPath = request.getContextPath();
%>

<div class="aside">
        <div class="nav-bar-header"><a href="#">不言博客</a></div>
    	<div class="nav-bar">
    		<ul class="navul">
    		 <li class="navlist current"><a href="<%=contextPath %>/index" class="">最新文章</a></li>
    		 	<c:forEach var="category"  items="${listCategory}">
    		 			 <li class="navlist"><a href="<%=contextPath %>/categories/${category.cname}" class=""> ${category.cname} </a></li>
    		 	</c:forEach>
    		</ul>
    	</div>
</div>