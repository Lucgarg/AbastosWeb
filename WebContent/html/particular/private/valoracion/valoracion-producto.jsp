<%@page
	import="com.abastos.market.web.util.*, java.util.*, com.abastos.model.*"%>
<%@include file="/html/commons/usuario/header.jsp"%>
<section class="tiendas">
	<div class="tiendas2">
</section>
<section>
	<nav></nav>
</section>
<section class="productoDetalle">
	<div>
		<%
		
		Integer producto = (Integer) request.getAttribute(AttributesNames.VALORACION);
		String idPedido = (String) request.getAttribute(AttributesNames.PEDIDO);
		String idProduct = (String)request.getAttribute(AttributesNames.PRODUCTO);
		
		%>
		<%if(errores.hasErrors()){%>
			<p class="error"><%=errores.printError(ParameterNames.ERROR)%></p>
		<%}%>

		<form
			action="<%=UrlBuilder.getUrl(request, ControllerPath.VALORACION)%>"
			class="valoracion">
			<input type="hidden" name="<%=ActionNames.ACTION%>"
				<%if(producto == 0){%>
				value="<%=ActionNames.PUNTUACION_PRODUCTO%>"
				<%}else{%>
				value="<%=ActionNames.UPDATE_VAL_PRODUCTO%>"
				><%}%>> <input type="hidden"
				 name="<%=ParameterNames.ID_PRODUCTO%>"
				value="<%=idProduct%>"> <input type="hidden"
				name="<%=ParameterNames.PEDIDO%>" value="<%=idPedido%>">
				<input type="hidden" name="<%=ParameterNames.URL%>" value="<%=UrlBuilder.urlCallBack(request, false)%>">
			<fieldset>
				<legend>Valorar producto</legend>
				<label class="star-blank"><input type="radio" id="11"
					value="1" name="<%=ParameterNames.PUNTUACION_PRODUCTO%>"
					<%if(producto == 1){%>checked<%}%>></label> <label
					class="star-blank"><input type="radio" id="12" value="2"
					name="<%=ParameterNames.PUNTUACION_PRODUCTO%>"
					<%if(producto == 2){%>checked<%}%>></label> <label
					class="star-blank"><input type="radio" id="13" value="3"
					name="<%=ParameterNames.PUNTUACION_PRODUCTO%>"
					<%if(producto == 3){%>checked<%}%>></label> <label
					class="star-blank"><input type="radio" id="14" value="4"
					name="<%=ParameterNames.PUNTUACION_PRODUCTO%>"
					<%if(producto == 4){%>checked<%}%>></label> <label
					class="star-blank"><input type="radio" id="15" value="5"
					name="<%=ParameterNames.PUNTUACION_PRODUCTO%>"
					<%if(producto == 5){%>checked<%}%>></label>
			</fieldset>
			<button type="submit">
		</form>


	</div>
</section>
<%@include file="/html/commons/usuario/footer.jsp"%>