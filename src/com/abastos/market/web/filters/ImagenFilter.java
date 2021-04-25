package com.abastos.market.web.filters;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.abastos.market.web.util.UrlBuilder;
import com.abastos.market.web.util.WebConstants;


public class ImagenFilter implements Filter {


	public ImagenFilter() {

	}


	public void destroy() {

	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String url = UrlBuilder.urlRealPath(httpRequest, WebConstants.UPLOAD_DIRECTORY_IMG);

		String [] urlArray = httpRequest.getRequestURI().split("imgs");
		String img = urlArray[urlArray.length -1];
		String imgUrl = new StringBuilder(url).append(img).toString();
		String mime = httpRequest.getServletContext().getMimeType(imgUrl);
		FileInputStream file = null;
		BufferedInputStream reader = null;
		BufferedOutputStream writer = null; 
		try {
			httpResponse.setContentType(mime);	
			File fil = new File(imgUrl);
			if(fil.exists()) {
			 file = new FileInputStream(imgUrl);
			 reader = new BufferedInputStream(file);  
			 writer = new BufferedOutputStream(httpResponse.getOutputStream()); 
			int ch = 0;
			while((ch = reader.read()) != -1) {
				writer.write(ch);
			}
			file.close();
			reader.close();  
			writer.close();
			}
		} catch (IOException e) {

			e.printStackTrace();
		}


	}



	public void init(FilterConfig fConfig) throws ServletException {

	}

}
