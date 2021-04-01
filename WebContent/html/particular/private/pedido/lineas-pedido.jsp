<%@page import="com.abastos.market.web.util.*, java.util.*, com.abastos.model.*"%>
<%@include file= "/html/commons/usuario/header.jsp"%>
<%Pedido pedido = (Pedido)request.getAttribute(AttributesNames.PEDIDO);%>


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
						href="<%=UrlBuilder.getUrlForController(request, ControllerPath.PRODUCTO, ActionNames.DETALLE, true, ParameterNames.ID_PRODUCTO, String.valueOf(p.getIdProducto()), ParameterNames.ID_TIENDA,String.valueOf(p.getIdTienda()))%>"><%=p.getNombreProducto()%></a>
				</p>
				
				
				
			
					<p>Numero de unidades</p>
					<p><%=p.getNumeroUnidades()%></p>
					<%if(p.getNumeroUnidades() != 1){%>
					<p>Precio unidad</p>
					<p><%=p.getPrecio()%></p>
						<%}%>
					
					<p><%=p.getPrecioFinal()%></p>
					
			<button><a href="<%=UrlBuilder.getUrlForController(request, ControllerPath.VALORACION, ActionNames.BUSCAR, true, 
					ParameterNames.ID_TIENDA, String.valueOf(p.getIdTienda()), ParameterNames.PEDIDO, String.valueOf(p.getIdPedido()))%>">valorar tienda</a></button>
			<button><a href="<%=UrlBuilder.getUrlForController(request, ControllerPath.VALORACION, ActionNames.BUSCAR, true,
					ParameterNames.ID_PRODUCTO, String.valueOf(p.getIdProducto()), ParameterNames.PEDIDO, String.valueOf(p.getIdPedido()))%>">valorar producto</a></button>
			</div>
			<%}%>
		
	</section>
	
<%@include file="/html/commons/usuario/footer.jsp"%>
