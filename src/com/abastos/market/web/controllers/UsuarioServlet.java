package com.abastos.market.web.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.market.web.util.ActionNames;
import com.abastos.market.web.util.AttributesNames;
import com.abastos.market.web.util.ControllerPath;
import com.abastos.market.web.util.CookieManager;
import com.abastos.market.web.util.ErrorNames;
import com.abastos.market.web.util.ParameterNames;
import com.abastos.market.web.util.ParameterUtils;
import com.abastos.market.web.util.SessionManager;
import com.abastos.market.web.util.UrlBuilder;
import com.abastos.market.web.util.WebConstants;
import com.abastos.model.Empresa;

import com.abastos.model.Particular;

import com.abastos.service.DataException;
import com.abastos.service.EmpresaService;

import com.abastos.service.ParticularService;

import com.abastos.service.exceptions.ServiceException;

import com.abastos.service.impl.EmpresaServiceImpl;

import com.abastos.service.impl.ParticularServiceImpl;
import com.google.gson.Gson;




public class UsuarioServlet extends HttpServlet {

	private static Logger logger = LogManager.getLogger(UsuarioServlet.class);

	private EmpresaService empresaService;
	private ParticularService particularService;

	public UsuarioServlet() {

		particularService = new ParticularServiceImpl();
		empresaService = new EmpresaServiceImpl();

	}




	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(logger.isDebugEnabled()) {
			logger.debug(ParameterUtils.print(request.getParameterMap()));
		}
		String action = request.getParameter(ActionNames.ACTION);
		String target = null;
		boolean redirect = false;
		String ajax = null;
		Errors error = new Errors();

