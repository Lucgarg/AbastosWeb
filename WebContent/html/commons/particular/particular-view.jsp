
<%@page import="com.abastos.market.web.util.*, com.abastos.model.*, com.abastos.market.web.model.*"%>

<div>

	<label
	 for="logIn" id="foto_perfil" style="background:url('<%=UrlBuilder.getUrlforImg(request, "usuario/particular/" + particular.getId() + "-perfil.jpeg")%>')no-repeat; background-size:cover; ">
	 <a href="<%=UrlBuilder.getUrl(request, ViewPaths.PERFIL_PARTICULAR) %>">Mi perfil</a></label>
	<label primerBloque="&#xe922" id="bloqueListas"for=registro><a href="<%=UrlBuilder.getUrlForController(request, ControllerPath.LISTA, ActionNames.BUSCAR, true)%>">Mis listas</a></label><label
		for="arrow-bottom"><div></div></label><label for="default-checked"><div
			tercerBloque="z">
			
			<a href="<%=UrlBuilder.getUrlForController(request, ControllerPath.CARRITO, ActionNames.DETALLE_CARRITO, true)%>" id="count"><%=carrito!=null? carrito.getLineasCarritoMap().size():""%></a>
		
		</div></label><label cuartoBloque="b" for="Idiomas">Idioma</label><label
		quintoBloque="u"><a href="<%=UrlBuilder.getUrlForController(request, ControllerPath.PEDIDO_PRIVATE, ActionNames.HISTORIAL_PEDIDO, true)%>">Mis pedidos</a></label>
		<label
		sextoBloque="&#xea14"><a href="<%=UrlBuilder.getUrlForController(request, ControllerPath.PARTICULAR, ActionNames.CERRAR, true)%>">Cerrar sesión</a></label>
		
</div>