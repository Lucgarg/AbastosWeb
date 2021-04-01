package com.abastos.market.web.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.abastos.market.web.util.SessionManager;
import com.abastos.market.web.util.UrlBuilder;
import com.abastos.market.web.util.ViewPaths;
import com.abastos.model.LineaPedido;
import com.abastos.model.Particular;
import com.abastos.model.Pedido;
import com.abastos.service.DataException;
import com.abastos.service.MailService;
import com.abastos.service.PedidoService;
import com.abastos.service.ProductoService;
import com.abastos.service.exceptions.MailException;
import com.abastos.service.impl.MailServiceImpl;
import com.abastos.service.impl.PedidoServiceImpl;
import com.abastos.service.impl.ProductoServiceImpl;


/**
 * Servlet implementation class PedidoServlet
 */
@WebServlet("/pedido")
public class PedidoServlet extends HttpServlet {
	private static Logger logger = LogManager.getLogger(PedidoServlet.class);
	private ProductoService productoService = null;
	private PedidoService pedidoService = null;

	private Map<String, Object> infoEmail = null;
	private MailService mailService = null;
	public PedidoServlet() {

		productoService = new ProductoServiceImpl();
		pedidoService = new PedidoServiceImpl();
		mailService = new MailServiceImpl();
		infoEmail = new HashMap<String, Object>();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(logger.isDebugEnabled()) {
			logger.debug(ParameterUtils.print(request.getParameterMap()));
		}
		String action = request.getParameter(ActionNames.ACTION);
		Particular particular = (Particular)SessionManager.get(request, AttributesNames.USUARIO);
		Errors error = new Errors();
		String target = null;
		boolean redirect = false;

		if(ActionNames.CREAR.equalsIgnoreCase(action)) {
			Pedido pedido = (Pedido)SessionManager.get(request, AttributesNames.PEDIDO);
			String aplicarDescuento = request.getParameter(ParameterNames.APLICAR_DESCUENTO);
			if(particular == null) {
				logger.info(request.getParameter("url"));
				error.add(ActionNames.CREAR_PEDIDO, ErrorNames.ERR_NOT_USER_LOG);
				request.setAttribute(AttributesNames.ERROR, error);
				target = UrlBuilder.urlCallBack(request, true);
				logger.info(target);
			}
			if(!error.hasErrors()) {
				pedido.setAplicarDescuento(Boolean.valueOf(aplicarDescuento));
				pedido.setIdParticular(particular.getId());
				pedido.setIdEstado('S');
				try {
					pedidoService.create(pedido);
					int puntos = pedidoService.calcPuntos(pedido.getPrecioTotal());
					infoEmail.put("user", particular);
					infoEmail.put("pedido", pedido);
					infoEmail.put("lineaP", pedido.getLineaPedido());
					infoEmail.put("puntos", puntos);
					mailService.sendMailHtml(infoEmail, 5L, particular.getEmail());
					SessionManager.remove(request, AttributesNames.CARRITO);
					SessionManager.remove(request, AttributesNames.PEDIDO);
					redirect = true;
					target = UrlBuilder.getUrlForController(request, ControllerPath.PRECREATE, ActionNames.INICIO, redirect);
					

				}catch(MailException e) {
					error.add(ActionNames.SEND_EMAIL, ErrorNames.ERR_SEND_EMAIL);
					request.setAttribute(AttributesNames.ERROR, error);
					target = UrlBuilder.getUrlForController(request, ControllerPath.CARRITO, ActionNames.DETALLE_CARRITO, redirect);
					logger.warn(e.getMessage(),e);
				}catch (DataException e) {
					logger.warn(e.getMessage(),e);
				}
				
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
		else if(ActionNames.DETALLE.equalsIgnoreCase(action)) {
			String idPedido = request.getParameter(ParameterNames.PEDIDO);
			try {
				Pedido pedido = pedidoService.findById(Long.valueOf(idPedido));

				request.setAttribute(AttributesNames.PEDIDO, pedido);
				target =  ViewPaths.LINEA_PEDIDO;
			} catch ( DataException e) {
				logger.warn(e.getMessage(),e);
			}
		}
		if(target != null) {
			if(redirect) { 
				logger.info("Redirect to..." + target);
				response.sendRedirect( target);
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
