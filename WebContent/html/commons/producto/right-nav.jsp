		<%
	if (empresa != null  && tienda == null) {
	%>
	<section class="block crear">
		<div class="block_second">
		<%}else{%>
	<section class="block search">
		<div class="block_second">
		<%}%>
			<input type="checkbox">
			
			<%if(tienda != null){%>
			<h1><%=tienda.getNombre()%></h1>

			<div>
				<figure>
					<img src="<%=request.getContextPath()%>/imgs/tiendas/<%=tienda.getId()%>-principal.jpg" alt="">
					<figcaption>
						<span><%=tienda.getPuntuacionMedia().getValoracionMedia()%></span>
					</figcaption>
				</figure>

				<div class="block_second--detail">
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
			<%}%>
				<%
			if(empresa != null){
			%>
	<button class="Buscar crear <%=tienda==null?"":"business"%>">
		<a
			href="<%=UrlBuilder.getUrlForController(request, ControllerPath.PRECREATE, ActionNames.PRODUCTO, true)%>">
			Crear producto</a>
	</button>
	<%}%>
			</div>
	</section>