package com.abastos.market.web.util;

import com.abastos.model.PuntuacionTienda;

public class AttributesUtils {

	public AttributesUtils() {
	
	}
	public static final String print(int punt, int value) {
		if(punt == value) {
			return "/checked/";
		}
		else {
			return "";
		}
	}
}
