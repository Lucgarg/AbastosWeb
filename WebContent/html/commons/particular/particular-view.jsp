
<%@page import="com.abastos.market.web.util.*, com.abastos.model.*"%>

<div>
	<%Pedido pedido = (Pedido)SessionManager.get(request, AttributesNames.PEDIDO);%>
	<label primerBloque="&#xe922" for=registro><a href="<%=UrlBuilder.getUrlForController(request, ControllerPath.LISTA, ActionNames.BUSCAR)%>">Mis listas</a></label><label
		segundoBloque="y" for="logIn">Mis valoraciones </label><label
		for="foNav"><div></div></label><label for=null><div
			tercerBloque="z">
			
			<a href="<%=UrlBuilder.getUrlForController(request, ControllerPath.PEDIDO, ActionNames.DETALLE)%>" id="count"></a>
		
		</div></label><label cuartoBloque="b" for="Idiomas">Idioma</label><label
		quintoBloque="u"><a href="<%=UrlBuilder.getUrlForController(request, ControllerPath.PEDIDO, ActionNames.HISTORIAL_PEDIDO)%>">Mis pedidos</a></label>
</div>