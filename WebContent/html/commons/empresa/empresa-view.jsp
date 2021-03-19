	<%@page import="com.abastos.market.web.util.UrlBuilder"%>
<div>
			<label primerBloque="p"for=registro><a href="<%=UrlBuilder.builder(request, ViewPathsActions.TIENDA_ACTION_BUSCAR)%>">Mis tiendas</a></label><label segundoBloque="v" for="logIn">
				<a href="<%=UrlBuilder.builder(request, ViewPathsActions.OFERTA_ACTION_BUSCAR)%>">Mis ofertas
				</a></label><label   for="foNav"><div ></div></label><label  for=null><div tercerBloque="y" nombre="Valoraciones"></div></label><label
				cuartoBloque="w"><a href="<%=UrlBuilder.builder(request, ViewPathsActions.PRODUCTO_ACTION_BUSCAR)%>">Mis productos</a></label>
		</div>