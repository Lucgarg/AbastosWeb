
<h1><%=tienda != null? tienda.getNombre():"Todas las tiendas"%></h1>
<%Map<String, String[]> parametros = (Map<String, String[]>)request.getParameterMap();%>
<form action=<%=UrlBuilder.getUrl(request, ControllerPath.PRODUCTO)%> method="post">
	<input type="hidden" name="<%=ActionNames.ACTION%>"
		value="<%=ActionNames.BUSCAR%>"> <input type="text"
		name="<%=ParameterNames.NOMBRE_PRODUCTO%>" value="<%=ParameterUtils.printParam(parametros, ParameterNames.NOMBRE_TIENDA, 0)%>"> <button type="submit" class="Buscar">buscar producto</button>
</form>