package com.abastos.market.web.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.market.web.util.ActionNames;
import com.abastos.market.web.util.AttributesNames;
import com.abastos.market.web.util.ControllerPath;
import com.abastos.market.web.util.ErrorNames;
import com.abastos.market.web.util.ParameterNames;
import com.abastos.market.web.util.ParameterUtils;
import com.abastos.market.web.util.UrlBuilder;
import com.abastos.market.web.util.WebConstants;
import com.abastos.model.ComunidadAutonoma;
import com.abastos.model.Localidad;
import com.abastos.model.Provincia;
import com.abastos.service.ComunidadAutonomaService;
import com.abastos.service.DataException;
import com.abastos.service.LocalidadService;
import com.abastos.service.ProvinciaService;
import com.abastos.service.impl.ComunidadAutonomaServiceImpl;
import com.abastos.service.impl.LocalidadServiceImpl;
import com.abastos.service.impl.ProvinciaServiceImpl;
import com.google.gson.Gson;

/**
 * Servlet implementation class Localizacion
 */
@WebServlet("/localizacion")
public class LocalizacionServlet extends HttpServlet {
	private static Logger logger = LogManager.getLogger(LocalizacionServlet.class);
	private ComunidadAutonomaService comServ = null;
	private LocalidadService locServ = null;
	private ProvinciaService proServ = null;


	public LocalizacionServlet() {
		super();
		comServ = new ComunidadAutonomaServiceImpl();
		locServ = new LocalidadServiceImpl();
		proServ = new ProvinciaServiceImpl();

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(logger.isDebugEnabled()) {
			logger.debug(ParameterUtils.print(request.getParameterMap()));
		}
		String action = request.getParameter(ActionNames.ACTION);
		String buscar = request.getParameter(ActionNames.BUSCAR);
		String target = null;
		Errors error = new Errors();

		if(ActionNames.PAIS.equalsIgnoreCase(action)) {
			String pais = request.getParameter(ParameterNames.PAIS);
			try {
				List<ComunidadAutonoma> comunidadAutonoma = null;
				if(buscar != null) {
					comunidadAutonoma = comServ.findByTienda(Integer.valueOf(pais));
				}
				else {
					comunidadAutonoma = comServ.findByIdPais(Long.valueOf(pais));
				}

				Gson gson = new Gson();
				response.setContentType( WebConstants.CONTENT_TYPE);
				response.getOutputStream().write(gson.toJson(comunidadAutonoma).getBytes());

			} catch (DataException e) {
				logger.warn(e.getMessage(),e);
				error.add(ParameterNames.ERROR, ErrorNames.ERR_GENERIC);
				request.setAttribute(AttributesNames.ERROR, error);
				target = UrlBuilder.getUrlForController(request, ControllerPath.TIENDA, ActionNames.BUSCAR, false);

			}
		}
		else if(ActionNames.COMUNIDAD.equalsIgnoreCase(action)) {
			String comunidad = request.getParameter(ParameterNames.COMUNIDAD);
			try {
				List<Provincia> provincias = null;
				if(buscar != null) {
					provincias = proServ.findBy(Long.valueOf(comunidad));
				}
				else {
					provincias = proServ.findByIdComunidad(Long.valueOf(comunidad));
				}
				Gson gson = new Gson();
				response.setContentType(WebConstants.CONTENT_TYPE);
				response.getOutputStream().write(gson.toJson(provincias).getBytes());
			} catch ( DataException e) {
				logger.warn(e.getMessage(),e);
				error.add(ParameterNames.ERROR, ErrorNames.ERR_GENERIC);
				request.setAttribute(AttributesNames.ERROR, error);
				target = UrlBuilder.getUrlForController(request, ControllerPath.TIENDA, ActionNames.BUSCAR, false);

			}
		}
		else if(ActionNames.PROVINCIA.equalsIgnoreCase(action)) {
			String provincia = request.getParameter(ParameterNames.PROVINCIA);
			try {
				List<Localidad> localidades = null;
				if(buscar != null) {
					localidades = locServ.findByTiendas(Long.valueOf(provincia));
				}
				else {
					localidades = locServ.findByIdProvincia(Long.valueOf(provincia));
				}
				Gson gson = new Gson();
				response.setContentType( WebConstants.CONTENT_TYPE);
				response.getOutputStream().write(gson.toJson(localidades).getBytes());
			} catch ( DataException e) {
				logger.warn(e.getMessage(),e);
				error.add(ParameterNames.ERROR, ErrorNames.ERR_GENERIC);
				request.setAttribute(AttributesNames.ERROR, error);
				target = UrlBuilder.getUrlForController(request, ControllerPath.TIENDA, ActionNames.BUSCAR, false);

			}
		}
		if(error.hasErrors()) {
			logger.info("Forwarding to..." + target);
			request.getRequestDispatcher(target).forward(request, response);
		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
