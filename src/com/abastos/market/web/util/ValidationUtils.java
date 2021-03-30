package com.abastos.market.web.util;

import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.mail.Email;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.market.web.controllers.Errors;
import com.abastos.market.web.controllers.ParticularServlet;
import com.abastos.service.MailService;
import com.mysql.cj.util.StringUtils;

public class ValidationUtils {
	private static Logger logger = LogManager.getLogger(ValidationUtils.class);
	private static final Pattern REG_ALIAS = Pattern.compile("[a-zA-Z][a-zA-Z0-9]{2,31}");
	private static final Pattern REG_NUMBER_NOT = Pattern.compile("[a-zA-Z]{3,20}");
	private static final Pattern REG_APELLIDOS = Pattern.compile("[a-zA-Z]{2,20}\\s[a-zA-Z]{2,20}");
	private static final Pattern REG_PISO = Pattern.compile("[a-zA-Z1-9._]{3,5}");
	private static final Pattern REG_NUMBER = Pattern.compile("^[6,8,9][0-9]{8}$|^\\\\+[0-9]{2}\\\\s[1-9][0-9]{8}$");
	private static final Pattern REG_CODPOSTAL = Pattern.compile("^\\d{5}-\\d{4}|\\d{5}|[A-Z]\\d[A-Z] \\d[A-Z]\\d$");
	private static EmailValidator mail  = EmailValidator.getInstance();


	public ValidationUtils() {

	}
	public static String passwordValidation(HttpServletRequest request,  Errors error) {
		Map<String, String[]> mapParameter = request.getParameterMap();
		String [] parametros = mapParameter.get(ParameterNames.PASSWORD);
		for(String p: parametros) {
			p.trim();
		}
		if(parametros.length == 0) {
			error.add(ParameterNames.PASSWORD, ErrorNames.ERR_PASSWORD_BLANK);
		}
		else if(parametros.length < 0) {
			error.add(ParameterNames.PASSWORD, ErrorNames.ERR_PASSWORD_BLANK);
		}
		else if(!parametros[0].trim().equals(parametros[1].trim())){
			error.add(ParameterNames.PASSWORD, ErrorNames.ERR_PASSWORD_DIFFERENT);	
		}
		logger.debug(new StringBuilder("errores encontrados en el campo ")
				.append(ParameterNames.PASSWORD).append(" ")
				.append(error.printError(ParameterNames.PASSWORD)).toString());
		if(!error.hasErrors()) {
			return parametros[0];
		}
		else {
			return null;
		}

	}
	public static String aliasValidator(HttpServletRequest request,  Errors error) {
		Map<String, String[]> mapParameter = request.getParameterMap();
		String alias = mapParameter.get(ParameterNames.ALIAS)[0];
		alias = alias.trim();
		if(StringUtils.isEmptyOrWhitespaceOnly(alias)) {
			error.add(ParameterNames.ALIAS, ErrorNames.ERR_MANDATORY);
		}
		if(!REG_ALIAS.matcher(alias).matches()) {
			error.add(ParameterNames.ALIAS, ErrorNames.ERR_ALIAS);
		}
		logger.debug(new StringBuilder("errores encontrados en el campo ")
				.append(ParameterNames.ALIAS).append(" ")
				.append(error.printError(ParameterNames.ALIAS)).toString());
		if(!error.hasErrors()) {
			return alias;
		}
		else {
			return null;
		}
	}
	public static String pisoValidator(HttpServletRequest request,  Errors error) {
		Map<String, String[]> mapParameter = request.getParameterMap();
		String piso = mapParameter.get(ParameterNames.PISO)[0];
		piso = piso.trim();
		if(StringUtils.isEmptyOrWhitespaceOnly(piso)) {
			error.add(ParameterNames.PISO, ErrorNames.ERR_MANDATORY);
		}
		if(!REG_PISO.matcher(piso).matches()) {
			error.add(ParameterNames.PISO, ErrorNames.ERR_ALIAS);
		}
		logger.debug(new StringBuilder("errores encontrados en el campo ")
				.append(ParameterNames.PISO).append(" ")
				.append(error.printError(ParameterNames.PISO)).toString());
		if(!error.hasErrors()) {
			return piso;
		}
		else {
			return null;
		}
	}
	public static String numberNotValidator(HttpServletRequest request, String parameter, Errors error) {
		Map<String, String[]> mapParameter = request.getParameterMap();
		String nombre = mapParameter.get(parameter)[0];
		nombre = nombre.trim();
		if(StringUtils.isEmptyOrWhitespaceOnly(nombre)) {
			error.add(parameter, ErrorNames.ERR_MANDATORY);
		}
		if(!REG_NUMBER_NOT.matcher(nombre).matches()) {
			error.add(parameter, ErrorNames.ERR_NAME);
		}
		logger.debug(new StringBuilder("errores encontrados en el campo ")
				.append(parameter).append(" ")
				.append(error.printError(parameter)).toString());
		if(!error.hasErrors()) {
			return nombre;
		}
		else {
			return null;
		}
	}
	public static String apellidosValidator(HttpServletRequest request, Errors error) {
		Map<String, String[]> mapParameter = request.getParameterMap();
		
		String apellido = mapParameter.get(ParameterNames.APELLIDOS)[0];
		
		apellido = apellido.trim();
		
		if(StringUtils.isEmptyOrWhitespaceOnly(apellido)) {
			error.add(ParameterNames.APELLIDOS, ErrorNames.ERR_MANDATORY);
		}
		if(!REG_APELLIDOS.matcher(apellido).matches()) {
			error.add(ParameterNames.APELLIDOS, ErrorNames.ERR_APELLIDOS);
		}
		
		if(!error.hasErrors()) {
			return apellido;
		}
		else {
			logger.debug(new StringBuilder("errores encontrados en el campo ")
					.append(ParameterNames.APELLIDOS).append(" ")
					.append(error.printError(ParameterNames.APELLIDOS)).toString());
			return null;
		}
	}

