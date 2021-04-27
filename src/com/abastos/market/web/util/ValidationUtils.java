package com.abastos.market.web.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.DoubleValidator;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.IntegerValidator;
import org.apache.commons.validator.routines.LongValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.abastos.market.web.controllers.Errors;


/**
 * @author Admin
 *
 */
public class ValidationUtils {
	private static Logger logger = LogManager.getLogger(ValidationUtils.class);
	private static final Pattern REG_NAME = Pattern.compile("(\\s*[0-9]{0,10}[a-zA-Z]{2,20}[0-9]{0,10}){1,5}");
	private static final Pattern REG_NUMBER_NOT = Pattern.compile("(\\s*[a-zA-Z]{2,20}){1,5}");
	private static final Pattern REG_APELLIDOS = Pattern.compile("(\\s*[a-zA-Z]{2,20}){1,5}");
	private static final Pattern REG_PISO = Pattern.compile("[1-9]{0,2}[a-zA-Z]{1,5}");
	private static final Pattern REG_TELEPHONE = Pattern.compile("^[6,8,9][0-9]{8}$|^\\\\+[0-9]{2}\\\\s[1-9][0-9]{8}$");
	private static final Pattern REG_CODPOSTAL = Pattern.compile("^\\d{5}-\\d{4}|\\d{5}|[A-Z]\\d[A-Z] \\d[A-Z]\\d$");
	private static EmailValidator mail  = EmailValidator.getInstance();
	private static DoubleValidator doubleVald = DoubleValidator.getInstance();
	private static IntegerValidator intVald = IntegerValidator.getInstance();
	private static LongValidator longVald = LongValidator.getInstance();
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
		if(error.printError(ParameterNames.PASSWORD) == null) {
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
		else if(!REG_NAME.matcher(nombre).matches()) {
			error.add(parameterName, ErrorNames.ERR_NAME);
		}

		if(error.printError(parameterName) == null) {
			return nombre;
		}
		else {
			logger.debug(new StringBuilder("errores encontrados en el campo ")
					.append(parameterName).append(" ")
					.append(error.printError(parameterName)).toString());
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
		if(error.printError(ParameterNames.PISO) == null) {
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
	
		if(error.printError(parameter) == null) {
			return nombre;
		}
		else {
			logger.debug(new StringBuilder("errores encontrados en el campo ")
					.append(parameter).append(" ")
					.append(error.printError(parameter)).toString());
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

		if(error.printError(ParameterNames.APELLIDOS) == null) {
			return apellido;
		}
		else {
			logger.debug(new StringBuilder("errores encontrados en el campo ")
					.append(ParameterNames.APELLIDOS).append(" ")
					.append(error.printError(ParameterNames.APELLIDOS)).toString());
			return null;
		}
	}

	public static String telefonoValidator(Map<String, String[]> mapParameter, Errors error, String...param) {
		if(mapParameter.get(param[0]) == null && mapParameter.get(param[1]) == null) {
			error.add(ParameterNames.CONTACTO, ErrorNames.ERR_MANDATORY);
			return null;
			
		}
		if(StringUtils.trimToEmpty(mapParameter.get(param[0])[0]) == "" 
				&& StringUtils.trimToEmpty(mapParameter.get(param[1])[0]) == "") {
			error.add(ParameterNames.CONTACTO, ErrorNames.ERR_MANDATORY);
			return null;
		}
		for(String s: param) {
		String telefono = mapParameter.get(s)[0];
		telefono = telefono.trim();
	
		if(!REG_TELEPHONE.matcher(telefono).matches() && StringUtils.trimToEmpty(telefono) != "") {
			error.add(s, ErrorNames.ERR_NAME);
		}
		
		if(error.printError(s) == null) {
			return telefono;
		}
		else {
			logger.debug(new StringBuilder("errores encontrados en el campo ")
					.append(s).append(" ")
					.append(error.printError(s)).toString());
		}

		}
		return null;
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
		if(error.printError(ParameterNames.CODIGO_POSTAL) == null) {
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

		if(error.printError(ParameterNames.EMAIL) == null) {
			return email;
		}
		else {
			logger.debug(new StringBuilder("errores encontrados en el campo ")
					.append(ParameterNames.EMAIL).append(" ")
					.append(error.printError(ParameterNames.EMAIL)).toString());
			return null;
		}
	}


	public static Integer integerValidator(Map<String, String[]> mapParameter, String parameter, Errors error, Integer minValue, Integer maxValue, Boolean mandatory) {
		if(mapParameter.get(parameter) == null && mandatory ==true) {
			error.add(parameter, ErrorNames.ERR_MANDATORY);
			return null;
		}
		String []numberParam = mapParameter.get(parameter);
		String number = null;

		if(numberParam != null) {
			number = numberParam[0];
		}
		Integer num = null;
		if(StringUtils.trimToEmpty(number) == "" && mandatory ==true) {
			error.add(parameter, ErrorNames.ERR_MANDATORY);
			logger.debug(new StringBuilder("errores encontrados en el campo ")
					.append(parameter).append(" ")
					.append(error.printError(parameter)).toString());
			return null;

		}
		else if(StringUtils.trimToEmpty(number) != ""){


			try {
				num = Integer.valueOf(number);
			}catch(NumberFormatException e) {
				logger.warn(e.getMessage(), e);
				error.add(parameter, ErrorNames.NOT_NUMBER_FORMAT);
				return null;
			}
			if(!intVald.isInRange(num, minValue, maxValue)) {
				error.add(parameter, ErrorNames.ERR_NUMBER_LIMIT);
				logger.debug(new StringBuilder("errores encontrados en el campo ")
						.append(parameter).append(" ")
						.append(error.printError(parameter)).toString());
				return null;
			}
			return num;
		}



		return num;
	}
	public static Double doubleValidator(Map<String, String[]> mapParameter, String parameter, Errors error, Double minValue, Double maxValue, Boolean mandatory) {
		if(mapParameter.get(parameter) == null && mandatory ==true) {
			error.add(parameter, ErrorNames.ERR_MANDATORY);
			return null;
		}
		String []numberParam = mapParameter.get(parameter);
		String number = null;
		if(numberParam != null) {
			number = numberParam[0];
		}
		Double num = null;
		if(StringUtils.trimToEmpty(number) == "" && mandatory ==true) {
			error.add(parameter, ErrorNames.ERR_MANDATORY);
			logger.debug(new StringBuilder("errores encontrados en el campo ")
					.append(parameter).append(" ")
					.append(error.printError(parameter)).toString());
			return null;

		}
		else if(StringUtils.trimToEmpty(number) != ""){
			try {
	
				num = Double.valueOf(number.replace(',', '.'));

			}catch(NumberFormatException e) {
				logger.warn(e.getMessage(), e);
				error.add(parameter, ErrorNames.NOT_NUMBER_FORMAT);
				return null;
			}
			if(!doubleVald.isInRange(num, minValue, maxValue)) {
				error.add(parameter, ErrorNames.ERR_NUMBER_LIMIT);
				logger.debug(new StringBuilder("errores encontrados en el campo ")
						.append(parameter).append(" ")
						.append(error.printError(parameter)).toString());
				return null;
			}
			return num;
		}

		return num;


	}
	public static Long longValidator(Map<String, String[]> mapParameter, String parameter, Errors error, Long minValue, Long maxValue, Boolean mandatory) {
		if(mapParameter.get(parameter) == null && mandatory ==true) {
			error.add(parameter, ErrorNames.ERR_MANDATORY);
			return null;
		}
		String []numberParam = mapParameter.get(parameter);
		String number = null;
		if(numberParam != null) {
			number = numberParam[0];
		}
		Long num = null;
		if(StringUtils.trimToEmpty(number) == "" && mandatory ==true) {
			error.add(parameter, ErrorNames.ERR_MANDATORY);
			logger.debug(new StringBuilder("errores encontrados en el campo ")
					.append(parameter).append(" ")
					.append(error.printError(parameter)).toString());
			return null;

		}
		else if(StringUtils.trimToEmpty(number) != ""){
			try {
				num = Long.valueOf(number);
				
			}catch(NumberFormatException e) {
				logger.warn(e.getMessage(), e);
				error.add(parameter, ErrorNames.NOT_NUMBER_FORMAT);
				return null;
			}
			if(!longVald.isInRange(num, minValue, maxValue)) {
				error.add(parameter, ErrorNames.ERR_NUMBER_LIMIT);
				logger.debug(new StringBuilder("errores encontrados en el campo ")
						.append(parameter).append(" ")
						.append(error.printError(parameter)).toString());
				return null;
		}
			return num;
		}
		return num;
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
			if(fechaDesde.before(new Date()) || fechaHasta.before(new Date())) {
				error.add(ParameterNames.FECHAS, ErrorNames.ERR_DATE_BREAK_LIMIT);
				logger.debug(new StringBuilder("errores encontrados en el campo ")
						.append(ParameterNames.FECHAS).append(" ")
						.append(error.printError(ParameterNames.FECHAS)).toString());
			}

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
			logger.debug(new StringBuilder("errores encontrados en el campo ")
					.append(ParameterNames.DESCUENTOS).append(" ")
					.append(error.printError(ParameterNames.DESCUENTOS)).toString());
			logger.debug(new StringBuilder("errores encontrados en el campo ")
					.append(ParameterNames.NUMBERS).append(" ")
					.append(error.printError(ParameterNames.NUMBERS)).toString());
			

		}
		else {
		String parameterA = mapParameter.get(parameter1)[0];
		String parameterB = mapParameter.get(parameter2)[0];
		if(StringUtils.trimToEmpty(parameterA) == "" && 
				StringUtils.trimToEmpty(parameterB) == ""	) {
			error.add(ParameterNames.DESCUENTOS, ErrorNames.ERR_MANDATORY);
			logger.debug(new StringBuilder("errores encontrados en el campo ")
					.append(ParameterNames.DESCUENTOS).append(" ")
					.append(error.printError(ParameterNames.DESCUENTOS)).toString());
		}
		else if(StringUtils.trimToEmpty(parameterA) != ""
				&& StringUtils.trimToEmpty(parameterB) != "") {
			error.add(ParameterNames.DESCUENTOS, ErrorNames.ERR_ONLY_ONE_FIELD);
			logger.debug(new StringBuilder("errores encontrados en el campo ")
					.append(ParameterNames.DESCUENTOS).append(" ")
					.append(error.printError(ParameterNames.DESCUENTOS)).toString());
		}
		}
	}
	/*validacion para comprobar si un campo esta relleno si se elige el tipo de oferta segundaUnidad
	 * o si estan sin rellenar si se elige el tipo de oferta "descuento"*/
	public static void onlyFieldEquals(Long idProdOferta, Errors error, Integer tipOferta, Integer...valor) {
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
			else if(idProdOferta != null) {
				error.add(ParameterNames.NUMBERS, ErrorNames.ERR_INCORRECT_SELECTION);
				logger.debug(new StringBuilder("errores encontrados en el campo ")
						.append(ParameterNames.NUMBERS).append(" ")
						.append(error.printError(ParameterNames.NUMBERS)).toString());
			}
			else {
				orderFieldNumber(valor[0], valor[1], error);
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
			else if(idProdOferta != null) {
				error.add(ParameterNames.NUMBERS, ErrorNames.ERR_INCORRECT_SELECTION);
				logger.debug(new StringBuilder("errores encontrados en el campo ")
						.append(ParameterNames.NUMBERS).append(" ")
						.append(error.printError(ParameterNames.NUMBERS)).toString());
			}
		}



	}
	/*validacion para verificar que si se elige el tipo de oferta "compra y llevate" se selecciona el producto al que se le aplicara la oferta*/
	public static void onlyFieldEquals(Errors error, Integer tipOferta, Long prodOfert, Integer...valor) {
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
		if(error.printError(ParameterNames.NUMBERS) != null) {
			logger.debug(new StringBuilder("errores encontrados en el campo ")
					.append(ParameterNames.NUMBERS).append(" ")
					.append(error.printError(ParameterNames.NUMBERS)).toString());
		}
	}


}
