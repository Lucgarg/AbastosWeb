package com.abastos.market.web.controllers;

import java.io.IOException;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.market.web.util.ActionNames;
import com.abastos.market.web.util.AttributesNames;
import com.abastos.market.web.util.ControllerPath;
import com.abastos.market.web.util.CookieManager;
import com.abastos.market.web.util.ErrorNames;
import com.abastos.market.web.util.ParameterNames;
import com.abastos.market.web.util.ParameterUtils;
import com.abastos.market.web.util.SessionManager;
import com.abastos.market.web.util.UrlBuilder;
import com.abastos.market.web.util.ViewPaths;
import com.abastos.market.web.util.WebConstants;
import com.abastos.model.Categoria;
import com.abastos.model.Empresa;
import com.abastos.model.Localidad;
import com.abastos.model.Oferta;
import com.abastos.model.Pais;
import com.abastos.model.Tienda;
import com.abastos.model.TipoOferta;
import com.abastos.service.CategoriaService;
import com.abastos.service.DataException;
import com.abastos.service.LocalidadService;
import com.abastos.service.OfertaService;
import com.abastos.service.PaisService;
import com.abastos.service.TiendaService;
import com.abastos.service.TipoOfertaService;
import com.abastos.service.impl.CategoriaServiceImpl;
import com.abastos.service.impl.LocalidadServiceImpl;
import com.abastos.service.impl.OfertaServiceImpl;
import com.abastos.service.impl.PaisServiceImpl;
import com.abastos.service.impl.TiendaServiceImpl;
import com.abastos.service.impl.TipoOfertaServiceImpl;
import com.google.gson.Gson;



