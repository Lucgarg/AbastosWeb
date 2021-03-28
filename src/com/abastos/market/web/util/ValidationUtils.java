package com.abastos.market.web.util;

import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.mail.Email;
import org.apache.commons.validator.routines.EmailValidator;

import com.abastos.market.web.controllers.Errors;

public class ValidationUtils {
	private static final Pattern REG_ALIAS = Pattern.compile("[a-zA-Z1-9._]{3,15}");
	private static final Pattern REG_NOMBRE = Pattern.compile("[a-zA-Z]{3,20}");
	private static final Pattern REG_APELLIDOS = Pattern.compile("[a-zA-Z]{2,120}");
	private static final Pattern REG_NUMBER = Pattern.compile("^[1-9][0-9]{8}$|^\\+[0-9]{2}\\s[1-9][0-9]{8}$");
	private static final Pattern REG_CODPOSTAL = Pattern.compile("\b^[1-9][0-9]{4}\b");
	EmailValidator email = EmailValidator.getInstance();
	
	
	public ValidationUtils() {
	
	}
	public static String passwordValidation(HttpServletRequest request, String parameter, Errors error) {
		Map<String, String[]> mapParameter = request.getParameterMap();
		String [] parametros = mapParameter.get(parameter);
		for(String p: parametros) {
			p.trim();
		}
		if(parametros.length == 0) {
			error.add(parameter, ErrorNames.ERR_PASSWORD_BLANK);
		}
		else if(parametros.length < 0) {
			error.add(parameter, ErrorNames.ERR_PASSWORD_BLANK);
		}
		else if(parametros[0].trim() != parametros[1].trim()){
			error.add(parameter, ErrorNames.ERR_PASSWORD_DIFFERENT);	
		}
		if(!error.hasErrors()) {
			return parametros[0];
		}
		else {
			return null;
		}
	}
	public static String aliasValidator(HttpServletRequest request, String parameter, Errors error) {
		Map<String, String[]> mapParameter = request.getParameterMap();
		String alias = mapParameter.get(parameter)[0];
		if(!REG_ALIAS.matcher(alias).matches()) {
			error.add(parameter, ErrorNames.ERR_ALIAS);
		}
		if(!error.hasErrors()) {
			return alias;
		}
		else {
			return null;
		}
	}
	
	
}
