package com.example.algamoneyapi.token;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.catalina.util.ParameterMap;

public class MyServletRequestWrapper extends HttpServletRequestWrapper{
	
	private String refreshToken;

	public MyServletRequestWrapper(HttpServletRequest request, String refreshToken) {
		super(request);
		this.refreshToken = refreshToken;
	}
	
	@Override
	public Map<String, String[]> getParameterMap() {
		ParameterMap<String, String[]> map = new ParameterMap<String, String[]>(getRequest().getParameterMap());
		map.put("refresh_token", new String[] { refreshToken });
		map.setLocked(true);
		
		return map;
	}
	
	

}
