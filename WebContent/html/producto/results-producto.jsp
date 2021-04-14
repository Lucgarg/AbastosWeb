<%@page import="com.abastos.service.utils.CacheNames"%>
<%@ page
	import="java.util.*, com.abastos.model.*, com.abastos.service.*, 
	com.abastos.service.impl.*, com.abastos.dao.*, com.abastos.market.web.util.*,
	com.abastos.model.*, com.abastos.cache.impl.*"%>
<%@include file="/html/commons/usuario/header.jsp"%>
<%@include file="/html/commons/producto/right-nav.jsp"%>
<%@include file="/html/commons/producto/left-nav.jsp"%>
<section class="block block--results">
		<%if(errores.hasErrors()){%>
	<p class="error generic"><%=errores.printError(ParameterNames.ERROR)%></p>
	<%}%>
	<div class="block_second">
	<%if(empresa != null){ %>
			<h1 id="title_section">Mis Productos</h1>
		<%}%>
		<%
		Map<Long, Producto> ofertPro=  (Map<Long,Producto>)CacheManagerImpl.getInstance().get(CacheNames.PRODUCTO_OFERTA).get("es");
		Map<Long,String> resultsTienda = (Map<Long, String>) request.getAttribute(AttributesNames.TIENDA_PRODUCTOS);
		Results<Producto> results = (Results<Producto>) request.getAttribute(AttributesNames.PRODUCTO);
		Pagination pagination = (Pagination)request.getAttribute(ParameterNames.PAGE);
		for(Producto p : results.getPage()){
			%>

		<div>
			<figure>
				<img
					src="<%=UrlBuilder.getUrlforImg(request, "productos/" + p.getId() + "-principal.jpg")%>" />
			</figure>
			<p>
				<a
					href="<%=UrlBuilder.getUrlForController(request, ControllerPath.PRODUCTO, ActionNames.DETALLE, true, ParameterNames.ID_PRODUCTO, String.valueOf(p.getId()))%>"><%=p.getNombre()%></a>
			</p>
			<%
				if(resultsTienda != null){
				%>
			<p><%=resultsTienda.get(p.getId())%></p>
			<%
				}
				%>

			<%
								if(p.getOferta() != null){
																	if(p.getOferta().getIdTipoOferta()==1){
								%>
			<p class="precio"><%=p.getPrecio()%></p>
			<%
				}else{
				%>
			<p class="precioNoMostrado"></p>
			<%
  				}
  				%>
			<div class="oferta">
				<p>oferta</p>
			</div>

			<div class="ofertaDetalle">
				<p><%=p.getOferta().getNombreOferta()%></p>
				<%
					 if(p.getOferta().getIdTipoOferta() == 1){
					 %>
				<p>
					-<%
					 	if(p.getOferta().getDescuentoFijo() !=0.0){
					 	%>
					<%=p.getOferta().getDescuentoFijo()%>
					&euro;
					<%
					 			  }else{
					 			  %>
					<%=p.getOferta().getDescuentoPcn()%>
					%
					<%
					 }
					 %>
				</p>
				<%
					 	}
					 				 				 if(p.getOferta().getIdTipoOferta() == 2){
					 	%>
				<p><%=p.getOferta().getDenominador()%>
					unidad -
					<%
					 	if(p.getOferta().getDescuentoFijo() != 0.0){
					 	%>
					<%=p.getOferta().getDescuentoFijo()%>
					&euro;
					<%
					 			  }else{
					 			  %>
					<%=p.getOferta().getDescuentoPcn()%>
					%

					<%
					 									 }
					 									 %>
				</p>
				<%
					 }
					 			 			 if(p.getOferta().getIdTipoOferta() == 3){
					 %>
				<p>
					Compra y ahorrate del producto
					<%=ofertPro.get(p.getOferta().getIdProdOferta()).getNombre()%>

					<%
 					 						 			if(p.getOferta().getDescuentoFijo() != 0.0){
 					 						 			%>
					<%=p.getOferta().getDescuentoFijo()%>
					&euro;
					<%
					 			}else{
					 			%>
					<%=p.getOferta().getDescuentoPcn()%>
					%
					<%
					 }
					 %>
				</p>
				<%
					 }
					 %>
			</div>
			<%
				 }
				 %>

			<p><%=p.getPrecioFinal()%></p>
			<%
					if(empresa == null){
					%>
			<form>
				<input type="number" min="1" max="<%=p.getStock()%>" name="<%=ParameterNames.NUMERO_UNIDADES%>">
				<button type="button" class="carritoCompra" name="<%=p.getId()%>"></button>
			</form>
			<%
					}
					%>
			<span><%=p.getValoracion()%></span>
		</div>
		<%
			}
			%>

	</div>

</section>
<!--paginacion-->
<%if(pagination.getTotalPages() > 1){%>
<div class="paginacion">
<%if(pagination.getPage() != pagination.getFirstPage()){%>
<a href="<%=UrlBuilder.getUrlForController(request, ControllerPath.PRODUCTO, 
		ActionNames.BUSCAR, ParameterNames.PAGE, String.valueOf(pagination.getFirstPage()))%>" 
		class="page"><%=pagination.getFirstPage()%></a>
<a class="page--blank">...</a>
<%}%>
<%for(int i = pagination.getFirstPagedPage(); i < pagination.getPage(); i++ ){%>
<%if(i != pagination.getFirstPage()){%>
<a href="<%=UrlBuilder.getUrlForController(request, ControllerPath.PRODUCTO, 
		ActionNames.BUSCAR, ParameterNames.PAGE, String.valueOf(i))%>" 
		class="page"><%=i%></a>		
<%}%>
<%}%>


<a href="<%=UrlBuilder.getUrlForController(request, ControllerPath.PRODUCTO,
		ActionNames.BUSCAR, ParameterNames.PAGE, String.valueOf(pagination.getPage()))%>" 
		style="padding-bottom: 2%; width: 2%;" class="page"><%=pagination.getPage()%></a>

<%for(int i = pagination.getPage()+1; i <= pagination.getLastPagedPage(); i++ ){%>
<%if(i != pagination.getTotalPages()){%>
<a href="<%=UrlBuilder.getUrlForController(request, ControllerPath.PRODUCTO, 
		ActionNames.BUSCAR, ParameterNames.PAGE, String.valueOf(i))%>" 
	 class="page"><%=i%></a>
	 <%}%>
<%}%>
<%if(pagination.getPage() != pagination.getTotalPages()){%>
<a class="page--blank">...</a>
<a href="<%=UrlBuilder.getUrlForController(request, ControllerPath.PRODUCTO, 
		ActionNames.BUSCAR, ParameterNames.PAGE, String.valueOf(pagination.getTotalPages()))%>" 
		 class="page"><%=pagination.getTotalPages()%></a>
		 <%}%>

</div>
<%}%>
<%@include file="/html/commons/usuario/footer.jsp"%>