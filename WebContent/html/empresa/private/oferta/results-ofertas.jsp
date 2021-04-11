<%@ page import="java.util.*, com.abastos.model.*, com.abastos.service.*, com.abastos.market.web.util.*" %>
<%@include file= "/html/commons/usuario/header.jsp"%>
	<section class="block">
		<div class="block_second">
	</section>
	<section>
		<nav>
			
		</nav>
	</section>
	<section class="block block--results">
		<%if(errores.printError(ParameterNames.ERROR) != null){%>
		<p class="error"><%=errores.printError(ParameterNames.ERROR)%></p>
		<%}%>
		<div class="block_second">
		<%
		List<Oferta> results = (List<Oferta>) request.getAttribute(AttributesNames.OFERTAS);
		%>
		<%
		for(Oferta o : results){
			%>
		
			<div>
				<p><%=o.getNombreOferta() %></p>
				<p><%=o.getNombreTipoOfer() %></p>
				<p><%=o.getFechaDesde() %></p>
				<p><%=o.getFechaHasta()%></p>
				<%if(o.getIdTipoOferta() == 2){%>
				<p>numerador: <%=o.getNumerador()%></p>
				<p>denominador: <%=o.getDenominador()%></p>
				<%} if(o.getIdTipoOferta() ==3){%>
				<p>Producto que recibe la oferta: <%=o.getNombreProdOferta()%></p>
			<%}%>
				<%if(o.getDescuentoFijo() != 0.0){%>
				<p>Descuento fijo: <%=o.getDescuentoFijo()%></p>
				<%}else{ %>
				<p>Descuento PCN: <%=o.getDescuentoPcn()%></p>
				<% 
			}%>
			</div>
			<% } %>
		</div>
			
		<button class="Buscar"><a href="<%=UrlBuilder.getUrlForController(request, ControllerPath.PRECREATE, ActionNames.OFERTA, true)%>"> Crear oferta</a></button>
	</section>
	<%@include file="/html/commons/usuario/footer.jsp"%>