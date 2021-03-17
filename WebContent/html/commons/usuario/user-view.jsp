  <%@page import="com.abastos.market.web.util.*, com.abastos.model.*"%>
  <div>
  <%Pedido pedido = (Pedido)SessionManager.get(request, AttributesNames.PEDIDO);%>
           	<label primerBloque="a"for=registro>Registrarse</label><label segundoBloque="l" for="logIn">Inicio
				Sesión</label><label   for="foNav"><div ></div></label><label  for=null><div tercerBloque="z">
				<%if(pedido != null){%>
			<a href="<%=UrlBuilder.builder(request, ViewPathsActions.PEDIDO_ACTION_DETALLE)%>">
			<%=pedido.getLineaPedido().size() >= 0? (pedido.getLineaPedido().size() == 0? 1:
					pedido.getLineaPedido().size()): ""%></a>
			<%}else{%>
			<%}%>
			
				</div></label><label cuartoBloque="b"for="Idiomas">Idioma</label>
               </div>