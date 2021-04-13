package com.abastos.market.web.controllers;



import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.dao.Results;
import com.abastos.market.web.model.Pagination;
import com.abastos.market.web.util.ActionNames;
import com.abastos.market.web.util.AttributesNames;
import com.abastos.market.web.util.ControllerPath;
import com.abastos.market.web.util.ErrorNames;
import com.abastos.market.web.util.ParameterNames;
import com.abastos.market.web.util.ParameterUtils;
import com.abastos.market.web.util.SessionManager;
import com.abastos.market.web.util.UrlBuilder;
import com.abastos.market.web.util.ViewPaths;
import com.abastos.model.Categoria;
import com.abastos.model.DireccionDto;
import com.abastos.model.Empresa;
import com.abastos.model.Localidad;
import com.abastos.model.Producto;
import com.abastos.model.Tienda;
import com.abastos.service.CategoriaService;
import com.abastos.service.DataException;
import com.abastos.service.LocalidadService;
import com.abastos.service.MailService;
import com.abastos.service.ProductoCriteria;
import com.abastos.service.ProductoService;
import com.abastos.service.TiendaCriteria;
import com.abastos.service.TiendaService;
import com.abastos.service.exceptions.LimitCreationException;
import com.abastos.service.exceptions.MailException;
import com.abastos.service.impl.CategoriaServiceImpl;
import com.abastos.service.impl.LocalidadServiceImpl;
import com.abastos.service.impl.MailServiceImpl;
import com.abastos.service.impl.ProductoServiceImpl;
import com.abastos.service.impl.TiendaServiceImpl;
import com.google.gson.Gson;



