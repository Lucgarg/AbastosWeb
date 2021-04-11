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


<a href="<%=UrlBuilder.getUrlForController(request, ControllerPath.TIENDA,
		ActionNames.BUSCAR, ParameterNames.PAGE, String.valueOf(pagination.getPage()))%>" 
		style="padding-bottom: 2%; width: 2%;" class="page"><%=pagination.getPage()%></a>

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