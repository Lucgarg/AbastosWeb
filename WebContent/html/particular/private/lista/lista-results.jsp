<%@page import="javax.swing.event.MouseInputListener"%>
<%@ page import="java.util.*, com.abastos.model.*, com.abastos.service.*, com.abastos.market.web.util.*" %>
<%@include file= "/html/commons/usuario/header.jsp"%>
	
	<section class="block block--results" style="margin-top:20%;">
		<%if(errores.hasErrors()){%>
	<p class="error generic"><%=errores.printError(ParameterNames.ERROR)%></p>
	<%}%>
		<div class="block_second">
		
			<h1 id="title_section">Mis listas</h1>
		
		<%
		List<Lista> results = (List<Lista>) request.getAttribute(AttributesNames.LISTA);
		%>
		<%
		for(Lista l : results){
			%>
		
			<div>
				
				<p>
					<a href="<%=UrlBuilder.getUrlForController(request, ControllerPath.LISTA, ActionNames.DETALLE, true, ParameterNames.LISTA, String.valueOf(l.getId()))%>"><%=l.getNombre()%></a>
				</p>
				<p>Fecha de creación</p>
				<p><%=l.getFechaCreacion()%></p>
			</div>
			<%
			}
			%>
		
		</div>
		<form action="<%=UrlBuilder.getUrl(request, ControllerPath.LISTA)%>" method="post">
		<input type="hidden" name="<%=ActionNames.ACTION%>" value="<%=ActionNames.CREAR%>">
		<input type="text" name="<%=ParameterNames.NOMBRE_LISTA%>">
		<button type="submit" class="Buscar">crear lista</button>
		</form>
	</section>
	<%@include file="/html/commons/usuario/footer.jsp"%>


