package com.abastos.market.web.util;

import java.text.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.Email;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.market.web.controllers.Errors;
import com.abastos.market.web.controllers.ParticularServlet;
import com.abastos.service.MailService;


public class ValidationUtils {
	private static Logger logger = LogManager.getLogger(ValidationUtils.class);
	private static final Pattern REG_NAME = Pattern.compile("[a-zA-Z][a-zA-Z0-9]{2,31}");
	private static final Pattern REG_NUMBER_NOT = Pattern.compile("[a-zA-Z]{3,20}");
	private static final Pattern REG_APELLIDOS = Pattern.compile("[a-zA-Z]{2,20}\\s[a-zA-Z]{0,20}");
	private static final Pattern REG_PISO = Pattern.compile("[a-zA-Z1-9._]{1,5}");
	private static final Pattern REG_TELEPHONE = Pattern.compile("^[6,8,9][0-9]{8}$|^\\\\+[0-9]{2}\\\\s[1-9][0-9]{8}$");
	private static final Pattern REG_CODPOSTAL = Pattern.compile("^\\d{5}-\\d{4}|\\d{5}|[A-Z]\\d[A-Z] \\d[A-Z]\\d$");
	private static EmailValidator mail  = EmailValidator.getInstance();
	private static final String DATE_FORMAT = "dd-MM-yyyy H:m:s";

	public ValidationUtils() {

	}


