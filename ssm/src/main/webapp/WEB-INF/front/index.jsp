<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>首页</title>
    <meta name="author" content="Mukosame" />
    <meta name="renderer" content="webkit">
    <meta name="description" content="Everything about Mukosame" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
    <%@include file="common/header.jsp"%>
    <link rel="stylesheet" href="/css/index.css" type="text/css" />
</head>
<body>
    <div class="index-content blog">
    <div class="section">
        <ul class="artical-cate">
            <li class="on"><a href="/"><span>${categoryName }</span></a></li>
        </ul>

        <div class="cate-bar"><span id="cateBar"></span></div>

        <ul class="artical-list">
		         <c:forEach var="article" items="${listArticle}">
		         	<li class="post">
		                <h2><a href="/article/${article.id}">${article.title }</a></h2>
		                <div class="artical-list-date">
		                	<span class="timeSpan"><i class="iconfont">&#xe6a9;</i><fmt:formatDate value="${article.publishDate}" pattern="yyyy-MM-dd HH:mm"/></span>
		                	<span class="viewCount"><i class="iconfont">&#xe618;</i>${article. clickCount}</span>
		                	<i class="iconfont">&#xe67e;</i><span id = "sourceId::${article.id}" class = "cy_cmt_count" ></span>
							<script id="cy_cmt_num" src="https://changyan.sohu.com/upload/plugins/plugins.list.count.js?clientId=cyto4vUae">
							</script>
		                </div>
		                <div class="title-desc">${article.blogDes }</div>
		                <div class="line"></div>
		                <div class="artical-list-tag" style="martin-top:10px">
		                	<c:forEach var="tags" items="${article.listBlogTags }">
		                		<a href="/tag/${tags.tagName}" class="tag"><span class="tag_name">${tags.tagName}</span></a>
		                	</c:forEach>
		                </div>
		            </li>
		         </c:forEach>
        </ul>
    </div>
    <!-- 左侧导航 -->
    <%@include file="common/left.jsp"%>
</div>


<script type="text/javascript">
    $(function(){
    	
    	 var posts = document.querySelectorAll('.post');
    	    Array.prototype.forEach.call(posts, function(post, i){
    	        post.addEventListener('click', function (event) {
    	            var url = this.querySelector('a').href;
    	            window.location.href = url;
    	        });
    	});
    	    
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
    	
    	
        function animateBar($item,noAni){
            var spanLeft = $item.find('span').offset().left;
            var conLeft = $item.parent().offset().left;
            var left = spanLeft - conLeft;
            var width = $item.find('span').width() + 8;

            if(noAni){
                $('#cateBar').css({left:left,width:width})
            }else{
                $('#cateBar').stop().animate({left:left,width:width},300)
            }
        }

        var waitForFinalEvent = (function () {
            var timers = {};
            return function (callback, ms, uniqueId) {
                if (!uniqueId) {
                    uniqueId = "Don't call this twice without a uniqueId";
                }
                if (timers[uniqueId]) {
                    clearTimeout (timers[uniqueId]);
                }
                timers[uniqueId] = setTimeout(callback, ms);
            };
        })();

        $('.artical-cate li').mouseenter(function(){
            animateBar($(this));
        }).mouseleave(function(){
            animateBar($('.artical-cate .on'));
        });

        $(window).resize(function(e){
            waitForFinalEvent(function(){
                animateBar($('.artical-cate .on'));
            })
        })
        animateBar($('.artical-cate .on'),true);
    });
</script>

</body>
</html>
