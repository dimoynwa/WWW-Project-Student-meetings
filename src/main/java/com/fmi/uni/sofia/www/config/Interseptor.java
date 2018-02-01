package com.fmi.uni.sofia.www.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class Interseptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if("/".equals(request.getServletPath()) || "/registrate".equals(request.getServletPath()) ||
				"/login".equals(request.getServletPath()) || "/upload".equals(request.getServletPath())) {
			return true;
		}
		boolean can = request.getSession().getAttribute("userId") != null;
		if(!can) {
			response.sendRedirect(request.getContextPath());
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
