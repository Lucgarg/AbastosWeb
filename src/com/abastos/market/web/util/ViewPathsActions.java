package com.abastos.market.web.util;

public class ViewPathsActions {

	public ViewPathsActions() {
		
	}
	public static final String TIENDA_ACTION_BUSCAR =  "tienda?action=buscar";
	public static final String TIENDA_ACTION_DETALLE =  "tienda?action=detalle&tienda=";
	public static final String PRECREATE_ACTION_EMPRESA = 	"precreate?action=oferta&empresa=";
	public static final String PRECREATE_ACTION_INDEX = "precreate?action=index";
	public static final String PRECREATE_ACTION_PRODUCTO = "precreate?action=producto&empresa=";
	public static final String PRECREATE_ACTION_REGISTRO_EMPRESA = "precreate?action=registrar&tipUsuario=empresa";
	public static final String PRECREATE_ACTION_REGISTRO_PARTICULAR = "precreate?action=registrar&tipUsuario=particular";
	public static final String PRECREATE_ACTION_TIENDA = 	"precreate?action=empresa&empresa=";
	public static final String PRODUCTO_ACTION_BUSCAR = "producto?action=buscar";
	public static final String PRODUCTO_ACTION_DETALLE = "producto?action=detalle&producto=";
	public static final String PARTICULAR_ACTION_REGISTRO = "particular?action=registrar";
	public static final String OFERTA_ACTION_BUSCAR = "oferta?action=buscar&empresa=";
	public static final String EMPRESA_ACTION_BUSCAR = "empresa?action=buscar&empresa=";
	public static final String EMPRESA_ACTION_REGISTRAR = "empresa?action=registrar";
	public static final String PEDIDO_ACTION_DETALLE = "/pedido?action=detalle";
}
