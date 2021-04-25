
<%@page
	import="com.abastos.market.web.util.*, java.util.*, com.abastos.model.*"%>
<%@include file="/html/commons/usuario/header.jsp"%>
<%Pedido pedido = (Pedido)SessionManager.get(request, AttributesNames.PEDIDO);%>
<%if(pedido.getLineaPedido().size() > 0){%>
<%@include file="/html/pedido/right-nav.jsp"%>
<section></section>
<section class="block block--results">
	<%if(errores.printError(ActionNames.SEND_EMAIL) != null){%>
	<p class="error generic"><%=errores.printError(ActionNames.SEND_EMAIL)%></p>
	<%}%>

	<%if(errores.printError(ParameterNames.ERROR) != null){%>
	<p class="error generic"><%=errores.printError(ParameterNames.ERROR)%></p>
	<%}%>
	
	<div class="block_second">

		<%
		for(LineaPedido p : pedido.getLineaPedido()){
			%>

		<div>
			<figure>
				<img
					src="<%=UrlBuilder.getUrlforImg(request, "productos/" + p.getIdProducto() + "-principal.jpg")%>" />
			</figure>
			<p>
				<a
					href="<%=UrlBuilder.getUrlForController(request, ControllerPath.PRODUCTO, ActionNames.DETALLE,true, ParameterNames.ID_PRODUCTO, String.valueOf(p.getIdProducto()), ParameterNames.ID_TIENDA,String.valueOf(p.getIdTienda()))%>"><%=p.getNombreProducto()%></a>
			</p>



			<%
					if(p.getIdOferta() != null){
					if(p.getIdTipoOferta()==1){%>
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
				<p><%=p.getNombreOferta()%></p>
				<%
					 if(p.getIdTipoOferta() == 1){
					 %>
				<p>
					-<%
					 	if(p.getDescuentoFijo() != 0.0){
					 	%>
					<%=p.getDescuentoFijo()%>
					&euro;
					<%
					 			  }else{
					 			  %>
					<%=p.getDescuentoPcn()%>
					%
					<%
					 }
					 %>
				</p>
				<%
					 	}
					 				 if(p.getIdTipoOferta() == 2){
					 	%>
				<p><%=p.getDenominador()%>
					unidad -
					<%
					 	if(p.getDescuentoFijo() != 0.0){
					 	%>
					<%=p.getDescuentoFijo()%>
					&euro;
					<%
					 			  }else{
					 			  %>
					<%=p.getDescuentoPcn()%>
					%

					<%
					 									 }
					 									 %>
				</p>
				<%
					 }
					 			 if(p.getIdTipoOferta() == 3){
					 %>
				<p>
					Compra y ahorrate del producto
					<%=p.getNombreProdOferta()%>

					<%
 					 						 			if(p.getDescuentoFijo() != 0.0){
 					 						 			%>
					<%=p.getDescuentoFijo()%>
					&euro;
					<%
					 			}else{
					 			%>
					<%=p.getDescuentoPcn()%>
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
			<p>Numero de unidades</p>
			<p><%=p.getNumeroUnidades()%></p>
			<%if(p.getNumeroUnidades() != 1){%>
			<p>Precio unidad</p>
			<p><%=p.getPrecio()%></p>
			<%}%>

			<p><%=p.getPrecioFinal()%></p>

			<button class="Buscar">
				<a
					href="<%=UrlBuilder.getUrlForController(request, ControllerPath.CARRITO, ActionNames.ELIMINAR, true, ParameterNames.ID_PRODUCTO, String.valueOf(p.getIdProducto()))%>">eliminar</a>
			</button>

		</div>
		<%}%>
		
</section>
<%if(particular != null){%>
<div class="infoPedido">
<div>
	     		<label>Direccion</label>
          		<%for(DireccionDto d: particular.getDireccionDto()){%>
          		<fieldset>
          	
          		<input type="radio" name="direccion"><p><%=new StringBuilder(d.getCalle()).append(", ").append("Número: ").append(d.getNumero())
          		.append(", ").append(d.getLocalidad()).append("(").append(d.getComunidadAutonoma())
          				.append("), ").append(d.getPais())%></p>
          		</fieldset>
          		<%}%>
</div>
<div>

<label>Introduce el número de tu tarjeta de crédito</label>
<input type="text">
</div>
</div>
<%}%>
<%}else{%>
<p
	style="position: absolute; top: 50%; right: 50%; font-size: 4rem; color: white;">El
	carrito esta vacio</p>
<%}%>
<%@include file="/html/commons/usuario/footer.jsp"%>