package com.abastos.market.web.util;

import javax.servlet.http.HttpServletRequest;

public class UrlBuilder {

	public UrlBuilder() {
		
	}
	/*
	 * metodo para facilitar la construción de url, requiere introducir un String para la referencia del elemento
	 * */
	public static String builder(HttpServletRequest request, String direct) {
		return new StringBuilder("http://").append(request.getServerName()).append(":")
				.append(request.getServerPort())
				.append(request.getContextPath()).append("/").append(direct).toString();
	}
	public static String builderImg(HttpServletRequest request, String direct) {
		return new StringBuilder("http://").append(request.getServerName()).append(":")
				.append(request.getServerPort())
				.append(request.getContextPath()).append("/imgs/").append(direct).toString();
	}
}
