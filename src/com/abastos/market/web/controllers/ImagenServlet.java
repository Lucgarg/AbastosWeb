package com.abastos.market.web.controllers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.market.web.util.ActionNames;

/**
 * Servlet implementation class ImagenServlet
 */
@WebServlet("/imagen")
public class ImagenServlet extends HttpServlet {
	  private final String UPLOAD_DIRECTORY = "/imgs";

	  private static Logger logger = LogManager.getLogger(ImagenServlet.class);

	public ImagenServlet() {
		super();

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("subiendo imagen...");
		
		logger.debug(ServletFileUpload.isMultipartContent(request));
		
		
		
		if(ServletFileUpload.isMultipartContent(request)){
			try {
				
				List<FileItem> multiparts = new ServletFileUpload(
						new DiskFileItemFactory()).parseRequest(request);
				
				for(FileItem item : multiparts){
					if(!item.isFormField()){
						String name = "prueba021.jpg";
						item.write( new File(UPLOAD_DIRECTORY + File.separator + name));
					}
				}

				
			} catch (Exception ex) {
				logger.debug(ex);
			}          

		}
		}

		

	



		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
