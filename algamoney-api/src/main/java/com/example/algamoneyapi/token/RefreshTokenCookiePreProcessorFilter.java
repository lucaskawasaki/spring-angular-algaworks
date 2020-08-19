package com.example.algamoneyapi.token;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RefreshTokenCookiePreProcessorFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		
		if(acharRequisitos(req)) {
			
			String refreshToken = acharCookieRefreshToken(req);
			req = new MyServletRequestWrapper(req, refreshToken);
		}
		
		chain.doFilter(req, response);
		
	}

	private boolean acharRequisitos(HttpServletRequest req) {
		return req.getRequestURI().equals("/oauth/token")
				&& req.getParameter("grant_type").equals("refresh_token")
				&& req.getCookies() != null;
	}

	private String acharCookieRefreshToken(HttpServletRequest req) {
		Stream<Cookie> cookies = Arrays.stream(req.getCookies());
		
		Cookie cookieFound = cookies.filter(cookie -> cookie.getName().equals("refreshToken"))
				.findAny()
				.orElse(null);
		
		return cookieFound == null ? null : cookieFound.getValue();
	}

}
