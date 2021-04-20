
<%@page
 import="com.abastos.market.web.util.*, com.abastos.cache.*, 
 com.abastos.service.utils.*, java.util.*, com.abastos.model.*, com.abastos.cache.impl.*"%>


			<%
	if (empresa != null) {
	%>
	<section class="block crear">
		<div class="block_second">
	<button class="Buscar crear">
		<a
			href="<%=UrlBuilder.getUrlForController(request, ControllerPath.PRECREATE, ActionNames.EMPRESA, true)%>">
			Crear tienda</a>
	</button>
	<%}else{%>
	<section class="block search">
		<div class="block_second">
		<%}%>
	</section>
<section id="menuSearch">
		<nav>
		
			<label class="menu" for="menu"> <input type="checkbox" />
				<div></div>
				
				<ul>
						<button class="Buscar"><a href="<%=UrlBuilder.getUrlForController(request, ControllerPath.PRECREATE, ActionNames.INICIO, ActionNames.RE_INICIO, "true")%>">
			Otras localidades
			</a></button>
					<%
					
					List<Categoria> catServ=  (List<Categoria>)request.getAttribute(AttributesNames.CATEGORIAS);
									%>
					<%
					String categoria = (String)request.getParameter(ParameterNames.CATEGORIA);
								String domicilio = (String)request.getParameter(ParameterNames.ENVIO_DOMICILIO);
								String nombre = (String)request.getParameter(ParameterNames.NOMBRE_TIENDA);
								
					%>
					<li><a href="<%=UrlBuilder.getUrlForController(request, ControllerPath.TIENDA, ActionNames.BUSCAR, (Integer)null )%>">Todas las categorias</a></li>
					<%
					for(Categoria cat: catServ){
								if(String.valueOf(cat.getId()).equals(categoria)){
					%>
					<li style="background-Color:grey"><a href="<%=UrlBuilder.getUrlForController(request, ControllerPath.TIENDA, ActionNames.BUSCAR, cat.getId())%>"><%=cat.getNombre()%></a></li>
					<%
					}else{
					%>
					<li><a href="<%=UrlBuilder.getUrlForController(request, ControllerPath.TIENDA, ActionNames.BUSCAR, cat.getId())%>"><%=cat.getNombre()%></a></li>
					<%} %>
					<%}%>
					
					<form action=" <%=UrlBuilder.getUrl(request, ControllerPath.TIENDA)%>" method="post">
					<input type="hidden" name=<%=ActionNames.ACTION%> value=<%=ActionNames.BUSCAR%>>
						<%if(categoria != null){%>
						<input type="hidden" name="<%=ParameterNames.CATEGORIA%>" value="<%=categoria%>">
						<%}%>
						<label>Envio a domicilio</label>
						<input type="checkbox" name=<%=ParameterNames.ENVIO_DOMICILIO%> value="true" <%if("true".equals(domicilio) && errores.printError(ActionNames.CREAR) == null){%>checked<%}%>>
						<br><button type="buscar" class="Buscar">buscar</button>
					</form>
				</ul>
			</label>
		</nav>
	</section>