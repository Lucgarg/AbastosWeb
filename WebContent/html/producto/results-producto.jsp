<%@ page import="java.util.*, com.abastos.model.*, com.abastos.service.*, com.abastos.service.impl.*, com.abastos.market.web.util.*"%>
	<%@include file= "/html/commons/usuario/header.jsp"%>
	<%@include file= "/html/commons/producto/right-nav.jsp"%>
	<%@include file= "/html/commons/producto/left-nav.jsp"%>
	<section class="tiendas producto">
		<div class="tiendas2">
			<%
		List<Producto> results = (List<Producto>) SessionManager.get(request, AttributesNames.PRODUCTO);
		
		for(Producto p : results){
			%>

			<div>
				<figure>
					<img src="<%=UrlBuilder.builderImg(request, "productos/" + p.getId() + "-principal.jpg")%>" />
				</figure>
				<p>
					<a
						href="<%=UrlBuilder.builder(request, ViewPathsActions.PRODUCTO_ACTION_DETALLE + "&tienda=" + p.getIdTienda())%>"><%=p.getNombre()%></a>
				</p>
				
				
				
				<%if(p.getOferta() != null){
					if(p.getOferta().getIdTipoOferta()==1){%>
				<p class="precio"><%=p.getPrecio()%></p>
				<%}else{%>
				<p class="precioNoMostrado"></p>  
				<%}%>
						<div class="oferta">
						<p>oferta</p>
					 </div>
					 
					 <div class="ofertaDetalle">
					 <p><%=p.getOferta().getNombreOferta()%></p>
					 <%if(p.getOferta().getIdTipoOferta() == 1){ %>
					 	<p>-<%if(p.getOferta().getDescuentoFijo() !=0.0){%>
					 			<%=p.getOferta().getDescuentoFijo()%>
					 			&euro;
					 			  <%}else{%>
					 				<%=p.getOferta().getDescuentoPcn()%> %
					 <%}%></p>
					 	<%}
					 if(p.getOferta().getIdTipoOferta() == 2){ %>
					 	<p><%=p.getOferta().getDenominador()%>
					 	 unidad -
					 			<%if(p.getOferta().getDescuentoFijo() != 0.0){%>
					 			<%=p.getOferta().getDescuentoFijo()%>
					 			 &euro;
					 			  <%}else{%>
					 				<%=p.getOferta().getDescuentoPcn()%> %
					 				
					 <%}%>
					 </p>
					 <%}
					 if(p.getOferta().getIdTipoOferta() == 3){%>
					 	<p>Compra y ahorrate del producto
					 	<%=p.getOferta().getNombreProdOferta()%> 
					 	
					 			<%if(p.getOferta().getDescuentoFijo() != 0.0){%>
					 			<%=p.getOferta().getDescuentoFijo()%>
					 			 &euro;
					 			<%}else{%>
					 				<%=p.getOferta().getDescuentoPcn()%> %
					 <%}%></p>
					 <%}%>
					 </div>
				 <%}%>
				
					<p><%=p.getPrecioFinal()%></p>
					<form action="<%=UrlBuilder.builder(request, "pedido")%>">
					<input type="text" name="<%=ParameterNames.NUMERO_UNIDADES%>"><br>
					<input type="hidden" name="<%=ActionNames.ACTION%>" value="<%=ActionNames.ADD%>">
 					<input type="hidden" name="<%=ParameterNames.ID_PRODUCTO%>" value="<%=p.getId()%>">
 					<button type="submit" class="carritoCompra"></button>
					</form>
				<span><%=p.getValoracion()%></span>
			</div>
			<%}%>
		
		</div>
	</section>
	<%@include file="/html/commons/usuario/footer.jsp"%>