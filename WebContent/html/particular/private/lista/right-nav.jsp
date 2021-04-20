<section class="block search">
		<div class="block_second">
			<input type="checkbox">
	
			<%
		Lista results = (Lista) request.getAttribute(AttributesNames.LISTA);
		%>
			
			<h1><%=results.getNombre()%></h1>

			<div>
				

				<div class="block_second--detail">
					<p>Fecha de creación</p>
					<p><%=results.getFechaCreacion()%></p>
				</div>
			
			</div>
	</section>