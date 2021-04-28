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


//filtro para evitar que se acceda a la sección productos desde un usuario 
//sin iniciar sesión y un particular sin haber selecionada con anterioridad una tienda
public class TiendaSessionFilter implements Filter {

	private static Logger logger = LogManager.getLogger(TiendaSessionFilter.class);

	public TiendaSessionFilter() {

	}


	public void destroy() {

	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String target = null;
		if(SessionManager.get(httpRequest, AttributesNames.TIENDA) == null && 
				SessionManager.get(httpRequest, AttributesNames.EMPRESA) == null) {
			logger.info("Intentando acceder  a la sección de tienda, sin tienda en sesion..");
			target = UrlBuilder.getUrlForController(httpRequest, ControllerPath.PRECREATE, ActionNames.INICIO, true);
			logger.info("Redirect to..." + target);
			httpResponse.sendRedirect(target);
		}else {
			chain.doFilter(request, response);
		}
	}


	public void init(FilterConfig fConfig) throws ServletException {

	}

}
