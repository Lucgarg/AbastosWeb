package com.abastos.market.web.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.market.web.util.UrlBuilder;
import com.abastos.market.web.util.ViewPaths;


@WebServlet("/error")
public class ErrorServlet extends HttpServlet {
	private static Logger logger = LogManager.getLogger(ErrorServlet.class);


	public ErrorServlet() {

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int action = response.getStatus();
		
		String target = null;
		if(404 == action) {
			logger.debug("Error "+action +" accediendo a " + request.getRequestURI());
			target = UrlBuilder.getUrl(request, ViewPaths.ERROR_404);
		}
		else if(500 == action) {
			logger.debug("Error "+action +" accediendo a " + request.getRequestURI());
			target = UrlBuilder.getUrl(request, ViewPaths.ERROR_500);
		}
		logger.debug("redirigiendo a " + target);
		response.sendRedirect(target);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
