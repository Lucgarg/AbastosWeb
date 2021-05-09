package com.abastos.market.web.controllers;

import java.io.IOException;

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
import com.abastos.model.Particular;
import com.abastos.model.Producto;
import com.abastos.model.PuntuacionTienda;
import com.abastos.model.Tienda;
import com.abastos.service.DataException;
import com.abastos.service.ProductoService;
import com.abastos.service.PuntuacionProductoService;
import com.abastos.service.PuntuacionTiendaService;
import com.abastos.service.TiendaService;
import com.abastos.service.impl.ProductoServiceImpl;
import com.abastos.service.impl.PuntuacionProductoServiceImpl;
import com.abastos.service.impl.PuntuacionTiendaServiceImpl;
import com.abastos.service.impl.TiendaServiceImpl;



public class ValoracionServlet extends HttpServlet {
	private static Logger logger = LogManager.getLogger(ValoracionServlet.class);
	private PuntuacionProductoService puntProdService = null;
	private PuntuacionTiendaService puntTiendService = null;
	private TiendaService tiendaService = null;
	private ProductoService productoService = null;
	public ValoracionServlet() {
		tiendaService = new TiendaServiceImpl();
		puntTiendService = new PuntuacionTiendaServiceImpl();
		puntProdService = new PuntuacionProductoServiceImpl();
		productoService = new ProductoServiceImpl();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(logger.isDebugEnabled()) {
			logger.info("accediendo a servlet valoracion");
			logger.debug(ParameterUtils.print(request.getParameterMap()));
		}
		String action = request.getParameter(ActionNames.ACTION);
		String target = null;
		boolean redirect = false;
		Errors error = new Errors();
		Particular particular = (Particular)SessionManager.get(request, AttributesNames.USUARIO);
		if(ActionNames.BUSCAR.equalsIgnoreCase(action)) {
			String idTienda = request.getParameter(ParameterNames.ID_TIENDA);
			String idProducto = request.getParameter(ParameterNames.ID_PRODUCTO);
			String pedido = request.getParameter(ParameterNames.PEDIDO);
			//se recupera el idioma si esta en sesión si no se pone como defecto "es"
			String idioma = (String)SessionManager.get(request, AttributesNames.IDIOMA);
			if(idioma == null) {
				idioma = "es";
			}
			try {
				//se comprueba si se esta accediendo a la opción de valorar producto o tienda y se comprueba si existen las puntuaciones, en función de su existencia
				//en la jsp se procedera a cambiar la acción del formulario a puntuar o actualizar
				if(idTienda != null) {
					Long tienda = Long.valueOf(idTienda);
					Tienda t = tiendaService.findById(tienda);
					PuntuacionTienda puntuacionTienda = puntTiendService.findPuntuacion(particular.getId(), tienda);
					request.setAttribute(AttributesNames.VALORACION, puntuacionTienda);
					request.setAttribute(AttributesNames.TIENDA, t);
					target =  ViewPaths.VALORACION_TIENDA;

				}
				else {
					Long producto = Long.valueOf(idProducto);
					Producto pro = productoService.findById(producto, idioma);
					Integer puntuacionProducto =	puntProdService.findPuntuacion(particular.getId(),producto);
					if(puntuacionProducto == null) {
						puntuacionProducto = 0;
					}
					request.setAttribute(AttributesNames.VALORACION, puntuacionProducto);
					request.setAttribute(AttributesNames.PRODUCTO, pro);
					target =  ViewPaths.VALORACION_PRODUCTO;

				}
				request.setAttribute(AttributesNames.PEDIDO, pedido);

			} catch ( DataException e) {
				logger.warn(e.getMessage(),e);
				error.add(ParameterNames.ERROR, ErrorNames.ERR_GENERIC);
				request.setAttribute(AttributesNames.ERROR, error);
				target = UrlBuilder.getUrlForController(request, ControllerPath.PRECREATE, ActionNames.INICIO, redirect);

			}

		}

		else if(ActionNames.PUNTUACION_PRODUCTO.equalsIgnoreCase(action)){
			String puntuacion = request.getParameter(ParameterNames.PUNTUACION_PRODUCTO);
			String producto = request.getParameter(ParameterNames.ID_PRODUCTO);

			try {
				puntProdService.create(particular.getId(), Long.valueOf(producto), Integer.valueOf(puntuacion));
				target = UrlBuilder.getUrlForController(request, ControllerPath.PEDIDO, ActionNames.DETALLE, redirect);
			} catch (DataException e) {
				logger.warn(e.getMessage(),e);
				error.add(ParameterNames.ERROR, ErrorNames.ERR_GENERIC);
				request.setAttribute(AttributesNames.ERROR, error);
				target = UrlBuilder.getUrlForController(request, ControllerPath.VALORACION, ActionNames.BUSCAR, redirect);

			}

		}
		else if(ActionNames.PUNTUACION_TIENDA.equalsIgnoreCase(action)) {
			String servDomicilio = request.getParameter(ParameterNames.PUNTUACION_ATD);
			String atencCliente= request.getParameter(ParameterNames.PUNTUACION_ATC);
			String precio= request.getParameter(ParameterNames.PUNTUACION_PRECIO);
			String tienda= request.getParameter(ParameterNames.ID_TIENDA);

			try {
				PuntuacionTienda puntTienda = new PuntuacionTienda();
				puntTienda.setIdPerfilParticular(particular.getId());
				puntTienda.setValoracionAtncCliente(Integer.valueOf(atencCliente));
				puntTienda.setValoracionPrecio(Integer.valueOf(precio));
				if(servDomicilio != null) {
				puntTienda.setValoracionServDomicilio(Integer.valueOf(servDomicilio));
				}
				puntTienda.setIdTienda(Long.valueOf(tienda));
				puntTiendService.create(puntTienda);
				target = UrlBuilder.getUrlForController(request, ControllerPath.PEDIDO, ActionNames.DETALLE, redirect);
			} catch (DataException e) {
				logger.warn(e.getMessage(),e);
				error.add(ParameterNames.ERROR, ErrorNames.ERR_GENERIC);
				request.setAttribute(AttributesNames.ERROR, error);
				target = UrlBuilder.getUrlForController(request, ControllerPath.VALORACION, ActionNames.BUSCAR, redirect);

			}

		}
		else if(ActionNames.UPDATE_VAL_PRODUCTO.equalsIgnoreCase(action)) {
			String puntuacion = request.getParameter(ParameterNames.PUNTUACION_PRODUCTO);
			String producto = request.getParameter(ParameterNames.ID_PRODUCTO);

			try {
				puntProdService.update(particular.getId(), Long.valueOf(producto), Integer.valueOf(puntuacion));
				target = UrlBuilder.getUrlForController(request, ControllerPath.PEDIDO, ActionNames.DETALLE, redirect);
			} catch (DataException e) {
				logger.warn(e.getMessage(),e);
				error.add(ParameterNames.ERROR, ErrorNames.ERR_GENERIC);
				request.setAttribute(AttributesNames.ERROR, error);
				target =  UrlBuilder.urlCallBack(request, true);

			}



		}
		else if(ActionNames.UPDATE_VAL_TIENDA.equalsIgnoreCase(action)) {
			String servDomicilio = request.getParameter(ParameterNames.PUNTUACION_ATD);
			String atencCliente= request.getParameter(ParameterNames.PUNTUACION_ATC);
			String precio= request.getParameter(ParameterNames.PUNTUACION_PRECIO);
			String tienda= request.getParameter(ParameterNames.ID_TIENDA);

			try {

				PuntuacionTienda puntTienda = new PuntuacionTienda();
				puntTienda.setIdPerfilParticular(particular.getId());
				puntTienda.setValoracionAtncCliente(Integer.valueOf(atencCliente));
				puntTienda.setValoracionPrecio(Integer.valueOf(precio));
				if(servDomicilio != null) {
				puntTienda.setValoracionServDomicilio(Integer.valueOf(servDomicilio));
				}
				puntTienda.setIdTienda(Long.valueOf(tienda));
				puntTiendService.update(puntTienda);
				target = UrlBuilder.getUrlForController(request, ControllerPath.PEDIDO, ActionNames.DETALLE, redirect);
			} catch (DataException e) {
				logger.warn(e.getMessage(),e);
				error.add(ParameterNames.ERROR, ErrorNames.ERR_GENERIC);
				request.setAttribute(AttributesNames.ERROR, error);
				target =  UrlBuilder.urlCallBack(request, true);

			}
		}
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
		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
