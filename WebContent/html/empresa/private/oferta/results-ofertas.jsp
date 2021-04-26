<%@ page import="java.util.*, com.abastos.model.*, com.abastos.service.*,
 com.abastos.market.web.util.*,  com.abastos.service.utils.*, com.abastos.cache.impl.*" %>
<%@include file= "/html/commons/usuario/header.jsp"%>
	<section class="block crear">
		<div class="block_second">
		<button class="Buscar crear"><a href="<%=UrlBuilder.getUrlForController(request, ControllerPath.PRECREATE, ActionNames.OFERTA, true)%>"> Crear oferta</a></button>
	</section>
	<section>
		<nav>
			
		</nav>
	</section>
	<section class="block block--results bussines">
		<%if(errores.printError(ParameterNames.ERROR) != null){%>
		<p class="error generic"><%=errores.printError(ParameterNames.ERROR)%></p>
		<%}%>
		<div class="block_second">
		<h1 id="title_section">Mis ofertas</h1>
		<%
		List<Oferta> results = (List<Oferta>) request.getAttribute(AttributesNames.OFERTAS);
		Map<Long, Producto> ofertPro=  (Map<Long,Producto>)request.getAttribute(AttributesNames.PRODUCTO_OFERTA);
		%>
		<%
		for(Oferta o : results){
			%>
		
			<div>
				<p><%=o.getNombreOferta() %></p>
				<p><%=o.getNombreTipoOfer() %></p>
				<p><%=DateUtils.getDate(o.getFechaDesde())%></p>
				<p><%=DateUtils.getTime(o.getFechaDesde())%></p>
				<p><%=DateUtils.getDate(o.getFechaHasta())%></p>
				<p><%=DateUtils.getTime(o.getFechaHasta())%></p>
				<%if(o.getIdTipoOferta() == 2){%>
				<p>numerador: <%=o.getNumerador()%></p>
				<p>denominador: <%=o.getDenominador()%></p>
				<%} if(o.getIdTipoOferta() ==3){%>
				<p>Producto que recibe la oferta: <a href="<%=UrlBuilder.getUrlForController(request, ControllerPath.PRODUCTO, ActionNames.DETALLE, true, ParameterNames.ID_PRODUCTO, String.valueOf(ofertPro.get(o.getIdProdOferta()).getId()))%>"><%=ofertPro.get(o.getIdProdOferta()).getNombre()%></a></p>
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
			
		
	</section>
	<%@include file="/html/commons/usuario/footer.jsp"%>