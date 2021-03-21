	<%@page import="com.abastos.market.web.util.ParameterNames"%>
<section class="tiendas">
		<div class="tiendas2">
	</section>
<section>
		<nav>
			<label class="menu" for="menu"> <input type="checkbox" />
				<div></div>
				<ul>
				
					<%List<Categoria> catServ = (List<Categoria>)request.getAttribute(AttributesNames.CATEGORIAS);%>
					<%String categoria = (String)request.getParameter(ParameterNames.CATEGORIA);
					String domicilio = (String)request.getParameter(ParameterNames.ENVIO_DOMICILIO);
					String nombre = (String)request.getParameter(ParameterNames.NOMBRE_TIENDA);
					%>
					<%for(Categoria cat: catServ){
					if(String.valueOf(cat.getId()).equals(categoria)){%>
					<li style="background-Color:grey"><a href="<%=UrlBuilder.builderMap(request, ViewPathsActions.TIENDA_ACTION_BUSCAR, cat.getId())%>"><%=cat.getNombre()%></a></li>
					<%}else{ %>
					<li><a href="<%=UrlBuilder.builderMap(request, ViewPathsActions.TIENDA_ACTION_BUSCAR, cat.getId())%>"><%=cat.getNombre()%></a></li>
					<%} %>
					<%}%>
					
					<form action=" <%=UrlBuilder.builder(request, ViewPathsServlet.TIENDA)%>" method="post">
					<input type="hidden" name=<%=ActionNames.ACTION%> value=<%=ActionNames.BUSCAR%>>
						<label>Envio a domicilio</label>
						<input type="checkbox" name=<%=ParameterNames.ENVIO_DOMICILIO%> value="true" <%if("true".equals(domicilio)){%>checked<%}%>>
						<input type="submit"
							name="buscar" value="buscar">
					</form>
				</ul>
			</label>
		</nav>
	</section>