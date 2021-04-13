package com.abastos.market.web.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.market.web.controllers.Errors;
import com.abastos.market.web.model.Carrito;
import com.abastos.market.web.util.ActionNames;
import com.abastos.market.web.util.AttributesNames;
import com.abastos.market.web.util.ControllerPath;
import com.abastos.market.web.util.CookieManager;
import com.abastos.market.web.util.ErrorNames;
import com.abastos.market.web.util.ParameterNames;
import com.abastos.market.web.util.SessionManager;
import com.abastos.market.web.util.UrlBuilder;
import com.abastos.model.Empresa;
import com.abastos.model.Particular;
import com.abastos.service.DataException;
import com.abastos.service.EmpresaService;
import com.abastos.service.ParticularService;
import com.abastos.service.impl.EmpresaServiceImpl;
import com.abastos.service.impl.ParticularServiceImpl;


public class InitSessionFilter implements Filter {
	private static Logger logger = LogManager.getLogger(InitSessionFilter.class);
	private ParticularService particularService = null;
	private EmpresaService empresaService = null;
	public InitSessionFilter() {
		particularService = new ParticularServiceImpl();
		empresaService = new EmpresaServiceImpl();
	}


	public void destroy() {

	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);
		String target = null;
		Carrito carrito = null;
		Errors error  = new Errors();
		if(session == null) {
			if (logger.isInfoEnabled()) {
				logger.info("Request from "+request.getRemoteAddr()+": Initializing session...");
			}
			session = httpRequest.getSession(true);
			carrito = new Carrito();
			SessionManager.set(httpRequest, AttributesNames.CARRITO, carrito);
		}
		carrito = (Carrito) SessionManager.get(httpRequest, AttributesNames.CARRITO);

		if(carrito == null) {
			carrito = new Carrito();
			SessionManager.set(httpRequest, AttributesNames.CARRITO, carrito);
		}
		//cookies para la funcionalidad de recordar nombre o sesion
		Cookie cookie = CookieManager.getCookie(httpRequest, ParameterNames.RECORDAR_USUARIO);
		Cookie cookieRemind = CookieManager.getCookie(httpRequest, ParameterNames.MANTENER_SESION);
		String result = null;
		String [] cookValue = null;

		if(cookie != null) {
			result = cookie.getValue();
			cookValue = result.split(":");
		}
		if(cookieRemind != null) {
			result = cookieRemind.getValue();
			cookValue = result.split(":");
		}

		if(cookie != null|| cookieRemind != null) {

			if(ActionNames.PARTICULAR.equalsIgnoreCase(cookValue[0])) {
				Particular particular = null;
				try {
					particular = particularService.findById(Long.valueOf(cookValue[1]));
				}  catch (DataException e) {
					error.add(ParameterNames.ERROR, ErrorNames.ERR_GENERIC);
					httpRequest.setAttribute(AttributesNames.ERROR, error);
					target = UrlBuilder.getUrlForController(httpRequest, ControllerPath.PRECREATE, ActionNames.INICIO, false);
					logger.warn(e.getMessage(),e);
				}
				if(httpRequest.getHeader("User-Agent").equals(UrlBuilder.decode(cookValue[2])) && 
						particular.getAlias().equals(cookValue[3])||
						particular.getEmail().equals(cookValue[3])) {


					if(cookie != null) {
						SessionManager.remove(httpRequest, AttributesNames.RECORDAR_EMPRESA);
						SessionManager.set(httpRequest, AttributesNames.RECORDAR_USUARIO, cookValue[3]);

					}
					if (cookieRemind != null) {
						SessionManager.set(httpRequest, AttributesNames.USUARIO, particular);

					}}}

			if(ActionNames.EMPRESA.equalsIgnoreCase(cookValue[0])) {
				Empresa empresa = null;
				try {
					empresa = empresaService.findById(Long.valueOf(cookValue[1]));
				}  catch (DataException e) {
					error.add(ParameterNames.ERROR, ErrorNames.ERR_GENERIC);
					httpRequest.setAttribute(AttributesNames.ERROR, error);
					target = UrlBuilder.getUrlForController(httpRequest, ControllerPath.TIENDA, ActionNames.BUSCAR, false);
					logger.warn(e.getMessage(),e);
				}
				if(httpRequest.getHeader("User-Agent").equals(UrlBuilder.decode(cookValue[2])) && 
						empresa.getAlias().equals(cookValue[3])||
						empresa.getCorreoElectronico().equals(cookValue[3])) {


					if(cookie != null) {
						SessionManager.remove(httpRequest, AttributesNames.RECORDAR_USUARIO);
						SessionManager.set(httpRequest, AttributesNames.RECORDAR_EMPRESA, cookValue[3]);
					}
					if (cookieRemind != null) {
						SessionManager.set(httpRequest, AttributesNames.EMPRESA, empresa);

					}}}

		}

		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
