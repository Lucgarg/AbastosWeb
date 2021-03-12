package com.abastos.market.web;

import java.io.IOException;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.abastos.model.ComunidadAutonoma;
import com.abastos.model.Localidad;
import com.abastos.model.Oferta;
import com.abastos.model.Pais;
import com.abastos.model.Provincia;
import com.abastos.model.Tienda;
import com.abastos.model.TipoOferta;
import com.abastos.service.CategoriaService;
import com.abastos.service.ComunidadAutonomaService;
import com.abastos.service.DataException;
import com.abastos.service.LocalidadService;
import com.abastos.service.OfertaService;
import com.abastos.service.PaisService;
import com.abastos.service.ProvinciaService;
import com.abastos.service.TiendaService;
import com.abastos.service.TipoOfertaService;
import com.abastos.service.impl.CategoriaServiceImpl;
import com.abastos.service.impl.ComunidadAutonomaServiceImpl;
import com.abastos.service.impl.LocalidadServiceImpl;
import com.abastos.service.impl.OfertaServiceImpl;
import com.abastos.service.impl.PaisServiceImpl;
import com.abastos.service.impl.ProvinciaServiceImpl;
import com.abastos.service.impl.TiendaServiceImpl;
import com.abastos.service.impl.TipoOfertaServiceImpl;

/**
 * Servlet implementation class PrecreateServlet
 */
@WebServlet("/precreate")
public class PrecreateServlet extends HttpServlet {

	private CategoriaService categoriaService = null;
	private PaisService paisService = null;
	private ComunidadAutonomaService comunidadAutonoma = null;
	private LocalidadService localidad = null;
	private ProvinciaService provincia = null;
	private Map<String, String> categoriasJson= null;
	private TipoOfertaService tipOfert = null;
	private TiendaService tiendaService = null;
	private OfertaService ofertaService = null;
	public PrecreateServlet() {
		categoriasJson = new HashMap<String, String>();
		categoriaService = new CategoriaServiceImpl();
		paisService = new PaisServiceImpl();
		localidad = new LocalidadServiceImpl();
		provincia = new ProvinciaServiceImpl();
		comunidadAutonoma = new ComunidadAutonomaServiceImpl();
		tipOfert = new TipoOfertaServiceImpl();
		tiendaService = new TiendaServiceImpl();
		ofertaService = new OfertaServiceImpl();

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter(ActionNames.ACTION);
		if(ActionNames.EMPRESA.equalsIgnoreCase(action)) {
			String idEmpresa = request.getParameter(ParameterNames.ID_EMPRESA);

			try {
				List<Categoria> categorias = categoriaService.findRoot("es");
				List<Pais> paises = paisService.findByAll();
				List<Provincia> provincias = provincia.findByIdComunidad(1L);
				List<Localidad> local = localidad.findByIdProvincia(1L);
				List<ComunidadAutonoma> comAut = comunidadAutonoma.findByIdPais(1L);


				request.setAttribute(AttributesNames.EMPRESA, idEmpresa);
				request.setAttribute(AttributesNames.CATEGORIAS, categorias);
				request.setAttribute(AttributesNames.PAISES, paises);
		

				request.getRequestDispatcher(ViewPaths.HTML_EMPRESA_CREATE_TIENDA_JSP).forward(request, response);
			} catch (DataException e) {
				e.printStackTrace();
			}
		}else if(ActionNames.OFERTA.equalsIgnoreCase(action)){
			String empresa = request.getParameter(ParameterNames.ID_EMPRESA);

			try {
				List<TipoOferta> tipoOferta = tipOfert.findAll();

				request.setAttribute(AttributesNames.EMPRESA, empresa);
				request.setAttribute(AttributesNames.TIPO, tipoOferta);
				request.getRequestDispatcher(ViewPaths.HTML_OFERTA_CREATE_OFERTA_JSP).forward(request, response);
			} catch (NumberFormatException | DataException e) {
				e.printStackTrace();
			}
		}
		else if(ActionNames.PRODUCTO.equalsIgnoreCase(action)) {
			String empresa = request.getParameter(ParameterNames.ID_EMPRESA);
			Long idEmpresa = Long.valueOf(empresa);
			try {
				List<Tienda> listTienda = tiendaService.findByIdEmpresa(idEmpresa);
				List<Oferta> listOferta = ofertaService.findByIdEmpresa(idEmpresa);
				List<Categoria> categorias=	categoriaService.findRoot("es");
				request.setAttribute(AttributesNames.EMPRESA, empresa);
				request.setAttribute(AttributesNames.RESULTS_TIENDA, listTienda);
				request.setAttribute(AttributesNames.OFERTAS, listOferta);
				request.setAttribute(AttributesNames.CATEGORIAS, categorias);
				request.getRequestDispatcher(ViewPaths.HTML_PRODUCTO_CREATE_PRODUCTO_JSP).forward(request, response);
			} catch (DataException e) {
				e.printStackTrace();
			}
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
