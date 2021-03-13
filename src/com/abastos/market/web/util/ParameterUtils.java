package com.abastos.market.web.util;

import java.util.Map;
import java.util.Map.Entry;

public class ParameterUtils {


/**
 * @param parameters request.getParameterMap();
 * @return String con formato de resultados más legible;
 */
public static final String print(Map<String, String[]> parameters) {
		
		StringBuilder sb = new StringBuilder();
	
		
		for(Map.Entry<String, String[]> values : parameters.entrySet()) {
			sb.append(values.getKey()).append("={");
			for(String va:values.getValue()) {
				sb.append(va).append(",");
			}
		}
		return sb.delete(sb.length()-1, sb.length()).toString();
}
}
		
	

