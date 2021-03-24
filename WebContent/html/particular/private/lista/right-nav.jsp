<section class="tiendas">
		<div class="tiendas2">
			<input type="checkbox">
			<%
		Lista results = (Lista) request.getAttribute(AttributesNames.LISTA);
		%>
			
			<h1><%=results.getNombre()%></h1>

			<div>
				

				<div class=informacionTienda>
					<p>Fecha de creación</p>
					<p><%=results.getFechaCreacion()%></p>
				</div>
			
			</div>
	</section>