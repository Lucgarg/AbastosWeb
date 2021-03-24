<%@page import="com.abastos.market.web.util.*, java.util.*, com.abastos.model.*"%>
<%@include file= "/html/commons/usuario/header.jsp"%>
<%Pedido pedido = (Pedido)request.getAttribute(AttributesNames.PEDIDO);%>

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
				
					
					<p><%=p.getPrecioFinal()%></p>
					
			<button><a href="<%=UrlBuilder.getUrlForController(request, ViewPaths.VALORACION, AttributesNames.TIENDA,  String.valueOf(p.getIdTienda()))%>">valorar tienda</a></button>
			<button><a href="<%=UrlBuilder.getUrlForController(request, ViewPaths.VALORACION, AttributesNames.PRODUCTO,  String.valueOf(p.getIdProducto()))%>">valorar producto</a></button>
			</div>
			<%}%>
		
	</section>
	
<%@include file="/html/commons/usuario/footer.jsp"%>
