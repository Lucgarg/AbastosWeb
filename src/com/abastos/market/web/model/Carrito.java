package com.abastos.market.web.model;

import java.util.HashMap;
import java.util.Map;

public class Carrito {
	private Long id;
	private Double precioTotal;
	private Boolean aplicarDescuento;
	private Long idParticular;
	
	private Map<Long, LineaCarrito> lineasCarritoMap;


	public Carrito() {
		
		lineasCarritoMap = new HashMap<Long, LineaCarrito>();
	}
	public Long getId() {
		return id;
	}
	public Double getPrecioTotal() {
		return precioTotal;
	}
	public Boolean getAplicarDescuento() {
		return aplicarDescuento;
	}
	public Long getIdParticular() {
		return idParticular;
	}

	public Map<Long, LineaCarrito> getLineasCarritoMap() {
		return lineasCarritoMap;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setPrecioTotal(Double precioTotal) {
		this.precioTotal = precioTotal;
	}
	public void setAplicarDescuento(Boolean aplicarDescuento) {
		this.aplicarDescuento = aplicarDescuento;
	}
	public void setIdParticular(Long idParticular) {
		this.idParticular = idParticular;
	}
	
	public void setLineasCarritoMap(Map<Long, LineaCarrito> lineasCarritoMap) {
		this.lineasCarritoMap = lineasCarritoMap;
	}

	public void addMap(Long idProducto, LineaCarrito lineaCarrito) {
		lineasCarritoMap.put(idProducto, lineaCarrito);
	}
}
