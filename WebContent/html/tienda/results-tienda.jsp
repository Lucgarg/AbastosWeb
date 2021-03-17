<%@page import="javax.swing.event.MouseInputListener"%>
<%@ page import="java.util.*, com.abastos.model.*, com.abastos.service.*, com.abastos.market.web.util.*" %>
<%@include file= "/html/commons/usuario/header.jsp"%>
	<section class="tiendas">
		<div class="tiendas2">
	</section>
	<section>
		<nav>
			<label class="menu" for="menu"> <input type="checkbox" />
				<div></div>
				<ul>
				
					<%List<Categoria> catServ = (List<Categoria>)request.getAttribute(AttributesNames.CATEGORIAS);%>
					<%for(Categoria cat: catServ){%>
					
					<li><a href="<%=UrlBuilder.builderMap(request, ViewPathsActions.TIENDA_ACTION_BUSCAR, cat.getId())%>"><%=cat.getNombre()%></a></li>
					<%}%>
					<form action=" <%=UrlBuilder.builder(request, ViewPathsServlet.TIENDA)%>" method="post">
					<input type="hidden" name=<%=ActionNames.ACTION%> value=<%=ActionNames.BUSCAR%>>
					
					<input type="hidden" name=<%=ParameterNames.LOCALIDAD%> value=<%=localidad%>>
						<label>Envio a domicilio</label>
						<input type="checkbox" name=<%=ParameterNames.ENVIO_DOMICILIO%> value="true">
						<h5>Categoria</h5>
						<select name=<%=ParameterNames.CATEGORIA%>>
							<option disabled selected>todas las categorias</option>
							<option value="1">Deportes</option>
							<option value="2">Alimentación</option>
							<option value="3">Juguetes</option>
							<option value="4">Droguería y salud</option>
							<option value="5">Moda y accesorios</option>
							<option value="6">Mascotas</option>
							<option value="7">Electrónica e informática</option>
							<option value="8">casa y jardín</option>
						</select>
					
						<input type="submit"
							name="buscar" value="buscar">
					</form>
				</ul>
			</label>
		</nav>
	</section>
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
	</section>
	<%@include file="/html/commons/usuario/footer.jsp"%>


