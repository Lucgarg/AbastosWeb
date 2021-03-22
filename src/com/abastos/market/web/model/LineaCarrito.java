package com.abastos.market.web.model;

import java.util.Date;

public class LineaCarrito {
	private Integer numeroUnidades;
	private Date addDate;
	private String nombreProducto;
	private Double precio;
	private Long idOferta;
	private Double descuentoPcn;
	private Double descuentoFijo;
	private Integer numerador;
	private Integer denominador;
	private Integer idTipoOferta;
	private Long idProdOferta;
	private String nombreProdOferta;
	private Double precioFinal;
	private String nombreOferta;
	private Long idTienda;
	private Long idProducto;
	public LineaCarrito() {
	
	}
	public Integer getNumeroUnidades() {
		return numeroUnidades;
	}
	public Date getAddDate() {
		return addDate;
	}
	public String getNombreProducto() {
		return nombreProducto;
	}
	public Double getPrecio() {
		return precio;
	}
	public Long getIdOferta() {
		return idOferta;
	}
	public Double getDescuentoPcn() {
		return descuentoPcn;
	}
	public Double getDescuentoFijo() {
		return descuentoFijo;
	}
	public Integer getNumerador() {
		return numerador;
	}
	public Integer getDenominador() {
		return denominador;
	}
	public Integer getIdTipoOferta() {
		return idTipoOferta;
	}
	public Long getIdProdOferta() {
		return idProdOferta;
	}
	public String getNombreProdOferta() {
		return nombreProdOferta;
	}
	public Double getPrecioFinal() {
		return precioFinal;
	}
	public String getNombreOferta() {
		return nombreOferta;
	}
	public Long getIdTienda() {
		return idTienda;
	}
	public void setNumeroUnidades(Integer numeroUnidades) {
		this.numeroUnidades = numeroUnidades;
	}
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public void setIdOferta(Long idOferta) {
		this.idOferta = idOferta;
	}
	public void setDescuentoPcn(Double descuentoPcn) {
		this.descuentoPcn = descuentoPcn;
	}
	public void setDescuentoFijo(Double descuentoFijo) {
		this.descuentoFijo = descuentoFijo;
	}
	public void setNumerador(Integer numerador) {
		this.numerador = numerador;
	}
	public void setDenominador(Integer denominador) {
		this.denominador = denominador;
	}
	public void setIdTipoOferta(Integer idTipoOferta) {
		this.idTipoOferta = idTipoOferta;
	}
	public void setIdProdOferta(Long idProdOferta) {
		this.idProdOferta = idProdOferta;
	}
	public void setNombreProdOferta(String nombreProdOferta) {
		this.nombreProdOferta = nombreProdOferta;
	}
	public void setPrecioFinal(Double precioFinal) {
		this.precioFinal = precioFinal;
	}
	public void setNombreOferta(String nombreOferta) {
		this.nombreOferta = nombreOferta;
	}
	public void setIdTienda(Long idTienda) {
		this.idTienda = idTienda;
	}
	public Long getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

}
