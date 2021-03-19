<%@page import="javax.swing.event.MouseInputListener"%>
<%@ page import="java.util.*, com.abastos.model.*, com.abastos.service.*, com.abastos.market.web.util.*" %>
<%@include file= "/html/commons/usuario/header.jsp"%>
<%@include file= "/html/commons/tienda/nav.jsp"%>	
	<section class="tiendas producto">
		<div class="tiendas2">
		<%
		List<Tienda> results = (List<Tienda>) request.getAttribute(AttributesNames.RESULTS_TIENDA);
		%>
		<%
		for(Tienda t : results){
			%>
		
			<div>
				<figure>
					<img src=" <%=UrlBuilder.builderImg(request, "tiendas/" + t.getId() + "-principal.jpg")%>" alt="">
				</figure>
				<p>
					<a href="<%=UrlBuilder.builder(request, ViewPathsActions.TIENDA_ACTION_DETALLE)%><%=t.getId()%>"><%=t.getNombre()%></a>
				</p>
				<p><%=t.getDireccionDto().getLocalidad()%></p>
				<span><%=t.getPuntuacionMedia().getValoracionMedia()%></span>
			</div>
			<% 
			}
		%>
		
		</div>
		<%if(empresa != null){%>
		<button><a href="<%=UrlBuilder.builder(request, ViewPathsActions.PRECREATE_ACTION_TIENDA)%>"> Crear producto</a></button>
		<%}%>
	</section>
	<%@include file="/html/commons/usuario/footer.jsp"%>


