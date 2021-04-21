
<%@page import="com.abastos.market.web.util.ParameterNames"%>
<%@page import="com.abastos.market.web.util.ActionNames"%>
<%@page import="com.abastos.market.web.util.ControllerPath"%>
<%@page import="com.abastos.market.web.util.UrlBuilder"%>
<div>
	<label
	 for="logIn" id="foto_perfil" style="background:url('<%=UrlBuilder.getUrlforImg(request, "usuario/empresa/" + empresa.getId() + "-perfil.jpeg")%>')no-repeat; background-size:cover; ">
	  <a href="<%=UrlBuilder.getUrl(request, ViewPaths.PERFIL_EMPRESA) %>">Mi perfil</a><button type="button" id="button_nav"></button></label>
	<label primerBloque="p" for=registro id="bloqueListas"><a
		href="<%=UrlBuilder.getUrlForController(request, ControllerPath.TIENDA, ActionNames.BUSCAR, true)%>">Mis
			tiendas</a></label><label segundoBloque="v" for="logIn" id="segundoBloque"> <a
		href="<%=UrlBuilder.getUrlForController(request, ControllerPath.OFERTA, ActionNames.BUSCAR, true)%>">Mis
			ofertas </a></label><label for="arrow-bottom"><div></div></label><label for="default-checked"><div
			tercerBloque="y" nombre="Valoraciones"></div></label><label cuartoBloque="w" id="cuartoBloque"><a
		href="<%=UrlBuilder.getUrlForController(request, ControllerPath.PRODUCTO_PRIVATE, ActionNames.BUSCAR, true, ActionNames.RE_INICIO, ParameterNames.TRUE)%>">Mis
			productos</a></label> <label quintoBloque="&#xea14" id="quintoBloque"><a
		href="<%=UrlBuilder.getUrlForController(request, ControllerPath.EMPRESA, ActionNames.CERRAR, true)%>">Cerrar
			sesión</a></label>
</div>