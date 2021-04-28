package com.abastos.market.web.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.market.web.util.ActionNames;
import com.abastos.market.web.util.AttributesNames;
import com.abastos.market.web.util.ControllerPath;
import com.abastos.market.web.util.SessionManager;
import com.abastos.market.web.util.UrlBuilder;


//filtro para evitar que el perfil empresa acceda a secciones unicas del perfil particular o usuario sin iniciar sesión
public class UserParticularFilter implements Filter {
	private static Logger logger = LogManager.getLogger(UserParticularFilter.class);

	public UserParticularFilter() {

	}


	public void destroy() {

	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String target = null;
		String action = request.getParameter(ActionNames.ACTION);


		if((SessionManager.get(httpRequest, AttributesNames.EMPRESA))!=null
				&& (ActionNames.INICIO.equals(action) || null == action)) {
			logger.info("perfilEmpresa intentando acceder a area restringida");
			target = UrlBuilder.getUrlForController(httpRequest, ControllerPath.TIENDA, ActionNames.BUSCAR, true);
			logger.info("Redirect to..." + target);
			httpResponse.sendRedirect(target);
		}
		else {
			chain.doFilter(request, response);
		}

	}


	public void init(FilterConfig fConfig) throws ServletException {

	}

}
