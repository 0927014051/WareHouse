package com.javaweb.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.UriComponentsBuilder;

import com.javaweb.controller.UserController;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	    String url = request.getRequestURI();
	    if(UserController.getStaff() == null) {  
	    	
	    	 String loginUrl ="/home";
		        String redirectUrl = UriComponentsBuilder.fromPath(loginUrl).build().toUriString();
		        response.sendRedirect(redirectUrl);
		        return false; // Dừng xử lý các interceptor và controller khác
		    	
	    }
	   // Integer role_id = (int) request.getSession().getAttribute("role_id");
	    return true;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
			org.springframework.web.servlet.ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
