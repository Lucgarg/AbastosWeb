	<%@page import="com.abastos.market.web.util.ActionNames"%>
<%@page import="com.abastos.market.web.util.ControllerPath"%>
<%@page import="com.abastos.market.web.util.UrlBuilder"%>
<div>
			<label primerBloque="p"for=registro><a href="<%=UrlBuilder.getUrlForController(request, ControllerPath.TIENDA, ActionNames.BUSCAR, true)%>">Mis tiendas</a></label><label segundoBloque="v" for="logIn">
				<a href="<%=UrlBuilder.getUrlForController(request, ControllerPath.OFERTA, ActionNames.BUSCAR ,true)%>">Mis ofertas
				</a></label><label   for="foNav"><div ></div></label><label  for=null><div tercerBloque="y" nombre="Valoraciones"></div></label><label
				cuartoBloque="w"><a href="<%=UrlBuilder.getUrlForController(request, ControllerPath.PRODUCTO_PRIVATE, ActionNames.BUSCAR, true)%>">Mis productos</a></label>
						<label
		quintoBloque="&#xea14"><a href="<%=UrlBuilder.getUrlForController(request, ControllerPath.EMPRESA, ActionNames.CERRAR, true)%>">Cerrar sesión</a></label>
		</div>