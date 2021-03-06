package com.mx.xvhx.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("securityInterceptor")
public class SecurityInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		log.info("[SecurityInterceptor : preHandle][" + request.getMethod() + "]" + "[" + request.getContextPath() + "]"
				+ request.getRequestURI() + getParameters(request));

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		log.info("[SecurityInterceptor : postHandle][" + request.getRequestURI() + "]");

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		if (ex != null) {
			ex.printStackTrace();
		}
		log.info("[SecurityInterceptor : afterCompletion][" + request.getRequestURI() + "][exception: " + ex + "]");

	}

	private String getParameters(HttpServletRequest request) {
		StringBuffer posted = new StringBuffer();
		Enumeration<?> e = request.getParameterNames();
		if (e != null) {
			posted.append("?");
		}
		while (e.hasMoreElements()) {
			if (posted.length() > 1) {
				posted.append("&");
			}
			String curr = (String) e.nextElement();
			posted.append(curr + "=");
			if (curr.contains("password") || curr.contains("pass") || curr.contains("pwd")) {
				posted.append("*****");
			} else {
				posted.append(request.getParameter(curr));
			}
		}
		String ip = request.getHeader("X-FORWARDED-FOR");
		String ipAddr = (ip == null) ? getRemoteAddr(request) : ip;
		if (ipAddr != null && !ipAddr.equals("")) {
			posted.append("&_psip=" + ipAddr);
		}
		return posted.toString();
	}

	private String getRemoteAddr(HttpServletRequest request) {
		String ipFromHeader = request.getHeader("X-FORWARDED-FOR");
		if (ipFromHeader != null && ipFromHeader.length() > 0) {
			log.debug("ip from proxy - X-FORWARDED-FOR : " + ipFromHeader);
			return ipFromHeader;
		}
		return request.getRemoteAddr();
	}

}
