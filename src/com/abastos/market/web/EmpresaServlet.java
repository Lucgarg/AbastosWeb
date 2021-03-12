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
import com.abastos.model.Empresa;
import com.abastos.model.Tienda;
import com.abastos.service.CategoriaService;
import com.abastos.service.DataException;
import com.abastos.service.EmpresaService;
import com.abastos.service.TiendaService;
import com.abastos.service.exceptions.ServiceException;
import com.abastos.service.impl.CategoriaServiceImpl;
import com.abastos.service.impl.EmpresaServiceImpl;
import com.abastos.service.impl.TiendaServiceImpl;

/**
 * Servlet implementation class EmpresaServlet
 */
@WebServlet("/empresa")
public class EmpresaServlet extends HttpServlet {


	
	
	private EmpresaService empresaService;
	private TiendaService tiendaService;
	private CategoriaService categoriaService;
	public EmpresaServlet() {
		categoriaService = new CategoriaServiceImpl();
		tiendaService = new TiendaServiceImpl();
		empresaService = new EmpresaServiceImpl();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter(ActionNames.ACTION);
		if(ActionNames.LOG_IN.equalsIgnoreCase(action)) {
			String usuario = request.getParameter(ParameterNames.NOMBRE_USUARIO);
		
			String email = usuario.matches("^(.+)@(.+)$")? usuario: null;
			usuario = email == null? usuario: null;
			
			String password = request.getParameter(ParameterNames.PASSWORD);
		
			try {
				Empresa empresa = empresaService.login(email,usuario, password);
				if(empresa !=null) {
					List<Tienda> tienda = tiendaService.findByIdEmpresa(empresa.getId());
					List<Categoria> categoria = categoriaService.findRoot("es");
				
					request.setAttribute(AttributesNames.EMPRESA, empresa);
					request.setAttribute(AttributesNames.RESULTS_TIENDA, tienda);
					request.setAttribute(AttributesNames.CATEGORIAS, categoria);
					request.setAttribute(AttributesNames.LOCALIDAD, empresa.getDireccion().getIdLocalidad());
					request.getRequestDispatcher(ViewPaths.HTML_EMPRESA_RESULTS_TIENDAS_JSP).forward(request, response);
				}
			} catch (DataException | ServiceException e) {

				e.printStackTrace();
			}
		}
		if(ActionNames.BUSCAR.equalsIgnoreCase(action)) {
			String idEmpresa = request.getParameter(ParameterNames.ID_EMPRESA);
			try {
				Empresa empresa = empresaService.findById(Long.valueOf(idEmpresa));
				if(empresa !=null) {
					List<Tienda> tienda = tiendaService.findByIdEmpresa(empresa.getId());
					List<Categoria> categoria = categoriaService.findRoot("es");
				
					request.setAttribute(AttributesNames.EMPRESA, empresa);
					request.setAttribute(AttributesNames.RESULTS_TIENDA, tienda);
					request.setAttribute(AttributesNames.CATEGORIAS, categoria);
					request.setAttribute(AttributesNames.LOCALIDAD, empresa.getDireccion().getIdLocalidad());
					request.getRequestDispatcher(ViewPaths.HTML_EMPRESA_RESULTS_TIENDAS_JSP).forward(request, response);
				}
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
