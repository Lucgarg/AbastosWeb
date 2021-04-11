
<%@page import="com.abastos.market.web.util.*, com.abastos.model.*"%>
<div>

	<label primerBloque="a" for=registro>Registrarse</label><label
		segundoBloque="l" for="logIn">Inicio Sesión</label><label for="arrow-bottom"><div></div></label><label
		for="default-checked"><div tercerBloque="z">
			<a
				href="<%=UrlBuilder.getUrlForController(request, ControllerPath.CARRITO, ActionNames.DETALLE_CARRITO, true)%>"
				id="count"><%=carrito!=null? carrito.getLineasCarritoMap().size():""%></a>

		</div></label><label cuartoBloque="b" for="Idiomas">Idioma</label>
</div>
