		<%
										if(pedido.getIdEstado() == 'C'){
										%>
				<a href=<%=UrlBuilder.getUrl(request, ViewPaths.VALORACION + "?" + AttributesNames.TIENDA +  "=" + p.getIdTienda() +  "&" + AttributesNames.PEDIDO + "=" + pedido.getId())%>><button type="button">Valorar tienda</button></a><br>
				<a href=<%=UrlBuilder.getUrl(request, ViewPaths.VALORACION + "?" + AttributesNames.PRODUCTO +  "=" + p.getIdProducto() +  "&" + AttributesNames.PEDIDO + "=" + pedido.getId())%>><button type="button">Valorar producto</button></a>
				
				<%}%>