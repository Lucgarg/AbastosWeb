
<%@ page
	import="java.util.*, com.abastos.model.*, com.abastos.service.*, com.abastos.dao.*, com.abastos.market.web.util.*"%>
<%@include file="/html/commons/usuario/header.jsp"%>
<%@include file="/html/commons/tienda/nav.jsp"%>
<section class="block block--results">
	<%
		Results<Tienda> results = (Results<Tienda>) request.getAttribute(AttributesNames.RESULTS_TIENDA);
		Pagination pagination = (Pagination)request.getAttribute(ParameterNames.PAGE);
		%>
	<%
		if (errores.hasErrors()) {
		%>
		<p class="error generic"><%=errores.printError(ParameterNames.ERROR)%></p>
		<%
		}
		%>
	<div class="block_second">
		<%if(empresa != null){ %>
			<h1 id="title_section">Mis tiendas</h1>
		<%}%>
	
	
		<%
		for (Tienda t : results.getPage()) {
		%>

		<div>
			<figure>
				<img
					src=" <%=UrlBuilder.getUrlforImg(request, "tiendas/" + t.getId() + "-principal.jpg")%>"
					alt="">
			</figure>
			<p>
				<a
					href="<%=UrlBuilder.getUrl(request, ViewPathsctions.TIENDA_ACTION_DETALLE)%><%=t.getId()%>"><%=t.getNombre()%></a>
			</p>
			<p><%=t.getDireccionDto().getLocalidad()%></p>
			<span><%=t.getPuntuacionMedia().getValoracionMedia()%></span>
		</div>
		<%
		}
		%>

	</div>
	
	<%
	 if (errores.printError(ActionNames.CREAR) != null) {
	%>
	<p><%=errores.printError(ActionNames.CREAR)%></p>
	<%
	}
	%>
</section>
<!--paginacion-->
<%if(pagination.getTotalPages() > 1){%>
<div class="paginacion">
<%if(pagination.getPage() != pagination.getFirstPage()){%>

<a href="<%=UrlBuilder.getUrlForController(request, ControllerPath.TIENDA, 
		ActionNames.BUSCAR, ParameterNames.PAGE, String.valueOf(pagination.getFirstPage()))%>" 
		class="page"><%=pagination.getFirstPage()%></a>
<a class="page--blank">...</a>
<%}%>
<%for(int i = pagination.getFirstPagedPage(); i < pagination.getPage(); i++ ){%>
<%if(i != pagination.getFirstPage()){%>
<a href="<%=UrlBuilder.getUrlForController(request, ControllerPath.TIENDA, 
		ActionNames.BUSCAR, ParameterNames.PAGE, String.valueOf(i))%>" 
		class="page"><%=i%></a>		
<%}%>
<%}%>

<%if(pagination.getPage() != pagination.getFirstPage() && pagination.getPage() != pagination.getTotalPages()){%>
<a href="<%=UrlBuilder.getUrlForController(request, ControllerPath.TIENDA,
		ActionNames.BUSCAR, ParameterNames.PAGE, String.valueOf(pagination.getPage()))%>" 
		style="padding-bottom: 2%; width: 2%;" class="page"><%=pagination.getPage()%></a>
<%}else{%>
<a href="#" 
		style="padding-bottom: 2%; width: 2%;" class="page"><%=pagination.getPage()%></a>
<%}%>
<%for(int i = pagination.getPage()+1; i <= pagination.getLastPagedPage(); i++ ){%>
<%if(i != pagination.getTotalPages()){%>
<a href="<%=UrlBuilder.getUrlForController(request, ControllerPath.TIENDA, 
		ActionNames.BUSCAR, ParameterNames.PAGE, String.valueOf(i))%>" 
	 class="page"><%=i%></a>
	 <%}%>
<%}%>
<%if(pagination.getPage() != pagination.getTotalPages()){%>
<a class="page--blank">...</a>
<a href="<%=UrlBuilder.getUrlForController(request, ControllerPath.TIENDA, 
		ActionNames.BUSCAR, ParameterNames.PAGE, String.valueOf(pagination.getTotalPages()))%>" 
		 class="page"><%=pagination.getTotalPages()%></a>
		 <%}%>

</div>
<%}%>
<%@include file="/html/commons/usuario/footer.jsp"%>