public class PrecreateServlet extends HttpServlet {
	private static Logger logger = LogManager.getLogger(PrecreateServlet.class);
	private CategoriaService categoriaService = null;
	private PaisService paisService = null;
	private TipoOfertaService tipOfert = null;
	private TiendaService tiendaService = null;
	private OfertaService ofertaService = null;
	private LocalidadService localService = null;
	public PrecreateServlet() {
		localService = new LocalidadServiceImpl();
		categoriaService = new CategoriaServiceImpl();
		paisService = new PaisServiceImpl();
		tipOfert = new TipoOfertaServiceImpl();
		tiendaService = new TiendaServiceImpl();
		ofertaService = new OfertaServiceImpl();

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter(ActionNames.ACTION);
		if(action == null)
		{ action = ActionNames.INICIO;
		request.setAttribute(AttributesNames.ACTION, action);
		}
		String idioma = (String)SessionManager.get(request, AttributesNames.IDIOMA);
		if(idioma == null) {
			idioma = "es";
		}
		Errors error = new Errors();
		String target = null;
		boolean redirect = false;
		String ajax = request.getParameter(ParameterNames.AJAX);
		if(logger.isDebugEnabled()) {
			logger.debug(ParameterUtils.print(request.getParameterMap()));
		}
		if(ActionNames.EMPRESA.equalsIgnoreCase(action)) {
			String idEmpresa = request.getParameter(ParameterNames.ID_EMPRESA);
			try {


				List<Categoria> categorias = categoriaService.findRoot("es");
				List<Pais> paises = paisService.findByAll();
				request.setAttribute(AttributesNames.EMPRESA, idEmpresa);
				request.setAttribute(AttributesNames.CATEGORIAS, categorias);
				request.setAttribute(AttributesNames.PAISES, paises);
				target = ViewPaths.EMPRESA_CREATE_TIENDA;
			} catch (DataException e) {
				error.add(ParameterNames.ERROR, ErrorNames.ERR_GENERIC);
				request.setAttribute(AttributesNames.ERROR, error);
				target = UrlBuilder.getUrlForController(request, ControllerPath.TIENDA, ActionNames.BUSCAR, redirect);
				logger.warn(e.getMessage(),e);
			}
		}else if(ActionNames.OFERTA.equalsIgnoreCase(action)){

			try {
				List<TipoOferta> tipoOferta = tipOfert.findAll();
				request.setAttribute(AttributesNames.TIPO, tipoOferta);

				target = ViewPaths.OFERTA_CREATE;

			} catch ( DataException e) {
				logger.warn(e.getMessage(),e);
				error.add(ParameterNames.ERROR, ErrorNames.ERR_GENERIC_ACCESS_CRE_OFERT);
				request.setAttribute(AttributesNames.ERROR, error);
				target = UrlBuilder.getUrlForController(request, ControllerPath.OFERTA, ActionNames.BUSCAR, redirect);

			}
		}
		else if(ActionNames.PRODUCTO.equalsIgnoreCase(action)) {

			Empresa empresa = (Empresa)SessionManager.get(request, AttributesNames.EMPRESA);
			try {
				List<Tienda> listTienda = tiendaService.findByIdEmpresa(empresa.getId());
				List<Oferta> listOferta = ofertaService.findByIdEmpresa(empresa.getId());
				List<Categoria> categorias=	categoriaService.findRoot("es");
				request.setAttribute(AttributesNames.RESULTS_TIENDA, listTienda);
				request.setAttribute(AttributesNames.OFERTAS, listOferta);
				request.setAttribute(AttributesNames.CATEGORIAS, categorias);
				target = ViewPaths.PRODUCTO_CREATE;
			} catch (DataException e) {
				logger.warn(e.getMessage(),e);
				error.add(ParameterNames.ERROR, ErrorNames.ERR_GENERIC);
				request.setAttribute(AttributesNames.ERROR, error);
				target = UrlBuilder.getUrlForController(request, ControllerPath.PRODUCTO_PRIVATE, ActionNames.BUSCAR, redirect);

			}
		}
		//se recuperan la lista de paises para la direccion en el registro de usuarios
		else if(ActionNames.REGISTRO.equalsIgnoreCase(action)) {
			String tipUser = request.getParameter(ParameterNames.TIP_USUARIO);
			try {
				List<Pais> paises = paisService.findByAll();
				request.setAttribute(AttributesNames.PAISES, paises);
				//dependiendo del tipo de usuario se hace forward a la página correspondiente
				if(ActionNames.EMPRESA.equalsIgnoreCase(tipUser)) {
					target = ViewPaths.EMPRESA_REGISTRO;
				}
				else if(ActionNames.PARTICULAR.equalsIgnoreCase(tipUser)){
					target = ViewPaths.PARTICULAR_REGISTRO;
				}
			} catch (DataException e) {
				logger.warn(e.getMessage(),e);
				error.add(ParameterNames.ERROR, ErrorNames.ERR_GENERIC);
				request.setAttribute(AttributesNames.ERROR, error);
				target = ViewPaths.TIENDA_BUSQUEDA;

			}
		}
		else if(ActionNames.INICIO.equalsIgnoreCase(action)) {
			List<Pais> paises;
			String reset = request.getParameter(ActionNames.RE_INICIO);
			try {
				//cookies para recordar localidad
				Cookie cookieLocal = CookieManager.getCookie(request, ParameterNames.LOCALIDAD);
				if(cookieLocal != null && !ParameterNames.TRUE.equals(reset)) {
					String result = cookieLocal.getValue();
					String []cookValue = result.split(":");
					
					if(request.getHeader(WebConstants.HEADER_AGENT).equals(UrlBuilder.decode(cookValue[1]))) {

						Localidad local = localService.findByIdLocalidad(Long.valueOf(cookValue[0]));
						SessionManager.set(request, ParameterNames.LOCALIDAD, local);
						target = UrlBuilder.getUrlForController(request, ControllerPath.TIENDA, ActionNames.BUSCAR, true);
						redirect = true;
					}
					else {
						paises = paisService.findByAll();
						request.setAttribute(AttributesNames.PAISES, paises);
						target = ViewPaths.TIENDA_BUSQUEDA;
					}
				}else {
				paises = paisService.findByAll();
				request.setAttribute(AttributesNames.PAISES, paises);
				target = ViewPaths.TIENDA_BUSQUEDA;
				}
				
			}catch (DataException e) {
				logger.warn(e.getMessage(),e);
				error.add(ParameterNames.ERROR, ErrorNames.ERR_GENERIC);
				request.setAttribute(AttributesNames.ERROR, error);
				target = ViewPaths.TIENDA_BUSQUEDA;

			}
		}
	
		else if(ActionNames.CATEGORIA.equalsIgnoreCase(action)) {
			String idTienda = request.getParameter(ParameterNames.ID_TIENDA);
			String idCategoria  = request.getParameter(ParameterNames.CATEGORIA);
			Integer categoria = null; 
			try {
				if(idTienda != null) {
					categoria = tiendaService.findById(Long.valueOf(idTienda)).getCategoria();
				}
				else {
					categoria = Integer.valueOf(idCategoria);
				}
				List<Categoria> listCategorias = categoriaService.findByIdPadre(categoria, idioma);

				Gson gson = new Gson();
				response.setContentType(WebConstants.CONTENT_TYPE);
				response.getOutputStream().write(gson.toJson(listCategorias).getBytes());
			} catch (DataException e) {
				logger.warn(e.getMessage(),e);
				error.add(ParameterNames.ERROR, ErrorNames.ERR_GENERIC);
				request.setAttribute(AttributesNames.ERROR, error);
				target = UrlBuilder.getUrlForController(request, ControllerPath.PRECREATE, ActionNames.BUSCAR, redirect);

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
			}
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
