
<%@page import="com.abastos.market.web.util.ParameterNames"%>
<%@page import="com.abastos.market.web.util.ParameterUtils"%>
<h1><%=localidad != null? localidad.getNombre():"Todas las tiendas"%></h1>
<%Map<String, String[]> parametros = (Map<String, String[]>)request.getParameterMap();%>
<form action=<%=UrlBuilder.getUrl(request, ControllerPath.TIENDA)%> method="post" id="buscadorOmniShop">
	<input type="hidden" name="<%=ActionNames.ACTION%>"
		value="<%=ActionNames.BUSCAR%>"> <input type="text"
		name="<%=ParameterNames.NOMBRE_TIENDA%>" autocomplete="off" value="<%=ParameterUtils.printParam(parametros, ParameterNames.NOMBRE_TIENDA, 0)%>" list="omniSearchShop"> 
		<datalist id="omniSearchShop">
     
    </datalist>
		<button type="submit" class="Buscar">buscar tienda</button>
</form>