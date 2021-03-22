package com.abastos.market.web.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.market.web.model.Carrito;
import com.abastos.market.web.model.LineaCarrito;
import com.abastos.market.web.util.ActionNames;
import com.abastos.market.web.util.AttributesNames;
import com.abastos.market.web.util.ParameterNames;
import com.abastos.market.web.util.ParameterUtils;
import com.abastos.market.web.util.SessionManager;
import com.abastos.market.web.util.ViewPaths;
import com.abastos.model.LineaPedido;
import com.abastos.model.Particular;
import com.abastos.model.Pedido;
import com.abastos.model.Producto;
import com.abastos.service.DataException;
import com.abastos.service.LineaPedidoService;
import com.abastos.service.PedidoService;
import com.abastos.service.ProductoService;
import com.abastos.service.impl.LineaPedidoServiceImpl;
import com.abastos.service.impl.PedidoServiceImpl;
import com.abastos.service.impl.ProductoServiceImpl;
import com.google.gson.Gson;

@WebServlet("/carrito")
public class CarritoServlet extends HttpServlet {
	private static Logger logger = LogManager.getLogger(CarritoServlet.class);
	private ProductoService productoService;
	private LineaPedidoService lineaPedidoService;
	private PedidoService pedidoService;
	public CarritoServlet() {
		productoService = new ProductoServiceImpl();
		lineaPedidoService = new LineaPedidoServiceImpl();
		pedidoService = new PedidoServiceImpl();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(logger.isDebugEnabled()) {
			logger.debug(ParameterUtils.print(request.getParameterMap()));
		}
		String action = request.getParameter(ActionNames.ACTION);
		Carrito carrito = (Carrito)SessionManager.get(request, AttributesNames.CARRITO);
		String target = null;
		String ajax = request.getParameter(ParameterNames.AJAX);
		boolean redirect = false;
		
		if(ActionNames.ADD.equalsIgnoreCase(action)) {
			String id= request.getParameter(ParameterNames.ID_PRODUCTO);
			String numeroUnidades = request.getParameter(ParameterNames.NUMERO_UNIDADES);
			LineaCarrito linCarrito = new LineaCarrito();
			linCarrito.setIdProducto(Long.valueOf(id));
			linCarrito.setNumeroUnidades(Integer.valueOf(numeroUnidades));
			boolean repeticion = false;
			if(carrito != null) {
				for(LineaCarrito lp: carrito.getLineaCarrito()) {
					if(lp.getIdProducto() == linCarrito.getIdProducto()) {
						int numUnidades = lp.getNumeroUnidades() + linCarrito.getNumeroUnidades();
						lp.setNumeroUnidades(numUnidades);
						repeticion = true;
					}

				}
				if(!repeticion) {
					carrito.add(linCarrito);
				}
			}
			else {
				carrito = new Carrito();
				carrito.add(linCarrito);
			}
			SessionManager.set(request, AttributesNames.CARRITO, carrito);
			Gson gson = new Gson();
			response.setContentType("application/json");
			response.getOutputStream().write(gson.toJson(carrito.getLineaCarrito().size()).getBytes());
		}
		else if(ActionNames.DETALLE_CARRITO.equalsIgnoreCase(action)) {
			Producto producto = null;
			Pedido pedido = new Pedido();
			try {
			for(LineaCarrito lc: carrito.getLineaCarrito()) {
			
					producto = productoService.findById(lc.getIdProducto(), "es");
					LineaPedido linPedido = new LineaPedido();
					if(producto.getOferta() != null) {
					linPedido.setDenominador(producto.getOferta().getDenominador());
					linPedido.setDescuentoFijo(producto.getOferta().getDescuentoFijo());
					linPedido.setDescuentoPcn(producto.getOferta().getDescuentoPcn());
					linPedido.setIdOferta(producto.getOferta().getId());
					linPedido.setIdProdOferta(producto.getOferta().getIdProdOferta());
					linPedido.setIdTipoOferta(producto.getOferta().getIdTipoOferta());
					linPedido.setNumerador(producto.getOferta().getNumerador());
					linPedido.setNombreProdOferta(producto.getOferta().getNombreProdOferta());
					linPedido.setNombreOferta(producto.getOferta().getNombreOferta());
					}
					linPedido.setIdTienda(producto.getIdTienda());
					linPedido.setNombreProducto(producto.getNombre());
					linPedido.setNumeroUnidades(lc.getNumeroUnidades());
					linPedido.setPrecio(producto.getPrecio());
					linPedido.setIdProducto(producto.getId());
					linPedido.setPrecioFinal(lineaPedidoService.calcPrecio(linPedido));
					pedido.add(linPedido);
			}
			pedido.setAplicarDescuento(false);
			pedido.setPrecioTotal(pedidoService.calcPrecio(pedido));
			SessionManager.set(request, AttributesNames.PEDIDO, pedido);
			target = ViewPaths.PEDIDO_RESULTS;
			} catch (DataException e) {
				logger.warn(e.getMessage(),e);
			}
		}
			if(ajax == null) {
			if(redirect) { 
				logger.info("Redirect to..." + target);
				response.sendRedirect(request.getContextPath() + target);
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
