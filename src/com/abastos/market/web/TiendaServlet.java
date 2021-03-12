package com.abastos.market.web;



import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.abastos.market.web.util.ActionNames;
import com.abastos.market.web.util.AttributesNames;
import com.abastos.market.web.util.ParameterNames;
import com.abastos.market.web.util.ViewPaths;
import com.abastos.model.Categoria;
import com.abastos.model.DireccionDto;
import com.abastos.model.Empresa;
import com.abastos.model.Producto;
import com.abastos.model.Tienda;
import com.abastos.service.CategoriaService;
import com.abastos.service.DataException;
import com.abastos.service.EmpresaService;
import com.abastos.service.ProductoCriteria;
import com.abastos.service.ProductoService;
import com.abastos.service.TiendaCriteria;
import com.abastos.service.TiendaService;
import com.abastos.service.exceptions.ServiceException;
import com.abastos.service.impl.CategoriaServiceImpl;
import com.abastos.service.impl.EmpresaServiceImpl;
import com.abastos.service.impl.ProductoServiceImpl;
import com.abastos.service.impl.TiendaServiceImpl;

/**
 * Servlet implementation class TiendaServlet
 */
@WebServlet("/tienda")
public class TiendaServlet extends HttpServlet {


	private TiendaService tiendaService;
	private ProductoService productoService;
	private CategoriaService categoriaService;
	private EmpresaService empresaService;
	public TiendaServlet() {
		tiendaService = new TiendaServiceImpl();
		productoService = new ProductoServiceImpl();
		categoriaService = new CategoriaServiceImpl();
		empresaService = new EmpresaServiceImpl();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		String action = request.getParameter(ActionNames.ACTION);
		if(ActionNames.BUSCAR.equalsIgnoreCase(action)) {
			String envioDomicilio = request.getParameter(ParameterNames.ENVIO_DOMICILIO);
			String localidad = request.getParameter(ParameterNames.LOCALIDAD);
			String categoria = request.getParameter(ParameterNames.CATEGORIA);
			String nombre = request.getParameter(ParameterNames.NOMBRE_TIENDA);
			TiendaCriteria tienda = new TiendaCriteria();
			if(localidad != null) {
				tienda.setIdLocalidad(Long.valueOf(localidad));
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
			try {
				List<Tienda> results = tiendaService.findByCriteria(tienda);
				List<Categoria> categorias = categoriaService.findRoot("es");
				request.setAttribute(AttributesNames.LOCALIDAD, localidad);
				request.setAttribute(AttributesNames.RESULTS_TIENDA, results);
				request.setAttribute(AttributesNames.CATEGORIAS, categorias);
				request.getRequestDispatcher(ViewPaths.HTML_TIENDA_RESULTS_TIENDA_JSP).forward(request, response);
			} catch (DataException e) {

				e.printStackTrace();
			}
		}
		else if(ActionNames.DETALLE.equalsIgnoreCase(action)) {
			String idStr = request.getParameter(ParameterNames.ID_TIENDA);
			Long id = Long.valueOf(idStr);
			ProductoCriteria productoCrit = new ProductoCriteria();
			productoCrit.setIdTienda(id);
			Tienda tienda = new Tienda();
			try {
				List<Producto> results = productoService.findBy(productoCrit, "es");

				tienda = tiendaService.findById(id);
				List<Categoria> categorias = categoriaService.findByIdPadre(tienda.getCategoria(),"es");
				request.setAttribute(AttributesNames.PRODUCTO, results);
				request.setAttribute(AttributesNames.TIENDA, tienda);
				request.setAttribute(AttributesNames.CATEGORIAS, categorias);
				request.getRequestDispatcher(ViewPaths.HTML_PRODUCTO_RESULTS_PRODUCTO_JSP).forward(request, response);
			} catch (DataException e) {

				e.printStackTrace();
			}
		}
		else if(ActionNames.CREAR.equalsIgnoreCase(action)) {
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
			String idEmpresa = request.getParameter(ParameterNames.ID_EMPRESA);
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
			tienda.setIdEmpresa(Long.valueOf(idEmpresa));
			tienda.setNombre(nombre);
			tienda.setNumeroMovil(movil);
			tienda.setNumeroTelefono(telefono);

			try {
				tiendaService.create(tienda);
				List<Tienda> tiendas = tiendaService.findByIdEmpresa(Long.valueOf(idEmpresa));
				List<Categoria> cat = categoriaService.findRoot("es");
				Empresa empresa = empresaService.findById(Long.valueOf(idEmpresa));
				request.setAttribute(AttributesNames.EMPRESA, empresa);
				request.setAttribute(AttributesNames.RESULTS_TIENDA, tiendas);
				request.setAttribute(AttributesNames.CATEGORIAS, cat);
				request.getRequestDispatcher(ViewPaths.HTML_EMPRESA_RESULTS_TIENDAS_JSP).forward(request, response);
			} catch (DataException | ServiceException e) {
			}
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

	}

}
