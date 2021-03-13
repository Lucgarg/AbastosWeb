package com.abastos.market.web;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.dao.util.DataUtils;
import com.abastos.market.web.util.ActionNames;
import com.abastos.market.web.util.AttributesNames;
import com.abastos.market.web.util.ParameterNames;
import com.abastos.market.web.util.ParameterUtils;
import com.abastos.market.web.util.ViewPaths;
import com.abastos.model.Oferta;
import com.abastos.model.Producto;
import com.abastos.service.DataException;
import com.abastos.service.OfertaService;
import com.abastos.service.ProductoService;
import com.abastos.service.impl.OfertaServiceImpl;
import com.abastos.service.impl.ProductoServiceImpl;

/**
 * Servlet implementation class OfertaServlet
 */
@WebServlet("/oferta")
public class OfertaServlet extends HttpServlet {

	private static Logger logger = LogManager.getLogger(OfertaServlet.class);

	private OfertaService ofertaService = null;
	private ProductoService productoService = null;

	public OfertaServlet() {

		ofertaService = new OfertaServiceImpl();
		productoService = new ProductoServiceImpl();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(logger.isDebugEnabled()) {
			logger.debug(ParameterUtils.print(request.getParameterMap()));
		}
		String action = request.getParameter(ActionNames.ACTION);
		String target = null;
		boolean redirect = false;
		//forward a resultado de oferta de una determinada empresa
		if(ActionNames.BUSCAR.equalsIgnoreCase(action)) {
			String empresa = request.getParameter(ParameterNames.ID_EMPRESA);
			try {
				List<Oferta> ofertas = ofertaService.findByIdEmpresa(Long.valueOf(empresa));
				List<Producto> productos = new ArrayList<Producto>();
				//TODO ver productoOferta
				for(Oferta o : ofertas) {
					if(o.getIdProdOferta() != 0) {
						productos.add(productoService.findById(o.getIdProdOferta(), "es"));

					}
				}
				request.setAttribute(AttributesNames.EMPRESA, empresa);
				request.setAttribute(AttributesNames.PRODUCTO_OFERTA, productos);
				request.setAttribute(AttributesNames.OFERTAS, ofertas);
				target = ViewPaths.OFERTA_RESULTS;
			} catch (NumberFormatException | DataException e) {
				logger.warn(e.getMessage(),e);
			}
		}
		//crea oferta redirect a resultado de ofertas
		else if (ActionNames.CREAR.equalsIgnoreCase(action)) {
			String empresa = request.getParameter(ParameterNames.ID_EMPRESA);
			String nombre = request.getParameter(ParameterNames.NOMBRE_OFERTA);
			String tipoOferta = request.getParameter(ParameterNames.TIPO_OFERTA);
			String desctPCN = request.getParameter(ParameterNames.DESCT_PCN);
			String desctFijo = request.getParameter(ParameterNames.DESCT_FIJO);
			String numerador = request.getParameter(ParameterNames.NUMERADOR);
			String denominador = request.getParameter(ParameterNames.DENOMINADOR);
			String fechaVigencia = request.getParameter(ParameterNames.FECHA_VIG);
			String horaVigencia = request.getParameter(ParameterNames.HORA_VIG);
			String fechaCaducidad = request.getParameter(ParameterNames.FECHA_CAD);
			String horaCaducidad = request.getParameter(ParameterNames.HORA_CAD);
			Oferta oferta = new Oferta();

			if(denominador !="") {
				oferta.setDenominador(Integer.valueOf(denominador));
			}
			if(desctFijo != "") {
				oferta.setDescuentoFijo(Double.valueOf(desctFijo));
			}
			if(desctPCN != "") {
				oferta.setDescuentoPcn(Double.valueOf(desctPCN));
			}
			if(numerador != "") {
				oferta.setNumerador(Integer.valueOf(numerador));
			}
			oferta.setIdTipoOferta(Integer.valueOf(tipoOferta));
			oferta.setNombreOferta(nombre);
			oferta.setIdEmpresa(Long.valueOf(empresa));
			//TODO factorizar
			String fechaDesde = new StringBuilder().append(fechaVigencia)
					.append(" ").append(horaVigencia).append(":00").toString();
			String fechaHasta = new StringBuilder().append(fechaCaducidad)
					.append(" ").append(horaCaducidad).append(":00").toString();
			//TODO revisar
			try {
				oferta.setFechaDesde(DataUtils.formatDate(fechaDesde));
				oferta.setFechaHasta(DataUtils.formatDate(fechaHasta));
			} catch (ParseException e) {

				logger.warn(e.getMessage(),e);
			}

			try {
				ofertaService.create(oferta);

				request.getRequestDispatcher(new StringBuilder().append(ViewPaths.OFERTA_ACTION_BUSCAR)
						.append(empresa).toString())
				.forward(request, response);
				target = ViewPaths.OFERTA_ACTION_BUSCAR + empresa;
				redirect = true;
			} catch (DataException e) {
				logger.warn(e.getMessage(),e);
			}
		}
		if(redirect) {
			logger.info("Redirect to..." + target);
			response.sendRedirect(request.getContextPath() + target);
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
