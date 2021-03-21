package com.abastos.market.web.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
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
			sb.append(decode(m.getKey())).append("=").append(decode(m.getValue()[0])).append("&");
		}
		return sb.toString()
;
	}
	public static String builderMap(HttpServletRequest request,String action,  Integer categoria) {
	
		Map<String, String[]> valores = request.getParameterMap();
		StringBuilder sb = new StringBuilder();
		sb.append("http://").append(request.getServerName()).append(":")
		.append(request.getServerPort())
		.append(request.getContextPath()).append("/").append(action).append("&");
		for(Map.Entry<String, String[]> m: valores.entrySet()) {
			if(ActionNames.ACTION.equalsIgnoreCase(m.getKey())){
				
			}
			if(m.getKey().equals(ParameterNames.CATEGORIA)) {
				
			}
			
			else {
			if(ActionNames.ACTION.equalsIgnoreCase(m.getKey())) {}
			else {
			sb.append(decode(m.getKey())).append("=").append(decode(m.getValue()[0])).append("&");
			}
			}
		}
		
		
		sb.append(decode(ParameterNames.CATEGORIA)).append("=").append(decode(String.valueOf(categoria)));
		return sb.toString();
	}
	protected static String decode(String value) {
	String codedBytes = Base64.getUrlEncoder().encodeToString(value.getBytes(StandardCharsets.UTF_8));

		return codedBytes;
	}
}
