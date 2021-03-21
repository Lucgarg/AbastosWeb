<%@page import="javax.swing.event.MouseInputListener"%>
<%@ page import="java.util.*, com.abastos.model.*, com.abastos.service.*, com.abastos.market.web.util.*" %>
<%@include file= "/html/commons/usuario/header.jsp"%>	
	<section class="tiendas producto">
		<div class="tiendas2">
		<%
		List<Pedido> results = (List<Pedido>) request.getAttribute(AttributesNames.PEDIDO);
		%>
		<%
		for(Pedido p : results){
			%>
		
			<div>
				<p>
					<a href="<%=UrlBuilder.builder(request, ViewPathsActions.PEDIDO_ACTION_DETALLE + "&" + ParameterNames.PEDIDO + "=" + p.getId())%>"><%=p.getFechaPedido()%></a>
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