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
import com.abastos.market.web.util.ParameterNames;
import com.abastos.market.web.util.ParameterUtils;
import com.abastos.market.web.util.SessionManager;
import com.abastos.market.web.util.UrlBuilder;
import com.abastos.market.web.util.ViewPaths;
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
		String action = request.getParameter(ActionNames.ACTION);
		String target = null;
		boolean redirect = false;
	
		if(ActionNames.REGISTRO.equalsIgnoreCase(action)) {
			String nombreUsuario = request.getParameter(ParameterNames.NOMBRE_USUARIO);
			String apellido = request.getParameter(ParameterNames.APELLIDOS);
			String alias = request.getParameter(ParameterNames.AlIAS);
			String numeroTelefono = request.getParameter(ParameterNames.TELEFONO);
			String numberoMovil = request.getParameter(ParameterNames.MOVIL);
			String email = request.getParameter(ParameterNames.EMAIL);
			String calle = request.getParameter(ParameterNames.CALLE);
			String numero = request.getParameter(ParameterNames.NUMERO);
			String piso = request.getParameter(ParameterNames.CODIGO_POSTAL);
			String localidad = request.getParameter(ParameterNames.LOCALIDAD);
			String password = request.getParameter(ParameterNames.PASSWORD);
			String codigoPostal = request.getParameter(ParameterNames.CODIGO_POSTAL);
			Particular particular = new Particular();
			particular.setAlias(alias);
			particular.setApellidos(apellido);
			particular.setContrasena(password);
			particular.setEmail(email);
			particular.setNombre(nombreUsuario);
			particular.setNumberoMovil(numberoMovil);
			particular.setNumeroTelefono(numeroTelefono);
			DireccionDto direccion = new DireccionDto();
			direccion.setCalle(calle);
			direccion.setNumero(Integer.valueOf(numero));
			direccion.setIdLocalidad(Long.valueOf(localidad));
			direccion.setPiso(piso);
			direccion.setCodigoPostal(codigoPostal);
			direccion.setIdTipoDireccion(1);
			particular.add(direccion);
			try {
				particularService.registrar(particular);
				Map<String,Object> valores = new HashMap<String,Object>();
				valores.put("user", particular);
				valores.put("enlace", UrlBuilder.getUrl(request, "precreate?action=index"));
				mailService.sendMail(valores,3L, particular.getEmail());
				target = ViewPaths.TIENDA_BUSQUEDA;
				redirect = true;
			} catch (DataException | ServiceException e) {
				logger.warn(e.getMessage(),e);
			}
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
