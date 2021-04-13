package com.abastos.market.web.controllers;

import java.io.IOException;
import java.lang.ModuleLayer.Controller;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
import com.abastos.market.web.util.MapBuilder;
import com.abastos.market.web.util.ParameterNames;
import com.abastos.market.web.util.ParameterUtils;
import com.abastos.market.web.util.SessionManager;
import com.abastos.market.web.util.UrlBuilder;
import com.abastos.market.web.util.ViewPaths;
import com.abastos.market.web.util.ViewPathsctions;
import com.abastos.model.Categoria;
import com.abastos.model.Empresa;
import com.abastos.model.Lista;
import com.abastos.model.Oferta;
import com.abastos.model.Particular;
import com.abastos.model.Producto;
import com.abastos.model.ProductoIdioma;
import com.abastos.model.Tienda;
import com.abastos.service.CategoriaService;
import com.abastos.service.DataException;
import com.abastos.service.ListaService;
import com.abastos.service.OfertaService;
import com.abastos.service.ProductoCriteria;
import com.abastos.service.ProductoService;
import com.abastos.service.TiendaService;
import com.abastos.service.exceptions.LimitCreationException;
import com.abastos.service.impl.CategoriaServiceImpl;
import com.abastos.service.impl.ListaServiceImpl;
import com.abastos.service.impl.OfertaServiceImpl;
import com.abastos.service.impl.ProductoServiceImpl;
import com.abastos.service.impl.TiendaServiceImpl;
import com.google.gson.Gson;