public class TiendaServlet extends HttpServlet {
	private static Logger logger = LogManager.getLogger(TiendaServlet.class);
	//numero de resultados por pagina
	private static int pageSize = 5;
	//numero de paginas que se muestran alrededor de la navegable
	private static int pagingPageCount = 3;
	//primera pagina
	private static int firstPage = 1;
	private TiendaService tiendaService;
	private ProductoService productoService;
	private CategoriaService categoriaService;
	private LocalidadService localidadService;
	private Map<String, Object> infoEmail = null;
	private MailService mailService = null;
	public TiendaServlet() {
		tiendaService = new TiendaServiceImpl();
		productoService = new ProductoServiceImpl();
		categoriaService = new CategoriaServiceImpl();
		localidadService = new LocalidadServiceImpl();
		infoEmail = new HashMap<String, Object>();
		mailService = new MailServiceImpl();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		if(logger.isDebugEnabled()) {
			logger.debug(ParameterUtils.print(request.getParameterMap()));
		}
		String action = request.getParameter(ActionNames.ACTION);
		String ajax = request.getParameter(ParameterNames.AJAX);
		String idioma = (String)SessionManager.get(request, AttributesNames.IDIOMA);
		Errors error = new Errors();
		Pagination pagination = new Pagination();
		Localidad local = null;
		if(idioma == null) {
			idioma = "es";
		}

		String target = null;
		boolean redirect = false;
		if(ActionNames.BUSCAR.equalsIgnoreCase(action)) {
			String envioDomicilio = request.getParameter(ParameterNames.ENVIO_DOMICILIO);
			String localidad = request.getParameter(ParameterNames.LOCALIDAD);
			String categoria = request.getParameter(ParameterNames.CATEGORIA);
			String nombre = request.getParameter(ParameterNames.NOMBRE_TIENDA);
			SessionManager.remove(request, AttributesNames.TIENDA);
			Empresa empresa = (Empresa)SessionManager.get(request, AttributesNames.EMPRESA);
			try {
				if(localidad!=null) {
					local = localidadService.findByIdLocalidad(Long.valueOf(localidad));
					SessionManager.set(request, AttributesNames.LOCALIDAD, local);
				}

				local = (Localidad)SessionManager.get(request, AttributesNames.LOCALIDAD);

			} catch ( DataException e1) {
				logger.warn(e1.getMessage(),e1);
				error.add(ParameterNames.ERROR, ErrorNames.ERR_GENERIC);
				request.setAttribute(AttributesNames.ERROR, error);
				target = UrlBuilder.getUrlForController(request, ControllerPath.PRECREATE, ActionNames.INICIO, redirect);

			}

			TiendaCriteria tienda = new TiendaCriteria();

			if(local != null) {
				tienda.setIdLocalidad(local.getId());
			}

			if(categoria != null ) {
				tienda.setCategoria(Integer.valueOf(categoria));
			}
			if(envioDomicilio != null) {
				tienda.setEnvioDomicilio(Boolean.valueOf(envioDomicilio));
			}
			if(nombre != null) {
				tienda.setNombre(nombre);
			}
			if(empresa != null) {
				tienda.setIdEmpresa(empresa.getId());
			}
			try {
				if(ajax !=null) {

					List<Tienda> results = tiendaService.findByIdEmpresa(empresa.getId());
					Gson gson = new Gson();
					response.setContentType("application/json; charset=ISO-8859-1");
					response.getOutputStream().write(gson.toJson(results).getBytes());
				}
				else {

					// Pagina solicitada por el usuario (o por defecto la primera
					// cuando todavia no ha usado el paginador)
					int page = ParameterUtils.getPageNumber(request.getParameter(ParameterNames.PAGE), 1);
					logger.info("pagina " + page);

					Results<Tienda> results = tiendaService.findByCriteria(tienda, (page-1)*pageSize+1, pageSize);
					// Datos para paginacion															
					// (Calculos aqui, datos comodos para renderizar)
					int totalPages = (int) Math.ceil((double)results.getTotal()/(double)pageSize);
					int firstPagedPage = Math.max(1, page-pagingPageCount);
					int lastPagedPage = Math.min(totalPages, page+pagingPageCount);
					pagination.setFirstPage(firstPage);
					pagination.setFirstPagedPage(firstPagedPage);
					pagination.setLastPagedPage(lastPagedPage);
					pagination.setPage(page);
					pagination.setTotalPages(totalPages);
					request.setAttribute(ParameterNames.PAGE, pagination);


					List<Categoria> categorias = categoriaService.findRoot(idioma);
					request.setAttribute(AttributesNames.LOCALIDAD, localidad);
					request.setAttribute(AttributesNames.RESULTS_TIENDA, results);
					request.setAttribute(AttributesNames.CATEGORIAS, categorias);
					target = ViewPaths.TIENDA_RESULTS;

				}

			} catch (DataException e) {
				error.add(ParameterNames.ERROR, ErrorNames.ERR_GENERIC_SEARCH_TIENDA);
				request.setAttribute(AttributesNames.ERROR, error);
				target = UrlBuilder.getUrlForController(request, ControllerPath.PRECREATE, ActionNames.INICIO, redirect);
				logger.warn(e.getMessage(),e);
			}


		}
		else if(ActionNames.DETALLE.equalsIgnoreCase(action)) {
			String idTienda = request.getParameter(ParameterNames.ID_TIENDA);
			Long id = Long.valueOf(idTienda);
			ProductoCriteria productoCrit = new ProductoCriteria();
			productoCrit.setIdTienda(id);
			Tienda tienda = new Tienda();
			try {
				// Pagina solicitada por el usuario (o por defecto la primera
				// cuando todavia no ha usado el paginador)
				int page = ParameterUtils.getPageNumber(request.getParameter(ParameterNames.PAGE), 1);
				logger.info("pagina " + page);
				Results<Producto> results = productoService.findBy(productoCrit, idioma, (page-1)*pageSize+1, pageSize);
				// Datos para paginacion															
				// (Calculos aqui, datos comodos para renderizar)
				int totalPages = (int) Math.ceil((double)results.getTotal()/(double)pageSize);
				int firstPagedPage = Math.max(1, page-pagingPageCount);
				int lastPagedPage = Math.min(totalPages, page+pagingPageCount);
				pagination.setFirstPage(firstPage);
				pagination.setFirstPagedPage(firstPagedPage);
				pagination.setLastPagedPage(lastPagedPage);
				pagination.setPage(page);
				pagination.setTotalPages(totalPages);
				request.setAttribute(ParameterNames.PAGE, pagination);
				tienda = tiendaService.findById(id);
				List<Categoria> categorias = categoriaService.findByIdPadre(tienda.getCategoria(),idioma);
				request.setAttribute(AttributesNames.CATEGORIAS, categorias);
				request.setAttribute(AttributesNames.PRODUCTO, results);

				SessionManager.set(request, AttributesNames.TIENDA, tienda);
				target = ViewPaths.PRODUCTO_RESULTS;

			} catch (DataException e) {
				error.add(ParameterNames.ERROR, ErrorNames.ERR_GENERIC_DETAIL_TIENDA);
				request.setAttribute(AttributesNames.ERROR, error);
				target = UrlBuilder.getUrlForController(request, ControllerPath.TIENDA, ActionNames.BUSCAR, redirect);
				logger.info(target);
			}


		}
		else if(ActionNames.CREAR.equalsIgnoreCase(action)) {
			Empresa empresa = (Empresa)SessionManager.get(request, AttributesNames.EMPRESA);
			String nombre = request.getParameter(ParameterNames.NOMBRE_TIENDA);
			String movil = request.getParameter(ParameterNames.MOVIL);
			String telefono = request.getParameter(ParameterNames.TELEFONO);
			String email = request.getParameter(ParameterNames.EMAIL);
			String envioDomicilio = request.getParameter(ParameterNames.ENVIO_DOMICILIO);
			String calle = request.getParameter(ParameterNames.CALLE);
			String piso = request.getParameter(ParameterNames.PISO);
			String numero = request.getParameter(ParameterNames.NUMERO);
			String codigoPost = request.getParameter(ParameterNames.CODIGO_POSTAL);
			String pais = request.getParameter(ParameterNames.PAIS);
			String comunidad = request.getParameter(ParameterNames.COMUNIDAD);
			String provincia = request.getParameter(ParameterNames.PROVINCIA);
			String localidad = request.getParameter(ParameterNames.LOCALIDAD);
			String categoria = request.getParameter(ParameterNames.CATEGORIA);
			DireccionDto direccion = new DireccionDto();
			direccion.setCalle(calle);
			direccion.setCodigoPostal(codigoPost);
			direccion.setIdComunidadAutonoma(Long.valueOf(comunidad));
			direccion.setPiso(piso);
			direccion.setNumero(Integer.valueOf(numero));
			direccion.setIdPais(Long.valueOf(pais));
			direccion.setIdProvincia(Long.valueOf(provincia));
			direccion.setIdLocalidad(Long.valueOf(localidad));
			direccion.setIdTipoDireccion(3);
			Tienda tienda = new Tienda();
			tienda.setCategoria(Integer.valueOf(categoria));
			tienda.setDireccionDto(direccion);
			tienda.setEmail(email);
			tienda.setEnvioDomicilio(Boolean.valueOf(envioDomicilio));
			tienda.setNombre(nombre);
			tienda.setNumeroMovil(movil);
			tienda.setNumeroTelefono(telefono);
			tienda.setIdEmpresa(empresa.getId());
			try {
				tiendaService.create(tienda);
				infoEmail.put("tienda", tienda);
				mailService.sendMailHtml(infoEmail,6L,tienda.getEmail());	

				redirect = true;
				target = UrlBuilder.getUrlForController(request,ControllerPath.TIENDA ,ActionNames.BUSCAR, redirect); 

			} catch (LimitCreationException e) {
				error.add(ActionNames.CREAR, ErrorNames.ERR_LIMIT_CREATION_SHOP);
				request.setAttribute(AttributesNames.ERROR, error);
				logger.warn(e.getMessage(),e);
			} catch(MailException e) {
				error.add(ActionNames.SEND_EMAIL, ErrorNames.ERR_SEND_EMAIL);
				request.setAttribute(AttributesNames.ERROR, error);
				logger.warn(e.getMessage(),e);

			}

			catch(DataException e) {
				error.add(ParameterNames.ERROR, ErrorNames.ERR_GENERIC_CREATION_TIENDA);
				request.setAttribute(AttributesNames.ERROR, error);
				logger.warn(e.getMessage(),e);

			}
			if(error.hasErrors()) {
				target = UrlBuilder.getUrlForController(request, ControllerPath.PRECREATE, ActionNames.EMPRESA, redirect);
			}

		}
		if(target == null) {
			target = UrlBuilder.getUrlForController(request, ControllerPath.PRECREATE, ActionNames.INICIO, redirect);
		}
		if(ajax == null) {
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
