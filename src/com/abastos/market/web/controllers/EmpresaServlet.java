package com.abastos.market.web.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.abastos.market.web.util.CookieManager;
import com.abastos.market.web.util.ErrorNames;
import com.abastos.market.web.util.ParameterNames;
import com.abastos.market.web.util.ParameterUtils;
import com.abastos.market.web.util.SessionManager;
import com.abastos.market.web.util.UrlBuilder;
import com.abastos.market.web.util.ViewPaths;
import com.abastos.market.web.util.WebConstants;
import com.abastos.model.Categoria;
import com.abastos.model.DireccionDto;
import com.abastos.model.Empresa;
import com.abastos.model.Tienda;
import com.abastos.service.CategoriaService;
import com.abastos.service.DataException;
import com.abastos.service.EmpresaService;
import com.abastos.service.MailService;
import com.abastos.service.TiendaService;
import com.abastos.service.exceptions.MailException;
import com.abastos.service.exceptions.ServiceException;
import com.abastos.service.impl.CategoriaServiceImpl;
import com.abastos.service.impl.EmpresaServiceImpl;
import com.abastos.service.impl.MailServiceImpl;
import com.abastos.service.impl.TiendaServiceImpl;


@WebServlet("/empresa")
public class EmpresaServlet extends HttpServlet {
	private static Logger logger = LogManager.getLogger(EmpresaServlet.class);



