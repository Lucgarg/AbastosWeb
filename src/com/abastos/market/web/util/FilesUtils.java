package com.abastos.market.web.util;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FilesUtils {

	public FilesUtils() {

	}
	/**
	 * @param request
	 * @return un mapa de parametros y objeto FileItem, para facilitar la recuperacion de parametros en formulario multipart
	 * @throws FileUploadException
	 */
	public static Map<String, FileItem> mapParam(HttpServletRequest request) throws FileUploadException {
		
		Map <String, FileItem> mapParam = new HashMap<String, FileItem>();
		List<FileItem> multiparts;

		multiparts = new ServletFileUpload(
				new DiskFileItemFactory()).parseRequest(request);
		
		for(FileItem item : multiparts){
			mapParam.put(item.getFieldName(), item);
		}
		return mapParam;
	
	}
	/**
	 * @param id del producto
	 * @param nameImg tipo de imagen
	 * @param url 
	 * @param file 
 	 * @throws Exception
	 */
	public static void writerImg(Long id, String nameImg, String url, FileItem file ) throws Exception {
		String name = new StringBuilder(String.valueOf(id)).append(nameImg).toString();
		file.write(new File(url + File.separator + name));
		
	}

}
