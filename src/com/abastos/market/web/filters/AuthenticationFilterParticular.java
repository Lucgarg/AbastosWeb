package com.abastos.market.web.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.market.web.util.ActionNames;
import com.abastos.market.web.util.AttributesNames;
import com.abastos.market.web.util.ControllerPath;
import com.abastos.market.web.util.SessionManager;
import com.abastos.market.web.util.UrlBuilder;
import com.abastos.market.web.util.ViewPaths;



public class AuthenticationFilterParticular implements Filter {
	private static Logger logger = LogManager.getLogger(AuthenticationFilterEmpresa.class);
	public AuthenticationFilterParticular() {

	}


	public void destroy() {

	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String target = null;
		
		if((SessionManager.get(httpRequest, AttributesNames.USUARIO))==null) {
			if(SessionManager.get(httpRequest, AttributesNames.EMPRESA)==null){
			logger.info("Usuario sin identificar");
			target = UrlBuilder.getUrlForController(httpRequest, ControllerPath.PRECREATE, ActionNames.INICIO);
			logger.info("Redirect to..." + target);
			httpResponse.sendRedirect(target);
			}
			else {
				logger.info("perfilEmpresa intentando acceder a area restringida");
				target = UrlBuilder.getUrlForController(httpRequest, ControllerPath.TIENDA, ActionNames.BUSCAR);
				logger.info("Redirect to..." + target);
				httpResponse.sendRedirect(target);
			}
		}
		else {
			chain.doFilter(request, response);
		}
	}


	public void init(FilterConfig fConfig) throws ServletException {

	}

}
