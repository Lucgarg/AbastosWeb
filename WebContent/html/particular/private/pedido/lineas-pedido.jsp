<%@page import="com.abastos.market.web.util.*, java.util.*, com.abastos.model.*"%>
<%@include file= "/html/commons/usuario/header.jsp"%>
<%Pedido pedido = (Pedido)request.getAttribute(AttributesNames.PEDIDO);%>
<section class="block search">
		<div class="block_second">
			<input type="checkbox">
	
		
			<h1><%=pedido.getFechaPedido()%></h1>

			<div>
				

				<div class="block_second--detail">
					<p>precio final</p>
					<p><%=pedido.getPrecioTotal()%></p>
					
					<p>Numero de productos</p>
					<p><%=pedido.getLineaPedido().size()%></p>
					
				</div>
			
			</div>
	</section>
<section class="block block--results detail">
		<%if(errores.hasErrors()){%>
		<p class="error generic"><%=errores.printError(ParameterNames.ERROR)%></p>
		<%}%>
		<div class="block_second">
			
		
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
					
			<a href="<%=UrlBuilder.getUrlForController(request, ControllerPath.VALORACION, ActionNames.BUSCAR, true, 
					ParameterNames.ID_TIENDA, String.valueOf(p.getIdTienda()), ParameterNames.PEDIDO, String.valueOf(p.getIdPedido()))%>"><button class="Buscar">valorar tienda</button></a>
			<a href="<%=UrlBuilder.getUrlForController(request, ControllerPath.VALORACION, ActionNames.BUSCAR, true,
					ParameterNames.ID_PRODUCTO, String.valueOf(p.getIdProducto()), ParameterNames.PEDIDO, String.valueOf(p.getIdPedido()))%>"><button class="Buscar">valorar producto</button></a>
			</div>
			<%}%>
		
	</section>
	
<%@include file="/html/commons/usuario/footer.jsp"%>