		//comprobamos el tipo de usuario
		if(ActionNames.LOG_IN.equalsIgnoreCase(action)) {
			String tipUsuario = request.getParameter(ParameterNames.TIP_USUARIO);
		
			//si es empresa...
			if(ActionNames.EMPRESA.equalsIgnoreCase(tipUsuario)) {
				String email = null;
				String usuario = request.getParameter(ParameterNames.NOMBRE_USUARIO);
				String recordarMantener = request.getParameter(ParameterNames.MANTENER_RECORDAR);
				SessionManager.remove(request, AttributesNames.TIENDA);
				SessionManager.remove(request, AttributesNames.LOCALIDAD);
				//Comprobamos si en el parametro nombre se introduzco el email o el nombre de usuario
				if (usuario.contains("@")) {
					//String email = usuario.matches("^(.+)@(.+)$")? usuario: null;
					email = usuario;
					usuario = null;
				}

				String password = request.getParameter(ParameterNames.PASSWORD);
				try {
					Empresa empresa = empresaService.login(email, usuario, password);
					if (logger.isDebugEnabled()) {
						logger.debug(empresa.getCorreoElectronico() + " logged.");
					}
					if(usuario == null) {
						usuario = email;
					}
					SessionManager.set(request, AttributesNames.EMPRESA, empresa);
					if(ParameterNames.RECORDAR_USUARIO.equalsIgnoreCase(recordarMantener)) {
						String cookieValue = CookieManager.createValue(tipUsuario, String.valueOf(empresa.getId()),
								UrlBuilder.encode(request.getHeader(WebConstants.HEADER_AGENT)),usuario);
						CookieManager.removeCookie(response, ParameterNames.RECORDAR_USUARIO, "/");
						CookieManager.addCookie(response, ParameterNames.RECORDAR_USUARIO, cookieValue, "/", 365 * 24 * 60 * 60);
					}
					if(ParameterNames.MANTENER_SESION.equalsIgnoreCase(recordarMantener)) {
						String cookieValue = CookieManager.createValue(tipUsuario, String.valueOf(empresa.getId()),
								UrlBuilder.encode(request.getHeader(WebConstants.HEADER_AGENT)),usuario);

						CookieManager.addCookie(response, ParameterNames.MANTENER_SESION, cookieValue, "/", 365 * 24 * 60 * 60);
					}

					redirect = true;
					target =  UrlBuilder.getUrlForController(request, ControllerPath.TIENDA, ActionNames.BUSCAR, redirect);


				}catch(ServiceException e){
					logger.warn(e.getMessage(), e);
					error.add(ActionNames.LOG_IN, ErrorNames.ERR_INCORRECT_LOGIN);


				}
				catch (DataException e) {
					logger.warn(e.getMessage(),e);
					error.add(ActionNames.LOG_IN, ErrorNames.ERR_GENERIC_LOGIN);


				}
				if(error.hasErrors()) {
					request.setAttribute(AttributesNames.ERROR, error);
					target =  UrlBuilder.urlCallBack(request, true);
				}
			}
			//si es particular...
			else if(ActionNames.PARTICULAR.equalsIgnoreCase(tipUsuario)) {

				String usuario = request.getParameter(ParameterNames.NOMBRE_USUARIO);
				String recordarMantener = request.getParameter(ParameterNames.MANTENER_RECORDAR);
				String email = null;
				if (usuario.contains("@")) {

					email = usuario;
					usuario = null;
				}
				logger.info("iniciando sesion con " + usuario);
				String password = request.getParameter(ParameterNames.PASSWORD);
				try {
					Particular particular = particularService.login(email,usuario, password);
					SessionManager.set(request, AttributesNames.USUARIO, particular);
					if(ParameterNames.RECORDAR_USUARIO.equalsIgnoreCase(recordarMantener)) {
						String cookieValue = CookieManager.createValue(tipUsuario, String.valueOf(particular.getId()),
								UrlBuilder.encode(request.getHeader(WebConstants.HEADER_AGENT)),usuario);

						CookieManager.removeCookie(response, ParameterNames.RECORDAR_USUARIO, "/");
						CookieManager.addCookie(response, ParameterNames.RECORDAR_USUARIO, cookieValue, "/", 365 * 24 * 60 * 60);
					}
					if(ParameterNames.MANTENER_SESION.equalsIgnoreCase(recordarMantener)) {
						String cookieValue = CookieManager.createValue(tipUsuario, String.valueOf(particular.getId()),
								UrlBuilder.encode(request.getHeader(WebConstants.HEADER_AGENT)),usuario);

						CookieManager.addCookie(response, ParameterNames.MANTENER_SESION, cookieValue, "/", 365 * 24 * 60 * 60);
					}

					target = UrlBuilder.urlCallBack(request, false);
					redirect = true;
				}catch(ServiceException e){
					logger.warn(e.getMessage(), e);
					error.add(ActionNames.LOG_IN, ErrorNames.ERR_INCORRECT_LOGIN);


				}
				catch (DataException e) {
					logger.warn(e.getMessage(), e);
					error.add(ActionNames.LOG_IN, ErrorNames.ERR_GENERIC_LOGIN);
					request.setAttribute(AttributesNames.ERROR, error);

				}
				if(error.hasErrors()) {
					request.setAttribute(AttributesNames.ERROR, error);

					target = UrlBuilder.urlCallBack(request, true);

				}
			}}
		else if(ActionNames.REMENBER_USER_NAME.equals(action)) {
			ajax = request.getParameter(ParameterNames.AJAX);
			Cookie cookie = CookieManager.getCookie(request, ParameterNames.RECORDAR_USUARIO);
			String tipUser = request.getParameter(ParameterNames.TIP_USUARIO);
			String [] cookieResult = null;
			if(cookie != null) {
				String result = cookie.getValue();
				cookieResult = result.split(":");
			
				if(cookieResult[0].equals(tipUser) &&  request.getHeader(WebConstants.HEADER_AGENT).equals(UrlBuilder.decode(cookieResult[2]))) {
					Gson gson = new Gson();
					response.setContentType(WebConstants.CONTENT_TYPE);
					response.getOutputStream().write(gson.toJson(cookieResult[3]).getBytes());
				}
			}

		}
		if(ajax == null) {
			if(target == null) {
				target = UrlBuilder.getUrlForController(request, ControllerPath.PRECREATE, ActionNames.INICIO, redirect);
			}
			if(redirect) { 
				logger.info("Redirect to..." + target);
				response.sendRedirect(target);
			}
			else {
				logger.info("Forwarding to..." + target);
				request.getRequestDispatcher(target).forward(request, response);
			}}

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
