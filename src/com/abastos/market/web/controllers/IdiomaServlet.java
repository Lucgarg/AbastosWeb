package com.abastos.market.web.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.market.web.util.ActionNames;
import com.abastos.market.web.util.AttributesNames;
import com.abastos.market.web.util.CookieManager;
import com.abastos.market.web.util.ParameterNames;
import com.abastos.market.web.util.ParameterUtils;
import com.abastos.market.web.util.SessionManager;
import com.abastos.market.web.util.UrlBuilder;
import com.abastos.market.web.util.WebConstants;


@WebServlet("/idioma")
public class IdiomaServlet extends HttpServlet {
	private static Logger logger = LogManager.getLogger(IdiomaServlet.class);


	public IdiomaServlet() {

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(logger.isDebugEnabled()) {
			logger.debug(ParameterUtils.print(request.getParameterMap()));
		}
		String action = request.getParameter(ActionNames.ACTION);
		String target = null;
		boolean redirect = false;


		if(ActionNames.SELECCIONAR.equals(action)) {
			String url = request.getParameter(ParameterNames.URL);
			String idioma = request.getParameter(ParameterNames.IDIOMA);
			//se crea una cookie para guardar el idioma
			SessionManager.set(request, AttributesNames.IDIOMA, idioma);
			String cookieValue = CookieManager.createValue(String.valueOf(idioma),
					UrlBuilder.encode(request.getHeader(WebConstants.HEADER_AGENT)));
			CookieManager.addCookie(response, ParameterNames.IDIOMA, cookieValue, "/", 365 * 24 * 60 * 60);
			redirect = true;
			target = url;

		}

		if(redirect) { 
			logger.info("Redirect to..." + target);
			response.sendRedirect(target);
		}
		else {
			logger.info("Forwarding to..." + target);
			request.getRequestDispatcher(target).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
