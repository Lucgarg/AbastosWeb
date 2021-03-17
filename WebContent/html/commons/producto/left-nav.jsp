<section>
		<nav>
			<label class="menu" for="menu"> <input type="checkbox" />
				<div></div>
				<ul>
				<%String categParameter = request.getParameter(ParameterNames.CATEGORIA);%>
				<%String precioDesde = request.getParameter(ParameterNames.PRECIO_DESDE);%>
				<%String precioHasta = request.getParameter(ParameterNames.PRECIO_HASTA);%>
				<%String oferta = request.getParameter(ParameterNames.OFERTA);%>
				<%String origen = request.getParameter(ParameterNames.ORIGEN);%>
				
					<%List<Categoria> cat = (List<Categoria>)SessionManager.get(request, AttributesNames.CATEGORIAS);%>
					<%for(Categoria c: cat) {
					
					if(String.valueOf(c.getId()).equals(categParameter)){%>
						<li style="background-Color:grey"><a href="<%=UrlBuilder.builderMap(request, ViewPathsActions.PRODUCTO_ACTION_BUSCAR,c.getId())%>"><%=c.getNombre()%></a>
					<%}else{
						
					
					%>
					<li><a href="<%=UrlBuilder.builderMap(request, ViewPathsActions.PRODUCTO_ACTION_BUSCAR,c.getId())%>"><%=c.getNombre()%></a>
					<%}if(c.getCategorias().size() > 0) {%>
					<ol><% }%>
					<%for(Categoria categoriaProducto: c.getCategorias()) {%>
						
							<li><a href="<%=UrlBuilder.builderMap(request, ViewPathsActions.PRODUCTO_ACTION_BUSCAR, c.getId())%>"><%=categoriaProducto.getNombre()%></a></li>
							<% }
							if(c.getCategorias().size() > 0){%>
							</ol><%}%>
							
							</li>
							<%} %>
					
					<form action="<%=UrlBuilder.builder(request, ViewPathsServlet.PRODUCTO)%>" method="post">
						<input type="hidden" name=<%=ActionNames.ACTION%> value=<%=ActionNames.BUSCAR%> /> <input
							type="text" name=<%=ParameterNames.PRECIO_DESDE%> placeholder="predioDesde" value=<%=precioDesde!=null?precioDesde:""%>><br>
						<input type="text" name=<%=ParameterNames.PRECIO_HASTA%> placeholder="precioHasta" value=<%=precioHasta!=null?precioHasta:""%>><br>
						<h5>origen</h5>
						<label for="origenN">Nacional</label> <input type="radio"
							name=<%=ParameterNames.ORIGEN%> id="origenN" value="N" <%if("N".equals(origen)){%>checked<%}%>><br> <label
							for="origenI">Internacional</label> <input type="radio"
							name=<%=ParameterNames.ORIGEN%> id="origenI" value="I" <%if("I".equals(origen)){%>checked<%}%>><br> <label
							for="origenL">Local</label> <input type="radio" name=<%=ParameterNames.ORIGEN%>
							id="origenL" value="L"  <%if("L".equals(origen)){%>checked<%}%>><br>

						<h5>Oferta</h5>
						<label for="true">si</label> <input type="checkbox" name=<%=ParameterNames.OFERTA%>
							value="true" <%if("true".equals(oferta)){%>checked<%}%>>

						<h5>Idioma</h5>
						<select name=<%=ParameterNames.IDIOMA%>>
							<option value="es">español</option>
							<option value="en">inglés</option>

						</select><br> <input type="hidden" name=<%=ParameterNames.ID_TIENDA%>
							value=<%=tienda.getId()%>> <input type="submit"
							value="buscar">
					</form>
				</ul>
			</label>
		</nav>
	</section>