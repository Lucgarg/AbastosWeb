package com.abastos.market.web.controllers;



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
import com.abastos.market.web.util.ControllerPath;
import com.abastos.market.web.util.ParameterNames;
import com.abastos.market.web.util.ParameterUtils;
import com.abastos.market.web.util.SessionManager;
import com.abastos.market.web.util.UrlBuilder;
import com.abastos.market.web.util.ViewPaths;
import com.abastos.market.web.util.ViewPathsctions;
import com.abastos.model.Categoria;
import com.abastos.model.DireccionDto;
import com.abastos.model.Empresa;
import com.abastos.model.Localidad;
import com.abastos.model.Producto;
import com.abastos.model.Tienda;
import com.abastos.service.CategoriaService;
import com.abastos.service.DataException;
import com.abastos.service.EmpresaService;
import com.abastos.service.LocalidadService;
import com.abastos.service.ProductoCriteria;
import com.abastos.service.ProductoService;
import com.abastos.service.TiendaCriteria;
import com.abastos.service.TiendaService;
import com.abastos.service.exceptions.ServiceException;
import com.abastos.service.impl.CategoriaServiceImpl;
import com.abastos.service.impl.EmpresaServiceImpl;
import com.abastos.service.impl.LocalidadServiceImpl;
import com.abastos.service.impl.ProductoServiceImpl;
import com.abastos.service.impl.TiendaServiceImpl;
import com.google.gson.Gson;

/**
 * Servlet implementation class TiendaServlet
 */
@WebServlet("/tienda")
public class TiendaServlet extends HttpServlet {
	private static Logger logger = LogManager.getLogger(TiendaServlet.class);

	private TiendaService tiendaService;
	private ProductoService productoService;
	private CategoriaService categoriaService;
	private EmpresaService empresaService;
	private LocalidadService localidadService;
	public TiendaServlet() {
		tiendaService = new TiendaServiceImpl();
		productoService = new ProductoServiceImpl();
		categoriaService = new CategoriaServiceImpl();
		empresaService = new EmpresaServiceImpl();
		localidadService = new LocalidadServiceImpl();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		if(logger.isDebugEnabled()) {
			logger.debug(ParameterUtils.print(request.getParameterMap()));
		}
		String action = request.getParameter(ActionNames.ACTION);
		String ajax = request.getParameter(ParameterNames.AJAX);
		String target = null;
		boolean redirect = false;
		if(ActionNames.BUSCAR.equalsIgnoreCase(action)) {
			String envioDomicilio = request.getParameter(ParameterNames.ENVIO_DOMICILIO);
			String localidad = request.getParameter(ParameterNames.LOCALIDAD);
			String categoria = request.getParameter(ParameterNames.CATEGORIA);
			String nombre = request.getParameter(ParameterNames.NOMBRE_TIENDA);
			Localidad local = new Localidad();
			Empresa empresa = (Empresa)SessionManager.get(request, AttributesNames.EMPRESA);
			try {
				if(localidad!=null) {
					local = localidadService.findByIdLocalidad(Long.valueOf(localidad));
					SessionManager.set(request, AttributesNames.LOCALIDAD, local);
				}

				local = (Localidad)SessionManager.get(request, AttributesNames.LOCALIDAD);

			} catch ( DataException e1) {
				logger.warn(e1.getMessage(),e1);
			}

			TiendaCriteria tienda = new TiendaCriteria();

			if(local != null) {
				tienda.setIdLocalidad(local.getId());
			}
			if(categoria != null) {
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
					
					List<Tienda> results = tiendaService.findByCriteria(tienda);
					Gson gson = new Gson();
					response.setContentType("application/json; charset=ISO-8859-1");
					response.getOutputStream().write(gson.toJson(results).getBytes());
				}
				else {
				List<Tienda> results = tiendaService.findByCriteria(tienda);
				List<Categoria> categorias = categoriaService.findRoot("es");
				request.setAttribute(AttributesNames.LOCALIDAD, localidad);
				request.setAttribute(AttributesNames.RESULTS_TIENDA, results);
				request.setAttribute(AttributesNames.CATEGORIAS, categorias);
				target = ViewPaths.TIENDA_RESULTS;
				}
				} catch (DataException e) {

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
				List<Producto> results = productoService.findBy(productoCrit, "es");
				tienda = tiendaService.findById(id);
				List<Categoria> categorias = categoriaService.findByIdPadre(tienda.getCategoria(),"es");
				request.setAttribute(AttributesNames.CATEGORIAS, categorias);
				request.setAttribute(AttributesNames.PRODUCTO, results);
				SessionManager.set(request, AttributesNames.TIENDA, tienda);
				target = ViewPaths.PRODUCTO_RESULTS;
			} catch (DataException e) {
				logger.warn(e.getMessage(),e);
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
				target = UrlBuilder.getUrlForController(request,ControllerPath.TIENDA ,ActionNames.BUSCAR); ;
				redirect = true;
			} catch (DataException | ServiceException e) {
				logger.warn(e.getMessage(),e);
			}

		}

		if(ajax == null) {
		if(redirect) { 
			logger.info("Redirect to..." + target);
			response.sendRedirect(UrlBuilder.getUrl(request, target));
		}
		else {
			logger.info("Forwarding to..." + target);
			request.getRequestDispatcher(target).forward(request, response);
		}
		}
	}

	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

	}

}
