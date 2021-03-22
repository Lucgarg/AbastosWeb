package com.abastos.market.web.model;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
	private Long id;
	private Double precioTotal;
	private Boolean aplicarDescuento;
	private Long idParticular;
	private List<LineaCarrito> lineasCarrito;
	public Carrito() {
		lineasCarrito = new ArrayList<LineaCarrito>();
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
	public List<LineaCarrito> getLineaCarrito() {
		return lineasCarrito;
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
	public void setLineaCarrito(List<LineaCarrito> lineaCarrito) {
		this.lineasCarrito = lineaCarrito;
	}
	public void add(LineaCarrito lineaCarrito) {
		lineasCarrito.add(lineaCarrito);
	}
}
