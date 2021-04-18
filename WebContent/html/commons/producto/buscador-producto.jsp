
<h1><%=tienda != null? tienda.getNombre():"Todas las tiendas"%></h1>

<form action=<%=UrlBuilder.getUrl(request, ControllerPath.PRODUCTO)%> method="post">
	<input type="hidden" name="<%=ActionNames.ACTION%>"
		value="<%=ActionNames.BUSCAR%>"> <input type="text"
		name="<%=ParameterNames.NOMBRE_PRODUCTO%>" value="<%=ParameterUtils.printParam(request, ParameterNames.NOMBRE_TIENDA, 0)%>"> <button type="submit" class="Buscar">buscar producto</button>
</form>