	public static String telefonoValidator(HttpServletRequest request, String parameter, Errors error) {
		Map<String, String[]> mapParameter = request.getParameterMap();
		String telefono = mapParameter.get(parameter)[0];
		telefono = telefono.trim();
		if(StringUtils.isEmptyOrWhitespaceOnly(telefono)) {
			error.add(parameter, ErrorNames.ERR_MANDATORY);
		}
		if(!REG_NUMBER.matcher(telefono).matches()) {
			error.add(parameter, ErrorNames.ERR_TELEPHONE);
		}
		logger.debug(new StringBuilder("errores encontrados en el campo ")
				.append(parameter).append(" ")
				.append(error.printError(parameter)).toString());
		if(!error.hasErrors()) {
			return telefono;
		}
		else {
			return null;
		}
	}
	public static String cdValidator(HttpServletRequest request, Errors error) {
		Map<String, String[]> mapParameter = request.getParameterMap();
		String codigoPostal = mapParameter.get(ParameterNames.CODIGO_POSTAL)[0];
		codigoPostal = codigoPostal.trim();
		if(StringUtils.isEmptyOrWhitespaceOnly(codigoPostal)) {
			error.add(ParameterNames.CODIGO_POSTAL, ErrorNames.ERR_MANDATORY);
		}
		if(!REG_CODPOSTAL.matcher(codigoPostal).matches()) {
			error.add(ParameterNames.CODIGO_POSTAL, ErrorNames.ERR_CDP);
		}
		logger.debug(new StringBuilder("errores encontrados en el campo ")
				.append(ParameterNames.CODIGO_POSTAL).append(" ")
				.append(error.printError(ParameterNames.CODIGO_POSTAL)).toString());
		if(!error.hasErrors()) {
			return codigoPostal;
		}
		else {
			return null;
		}
	}
	public static String emailValidator(HttpServletRequest request,  Errors error) {
		Map<String, String[]> mapParameter = request.getParameterMap();
		String email = mapParameter.get(ParameterNames.EMAIL)[0];
		email = email.trim();
		if(StringUtils.isEmptyOrWhitespaceOnly(email)) {
			error.add(ParameterNames.EMAIL, ErrorNames.ERR_MANDATORY);
		}
		if(!mail.isValid(email)) {
			error.add(ParameterNames.EMAIL, ErrorNames.ERR_EMAIL);
		}

		if(!error.hasErrors()) {
			return email;
		}
		else {
			logger.debug(new StringBuilder("errores encontrados en el campo ")
					.append(ParameterNames.EMAIL).append(" ")
					.append(error.printError(ParameterNames.EMAIL)).toString());
			return null;
		}
	}
	public static Integer integerValidator(HttpServletRequest request, String parameter, Errors error) {
		Map<String, String[]> mapParameter = request.getParameterMap();
		String number = mapParameter.get(parameter)[0];
		number.trim();
		
		Integer num = null;
		try {
			if(StringUtils.isEmptyOrWhitespaceOnly(parameter)) {
				error.add(parameter, ErrorNames.ERR_MANDATORY);
				logger.debug(new StringBuilder("errores encontrados en el campo ")
						.append(parameter).append(" ")
						.append(error.printError(parameter)).toString());
				return null;

			}
			else {
				num = Integer.valueOf(number);
			}

		}catch(NumberFormatException e) {
			logger.warn(e.getMessage(), e);
			error.add(parameter, ErrorNames.NOT_NUMBER_FORMAT);
			return null;
		}
		return num;
	}
	public static Long longValidator(HttpServletRequest request, String parameter, Errors error) {

		Map<String, String[]> mapParameter = request.getParameterMap();
		String number = mapParameter.get(parameter)[0];
		number = number.trim();
		Long num = null;
		try {
			if(StringUtils.isEmptyOrWhitespaceOnly(parameter)) {
				error.add(parameter, ErrorNames.ERR_MANDATORY);
				logger.debug(new StringBuilder("errores encontrados en el campo ")
						.append(parameter).append(" ")
						.append(error.printError(parameter)).toString());
				return null;
			}
			else {
				num = Long.valueOf(number);
			}

		}catch(NumberFormatException e) {
			logger.warn(e.getMessage(), e);
			error.add(parameter, ErrorNames.NOT_NUMBER_FORMAT);
			return null;
		}
		return num;
	}





}
