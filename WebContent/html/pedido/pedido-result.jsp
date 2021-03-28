 <%@page import="com.abastos.market.web.util.*, java.util.*, com.abastos.model.*"%>
<%@include file= "/html/commons/usuario/header.jsp"%>
<%Pedido pedido = (Pedido)SessionManager.get(request, AttributesNames.PEDIDO);%>
<%if(pedido.getLineaPedido().size() > 0){%>
<%@include file= "/html/pedido/right-nav.jsp"%>
<section class="tiendas producto">
		<div class="tiendas2">
			
			<%
		for(LineaPedido p : pedido.getLineaPedido()){
			%>

			<div>
				<figure>
					<img src="<%=UrlBuilder.getUrlforImg(request, "productos/" + p.getIdProducto() + "-principal.jpg")%>" />
				</figure>
				<p>
					<a
						href="<%=UrlBuilder.getUrlForController(request, ControllerPath.PRODUCTO, ActionNames.DETALLE, ParameterNames.ID_PRODUCTO, String.valueOf(p.getIdProducto()), ParameterNames.ID_TIENDA,String.valueOf(p.getIdTienda()))%>"><%=p.getNombreProducto()%></a>
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
					 	<p>-<%
					 	if(p.getDescuentoFijo() != null){
					 	%>
					 			<%=p.getDescuentoFijo()%>
					 			&euro;
					 			  <%
					 			  }else{
					 			  %>
					 				<%=p.getDescuentoPcn()%> %
					 <%
					 }
					 %></p>
					 	<%
					 	}
					 				 if(p.getIdTipoOferta() == 2){
					 	%>
					 	<p><%=p.getDenominador()%>
					 	 unidad -
					 			<%
					 	if(p.getDescuentoFijo() != null){
					 	%>
					 			<%=p.getDescuentoFijo()%>
					 			 &euro;
					 			  <%
					 			  }else{
					 			  %>
					 				<%=p.getDescuentoPcn()%> %
					 				
					 <%
					 									 }
					 									 %>
					 </p>
					 <%
					 }
					 			 if(p.getIdTipoOferta() == 3){
					 %>
					 	<p>Compra y ahorrate del producto
					 	<%=p.getNombreProdOferta()%> 
					 	
					 			<%
 					 						 			if(p.getDescuentoFijo() != null){
 					 						 			%>
					 			<%=p.getDescuentoFijo()%>
					 			 &euro;
					 			<%
					 			}else{
					 			%>
					 				<%=p.getDescuentoPcn()%> %
					 <%
					 }
					 %></p>
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
					
			<button><a href="<%=UrlBuilder.getUrlForController(request, ControllerPath.CARRITO, ActionNames.ELIMINAR, ParameterNames.ID_PRODUCTO, String.valueOf(p.getIdProducto()))%>">eliminar</a></button>
				
			</div>
			<%}%>
		
	</section>
	<%}else{%>
	<p style="position:absolute; top:50%; right:50%; font-size:4rem; color:white;">El carrito esta vacio</p>
	<%}%>
<%@include file="/html/commons/usuario/footer.jsp"%>