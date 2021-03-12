package com.abastos.market.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.abastos.market.web.util.ActionNames;
import com.abastos.market.web.util.ParameterNames;
import com.abastos.model.ComunidadAutonoma;
import com.abastos.model.Localidad;
import com.abastos.model.Provincia;
import com.abastos.service.ComunidadAutonomaService;
import com.abastos.service.DataException;
import com.abastos.service.LocalidadService;
import com.abastos.service.ProvinciaService;
import com.abastos.service.impl.ComunidadAutonomaServiceImpl;
import com.abastos.service.impl.LocalidadServiceImpl;
import com.abastos.service.impl.ProvinciaServiceImpl;
import com.google.gson.Gson;

/**
 * Servlet implementation class Localizacion
 */
@WebServlet("/localizacion")
public class Localizacion extends HttpServlet {

	private ComunidadAutonomaService comServ = null;
	private LocalidadService locServ = null;
	private ProvinciaService proServ = null;
 
  
    public Localizacion() {
        super();
        comServ = new ComunidadAutonomaServiceImpl();
        locServ = new LocalidadServiceImpl();
        proServ = new ProvinciaServiceImpl();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter(ActionNames.ACTION);
		
		if(ActionNames.PAIS.equalsIgnoreCase(action)) {
			String pais = request.getParameter(ParameterNames.PAIS);
			System.out.println(pais);
			
			try {
				
				List<ComunidadAutonoma> comunidadAutonoma = comServ.findByIdPais(Long.valueOf(pais));
				
				Gson gson = new Gson();
				response.setContentType( "application/json");
				response.getWriter( ).println( gson.toJson(comunidadAutonoma));
				
			
			} catch (NumberFormatException | DataException e) {
				e.printStackTrace();
			}
		}
		else if(ActionNames.COMUNIDAD.equalsIgnoreCase(action)) {
			String comunidad = request.getParameter(ParameterNames.COMUNIDAD);
			try {
				List<Provincia> provincias = proServ.findByIdComunidad(Long.valueOf(comunidad));
				Gson gson = new Gson();
				response.setContentType( "application/json");
				response.getWriter( ).println( gson.toJson(provincias));
			} catch (NumberFormatException | DataException e) {
				e.printStackTrace();
			}
		}
		else if(ActionNames.PROVINCIA.equalsIgnoreCase(action)) {
			String provincia = request.getParameter(ParameterNames.PROVINCIA);
			try {
				List<Localidad> localidades = locServ.findByIdProvincia(Long.valueOf(provincia));
				Gson gson = new Gson();
				response.setContentType( "application/json");
				response.getWriter( ).println( gson.toJson(localidades));
			} catch (NumberFormatException | DataException e) {
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
