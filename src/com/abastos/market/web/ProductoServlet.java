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
import com.abastos.model.Oferta;
import com.abastos.model.Producto;
import com.abastos.model.ProductoIdioma;
import com.abastos.model.Tienda;
import com.abastos.service.CategoriaService;
import com.abastos.service.DataException;
import com.abastos.service.OfertaService;
import com.abastos.service.ProductoCriteria;
import com.abastos.service.ProductoService;
import com.abastos.service.TiendaService;
import com.abastos.service.exceptions.LimitCreationException;
import com.abastos.service.impl.CategoriaServiceImpl;
import com.abastos.service.impl.OfertaServiceImpl;
import com.abastos.service.impl.ProductoServiceImpl;
import com.abastos.service.impl.TiendaServiceImpl;




@WebServlet("/producto")
public class ProductoServlet extends HttpServlet {
	
	private CategoriaService categoriaService;
	private ProductoService productoServ = null;
	private TiendaService tiendaServ = null;
	private OfertaService ofertServ = null;
	public ProductoServlet() {
		categoriaService = new CategoriaServiceImpl();
		productoServ = new ProductoServiceImpl();
		tiendaServ = new TiendaServiceImpl();
		ofertServ = new OfertaServiceImpl();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter(ActionNames.ACTION);
		if(ActionNames.BUSCAR.equalsIgnoreCase(action)){
			String precioDesde = request.getParameter(ParameterNames.PRECIO_DESDE);
			String precioHasta = request.getParameter(ParameterNames.PRECIO_HASTA);
			String origen = request.getParameter(ParameterNames.ORIGEN);
			String oferta = request.getParameter(ParameterNames.OFERTA);
			String categoria = request.getParameter(ParameterNames.CATEGORIA);
			String idioma = request.getParameter(ParameterNames.IDIOMA);
			String idTienda = request.getParameter(ParameterNames.ID_TIENDA);
			String idEmpresa = request.getParameter(ParameterNames.ID_EMPRESA);
			
			
			try {
			ProductoCriteria productoCri = new ProductoCriteria();
			if(origen != null) {
				productoCri.setIdOrigen(origen.charAt(0));
			}
			if(oferta != null) {
				productoCri.setOferta(Boolean.valueOf(oferta));
			}
			if(precioDesde != "" ) {
				if(precioDesde != null) {
				productoCri.setPredioDesde(Double.valueOf(precioDesde));
				}
			}
			if(precioHasta != "" ) {
				if(precioHasta != null) {
				productoCri.setPrecioHasta(Double.valueOf(precioHasta));
				}
			}
			if(categoria != null) {
				productoCri.setIdCategoria(Integer.valueOf(categoria));
			}
			if(idTienda != null) {
			productoCri.setIdTienda(Long.valueOf(idTienda));
			}
			if(idEmpresa != null) {
			productoCri.setIdEmpresa(Long.valueOf(idEmpresa));
			}
		
				List<Producto> 	results = productoServ.findBy(productoCri, "es");
			
				Tienda tienda = null;
				List<Categoria> categorias = null;
				List<Tienda> tiendaResults = null;
				if(idTienda != null) {
				 tienda = tiendaServ.findById(Long.valueOf(idTienda));
				 
				 categorias = categoriaService.findByIdPadre(Integer.valueOf(tienda.getCategoria()), "es");
				
				 request.setAttribute(ParameterNames.CATEGORIA, categorias);
				}
				else if(idEmpresa != null){
				tiendaResults= tiendaServ.findByIdEmpresa(Long.valueOf(idEmpresa));
				
				}
				
			
				request.setAttribute(AttributesNames.EMPRESA, idEmpresa);
				request.setAttribute(AttributesNames.PRODUCTO, results);
				if(idTienda != null) {
				request.setAttribute(AttributesNames.TIENDA, tienda);
				}
				else if (idEmpresa != null) {
					request.setAttribute(AttributesNames.TIENDA, tiendaResults);
				}
				
				if(idEmpresa ==null) {
					request.getRequestDispatcher(ViewPaths.HTML_PRODUCTO_RESULTS_PRODUCTO_JSP).forward(request, response);
				}
				else {
					request.getRequestDispatcher(ViewPaths.HTML_EMPRESA_RESULTS_PRODUCTO_JSP).forward(request, response);
				}
			} catch (DataException e) {

				e.printStackTrace();
			}
		}
		else if(ActionNames.DETALLE.equalsIgnoreCase(action)) {
			String idStr = request.getParameter(ParameterNames.ID_PRODUCTO);
			String idTien = request.getParameter(ParameterNames.ID_TIENDA);
			Long id = Long.valueOf(idStr);

			Tienda tienda = new Tienda();
			try {
				Producto result = productoServ.findById(id, "es");
				tienda = tiendaServ.findById(Long.valueOf(idTien));
				request.setAttribute(AttributesNames.PRODUCTO, result);
				request.setAttribute(AttributesNames.TIENDA, tienda);
				request.getRequestDispatcher(ViewPaths.HTML_PRODUCTO_DETALLE_PRODUCTO_JSP).forward(request, response);
			} catch (DataException e) {

				e.printStackTrace();
			}

		}
		else if(ActionNames.CREAR.equalsIgnoreCase(action)) {
			String empresa = request.getParameter(ParameterNames.ID_EMPRESA);
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
			
				productoServ.create(producto);
			
			response.sendRedirect(new StringBuilder(ViewPaths.HTTP_LOCALHOST_8080_ABASTOS_PRODUCTO_ACTION_BUSCAR_ID_EMPRESA)
					.append(empresa).toString());
			
		
			
			} catch (LimitCreationException | DataException e) {
				e.printStackTrace();
			}
		}
		


	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
