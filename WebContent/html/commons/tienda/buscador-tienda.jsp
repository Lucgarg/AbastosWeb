
<h1><%=localidad != null? localidad.getNombre():"Todas las tiendas"%></h1>

<form action=<%=UrlBuilder.getUrl(request, ControllerPath.TIENDA)%>>
	<input type="hidden" name="<%=ActionNames.ACTION%>"
		value="<%=ActionNames.BUSCAR%>"> <input type="text"
		name="<%=ParameterNames.NOMBRE_TIENDA%>"> <button type="submit" class="Buscar">buscar tienda</button>
</form>