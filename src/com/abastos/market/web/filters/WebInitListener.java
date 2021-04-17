package com.abastos.market.web.filters;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.abastos.service.DataException;
import com.abastos.service.CategoriaService;
import com.abastos.service.PaisService;
import com.abastos.service.ProductoService;
import com.abastos.service.impl.CategoriaServiceImpl;
import com.abastos.service.impl.PaisServiceImpl;
import com.abastos.service.impl.ProductoServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
			
			e.printStackTrace();
		}
		
		
	}

}
