package com.abastos.market.web.util;

public class ErrorNames {

	public ErrorNames() {
		
	}
	public static final String ERR_PASSWORD_BLANK = "Es necesario rellenar los dos campos";
	public static final String ERR_PASSWORD_DIFFERENT ="No coinciden";
	public static final String ERR_MANDATORY = "campo obligatorio";
	public static final String ERR_NAME ="formato no adecuado";
	public static final String ERR_CDP= "codigo postal no cumple con el formato";
	public static final String ERR_EMAIL="email no cumple con el formato";
	public static final String NOT_NUMBER_FORMAT ="no puede contener letras";
	public static final String USER_NOT_FOUND ="el usuario no se encuentra";
	public static final String ERR_GENERIC = "ha sucedido un error";
	public static final String ERR_SEND_EMAIL = "error al enviar el email de confirmación";
	public static final String ERR_INCORRECT_LOGIN =  "usuario o password incorrecta";
	public static final String ERR_LIMIT_CREATION_SHOP = "Lo sentimos, ha alcanzado el limite de tiendas creadas";
	public static final String ERR_LIMIT_CREATION_PRODUCTS = "Lo sentimos, ha alcanzado el limite de producto creados";
	public static final String ERR_NOT_USER_LOG = "Para realizar su pedido, proceda a iniciar sesión";
	public static final String ERR_DATE_FORMAT = "Formato de fecha incorrecto";
	public static final String ERR_DATE_ORDER_INCORRECT = "Las fechas estan desordenadas o son identicas";
	public static final String ERR_ONLY_ONE_FIELD ="Unicamente uno de los campos puede estar relleno";
	public static final String ERR_ORDER_FIELD = "El primer campo no puede ser mayor al segundo";
	public static final String ERR_ORDER_EQUALS = "Los campos no pueden ser iguales";
	public static final String ERR_OFFER_FIELD_MAND = "Con este tipo de oferta es necesario rellenar estos campos";
	public static final String ERR_PRODUCT_OFFERT = "Con este tipo de oferta es necesario seleccionar un producto";
	public static final String ERR_FORBIDDEN_FIELD = "Con este tipo de oferta no se pueden cubrir estos campos";
	public static final String ERR_IN_FIELD = "Revisa los campos, se han producido errores";
}