package com.abastos.market.web.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.dao.Results;
import com.abastos.market.web.configuration.ConfConstants;
import com.abastos.market.web.configuration.ConfigurationManager;
import com.abastos.market.web.model.Pagination;
import com.abastos.market.web.util.ActionNames;
import com.abastos.market.web.util.AttributesNames;
import com.abastos.market.web.util.ControllerPath;
import com.abastos.market.web.util.ErrorNames;
import com.abastos.market.web.util.FilesUtils;
import com.abastos.market.web.util.MapBuilder;
import com.abastos.market.web.util.ParameterNames;
import com.abastos.market.web.util.ParameterUtils;
import com.abastos.market.web.util.SessionManager;
import com.abastos.market.web.util.UrlBuilder;
import com.abastos.market.web.util.ViewPaths;
import com.abastos.market.web.util.WebConstants;
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
	private static ConfigurationManager cfg = ConfigurationManager.getInstance();

	//numero de resultados por pagina
	private static int pageSize = Integer.valueOf(cfg.getParameter(ConfConstants.pageSize));
	//numero de paginas que se muestran alrededor de la navegable
	private static int pagingPageCount = Integer.valueOf(cfg.getParameter(ConfConstants.pagingPageCount));
	//primera pagina
	private static int firstPage = Integer.valueOf(cfg.getParameter(ConfConstants.firstPage));
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
		Empresa empresa = (Empresa)SessionManager.get(request, AttributesNames.EMPRESA);
		//se recupera el idioma si esta en sesión si no se pone como defecto "es"
		String idioma = (String)SessionManager.get(request, AttributesNames.IDIOMA);
		if(idioma == null) {
			idioma = "es";
		}
		if(action == null) {
			action = request.getContentType().split(";")[0];
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
			String nombre = request.getParameter(ParameterNames.NOMBRE_PRODUCTO);
			String reset = request.getParameter(ActionNames.RE_INICIO);
			Tienda tienda = null;
			String tiend = null;
			ProductoCriteria productoCri = new ProductoCriteria();
			//si el request no procede de una llamada ajax se procede a recuperar la tienda de sesión.
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


			if(origen != null && !"D".equals(origen)) {
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

				//si el request procede una llamada ajax, se procede a buscar todos los productos de una tienda para insertarlo en un select de productos
				if(ajax != null) {

					List<Producto> listProducts = productoServ.findByIdTienda(productoCri.getIdTienda(),idioma);

					Gson gson = new Gson();
					response.setContentType(WebConstants.CONTENT_TYPE);
					response.getOutputStream().write(gson.toJson(listProducts).getBytes());
				}
				else {
					//busqueda de productos en funcion de productoCriteria
					// Pagina solicitada por el usuario (o por defecto la primera
					// cuando todavia no ha usado el paginador)
					int page = ParameterUtils.getPageNumber(request.getParameter(ParameterNames.PAGE), 1);
					logger.info("pagina " + page);
					Results<Producto> 	results = productoServ.findBy(productoCri, idioma, (page-1)*pageSize+1, pageSize);
					Map<Long, Producto> ofertPro= productoServ.findByProductOfert(idioma);
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
					//si estamos dentro de la sección de productos de una tienda se recupera las categorias que pertenecen a la categoria de la tienda
					if(tienda != null) {
						List<Categoria> categorias = categoriaService.findByIdPadre(Integer.valueOf(tienda.getCategoria()),idioma);
						request.setAttribute(AttributesNames.CATEGORIAS, categorias);
					}
					//para recuperar todas las tiendas de una empresa
					if(empresa != null) {
						//accion para borrar de la sesión la tienda que esta guardada en ese momento y así proceder a mostrar todos los productos de una empresa
						//con la tienda a la que pertenecen
						if(reset != null) {
							SessionManager.remove(request, AttributesNames.TIENDA);
						}
						List<Tienda> tiendaResults = tiendaServ.findByIdEmpresa(empresa.getId());
						List<Categoria> categorias = categoriaService.findRoot(idioma);
						//mapa para asociar el producto con la tienda a la que pertenecen
						Map<Long, String> result  = MapBuilder.builderMapTienProdc(results.getPage(), tiendaResults);
						request.setAttribute(AttributesNames.TIENDA_PRODUCTOS, result);
						request.setAttribute(AttributesNames.CATEGORIAS, categorias);
					}
					request.setAttribute(AttributesNames.PRODUCTO, results);
					request.setAttribute(AttributesNames.PRODUCTO_OFERTA, ofertPro);
					target = ViewPaths.PRODUCTO_RESULTS;
				}

			}
			catch (DataException e) {
				logger.warn(e.getMessage(),e);
				error.add(ParameterNames.ERROR, ErrorNames.ERR_GENERIC_SEARCH_PRODUCT);
				request.setAttribute(AttributesNames.ERROR, error);
				target = UrlBuilder.getUrlForController(request, ControllerPath.TIENDA, ActionNames.DETALLE, redirect);

			}
		}



		//Se muestra la vista detalle de un producto
		else if(ActionNames.DETALLE.equalsIgnoreCase(action)) {
			String idProducto = request.getParameter(ParameterNames.ID_PRODUCTO);
			String idTienda = request.getParameter(ParameterNames.ID_TIENDA);
			Producto proOfert = null;
			Long id = Long.valueOf(idProducto);
			Tienda tienda = null;
			List<Categoria> categorias = null;
			try {
				Producto result = productoServ.findById(id, idioma);

				if(result.getOferta() != null) {
					if(result.getOferta().getIdProdOferta() != 0L) {
						proOfert = productoServ.findById(result.getOferta().getIdProdOferta(), idioma);
					}
				}

				if(particular != null) {
					List<Lista> listas = listaService.findByIdParticular(particular.getId());
					//se comprueba que el paremetro idTienda no sea null, en caso de que el request 
					//proceda de la seccion lineaListas o de lineaPedido del historial de pedido
					if(idTienda != null) {
						tienda = tiendaServ.findById(Long.valueOf(idTienda));
						categorias = categoriaService.findByIdPadre(Integer.valueOf(tienda.getCategoria()),idioma);
						request.setAttribute(AttributesNames.CATEGORIAS, categorias);
						request.setAttribute(AttributesNames.TIENDA, tienda);
					}
					//si el parametro idTienda es null entonces se recupera la tienda de sesesión
					//y las categorias que son hijas de la categoria a la que pertenece la tienda
					else {
						tienda = (Tienda)SessionManager.get(request, AttributesNames.TIENDA);
						categorias = 	categoriaService.findByIdPadre(tienda.getCategoria(),idioma);
						request.setAttribute(AttributesNames.CATEGORIAS, categorias);
					}
					request.setAttribute(AttributesNames.LISTA, listas);
				}
				// en el resto de casos si la tienda esta en sesión la categoria que se recupera es la de la propia tienda
				//en caso contrario se recuperan todas las categorias
				else {
					tienda = (Tienda)SessionManager.get(request, AttributesNames.TIENDA);
					if(tienda != null) {
						categorias  =categoriaService.findByIdPadre(tienda.getCategoria(),idioma);
						request.setAttribute(AttributesNames.CATEGORIAS, categorias);
					}
					else {
						categorias = categoriaService.findRoot(idioma);
						request.setAttribute(AttributesNames.CATEGORIAS, categorias);
					}
				}


				request.setAttribute(AttributesNames.PRODUCTO, result);
				request.setAttribute(AttributesNames.PRODUCTO_OFERTA, proOfert);

				target = ViewPaths.PRODUCTO_DETALLE;

			} catch (DataException e) {
				logger.warn(e.getMessage(),e);	
				error.add(ParameterNames.ERROR, ErrorNames.ERR_GENERIC_DETAIL_PRODUCT);
				request.setAttribute(AttributesNames.ERROR, error);
				target = UrlBuilder.getUrlForController(request, ControllerPath.PRODUCTO, ActionNames.BUSCAR, redirect);

			}

		}
		//Se crea producto, se redirige al sevlet producto para recuperar los resultados de productos
		else if(WebConstants.MULTIPART.equals(action)||ServletFileUpload.isMultipartContent(request)) {

			logger.info("creando producto...");

			Map<String, FileItem> mapParam;
			try {
				mapParam = FilesUtils.mapParam(request);
				String URL = UrlBuilder.urlRealPath(request, WebConstants.UPLOAD_DIRECTORY);
				String nombreCast = mapParam.get(ParameterNames.NOMBRE_CASTELLANO).getString();
				String nombreIngles= mapParam.get(ParameterNames.NOMBRE_INGLES).getString();
				String caractCastellano= mapParam.get(ParameterNames.CARACT_CASTELLANO).getString();
				String caractIngles = mapParam.get(ParameterNames.CARACT_INGLES).getString();
				String precio = mapParam.get(ParameterNames.PRECIO).getString();
				String tienda = mapParam.get(ParameterNames.ID_TIENDA).getString();
				String oferta = null;
				if(mapParam.get(ParameterNames.OFERTA) != null) {
					oferta = 	mapParam.get(ParameterNames.OFERTA).getString();
				}
				String categoria = mapParam.get(ParameterNames.CATEGORIA).getString();
				String origen = mapParam.get(ParameterNames.ORIGEN).getString();
				String stock = mapParam.get(ParameterNames.STOCK).getString();
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

					if(oferta != null && !"0".equals(oferta)) {
						Oferta ofert;
						ofert = ofertServ.findById(Long.valueOf(oferta));
						if(ofert == null) {
							ofert = new Oferta();
							ofert.setId(Long.valueOf(oferta));
							producto.setOferta(ofert);	
						}

					}

					producto.setPrecio(Double.valueOf(precio.replace(',', '.')));
					producto.setStock(Integer.valueOf(stock));
					producto.setTipoOrigen(origen.charAt(0));	
					logger.info(producto.getPrecio());
					productoServ.create(producto);	

					if(mapParam.get(ParameterNames.IMAGEN_PRINCIPAL) != null) {
						FilesUtils.writerImg(producto.getId(), ParameterNames.IMAGEN_PRINCIPAL,
								URL, mapParam.get(ParameterNames.IMAGEN_PRINCIPAL));
					}
					if(mapParam.get(ParameterNames.IMAGEN_GALERIA) != null) {
						FilesUtils.writerImg(producto.getId(), ParameterNames.IMAGEN_GALERIA,
								URL, mapParam.get(ParameterNames.IMAGEN_GALERIA));
					}
					if(mapParam.get(ParameterNames.IMAGEN_GALEIRA_SCD) != null) {
						FilesUtils.writerImg(producto.getId(), ParameterNames.IMAGEN_GALEIRA_SCD,
								URL, mapParam.get(ParameterNames.IMAGEN_GALEIRA_SCD));
					}

					redirect = true;
					target = UrlBuilder.getUrlForController(request,ControllerPath.PRODUCTO ,ActionNames.BUSCAR, redirect);

				}	catch(LimitCreationException e) {
					error.add(ActionNames.CREAR, ErrorNames.ERR_LIMIT_CREATION_PRODUCTS);
					request.setAttribute(AttributesNames.ERROR, error);
					target = UrlBuilder.getUrlForController(request, ControllerPath.PRECREATE, ActionNames.PRODUCTO, redirect);
					logger.warn(e.getMessage(),e);
				}catch (DataException e) {
					logger.warn(e.getMessage(),e);
					error.add(ParameterNames.ERROR, ErrorNames.ERR_GENERIC_CREATE_PRODUCT);
					request.setAttribute(AttributesNames.ERROR, error);
					target = UrlBuilder.getUrlForController(request, ControllerPath.PRECREATE, ActionNames.PRODUCTO, redirect);


				} catch (FileUploadException e1) {
					logger.warn(e1.getMessage(),e1);
					error.add(ParameterNames.ERROR, ErrorNames.ERR_GENERIC_UPLOAD_IMAGE);
					request.setAttribute(AttributesNames.ERROR, error);
					target = UrlBuilder.getUrlForController(request, ControllerPath.PRECREATE, ActionNames.PRODUCTO, redirect);
				}
			} catch (Exception e) {
				logger.warn(e.getMessage(),e);
				error.add(ParameterNames.ERROR, ErrorNames.ERR_GENERIC_CREATE_PRODUCT);
				request.setAttribute(AttributesNames.ERROR, error);
				target = UrlBuilder.getUrlForController(request, ControllerPath.PRECREATE, ActionNames.PRODUCTO, redirect);
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
