package com.abastos.market.web.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
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
import com.abastos.market.web.util.SessionManager;
import com.abastos.market.web.util.UrlBuilder;
import com.abastos.market.web.util.ViewPaths;
import com.abastos.market.web.util.WebConstants;
import com.abastos.model.LineaLista;
import com.abastos.model.Lista;
import com.abastos.model.Particular;
import com.abastos.service.DataException;
import com.abastos.service.LineaListaService;
import com.abastos.service.ListaService;
import com.abastos.service.impl.LineaListaServiceImpl;
import com.abastos.service.impl.ListaServiceImpl;
import com.google.gson.Gson;

/**
 * Servlet implementation class ListaServlet
 */

public class ListaServlet extends HttpServlet {
	private static Logger logger = LogManager.getLogger(ListaServlet.class);
	private ListaService listaService = null;
	private LineaListaService lineaListaService = null;
	public ListaServlet() {
		listaService = new ListaServiceImpl();
		lineaListaService = new LineaListaServiceImpl();



	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(logger.isDebugEnabled()) {
			logger.debug(ParameterUtils.print(request.getParameterMap()));
		}
		String action = request.getParameter(ActionNames.ACTION);
		Particular particular = (Particular)SessionManager.get(request, AttributesNames.USUARIO);
		String ajax = request.getParameter(ParameterNames.AJAX);
		String target = null;
		boolean redirect = false;
		Errors error = new Errors();
		if(ActionNames.BUSCAR.equalsIgnoreCase(action)) {

			try {

				List<Lista> listas = listaService.findByIdParticular(particular.getId());
				request.setAttribute(AttributesNames.LISTA, listas);
				target = ViewPaths.PARTICULAR_LISTA;
		
			} catch (DataException e) {
				logger.warn(e.getMessage(),e);
				error.add(ParameterNames.ERROR, ErrorNames.ERR_GENERIC_SEARCH_LISTA);
				request.setAttribute(AttributesNames.ERROR, error);
				target = UrlBuilder.getUrlForController(request, ControllerPath.PRECREATE, ActionNames.INICIO, redirect);
			}
		}
		else if(ActionNames.DETALLE.equalsIgnoreCase(action)) {
			String idLista = request.getParameter(ParameterNames.LISTA);

			try {
				Lista listas = listaService.findById(Long.valueOf(idLista));
				request.setAttribute(AttributesNames.LISTA, listas);
				target = ViewPaths.LISTA_LINEAS;
				
			} catch ( DataException e) {
				logger.warn(e.getMessage(),e);
				error.add(ParameterNames.ERROR, ErrorNames.ERR_GENERIC_DETAIL_LISTA);
				request.setAttribute(AttributesNames.ERROR, error);
				target = UrlBuilder.getUrlForController(request, ControllerPath.LISTA, ActionNames.BUSCAR, redirect);
				
			}
		}
		
		else if(ActionNames.CREAR.equalsIgnoreCase(action)){
			String nombre = request.getParameter(ParameterNames.NOMBRE_LISTA);
		
			Lista lista = new Lista();
			lista.setNombre(nombre);
			lista.setIdParticular(particular.getId());
			
		
			try {
				listaService.create(lista);
				
				redirect = true;
				target = UrlBuilder.getUrlForController(request,ControllerPath.LISTA ,ActionNames.BUSCAR, redirect);  

			} catch (DataException e) {
				logger.warn(e.getMessage(),e);
				error.add(ParameterNames.ERROR, ErrorNames.ERR_GENERIC_CREATE_LISTA);
				request.setAttribute(AttributesNames.ERROR, error);
				target = UrlBuilder.getUrlForController(request, ControllerPath.LISTA, ActionNames.BUSCAR, redirect);
				
			}
		}
		// desde una llamada ajax se añáde desde el detalle de producto, este a una determinada lista.
		else if(ActionNames.UPDATE.equalsIgnoreCase(action)) {
			
			String idLista = request.getParameter(ParameterNames.LISTA);
			String precio = request.getParameter(ParameterNames.PRECIO);
			String nombre = UrlBuilder.decode(request.getParameter(ParameterNames.NOMBRE_CASTELLANO));
			String idProducto = request.getParameter(ParameterNames.ID_PRODUCTO);
			LineaLista linList = new LineaLista();
			Long idPro = Long.valueOf(idProducto);
			Long idList = Long.valueOf(idLista);
			linList.setId(idList);
			linList.setNombreProducto(nombre);
			linList.setIdProducto(idPro);
			linList.setPrecio(Double.valueOf(precio));
			try {
				Gson gson = new Gson();
				response.setContentType( WebConstants.CONTENT_TYPE);
				LineaLista linL = lineaListaService.findById(idList, idPro);
				if(linL == null) {
				lineaListaService.create(linList);
				response.getOutputStream().write(gson.toJson(ParameterNames.TRUE).getBytes());
				}
				else {
					response.getOutputStream().write(gson.toJson(ParameterNames.FALSE).getBytes());
				}
			} catch (DataException e) {
				logger.warn(e.getMessage(),e);
				
				
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