public class ProductoServlet extends HttpServlet {
	private static Logger logger = LogManager.getLogger(ProductoServlet.class);
	private CategoriaService categoriaService;
	private ProductoService productoServ = null;
	private TiendaService tiendaServ = null;
	private OfertaService ofertServ = null;
	private ListaService listaService = null;
	//numero de resultados por pagina
	private static int pageSize = 5;
	//numero de paginas que se muestran alrededor de la navegable
	private static int pagingPageCount = 3;
	//primera pagina
	private static int firstPage = 1;
	public ProductoServlet() {
		categoriaService = new CategoriaServiceImpl();
		productoServ = new ProductoServiceImpl();
		tiendaServ = new TiendaServiceImpl();
		ofertServ = new OfertaServiceImpl();
		listaService = new ListaServiceImpl();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(logger.isDebugEnabled()) {
			logger.debug(ParameterUtils.print(request.getParameterMap()));
		}
		String action = request.getParameter(ActionNames.ACTION);
		String ajax = request.getParameter(ParameterNames.AJAX);
		Errors error = new Errors();
		Pagination pagination = new Pagination();
		Particular particular = (Particular)SessionManager.get(request, AttributesNames.USUARIO);
		String idioma = (String)SessionManager.get(request, AttributesNames.IDIOMA);
		if(idioma == null) {
			idioma = "es";
		}
		
		String target = null;
		boolean redirect = false;
		//forward a resultado de productos
		if(ActionNames.BUSCAR.equalsIgnoreCase(action)){
			String precioDesde = request.getParameter(ParameterNames.PRECIO_DESDE);
			String precioHasta = request.getParameter(ParameterNames.PRECIO_HASTA);
			String origen = request.getParameter(ParameterNames.ORIGEN);
			String oferta = request.getParameter(ParameterNames.OFERTA);
			String categoria = request.getParameter(ParameterNames.CATEGORIA);
			Empresa empresa = (Empresa)SessionManager.get(request, AttributesNames.EMPRESA);
			String nombre = request.getParameter(ParameterNames.NOMBRE_PRODUCTO);
			Tienda tienda = null;
			String tiend = null;
			ProductoCriteria productoCri = new ProductoCriteria();
			if(ajax == null) {
				tienda = (Tienda)SessionManager.get(request, AttributesNames.TIENDA);
				if(empresa != null) {
					productoCri.setIdEmpresa(Long.valueOf(empresa.getId()));
				}
				if(tienda != null) {
					productoCri.setIdTienda(Long.valueOf(tienda.getId()));
				}
			}
			else {
				tiend = request.getParameter(ParameterNames.ID_TIENDA);
				if(tiend != null) {
					productoCri.setIdTienda(Long.valueOf(tiend));
				}
			}


			if(origen != null) {
				productoCri.setIdOrigen(origen.charAt(0));
			}
			if(oferta != null) {
				productoCri.setOferta(Boolean.valueOf(oferta));
			}
			if(precioDesde != "" & precioDesde != null) {

				productoCri.setPredioDesde(Double.valueOf(precioDesde));

			}
			if(precioHasta != "" & precioHasta != null ) {

				productoCri.setPrecioHasta(Double.valueOf(precioHasta));

			}
			if(categoria != null) {

				productoCri.setIdCategoria(Integer.valueOf(categoria));			
			}
			if(nombre != null) {
				productoCri.setNombre(nombre);
			}



			try {
			
				
				if(ajax != null) {
					List<Producto> listProducts = productoServ.findByIdTienda(productoCri.getIdTienda(),idioma);
				
					Gson gson = new Gson();
					response.setContentType("application/json; charset=ISO-8859-1");
					response.getOutputStream().write(gson.toJson(listProducts).getBytes());
				}
				else {
					//busqueda de productos en funcion de productoCriteria
					// Pagina solicitada por el usuario (o por defecto la primera
					// cuando todavia no ha usado el paginador)
					int page = ParameterUtils.getPageNumber(request.getParameter(ParameterNames.PAGE), 1);
					logger.info("pagina " + page);
					Results<Producto> 	results = productoServ.findBy(productoCri, idioma, (page-1)*pageSize+1, pageSize);
					
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
					if(tienda != null) {
						List<Categoria> categorias = categoriaService.findByIdPadre(Integer.valueOf(tienda.getCategoria()),"es");
						request.setAttribute(AttributesNames.CATEGORIAS, categorias);
					}
					if(empresa != null) {
						List<Tienda> tiendaResults = tiendaServ.findByIdEmpresa(empresa.getId());
						List<Categoria> categorias = categoriaService.findRoot(idioma);
						Map<Long, String> result  = MapBuilder.builderMapTienProdc(results.getPage(), tiendaResults);
						request.setAttribute(AttributesNames.TIENDA_PRODUCTOS, result);
						request.setAttribute(AttributesNames.CATEGORIAS, categorias);
					}
					request.setAttribute(AttributesNames.PRODUCTO, results);
					target = ViewPaths.PRODUCTO_RESULTS;
				}
				
			}
			catch (DataException e) {
				error.add(ParameterNames.ERROR, ErrorNames.ERR_GENERIC_SEARCH_PRODUCT);
				request.setAttribute(AttributesNames.ERROR, error);
				target = UrlBuilder.getUrlForController(request, ControllerPath.TIENDA, ActionNames.DETALLE, redirect);
				logger.warn(e.getMessage(),e);
			}
		}



		//Se muestra la vista detalle de un producto
		else if(ActionNames.DETALLE.equalsIgnoreCase(action)) {
			String idProducto = request.getParameter(ParameterNames.ID_PRODUCTO);
			Tienda tiend = (Tienda)SessionManager.get(request, AttributesNames.TIENDA);
			Long id = Long.valueOf(idProducto);
			Tienda tienda = new Tienda();
			try {
				Producto result = productoServ.findById(id, idioma);
				tienda = tiendaServ.findById(tiend.getId());
				if(particular != null) {
					List<Lista> listas = listaService.findByIdParticular(particular.getId());
					request.setAttribute(AttributesNames.LISTA, listas);
				}
				List<Categoria> categorias = categoriaService.findByIdPadre(tienda.getCategoria(), idioma);
				request.setAttribute(AttributesNames.CATEGORIAS, categorias);
				request.setAttribute(AttributesNames.PRODUCTO, result);
				request.setAttribute(AttributesNames.TIENDA, tienda);
				target = ViewPaths.PRODUCTO_DETALLE;
				throw new DataException();
			} catch (DataException e) {
				error.add(ParameterNames.ERROR, ErrorNames.ERR_GENERIC_DETAIL_PRODUCT);
				request.setAttribute(AttributesNames.ERROR, error);
				target = UrlBuilder.getUrlForController(request, ControllerPath.PRODUCTO, ActionNames.BUSCAR, redirect);
				logger.warn(e.getMessage(),e);	
			}

		}
		//Se crea producto, se redirige al sevlet producto para recuperar los resultados de productos
		else if(ActionNames.CREAR.equalsIgnoreCase(action)) {
			logger.info("creando producto...");
			String nombreCast = request.getParameter(ParameterNames.NOMBRE_CASTELLANO);
			String nombreIngles= request.getParameter(ParameterNames.NOMBRE_INGLES);
			String caractCastellano= request.getParameter(ParameterNames.CARACT_CASTELLANO);
			String caractIngles = request.getParameter(ParameterNames.CARACT_INGLES);
			String precio = request.getParameter(ParameterNames.PRECIO);
			String tienda = request.getParameter(ParameterNames.ID_TIENDA);
			String oferta = request.getParameter(ParameterNames.OFERTA);
			String categoria = request.getParameter(ParameterNames.CATEGORIA);
			String origen = request.getParameter(ParameterNames.ORIGEN);
			String stock = request.getParameter(ParameterNames.STOCK);
			try {
				ProductoIdioma productIdioma = new ProductoIdioma();
				productIdioma.setIdIdioma("es");
				productIdioma.setCaractProduct(caractCastellano);
				productIdioma.setNombreProducto(nombreCast);
				ProductoIdioma productIdiomaIng = new ProductoIdioma();
				productIdiomaIng.setIdIdioma("en");
				productIdiomaIng.setCaractProduct(caractIngles);
				productIdiomaIng.setNombreProducto(nombreIngles);
				Producto producto= new Producto();
				producto.add(productIdiomaIng);
				producto.add(productIdioma);
				producto.setNombre(nombreCast);
				producto.setIdCategoria(Integer.valueOf(categoria));
				producto.setIdTienda(Long.valueOf(tienda));
				Oferta ofert;
				ofert = ofertServ.findById(Long.valueOf(oferta));
				producto.setOferta(ofert);
				producto.setPrecio(Double.valueOf(precio));
				producto.setStock(Integer.valueOf(stock));
				producto.setTipoOrigen(origen.charAt(0));	
				logger.info(producto.getPrecio());
				productoServ.create(producto);		
				redirect = true;
				target = UrlBuilder.getUrlForController(request,ControllerPath.PRODUCTO ,ActionNames.BUSCAR, redirect);

			}	catch(LimitCreationException e) {
				error.add(ActionNames.CREAR, ErrorNames.ERR_LIMIT_CREATION_PRODUCTS);
				request.setAttribute(AttributesNames.ERROR, error);
				target = UrlBuilder.getUrlForController(request, ControllerPath.PRECREATE, ActionNames.PRODUCTO, redirect);
				logger.warn(e.getMessage(),e);
			}catch (DataException e) {
				error.add(ParameterNames.ERROR, ErrorNames.ERR_GENERIC);
				request.setAttribute(AttributesNames.ERROR, error);
				target = UrlBuilder.getUrlForController(request, ControllerPath.PRECREATE, ActionNames.PRODUCTO, redirect);
				logger.warn(e.getMessage(),e);
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
