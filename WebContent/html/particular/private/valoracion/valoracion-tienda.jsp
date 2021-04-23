<%@page
	import="com.abastos.market.web.util.*, java.util.*, com.abastos.model.*"%>
<%@include file="/html/commons/usuario/header.jsp"%>
<section class="block search">
	<div class="block_second">
</section>
<section>
	<nav></nav>
</section>
<section class="centralBlock">
	<div>
		<%
		PuntuacionTienda puntTienda = (PuntuacionTienda) request.getAttribute(AttributesNames.VALORACION);
		String idPedido = (String) request.getAttribute(AttributesNames.PEDIDO);
		Tienda t = (Tienda) request.getAttribute(AttributesNames.TIENDA);
		%>
		<h1 id="title_section"><%=t.getNombre()%></h1>
		<%
		if (errores.hasErrors()) {
		%>
		<p class="error"><%=errores.printError(ParameterNames.ERROR)%></p>
		<%
		}
		%>

		<form
			action="<%=UrlBuilder.getUrl(request, ControllerPath.VALORACION)%>"
			class="valoracion">
			<input type="hidden" name="<%=ParameterNames.URL%>"
				value="<%=UrlBuilder.urlCallBack(request, false)%>"> <input
				type="hidden" name="<%=ActionNames.ACTION%>"
				<%if (puntTienda == null) {%>
				value="<%=ActionNames.PUNTUACION_TIENDA%>" <%} else {%>
				value="<%=ActionNames.UPDATE_VAL_TIENDA%>">
			<%
			}
			%>
			<input type="hidden" name="<%=ParameterNames.ID_TIENDA%>" value="<%=t.getId()%>"> <input
				name="<%=ParameterNames.PEDIDO%>" value="<%=idPedido%>">

			<fieldset class="centralBlock_form--valoracion">
				<legend>Atención al cliente</legend>
				<label class="star-blank"><input type="radio" id="1"
					value="1" name="<%=ParameterNames.PUNTUACION_ATC%>"
					<%if (puntTienda !=null){if(puntTienda.getValoracionAtncCliente() == 1) {%> checked <%}}%>></label>
				<label class="star-blank"><input type="radio" id="2"
					value="2" name="<%=ParameterNames.PUNTUACION_ATC%>"
					<%if (puntTienda !=null) {if (puntTienda.getValoracionAtncCliente() == 2) {%> checked <%}}%>></label>
				<label class="star-blank"><input type="radio" id="3"
					value="3" name="<%=ParameterNames.PUNTUACION_ATC%>"
					<%if (puntTienda !=null) {if (puntTienda.getValoracionAtncCliente() == 3) {%> checked <%}}%>></label>
				<label class="star-blank"><input type="radio" id="4"
					value="4" name="<%=ParameterNames.PUNTUACION_ATC%>"
					<%if (puntTienda !=null) {if (puntTienda.getValoracionAtncCliente() == 4) {%> checked <%}}%>></label>
				<label class="star-blank"><input type="radio" id="5"
					value="5" name="<%=ParameterNames.PUNTUACION_ATC%>"
					<% if (puntTienda !=null) {if (puntTienda.getValoracionAtncCliente() == 5) {%> checked <%}}%>></label>
			</fieldset>
			<fieldset class="centralBlock_form--valoracion">
				<legend>Atención a domicilio</legend>
				<label class="star-blank"><input type="radio" id="6"
					value="1" name="<%=ParameterNames.PUNTUACION_ATD%>"
					<%if (puntTienda !=null) {if (puntTienda.getValoracionServDomicilio() == 1) {%> checked <%}}%>></label>
				<label class="star-blank"><input type="radio" id="7"
					value="2" name="<%=ParameterNames.PUNTUACION_ATD%>"
					<% if (puntTienda !=null) {if (puntTienda.getValoracionServDomicilio() == 2) {%> checked <%}}%>></label>
				<label class="star-blank"><input type="radio" id="8"
					value="3" name="<%=ParameterNames.PUNTUACION_ATD%>"
					<%if (puntTienda !=null) {if (puntTienda.getValoracionServDomicilio() == 3) {%> checked <%}}%>></label>
				<label class="star-blank"><input type="radio" id="9"
					value="4" name="<%=ParameterNames.PUNTUACION_ATD%>"
					<%if (puntTienda !=null) {if (puntTienda.getValoracionServDomicilio() == 4) {%> checked <%}}%>></label>
				<label class="star-blank"><input type="radio" id="10"
					value="5" name="<%=ParameterNames.PUNTUACION_ATD%>"
					<%if (puntTienda !=null) {if (puntTienda.getValoracionServDomicilio() == 5) {%> checked <%}}%>></label>
			</fieldset>
			<fieldset class="centralBlock_form--valoracion">
				<legend>Precios</legend>
				<label class="star-blank"><input type="radio" id="11"
					value="1" name="<%=ParameterNames.PUNTUACION_PRECIO%>"
					<%if (puntTienda !=null) {if (puntTienda.getValoracionPrecio() == 1) {%> checked <%}}%>></label>
				<label class="star-blank"><input type="radio" id="12"
					value="2" name="<%=ParameterNames.PUNTUACION_PRECIO%>"
					<%if (puntTienda !=null) {if (puntTienda.getValoracionPrecio() == 2) {%> checked <%}}%>></label>
				<label class="star-blank"><input type="radio" id="13"
					value="3" name="<%=ParameterNames.PUNTUACION_PRECIO%>"
					<%if (puntTienda !=null) {if (puntTienda.getValoracionPrecio() == 3) {%> checked <%}}%>></label>
				<label class="star-blank"><input type="radio" id="14"
					value="4" name="<%=ParameterNames.PUNTUACION_PRECIO%>"
					<%if (puntTienda !=null) {if (puntTienda.getValoracionPrecio() == 4) {%> checked <%}}%>></label>
				<label class="star-blank"><input type="radio" id="15"
					value="5" name="<%=ParameterNames.PUNTUACION_PRECIO%>"
					<%if (puntTienda !=null) {if (puntTienda.getValoracionPrecio() == 5) {%> checked <%}}%>></label>
			</fieldset>
			<button type="submit" class="Buscar">
				<%
				if (puntTienda == null) {
				%>Realizar valoración
				<%
				} else {
				%>Actualizar valoración<%
				}
				%>
			</button>
		</form>


	</div>
</section>
<%@include file="/html/commons/usuario/footer.jsp"%>