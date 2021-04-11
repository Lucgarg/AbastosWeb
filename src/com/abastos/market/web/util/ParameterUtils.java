package com.abastos.market.web.util;

import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

public class ParameterUtils {


	/**
	 * @param parameters request.getParameterMap();
	 * @return String con formato de resultados más legible;
	 */
	public static final String print(Map<String, String[]> parameters) {

		StringBuilder sb = new StringBuilder();


		for(Map.Entry<String, String[]> values : parameters.entrySet()) {
			if(ParameterNames.PASSWORD.equals(values.getKey())) {}
			else {
				sb.append(values.getKey()).append("={");

				for(String va:values.getValue()) {
					sb.append(va).append("}");
				}
			}
		}
		return  sb.toString();
	}
	public static final String printParam(HttpServletRequest request, String parameter, int pos) {
		Map<String, String[]> params = request.getParameterMap();
		if(params.get(parameter) == null) {
			return "";
		}
		else {

			return params.get(parameter)[pos];
		}
	}

	/**
	 * Obtiene el valor entero de un valor de parametro currentPageValue
	 */
	public static final int getPageNumber(String pageValue, int defaultValue) {
		int pageNumber = defaultValue;
		if (pageValue!=null) {
			
				pageNumber = Integer.valueOf(pageValue);
			
		}
		return pageNumber;
	}



}



