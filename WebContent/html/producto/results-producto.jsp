<%@ page
	import="java.util.*, com.abastos.model.*, com.abastos.service.*, com.abastos.service.impl.*, com.abastos.market.web.util.*"%>
<%@include file="/html/commons/usuario/header.jsp"%>
<%@include file="/html/commons/producto/right-nav.jsp"%>
<%@include file="/html/commons/producto/left-nav.jsp"%>
<section class="tiendas producto">
		<%if(errores.hasErrors()){%>
	<p class="error"><%=errores.printError(ParameterNames.ERROR)%></p>
	<%}%>
	<div class="tiendas2">
		<%
		List<Producto> results = (List<Producto>) request.getAttribute(AttributesNames.PRODUCTO);
		Map<Long,String> resultsTienda = (Map<Long, String>) request.getAttribute(AttributesNames.TIENDA);
		for(Producto p : results){
			%>

		<div>
			<figure>
				<img
					src="<%=UrlBuilder.getUrlforImg(request, "productos/" + p.getId() + "-principal.jpg")%>" />
			</figure>
			<p>
				<a
					href="<%=UrlBuilder.getUrlForController(request, ControllerPath.PRODUCTO, ActionNames.DETALLE, true, ParameterNames.ID_PRODUCTO, String.valueOf(p.getId()))%>"><%=p.getNombre()%></a>
			</p>
			<%
				if(resultsTienda != null){
				%>
			<p><%=resultsTienda.get(p.getId())%></p>
			<%
				}
				%>

			<%
								if(p.getOferta() != null){
																	if(p.getOferta().getIdTipoOferta()==1){
								%>
			<p class="precio"><%=p.getPrecio()%></p>
			<%
				}else{
				%>
			<p class="precioNoMostrado"></p>
			<%
  				}
  				%>
			<div class="oferta">
				<p>oferta</p>
			</div>

			<div class="ofertaDetalle">
				<p><%=p.getOferta().getNombreOferta()%></p>
				<%
					 if(p.getOferta().getIdTipoOferta() == 1){
					 %>
				<p>
					-<%
					 	if(p.getOferta().getDescuentoFijo() !=0.0){
					 	%>
					<%=p.getOferta().getDescuentoFijo()%>
					&euro;
					<%
					 			  }else{
					 			  %>
					<%=p.getOferta().getDescuentoPcn()%>
					%
					<%
					 }
					 %>
				</p>
				<%
					 	}
					 				 				 if(p.getOferta().getIdTipoOferta() == 2){
					 	%>
				<p><%=p.getOferta().getDenominador()%>
					unidad -
					<%
					 	if(p.getOferta().getDescuentoFijo() != 0.0){
					 	%>
					<%=p.getOferta().getDescuentoFijo()%>
					&euro;
					<%
					 			  }else{
					 			  %>
					<%=p.getOferta().getDescuentoPcn()%>
					%

					<%
					 									 }
					 									 %>
				</p>
				<%
					 }
					 			 			 if(p.getOferta().getIdTipoOferta() == 3){
					 %>
				<p>
					Compra y ahorrate del producto
					<%=p.getOferta().getNombreProdOferta()%>

					<%
 					 						 			if(p.getOferta().getDescuentoFijo() != 0.0){
 					 						 			%>
					<%=p.getOferta().getDescuentoFijo()%>
					&euro;
					<%
					 			}else{
					 			%>
					<%=p.getOferta().getDescuentoPcn()%>
					%
					<%
					 }
					 %>
				</p>
				<%
					 }
					 %>
			</div>
			<%
				 }
				 %>

			<p><%=p.getPrecioFinal()%></p>
			<%
					if(empresa == null){
					%>
			<form>
				<input type="text" name="<%=ParameterNames.NUMERO_UNIDADES%>"><br>
				<button type="button" class="carritoCompra" name="<%=p.getId()%>"></button>
			</form>
			<%
					}
					%>
			<span><%=p.getValoracion()%></span>
		</div>
		<%
			}
			%>

	</div>
	<%
			if(empresa != null){
			%>
	<button class="Buscar">
		<a
			href="<%=UrlBuilder.getUrlForController(request, ControllerPath.PRECREATE, ActionNames.PRODUCTO, true)%>">
			Crear producto</a>
	</button>
	<%}%>
</section>

<%@include file="/html/commons/usuario/footer.jsp"%>