package com.abastos.market.web;

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
import com.abastos.market.web.util.ParameterNames;
import com.abastos.market.web.util.ParameterUtils;
import com.abastos.market.web.util.ViewPaths;
import com.abastos.model.Categoria;
import com.abastos.model.Empresa;
import com.abastos.model.Tienda;
import com.abastos.service.CategoriaService;
import com.abastos.service.DataException;
import com.abastos.service.EmpresaService;
import com.abastos.service.ParticularService;
import com.abastos.service.TiendaService;
import com.abastos.service.exceptions.ServiceException;
import com.abastos.service.impl.CategoriaServiceImpl;
import com.abastos.service.impl.EmpresaServiceImpl;
import com.abastos.service.impl.ParticularServiceImpl;
import com.abastos.service.impl.TiendaServiceImpl;


@WebServlet("/usuario")
public class UsuarioServlet extends HttpServlet {

	private static Logger logger = LogManager.getLogger(UsuarioServlet.class);

	private EmpresaService empresaService;
	private TiendaService tiendaService;
	private CategoriaService categoriaService;
	public UsuarioServlet() {
		empresaService = new EmpresaServiceImpl();
		tiendaService = new TiendaServiceImpl();
		categoriaService = new CategoriaServiceImpl();
	}




	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(logger.isDebugEnabled()) {
			logger.debug(ParameterUtils.print(request.getParameterMap()));
		}
		String action = request.getParameter(ActionNames.ACTION);
		String target = null;
		boolean redirect = false;
		if(ActionNames.LOG_IN.equalsIgnoreCase(action)) {
			String tipUsuario = request.getParameter(ParameterNames.TIP_USUARIO);
			if(ActionNames.EMPRESA.equalsIgnoreCase(tipUsuario)) {
				String usuario = request.getParameter(ParameterNames.NOMBRE_USUARIO);
				String email = usuario.matches("^(.+)@(.+)$")? usuario: null;
				usuario = email == null? usuario: null;
				String password = request.getParameter(ParameterNames.PASSWORD);
				try {
					Empresa empresa = empresaService.login(email,usuario, password);
					List<Tienda> tienda = tiendaService.findByIdEmpresa(empresa.getId());
					List<Categoria> categoria = categoriaService.findRoot("es");
					request.setAttribute(AttributesNames.EMPRESA, empresa);
					request.setAttribute(AttributesNames.RESULTS_TIENDA, tienda);
					request.setAttribute(AttributesNames.CATEGORIAS, categoria);
					request.setAttribute(AttributesNames.LOCALIDAD, empresa.getDireccion().getIdLocalidad());
					target = ViewPaths.EMPRESA_RESULTS_TIENDAS;
				} catch (DataException | ServiceException e) {
					logger.warn(e.getMessage(),e);
				}
			}

		}
		if(ActionNames.REGISTRO.equalsIgnoreCase(action)) {
			
		}
		if(redirect) { 
			logger.info("Redirect to..." + target);
			response.sendRedirect(request.getContextPath() + target);
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
