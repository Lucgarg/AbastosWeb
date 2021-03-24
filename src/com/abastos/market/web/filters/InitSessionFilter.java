package com.abastos.market.web.filters;

import java.io.IOException;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.market.web.model.Carrito;
import com.abastos.market.web.util.AttributesNames;
import com.abastos.market.web.util.SessionManager;


public class InitSessionFilter implements Filter {
	private static Logger logger = LogManager.getLogger(InitSessionFilter.class);

	public InitSessionFilter() {

	}


	public void destroy() {

	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);
		String target = null;
		Carrito carrito = null;
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
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
