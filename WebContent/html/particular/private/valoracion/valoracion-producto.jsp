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
		
		Integer producto = (Integer) request.getAttribute(AttributesNames.VALORACION);
		String idPedido = (String) request.getAttribute(AttributesNames.PEDIDO);
		Producto pro = (Producto)request.getAttribute(AttributesNames.PRODUCTO);
		
		%>
		<h1 id="title_section"><%=pro.getNombre()%></h1>
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
				<%}%>> <input type="hidden"
				 name="<%=ParameterNames.ID_PRODUCTO%>"
				value="<%=pro.getId()%>"> <input type="hidden"
				name="<%=ParameterNames.PEDIDO%>" value="<%=idPedido%>">
				<input type="hidden" name="<%=ParameterNames.URL%>" value="<%=UrlBuilder.urlCallBack(request, false)%>">
			
			<fieldset class="centralBlock_form--valoracion">
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
			<button type="submit" class="Buscar"><%if(producto == 0){%>Realizar valoración
			<%}else{%>Actualizar valoración<%}%>
			</button>
		</form>


	</div>
</section>
<%@include file="/html/commons/usuario/footer.jsp"%>