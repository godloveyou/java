package cn.nilaile.ssm.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.nilaile.ssm.entity.User;

public class BossLoginInterceptor extends HandlerInterceptorAdapter   {

	 private final Logger log = LoggerFactory.getLogger(BossLoginInterceptor.class);  
	 
	@Override  
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {  
        //获取请求的URL  
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String url = requestUri.substring(contextPath.length());  
        log.info(requestUri+": "+contextPath+" :"+url);
//        Enumeration<String> enums = request.getHeaderNames();
//        while(enums.hasMoreElements()){
//        	String key = enums.nextElement();
//        	String v = request.getHeader(key);
//        	log.info("key ---》"+key+"------>"+v);
//        }
        
//        if (request.getHeader("x-requested-with")!= null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){//如果是ajax请求响应头会有x-requested-with 
//            log.info("ajax请求确认");
//        	return false;
//		}
        
        //获取Session  
        HttpSession session = request.getSession();  
        User u = (User)session.getAttribute("user");  
        
        if(u != null){
        	 return true;
        }
        
        if(url.indexOf("/boss/doLogin")>=0){
        	return true;
        }
        
        request.getRequestDispatcher("/WEB-INF/boss/login/login.jsp").forward(request, response);  
        return false;
        
    }  
    @Override  
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {  
        System.out.println("===========HandlerInterceptor1 postHandle");  
    }  
    @Override  
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {  
        System.out.println("===========HandlerInterceptor1 afterCompletion");  
    }  
    
    
}
