package com.abastos.market.web.util;

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