	private EmpresaService empresaService;
	private TiendaService tiendaService;
	private CategoriaService categoriaService;
	private MailService mailService;
	public EmpresaServlet() {
		categoriaService = new CategoriaServiceImpl();
		tiendaService = new TiendaServiceImpl();
		empresaService = new EmpresaServiceImpl();
		mailService = new MailServiceImpl();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(logger.isDebugEnabled()) {
			logger.debug(ParameterUtils.print(request.getParameterMap()));
		}
		String action = request.getParameter(ActionNames.ACTION);
		Errors error = new Errors();
		String target = null;
		boolean redirect = false;
		String idioma = (String)SessionManager.get(request, AttributesNames.IDIOMA);
		if(idioma == null) {
			idioma = "es";
		}
		//forward a resultado de tiendas de una empresa
		if(ActionNames.BUSCAR.equalsIgnoreCase(action)) {
			String idEmpresa = request.getParameter(ParameterNames.ID_EMPRESA);
			try {
				Empresa empresa = empresaService.findById(Long.valueOf(idEmpresa));
				if(empresa !=null) {
					List<Tienda> tienda = tiendaService.findByIdEmpresa(empresa.getId());
					List<Categoria> categoria = categoriaService.findRoot(idioma);
					request.setAttribute(AttributesNames.EMPRESA, empresa);
					request.setAttribute(AttributesNames.RESULTS_TIENDA, tienda);
					request.setAttribute(AttributesNames.CATEGORIAS, categoria);
					request.setAttribute(AttributesNames.LOCALIDAD, empresa.getDireccion().getIdLocalidad());
					target = ViewPaths.EMPRESA_RESULTS_TIENDAS;

				}
			} catch (DataException e) {
				logger.warn(e.getMessage(),e);
				error.add(ActionNames.REGISTRO, ErrorNames.ERR_GENERIC_SEARCH_TIENDA);
				request.setAttribute(AttributesNames.ERROR, error);
				target = ViewPaths.PERFIL_EMPRESA;
			}


		}
		if(ActionNames.REGISTRO.equalsIgnoreCase(action)) {
			Empresa idEmpresa = null;
			String nombreUsuario = request.getParameter(ParameterNames.NOMBRE_USUARIO);
			String apellidos = request.getParameter(ParameterNames.APELLIDOS);
			String alias = request.getParameter(ParameterNames.ALIAS);
			String CIF = request.getParameter(ParameterNames.CIF);
			String razonSocial = request.getParameter(ParameterNames.RAZON_SOCIAL);
			String email = request.getParameter(ParameterNames.EMAIL);
			String calle = request.getParameter(ParameterNames.CALLE);
			String numero = request.getParameter(ParameterNames.NUMERO);
			String piso = request.getParameter(ParameterNames.CODIGO_POSTAL);
			String localidad = request.getParameter(ParameterNames.LOCALIDAD);
			String pasword = request.getParameter(ParameterNames.PASSWORD);
			String codigoPostal = request.getParameter(ParameterNames.CODIGO_POSTAL);
			Empresa empresa = new Empresa();
			empresa.setNombre(nombreUsuario);
			empresa.setApellidos(apellidos);
			empresa.setAlias(alias);
			empresa.setCif(CIF);
			empresa.setRazonSocial(razonSocial);
			empresa.setCorreoElectronico(email);
			empresa.setContrasena(pasword);
			DireccionDto direccionDto = new DireccionDto();
			direccionDto.setCalle(calle);
			direccionDto.setNumero(Integer.valueOf(numero));
			direccionDto.setPiso(piso);
			direccionDto.setIdLocalidad(Long.valueOf(localidad));
			direccionDto.setIdTipoDireccion(2);
			direccionDto.setCodigoPostal(codigoPostal);
			empresa.setDireccion(direccionDto);
			try {
				idEmpresa = empresaService.registrar(empresa);
				Map<String,Object> valores = new HashMap<String,Object>();
				valores.put(WebConstants.HEADER_AGENT, empresa);
				valores.put(WebConstants.ENLACE, UrlBuilder.getUrlForController(request, ControllerPath.EMPRESA, 
						ActionNames.CONFIRMAR_REGISTRO, true, ParameterNames.EMPRESA, String.valueOf(idEmpresa.getId())));
				mailService.sendMail(valores,3L, empresa.getCorreoElectronico());
				redirect = true;
				target = UrlBuilder.getUrlForController(request, ControllerPath.PRECREATE, ActionNames.INICIO, redirect);

			}catch(MailException e) {
				logger.warn(e.getMessage(),e);
				error.add(ActionNames.REGISTRO, ErrorNames.ERR_SEND_EMAIL);
			} 
			catch (DataException e) {
				logger.warn(e.getMessage(),e);
				error.add(ActionNames.REGISTRO, ErrorNames.ERR_GENERIC);
			}
			if(error.hasErrors()) {

				request.setAttribute(AttributesNames.ERROR, error);
				target = UrlBuilder.getUrlForController(request, ControllerPath.PRECREATE, ActionNames.REGISTRO, redirect,
						ParameterNames.TIP_USUARIO, ActionNames.EMPRESA);
			}
		}
		else if(ActionNames.CONFIRMAR_REGISTRO.equals(action)) {
			String idParticular = request.getParameter(ParameterNames.EMPRESA);
			try {
				empresaService.updateAlta(Long.valueOf(idParticular));
				request.setAttribute(AttributesNames.CONFIRMAR_REGISTRO, ParameterNames.TRUE);
				target = UrlBuilder.getUrlForController(request, ControllerPath.PRECREATE, ActionNames.INICIO, redirect);
			} catch (DataException e) {
				logger.warn(e.getMessage(),e);
				error.add(ParameterNames.ERROR, ErrorNames.ERR_GENERIC);
				request.setAttribute(AttributesNames.ERROR, error);
				target = UrlBuilder.getUrlForController(request, ControllerPath.TIENDA, ActionNames.BUSCAR, redirect);

			}
		}
		else if(ActionNames.CERRAR.equalsIgnoreCase(action)) {
			SessionManager.remove(request, AttributesNames.EMPRESA);

			CookieManager.removeCookie(response, ParameterNames.MANTENER_SESION, "/");
			redirect = true;
			target   = UrlBuilder.getUrlForController(request, ControllerPath.PRECREATE, ActionNames.INICIO, redirect);

		}
		if(target == null) {
			target = UrlBuilder.getUrlForController(request, ControllerPath.PRECREATE, ActionNames.INICIO, redirect);
		}
		if(redirect) { 
			logger.info("Redirect to..." + target);
			response.sendRedirect( target);
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
