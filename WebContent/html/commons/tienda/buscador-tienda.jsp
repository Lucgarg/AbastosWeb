
<%@page import="com.abastos.market.web.util.ParameterNames"%>
<%@page import="com.abastos.market.web.util.ParameterUtils"%>
<h1><%=localidad != null? localidad.getNombre():"Todas las tiendas"%></h1>

<form action=<%=UrlBuilder.getUrl(request, ControllerPath.TIENDA)%> method="post">
	<input type="hidden" name="<%=ActionNames.ACTION%>"
		value="<%=ActionNames.BUSCAR%>"> <input type="text"
		name="<%=ParameterNames.NOMBRE_TIENDA%>" value="<%=ParameterUtils.printParam(request, ParameterNames.NOMBRE_TIENDA, 0)%>"> <button type="submit" class="Buscar">buscar tienda</button>
</form>