package com.fmi.uni.sofia.www.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
public class Controller {

	@RequestMapping(value="/")
	public String home(HttpServletRequest req) {
		if(getUserId(req) != null) {
			return "dashboard";
		}
		return "index";
	}
	
	@RequestMapping(value="/dashboard")
	public String dashboard() {
		return "dashboard";
	}
	
	private Integer getUserId(HttpServletRequest req) {
		try {
			String s = req.getSession().getAttribute("userId").toString();
			return Integer.parseInt(s);
		} catch (Exception e) {
			return null;
		}
	}
	
}
