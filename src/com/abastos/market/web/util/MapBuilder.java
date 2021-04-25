package com.abastos.market.web.util;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.abastos.model.Producto;
import com.abastos.model.Tienda;

public class MapBuilder {
	
	public static Map<Long, String> builderMapTienProdc(List<Producto> obj, List<Tienda> secObj){
		Map<Long, String> result = new HashMap<Long, String>();
		for(Producto p: obj) {
			for(Tienda t: secObj) {
				if(p.getIdTienda() == t.getId()) {
					result.put(p.getId(), t.getNombre());
				
				}
			}
		}
		return result;
	}
	
}
