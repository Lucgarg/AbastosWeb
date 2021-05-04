
<h1><%=tienda != null? tienda.getNombre():"Todas las tiendas"%></h1>
<%Map<String, String[]> parametros = (Map<String, String[]>)request.getParameterMap();%>
<form action=<%=UrlBuilder.getUrl(request, ControllerPath.PRODUCTO)%> method="post" id="buscadorOmniProduct">
	<input type="hidden" name="<%=ActionNames.ACTION%>"
		value="<%=ActionNames.BUSCAR%>"> <input type="text"
		name="<%=ParameterNames.NOMBRE_PRODUCTO%>" autocomplete="off" value="<%=ParameterUtils.printParam(parametros, ParameterNames.NOMBRE_PRODUCTO, 0)%>"list="omniSearchProduct"> 
			<datalist id="omniSearchProduct">
     
    </datalist>
		<button type="submit" class="Buscar">buscar producto</button>
</form>