	public static String passwordValidation(Map<String, String[]> mapParameter,  Errors error) {
		if(mapParameter.get(ParameterNames.PASSWORD) == null) {
			error.add(ParameterNames.PASSWORD, ErrorNames.ERR_MANDATORY);
			return null;
		}
			String [] parametros = mapParameter.get(ParameterNames.PASSWORD);
			//validacion de que los dos capos estan cubiertos
			for(String p: parametros) {
				if(StringUtils.trimToEmpty(p) == "") {
					error.add(ParameterNames.PASSWORD, ErrorNames.ERR_PASSWORD_BLANK);
					return null;
				}
			}
			
			
			
			//validacion de que los dos campos son iguales
			if(!StringUtils.trimToEmpty(parametros[0]).equals(StringUtils.trimToEmpty(parametros[1]))){
				error.add(ParameterNames.PASSWORD, ErrorNames.ERR_PASSWORD_DIFFERENT);	
			}
			logger.debug(new StringBuilder("errores encontrados en el campo ")
					.append(ParameterNames.PASSWORD).append(" ")
					.append(error.printError(ParameterNames.PASSWORD)).toString());
			if(!error.hasErrors()) {
				return parametros[0];
			}

		
		
		return null;

	}
	public static String nameValidator(Map<String, String[]> mapParameter, String parameterName,  Errors error) {
		if(mapParameter.get(parameterName) == null) {
			error.add(parameterName, ErrorNames.ERR_MANDATORY);
			return null;
		}
		
		String nombre = mapParameter.get(parameterName)[0];
		nombre = nombre.trim();
		if(StringUtils.trimToEmpty(nombre) == "") {
			error.add(parameterName, ErrorNames.ERR_MANDATORY);
		}
		if(!REG_NAME.matcher(nombre).matches()) {
			error.add(parameterName, ErrorNames.ERR_NAME);
		}
		logger.debug(new StringBuilder("errores encontrados en el campo ")
				.append(parameterName).append(" ")
				.append(error.printError(parameterName)).toString());
		if(!error.hasErrors()) {
			return nombre;
		}
		else {
			return null;
		}
	}
	public static String pisoValidator(Map<String, String[]> mapParameter,  Errors error) {
		if(mapParameter.get(ParameterNames.PISO) == null) {
			error.add(ParameterNames.PISO, ErrorNames.ERR_MANDATORY);
			return null;
		}
		String piso = mapParameter.get(ParameterNames.PISO)[0];
		piso = piso.trim();
		if(StringUtils.trimToEmpty(piso) == "") {
			error.add(ParameterNames.PISO, ErrorNames.ERR_MANDATORY);
		}
		if(!REG_PISO.matcher(piso).matches()) {
			error.add(ParameterNames.PISO, ErrorNames.ERR_NAME);
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
	public static String numberNotValidator(Map<String, String[]> mapParameter, String parameter, Errors error) {
		if(mapParameter.get(parameter) == null) {
			error.add(parameter, ErrorNames.ERR_MANDATORY);
			return null;
		}
		String nombre = mapParameter.get(parameter)[0];
		nombre = nombre.trim();
		if(StringUtils.trimToEmpty(nombre) == "") {
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
	public static String apellidosValidator(Map<String, String[]> mapParameter, Errors error) {
		if(mapParameter.get(ParameterNames.APELLIDOS) == null) {
			error.add(ParameterNames.APELLIDOS, ErrorNames.ERR_MANDATORY);
			return null;
		}

		String apellido = mapParameter.get(ParameterNames.APELLIDOS)[0];

		apellido = apellido.trim();

		if(StringUtils.trimToEmpty(apellido) == "") {
			error.add(ParameterNames.APELLIDOS, ErrorNames.ERR_MANDATORY);
		}
		if(!REG_APELLIDOS.matcher(apellido).matches()) {
			error.add(ParameterNames.APELLIDOS, ErrorNames.ERR_NAME);
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

	public static String telefonoValidator(Map<String, String[]> mapParameter, String parameter, Errors error) {
		if(mapParameter.get(parameter) == null) {
			error.add(parameter, ErrorNames.ERR_MANDATORY);
			return null;
		}
		String telefono = mapParameter.get(parameter)[0];
		telefono = telefono.trim();
		if(StringUtils.trimToEmpty(telefono) == "") {
			error.add(parameter, ErrorNames.ERR_MANDATORY);
		}
		if(!REG_TELEPHONE.matcher(telefono).matches()) {
			error.add(parameter, ErrorNames.ERR_NAME);
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
	public static String cdValidator(Map<String, String[]> mapParameter, Errors error) {
		if(mapParameter.get(ParameterNames.CODIGO_POSTAL) == null) {
			error.add(ParameterNames.CODIGO_POSTAL, ErrorNames.ERR_MANDATORY);
			return null;
		}
		String codigoPostal = mapParameter.get(ParameterNames.CODIGO_POSTAL)[0];
		codigoPostal = codigoPostal.trim();
		if(StringUtils.trimToEmpty(codigoPostal) == "") {
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
	public static String emailValidator(Map<String, String[]> mapParameter,  Errors error) {
		if(mapParameter.get(ParameterNames.EMAIL) == null) {
			error.add(ParameterNames.EMAIL, ErrorNames.ERR_MANDATORY);
			return null;
		}
		String email = mapParameter.get(ParameterNames.EMAIL)[0];
		email = email.trim();
		if(StringUtils.trimToEmpty(email) == "") {
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
	public static Integer integerValidator(Map<String, String[]> mapParameter, String parameter, Errors error) {
		if(mapParameter.get(parameter) == null) {
			error.add(parameter, ErrorNames.ERR_MANDATORY);
			return null;
		}
		String []numberParam = mapParameter.get(parameter);
		String number = null;
		if(null != numberParam) {
			number = numberParam[0];

			number.trim();

			Integer num = null;
			if(StringUtils.trimToEmpty(number) != "") {
				try {
					if(StringUtils.trimToEmpty(parameter) == "") {
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
				}}
			return num;}
		else {
			return null;
		}
	}
	public static Double doubleValidator(Map<String, String[]> mapParameter, String parameter, Errors error) {
		if(mapParameter.get(parameter) == null) {
			error.add(parameter, ErrorNames.ERR_MANDATORY);
			return null;
		}
		String []numberParam = mapParameter.get(parameter);
		String number = null;
		if(null != numberParam) {
			number = numberParam[0];

			number.trim();

			Double num = null;
			if(StringUtils.trimToEmpty(number) != "") {
				try {
					if(StringUtils.trimToEmpty(parameter) != "") {
						error.add(parameter, ErrorNames.ERR_MANDATORY);
						logger.debug(new StringBuilder("errores encontrados en el campo ")
								.append(parameter).append(" ")
								.append(error.printError(parameter)).toString());
						return null;

					}
					else {
						num = Double.valueOf(number);
					}

				}catch(NumberFormatException e) {
					logger.warn(e.getMessage(), e);
					error.add(parameter, ErrorNames.NOT_NUMBER_FORMAT);
					return null;
				}}
			return num;}
		else {
			return null;
		}
	}
	public static Long longValidator(Map<String, String[]> mapParameter, String parameter, Errors error) {
		if(mapParameter.get(parameter) == null) {
			error.add(parameter, ErrorNames.ERR_MANDATORY);
			return null;
		}
		String []numberParam = mapParameter.get(parameter);
		String number = null;
		if(null != numberParam) {
			number = numberParam[0];
			number.trim();
			Long num = null;
			if(StringUtils.trimToEmpty(number) != "") {
				try {
					if(StringUtils.trimToEmpty(parameter) == "") {
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
				}}
			return num;
		}
		else {
			return null;}
	}

	public static Date dateValidation(HttpServletRequest request,String fecha, String parameterValue, String parameter, Errors error) {
		
		DateFormat df = new SimpleDateFormat(DATE_FORMAT);
		Date data = null;

		try {
			if(StringUtils.trimToEmpty(parameterValue) == "") {
				error.add(parameter, ErrorNames.ERR_MANDATORY);
			}
			else {
				data = df.parse(fecha);
			}
		}catch(ParseException e) {
			error.add(parameter, ErrorNames.ERR_DATE_FORMAT);
			logger.warn(e.getMessage(), e);
			logger.debug(new StringBuilder("errores encontrados en el campo ")
					.append(parameter).append(" ")
					.append(error.printError(parameter)).toString());
			return null;
		}

		return data;
	}
	/*metodo para validar que la primera fecha es menor que la segunda*/
	public static void dateOrderValidation(Date fechaDesde, Date fechaHasta, Errors error) {
		if(fechaDesde != null && fechaHasta != null) {
			if(fechaDesde.after(fechaHasta) || fechaDesde.equals(fechaHasta)) {
				error.add(ParameterNames.FECHAS, ErrorNames.ERR_DATE_ORDER_INCORRECT);
				logger.debug(new StringBuilder("errores encontrados en el campo ")
						.append(ParameterNames.FECHAS).append(" ")
						.append(error.printError(ParameterNames.FECHAS)).toString());
			}
		}


	}
	/*validacion de los campos de ofertas para verificar que solo uno de los tipos de descuento esta cubierto*/
	public static void onlyOneField(Map<String, String[]> mapParameter, String parameter1, String parameter2, Errors error ) {
		if(mapParameter.get(parameter1) == null &&  mapParameter.get(parameter2) == null) {
			error.add(ParameterNames.DESCUENTOS, ErrorNames.ERR_MANDATORY);
			
		}
		String parameterA = mapParameter.get(parameter1)[0];
		String parameterB = mapParameter.get(parameter2)[0];
		if(StringUtils.trimToEmpty(parameterA) == "" && 
				StringUtils.trimToEmpty(parameterB) == ""	) {
			error.add(ParameterNames.DESCUENTOS, ErrorNames.ERR_MANDATORY);
		}
		else if(StringUtils.trimToEmpty(parameterA) != ""
				&& StringUtils.trimToEmpty(parameterB) != "") {
			error.add(ParameterNames.DESCUENTOS, ErrorNames.ERR_ONLY_ONE_FIELD);
		}
	}
	/*validacion para comprobar si un campo esta relleno si se elige el tipo de oferta segundaUnidad
	 * o si estan sin rellenar si se elige el tipo de oferta "descuento"*/
	public static void onlyFieldEquals(HttpServletRequest request, Errors error, Integer tipOferta, Integer...valor) {
		boolean errors = false;
	
		if(2 == tipOferta) {
			errors = true;
			for(Integer t: valor) {
				if(null != t) {
					errors = false;
				}
			}
			if(errors) {
				error.add(ParameterNames.NUMBERS, ErrorNames.ERR_OFFER_FIELD_MAND);
				logger.debug(new StringBuilder("errores encontrados en el campo ")
						.append(ParameterNames.NUMBERS).append(" ")
						.append(error.printError(ParameterNames.NUMBERS)).toString());
			}
		}
		else if(1 == tipOferta) {
			for(Integer t: valor) {
				if(null != t) {
					errors = true;
				}
			}
			if(errors) {
				error.add(ParameterNames.NUMBERS, ErrorNames.ERR_FORBIDDEN_FIELD);
				logger.debug(new StringBuilder("errores encontrados en el campo ")
						.append(ParameterNames.NUMBERS).append(" ")
						.append(error.printError(ParameterNames.NUMBERS)).toString());
			}
		}

		else if(2 == tipOferta){
			orderFieldNumber(valor[0], valor[1], error);
		}

	}
	/*validacion para verificar que si se elige el tipo de oferta "compra y llevate" se selecciona el producto al que se le aplicara la oferta*/
	public static void onlyFieldEquals(HttpServletRequest request, Errors error, Integer tipOferta, Long prodOfert, Integer...valor) {
		boolean errors = false;
		
		if(3 == tipOferta) {
			if(null == prodOfert) {
				error.add(ParameterNames.PRODUCTO_OFERTA, ErrorNames.ERR_PRODUCT_OFFERT);
				logger.debug(new StringBuilder("errores encontrados en el campo ")
						.append(ParameterNames.PRODUCTO_OFERTA).append(" ")
						.append(error.printError(ParameterNames.PRODUCTO_OFERTA)).toString());
			}
			for(Integer v: valor) {
				if(null != v) {
					errors = true;
				}
			}
		}



		if(errors) {
			error.add(ParameterNames.NUMBERS, ErrorNames.ERR_FORBIDDEN_FIELD);
			logger.debug(new StringBuilder("errores encontrados en el campo ")
					.append(ParameterNames.NUMBERS).append(" ")
					.append(error.printError(ParameterNames.NUMBERS)).toString());
		}


	}
	/*validacion para verificar que un numero es mayor que otro cuando esta condicion no se puede producir*/
	public static void orderFieldNumber(Integer num, Integer num2, Errors error) {
		if(num >= num2) {
			error.add(ParameterNames.NUMBERS, ErrorNames.ERR_ORDER_FIELD);

		}
		if(num == num2) {
			error.add(ParameterNames.NUMBERS, ErrorNames.ERR_ORDER_EQUALS);
		}
		logger.debug(new StringBuilder("errores encontrados en el campo ")
				.append(ParameterNames.NUMBERS).append(" ")
				.append(error.printError(ParameterNames.NUMBERS)).toString());
	}


}
