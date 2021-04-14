package com.abastos.market.web.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.service.CategoriaService;
import com.abastos.service.ComunidadAutonomaService;
import com.abastos.service.DataException;
import com.abastos.service.LocalidadService;
import com.abastos.service.PaisService;
import com.abastos.service.ProductoService;
import com.abastos.service.ProvinciaService;
import com.abastos.service.impl.CategoriaServiceImpl;
import com.abastos.service.impl.ComunidadAutonomaServiceImpl;
import com.abastos.service.impl.LocalidadServiceImpl;
import com.abastos.service.impl.PaisServiceImpl;
import com.abastos.service.impl.ProductoServiceImpl;
import com.abastos.service.impl.ProvinciaServiceImpl;



public class WebInitFilter implements Filter {
	private static Logger logger = LogManager.getLogger(WebInitFilter.class);
	private CategoriaService categService = null;
	private PaisService paisService = null;
	private ProductoService productoService = null;
    public WebInitFilter() {
    	categService = new CategoriaServiceImpl();
       paisService = new PaisServiceImpl();
       productoService = new ProductoServiceImpl();	
    }


	public void destroy() {
		
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		logger.info("accediendo a WebInitFilter..");
		
		try {
			logger.info("inicializando categorias...");
			categService.findRoot("es");
			logger.info("inicializando paises...");
			paisService.findByAll();
			logger.info("inicializando productos...");
			productoService.findByProductOfert("es");
		} catch (DataException e) {
			
			e.printStackTrace();
		}
		chain.doFilter(request, response);
	}


	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
