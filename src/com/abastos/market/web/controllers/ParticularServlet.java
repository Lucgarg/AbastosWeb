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

/**
 * Servlet implementation class ParticularServlet
 */
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
			particular.setAlias(ValidationUtils.nameValidator(request, ParameterNames.ALIAS,  error));
			particular.setApellidos(ValidationUtils.apellidosValidator(request, error));
			particular.setContrasena(ValidationUtils.passwordValidation(request,  error));
			particular.setEmail(ValidationUtils.emailValidator(request,  error));
			particular.setNombre(ValidationUtils.numberNotValidator(request,ParameterNames.NOMBRE_USUARIO,error));
			particular.setNumberoMovil(ValidationUtils.telefonoValidator(request,ParameterNames.MOVIL , error));
			particular.setNumeroTelefono(ValidationUtils.telefonoValidator(request, ParameterNames.TELEFONO,error));
			DireccionDto direccion = new DireccionDto();
			direccion.setCalle(ValidationUtils.numberNotValidator(request,ParameterNames.CALLE,error));
			direccion.setNumero(ValidationUtils.integerValidator(request, ParameterNames.NUMERO, error));
			direccion.setIdLocalidad(ValidationUtils.longValidator(request, ParameterNames.LOCALIDAD, error));
			direccion.setPiso(ValidationUtils.pisoValidator(request, error));
			direccion.setCodigoPostal(ValidationUtils.cdValidator(request, error));
			direccion.setIdTipoDireccion(1);
			particular.add(direccion);
			if(!error.hasErrors()) {
				try {
					logger.info("registrando usuario");
					particularService.registrar(particular);
				}
				catch(DataException e) {
					logger.warn(e.getMessage(),e);
					error.add(ActionNames.REGISTRO, ErrorNames.ERR_GENERIC);
				}
			}
			if(!error.hasErrors()) {
				logger.info(new StringBuilder("enviando email a ").append(particular.getEmail()));
				Map<String,Object> valores = new HashMap<String,Object>();
				valores.put("user", particular);
				valores.put("enlace", UrlBuilder.getUrl(request, "precreate?action=index"));
				try {
					mailService.sendMail(valores,3L, particular.getEmail());
					redirect = true;
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
		else if(ActionNames.CERRAR.equalsIgnoreCase(action)) {
			SessionManager.remove(request, AttributesNames.USUARIO);
			redirect = true;
			target   = UrlBuilder.getUrlForController(request, ControllerPath.PRECREATE, ActionNames.INICIO, redirect);

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