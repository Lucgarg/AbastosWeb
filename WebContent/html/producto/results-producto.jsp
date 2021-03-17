<%@ page import="java.util.*, com.abastos.model.*, com.abastos.service.*, com.abastos.service.impl.*, com.abastos.market.web.util.*"%>
	<%@include file= "/html/commons/usuario/header.jsp"%>
	<section class="tiendas">
		<div class="tiendas2">
			<input type="checkbox">
			<%Tienda tienda = (Tienda)SessionManager.get(request, AttributesNames.TIENDA);%>
			<h1><%=tienda.getNombre()%></h1>

			<div>
				<figure>
					<img src="<%=request.getContextPath()%>/imgs/tiendas/<%=tienda.getId()%>-principal.jpg" alt="">
					<figcaption>
						<span><%=tienda.getPuntuacionMedia().getValoracionMedia()%></span>
					</figcaption>
				</figure>

				<div class=informacionTienda>
					<p>
						Jardin y hogar<br>
						<%=tienda.getDireccionDto().getCalle()%><br>
						<%=tienda.getDireccionDto().getPiso()%><br>
						<%=tienda.getNumeroMovil()%><br>
						<%=tienda.getNumeroTelefono()%><br>
						<%=tienda.getEmail()%></p>
					<label class="forma">Atencion al cliente
						<span><%=tienda.getPuntuacionMedia().getValoracionAtncClienteMedia()%></span>
					</label> <label class="forma">Servicio a domicilio
						<span><%=tienda.getPuntuacionMedia().getValoracionServDomicilioMedia()%></span>
					<label class="forma">Precios
						<span><%=tienda.getPuntuacionMedia().getValoracionPrecioMedia()%></span>
					</label>
				</div>

			</div>
	</section>
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
	<section class="tiendas producto">
		<div class="tiendas2">
			<%
		List<Producto> results = (List<Producto>) SessionManager.get(request, AttributesNames.PRODUCTO);
		
		for(Producto p : results){
			%>

			<div>
				<figure>
					<img src="<%=UrlBuilder.builderImg(request, "productos/" + p.getId() + "-principal.jpg")%>" />
				</figure>
				<p>
					<a
						href="<%=request.getContextPath()%><%=ViewPathsActions.PRODUCTO_ACTION_DETALLE%><%=p.getId()%>&tienda=<%=tienda.getId()%>"><%=p.getNombre()%></a>
				</p>
				
				
				
				<%if(p.getOferta() != null){
					if(p.getOferta().getIdTipoOferta()==1){%>
				<p class="precio"><%=p.getPrecio()%></p>
				<%}else{%>
				<p class="precioNoMostrado"></p>  
				<%}%>
						<div class="oferta">
						<p>oferta</p>
					 </div>
					 
					 <div class="ofertaDetalle">
					 <p><%=p.getOferta().getNombreOferta()%></p>
					 <%if(p.getOferta().getIdTipoOferta() == 1){ %>
					 	<p>-<%if(p.getOferta().getDescuentoFijo() !=0.0){%>
					 			<%=p.getOferta().getDescuentoFijo()%>
					 			&euro;
					 			  <%}else{%>
					 				<%=p.getOferta().getDescuentoPcn()%> %
					 <%}%></p>
					 	<%}
					 if(p.getOferta().getIdTipoOferta() == 2){ %>
					 	<p><%=p.getOferta().getDenominador()%>
					 	 unidad -
					 			<%if(p.getOferta().getDescuentoFijo() != 0.0){%>
					 			<%=p.getOferta().getDescuentoFijo()%>
					 			 &euro;
					 			  <%}else{%>
					 				<%=p.getOferta().getDescuentoPcn()%> %
					 				
					 <%}%>
					 </p>
					 <%}
					 if(p.getOferta().getIdTipoOferta() == 3){%>
					 	<p>Compra y ahorrate del producto
					 	<%=p.getOferta().getNombreProdOferta()%> 
					 	
					 			<%if(p.getOferta().getDescuentoFijo() != 0.0){%>
					 			<%=p.getOferta().getDescuentoFijo()%>
					 			 &euro;
					 			<%}else{%>
					 				<%=p.getOferta().getDescuentoPcn()%> %
					 <%}%></p>
					 <%}%>
					 </div>
				 <%}%>
				
					<p><%=p.getPrecioFinal()%></p>
					<form action="<%=UrlBuilder.builder(request, "pedido")%>">
					<input type="text" name="<%=ParameterNames.NUMERO_UNIDADES%>"><br>
					<input type="hidden" name="<%=ActionNames.ACTION%>" value="<%=ActionNames.ADD%>">
 					<input type="hidden" name="<%=ParameterNames.ID_PRODUCTO%>" value="<%=p.getId()%>">
 					<button type="submit" class="carritoCompra"></button>
					</form>
				<span><%=p.getValoracion()%></span>
			</div>
			<%}%>
		
		</div>
	</section>
	<%@include file="/html/commons/usuario/footer.jsp"%>