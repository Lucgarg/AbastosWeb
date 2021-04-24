
<%@ page import="java.util.*, com.abastos.model.*, com.abastos.service.*, com.abastos.market.web.util.*" %>
<%@include file= "/html/commons/usuario/header.jsp"%>	
	<section class="block block--results"style="margin-top:20%">
	 	<%if(errores.hasErrors()){%>
	<p class="error generic"><%=errores.printError(ParameterNames.ERROR)%></p>
	<%}%>
		<div class="block_second">
		<input type="checkbox">
		<h1 id="title_section">Mis compras</h1>
		<%
		List<Pedido> results = (List<Pedido>) request.getAttribute(AttributesNames.PEDIDO);
		%>
		<%
		for(Pedido p : results){
			%>
		
			<div>
				<p>
					<a href="<%=UrlBuilder.getUrlForController(request, ControllerPath.PEDIDO, ActionNames.DETALLE, true, ParameterNames.PEDIDO , String.valueOf(p.getId()))%>"><%=p.getFechaPedido()%></a>
				</p>
				<p>Precio total</p>
				<p><%=p.getPrecioTotal()%></p>
			</div>
			<% 
			}
		%>
		
		</div>
		
	</section>
	<%@include file="/html/commons/usuario/footer.jsp"%>