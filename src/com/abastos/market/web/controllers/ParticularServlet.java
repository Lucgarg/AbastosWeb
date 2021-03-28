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
			particular.setAlias(mapParameter.get(ParameterNames.AlIAS)[0]);
			particular.setApellidos(mapParameter.get(ParameterNames.APELLIDOS)[0]);
			particular.setContrasena(ValidationUtils.passwordValidation(request, ParameterNames.PASSWORD, error));
			particular.setEmail(mapParameter.get(ParameterNames.EMAIL)[0]);
			particular.setNombre(mapParameter.get(ParameterNames.NOMBRE_USUARIO)[0]);
			particular.setNumberoMovil(mapParameter.get(ParameterNames.MOVIL)[0]);
			particular.setNumeroTelefono(mapParameter.get(ParameterNames.TELEFONO)[0]);
			DireccionDto direccion = new DireccionDto();
			direccion.setCalle(mapParameter.get(ParameterNames.CALLE)[0]);
			direccion.setNumero(Integer.valueOf(mapParameter.get(ParameterNames.NUMERO)[0]));
			direccion.setIdLocalidad(Long.valueOf(mapParameter.get(ParameterNames.LOCALIDAD)[0]));
			direccion.setPiso(mapParameter.get(ParameterNames.PISO)[0]);
			direccion.setCodigoPostal(mapParameter.get(ParameterNames.CODIGO_POSTAL)[0]);
			direccion.setIdTipoDireccion(1);
			particular.add(direccion);
			try {
				particularService.registrar(particular);
				Map<String,Object> valores = new HashMap<String,Object>();
				valores.put("user", particular);
				valores.put("enlace", UrlBuilder.getUrl(request, "precreate?action=index"));
				mailService.sendMail(valores,3L, particular.getEmail());
				target = UrlBuilder.getUrlForController(request, ControllerPath.PRECREATE, ActionNames.INICIO);
				redirect = true;
			} catch (DataException | ServiceException e) {
				logger.warn(e.getMessage(),e);
			}
		}
		else if(ActionNames.CERRAR.equalsIgnoreCase(action)) {
			SessionManager.remove(request, AttributesNames.USUARIO);
			target   = UrlBuilder.getUrlForController(request, ControllerPath.PRECREATE, ActionNames.INICIO);
			redirect = true;
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