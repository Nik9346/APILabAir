package it.labair.helper;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class ControlloCookie {

	public String getSessionId(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if("JSESSIONID".equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}
}
