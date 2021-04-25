
<%@page
	import="com.abastos.market.web.util.*, java.util.*, com.abastos.model.*"%>
<%@include file="/html/commons/usuario/header.jsp"%>
<div class="logo">
	<figure>
		<img src="<%=UrlBuilder.getUrlforImg(request, "logo.jpg")%>" alt="">
	</figure>
</div>
<section class="localizacion">
	<%if(errores.printError(ParameterNames.ERROR) != null){%>
	<p class="error generic"><%=errores.printError(ParameterNames.ERROR)%></p>
	<%}%>
	<form action="<%=UrlBuilder.getUrl(request, ControllerPath.TIENDA)%>"
		method="post" autocomplete="off" id="formSearch">
		<input type="hidden" name=<%=ActionNames.ACTION%>
			value=<%=ActionNames.BUSCAR%>> <label class="select"
			for="pais"> <select name=<%=ParameterNames.PAIS%> id="pais">
				<%List<Pais> paises=  (List<Pais>)request.getAttribute(AttributesNames.PAISES);%>

				<option value="0">--selecciona un pais--</option>
				<%if(paises!= null){%>
				<%for(Pais p: paises){%>
				<option value=<%=p.getId()%>><%=p.getNombre()%></option>
				<%}%>
				<%}%>
		</select>
		</label> <label class="select" for="comunidad"> <select
			name=<%=ParameterNames.COMUNIDAD%> id="comunidad">
				<option value="0" selected>--selecciona una comunidad--</option>
		</select>
		</label> <label class="select" for="provincia"> <select
			name=<%=ParameterNames.PROVINCIA%> id="provincia">
				<option value="0" selected>--selecciona una Provincia--</option>
		</select>
		</label> <label class="select" for="localidad"> <select
			name="<%=ParameterNames.LOCALIDAD%>" id="localidad">
				<option value="0" selected>--selecciona una localidad--</option>
		</select>
		</label><br> <input type="submit" class="Buscar" value="Buscar">
	</form>


</section>
<%@include file="/html/commons/usuario/footer.jsp"%>