package com.abastos.market.web.controllers;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.market.web.model.Carrito;
import com.abastos.market.web.model.LineaCarrito;
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
import com.abastos.model.LineaPedido;
import com.abastos.model.Particular;
import com.abastos.model.Pedido;
import com.abastos.model.Producto;
import com.abastos.service.DataException;
import com.abastos.service.LineaPedidoService;
import com.abastos.service.ParticularService;
import com.abastos.service.PedidoService;
import com.abastos.service.ProductoService;
import com.abastos.service.impl.LineaPedidoServiceImpl;
import com.abastos.service.impl.ParticularServiceImpl;
import com.abastos.service.impl.PedidoServiceImpl;
import com.abastos.service.impl.ProductoServiceImpl;
import com.google.gson.Gson;


public class CarritoServlet extends HttpServlet {
	private static Logger logger = LogManager.getLogger(CarritoServlet.class);
	private ProductoService productoService;
	private LineaPedidoService lineaPedidoService;
	private PedidoService pedidoService;
	private ParticularService particularService;
	public CarritoServlet() {
		productoService = new ProductoServiceImpl();
		lineaPedidoService = new LineaPedidoServiceImpl();
		pedidoService = new PedidoServiceImpl();
		particularService =new ParticularServiceImpl();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(logger.isDebugEnabled()) {
			logger.debug(ParameterUtils.print(request.getParameterMap()));
		}
		String action = request.getParameter(ActionNames.ACTION);
		Carrito carrito = (Carrito)SessionManager.get(request, AttributesNames.CARRITO);
		String target = null;
		Errors error = new Errors();
		String idioma = (String)SessionManager.get(request, AttributesNames.IDIOMA);
		if(idioma == null) {
			idioma = "es";
		}
		String ajax = request.getParameter(ParameterNames.AJAX);
		boolean redirect = false;
		//con una llamada ajax se van a�adiendo productos al objeto carrito
		if(ActionNames.ADD.equalsIgnoreCase(action)) {
			String id= request.getParameter(ParameterNames.ID_PRODUCTO);
			String numeroUnidades = request.getParameter(ParameterNames.NUMERO_UNIDADES);
			LineaCarrito linCarrito = new LineaCarrito();
			linCarrito.setIdProducto(Long.valueOf(id));
			linCarrito.setNumeroUnidades(Integer.valueOf(numeroUnidades));

			Producto producto = null;
			boolean repeticion = false;
			//se comprueba que no se ha clicado dos veces en el mismo producto para sumar el numero de unidades que se han solicitado la segunda vez 
			//al n�mero de unidades actuales
			if(repeticion == false) {
				for(Map.Entry<Long, LineaCarrito> lp: carrito.getLineasCarritoMap().entrySet()) {

					if(lp.getValue().getIdProducto() == linCarrito.getIdProducto()) {
						try {
							producto = productoService.findById(linCarrito.getIdProducto(), idioma);

							int numUnidades = lp.getValue().getNumeroUnidades() + linCarrito.getNumeroUnidades();
							// se verifica que el numero de unidades solicitadas no excede el n�mero del stock del producto
							// en tal caso el n�mero total de productos solicitados ser� el m�ximo del stock
							if(numUnidades > producto.getStock()) {
								numUnidades = producto.getStock();
							}
							lp.getValue().setNumeroUnidades(numUnidades);
							repeticion = true;
						} catch (DataException e) {
							logger.warn(e.getMessage(),e);
							error.add(ParameterNames.ERROR, ErrorNames.ERR_GENERIC);
							request.setAttribute(AttributesNames.ERROR, error);
							target = UrlBuilder.getUrlForController(request, ControllerPath.PRECREATE, ActionNames.INICIO, redirect);

						}
					}

				}
				if(!repeticion) {
					carrito.addMap(linCarrito.getIdProducto(), linCarrito);
				}
			}

			SessionManager.set(request, AttributesNames.CARRITO, carrito);

			Gson gson = new Gson();
			response.setContentType(WebConstants.CONTENT_TYPE);
			response.getOutputStream().write(gson.toJson(carrito.getLineasCarritoMap().size()).getBytes());

		}
		else if(ActionNames.DETALLE_CARRITO.equalsIgnoreCase(action)) {
			Producto producto = null;
			Pedido pedido = new Pedido();
			try {
				Particular parti = (Particular)SessionManager.get(request, AttributesNames.USUARIO);
				if(parti != null) {
					parti = particularService.findById(parti.getId());
					SessionManager.set(request, AttributesNames.USUARIO, parti);
				}
				//se recupera la informaci�n de cada producto
				for(Map.Entry<Long, LineaCarrito> lc: carrito.getLineasCarritoMap().entrySet()) {

					producto = productoService.findById(lc.getValue().getIdProducto(), idioma);
					LineaPedido linPedido = new LineaPedido();
					linPedido.setIdTienda(producto.getIdTienda());
					linPedido.setNombreProducto(producto.getNombre());
					linPedido.setNumeroUnidades(lc.getValue().getNumeroUnidades());
					linPedido.setPrecio(producto.getPrecioFinal());
					linPedido.setIdProducto(producto.getId());
					//se verifica si tiene oferta
					if(producto.getOferta() != null) {
						linPedido.setDenominador(producto.getOferta().getDenominador());
						linPedido.setDescuentoFijo(producto.getOferta().getDescuentoFijo());
						linPedido.setDescuentoPcn(producto.getOferta().getDescuentoPcn());
						linPedido.setIdOferta(producto.getOferta().getId());
						linPedido.setIdProdOferta(producto.getOferta().getIdProdOferta());
						linPedido.setIdTipoOferta(producto.getOferta().getIdTipoOferta());
						linPedido.setNumerador(producto.getOferta().getNumerador());

						linPedido.setNombreOferta(producto.getOferta().getNombreOferta());

					}
					linPedido.setPrecioFinal(lineaPedidoService.calcPrecio(linPedido));
					linPedido.setPrecio(producto.getPrecio());
					linPedido.setIdTienda(producto.getIdTienda());
					pedido.add(linPedido);
				}
				//por defecto para que aparezca el precio total se pone aplicar descuento con puntos a false
				pedido.setAplicarDescuento(false);
				pedido.setPrecioTotal(pedidoService.calcPrecio(pedido));
				// se recuperan los productos que pertenecen a la oferta "compra y llevate"
				Map<Long, Producto> ofertPro= productoService.findByProductOfert(idioma);
				SessionManager.set(request, AttributesNames.PEDIDO, pedido);
				request.setAttribute(AttributesNames.PRODUCTO_OFERTA, ofertPro);
				target = ViewPaths.PEDIDO_RESULTS;

			} catch (DataException e) {
				logger.warn(e.getMessage(),e);
				error.add(ParameterNames.ERROR, ErrorNames.ERR_GENERIC_DETAIL_CART);
				request.setAttribute(AttributesNames.ERROR, error);
				target = UrlBuilder.getUrlForController(request, ControllerPath.PRECREATE, ActionNames.INICIO, redirect);

			}
		}
		else if(ActionNames.ELIMINAR.equals(action)) {
			String producto = request.getParameter(ParameterNames.ID_PRODUCTO);
			carrito.getLineasCarritoMap().remove(Long.valueOf(producto));
			redirect = true;
			target = UrlBuilder.getUrlForController(request, ControllerPath.CARRITO, ActionNames.DETALLE_CARRITO, redirect);

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
			}
		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
