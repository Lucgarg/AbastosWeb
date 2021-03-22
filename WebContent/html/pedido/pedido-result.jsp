  <%@page import="com.abastos.market.web.util.*, java.util.*, com.abastos.model.*"%>
	<%@include file= "/html/commons/usuario/header.jsp"%>
<section class="tiendas producto">
		<div class="tiendas2">
			<%
		Pedido pedido = null;
			pedido = (Pedido)request.getAttribute(AttributesNames.PEDIDO);
			if(pedido == null){
			pedido = (Pedido)SessionManager.get(request, AttributesNames.PEDIDO);
			}
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
																if(p.getIdOferta() != 0){
																			if(p.getIdTipoOferta()==1){
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
					 <p><%=p.getNombreOferta()%></p>
					 <%
					 if(p.getIdTipoOferta() == 1){
					 %>
					 	<p>-<%
					 	if(p.getDescuentoFijo() !=0.0){
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
					 	if(p.getDescuentoFijo() != 0.0){
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
 					 						 			if(p.getDescuentoFijo() != 0.0){
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
				
					<p><%=p.getPrecioFinal()%></p>
					
					<%
										if(pedido.getIdEstado() == 'C'){
										%>
				<a href=<%=UrlBuilder.getUrl(request, ViewPaths.VALORACION + "?" + AttributesNames.TIENDA +  "=" + p.getIdTienda() +  "&" + AttributesNames.PEDIDO + "=" + pedido.getId())%>><button type="button">Valorar tienda</button></a><br>
				<a href=<%=UrlBuilder.getUrl(request, ViewPaths.VALORACION + "?" + AttributesNames.PRODUCTO +  "=" + p.getIdProducto() +  "&" + AttributesNames.PEDIDO + "=" + pedido.getId())%>><button type="button">Valorar producto</button></a>
				
				<%}%>
				
			</div>
			<%}%>
			
		</div>
	</section>
<%@include file="/html/commons/usuario/footer.jsp"%>