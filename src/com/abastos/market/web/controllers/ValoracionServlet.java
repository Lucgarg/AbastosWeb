package com.abastos.market.web.controllers;

import java.io.IOException;
import java.util.Map;

import javax.print.DocFlavor.URL;
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
import com.abastos.market.web.util.ParameterNames;
import com.abastos.market.web.util.ParameterUtils;
import com.abastos.market.web.util.SessionManager;
import com.abastos.market.web.util.UrlBuilder;
import com.abastos.market.web.util.ViewPaths;
import com.abastos.market.web.util.ViewPathsctions;
import com.abastos.model.Particular;
import com.abastos.model.PuntuacionTienda;
import com.abastos.service.DataException;
import com.abastos.service.PuntuacionProductoService;
import com.abastos.service.PuntuacionTiendaService;
import com.abastos.service.impl.PuntuacionProductoServiceImpl;
import com.abastos.service.impl.PuntuacionTiendaServiceImpl;


@WebServlet("/valoracion")
public class ValoracionServlet extends HttpServlet {
	private static Logger logger = LogManager.getLogger(ValoracionServlet.class);
	private PuntuacionProductoService puntProdService = null;
	private PuntuacionTiendaService puntTiendService = null;
	public ValoracionServlet() {
		puntTiendService = new PuntuacionTiendaServiceImpl();
		puntProdService = new PuntuacionProductoServiceImpl();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(logger.isDebugEnabled()) {
			logger.info("accediendo a servlet valoracion");
			logger.debug(ParameterUtils.print(request.getParameterMap()));
		}
		String action = request.getParameter(ActionNames.ACTION);
		String target = null;
		boolean redirect = false;
		Particular particular = (Particular)SessionManager.get(request, AttributesNames.USUARIO);
		if(ActionNames.BUSCAR.equalsIgnoreCase(action)) {
			String idTienda = request.getParameter(ParameterNames.ID_TIENDA);
			String idProducto = request.getParameter(ParameterNames.ID_PRODUCTO);
			String pedido = request.getParameter(ParameterNames.PEDIDO);
		
			try {
				if(idTienda != null) {
					PuntuacionTienda puntuacionTienda = puntTiendService.findPuntuacion(Long.valueOf(idTienda), particular.getId());
					request.setAttribute(AttributesNames.VALORACION, puntuacionTienda);
					request.setAttribute(AttributesNames.TIENDA, idTienda);
					target =  ViewPaths.VALORACION_TIENDA;
					
				}
				else {
					
					
					Integer puntuacionProducto =	puntProdService.findPuntuacion(particular.getId(),Long.valueOf(idProducto));
					if(puntuacionProducto == null) {
						puntuacionProducto = 0;
					}
					request.setAttribute(AttributesNames.VALORACION, puntuacionProducto);
					request.setAttribute(AttributesNames.PRODUCTO, idProducto);
					target =  ViewPaths.VALORACION_PRODUCTO;
					
				}
				
				request.setAttribute(AttributesNames.PEDIDO, pedido);
				
			} catch ( DataException e) {
				logger.warn(e.getMessage(),e);
			}
		}

		else if(ActionNames.PUNTUACION_PRODUCTO.equalsIgnoreCase(action)){
			String puntuacion = request.getParameter(ParameterNames.PUNTUACION_PRODUCTO);
			String producto = request.getParameter(ParameterNames.ID_PRODUCTO);
			String pedido = request.getParameter(ParameterNames.PEDIDO);
			try {
				puntProdService.create(particular.getId(), Long.valueOf(producto), Integer.valueOf(puntuacion));
				target = UrlBuilder.getUrlForController(request, ControllerPath.PEDIDO, ActionNames.DETALLE, redirect);
			} catch (DataException e) {
				logger.warn(e.getMessage(),e);
			}
		}
		else if(ActionNames.PUNTUACION_TIENDA.equalsIgnoreCase(action)) {
			String servDomicilio = request.getParameter(ParameterNames.PUNTUACION_ATD);
			String atencCliente= request.getParameter(ParameterNames.PUNTUACION_ATC);
			String precio= request.getParameter(ParameterNames.PUNTUACION_PRECIO);
			String tienda= request.getParameter(ParameterNames.ID_TIENDA);
			String pedido = request.getParameter(ParameterNames.PEDIDO);
			try {
				PuntuacionTienda puntTienda = new PuntuacionTienda();
				puntTienda.setIdPerfilParticular(particular.getId());
				puntTienda.setValoracionAtncCliente(Integer.valueOf(atencCliente));
				puntTienda.setValoracionPrecio(Integer.valueOf(precio));
				puntTienda.setValoracionServDomicilio(Integer.valueOf(servDomicilio));
				puntTienda.setIdTienda(Long.valueOf(tienda));
				puntTiendService.create(puntTienda);
				target = UrlBuilder.getUrlForController(request, ControllerPath.PEDIDO, ActionNames.DETALLE, redirect);
			} catch (DataException e) {
				logger.warn(e.getMessage(),e);
			}
		}
		else if(ActionNames.UPDATE_VAL_PRODUCTO.equalsIgnoreCase(action)) {
			String puntuacion = request.getParameter(ParameterNames.PUNTUACION_PRODUCTO);
			String producto = request.getParameter(ParameterNames.ID_PRODUCTO);
			String pedido = request.getParameter(ParameterNames.PEDIDO);
			try {
				puntProdService.update(particular.getId(), Long.valueOf(producto), Integer.valueOf(puntuacion));
				target = UrlBuilder.getUrlForController(request, ControllerPath.PEDIDO, ActionNames.DETALLE, redirect);
			} catch (DataException e) {
				logger.warn(e.getMessage(),e);
			}


		}
		else if(ActionNames.UPDATE_VAL_TIENDA.equalsIgnoreCase(action)) {
			String servDomicilio = request.getParameter(ParameterNames.PUNTUACION_ATD);
			String atencCliente= request.getParameter(ParameterNames.PUNTUACION_ATC);
			String precio= request.getParameter(ParameterNames.PUNTUACION_PRECIO);
			String tienda= request.getParameter(ParameterNames.ID_TIENDA);
			String pedido = request.getParameter(ParameterNames.PEDIDO);
			try {
				PuntuacionTienda puntTienda = new PuntuacionTienda();
				puntTienda.setIdPerfilParticular(particular.getId());
				puntTienda.setValoracionAtncCliente(Integer.valueOf(atencCliente));
				puntTienda.setValoracionPrecio(Integer.valueOf(precio));
				puntTienda.setValoracionServDomicilio(Integer.valueOf(servDomicilio));
				puntTienda.setIdTienda(Long.valueOf(tienda));
				puntTiendService.update(puntTienda);
				target = UrlBuilder.getUrlForController(request, ControllerPath.PEDIDO, ActionNames.DETALLE, redirect);
			} catch (DataException e) {
				logger.warn(e.getMessage(),e);
			}


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
