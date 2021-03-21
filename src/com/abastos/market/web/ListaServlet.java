package com.abastos.market.web;

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
import com.abastos.market.web.util.ParameterNames;
import com.abastos.market.web.util.ParameterUtils;
import com.abastos.market.web.util.SessionManager;
import com.abastos.market.web.util.UrlBuilder;
import com.abastos.market.web.util.ViewPaths;
import com.abastos.market.web.util.ViewPathsActions;
import com.abastos.model.LineaLista;
import com.abastos.model.Lista;
import com.abastos.model.Particular;
import com.abastos.service.DataException;
import com.abastos.service.LineaListaService;
import com.abastos.service.ListaService;
import com.abastos.service.impl.LineaListaServiceImpl;
import com.abastos.service.impl.ListaServiceImpl;

/**
 * Servlet implementation class ListaServlet
 */
@WebServlet("/lista")
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
		if(ActionNames.BUSCAR.equalsIgnoreCase(action)) {
		
			try {
				
				List<Lista> listas = listaService.findByIdParticular(particular.getId());
				
				request.setAttribute(AttributesNames.LISTA, listas);
				target = ViewPaths.PARTICULAR_LISTA;
			} catch (DataException e) {
				logger.warn(e.getMessage(),e);
				
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
			}
		}
		else if(ActionNames.CREAR.equalsIgnoreCase(action)){
			String nombre = request.getParameter(ParameterNames.NOMBRE_LISTA);
			Lista lista = new Lista();
			lista.setNombre(nombre);
			lista.setIdParticular(particular.getId());
			LineaLista lineaLista = new LineaLista();
			
			try {
				listaService.create(lista);
				target = ViewPathsActions.LISTA_BUSCAR;
				redirect = true;
			} catch (DataException e) {
				logger.warn(e.getMessage(),e);
			}
		}
		else if(ActionNames.UPDATE.equalsIgnoreCase(action)) {
			String idLista = request.getParameter(ParameterNames.LISTA);
			String precio = request.getParameter(ParameterNames.PRECIO);
			String nombre = request.getParameter(ParameterNames.NOMBRE_CASTELLANO);
			String idProducto = request.getParameter(ParameterNames.ID_PRODUCTO);
			LineaLista linList = new LineaLista();
			linList.setId(Long.valueOf(idLista));
			linList.setNombreProducto(nombre);
			linList.setIdProducto(Long.valueOf(idProducto));
			linList.setPrecio(Double.valueOf(precio));
			try {
			lineaListaService.create(linList);
			} catch (DataException e) {
				logger.warn(e.getMessage(),e);
			}
		}
		if(ajax == null) {
		if(redirect) {
			logger.info("Redirect to..." + target);
			response.sendRedirect(UrlBuilder.builder(request, target));
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
