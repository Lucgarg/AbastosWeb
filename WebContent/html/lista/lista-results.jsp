<%@page import="javax.swing.event.MouseInputListener"%>
<%@ page import="java.util.*, com.abastos.model.*, com.abastos.service.*, com.abastos.market.web.util.*" %>
<%@include file= "/html/commons/usuario/header.jsp"%>	
	<section class="tiendas producto">
		<div class="tiendas2">
		<%
		List<Lista> results = (List<Lista>) request.getAttribute(AttributesNames.LISTA);
		%>
		<%
		for(Lista l : results){
			%>
		
			<div>
				
				<p>
					<a href="<%=UrlBuilder.builder(request, ViewPathsActions.LISTA_DETALLE)%><%=l.getId()%>"><%=l.getNombre()%></a>
				</p>
				<p>Fecha de creación</p>
				<p><%=l.getFechaCreacion()%></p>
			</div>
			<% 
			}
		%>
		
		</div>
		<form action="<%=UrlBuilder.builder(request, ActionNames.LISTA)%>">
		<input type="hidden" name="<%=ActionNames.ACTION%>" value="<%=ActionNames.CREAR%>">
		<input type="text" name="<%=ParameterNames.NOMBRE_LISTA%>">
		<input type="submit">
		</form>
	</section>
	<%@include file="/html/commons/usuario/footer.jsp"%>


