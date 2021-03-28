package com.abastos.market.web.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Errors {
	Map<String, List<String>> errors = null;
	public Errors() {
		errors = new HashMap<String, List<String>>();
	}
	
	public void add(String err, String errName) {
		List<String> listErr = errors.get(err);
		if(listErr == null) {
			listErr = new ArrayList<String>();
		}
		errors.get(err).add(errName);
	}
	
	public boolean hasErrors() {
		if(errors.isEmpty()) {
			return true;
		}

			return false;
	}

}
