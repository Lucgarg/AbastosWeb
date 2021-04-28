package com.abastos.market.web.util;

import java.util.Map;

/**
 * @author Usuario
 *
 */
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
	
	/**
	 * @param params mapa de parametros
	 * @param parameter parametros a recuperar del mapa de parametros
	 * @param pos posicion cuando el parametro solicitado tiene más de un valor
	 * @return el parametro solitado si existe o sino un string vacio
	 */
	public static final String printParam(Map<String, String[]> params, String parameter, int pos) {
		
		if(params.get(parameter) == null) {
			return "";
		}
		else {

			return params.get(parameter)[pos];
		}
	}
	/**
	 * @param s parametro
	 * @return tras comprobar si el parametro es  diferente a null, se devuelve el mismo parametro o un string vacio
	 */
	public static final String printParam(String s) {
		if(null != s) {
			return s;
		}
		return "";
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



