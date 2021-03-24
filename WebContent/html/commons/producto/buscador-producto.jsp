
<h1><%=tienda != null? tienda.getNombre():"Todas las tiendas"%></h1>

<form action=<%=UrlBuilder.getUrl(request, ControllerPath.PRODUCTO)%>>
	<input type="hidden" name="<%=ActionNames.ACTION%>"
		value="<%=ActionNames.BUSCAR%>"> <input type="text"
		name="<%=ParameterNames.NOMBRE_PRODUCTO%>"> <input type="submit">
</form>