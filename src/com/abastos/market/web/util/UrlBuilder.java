package com.abastos.market.web.util;

import java.util.Map;

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
	public static String builderMap(HttpServletRequest request,String action) {
		Map<String, String[]> valores = request.getParameterMap();
		StringBuilder sb = new StringBuilder();
		sb.append("http://").append(request.getServerName()).append(":")
		.append(request.getServerPort())
		.append(request.getContextPath()).append("/").append(action).append("&");
		for(Map.Entry<String, String[]> m: valores.entrySet()) {
			sb.append(m.getKey()).append("=").append(m.getValue()[0]).append("&");
		}
		return sb.toString();
	}
	public static String builderMap(HttpServletRequest request,String action,  Integer categoria) {
		Map<String, String[]> valores = request.getParameterMap();
		StringBuilder sb = new StringBuilder();
		sb.append("http://").append(request.getServerName()).append(":")
		.append(request.getServerPort())
		.append(request.getContextPath()).append("/").append(action).append("&");
		for(Map.Entry<String, String[]> m: valores.entrySet()) {
			if(m.getKey().equals(ParameterNames.CATEGORIA)) {
				
			}
			
			else {
				
			sb.append(m.getKey()).append("=").append(m.getValue()[0]).append("&");
			}
		}
		
		
		sb.append(ParameterNames.CATEGORIA).append("=").append(categoria);
		
		return sb.toString();
	}
}
