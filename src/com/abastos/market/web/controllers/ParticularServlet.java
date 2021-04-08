package com.abastos.market.web.controllers;

import java.io.IOException;
import java.util.HashMap;
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
import com.abastos.market.web.util.ValidationUtils;
import com.abastos.model.DireccionDto;
import com.abastos.model.Particular;
import com.abastos.service.ContenidoService;
import com.abastos.service.DataException;
import com.abastos.service.MailService;
import com.abastos.service.ParticularService;
import com.abastos.service.exceptions.ServiceException;
import com.abastos.service.exceptions.UserNotFoundException;
import com.abastos.service.impl.ContenidoServiceImpl;
import com.abastos.service.impl.MailServiceImpl;
import com.abastos.service.impl.ParticularServiceImpl;


@WebServlet("/particular")
public class ParticularServlet extends HttpServlet {
	private static Logger logger = LogManager.getLogger(ParticularServlet.class);
	private ParticularService particularService = null;
	private ContenidoService contenidoService = null;
	private MailService mailService = null;

	public ParticularServlet() {
		super();
		particularService = new ParticularServiceImpl();
		contenidoService = new ContenidoServiceImpl();
		mailService = new MailServiceImpl();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(logger.isDebugEnabled()) {
			logger.debug(ParameterUtils.print(request.getParameterMap()));
		}
		Map<String, String[]> mapParameter = request.getParameterMap();
		String action = request.getParameter(ActionNames.ACTION);
		String target = null;
		boolean redirect = false;
		Errors error = new  Errors();
		if(ActionNames.REGISTRO.equalsIgnoreCase(action)) {

			Particular particular = new Particular();
			Particular idParticular = null;
			particular.setAlias(ValidationUtils.nameValidator(mapParameter, ParameterNames.ALIAS,  error));
			particular.setApellidos(ValidationUtils.apellidosValidator(mapParameter, error));
			particular.setContrasena(ValidationUtils.passwordValidation(mapParameter,  error));
			particular.setEmail(ValidationUtils.emailValidator(mapParameter,  error));
			particular.setNombre(ValidationUtils.numberNotValidator(mapParameter,ParameterNames.NOMBRE_USUARIO,error));
			particular.setNumberoMovil(ValidationUtils.telefonoValidator(mapParameter,ParameterNames.MOVIL , error));
			particular.setNumeroTelefono(ValidationUtils.telefonoValidator(mapParameter, ParameterNames.TELEFONO,error));
			DireccionDto direccion = new DireccionDto();
			direccion.setCalle(ValidationUtils.numberNotValidator(mapParameter,ParameterNames.CALLE,error));
			direccion.setNumero(ValidationUtils.integerValidator(mapParameter, ParameterNames.NUMERO, error));
			direccion.setIdLocalidad(ValidationUtils.longValidator(mapParameter, ParameterNames.LOCALIDAD, error));
			direccion.setPiso(ValidationUtils.pisoValidator(mapParameter, error));
			direccion.setCodigoPostal(ValidationUtils.cdValidator(mapParameter, error));
			direccion.setIdTipoDireccion(1);
			particular.add(direccion);
			if(!error.hasErrors()) {
				try {
					logger.info("registrando usuario");
					idParticular = particularService.registrar(particular);
				}
				catch(DataException e) {
					logger.warn(e.getMessage(),e);
					error.add(ActionNames.REGISTRO, ErrorNames.ERR_GENERIC);
				}
			}
			if(!error.hasErrors()) {
				logger.info(new StringBuilder("enviando email a ").append(particular.getEmail()));
				Map<String,Object> valores = new HashMap<String,Object>();
				redirect = true;
				valores.put("user", particular);
				valores.put("enlace", UrlBuilder.getUrlForController(request, ControllerPath.PARTICULAR, 
						ActionNames.CONFIRMAR_REGISTRO, redirect, ParameterNames.PARTICULAR, String.valueOf(idParticular.getId())));
				try {
					mailService.sendMail(valores,3L, particular.getEmail());
					
					target = UrlBuilder.getUrlForController(request, ControllerPath.PRECREATE, ActionNames.INICIO, redirect);
				} catch (ServiceException e) {
					logger.warn(e.getMessage(),e);
					error.add(ActionNames.REGISTRO, ErrorNames.ERR_SEND_EMAIL);
				}
			}
			if(error.hasErrors()) {

				request.setAttribute(AttributesNames.ERROR, error);
				target = UrlBuilder.getUrlForController(request, ControllerPath.PRECREATE, ActionNames.REGISTRO, redirect,
						ParameterNames.TIP_USUARIO, ActionNames.PARTICULAR);
			}

		}
		else if(ActionNames.CONFIRMAR_REGISTRO.equals(action)) {
			String idParticular = request.getParameter(ParameterNames.PARTICULAR);
			try {
				particularService.updateAlta(Long.valueOf(idParticular));
				redirect = true;
				target = UrlBuilder.getUrlForController(request, ControllerPath.PRECREATE, ActionNames.INICIO, redirect);
			} catch (DataException e) {
				logger.warn(e.getMessage(),e);
				error.add(ActionNames.CONFIRMAR_REGISTRO, ErrorNames.ERR_GENERIC);
				
			}
			if(error.hasErrors()) {
				request.setAttribute(AttributesNames.ERROR, error);
				redirect = true;
				target = UrlBuilder.getUrlForController(request, ControllerPath.PRECREATE, ActionNames.INICIO, redirect);
			}
		}
		else if(ActionNames.CERRAR.equalsIgnoreCase(action)) {
			SessionManager.remove(request, AttributesNames.USUARIO);
	
			CookieManager.removeCookie(response, ParameterNames.MANTENER_SESION, "/");
			redirect = true;
			target   = UrlBuilder.getUrlForController(request, ControllerPath.PRECREATE, ActionNames.INICIO, redirect);
			
		}
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
	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}