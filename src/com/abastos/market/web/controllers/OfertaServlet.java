package com.abastos.market.web.controllers;

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
import com.abastos.market.web.util.ControllerPath;
import com.abastos.market.web.util.ErrorNames;
import com.abastos.market.web.util.ParameterNames;
import com.abastos.market.web.util.ParameterUtils;
import com.abastos.market.web.util.SessionManager;
import com.abastos.market.web.util.UrlBuilder;
import com.abastos.market.web.util.ValidationUtils;
import com.abastos.market.web.util.ViewPaths;
import com.abastos.market.web.util.ViewPathsctions;
import com.abastos.model.Empresa;
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
		Errors error = new Errors();
		String target = null;
		boolean redirect = false;
		//forward a resultado de oferta de una determinada empresa
		if(ActionNames.BUSCAR.equalsIgnoreCase(action)) {
			Empresa empresa = (Empresa)SessionManager.get(request, AttributesNames.EMPRESA);
			try {
				List<Oferta> ofertas = ofertaService.findByIdEmpresa(Long.valueOf(empresa.getId()));
				List<Producto> productos = new ArrayList<Producto>();

				request.setAttribute(AttributesNames.OFERTAS, ofertas);
				target = ViewPaths.OFERTA_RESULTS;
			} catch (NumberFormatException | DataException e) {
				logger.warn(e.getMessage(),e);
			}
		}
		//crea oferta redirect a resultado de ofertas
		else if (ActionNames.CREAR.equalsIgnoreCase(action)) {
			String nombre = request.getParameter(ParameterNames.NOMBRE_OFERTA);
			String fechaVigencia = request.getParameter(ParameterNames.FECHA_VIG);
			String horaVigencia = request.getParameter(ParameterNames.HORA_VIG);
			String fechaCaducidad = request.getParameter(ParameterNames.FECHA_CAD);
			String horaCaducidad = request.getParameter(ParameterNames.HORA_CAD);
			Empresa empresa = (Empresa)SessionManager.get(request, AttributesNames.EMPRESA);
			Oferta oferta = new Oferta();


			oferta.setDenominador(ValidationUtils.integerValidator(request, ParameterNames.DENOMINADOR, error));
			oferta.setDescuentoFijo(ValidationUtils.doubleValidator(request, ParameterNames.DESCT_FIJO, error));
			oferta.setDescuentoPcn(ValidationUtils.doubleValidator(request, ParameterNames.DESCT_PCN, error));
			oferta.setNumerador(ValidationUtils.integerValidator(request, ParameterNames.NUMERADOR, error));
			oferta.setIdTipoOferta(ValidationUtils.integerValidator(request, ParameterNames.TIPO_OFERTA, error));
			oferta.setNombreOferta(ValidationUtils.nameValidator(request, ParameterNames.NOMBRE_OFERTA, error));
			oferta.setIdEmpresa(empresa.getId());
			ValidationUtils.onlyFieldEquals(request, error, oferta.getIdTipoOferta(),  oferta.getNumerador(), oferta.getDenominador());
			ValidationUtils.onlyOneField(request, ParameterNames.DESCT_FIJO, ParameterNames.DESCT_PCN, error);
			String fechaDesde = new StringBuilder().append(fechaVigencia)
					.append(" ").append(horaVigencia).append(":00").toString();
			String fechaHasta = new StringBuilder().append(fechaCaducidad)
					.append(" ").append(horaCaducidad).append(":00").toString();

			try {
				oferta.setFechaDesde(ValidationUtils.dateValidation(request,fechaDesde, fechaVigencia, ParameterNames.FECHA_VIG,error));
				oferta.setFechaHasta(ValidationUtils.dateValidation(request,fechaHasta, fechaCaducidad, ParameterNames.FECHA_CAD, error));
				ValidationUtils.dateOrderValidation(oferta.getFechaDesde(), oferta.getFechaHasta(), error);
				if(!error.hasErrors()) {
					ofertaService.create(oferta);
					redirect = true;
					target = UrlBuilder.getUrlForController(request,ControllerPath.OFERTA ,ActionNames.BUSCAR, redirect);
				}
				else {
					request.setAttribute(AttributesNames.ERROR, error);
					target = UrlBuilder.getUrlForController(request,ControllerPath.PRECREATE, ActionNames.OFERTA, redirect);
				}
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
