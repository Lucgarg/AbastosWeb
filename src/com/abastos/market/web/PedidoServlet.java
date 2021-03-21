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
import com.abastos.model.LineaPedido;
import com.abastos.model.Particular;
import com.abastos.model.Pedido;
import com.abastos.model.Producto;
import com.abastos.service.DataException;
import com.abastos.service.PedidoService;
import com.abastos.service.ProductoService;
import com.abastos.service.TiendaService;
import com.abastos.service.exceptions.MailException;
import com.abastos.service.impl.PedidoServiceImpl;
import com.abastos.service.impl.ProductoServiceImpl;
import com.abastos.service.impl.TiendaServiceImpl;
import com.google.gson.Gson;

/**
 * Servlet implementation class PedidoServlet
 */
@WebServlet("/pedido")
public class PedidoServlet extends HttpServlet {
	private static Logger logger = LogManager.getLogger(PedidoServlet.class);
	private ProductoService productoService = null;
	private PedidoService pedidoService = null;
	private TiendaService tiendaService = null;
	public PedidoServlet() {
		super();	
		productoService = new ProductoServiceImpl();
		pedidoService = new PedidoServiceImpl();
		tiendaService = new TiendaServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(logger.isDebugEnabled()) {
			logger.debug(ParameterUtils.print(request.getParameterMap()));
		}
		String action = request.getParameter(ActionNames.ACTION);
		Particular particular = (Particular)SessionManager.get(request, AttributesNames.USUARIO);
		String target = null;
		boolean redirect = false;
		if(ActionNames.ADD.equalsIgnoreCase(action)) {
			String id= request.getParameter(ParameterNames.ID_PRODUCTO);
			String numeroUnidades = request.getParameter(ParameterNames.NUMERO_UNIDADES);
			LineaPedido linPedido = new LineaPedido();
			linPedido.setIdProducto(Long.valueOf(id));
			linPedido.setNumeroUnidades(Integer.valueOf(numeroUnidades));
			Pedido pedido = (Pedido)SessionManager.get(request, AttributesNames.PEDIDO);
			boolean repeticion = false;
			if(pedido != null) {
				for(LineaPedido lp: pedido.getLineaPedido()) {
					if(lp.getIdProducto() == linPedido.getIdProducto()) {
						int numUnidades = lp.getNumeroUnidades() + linPedido.getNumeroUnidades();
						lp.setNumeroUnidades(numUnidades);
						repeticion = true;
					}

				}
				if(!repeticion) {
					pedido.add(linPedido);
				}
			}
			else {
				pedido = new Pedido();
				pedido.add(linPedido);
				pedido.setIdEstado('S');
			}
			SessionManager.set(request, AttributesNames.PEDIDO, pedido);
			Gson gson = new Gson();
			response.setContentType("application/json");
			response.getOutputStream().write(gson.toJson(pedido.getLineaPedido().size()).getBytes());

		}
		else if(ActionNames.DETALLE.equalsIgnoreCase(action)) {
			Pedido pedido = null;
			//comprobamos si idPedido es null, si no lo es, la llamada del .jsp procede del historial de pedidos del particular
			String idPedido = request.getParameter(ParameterNames.PEDIDO);
			if(idPedido != null) {
				try {
					pedido = pedidoService.findById(Long.valueOf(idPedido));
				} catch (DataException e) {
					logger.warn(e.getMessage(),e);
				}
			}else {
				pedido = (Pedido)SessionManager.get(request, AttributesNames.PEDIDO);
			}

			try {
				for(LineaPedido lp: pedido.getLineaPedido()) {

					Producto producto = productoService.findById(lp.getIdProducto(), "es");

					if(producto.getOferta() != null) {

						lp.setDenominador(producto.getOferta().getDenominador());
						lp.setDescuentoFijo(producto.getOferta().getDescuentoFijo());
						lp.setDescuentoPcn(producto.getOferta().getDescuentoPcn());
						lp.setIdOferta(producto.getOferta().getId());
						lp.setIdProdOferta(producto.getOferta().getIdProdOferta());
						lp.setIdTipoOferta(producto.getOferta().getIdTipoOferta());
						lp.setNumerador(producto.getOferta().getNumerador());
						lp.setNombreProdOferta(producto.getOferta().getNombreProdOferta());
						lp.setNombreOferta(producto.getOferta().getNombreOferta());

					}

					lp.setIdTienda(producto.getId());
					lp.setNombreProducto(producto.getNombre());
					lp.setPrecio(producto.getPrecioFinal());
				}
				if(idPedido == null) {
				pedido.setAplicarDescuento(false);
				pedido.setIdEstado("S".charAt(0));
				if(pedido.getIdParticular() != particular.getId()) {
					pedido.setIdParticular(particular.getId());
					Pedido pedidoResult = pedidoService.create(pedido);
					pedido = pedidoService.findById(pedidoResult.getId());
				
				}
				
				SessionManager.set(request, AttributesNames.PEDIDO, pedido);
				redirect = true;
				}
				else {
					request.setAttribute(AttributesNames.PEDIDO, pedido);
				}
				System.out.println(pedido.getIdEstado());
				target = ViewPaths.PEDIDO_RESULTS;
				
			} catch (DataException | MailException e) {
				logger.warn(e.getMessage(),e);
			}
		}
		else if(ActionNames.HISTORIAL_PEDIDO.equalsIgnoreCase(action)) {
			try {
				List<Pedido> listPedido = pedidoService.findByIdParticular(particular.getId());
				request.setAttribute(AttributesNames.PEDIDO, listPedido);
				target = ViewPaths.HISTORIAL_PEDIDO;
			} catch (DataException e) {
				logger.warn(e.getMessage(),e);
			}

		}
		if(target != null) {
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
