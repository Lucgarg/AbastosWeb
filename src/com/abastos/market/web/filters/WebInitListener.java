package com.abastos.market.web.filters;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.market.web.controllers.Errors;
import com.abastos.market.web.util.ActionNames;
import com.abastos.market.web.util.AttributesNames;
import com.abastos.market.web.util.ControllerPath;
import com.abastos.market.web.util.ErrorNames;
import com.abastos.market.web.util.ParameterNames;
import com.abastos.market.web.util.UrlBuilder;
import com.abastos.service.CategoriaService;
import com.abastos.service.DataException;
import com.abastos.service.PaisService;
import com.abastos.service.ProductoService;
import com.abastos.service.impl.CategoriaServiceImpl;
import com.abastos.service.impl.PaisServiceImpl;
import com.abastos.service.impl.ProductoServiceImpl;
public class WebInitListener implements ServletContextListener {
	private static Logger logger = LogManager.getLogger(WebInitListener.class);
	private CategoriaService categService = null;
	private PaisService paisService = null;
	private ProductoService productoService = null;
    public WebInitListener() {
    	categService = new CategoriaServiceImpl();
       paisService = new PaisServiceImpl();
       productoService = new ProductoServiceImpl();	
    }

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		logger.info("accediendo a WebInitFilter..");
		Errors error = new Errors();
		try {
			logger.info("inicializando categorias...");
			categService.findRoot("es");
			categService.findRoot("en");
			logger.info("inicializando paises...");
			paisService.findByAll();
			logger.info("inicializando productos...");
			productoService.findByProductOfert("es");
			productoService.findByProductOfert("en");
		
		} catch (DataException e) {
			logger.warn(e.getMessage(),e);

			
		}
		
		
	}

}
