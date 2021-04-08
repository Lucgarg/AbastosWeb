package com.abastos.market.web.util;

import java.util.Base64;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class UrlBuilder {

	private static Logger logger = LogManager.getLogger(UrlBuilder.class); 
	private static Base64.Encoder ENCODER = Base64.getUrlEncoder();
	private static final String URL_SERVLET_PATH = "javax.servlet.forward.servlet_path";
	private static final String URL_ATTRIBUTE = "url";

	public UrlBuilder() {

	}

	/** metodo para construir urls cuando solo se necesita un parametro, utilizado en errores y en el action de formularios
	 * @param request
	 * @param direct
	 * @return
	 */
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

	/**metodo para construir url con una lista de parametros
	 * @param request
	 * @param controllerPath servlet al que se realiza la peticion
	 * @param controllerAction accion que se realiza
	 * @param paramsAndValues parametros con los que se realiza la peticion
	 * @return url con parametros
	 */
	public static String getUrlForController(HttpServletRequest request, String controllerPath, String controllerAction, boolean redirect, String... paramsAndValues) {
		StringBuilder sb = new StringBuilder();
		if(redirect == true) {
			sb.append("http://").append(request.getServerName()).append(":")
			.append(request.getServerPort())
			.append(request.getContextPath());}

		sb.append("/").append(controllerPath)
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
	/**metodo para construir url en caso de hacer redirect hacia otro servlet
	 * @param request
	 * @param controllerPath servlet al que se quiere hacer la peticion
	 * @param controllerAction constante con el tipo de accion
	 * @return url
	 */
	public static String getUrlForController(HttpServletRequest request, String controllerPath, String controllerAction, boolean redirect ) {
		StringBuilder sb = new StringBuilder();
		if(redirect == true) {
			sb.append("http://").append(request.getServerName()).append(":")
			.append(request.getServerPort())
			.append(request.getContextPath());}

		sb.append("/").append(controllerPath)
		.append("?")
		.append(ActionNames.ACTION)
		.append("=")
		.append(controllerAction);

		return sb.toString();

	}
	/**metodo para contruir la url para guardar parametros de busqueda cuando se utiliza la url
	 * @param request
	 * @param context servlet al que se realiza la peticion
	 * @param action accion que queremos realizar con los datos enviados
	 * @param categoria 
	 * @return url evitando la repeticion de parametros
	 */
	public static String getUrlForController(HttpServletRequest request,String context, String action,  Integer categoria) {

		Map<String, String[]> valores = request.getParameterMap();
		StringBuilder sb = new StringBuilder();
		sb.append("http://").append(request.getServerName()).append(":")
		.append(request.getServerPort())
		.append(request.getContextPath()).append("/").append(context)
		.append("?")
		.append(ActionNames.ACTION).append("=").append(action).append("&");
		for(Map.Entry<String, String[]> m: valores.entrySet()) {
			//para evitar evitar que se utilize el parametro de nombre de la tienda o producto en el resto de urls a la hora de realizar la busqueda
			if(ParameterNames.NOMBRE_TIENDA.equals(m.getKey()) || ParameterNames.NOMBRE_PRODUCTO.equals(m.getKey())){}
			else {
				//para evitar la repeticion de categorias en la busqueda
				if(	m.getKey().equals(ParameterNames.CATEGORIA)) {}
				else {
					//para evitar la repeticion de acciones en la busqueda
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

	/**
	 * @param request
	 * @param forward true indica que el callback se utiliza en un forward, false es una llamada directa
	 * @return url para punto que se estaba en el momento anterior a llamar al servlet
	 */
	public static String urlCallBack(HttpServletRequest request, boolean forward) {


	
		Map<String, String[]> mapParam = request.getParameterMap();
		StringBuilder sb = new StringBuilder();

		//si forward == false entonces contruimos el contexto

		if(forward == false) {
			sb.append("http://").append(request.getServerName()).append(":")
			.append(request.getServerPort()).append(request.getContextPath());
		}
		// recuperamos la url que guardamos como parametro cada vez que se hace uso del formulario del login

		String []url = mapParam.get(URL_ATTRIBUTE);
		//comprobamos que no sea null

		if(url != null) {
			String url2 = url[0].split(request.getContextPath())[1];
			sb.append(url2);
		}
		else if(request.getAttribute(URL_SERVLET_PATH) == null) {
			sb.append(request.getHeader("referer").split(request.getContextPath())[1]);
		}

		else {
			//construimos desde cero la url recuperando el contexto el servlet

			sb.append(request.getAttribute(URL_SERVLET_PATH)).append("?");
			//si ya disponemos de parametros porque se ha realizado una llamada a un servlet con anterioridad
			//contruimos con estos la url
			if(!request.getParameterMap().isEmpty()) {
				for(Map.Entry<String, String[]> m: mapParam.entrySet()) {
					sb.append(m.getKey()).append("=").append(m.getValue()[0]).append("&");
				}
			}
			//Para la pagina de inicio recuperamos el atributo de la primera llamada
			else {
				sb.append(ActionNames.ACTION).append("=").
				append(request.getAttribute(ActionNames.ACTION));
			}
		}
		
		
		logger.info("url resultante " + sb.toString());
		return sb.toString();

	}
	public static String decode(String value) {

		byte[] decodedBytes = Base64.getDecoder().decode(value);
		String decodedString = new String(decodedBytes);
		return decodedString;
	}
	public static String encode(String value) {
		String encodedString = Base64.getEncoder().encodeToString(value.getBytes());
		return encodedString;
	}
}
