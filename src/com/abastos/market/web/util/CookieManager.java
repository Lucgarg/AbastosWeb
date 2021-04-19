package com.abastos.market.web.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Utilidades para el manejo de cookies (documentaci�n mejorable).
 * 
 * @author https://www.linkedin.com/in/joseantoniolopezperez
 * @version 0.2
 */
public class CookieManager {

	public CookieManager() {

	}



	/**
	 * Busca una cookie por su nombre (case insensitive). 
	 * @return null si no la encuentra.
	 */
	public static final Cookie getCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();		
		if (cookies!=null) {
			for (Cookie c: cookies) {
				if (c.getName().equalsIgnoreCase(name)) {
					return c;
				}
			}
		}
		return null;    
	}

	/**
	 * A�ade una cookie a la response.
	 */
	public static final void addCookie(HttpServletResponse response, String name, String value, String path, int timeToLive) {		
		Cookie c = new Cookie(name, value);		
		c.setMaxAge(timeToLive);
		c.setPath(path);
		response.addCookie(c);	
	}

	/**
	 * Commodity method para "eliminar" una cookie.
	 */
	public static final void removeCookie(HttpServletResponse response, String name, String path) {
		addCookie(response, name, null, path, 0);
	}
	
	/**
	 * Metodo para crear key/value de una cookie
	 */
	public static String  createValue(String...value) {
		StringBuilder stb = new StringBuilder();
		for(String s: value) {
			stb.append(s).append(":");
		}
	
		return stb.delete(stb.length(), stb.length()).toString();

	}
	 
}
