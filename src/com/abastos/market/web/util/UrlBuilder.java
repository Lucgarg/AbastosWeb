package com.abastos.market.web.util;

import java.util.Base64;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sun.mail.iap.Response;

public class UrlBuilder {

	private static Logger logger = LogManager.getLogger(UrlBuilder.class); 
	private static Base64.Encoder ENCODER = Base64.getUrlEncoder();
	private static final String URL_SERVLET =  "javax.servlet.forward.request_uri";
	private static final String URL_SERVLET_PATH = "javax.servlet.forward.servlet_path";
	public UrlBuilder() {

	}
	/*
	 * metodo para facilitar la construción de url, requiere introducir un String para la referencia del elemento
	 * */
	public static String getUrl(HttpServletRequest request, String direct) {
		return new StringBuilder("http://").append(request.getServerName()).append(":")
				.append(request.getServerPort())
				.append(request.getContextPath()).append("/").append(direct).toString();

	}

	public static String getUrlforImg(HttpServletRequest request, String direct) {
		return new StringBuilder("http://").append(request.getServerName()).append(":")
				.append(request.getServerPort())
				.append(request.getContextPath()).append("/imgs/").append(direct).toString();


	}
	public static String getUrlForController(HttpServletRequest request,String action) {

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
	public static String getUrlForController(HttpServletRequest request, String controllerPath, String controllerAction, String... paramsAndValues) {
		StringBuilder sb = new StringBuilder("http://").append(request.getServerName()).append(":")
				.append(request.getServerPort())
				.append(request.getContextPath()).append("/").append(controllerPath)
				.append("?")
				.append(ActionNames.ACTION)
				.append("=")
				.append(controllerAction)
				.append("&");
		if(paramsAndValues.length > 1) {
			for(int i=0; i < paramsAndValues.length; i+=2) {
				sb.append(paramsAndValues[i]).append("=").append(paramsAndValues[i + 1]).append("&");
			}}
		else {
			sb.append(paramsAndValues[0]);
		}

		return sb.toString();

	}
	public static String getUrlForForward(HttpServletRequest request, String controllerPath, String controllerAction, String... paramsAndValues) {
		StringBuilder sb = new StringBuilder("/").append(controllerPath)
				.append("?")
				.append(ActionNames.ACTION)
				.append("=")
				.append(controllerAction)
				.append("&");
		if(paramsAndValues.length > 1) {
			for(int i=0; i < paramsAndValues.length; i+=2) {
				sb.append(paramsAndValues[i]).append("=").append(paramsAndValues[i + 1]).append("&");
			}}
		else {
			sb.append(paramsAndValues[0]);
		}

		return sb.toString();

	}

	public static String getUrlForController(HttpServletRequest request, String controllerPath, String controllerAction ) {
		StringBuilder sb = new StringBuilder("http://").append(request.getServerName()).append(":")
				.append(request.getServerPort())
				.append(request.getContextPath()).append("/").append(controllerPath)
				.append("?")
				.append(ActionNames.ACTION)
				.append("=")
				.append(controllerAction);

		return sb.toString();

	}

	public static String getUrlForController(HttpServletRequest request,String context, String action,  Integer categoria) {

		Map<String, String[]> valores = request.getParameterMap();


		StringBuilder sb = new StringBuilder();
		sb.append("http://").append(request.getServerName()).append(":")
		.append(request.getServerPort())
		.append(request.getContextPath()).append("/").append(context)
		.append("?")
		.append(ActionNames.ACTION).append("=").append(action).append("&");
		for(Map.Entry<String, String[]> m: valores.entrySet()) {
			if(ParameterNames.NOMBRE_TIENDA.equals(m.getKey()) || ParameterNames.NOMBRE_PRODUCTO.equals(m.getKey())){}
			else {
				if(ActionNames.ACTION.equalsIgnoreCase(m.getKey())){

				}
				if(m.getKey().equals(ParameterNames.CATEGORIA)) {

				}

				else {
					if(ActionNames.ACTION.equalsIgnoreCase(m.getKey())) {}
					else {
						sb.append(m.getKey()).append("=").append(m.getValue()[0]).append("&");
					}
				}
			}
		}


		sb.append(ParameterNames.CATEGORIA).append("=").append(String.valueOf(categoria));
		return sb.toString();
	}
	public static String urlCallBack(HttpServletRequest request) {
		Map<String, String[]> mapParam = request.getParameterMap();
		StringBuilder sb = new StringBuilder("http://").append(request.getServerName()).append(":")
				.append(request.getServerPort());
		if(request.getQueryString() != null) {
			logger.debug("b " + request.getQueryString());
			sb.append(request.getAttribute(URL_SERVLET));
			sb.append("?").append(request.getQueryString());
		}
		else if(request.getParameter(ActionNames.ACTION) != null) {
			logger.debug("c " + request.getAttribute(URL_SERVLET));
			sb.append(request.getAttribute(URL_SERVLET)).append("?");
			for(Map.Entry<String, String[]> m: mapParam.entrySet()) {
				sb.append(m.getKey()).append("=").append(m.getValue()[0]).append("&");
			}
		}
		else if(request.getAttribute(ActionNames.ACTION) != null) {
			
			sb.append(request.getContextPath()).append(request.getAttribute(URL_SERVLET_PATH)).append("?").append(ActionNames.ACTION).append("=")
			.append(request.getAttribute(ActionNames.ACTION));
		}
		else{
			logger.debug("d " + request.getRequestURI());
			sb.append(request.getRequestURI());
		}
		
		
		logger.info(sb.toString());
		return sb.toString();

	}
	protected static String decode(String value) {
		String codedBytes = ENCODER.encodeToString(value.getBytes());
		return codedBytes;
	}
}
