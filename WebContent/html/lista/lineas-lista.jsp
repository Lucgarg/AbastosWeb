<%@page import="javax.swing.event.MouseInputListener"%>
<%@ page import="java.util.*, com.abastos.model.*, com.abastos.service.*, com.abastos.market.web.util.*" %>
<%@include file= "/html/commons/usuario/header.jsp"%>	
<%@include file= "/html/lista/right-nav.jsp"%>
	<section class="tiendas producto">
		<div class="tiendas2">
		
		<%
		for(LineaLista l : results.getLineaLista()){
			%>
		
			<div>
				<figure>
					<img src=" <%=UrlBuilder.getUrlforImg(request, "producto/" + l.getIdProducto() + "-principal.jpg")%>" alt="">
				</figure>
				<p>
					<a href="<%=UrlBuilder.getUrlForController(request, ControllerPath.PRODUCTO, ActionNames.DETALLE, String.valueOf(l.getIdProducto()))%>"><%=l.getNombreProducto()%></a>
				</p>
				<p>Fecha de guardado</p>
				<p><%=l.getAddDate()%></p>
				<p>Precio</p>
				<p><%=l.getPrecio()%></p>
				
			</div>
			<% 
			}
		%>
		
		</div>
	
	</section>
	<%@include file="/html/commons/usuario/footer.jsp"%>


