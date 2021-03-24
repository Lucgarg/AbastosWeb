
<%@page import="com.abastos.market.web.util.*, com.abastos.model.*, com.abastos.market.web.model.*"%>

<div>

	<%Carrito carrito = (Carrito)SessionManager.get(request,AttributesNames.CARRITO);%>
	<label primerBloque="&#xe922" for=registro><a href="<%=UrlBuilder.getUrlForController(request, ControllerPath.LISTA, ActionNames.BUSCAR)%>">Mis listas</a></label><label
		segundoBloque="y" for="logIn">Mis valoraciones </label><label
		for="foNav"><div></div></label><label for=null><div
			tercerBloque="z">
			
			<a href="<%=UrlBuilder.getUrlForController(request, ControllerPath.CARRITO, ActionNames.DETALLE_CARRITO)%>" id="count"><%=carrito!=null? carrito.getLineasCarritoMap().size():""%></a>
		
		</div></label><label cuartoBloque="b" for="Idiomas">Idioma</label><label
		quintoBloque="u"><a href="<%=UrlBuilder.getUrlForController(request, ControllerPath.PEDIDO, ActionNames.HISTORIAL_PEDIDO)%>">Mis pedidos</a></label>
		<label
		sextoBloque="&#xea14"><a href="<%=UrlBuilder.getUrlForController(request, ControllerPath.PARTICULAR, ActionNames.CERRAR)%>">Cerrar sesión</a></label>
		
</div>